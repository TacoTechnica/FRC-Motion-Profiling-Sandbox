package org.usfirst.frc.team694.trajectory;

public abstract class InterpolationFunction {

	protected double y0, y1, y2, y3;

	public void setPoints(double y0, double y1, double y2, double y3) {
		this.y0 = y0;
		this.y1 = y1;
		this.y2 = y2;
		this.y3 = y3;
	}

	public abstract double getValue(double x);
	public abstract double getDerivative(double x);		
}
