package application;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller {
	
	private GridPane grid;
	
	
	public GridPane getGrid() {
		return grid;
	}

	public void setGrid(GridPane grid) {
		this.grid = grid;
	}

	public void handleEvents() {
		
		//Add events handling for every cell in the grid
		grid.getChildren().forEach(e->{
			e.setOnMouseClicked(v->{
				if(v.getButton() == MouseButton.PRIMARY) {
					//TODO: Exception in thread "JavaFX Application Thread" java.lang.ClassCastException: 
					//javafx.scene.Group cannot be cast to javafx.scene.shape.Rectangle
					Rectangle cell = (Rectangle)e;
					
					if(cell.getFill() == Color.WHITE) {
						cell.setFill(Color.BLACK);
					}else {
						cell.setFill(Color.WHITE);
					}
				}
			});
		});
	}

}
