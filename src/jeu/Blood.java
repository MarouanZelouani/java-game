package jeu;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Blood {
    
    private Rectangle rect1;
    private Rectangle rect2;
    
    public Blood () {
        rect1 = new Rectangle (100, 10);
        rect1.setStroke(Color.BLACK);
        rect1.setFill(Color.TRANSPARENT);
        //rect1.setTranslateX(690);
        //rect1.setTranslateY(580);
        rect1.setLayoutX(30);
        rect1.setLayoutY(530);
        
        rect2 = new Rectangle(98, 8);
        //rect2.setStroke(Color.BLACK);
        rect2.setFill(Color.YELLOW);
        //rect2.setTranslateX(691);
        //rect2.setTranslateY(581);
        rect2.setLayoutX(31);
        rect2.setLayoutY(531);
    }
    
    
    
    public void change(){
    	rect1.setTranslateX(650);
        rect1.setTranslateY(10);
        rect2.setTranslateX(651);
        rect2.setTranslateY(11);
    }

    public void  removeplace() {
        rect1.setLayoutX(30);
        rect1.setLayoutY(30);
        rect2.setLayoutX(31);
        rect2.setLayoutY(31);
    }
    
    public Rectangle getRect1 () {
        return rect1;
    }
    
    public Rectangle getRect2 () {
        return rect2;
    }
    
    public void getdamaged (int damage) {
        if (damage % 25 == 0){
            this.rect2.setWidth(rect2.getWidth() - 5);
        }
    }
    public void getdamaged2 () {
            this.rect2.setWidth(rect2.getWidth() - 10);
    }
    
    public boolean stillAlive () {
        return rect2.getWidth() <= 0;
    }
    
    public void resetBlood () {
        this.rect2.setWidth(98);
    }
}
