package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
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
			BorderPane root = new BorderPane();
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
