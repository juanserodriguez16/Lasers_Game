package ui;

import model.Gamezone;

public class Menu {
	private Gamezone gz;
	public void menu() {
	Gamezone gz = new Gamezone(5, 4, 6);
	gz.setFirst();
	gz.ShowCell();
	}
}
