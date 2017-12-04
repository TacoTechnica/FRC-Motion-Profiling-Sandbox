package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.robot.Robot;
import org.usfirst.frc.team694.robot.RobotMap;
import org.usfirst.frc.team694.trajectory.Path;
import org.usfirst.frc.team694.trajectory.Vector2;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class DrivetrainMotionCurveCommand extends Command {
	private static final double BOT_WIDTH = 10d; // UNITS?!?!

	private Path path;
	private Vector2[] waypoints;

	// Our current progress from the trajectory
	private double trajectoryProgress = 0;

	// TODO: Uncomment variables once the current command works
	// Holds our previous index, letting us know if we've moved to a new segment
	//private int lastIndex = -1; 
	// Holds the arc length so we don't have to recalculate it every iteration
	//private double arcLengthCache; 

    public DrivetrainMotionCurveCommand(Vector2[] waypoints) {
    	this.waypoints = waypoints;
    	path = new Path(waypoints);
    	path.generate();

        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	// TODO: Is this redundant?
    	if (trajectoryProgress >= path.getSegmentCount()) {
    		return;
    	}

    	// Distances traveled (inches)
    	double deltaLeft = Robot.drivetrain.leftEncoderDistance();
    	double deltaRight = Robot.drivetrain.rightEncoderDistance();
    	double delta = (deltaLeft + deltaRight) / 2;

    	// Get the change in progress from the last frame, and increase our trajectory progress with it
    	double deltaProgress = delta / path.getSegmentArcLength(trajectoryProgress);
    	trajectoryProgress += deltaProgress;

    	Robot.drivetrain.resetEncoders();

    	// Set drivetrain velocities
    	double targetSpeed = path.getDerivative(trajectoryProgress).getMagnitude();
    	targetSpeed *= path.getTargetSpeed(trajectoryProgress);
    	targetSpeed /= RobotMap.DRIVETRAIN_WHEEL_MAX_SPEED;

    	double targetAngle = path.getAngle(trajectoryProgress);
    	double[] drivetrainVelocities = getDrivetrainVelocities(targetSpeed, targetAngle);
    	Robot.drivetrain.tankDrive(drivetrainVelocities[0], drivetrainVelocities[1]);

    	// TODO: Add once the more basic method works lastIndex = (int) trajectoryProgress;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return trajectoryProgress >= path.getSegmentCount();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// Do nothing
    }

    // TODO: Should these be moved to a different, static class?

    // Returns left and right drivetrain velocities given desired parameters
    // as an array: double[] {left, right}
    // netVelocity: net velocity of bot
    // angularVelocity: radians per second
    public double[] getDrivetrainVelocities(double netVelocity, double angularVelocity) {
    	double[] result = new double[2];
    	result[0] = netVelocity + BOT_WIDTH / (2d * angularVelocity);
    	result[1] = 2*netVelocity - result[0];
    	/* Bad math?
    	result[1] = netVelocity - 0.5*angularVelocity;
    	result[0] = 2*netVelocity - result[1];
    	*/
    	return result;
    }

}
