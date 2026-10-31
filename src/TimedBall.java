import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TimedBall extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage)  {
		Circle ball = new Circle(20,Color.BLUE);
		ball.setCenterX(50);
		ball.setCenterY(150);
		
		Pane root =new Pane(ball);
		Scene scene = new Scene(root,600,300);
		
		
		//speed is measure in pixels per second
		double speed = 240;
		
		// Store the previous animation time 
		long[] previousTime = {0};
		
		new AnimationTimer() {
			@Override
			public void handle(long now) {
				//skip movement on the first update
				
				if(previousTime[0] ==0) {
					previousTime[0] =now ;
					return;
				}
				
				// Calculate elapsed time in seconds
				double elapsedTime = (now -previousTime[0])/1_000_000_000.0;
				previousTime[0] = now ;
				
				//move according to speed and elapsed time
				double distance = speed * elapsedTime;
				
				ball.setCenterX(ball.getCenterX() + distance);
			}
		}.start();
		
		stage.setScene(scene);
		stage.setTitle("Timed Ball");
		stage.show();
	}

}
