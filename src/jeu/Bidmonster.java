package jeu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class Bidmonster extends entite{
	static int number =0;
	public Blood blood;
	public Blood getBlood() {
		return blood;
	}
	public void setBlood(Blood blood) {
		this.blood = blood;
	}
public Bidmonster(Zone zone)  {
	Image c= null;
	try {
		c=new Image(new FileInputStream("Images/dragon.png"));
		
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
    public static int getNumber() {
	return number;
    }
    static public void setNumber(int i) {
    	number=i;
    }
    
    public void getsound () {
    	String url = "Images/taunt.wav";
    	Media m = new Media(Paths.get(url).toUri().toString());
    	AudioClip mp = new AudioClip(m.getSource());
		mp.play();
    }
}
