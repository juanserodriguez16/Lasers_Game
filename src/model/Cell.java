package model;

public class Cell {
	private String Mirror;
	private int fila;
	private char columna;
	public Cell (String Mirror, int fila, char columna) {
		this.Mirror = Mirror;
		this.fila = fila;
		this.columna = columna;
	}
	public String getMirror() {
		return Mirror;
	}
	public void setMirror(String mirror) {
		Mirror = mirror;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
	public char getColumna() {
		return columna;
	}
	public void setColumna(char columna) {
		this.columna = columna;
	}
}
