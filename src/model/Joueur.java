package model;

public class Joueur {
	String name;
	char couleur;
	boolean tour, isControlled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getCouleur() {
		return couleur;
	}

	public void setCouleur(char couleur) {
		this.couleur = couleur;
	}

	public boolean isTour() {
		return tour;
	}

	public Joueur(String name, char couleur, boolean tour, boolean isControlled) {
		super();
		this.name = name;
		this.couleur = couleur;
		this.tour = tour;
		this.isControlled = isControlled;
	}

	public void setTour(boolean tour) {
		this.tour = tour;
	}

	public boolean isControlled() {
		return isControlled;
	}

	public void setControlled(boolean isControlled) {
		this.isControlled = isControlled;
	}

}
