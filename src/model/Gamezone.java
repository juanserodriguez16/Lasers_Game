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
	/**
	* Constructor of the class  <br>
	* <b> pre: </b>  
	* <b> post: </b> the variables are initialized, the numbers are 0 and the strings are empty.
	*
	*/
	public Gamezone() {
		root = null;
	}
	/**
	 * creation of the matrix and initialization of the attributes of the game zone class  <br>
	 * <b> pre: </b>
	 * <b> post: </b> a game zone type object in addition to the initialization of the variables.
	 * @param gamer the variable that stores the player's username. gamer != null.
	 * @param numfilas the number of rows in the matrix. numfilas != null.
	 * @param numcolumnas the number of columns in the matrix. numcolumnas != null.
	 * @param nummirrors the number of mirrors in the matrix. nummirrors != null.
	 */
	public void createGame(String gamer, int numfilas, int numcolumnas, int nummirrors) {
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

	}
	public Cell getActual() {
		return actual;

	}
	/**
	 * initialize variables <br>
	 * <b> pre: </b>
	 * <b> post: </b> initialize all variables that influence matrix traversal.
	 */
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

	/**
	 * create of matrix <br>
	 * <b> pre: </b>
	 * <b> post: </b> create the relationships between the nodes to create the matrix.
	 */
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
	/**
	 * randomly assigns the coordinate of a mirror, including the direction<br>
	 * <b> pre: </b>
	 * <b> post: </b> new mirror in the cell.
	 */
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
	/**
	 * Go through the matrix and locate the mirrors generating their address<br>
	 * <b> pre: </b> Relationships in the matrix must be created
	 * <b> post: </b> a cell with an assigned mirror
	 * @param rowmirrow the coordinate in rows. rowmirrow != null.
	 * @param colmirrow the coordinate in columns. rowmirrow != null.
	 * @param direction direction of the mirror. direction != null.
	 * @param nodo element to traverse the matrix. 
	 */
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

	/**
	 * stores each cell to be displayed by console.<br>
	 */
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
	/**
	 * returns the cell where the beam is going to be fired and stores it in the current variable <br>
	 * <b> pre: </b> Relationships in the matrix must be created and cowls and rows
	 * <b> post: </b> the cell where the beam begins to shoot
	 * @param f rows. f != null && f <= numfilas.
	 * @param c columns. c != null && c <= numcolumnas.
	 */
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
	/**
	 * Modify the direction of the beam's travel
	 */
	public void moverigth() {
		setCurrent(actual);
		actual = actual.getRigthcell();
	}
	/**
	 * Modify the direction of the beam's travel
	 */
	public void moveleft() {
		setCurrent(actual);
		actual = actual.getLefthcell();
	}
	/**
	 * Modify the direction of the beam's travel
	 */
	public void moveup() {
		setCurrent(actual);
		actual = actual.getUpcell();
	}
	/**
	 * Modify the direction of the beam's travel
	 */
	public void movedown() {
		setCurrent(actual);
		actual = actual.getDowncell();
	}
	/**
	 * Movement controller method <br>
	 * <b> pre: </b>  the direction of the mirror must be defined in addition to the current position
	 * <b> post: </b> The direction in which each cell should move
	 */
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
	/**
	 * determine a direction of the mirror. <br>
	 * <b> pre: </b> current cell cannot be null.
	 * <b> post: </b> the current mirror direction.
	 * @return direction. 
	 */
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
	/**
	 * add a score to the binary search tree. <br>
	 * <b> pre: </b> 
	 * <b> post: </b> the new score becomes the root
	 * @param newScore
	 */
	public void addScore(Score newScore) {
		if (root == null) {
			root = newScore;
		}else {
			addScore(root,newScore); 
		}
	}
	/**
	 * add a score to the binary search tree. <br>
	 * <b> pre: </b> the score cannot be null and there must be a root
	 * <b> post: </b> an added score.
	 * @param recur current score. recur != null.
	 * @param newScore the new score to add. newScore != null.
	 */
	public void addScore(Score recur, Score newScore ) {
		if(newScore.getPoints()<= recur.getPoints() && recur.getLeft() ==null) {
			recur.setLeft(newScore);
			newScore.setParent(recur);
		}else if (newScore.getPoints()>recur.getPoints() && recur.getRight()==null) {
			recur.setRight(newScore);
			newScore.setParent(recur);	
		}else {
			if(newScore.getPoints() <= recur.getPoints() && recur.getLeft() != null) {
				addScore(recur.getLeft(),newScore);
			}else {
				addScore(recur.getRight(),newScore);
			}
		}
	}
	/**
	 * returns the string generated by the in-order method led by the root <br>
	 * <b> pre: </b> root created and filled
	 * <b> post: </b> string with the information of the binary search tree
	 * @return String score.
	 */
	public String inOrden() {
		return inOrden(root);
	}
	/**
	 * generates a string with the data name and score of the players<br>
	 * <b> pre: </b> The scores must exist and the binary search tree cannot be empty.
	 * <b> post: </b> string with sorted data from binary search tree
	 * @param recur current score. recur != null.
	 * @return String score
	 */
	public String inOrden(Score recur) {
		String score= "";
		if(recur != null) {
			score += inOrden(recur.getLeft());
			score += recur.getPlayer() + " : " + recur.getPoints() + "\n";
			score += inOrden(recur.getRight());
		}
		return score;
	}

}

