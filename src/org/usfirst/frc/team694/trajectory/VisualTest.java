package org.usfirst.frc.team694.trajectory;

import javafx.scene.Group;

import org.usfirst.frc.team694.robot.commands.DrivetrainMotionCurveExampleCommand;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.Scene;

// This is super sh*tty
// TODO: Use another library instead of making your own
public class VisualTest extends Application {

	private final int WIDTH = 640;
	private final int HEIGHT = 640;

	private final double ZOOM = 0.1;

	private Path path;
	//private ParametricSpline spline;

	private Vector2 globalOffset;

	Vector2 point[] = new Vector2[8];


	private void initGraphing() {

		/*point[0] = new Vector2(0, 10);
		point[1] = new Vector2(10, 20);
		point[2] = new Vector2(20, 0);
		point[3] = new Vector2(30, 15);
		point[4] = new Vector2(50, 10);
		point[5] = new Vector2(50, 20);
		point[6] = new Vector2(60, 0);
		point[7] = new Vector2(70, 15);
		*/
		point = DrivetrainMotionCurveExampleCommand.points;

		/*spline = new HermiteSpline(point[0], point[1], point[2], point[3]);
		spline.generateBiCubicSpline();
		*/
		path = new Path(point);
		path.generate();
		globalOffset = new Vector2(-200+WIDTH/2, HEIGHT/2);
	}


	private void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.BLACK);
		
		for(Vector2 point : point) {
			Vector2 pos = point.clone();
			pos.multiplyScalar(1/ZOOM);
			pos.add(globalOffset);
			double s = 10;
			gc.fillOval(pos.x - s/2, pos.y - s/2, s, s);
		}

		Vector2 prev = null;
		for(double t = 1; t < point.length - 2; t += 0.01) {
			Vector2 current = path.getValue(t);
			current.multiplyScalar(1/ZOOM);
			current.add(globalOffset);
			if (prev == null) {
				prev = current;
			}
			gc.strokeLine(prev.x, prev.y, current.x, current.y);
			prev = current;
		}

		/*for(int xx = -WIDTH; xx < WIDTH; xx++) {
			Vector2 current = new Vector2(xx, func.getValue(xx*ZOOM)/ZOOM);
			current.add(globalOffset);
			System.out.println("(" + xx + ", " + func.getValue(xx) + ")");
			if (prev == null) {
				prev = current;
			}
			gc.strokeLine(prev.x, prev.y, current.x, current.y);
			prev = current;
		}*/
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initGraphing();
		primaryStage.setTitle("Test");
        Group root = new Group();

        root.setScaleY(-1);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        draw(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        
	}

	public static void main(String[] args) {
        launch(args);
    }
}
