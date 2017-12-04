package org.usfirst.frc.team694.trajectory;

public class HermiteSpline extends ParametricSpline {

	public HermiteSpline(Vector2 p0, Vector2 p1, Vector2 p2, Vector2 p3) {
		super(p0, p1, p2, p3);
	}

	@Override
	protected InterpolationFunction createNewInterpolationFunction() {
		return new HermiteFunction();
	}

	@Override
	public double getArcLength(double start, double end) {
		double sumLength = 0;
		double dt = (end - start) / TrajectoryConstants.ARC_LENGTH_SAMPLE_COUNT;

		for(int i = 0; i < TrajectoryConstants.ARC_LENGTH_SAMPLE_COUNT; i++) {
			// TODO: We're adding line segments. Should we add derivatives?
			Vector2 delta = getValue(dt*i+dt).subtract(getValue(dt*i));
			sumLength += delta.getMagnitude();
		}
		return sumLength;
	}

}
