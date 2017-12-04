package org.usfirst.frc.team694.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public interface RobotMap {
	int LEFT_TOP_MOTOR_PORT = 1;
    int LEFT_BOTTOM_MOTOR_PORT = 2;
    int RIGHT_TOP_MOTOR_PORT = 3;
    int RIGHT_BOTTOM_MOTOR_PORT = 4;

    int DRIVETRAIN_ENCODERS_PULSES_PER_REVOLUTION = 256;
    double DRIVETRAIN_WHEEL_DIAMETER = 4.0;
    double DRIVETRAIN_ENCODERS_FACTOR = 4.0; // output must be scaled *down* by 4 due to type of encoder
    double DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION = Math.PI * DRIVETRAIN_WHEEL_DIAMETER;

    double DRIVETRAIN_ENCODERS_MAX_RPM = 0; // TODO: Figure this out!
    double DRIVETRAIN_WHEEL_MAX_SPEED = DRIVETRAIN_ENCODERS_MAX_RPM * DRIVETRAIN_ENCODERS_INCHES_PER_REVOLUTION;

    ////////////////////////////////////////////////////////////////////////////
    // SOLENOIDS: //////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    int GEAR_SHIFT_SOLENOID_PORT = 0;
}
