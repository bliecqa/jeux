package jeux;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PlusMoins extends Jeu implements EventHandler<KeyEvent>{
	Stage stage;
	Scene menu;
	public PlusMoins(Stage stage, Scene menu) {
		// TODO Auto-generated constructor stub
		
		this.stage=stage;
		this.menu=menu;
		setPrefSize(600	, 700);
		
	}
	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		System.out.println("hello");
	}

}
