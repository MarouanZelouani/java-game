package jeu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class balle extends entite {
	private Point2D d=new Point2D(0,0);
	public balle(arme  arme) {
		Image b= null;
	try {
		b=new Image(new FileInputStream("Images/Ballon.png"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}corps = new ImageView(b);	
	corps.setTranslateX(arme.getCorps().getTranslateX());
	corps.setTranslateY(arme.getCorps().getTranslateY()); 
	ud(arme.getCorps().getRotate()-90);
	}
	private void ud(double routation) {
		Point2D p;
		double x=Math.cos(Math.toRadians(routation));
		double y=Math.sin(Math.toRadians(routation));
		p = new Point2D(x,y);
		d=p.normalize().multiply(4);
	}
	public void move() {
		corps.setTranslateX(corps.getTranslateX()+d.getX());
		corps.setTranslateY(corps.getTranslateY()+d.getY());
	}
	
	public void getsound () {
    	String url = "Images/Rasengan.mp3";
    	Media m = new Media(Paths.get(url).toUri().toString());
    	AudioClip mp = new AudioClip(m.getSource());
		//mp.play();
		
		String url2 = "Images/effect.mp3";
    	Media m2 = new Media(Paths.get(url2).toUri().toString());
    	AudioClip mp2 = new AudioClip(m2.getSource());
		mp2.play();
		
		mp.setVolume(0.1);
		mp2.setVolume(0.2);
    }	
}