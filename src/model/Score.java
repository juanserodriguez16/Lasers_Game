package model;

public class Score {
	private String player;
	private int points;
	
	public Score (String player, int points) {
		this.player = player;
		this.points = points;
		
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
	

}
