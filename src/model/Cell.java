package model;

public class Cell {
	private String Mirror;
	private int fila;
	private int columna;
	private Cell rigthcell;
	private Cell lefthcell;
	private Cell upcell;
	private Cell downcell;
	private boolean findMirrow;
	
	public Cell ( int fila, int  columna) {
		Mirror = " ";
		this.fila = fila;
		this.columna = columna;
		rigthcell = null;
		lefthcell = null;
		upcell = null;
		downcell = null;
		
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
	public int  getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public Cell getRigthcell() {
		return rigthcell;
	}
	public void setRigthcell(Cell rigthcell) {
		this.rigthcell = rigthcell;
	}
	public Cell getLefthcell() {
		return lefthcell;
	}
	public void setLefthcell(Cell lefthcell) {
		this.lefthcell = lefthcell;
	}
	public Cell getUpcell() {
		return upcell;
	}
	public void setUpcell(Cell upcell) {
		this.upcell = upcell;
	}
	public Cell getDowncell() {
		return downcell;
	}
	public void setDowncell(Cell downcell) {
		this.downcell = downcell;
	}
}
