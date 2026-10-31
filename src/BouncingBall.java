import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class BouncingBall extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		Circle ball = new Circle(20,Color.BLUE);
		ball.setCenterX(50);
		ball.setCenterY(150);
		
		
		Pane root = new Pane(ball);
		
		Scene scene = new Scene(root,600,300);
		
		//
		 double[] speed = {240} ;
		 boolean[] isResting = {false};
		 //we want the ball to start moving up wards
		 double[] vertSpeed = {-200} ;
		 double gravity = 500;
		 double airResistance = 0.75;
		 double restitution = 0.8;
		long[] previousTime = {0};
		
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				
				
				if(previousTime[0] == 0) {
					previousTime[0]= now;
					return;
				}
				
				double elapsedTime = (now -previousTime[0])/1_000_000_000.0;
				previousTime[0] = now;
				
				// Apply gravity only when the ball is not resting
				if (!isResting[0]) {
				    vertSpeed[0] +=  gravity * elapsedTime;
				    vertSpeed[0] -= airResistance * vertSpeed[0] * elapsedTime;
				}
				speed[0] -= airResistance * speed[0] * elapsedTime;
				double distance = speed[0] * elapsedTime;
				double distanceY = vertSpeed[0] * elapsedTime;
				//I need the eucleadian distance  don't I
				
			    ball.setCenterX(ball.getCenterX() + distance);
			    ball.setCenterY(ball.getCenterY() + distanceY);
				
			    //Checking the bounds to make sure that the ball bounces 
			    //Ceilling
			    if(ball.getCenterX() >= 580 ) {
			    	ball.setCenterX(580);
			    	speed[0]= -Math.abs(speed[0]);
			    }else if(ball.getCenterX() <= 20) {
			    	ball.setCenterX(20);
			    	speed[0] = Math.abs(speed[0]);
			    }
			    
			    // Floor Condition 
			    if(ball.getCenterY() >= 280 ) {
			    	ball.setCenterY(280);
			    	vertSpeed[0]= -vertSpeed[0] * restitution;
			    	
			    	if(Math.abs(vertSpeed[0])< 15) {
				    	   vertSpeed[0] =0;
				    	   isResting[0] = true;
				    	}
			    }else if(ball.getCenterY() <= 20) {
			    	ball.setCenterY(20);
			    	vertSpeed[0] = Math.abs(vertSpeed[0]) * restitution;
			    }
			}
			
		}.start();
		
		stage.setScene(scene);
		stage.setTitle("Bouncing Ball");
		stage.show();
		
	}

}
