package jeux;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Morpion extends Jeu{
	
	
	
	Morpion(){
		setPrefSize(600, 600);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				Cell c=new Cell();
				c.setTranslateX(i*200);
				c.setTranslateY(j*200);
				getChildren().add(c);
			}
		}
	}

	
	private class Cell extends StackPane{
		Rectangle r=new Rectangle(200,200);
		public Cell(){
			r.setFill(null);
			r.setStroke(Color.BLACK);
			getChildren().add(r);
			
		}
	}
	
}
