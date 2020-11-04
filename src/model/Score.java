package model;

public class Score {
	private String player;
	private int points;
	private Score left;
	private Score right;
	private Score parent;
	private int numfilas;
	private int numcolumnas;
	private int nummirrors;
	
	public Score (String player, int points, int numfilas, int numcolumnas, int nummirrors) {
		this.player = player;
		this.points = points;
		this.numfilas = numfilas;
		this.numcolumnas = numcolumnas;
		this.nummirrors = nummirrors;
		parent = null;
		left = null;
		right = null;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public Score getRight() {
		return right;
	}
	public void setRight(Score right) {
		this.right = right;
	}
	public Score getLeft() {
		return left;
	}
	public void setLeft(Score left) {
		this.left = left;
	}
	public int getNumfilas() {
		return numfilas;
	}
	public void setNumfilas(int numfilas) {
		this.numfilas = numfilas;
	}
	public int getNumcolumnas() {
		return numcolumnas;
	}
	public void setNumcolumnas(int numcolumnas) {
		this.numcolumnas = numcolumnas;
	}
	public int getNummirrors() {
		return nummirrors;	}
	public void setNummirrors(int nummirrors) {
		this.nummirrors = nummirrors;
	}
	
	public Score getParent() {
		return parent;
	}
	public void setParent(Score parent) {
		this.parent = parent;
	}

}
