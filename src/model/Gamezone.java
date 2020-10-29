package model;

public class Gamezone {
	private int numfilas;
	private int numcolumnas;
	private int numfasig;
	private int numcasig;
	private Cell first;
	private Cell actual;
	private Cell firstCol;
	private int nummirrors;
	private int mirrorsasig;
	
	public Gamezone(int numfilas, int numcolumnas, int nummirrors) {
		this.numfilas = numfilas;
		this.numcolumnas = numcolumnas;
		this.nummirrors = nummirrors;
		first = null;
		actual = null;
		firstCol = null;
		numfasig = 0;
		numcasig = 0;
		mirrorsasig = 0;
		
		newCell();
		newMirrow();
		
		
	}
	public void setFirst() {
		actual = first;
		firstCol = first;
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

	
	public void newCell() {
		boolean newfila;
		
		newfila = false;
		numcasig ++;
		
		if(numfasig == 0) {
			numfasig++;	

		}

		if(numcasig>numcolumnas) {
			numcasig = 1;
			numfasig++;
			newfila = true;
		}
		if(numfasig<=numfilas) {
			Cell newcell = new Cell(numfasig, numcasig);
			if(first == null) {
				first = newcell;
				actual = first;
				firstCol = first;
			}else {
				if (newfila) {					
					newcell.setUpcell(firstCol);
					firstCol.setDowncell(newcell);
					firstCol = newcell;					
				}else {
					newcell.setLefthcell(actual);
					if(actual.getUpcell() != null) {
						if(actual.getUpcell().getRigthcell() != null) {
							actual.getUpcell().getRigthcell().setDowncell(newcell);
							newcell.setUpcell(actual.getUpcell().getRigthcell());
						}
					}				
					actual.setRigthcell(newcell);
				}
				actual = newcell;				
			}
			newCell();
		}
		
	}

	public void newMirrow() {
		int rowmirrow = 0;
		int colmirrow = 0;
		int direction = 0;
		
		rowmirrow = (int) (Math.random()*numfilas + 1);
		colmirrow = (int) (Math.random()*numcolumnas + 1);
		direction = (int) (Math.random()*2 + 1);
		mirrorsasig ++;
		AsigMirrow(rowmirrow, colmirrow, direction, first);
		if(mirrorsasig < nummirrors) {
			newMirrow();
		}
	}

	public void AsigMirrow(int rowmirrow, int colmirrow, int direction, Cell nodo){
		String mirr;
		if(nodo.getColumna() != colmirrow)
			AsigMirrow(rowmirrow, colmirrow, direction, nodo.getRigthcell());
		else {
			if(nodo.getFila() != rowmirrow)
				AsigMirrow(rowmirrow, colmirrow, direction, nodo.getDowncell());
			else
				{
					if (direction == 1) mirr = "/"; else mirr = "\\";
					
					if (nodo.getMirror().equalsIgnoreCase(" "))
						nodo.setMirror(mirr);
					else
						mirrorsasig --;
				}
			
			}
	}

	public int getNummirrors() {
		return nummirrors;
	}
	public void setNummirrors(int nummirrors) {
		this.nummirrors = nummirrors;
	}

	
	public void ShowCell() {
		if(actual != null) {
			System.out.print("["+actual.getMirror()+"]");
			if (actual.getRigthcell() != null) 
				actual = actual.getRigthcell();
			 else {
				 System.out.println("");
				actual = firstCol.getDowncell();
				firstCol = firstCol.getDowncell();
			 }
			ShowCell();

		}
		
	}	
	/*
	public void addRight(long n) {
		Number newnumber = new Number(n);
		cant++;
		if(firstnumber==null) {
			firstnumber = newnumber;
			lastnumber = firstnumber;
			mediananumber = firstnumber;
			pos =1;
		}else {
			Number current = lastnumber;
			newnumber.setPrevnum(lastnumber);
			current.setNextnum(newnumber);
			lastnumber = newnumber;
			setMediana();			
		}
	}
	*/
	
}

