package org.usfirst.frc.team694.robot.commands;

import org.usfirst.frc.team694.trajectory.Vector2;

public class DrivetrainMotionCurveExampleCommand extends DrivetrainMotionCurveCommand {

	public static Vector2[] points = new Vector2[]{
			new Vector2(-10, 0), // Direction vector
			new Vector2(0, 0), // Start position vector
			new Vector2(10, 10),
			new Vector2(20, 0), // End position vector
			new Vector2(30, 0)
	};

	public DrivetrainMotionCurveExampleCommand() {
		super(points);
	}

}
