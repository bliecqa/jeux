package jeux;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Snake extends Jeu implements EventHandler<KeyEvent> {
	Stage stage;
	Scene menu;
	Field field = new Field();
	Label label=new Label("Résultat : ");
	final int XSCREEN=1000,YSCREEN=800;
	public enum Direction {
		UP, DOWN, RIGHT, LEFT

	}

	Direction dir = Direction.RIGHT;

	public Snake(Stage stage, Scene menu) {
		// TODO Auto-generated constructor stub
		this.stage = stage;
		this.menu = menu;
		setPrefSize(XSCREEN, YSCREEN);
		animate.start();
		label.setTranslateX(0);
		label.setTranslateY(YSCREEN-100);
		label.setTextFill(Color.BROWN);
		label.setFont(Font.font(25));
		getChildren().addAll(field,label);

	}

	AnimationTimer animate = new AnimationTimer() {

		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			if (dir == Direction.RIGHT) {
				field.worm.move(field.worm.x+5, field.worm.y);
				if(field.worm.x>field.background.getWidth()-25){
					animate.stop();
					showFail();
				}
			}
			if (dir == Direction.LEFT) {
				field.worm.move(field.worm.x-5, field.worm.y);
				if(field.worm.x<5){
					animate.stop();
					showFail();
				}
			}
			if (dir == Direction.DOWN) {
				field.worm.move(field.worm.x, field.worm.y+5);
				if(field.worm.y>field.background.getHeight()-25){
					animate.stop();
					showFail();
				}
			}
			if (dir == Direction.UP) {
				field.worm.move(field.worm.x, field.worm.y-5);
				if(field.worm.y<5){
					animate.stop();
					showFail();
				}
			}
			
			if(field.food.food.contains(field.worm.body.getX()+field.worm.body.getWidth()/2, field.worm.body.getY()+field.worm.body.getHeight()/2)){
				field.food.food.setX(field.food.r.nextInt((int) field.background.getWidth()));
				field.food.food.setY(field.food.r.nextInt((int) field.background.getHeight()));
			}
		
		}

		
	};
	
	public void showFail(){
		label.setText("Résultat :   PERDU !!!!!");
		
	}
	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub

		if (event.getCode() == KeyCode.UP) {
			dir = Direction.UP;
			field.worm.rotate(90);
		}
		if (event.getCode() == KeyCode.DOWN) {
			dir = Direction.DOWN;
			field.worm.rotate(90);
		}
		if (event.getCode() == KeyCode.LEFT) {
			dir = Direction.LEFT;
			field.worm.rotate(0);
		}
		if (event.getCode() == KeyCode.RIGHT) {
			dir = Direction.RIGHT;
			field.worm.rotate(0);
		}
	}

	private class Field extends StackPane {
		Rectangle background = new Rectangle(0, 0, 1000, 700);
		Worm worm = new Worm();
		Food food=new Food();
		Field() {
			getChildren().addAll(background, worm,food);
		
		}
		private class Food extends Pane{
			Random r=new Random();
			Rectangle food=new Rectangle(r.nextInt((int) background.getWidth()),r.nextInt((int) background.getHeight()),20,20);
			Food(){
				food.setFill(Color.YELLOW);
				getChildren().add(food);
			}
		}
		private class Worm extends Pane {
			Rectangle body = new Rectangle(background.getWidth() / 2, background.getHeight() / 2, 20, 10);
			double x = body.getX(), y = body.getY();

			Worm() {
				body.setFill(Color.GREEN);
				getChildren().add(body);
			}

			void move(double x, double y) {
				this.x = x;
				this.y = y;
				body.setX(x);
				body.setY(y);
			}

			void rotate(double degres) {
				body.setRotate(degres);
			}
		}

	}

}
