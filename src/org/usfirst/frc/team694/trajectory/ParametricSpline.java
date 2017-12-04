package org.usfirst.frc.team694.trajectory;

public abstract class ParametricSpline {

	// Three points for our cubic spline
	// p1 and p2 are our start and end coordinates
	// p0 and p3 are outside of our equation, and are used to
	//           find the derivatives used in the interpolation
	private Vector2 p0, p1, p2, p3;

	// Parametric spline functions for our X and Y components
	private InterpolationFunction parametricX, parametricY;

	public ParametricSpline(Vector2 p0, Vector2 p1, Vector2 p2, Vector2 p3) {
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		parametricX = createNewInterpolationFunction();
		parametricY = createNewInterpolationFunction();
	}

	public void generateBiCubicSpline() {
		parametricX.setPoints(p0.x, p1.x, p2.x, p3.x);
		parametricY.setPoints(p0.y, p1.y, p2.y, p3.y);
	}

	public Vector2 getValue(double progress) {
		double x = parametricX.getValue(progress);
		double y = parametricY.getValue(progress);
		return new Vector2(x,y);
	}
	public Vector2 getDerivative(double progress) {
		double dx = parametricX.getDerivative(progress);
		double dy = parametricY.getDerivative(progress);
		return new Vector2(dx, dy);
	}

	public double getAngle(double progress) {
		return Math.atan2(parametricY.getDerivative(progress), parametricX.getDerivative(progress));
	}

	protected abstract InterpolationFunction createNewInterpolationFunction();

	public abstract double getArcLength(double start, double end);


}
