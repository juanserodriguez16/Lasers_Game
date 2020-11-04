package model;

public class Gamezone {
	private int numfilas;
	private int numcolumnas;
	private int numfasig;
	private int numcasig;
	private Cell first;
	private Cell actual;
	private Cell firstCol;
	private Cell current;
	private int nummirrors;
	private int mirrorsasig;
	private String gamer;
	private String matrix;
	private int contmirrors;
	private int direction;
	private Cell cellIn;
	private Cell cellOut;
	private Cell cellx;
	private Score root;

	public Gamezone(String gamer, int numfilas, int numcolumnas, int nummirrors) {
		this.gamer = gamer;
		this.numfilas = numfilas;
		this.numcolumnas = numcolumnas;
		this.nummirrors = nummirrors;
		first = null;
		actual = null;
		firstCol = null;
		setCurrent(null);
		setCellIn(null);
		setCellOut(null);
		setCellx(null);
		numfasig = 0;
		numcasig = 0;
		mirrorsasig = 0;
		matrix = "";
		contmirrors = nummirrors;
		root = null;
		



	}
	public Cell getActual() {
		return actual;

	}
	public void setFirst() {
		actual = first;
		firstCol = first;
		setCurrent(first);
		matrix = "";

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
		String mat;
		if(actual != null) {
			if (cellx == actual)
				mat = "[X]";
			else if(cellIn == actual)
				mat = "[S]";
			else if (cellOut == actual)
				mat = "[E]";
			else if(actual.isFindMirrow())
				mat = "["+actual.getMirror()+"]";
			else
				mat = "[ ]";
			setMatrix(getMatrix() + mat );


			if (actual.getRigthcell() != null) 
				actual = actual.getRigthcell();
			else {

				setMatrix(getMatrix() + "\n");

				actual = firstCol.getDowncell();
				firstCol = firstCol.getDowncell();

			}
			ShowCell();

		}

	}	
	/*identifico donde va a empezar el rayo y lo pongo en la variable actual*/
	public void getStartray(int f , int c) {
		if (actual.getFila() != f || actual.getColumna() != c){
			if (actual.getRigthcell() != null && actual.getColumna() != c) 
				actual = actual.getRigthcell();
			else {
				if (actual.getFila() != f && actual.getDowncell() != null)
				{
					actual = firstCol.getDowncell();
					firstCol = firstCol.getDowncell();
				}

			}
			getStartray(f,c);
		}

		setCurrent(actual);
	}

	public void moverigth() {
		setCurrent(actual);
		actual = actual.getRigthcell();
	}
	public void moveleft() {
		setCurrent(actual);
		actual = actual.getLefthcell();
	}
	public void moveup() {
		setCurrent(actual);
		actual = actual.getUpcell();
	}
	public void movedown() {
		setCurrent(actual);
		actual = actual.getDowncell();
	}

	public void move() {
		int vCelda = 0;
		if (actual != null) {
			vCelda = mpos();
			if (vCelda != 0) {
				if(vCelda == 1 && getDirection() == 1) // DE arriba hacia abajo
					setDirection (4); //salir a la izquierda
				else if(vCelda == 1 && getDirection() == 2) // DE abajo hacia arriba
					setDirection (3); //salir a derecha
				else if(vCelda == 1 && getDirection() == 3) // DE derecha a Izquierda
					setDirection (2); //salir a la arriba
				else if(vCelda == 1 && getDirection() == 4) // DE iquierda a derecha
					setDirection (1); //salir a abajo
				else if(vCelda == 2 && getDirection() == 1) // DE arriba hacia abajo
					setDirection (3); //salir a la derecha
				else if(vCelda == 2 && getDirection() == 2) // DE abajo hacia arriba
					setDirection (4); //salir a izquierda
				else if(vCelda == 2 && getDirection() == 3) // DE derecha a Izquierda
					setDirection (1); //salir a abajo
				else if(vCelda == 2 && getDirection() == 4) // DE iquierda a derecha
					setDirection (2); //salir a arriba
			}
			switch (getDirection()) {
			case 1:
				movedown();
				break;
			case 2:
				moveup();
				break;
			case 3:
				moverigth();
				break;
			case 4:
				moveleft();
				break;
			}
			move();
		}


	}

	public int mpos() {
		int mp = 0;
		if(actual.getMirror().equalsIgnoreCase("\\")) {
			mp = 2;
		}else if(actual.getMirror().equalsIgnoreCase("/")) {
			mp = 1;
		}else if(actual.getMirror().equalsIgnoreCase(" ")) {
			mp = 0;
		}
		return mp;
	}

	public String getGamer() {
		return gamer;
	}
	public void setGamer(String gamer) {
		this.gamer = gamer;
	}
	public String getMatrix() {
		return matrix;
	}
	public void setMatrix(String matrix) {
		this.matrix = matrix;
	}
	public int getContmirrors() {
		return contmirrors;
	}
	public void setContmirrors(int contmirrors) {
		this.contmirrors = contmirrors;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public Cell getCurrent() {
		return current;
	}
	public void setCurrent(Cell current) {
		this.current = current;
	}
	public Cell getCellIn() {
		return cellIn;
	}
	public void setCellIn(Cell cellIn) {
		this.cellIn = cellIn;
	}
	public Cell getCellOut() {
		return cellOut;
	}
	public void setCellOut(Cell cellOut) {
		this.cellOut = cellOut;
	}
	public Cell getCellx() {
		return cellx;
	}
	public void setCellx(Cell cellx) {
		this.cellx = cellx;
	}
	public Score getRoot() {
		return root;
	}
	public void setRoot(Score root) {
		this.root = root;
	}
	public void addScore(Score newScore) {
		if (root == null)
			root = newScore;
		else 
			addScore(root,newScore); 
	}
	public void addScore(Score recur, Score newScore ) {
		if(newScore.getPoints()<= recur.getPoints() && recur.getLeft() ==null) {
			recur.setLeft(newScore);
			newScore.setParent(recur);
		}else if (newScore.getPoints()>recur.getPoints() && recur.getRight()==null) {
			recur.setRight(newScore);
			newScore.setParent(recur);	
		}
	}
}

