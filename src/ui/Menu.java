package ui;

import java.util.Scanner;

import model.Gamezone;

public class Menu {
	private static Gamezone gz;
	private static Scanner sc;
	
	public void initialize() {
	 sc = new Scanner(System.in);
	}
	public void showmenu() {
		System.out.println("1) Jugar");
		System.out.println("2) Mostrar tabla de posiciones");
		System.out.println("3) Salir del juego");
	}
	public void creategamezone() {
		System.out.println("Digita los siguientes datos separados por espacios \n(nombre de usuario, filas de la cuadricula,"
				+ " columnas de la cuadricula , cantidad de espejos)");
		String d = sc.nextLine();
		String[] data = d.split(" "); 
		gz = new Gamezone(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
		gz.	newCell();
		gz.newMirrow();
		
	}
	public void showMatrix() {
		gz.setFirst();
		gz.ShowCell();
		System.out.println(gz.getMatrix());
	}
	public void nextoption() {
		String opt = sc.nextLine();
		char a = opt.charAt(0);
		String b = "" + a;
		if (b.equalsIgnoreCase("L")) {
			
		}else {
			readcoordenadas(opt);
		}
		
		}
	public void readcoordenadas(String opt) {
		
		int fila = opt.charAt(0);
		int columna = (int)opt.charAt(1) - 64;
		
	}
	
	
	public void menu() {
		
	initialize();
	creategamezone();
	
	gz.ShowCell();
	
	showMatrix();
	
	
	}
	public void startprogram() {

		showmenu();
		int x = Integer.parseInt(sc.nextLine());
		if (x != 3) {


			switch(x) {
			case 1: creategamezone();

			showMatrix();

			break;
			case 2: 
			}
			startprogram();	
		}
		
	}
}
