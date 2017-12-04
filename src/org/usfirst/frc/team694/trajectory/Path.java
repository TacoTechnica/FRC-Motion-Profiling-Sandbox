package org.usfirst.frc.team694.trajectory;

import java.util.ArrayList;
import java.util.Arrays;

public class Path {

	private ArrayList<Vector2> waypoints;
	private PathSegment[] segments;

	public Path(Vector2... waypoints) {
		this.waypoints = new ArrayList<Vector2>(Arrays.asList(waypoints));
	}

	// Gets the spline we should use, for a certain progress
	private PathSegment getSegment(double progress) {
		int segmentIndex = (int) progress - 1;
		return segments[ segmentIndex ];
	}

	// TODO: Is this dumb?
	// Gets the relative progress to our desired spline
	private double getRelativeProgress(double progress) {
		int splineIndex = (int) progress;
		return progress + 1 - splineIndex;
	}

	public void addWaypoint(Vector2 waypoint) {
		waypoints.add(waypoint);
	}

	public void generate() {

		// Interpolation must have 4 points.
		// If we have less, we can probably get away with other interpolation
		// methods (quadratic for 3, linear for 2)
		if (waypoints.size() < 4) {
			System.err.println("CRITICAL TRAJECTORY GENERATION PROBLEM: "
					+ "Waypoint count less than 4! Other interpolation methods not "
					+ "supported");
			System.exit(0);
			return;
		}

		// 4 points in a spline, we only care about the middle parts!
		segments = new PathSegment[waypoints.size() - 3];

		for(int i = 0; i < waypoints.size() - 3; i++) {
			segments[i] = new PathSegment(new HermiteSpline(
					waypoints.get(i),
					waypoints.get(i + 1), 
					waypoints.get(i + 2),
					waypoints.get(i + 3)));
			segments[i].generate();
		}
	}

	// Converts time (seconds) into progress (double of 0 to number of splines - 1)
	/*public double timeToProgress(double time) {
		int progCount = 0;
		PathSegment current = segments[0];
		while(time > current.getTimeDuration()) {
			time -= current.getTimeDuration();
			progCount += 1;
			current = segments[progCount];
		}
		// Return how many segments we've passed plus the remainder
		return progCount + (time / current.getTimeDuration());
	}*/

	// progress: how far we've gone between each spline.
	// ex. 2.5 means half way between spline of index 2 and 3
	public Vector2 getValue(double progress) {
		PathSegment segment = getSegment(progress);
		return segment.getValue( getRelativeProgress(progress) );
	}

	public Vector2 getDerivative(double progress) {
		PathSegment segment = getSegment(progress);
		return segment.getDerivative( getRelativeProgress(progress) );		
	}

	public double getAngle(double progress) {
		PathSegment segment = getSegment(progress);
		return segment.getAngle( getRelativeProgress(progress) );
	}

	// TODO: Print statements to make sure this logic works
	public double getArcLength(double progress) {
		double sum = 0;
		int index = 0;
		while(progress > 1) {
			sum += segments[index].getArcLength(0,1);
			index++;
			progress -= 1;
		}
		sum += segments[index].getArcLength(0,progress);
		return sum;
	}

	public double getSegmentArcLength(double progress) {
		PathSegment segment = getSegment(progress);
		return segment.getArcLength(0, 1);
	}

	public double getTargetSpeed(double progress) {
		PathSegment segment = getSegment(progress);
		return segment.getDriveSpeed();
	}

	public int getSegmentCount() {
		return segments.length;
	}

}
