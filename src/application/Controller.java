package application;

import javafx.application.Platform;
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
	
	private int width;
	private int height;
	
	private static boolean running = false;
	
	private static double speed = 0.1;
	
	private Rectangle[][] cellArray;
	
	public Controller(int width, int height, GridPane grid, Rectangle[][] cellArray) {
		this.width = width;
		this.height = height;
		this.grid = grid;
		this.cellArray = cellArray;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public static boolean isRunning() {
		return running;
	}

	public static void setRunning(boolean running) {
		Controller.running = running;
	}

	public static double getSpeed() {
		return speed;
	}

	public static void setSpeed(double speed) {
		Controller.speed = speed;
	}

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
	
	/**
	 * Performs one step of the Game of Life
	 * TODO: Optimization of algorithm
	 */
	private void doStep() {
	
		boolean[][] boolCellArray = new boolean[height][width];
		
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				int neighbors = 0;
				
				//Getting the number of living neighbors
				for(int i = -1; i < 2; i++) {
					for(int j = -1; j < 2; j++) {
						if(i == 0 && j == 0) continue;
						if(row == 0 && i == -1) continue;
						if(row == height - 1 && i == 1) continue;
						if(col == 0 && j == -1) continue;
						if(col == width -1 && j == 1) continue;

						if(cellArray[row + i][col + j].getFill() == Color.BLACK) neighbors++;
					}
				}
				
				//Applying the rules
				if(cellArray[row][col].getFill() == Color.BLACK) { // live cell
					if(neighbors < 2 || neighbors > 3) {
						boolCellArray[row][col] = false;
					} else {
						boolCellArray[row][col] = true;
					}
				} else { // dead cell
					if(neighbors == 3) {
						boolCellArray[row][col] = true;
					} else {
						boolCellArray[row][col] = false;
					}
				}
			}
		}
		
		//Redrawing the grid with new cells
		for(int row = 0; row < height; row++) {
			for(int col = 0; col < width; col++) {
				if(boolCellArray[row][col] == true) cellArray[row][col].setFill(Color.BLACK);
				if(boolCellArray[row][col] == false) cellArray[row][col].setFill(Color.WHITE);
			}
		}
		
	}
	
	/**
	 * Starts performing steps until the stop button is pressed
	 */
	private void startAlgorithm() {
		Thread cycleThread = new Thread(() ->{
			while(running) {
					try {
						Thread.sleep((long)(1000-1000*speed));
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					
					Platform.runLater(()->{
						doStep();
					});
			}	
		});	
		cycleThread.start();
	}

	/**
	 * Handles input events
	 */
	public void handleInputEvents() {
		
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
		
		//Start button
		startButton.setOnAction(v->{
			if(running) return;
			running = true;
			startAlgorithm();
		});
		
		//Stop button
		stopButton.setOnAction(v->{
			running = false;
		});
		
		//Speedup button
		speedupButton.setOnAction(v->{
			speed += 0.1;
			if(speed > 1.0) {
				speed = 1.0;
			}
		});
		
		//Slowdown button
		slowdownButton.setOnAction(v->{
			speed -= 0.1;
			if(speed < 0.0) {
				speed = 0.0;
			}
		});
		
		
	}

}
