package org.usfirst.frc.team694.trajectory;

public class HermiteFunction extends InterpolationFunction {

	private double m0, m1;
	private double bias = 0;
	private double tension = 0;

	@Override
	public void setPoints(double y0, double y1, double y2, double y3) {
		super.setPoints(y0, y1, y2, y3);
		m0  = (y1-y0)*(1+bias)*(1-tension)/2;
		m0 += (y2-y1)*(1-bias)*(1-tension)/2;
		m1  = (y2-y1)*(1+bias)*(1-tension)/2;
		m1 += (y3-y2)*(1-bias)*(1-tension)/2;
	}

	@Override
	public double getValue(double mu) {
		// Start at 0.
		mu -= 1;
		double mu2 = mu * mu;
		double mu3 = mu * mu *mu;

		double a0 =  2*mu3 - 3*mu2 + 1;
		double a1 =    mu3 - 2*mu2 + mu;
		double a2 =    mu3 -   mu2;
		double a3 = -2*mu3 + 3*mu2;

		return(a0*y1+a1*m0+a2*m1+a3*y2);
	}

	@Override
	public double getDerivative(double mu) {
		mu -= 1;
		double mu2 = mu * mu;

		double a0 =  6*mu2 - 6*mu;
		double a1 =  3*mu2 - 4*mu + 1;
		double a2 =  3*mu2 - 2*mu;
		double a3 = -6*mu2 + 6*mu;

		return(a0*y1+a1*m0+a2*m1+a3*y2);
	}


}
