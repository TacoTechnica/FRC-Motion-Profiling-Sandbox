package org.usfirst.frc.team694.trajectory;

/**
 *  Holds a path segment, including the spline and other trajectory information
 */
public class PathSegment {

	// Speed to go through the curve
	private double driveSpeed = 1;
	private ParametricSpline spline;

	public PathSegment(ParametricSpline spline) {
		this.spline = spline;
	}

	public void generate() {
		spline.generateBiCubicSpline();
	}

	public void setSpeed(double driveSpeed) {
		this.driveSpeed = driveSpeed;
	}

	public double getDriveSpeed() {
		return driveSpeed;
	}

	public double getAngle(double progress) {
		return spline.getAngle(progress);
	}

	public Vector2 getValue(double progress) {
		return spline.getValue(progress);
	}
	
	public Vector2 getDerivative(double progress) {
		return spline.getDerivative(progress);
	}

	public double getArcLength(double progressStart, double progressEnd) {
		return spline.getArcLength(progressStart, progressEnd);
	}

}
