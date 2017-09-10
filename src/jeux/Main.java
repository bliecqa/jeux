package jeux;


import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application{
	static Stage stage;
	static Scene menu;
	Jeu jeu;
	@FXML
	Button morpion,snake;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root=FXMLLoader.load(getClass().getResource("Main.fxml"));;
		this.stage=stage;
		menu=new Scene(root);
		this.stage.setScene(menu);
		this.stage.show();
	}

	
	@FXML
	public void onMorpionPressed(){
		stage.close();
		System.out.println("Morpion sélectionné");
		jeu=new Morpion(stage,menu);
		Scene gameMorpion=new Scene(jeu);
		stage.setScene(gameMorpion);
		stage.show();
	}
	
	@FXML
	public void onSnakePressed(){
		stage.close();
		System.out.println("+ OU - sélectionné");
		jeu=new Snake(stage,menu);
		Scene gameSnake=new Scene(jeu);
		stage.setX(300);
		stage.setY(0);
		gameSnake.setOnKeyPressed((Snake)jeu);
		stage.setScene(gameSnake);
		stage.show();
	}
}
