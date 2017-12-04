package org.usfirst.frc.team694.trajectory;

/** Vector2
 * 
 * Basic 2D Vector class for our position and velocity components
 * 
 *
 */

public class Vector2 {

	public double x, y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2 add(Vector2 vec) {
		this.x += vec.x;
		this.y += vec.y;
		return this;
	}

	public Vector2 subtract(Vector2 vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		return this;
	}

	public Vector2 multiplyScalar(double multiple) {
		this.x *= multiple;
		this.y *= multiple;
		return this;
	}
	
	public double getMagnitudeSquared() {
		return x*x + y*y;
	}

	public double getMagnitude() {
		return Math.sqrt(getMagnitudeSquared());
	}

	public Vector2 normalize() {
		this.x /= getMagnitude();
		this.y /= getMagnitude();
		return this;
	}

	public double getAngle() {
		return Math.atan2(y, x);
	}

	public Vector2 clone() {
		return new Vector2(x,y);
	}

}
