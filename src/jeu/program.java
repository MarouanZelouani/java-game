package jeu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class program extends Application {
	boolean f;
	public  Scene scene;
	private int damage = 10;  
	private int damage2 = 50;
	private int damageinho = 10;
	private MediaPlayer mp;
	private int nbmonstrerest = 10;
	private int nbmonstren = 0;
	private int nbmonstren2 = 0;
	private Text nbmonstre = new Text();
	private int nbballrest = 100;
	private Text nbball = new Text();
	private int nbmonstrerest2 = 10;
	private Text nbmonstre2 = new Text();
	Rectangle r = new Rectangle(135, 30);
    Rectangle b = new Rectangle(115, 30);
    Rectangle bg = new Rectangle(100, 30);
	private Pane contaner = new Pane();
	private Pane contaner2 = new Pane();
	private Pane contaner3 = new Pane();
    Zone Zone1 = new Zone(0, 40, 700,100);
    Zone Zone2 = new Zone(0, 400, 700,200);
    //private player p = new player(Zone2,"Images/hero1.png");
	private player p ;
	private arme a ;
    //private arme a = new arme(p);
    
    private ArrayList<monstre> monstres = new ArrayList<>();
    private ArrayList<balle> balles = new ArrayList<>();
    private ArrayList<balle> balles2 = new ArrayList<>();
    private ArrayList<monstre> monstres2 = new ArrayList<>();
    private ArrayList<balle> bilal = new ArrayList<>();
    private ArrayList<balle> balles3 = new ArrayList<>();    
    private ArrayList<Bidmonster> monstres4 = new ArrayList<>();
    private ArrayList<balle> bilal2 = new ArrayList<>();
    
    EventHandler<KeyEvent> event;
    AnimationTimer animation,animation1;
    
    public void refreche(Stage primaryStage) {
    	//to move balles
    	for(balle balle:balles) {
    		balle.move();
    		}
		//to create montres
    	if(Math.random()<0.01 && monstre.getNumber()<10) {	
        monstre m = new monstre(Zone1);
        contaner.getChildren().add(m.getCorps());
        m.getsound();
        monstres.add(m);   
        nbmonstren++;
        monstre.setNumber(nbmonstren);
    		}
    	
       //to kill montres
    	for(balle balle:balles) {
    		for(monstre monstre:monstres) {
    			if(balle.touche(monstre)) {
    				contaner.getChildren().removeAll(balle.getCorps(),monstre.getCorps());
    				balle.setAlive(false);
    				monstre.setAlive(false);
    				nbmonstrerest--;
    		        nbmonstre.setText("Number of monsters: "+nbmonstrerest);	       
    			}
    		};
    	}
    	
		monstres.removeIf(entite::dead);
		balles.removeIf(entite::dead);
		 if(nbmonstrerest <=0){
    		        WIN(primaryStage, 1);
        }
    }
    
    public void refreche2(Stage primaryStage) {
    	
    	for(balle b : bilal){
            if(b.touche(p)){
                p.getBlood().getdamaged(damage);
                damage++;
            }
        } 
    	//to move balles
    	for(balle balle2:balles2) {
    		balle2.move();
    		} 
		//to create montres
    	if(Math.random()<0.01 && monstre.getNumber()<10) {	
        monstre m2 = new monstre(Zone1);
        contaner2.getChildren().add(m2.getCorps());
        monstres2.add(m2);   
        nbmonstren2++;
        monstre.setNumber(nbmonstren2);
        double x = 70*Math.random();
        double y = (150*Math.random())+30;
        
        double x1 = (700*Math.random())+150;
        double y1 = (150*Math.random())+30;
        
        Line path = new Line(x, y, x1,y1);
        // Set up a Path Transition for the Rectangle
        PathTransition trans = new PathTransition(Duration.seconds(2), path, m2.getCorps());
        trans.setOrientation(PathTransition.OrientationType.NONE);
        // Let the animation run forever
        trans.setCycleCount(FadeTransition.INDEFINITE);
        // Reverse direction on alternating cycles
        trans.setAutoReverse(true);
        // Play the Animation
        trans.play(); 
    		}
       //to kill montres
    	for(balle balle2:balles2) {
    		for(monstre monstre2:monstres2) {
    			if(balle2.touche(monstre2)) {
    				contaner2.getChildren().removeAll(balle2.getCorps(),monstre2.getCorps());
    				balle2.setAlive(false);
    				monstre2.setAlive(false);
    				nbmonstrerest2--;
    		        nbmonstre2.setText("Number of monsters: "+nbmonstrerest2);	       
    			}
    		};
    	}
		monstres2.removeIf(entite::dead);
		balles2.removeIf(entite::dead);
		if(nbmonstrerest2 <=0) {try {
			WIN(primaryStage,2);
		} catch (Exception e) {
			e.printStackTrace();
		} animation.stop(); }	
		if(p.getBlood().stillAlive()) { try {
			gameEnd(primaryStage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} animation.stop(); }
    }
    public void evenements(Stage primaryStage,Pane contaner,ArrayList<balle> balles){  
    	event = new  EventHandler<KeyEvent>() {
		    @Override
			public void handle(KeyEvent event) {
	    	switch (event.getCode()) {case UP:	p.Up(); a.Up();  break;
	    	case DOWN:	p.Down(); a.Down();  break;
	    	case LEFT:	p.Left(); a.Left();break;
	    	case RIGHT:	p.Right();  a.Right();break;
	    	case X:	    a.left();  break;
	    	case Z:	    a.right();  break;
	    	case SPACE:	 if(nbballrest<=0) {lostballs(primaryStage);}
	    	balle balle = new balle(a);
	    	contaner.getChildren().add(balle.getCorps());
	    	balle.getsound();
	   		balles.add(balle);
	   		nbballrest--;
	   		nbball.setText("Number of balls : "+nbballrest);
			   default:
			break;}
		    }
		};
    } 
    public void lostballs(Stage stage){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game");
        //if (p.getBlood().stillAlive())
        	alert.setContentText("YOU DIEA !! TRY AGAIN");
        //else
            alert.setContentText("YOU USED ALL THE BULLETS !! TRY AGAIN");
        alert.showAndWait();
        stage.close();
    }
    public void WIN(Stage stage, int level){
    	if (level==1) { nbmonstrerest=1;}
    	if (level==2) { nbmonstrerest=1;}
        stage.close();
                Stage alert = new Stage();
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.initOwner(stage);
                Pane alertVbox = new Pane();
                Text t = new Text("You win ");
                t.setLayoutX(200);
                t.setLayoutY(80);
                t.setFill(Color.WHITE);
                t.setFont(Font.font(null, FontWeight.BOLD, 26));
                
                Button b = new Button("Next level");  
                
                //b.setStyle("-fx-background-color: TRANSPARENT;");
                
                b.setLayoutX(200);
                b.setLayoutY(150);
                b.setPrefHeight(40);
        	    b.setPrefWidth(100);
        	    b.setStyle("-fx-background-color: #C684EE; ");
                
                b.setOnAction((eh)->{
                	alert.close();
                	try {
                		if (level == 1)
						stage2(stage);
                		if (level == 2)
    						stage3(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
                });
                
                Image img1 = null;
				try {
					img1 = new Image(new FileInputStream("Images/end.jpeg"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		BackgroundSize backgroundSize = new BackgroundSize(550, 350, true, true, true, true);
        		BackgroundImage backImg = new BackgroundImage(img1,
                         BackgroundRepeat.NO_REPEAT,
                         BackgroundRepeat.NO_REPEAT,
                         BackgroundPosition.CENTER,
                         backgroundSize);
        		Background backGround = new Background(backImg);
        		alertVbox.setBackground(backGround);
                
                alertVbox.getChildren().addAll(t,b);
                Scene alertScene = new Scene(alertVbox, 500, 300);
                alert.setScene(alertScene);
                alert.setTitle("Stage 2");
                alert.show(); 
                }
    public void stage3(Stage primarystage) throws Exception{
    	primarystage.setWidth(800); 
	    primarystage.setHeight(600); 
		primarystage.setTitle("Game"); 
		primarystage.show();
		primarystage.setResizable(false);
		Blood B1 =new Blood();
		p.setBlood(B1);
		p.getBlood().removeplace();
		 Bidmonster m1 = new Bidmonster(Zone1);
		 Bidmonster  m2 = new Bidmonster(Zone1);
	        
	        Line path = new Line(-10, 50, 810,50);
	        PathTransition trans = new PathTransition(Duration.seconds(2), path, m1.getCorps());
	        trans.setOrientation(PathTransition.OrientationType.NONE);
	        trans.setCycleCount(FadeTransition.INDEFINITE);
	        trans.setAutoReverse(true);
	        trans.play(); 
	        
	        Line path1 = new Line(-10, 150, 810,150);
	        PathTransition trans1 = new PathTransition(Duration.seconds(3), path1, m2.getCorps());
	        trans1.setOrientation(PathTransition.OrientationType.NONE);
	        trans1.setCycleCount(FadeTransition.INDEFINITE);
	        trans1.setAutoReverse(true);
	        trans1.play(); 
	        
	        m2.getBlood().change();
	        
	        
		this.animation = new AnimationTimer() {
			@Override
			public void handle(long now) {
		    	p.setBlood(B1);
		    	for(balle b : bilal2){
		            if(b.touche(p)){
		                p.getBlood().getdamaged(damage2);
		                damage2++;
		            }
		        } 
		    	//to move balles
				for(balle b : balles3){
					if(b.touche(m1)){
						m1.getBlood().getdamaged2();
						//damageinho++;
					}
					if(b.touche(m2)){
						m2.getBlood().getdamaged2();
						//damageinho++;
					}
				}
				for(balle balle2: balles3) {
		    		balle2.move();
		    		} 
                for(balle balle : balles3){
                    if(balle.touche(m1)){
                        contaner3.getChildren().remove(balle.getCorps());
                        balle.setAlive(false);
                        //m1.getBlood().getdamaged(damage2);
                        //damage2++;
						//if(m1.getBlood().stillAlive()) contaner3.getChildren().removeAll(m1.getBlood().getRect1(),m1.getBlood().getRect2(),m1.getCorps());
                   }
                   if(balle.touche(m2)){
                        contaner3.getChildren().remove(balle.getCorps());
                        balle.setAlive(false);
                        //m2.getBlood().getdamaged(damage);
                        //damage++;
					   //if(m2.getBlood().stillAlive()) contaner3.getChildren().removeAll(m2.getBlood().getRect1(),m2.getBlood().getRect2(),m2.getCorps());
                   }
                }
		    	if(m1.getBlood().stillAlive()) contaner3.getChildren().removeAll(m1.getBlood().getRect1(),m1.getBlood().getRect2(),m1.getCorps());
                if(m2.getBlood().stillAlive()) contaner3.getChildren().removeAll(m2.getBlood().getRect1(),m2.getBlood().getRect2(),m2.getCorps());
                if(m1.getBlood().stillAlive() && m2.getBlood().stillAlive())
					try {

						gameEnd(primarystage);
						animation.stop();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
                monstres4.removeIf(entite::dead);
		    	balles3.removeIf(entite::dead);
		    	if(p.getBlood().stillAlive()) { try {
		    		gameEnd(primarystage);
		    	} catch (FileNotFoundException e) {
		    		// TODO Auto-generated catch block
		    		e.printStackTrace();
		    	} animation.stop(); }
			}
        	    };

        animation.start();
		evenements(primarystage,contaner3,balles3);
		Scene scene = new Scene(contaner3);
		contaner3.getChildren().addAll(m1.getCorps(),m2.getCorps(),m1.getBlood().getRect1(),m2.getBlood().getRect1(),m1.getBlood().getRect2(),m2.getBlood().getRect2());
		
		/*contaner2.setStyle("-fx-background-image:url('file:///C:/Users/DELL/eclipse-workspace/jeu-kenza/Images/bg.gif');"
				+ "-fx-background-repeat: no-repeat; "
				+ "-fx-background-size: 800 600; "
				+ "-fx-background-position: center center;"); */
		
		Image image = new Image(new FileInputStream("Images/bg.gif"));
		BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, true);
		BackgroundImage bImg = new BackgroundImage(image,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundPosition.CENTER,
                 backgroundSize);
		Background bGround = new Background(bImg);
		contaner3.setBackground(bGround);
		
		
		balle b1 = new balle(a);
        bilal2.add(b1);
		
        Line path2 = new Line(0, 280, 800,280);
        // Set up a Path Transition for the Rectangle
        PathTransition trans2 = new PathTransition(Duration.seconds(5), path2, b1.getCorps());
        trans2.setOrientation(PathTransition.OrientationType.NONE);
        // Let the animation run forever
        trans2.setCycleCount(FadeTransition.INDEFINITE);
        // Reverse direction on alternating cycles
        trans2.setAutoReverse(true);
        // Play the Animation
        trans2.play();
        
        
        
        balle b3 = new balle(a);
        bilal2.add(b3);
        
        Line path3 = new Line(-100, 520, 900,520);
        // Set up a Path Transition for the Rectangle
        PathTransition trans3 = new PathTransition(Duration.seconds(6), path3, b3.getCorps());
        trans3.setOrientation(PathTransition.OrientationType.NONE);
        // Let the animation run forever
        trans3.setCycleCount(FadeTransition.INDEFINITE);
        // Reverse direction on alternating cycles
        trans3.setAutoReverse(true);
        // Play the Animation
        trans3.play(); 
		
		
		scene.setOnKeyPressed(event);
		primarystage.setScene(scene);
        b.setFill(Color.WHITE);
        b.setTranslateX(650);
        b.setTranslateY(20);
        nbball.setText("Number of balls : "+nbballrest);
        nbball.setTranslateX(651);
        nbball.setTranslateY(40);
        contaner3.getChildren().addAll(b1.getCorps() ,b3.getCorps());
        contaner3.getChildren().addAll(p.getCorps(),a.getCorps(),b,nbball);
		p.getBlood().removeplace();
        contaner3.getChildren().addAll(p.getBlood().getRect1(), p.getBlood().getRect2());
        
}
    public void stage2(Stage primarystage) throws Exception{
	    primarystage.setWidth(800); 
	    primarystage.setHeight(600); 
		primarystage.setTitle("Game"); 
		primarystage.show();
		primarystage.setResizable(false);
		this.animation = new AnimationTimer() {
			@Override
			public void handle(long now) {
		    	monstre.setNumber(nbmonstren2);
				refreche2(primarystage);}
	    };
        animation.start();
		evenements(primarystage,contaner2,balles2);
		Scene scene = new Scene(contaner2);
		
		/*contaner2.setStyle("-fx-background-image:url('file:///C:/Users/DELL/eclipse-workspace/jeu-kenza/Images/bg.gif');"
				+ "-fx-background-repeat: no-repeat; "
				+ "-fx-background-size: 800 600; "
				+ "-fx-background-position: center center;"); */
		
		Image image = new Image(new FileInputStream("Images/bg.gif"));
		BackgroundSize backgroundSize = new BackgroundSize(800, 600, true, true, true, true);
		BackgroundImage bImg = new BackgroundImage(image,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundPosition.CENTER,
                 backgroundSize);
		Background bGround = new Background(bImg);
		contaner2.setBackground(bGround);
		
		
		balle b1 = new balle(a);
        bilal.add(b1);
		
        Line path = new Line(0, 280, 800,280);
        // Set up a Path Transition for the Rectangle
        PathTransition trans = new PathTransition(Duration.seconds(5), path, b1.getCorps());
        trans.setOrientation(PathTransition.OrientationType.NONE);
        // Let the animation run forever
        trans.setCycleCount(FadeTransition.INDEFINITE);
        // Reverse direction on alternating cycles
        trans.setAutoReverse(true);
        // Play the Animation
        trans.play();
        
        
        
        balle b3 = new balle(a);
        bilal.add(b3);
        
        Line path3 = new Line(-100, 520, 900,520);
        // Set up a Path Transition for the Rectangle
        PathTransition trans3 = new PathTransition(Duration.seconds(6), path3, b3.getCorps());
        trans3.setOrientation(PathTransition.OrientationType.NONE);
        // Let the animation run forever
        trans3.setCycleCount(FadeTransition.INDEFINITE);
        // Reverse direction on alternating cycles
        trans3.setAutoReverse(true);
        // Play the Animation
        trans3.play(); 
		
		
		scene.setOnKeyPressed(event);
		primarystage.setScene(scene);
        r.setFill(Color.WHITE);
        r.setTranslateX(8);
        r.setTranslateY(20);
        nbmonstre2.setText("Number of monstres : "+nbmonstrerest2);
        nbmonstre2.setTranslateX(9);
        nbmonstre2.setTranslateY(40);
        b.setFill(Color.WHITE);
        b.setTranslateX(650);
        b.setTranslateY(20);
        nbball.setText("Number of balls : "+nbballrest);
        nbball.setTranslateX(651);
        nbball.setTranslateY(40);
        contaner2.getChildren().addAll(b1.getCorps() ,b3.getCorps());
        contaner2.getChildren().addAll(p.getCorps(),a.getCorps(),r,b,nbball,nbmonstre2);
        contaner2.getChildren().addAll(p.getBlood().getRect1(), p.getBlood().getRect2());
}
	public void start(Stage primaryStage) throws Exception{ 
		 
		    //Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
		    //Scene fxmlScene = new Scene(root);
		    
		    Pane root = new Pane();
		    Button bt1 = new Button("Start");
		    Button bt2 = new Button("Quit");
		    bt1.setPrefHeight(50);
		    bt1.setPrefWidth(120);
		    bt1.setLayoutX(350);
		    bt1.setLayoutY(410);
		    
		    bt2.setPrefHeight(50);
		    bt2.setPrefWidth(120);
		    bt2.setLayoutX(350);
		    bt2.setLayoutY(465);
		    
		    bt1.setStyle("-fx-background-color: #C684EE; ");
		    bt2.setStyle("-fx-background-color: #C684EE; ");
		    Image img = new Image(new FileInputStream("Images/logo.jpeg"));
			BackgroundSize backgroundSize = new BackgroundSize(900, 600, true, true, true, true);
			BackgroundImage backImg = new BackgroundImage(img,
	                 BackgroundRepeat.NO_REPEAT,
	                 BackgroundRepeat.NO_REPEAT,
	                 BackgroundPosition.CENTER,
	                 backgroundSize);
			Background backGround = new Background(backImg);
			root.setBackground(backGround);
		    
		    root.getChildren().addAll(bt1, bt2);
		    Scene scene2 = new Scene (root);
		    bt1.setOnAction((event)-> {
		    	//primaryStage.setScene(scene);
		    	try {
					choseGendre(primaryStage);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    });
		    bt2.setOnAction((event)-> {
		    	primaryStage.close();
		    });
		    
		    music("Images/home.mp3");
		    
		    primaryStage.setWidth(800); 
		    primaryStage.setHeight(600); 
			primaryStage.setTitle("Game"); 
			primaryStage.show();
			primaryStage.setResizable(false);
			this.animation1 = new AnimationTimer() {
				@Override
				public void handle(long now) {
					refreche(primaryStage);
				}
		    };
            //animation1.start();
			evenements(primaryStage,contaner,balles);
			this.scene = new Scene(contaner);
			
			/*contaner.setStyle("-fx-background-image:url('file:///C:/Users/DELL/eclipse-workspace/jeu-kenza/Images/bg.gif');"
					+ "-fx-background-repeat: no-repeat; "
					+ "-fx-background-size: 800 600; "
					+ "-fx-background-position: center center;");*/
			
			Image image = new Image(new FileInputStream("Images/bg.gif"));
			BackgroundImage bImg = new BackgroundImage(image,
	                 BackgroundRepeat.NO_REPEAT,
	                 BackgroundRepeat.NO_REPEAT,
	                 BackgroundPosition.CENTER,
	                 backgroundSize);
			Background bGround = new Background(bImg);
			contaner.setBackground(bGround);
			
			
			
			scene.setOnKeyPressed(event);
			
			primaryStage.setScene(scene2);
			
	        r.setFill(Color.WHITE);
	        r.setTranslateX(8);
	        r.setTranslateY(20);
	        nbmonstre.setText("Number of monstres : "+nbmonstrerest);
	        nbmonstre.setTranslateX(9);
	        nbmonstre.setTranslateY(40);
	        b.setFill(Color.WHITE);
	        b.setTranslateX(650);
	        b.setTranslateY(20);
	        nbball.setText("Number of balls : "+nbballrest);
	        nbball.setTranslateX(651);
	        nbball.setTranslateY(40);
	        // contaner.getChildren().addAll(p.getCorps(),a.getCorps(),r,b,nbball,nbmonstre);
	        //contaner.getChildren().addAll(p.getBlood().getRect1(),p.getBlood().getRect2());
	}
	private void choseGendre(Stage stage) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		Pane pane = new Pane();
		Button male = new Button("Male");
		Button fimale = new Button("Female");
		
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		
		Text t = new Text("Chose Your Gender");
		t.setEffect(ds);
		t.setCache(true);
		t.setFill(Color.WHITE);
        t.setFont(Font.font(null, FontWeight.BOLD, 45));
        t.setLayoutY(160);
        t.setLayoutX(240);
		
		male.setPrefHeight(50);
	    male.setPrefWidth(120);
	    male.setLayoutX(250);
	    male.setLayoutY(410);
	    
	    fimale.setPrefHeight(50);
	    fimale.setPrefWidth(120);
	    fimale.setLayoutX(440);
	    fimale.setLayoutY(410);
	    
		Scene scene3 = new Scene(pane);
		stage.setScene(scene3);
		
		Image fimaleimage = new Image(new FileInputStream("Images/hero.png"));
		ImageView Fi = new ImageView(fimaleimage);
		Fi.setLayoutX(560);
		Fi.setLayoutY(250);
		
		
		Image maleimage = new Image(new FileInputStream("Images/hero1.png"));
		ImageView Mi = new ImageView(maleimage);
		Mi.setLayoutX(150);
		Mi.setLayoutY(250);
		
		fimale.setStyle("-fx-background-color: #C684EE; ");
	    male.setStyle("-fx-background-color: #C684EE; ");
	    Image img = new Image(new FileInputStream("Images/end.jpeg"));
		BackgroundSize backgroundSize = new BackgroundSize(900, 600, true, true, true, true);
		BackgroundImage backImg = new BackgroundImage(img,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundPosition.CENTER,
                 backgroundSize);
		Background backGround = new Background(backImg);
		pane.setBackground(backGround);
		
		pane.getChildren().addAll(male, fimale, Fi, Mi, t);
		
		
		male.setOnAction(event -> {
			this.p = new player(Zone2,"Images/hero1.png");
			this.a = new arme(p);
			contaner.getChildren().addAll(p.getCorps(),a.getCorps(),r,b,nbball,nbmonstre);
			stage.setScene(scene);
			animation1.start();
			f=true;
		});
		
		fimale.setOnAction(event -> {
			this.p = new player(Zone2,"Images/hero.png");
			this.a = new arme(p);
			stage.setScene(scene);
			animation1.start();
			contaner.getChildren().addAll(p.getCorps(),a.getCorps(),r,b,nbball,nbmonstre);
			f=false;
		});
	}
	public void music (String url) {
		Media m = new Media(Paths.get(url).toUri().toString());
		mp = new MediaPlayer(m);
		mp.play();
		mp.setVolume(0.1);
		mp.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         mp.seek(Duration.ZERO);
		       }
		});
	}
	
	public void gameEnd (Stage stage) throws FileNotFoundException {
		//nbmonstrerest=1;
		Text t;
        stage.close();
        Stage alert = new Stage();
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);
        Pane alertVbox = new Pane();
        if (p.getBlood().stillAlive()) {
            t = new Text("OHH You lost, better Lack Next Time");
        } else {
        	t = new Text("Congratulations You Win The Game :)");
        }
        
        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);
        
        //t.setEffect(is);
        t.setFill(Color.WHITE);
        t.setFont(Font.font(null, FontWeight.BOLD, 26));
        
        t.setLayoutX(50);
        t.setLayoutY(80);
        
        Button again = new Button("Try Again");
        Button quit = new Button("Quit");
        
        again.setPrefHeight(40);
	    again.setPrefWidth(100);
	    again.setLayoutX(100);
	    again.setLayoutY(180);
	    
	    quit.setPrefHeight(40);
	    quit.setPrefWidth(100);
	    quit.setLayoutX(300);
	    quit.setLayoutY(180);
        
	    again.setStyle("-fx-background-color: #C684EE; ");
	    quit.setStyle("-fx-background-color: #C684EE; ");
	    
	    //again.setStyle("button-hover-color: #DFD2E7 ;");
	    //quit.setStyle("button-hover-color: #DFD2E7 ;");
	 
        again.setOnAction((eh)->{
        	alert.close();
        	nbmonstrerest=1;
        	//stage.setScene(scene);
        	//animation.start();
        	//stage.show();
        	try {
				new program().start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
        
        quit.setOnAction((event)->{
        	alert.close();
        });
        
        alertVbox.getChildren().addAll(t, again , quit);
        
        Image img = new Image(new FileInputStream("Images/end.jpeg"));
		BackgroundSize backgroundSize = new BackgroundSize(550, 350, true, true, true, true);
		BackgroundImage backImg = new BackgroundImage(img,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundPosition.CENTER,
                 backgroundSize);
		Background backGround = new Background(backImg);
		alertVbox.setBackground(backGround);
        
        Scene alertScene = new Scene(alertVbox, 500, 300);
        alert.setScene(alertScene);
        alert.setTitle("EndGame");
        alert.show(); 
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}            
