import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BallSimulation  extends Application{

	@Override
	public void start(Stage stage) {
		Pane pane = new Pane(); // the window 
		pane.setPrefSize(600, 400);
		
		Circle ball = new Circle(50,50,20);
		ball.setFill(Color.BLUE
				);
		
		pane.getChildren().add(ball); //adding the ball to the screen
		
		double[] speed = {5} ;
		double[] verticalSpeed = {4};
		
		Boolean[] isBlue = {true}; //boolean array to alternate between colors  
		
		// animationtimer is a built in function I guess
		AnimationTimer timer  = new AnimationTimer() {
			 @Override 
			 public void handle(long now) {
				 // making the ball move from center x
				 ball.setCenterX(ball.getCenterX() + speed[0]);
				 ball.setCenterY(ball.getCenterY() + verticalSpeed[0]);
				 
				 //Controlling the bounds the ball does not go beyond its radius and beyond its x side
				 if(ball.getCenterX() >= 580 || ball.getCenterX() <= 20) {
					 speed[0] = -speed[0];
					 
					 if(isBlue[0]) {
						 ball.setFill(Color.RED);
					 }else {
						 ball.setFill(Color.BLUE);
					 }
					 isBlue[0] = !isBlue[0];
					 
				 }
				 if(ball.getCenterY() >= 380 || ball.getCenterY() <=20) {
					 verticalSpeed[0] = -verticalSpeed[0];
					 
					 if(isBlue[0]) {
						 ball.setFill(Color.RED);
					 }else {
						 ball.setFill(Color.BLUE);
					 }
					 isBlue[0]= !isBlue[0];
				 }
			 }
		};
		
		Scene scene = new Scene(pane, 600,400);
		stage.setTitle("My First simulation");
		stage.setScene(scene);
		stage.show();
		
		timer.start();
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

}
