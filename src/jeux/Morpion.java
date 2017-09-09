package jeux;

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Morpion extends Jeu {
	Stage stage;
	Scene menu;
	boolean joueur = true;
	Button quitter = new Button("QUITTER");
	Label label = new Label("Resultat : ");
	Cell[][] cells = new Cell[3][3];

	Morpion(Stage s, Scene sc) {
		stage = s;
		menu = sc;
		setPrefSize(600, 700);
		label.setTextFill(Color.BLACK);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Cell c = new Cell();
				c.setTranslateY(i * 200);
				c.setTranslateX(j * 200);
				cells[i][j] = c;
				getChildren().add(c);

			}
		}
		quitter.setTranslateY(600);
		quitter.setTranslateX(500);
		quitter.setOnAction(e -> {
			Alert a = new Alert(AlertType.CONFIRMATION, "Voulez vous vraiment retourner au Menu ?", null);
			Optional<ButtonType> result = a.showAndWait();
			if (result.get() == ButtonType.OK) {
				stage.setScene(menu);
			}
		});
		label.setTranslateY(600);
		getChildren().addAll(label, quitter);

	}

	boolean isTie() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!cells[i][j].prise)
					return false;
			}
		}
		return true;
	}

	boolean hasWon() {
		for (int i = 0; i < 3; i++) {
			if (cells[i][0].text.getText().equals(cells[i][1].text.getText())
					&& cells[i][1].text.getText().equals(cells[i][2].text.getText())&& cells[i][0].prise) {
				return true;
			}
			if (cells[0][i].text.getText().equals(cells[1][i].text.getText())
					&& cells[1][i].text.getText().equals(cells[2][i].text.getText())&& cells[0][i].prise) {
				return true;
			}
		}
		if (cells[0][0].text.getText().equals(cells[1][1].text.getText())
				&& cells[1][1].text.getText().equals(cells[2][2].text.getText())&& cells[0][0].prise) {
			return true;
		}
		if (cells[2][0].text.getText().equals(cells[1][1].text.getText())
				&& cells[1][1].text.getText().equals(cells[0][2].text.getText())&& cells[2][0].prise) {
			return true;
		}
		return false;
	}

	private class Cell extends StackPane {
		Text text = new Text();
		Rectangle r = new Rectangle(200, 200);
		boolean prise = false;

		public Cell() {

			r.setFill(null);
			r.setStroke(Color.BLACK);
			text.setTextAlignment(TextAlignment.CENTER);
			text.setFont(Font.font(50));
			setOnMouseClicked(e -> {

				if (!prise && !hasWon()) {
					if (joueur) {
						onJ1pressed();
						joueur = false;
						prise = true;
					} else {
						onJ2pressed();
						joueur = true;
						prise = true;
					}

				}
				if (hasWon()) {
					if (!joueur)
						label.setText("Résultat :  Joueur 1 a gagné");
					if (joueur)
						label.setText("Résultat :  Joueur 2 a gagné");
				}
				if(isTie())
					label.setText("Résultat :   Egalité");

			});
			setAlignment(Pos.CENTER);
			getChildren().addAll(r, text);

		}

		private void onJ2pressed() {
			// TODO Auto-generated method stub
			text.setText("O");
		}

		private void onJ1pressed() {
			text.setText("X");
		}

	}

}
