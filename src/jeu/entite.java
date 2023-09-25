package jeu;


import javafx.scene.Node;

public class entite {
	protected Node corps;
	protected boolean alive=true;
	public Node getCorps() {
		return corps;
	}

	public void setCorps(Node corps) {
		this.corps = corps;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public boolean dead() {
		return !alive;
	}
	public boolean touche(entite e) {
		return corps.getBoundsInParent().intersects(e.getCorps().getBoundsInParent());
	}
}
