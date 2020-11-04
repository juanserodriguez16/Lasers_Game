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
	public void readcoordenadas( ) {
		int n1, n2, fila = 0, columna = 0;
		boolean esquina = false;
		String fil, col;
		gz.setFirst();
		String opt = sc.nextLine();

		opt = opt.toUpperCase();
		char l = opt.charAt(0);
		String locate = l + "";
		char d = opt.charAt(opt.length()-1);
		String direc = d + "";
		char z = opt.charAt(opt.length()-2);
		n1 = d;
		n2 = z;
		if (locate.equalsIgnoreCase("L")) {
			gz.setCellIn(null);
			gz.setCellOut(null);
			gz.setCellx(null);
			fil = opt.substring(1, opt.length()-2);
			col = (opt.charAt(opt.length()-2) - 64) +"";	
			fila= Integer.parseInt(fil);
			columna = Integer.parseInt(col)  ;
			gz.getStartray(fila, columna);
			String mi = gz.getActual().getMirror();
			if(mi.equalsIgnoreCase("/"))
				mi = "R";
			else if(mi.equalsIgnoreCase("\\"))
				mi = "L";
			if(mi.equalsIgnoreCase(direc)) {
				gz.getActual().setFindMirrow(true);
				gz.setContmirrors(gz.getContmirrors()-1);
			}else 
				gz.setCellx(gz.getActual());

		}else {
			if(n1>=65 && n1 <= 90 && n2>=65 && n2 <= 90) {
				esquina = true;
				fil = opt.substring(0, opt.length()-2);
				col = (opt.charAt(opt.length()-2) - 64) +"";	

			}
			else
			{
				fil = opt.substring(0, opt.length()-1);
				col = (opt.charAt(opt.length()-1) -64 )+"";					

			}
			fila= Integer.parseInt(fil);
			columna = Integer.parseInt(col)  ;
			gz.setCellIn(null);
			gz.setCellOut(null);
			gz.setCellx(null);
			gz.getStartray(fila, columna);
			gz.setCellIn(gz.getActual());

			if (esquina && gz.getActual().getFila() == 1 && gz.getActual().getColumna() == 1 && direc.equalsIgnoreCase("H")) 
				gz.setDirection(3);
			else if (esquina && gz.getActual().getFila() == 1 && gz.getActual().getColumna() == 1 && direc.equalsIgnoreCase("V"))
				gz.setDirection(1);
			else if (esquina && gz.getActual().getFila() == 1 && gz.getActual().getColumna() == gz.getNumcolumnas() && direc.equalsIgnoreCase("H"))
				gz.setDirection(4);
			else if (esquina && gz.getActual().getFila() == 1 && gz.getActual().getColumna() == gz.getNumcolumnas() && direc.equalsIgnoreCase("V"))
				gz.setDirection(1);
			else if (esquina && gz.getActual().getFila() == gz.getNumfilas() && gz.getActual().getColumna() == 1 && direc.equalsIgnoreCase("H"))
				gz.setDirection(3);
			else if (esquina && gz.getActual().getFila() == gz.getNumfilas() && gz.getActual().getColumna() == 1 && direc.equalsIgnoreCase("V"))
				gz.setDirection(2);
			else if (esquina && gz.getActual().getFila() == gz.getNumfilas() && gz.getActual().getColumna() == gz.getNumcolumnas() && direc.equalsIgnoreCase("H"))
				gz.setDirection(4);
			else if (esquina && gz.getActual().getFila() == gz.getNumfilas() && gz.getActual().getColumna() == gz.getNumcolumnas() && direc.equalsIgnoreCase("V"))
				gz.setDirection(2);
			else if(gz.getActual().getFila() == 1)
				gz.setDirection(1);
			else if(gz.getActual().getFila() == gz.getNumfilas())
				gz.setDirection(2);
			else if(gz.getActual().getColumna() == 1)
				gz.setDirection(3);
			else if(gz.getActual().getColumna() == gz.getNumcolumnas())
				gz.setDirection(4);
			gz.move();
			gz.setCellOut(gz.getCurrent());



		}
	}

	public void play() {
		if (gz.getContmirrors() != 0) {
			showMatrix();
			readcoordenadas( );
			System.out.println(gz.getGamer() +" te faltan " + gz.getContmirrors() + " espejos por encontrar");
			play();
		}
		System.out.println("Felicidades ganaste");
	}
	public void calculatepoints() {
		
	}
	public void startprogram() {

		showmenu();
		int x = Integer.parseInt(sc.nextLine());
		if (x != 3) {


			switch(x) {
			case 1:
				creategamezone();
				play();

				break;
			case 2: 
			}
			startprogram();	
		}

	}

}
