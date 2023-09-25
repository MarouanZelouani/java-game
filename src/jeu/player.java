package jeu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class player extends entite{
	public Blood blood; 
public player(Zone zone,String lien)  {
	Image c= null;
	try {
		c=new Image(new FileInputStream(lien));
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}corps = new ImageView(c);
	((ImageView)corps).setX(0);
	((ImageView)corps).setY(0);
	
	double x=zone.getX1()+(zone.getX2()-zone.getX1())*Math.random();
	double y=zone.getY1()+(zone.getY2()-zone.getY1())*Math.random();
	corps.setTranslateX(x);
	corps.setTranslateY(y);
	this.blood = new Blood();
	}

public void Up() {
	corps.setTranslateY(corps.getTranslateY()-10);
}

public void Down() {
	corps.setTranslateY(corps.getTranslateY()+10);
	
}

public void Left() {

	corps.setTranslateX(corps.getTranslateX()-10);
	
}

public void Right() {

	corps.setTranslateX(corps.getTranslateX()+10);
}
public Blood getBlood () {
	return blood;
}

public void setBlood(Blood blood) {
	this.blood = blood;
}

}
