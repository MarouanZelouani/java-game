package jeu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class arme extends entite {
		public arme(player player) {
			Image a= null;
		try {
			a=new Image(new FileInputStream("Images/Arme.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}corps = new ImageView(a);  
			  corps.setTranslateX(player.getCorps().getTranslateX()+65);
			  corps.setTranslateY(player.getCorps().getTranslateY()-10);
			  }
		public double getRotate() {
			return corps.getRotate()-90;
		}
		public void right() {
			corps.setRotate(corps.getRotate()+2);
		}
		public void left() {
			corps.setRotate(corps.getRotate()-2);
		}
		public void Up() {
			corps.setTranslateY(corps.getTranslateY()-10);
		}
		public void Down() {corps.setTranslateY(corps.getTranslateY()+10);}
		public void Left() {corps.setTranslateX(corps.getTranslateX()-10);}
		public void Right() {corps.setTranslateX(corps.getTranslateX()+10);}
}





















