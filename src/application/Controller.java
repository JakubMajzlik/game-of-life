package application;

import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
	
	private GridPane grid;
	
	private Button startButton;
	private Button stopButton;
	private Button speedupButton;
	private Button slowdownButton;
	
	private static boolean running = false;
	
	private static double speed = 0.1;
	
	
	public Button getStartButton() {
		return startButton;
	}

	public void setStartButton(Button startButton) {
		this.startButton = startButton;
	}

	public Button getStopButton() {
		return stopButton;
	}

	public void setStopButton(Button stopButton) {
		this.stopButton = stopButton;
	}

	public Button getSpeedupButton() {
		return speedupButton;
	}

	public void setSpeedupButton(Button speedupButton) {
		this.speedupButton = speedupButton;
	}

	public Button getSlowdownButton() {
		return slowdownButton;
	}

	public void setSlowdownButton(Button slowdownButton) {
		this.slowdownButton = slowdownButton;
	}

	public GridPane getGrid() {
		return grid;
	}

	public void setGrid(GridPane grid) {
		this.grid = grid;
	}
	
	private void doCycle() {
		if(running) return;
		running = true;
		
		/*Thread cycleThread = new Thread(() ->{
			while(running) {
				System.out.println("Speed: " + speed);
				try {
					Thread.sleep((long)(1000*speed));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}	
		});	
		cycleThread.run();*/
	}

	public void handleEvents() {
		
		//Add events handling for every cell in the grid
		grid.getChildren().forEach(e->{
			e.setOnMouseClicked(v->{
				if(v.getButton() == MouseButton.PRIMARY) {
					Rectangle cell;
					
					//If we click to grid lines, e will be Group type, so casting will fail.
					try {
						cell = (Rectangle)e;
					} catch(ClassCastException ex) {
						return;
					}
					
					
					if(cell.getFill() == Color.WHITE) {
						cell.setFill(Color.BLACK);
					}else {
						cell.setFill(Color.WHITE);
					}
				}
			});
		});
		
		///////////BUTTTONS///////////
		
		startButton.setOnAction(v->{
			doCycle();
		});
		
		stopButton.setOnAction(v->{
			running = false;
		});
		
		speedupButton.setOnAction(v->{
			speed += 0.1;
			if(speed > 1.0) {
				speed = 1.0;
			}
		});
		
		slowdownButton.setOnAction(v->{
			speed -= 0.1;
			if(speed < 0.0) {
				speed = 0.0;
			}
		});
		
		
	}

}
