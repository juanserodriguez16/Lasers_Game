package model;

public class Score {
	private String player;
	private int points;
	private Score left;
	private Score right;
	
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
	public void add(String player, Integer points) {
	    if (points < this.points) {
	        if (left != null) {
	            left.add( player ,points);
	        } else {
	            left = new Score(player, points);
	        }
	    } else {
	        if (right != null) {
	            right.add(player, points);
	        } else {
	            right = new Score(player, points);
	        }
	    }
	}
	

}
