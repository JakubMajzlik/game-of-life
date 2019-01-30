package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Main extends Application {
	
	private final int WIDTH = 20;
	private final int HEIGHT = 20;
	private final int CELLSIZE = 40;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = new VBox();
			
			//////////Buttons///////////
			
			HBox buttonsPane = new HBox();
			
			Button startButton = new Button("START");
			Button stopButton = new Button("STOP");
			Button speedupButton = new Button("+");
			Button slowdownButton = new Button("-");
			
			//Adding buttons to hbox
			buttonsPane.getChildren().add(startButton);
			buttonsPane.getChildren().add(stopButton);
			buttonsPane.getChildren().add(speedupButton);
			buttonsPane.getChildren().add(slowdownButton);
			
			root.getChildren().add(buttonsPane);
			
			////////////GRID////////////
			
			GridPane grid = new GridPane();
			
			//Setting up the grid for cells
			grid.setGridLinesVisible(true);
			grid.setPadding(new Insets(5));
			grid.setVgap(1.0);
			grid.setHgap(1.0);
			
			//Adding cells to the grid
			for(int i = 0; i < WIDTH; i++) {
				for(int j = 0; j < HEIGHT; j++) {
					Rectangle cell = new Rectangle(CELLSIZE, CELLSIZE, Color.WHITE);
					grid.add(cell, i, j);
				}
			}
			
			root.getChildren().add(grid);
			
			
			Controller controller = new Controller();
			
			controller.setGrid(grid);
			controller.setStartButton(startButton);
			controller.setStopButton(stopButton);
			controller.setSpeedupButton(speedupButton);
			controller.setSlowdownButton(slowdownButton);
			
			controller.handleEvents();
			
			Scene scene = new Scene(root,400,400);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
