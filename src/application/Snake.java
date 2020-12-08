package application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import application.Field.FieldState;



/**
 * A j�t�kban szerepl� kjgy�k oszt�lya. A felel�ss�g�k reprezent�lni a kigy�t, mozogni a p�ly�n,
 * enni az alm�kat , n�ni �s meghalni. A robot kigy� k�pes mag�t ir�nyitani. A robotkigy�nak 3-as
 * a snakeID-ja.
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Snake extends Thread {
	//Enum
	/**
	 * A mozg�s ir�ny�t reprezent�l� tipus 
	 */
	public enum Type_Direction {up, down, right, left};
	
	//Adattagok
	/**
	 * A kigy� azonosit�ja
	 */
	private int snakeID;
	/**
	 * A jelenlegi mozg�si ir�ny
	 */
	private Type_Direction move_dir;
	/**
	 * Az el�z� l�p�s ir�nya
	 */
	private Type_Direction last_dir;
	/**
	 * A kigy� teste, Point-ok list�ja
	 */
	private List<Point> body;
	/**
	 * Az aktu�lis p�lya
	 */
	private Map map;
	/**
	 * A j�t�kos pontjai
	 */
	private int points = 0;
	/**
	 * A j�t�kos pontjait megjelenit� MyText objektum
	 */
	private MyText point_text;
	/**
	 * A j�t�kos nev�t megjelenit� MyText objektum
	 */
	private MyText name;
	/**
	 * A gy�zelem t�ny�t t�rol� adattag
	 */
	private boolean winner = true;
	/**
	 * A robotkigy� mozg�s�hoz sz�ks�ges lista, ami t�rolja a mozg�si ir�nyokat
	 */
	private ArrayList<Type_Direction> erremenj = new ArrayList<>();
	
	
	//Konstruktor
	/**
	 * L�trehozza a sz�ks�ges objektumokat, be�llitja az alap�rrtelmezett �rt�keket
	 * @param i - A kigy� ID-je
	 * 				�rt�ke lehet:	1:	1es j�tkos
	 * 								2:	2es j�t�kos
	 * 								3:	Robot
	 * @param m - Az adott p�lya
	 */
	public Snake(int i, Map m) {
		snakeID = i;
		body = new ArrayList<>();
		move_dir = Type_Direction.right;
		last_dir = Type_Direction.right;
		map = m;
		name = new MyText("", "medium");
		point_text = new MyText(String.valueOf(points), "medium");
	}
	
	
	
	//Getterek
	/**
	 * Visszaadja a kigy� ID-j�t
	 * @return - SnakeID
	 */
	public int getSnakeID() {
		return snakeID;
	}
	/**
	 * Visszaadja a jelenlegi mozg�si ir�nyt
	 * @return - Aktu�lis ir�ny
	 */
	public Type_Direction getMoveDir() {
		return move_dir;
	}
	/**
	 * Visszaadja az el�z� l�p�s ir�ny�t
	 * @return - Az el�z� l�p�s ir�nya
	 */
	public Type_Direction getLastDir() {
		return last_dir;
	}
	/**
	 * Visszaadja a kigy� test�nek pointjait t�rol� list�t
	 * @return - A test list�ja
	 */
	public List<Point> getBody(){
		return body;
	}
	/**
	 * Visszaadja az aktu�lis p�ly�t
	 * @return - Az aktu�lis map
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * Visszaadja a pontok sz�m�t
	 * @return - A pontok sz�ma
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Visszaadja a pontot megjelenit� MyText objektumot
	 * @return - A pontot megjelenit� MyText objektum
	 */
	public MyText getPointMyText() {
		return point_text;
	}
	/**
	 * Visszaadja a j�t�kos nev�t
	 * @return - A j�t�kos neve
	 */
	public String getSnakeName() {
		return name.getText();
	}
	/**
	 * Visszaadja a j�t�kos nev�t megjelenit� MyText objektumot
	 * @return - A nevet megjelenit� MyText objektum
	 */
	public MyText getSnakeNameMyText() {
		return name;
	}
	/**
	 * Visszaadja, hogy nyert-e a j�t�kos
	 * @return - Az eredm�ny
	 */
	public  boolean getWinner() {
		return winner;
	}
	/**
	 * A robotkigy� �tj�nka list�ja
	 * @return - Az it�nyokat tartalmaz� lista
	 */
	public List<Type_Direction> getErremenj(){
		return erremenj;
	}
	
	
	
	//Setterek
	/**
	 * Be�llitja a kigy� ID-j�t
	 * @param i - A be�llitand� �rt�k
	 */
	public void setSnakeID(int i) {
		snakeID = i;
	}
	/**
	 * Be�llitja a jelenlegi ir�nyt �s m�dositja az utols� l�p�s ir�ny�nak �rt�k�t is
	 * @param td - A be�llitand� �rt�k
	 */
	public void setMoveDir(Type_Direction td) {
		if(checkDirection(td)) {
			setLastDir(move_dir);
			move_dir = td;
		}
	}
	/**
	 * Be�llitja az udols� l�p�s ir�ny�t
	 * @param td - A be�llitand� �rt�k
	 */
	public void setLastDir(Type_Direction td) {
		last_dir = td;
	}
	/**
	 * Be�llitja a kigy� test�t t�rol� list�t
	 * @param l - A be�llitand� objektum
	 */
	public void setBody(List<Point> l){
		body = l;
	}
	/**
	 * Be�llitja a p�ly�t
	 * @param m - A be�llitand� objektum
	 */
	public void setMap(Map m) {
		map = m;
	}
	/**
	 * Be�llitja a pontokat
	 * @param i - A be�llitand� �rt�k
	 */
	public void setPoints(int i) {
		points = i;
		point_text.setText(String.valueOf(points));
	}
	/**
	 * Be��llitja a pontokat megjelenit� MyTextet
	 * @param mt - A be�llitand� objektum
	 */
	public void setPointMyText(MyText mt) {
		point_text = mt;
	}
	/**
	 * Be�llitja a kigy� nev�t
	 * @param s - A be�llitand� �rt�k
	 */
	public void setSnakeName(String s) {
		name.setText(s);
	}
	/**
	 * Be�llitja a nevet megjelenit� MyText-et
	 * @param mt - A be�llitand� objektum
	 */
	public void setSnakeNameMyText(MyText mt) {
		name = mt;
	}
	/**
	 * Be�llitja a nyer�s �rt�k�t
	 * @param b - A be�llitand� �rt�k
	 */
	public void setWinner(boolean b) {
		winner = b;
	}
	
	
	
	
	//Algoritmusok
	/**
	 * Felhelyezi a kigy�t a p�ly�ra
	 */
	public void placeSnake() {
		int n = 0;
		if(snakeID == 1)
			n = 1;
		else
			n = 2;
		body.add(new Point(10, (n *map.getMapSize()/3)));
		if(n == 1) map.getMapElement(pointToInt(getHead())).setFieldState(FieldState.snake1_body);
		else if(n == 2) map.getMapElement(pointToInt(getHead())).setFieldState(FieldState.snake2_body);
		body.add(new Point(11, (n *map.getMapSize()/3)));
		if(n == 1) map.getMapElement(pointToInt(getHead())).setFieldState(FieldState.snake1_head);
		else if(n == 2) map.getMapElement(pointToInt(getHead())).setFieldState(FieldState.snake2_head);
	}
	
	/**
	 * Leveszi a kigy�t a p�ly�r�l
	 */
	public void removeSnake() {
		for(int i = 0; i < body.size(); i++) {
			map.getMapElement(pointToInt(body.get(i))).setFieldState(FieldState.empty);
		}
		//map.getMap().forEach((n) -> n.setFieldState(FieldState.empty));
	}
	/**
	 * VIssza�llitja a kigy�t eredeti helyzetbe
	 */
	public void resetSnake() {
		removeSnake();
		body.clear();
		setPoints(0);
		placeSnake();
		setLastDir(Type_Direction.right);
		setMoveDir(Type_Direction.right);
	}
	/**
	 * A l�p�st int�z� f�ggv�ny (v�r, majd l�p)
	 */
	public void run() {
		while(map.getGame().getSettings().getGameRuns()) {
			try {
				Thread.sleep(map.getGame().getSettings().getSpeed());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			move();
			if(snakeID == 3) 
				robotControl();
		}
	}
	/**
	 * A kigy� mozg�s��rt felel�s met�dus
	 */
	public synchronized void move() {
		int n = canMove();
		
		if(n == 1 || n == 0) {
			
			oneStep();
			if(n == 0) {
		        Music m = new Music("point", map.getGame().getSettings());
		        Thread t1= new Thread(m);
		        t1.run(); 
				increasePoints();
				map.addFood();
			}
			else
				remove(getTail());
		} else if(n == -1) {
			
			winner = false;
			if(snakeID == 1) {
				map.getGame().snake1Loses();
			} else {
				map.getGame().snake2Loses();
			}
			map.getGame().stopGame();
		} else if(n == 2) {
			oneStep();
			remove(getTail());
		}
		setLastDir(getMoveDir());
	}
	/**
	 * Egy l�p�s megt�tel��rt felel�s met�dus.
	 */
	public synchronized void oneStep() {
		int x = (int)getHead().getX();
		int y = (int)getHead().getY();

		if(getMoveDir() == Type_Direction.right) {
			body.add(new Point(x + 1, y));
		} else if(getMoveDir() == Type_Direction.left) {
			body.add(new Point(x - 1, y));
		} else if(getMoveDir() == Type_Direction.up) {
			body.add(new Point(x, y - 1));
		} else if(getMoveDir() == Type_Direction.down) {
			body.add(new Point(x, y + 1));
		}
		
		FieldState fs;
		if(snakeID == 1) {
			fs = FieldState.snake1_head;
		} else {
			fs = FieldState.snake2_head;
		}
		map.getMapElement(pointToInt(getHead())).setFieldState(fs);
	}
	/**
	 * Kivesz egy pontot a testb�l
	 * @param p - A kivevend� pont
	 */
	public void remove(Point p) {
		map.getMap().get(pointToInt(p)).setFieldState(FieldState.empty);
		body.remove(p);
	}
	/**
	 * Egy adott pont (x,y) hely�t (index�t) sz�molja ki az adot p�lya mez� list�j�ban
	 * @param p - A keresett pont
	 * @return - A pont indexe
	 */
	public int pointToInt(Point p) {
		int size = map.getMapSize();
		return (int)p.getY() * size + (int)p.getX();
	}
	/**
	 * Visszaadja a kigy� fej�t
	 * @return - A kigy� feje
	 */
	public Point getHead() {
		return body.get(body.size()-1);
	}
	/**
	 * VIsszaadja a kigy� fark�t
	 * @return - A kigy� test�nek utols� eleme
	 */
	public Point getTail() {
		return body.get(0);
	}
	
	///1 ... can move
	///0 ... food
	///-1 ... lose
	/**
	 * A l�p�s lehet�s�g�t eld�nt� met�dus
	 * @return - A l�p�s lehet�s�g�nek eredm�nye.
	 * 				�rt�ke lehet:	-1:	A kigy� veszit
	 * 								0:	�telre l�pett
	 * 								1:	Szabad l�pni az adott mez�re 
	 */
	public int canMove() {
		int x = 0, y = 0;
		if(getMoveDir() == Type_Direction.right) {
			x = (int)getHead().getX() + 1;
			y = (int)getHead().getY();
		} else if(getMoveDir() == Type_Direction.left) {
			x = (int)getHead().getX() - 1;
			y = (int)getHead().getY();
		} else if(getMoveDir() == Type_Direction.up) {
			x = (int)getHead().getX();
			y = (int)getHead().getY() - 1;
		} else if(getMoveDir() == Type_Direction.down) {
			x = (int)getHead().getX();
			y = (int)getHead().getY() + 1;
		} 
		
		
		if(x >= 0 && x < map.getMapSize()) {
			if(y >= 0 && y < map.getMapSize()) {
				FieldState state = map.getMap().get(x + y*map.getMapSize()).getFieldState();
				if(state == FieldState.empty){
				}	else if(state == FieldState.food) { return 0;}
				else if(state == FieldState.snake1_body || state == FieldState.snake2_body) { return -1;}
				else if(state == FieldState.snake1_head || state == FieldState.snake2_head) { return -1;}
			} else return -1; 	//y out
		} else return -1; 	//x out
		
		return 1 ;
	}
	/**
	 * Az ir�nyv�lt�st ellen�rzi (az�rt, hogy ellenkez� ir�nyba ne tudjon l�pni)
	 * @param td - Az ellen�rizend� ir�ny
	 * @return - A ki�rt�kel�s eredm�nye
	 */
	public boolean checkDirection(Type_Direction td) {
		if(		td == Type_Direction.right && getLastDir() == Type_Direction.left ||
				td == Type_Direction.left && getLastDir() == Type_Direction.right ||
				td == Type_Direction.up && getLastDir() == Type_Direction.down ||
				td == Type_Direction.down && getLastDir() == Type_Direction.up) {
			return false;
		}
		return true;
	}
	
	/**
	 * Pontokat n�veli, ha �telre l�pett
	 */
	public void increasePoints() {
		points += 10;
		point_text.setText(String.valueOf(points));
	}
	
	
	
	
	//A robotot ir�nyit� met�dusok
	/**
	 * Az alm�hoz  rekurzivan megkeresi az utat, majd elmegy oda
	 */
	public void robotControl() {
		Point snake = getHead();
		int foodInt = findFood();
		Point food = new Point(foodInt % map.getMapSize(), (foodInt - (foodInt % map.getMapSize()))/map.getMapSize());
		checkIt(snake, food, null);
		erremegyek();
	}
	
	//Sajnos az optim�lis �t megtal�l�s�nak �rdek�ben a f�ggv�ny kicsit hossz� lett, ez�rt eln�z�st k�rek!
	/**
	 * Az �tkeres�st rekurzivan keres� f�ggv�ny
	 * @param p - A vizsg�lt pont
	 * @param food - Az alma helye
	 * @param td - Az ir�ny, ahonnan �rkezett (hogy arra m�r ne keressen �jra)
	 * @return - Ha megtal�lta az utat, igazzal t�r vissza
	 */
	public boolean checkIt(Point p, Point food, Type_Direction td) {
		
		if(p.getX() == food.getX() && p.getY() == food.getY()) {
			return true;
		} else {
			if(p.getX() < food.getX()) {
				if(p.getY() < food.getY()) { //jobb le
					if(td != Type_Direction.right && canMove(p, Type_Direction.right) == 1) {
						if(checkIt(new Point((int)p.getX()+1, (int)p.getY()), food, Type_Direction.left)) {
							lepj(Type_Direction.right);
							return true;
						}	
					} else if(td != Type_Direction.down && canMove(p, Type_Direction.down) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()+1), food, Type_Direction.up)) {
							lepj(Type_Direction.down);
							return true;
						}
					} else if(td != Type_Direction.left && canMove(p, Type_Direction.left) == 1) {
						if(checkIt(new Point((int)p.getX()-1, (int)p.getY()), food, Type_Direction.right)) {
							lepj(Type_Direction.left);
							return true;
						}
					} else if(td != Type_Direction.up && canMove(p, Type_Direction.up) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()-1), food, Type_Direction.down)) {
							lepj(Type_Direction.up);
							return true;
						}
					}
					
				} else { //jobb fel
					if (td != Type_Direction.right && canMove(p, Type_Direction.right) == 1) {
						if(checkIt(new Point((int)p.getX()+1, (int)p.getY()), food, Type_Direction.left)) {
							lepj(Type_Direction.right);
							return true;
						}
					} else if(td != Type_Direction.up && canMove(p, Type_Direction.up) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()-1), food, Type_Direction.down)) {
							lepj(Type_Direction.up);
							return true;
						}
					} else if(td != Type_Direction.left && canMove(p, Type_Direction.left) == 1) {
						if(checkIt(new Point((int)p.getX()-1, (int)p.getY()), food, Type_Direction.right)) {
							lepj(Type_Direction.left);
							return true;
						}
					} else if(td != Type_Direction.down && canMove(p, Type_Direction.down) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()+1), food, Type_Direction.up)) {
							lepj(Type_Direction.down);
							return true;
						}
					}
				}
			} else if(p.getX() == food.getX()) {
				if(p.getY () < food.getY()) { // egy oszlopban lefele
					if(td != Type_Direction.down && canMove(p, Type_Direction.down) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()+1), food, Type_Direction.up)) {
							lepj(Type_Direction.down);
							return true;
						}
					} else if (td != Type_Direction.right && canMove(p, Type_Direction.right) == 1) {
						if(checkIt(new Point((int)p.getX()+1, (int)p.getY()), food, Type_Direction.left)) {
							lepj(Type_Direction.right);
							return true;
						}
					} else if(td != Type_Direction.left && canMove(p, Type_Direction.left) == 1) {
						if(checkIt(new Point((int)p.getX()-1, (int)p.getY()), food, Type_Direction.right)) {
							lepj(Type_Direction.left);
							return true;
						}
					} else if(td != Type_Direction.up && canMove(p, Type_Direction.up) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()-1), food, Type_Direction.down)) {
							lepj(Type_Direction.up);
							return true;
						}
					}
				} else { //egy oszlopban felfele
					if(td != Type_Direction.up && canMove(p, Type_Direction.up) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()-1), food, Type_Direction.down)) {
							lepj(Type_Direction.up);
							return true;
						}
					} else if (td != Type_Direction.right && canMove(p, Type_Direction.right) == 1) {
						if(checkIt(new Point((int)p.getX()+1, (int)p.getY()), food, Type_Direction.left)) {
							lepj(Type_Direction.right);
							return true;
						}
					} else if(td != Type_Direction.left && canMove(p, Type_Direction.left) == 1) {
						if(checkIt(new Point((int)p.getX()-1, (int)p.getY()), food, Type_Direction.right)) {
							lepj(Type_Direction.left);
							return true;
						}
					} else if(td != Type_Direction.down && canMove(p, Type_Direction.down) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()+1), food, Type_Direction.up)) {
							lepj(Type_Direction.down);
							return true;
						}
					}
				}
			} else {
				if(p.getY() < food.getY()) { //balra le
					if(td != Type_Direction.left && canMove(p, Type_Direction.left) == 1) {
						if(checkIt(new Point((int)p.getX()-1, (int)p.getY()), food, Type_Direction.right)) {
							lepj(Type_Direction.left);
							return true;
						}
					} else if(td != Type_Direction.down && canMove(p, Type_Direction.down) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()+1), food, Type_Direction.up)) {
							lepj(Type_Direction.down);
							return true;
						}
					} else if (td != Type_Direction.right && canMove(p, Type_Direction.right) == 1) {
						if(checkIt(new Point((int)p.getX()+1, (int)p.getY()), food, Type_Direction.left)) {
							lepj(Type_Direction.right);
							return true;
						}
					} else if(td != Type_Direction.up && canMove(p, Type_Direction.up) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()-1), food, Type_Direction.down)) {
							lepj(Type_Direction.up);
							return true;
						}
					}
					
				} else { //balra fel
					if(td != Type_Direction.left && canMove(p, Type_Direction.left) == 1) {
						if(checkIt(new Point((int)p.getX()-1, (int)p.getY()), food, Type_Direction.right)) {
							lepj(Type_Direction.left);
							return true;
						}
					} else if(td != Type_Direction.up && canMove(p, Type_Direction.up) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()-1), food, Type_Direction.down)) {
							lepj(Type_Direction.up);
							return true;
						}
					} else if(td != Type_Direction.right && canMove(p, Type_Direction.right) == 1) {
						if(checkIt(new Point((int)p.getX()+1, (int)p.getY()), food, Type_Direction.left)) {
							lepj(Type_Direction.right);
							return true;
					}
					} else if(td != Type_Direction.down && canMove(p, Type_Direction.down) == 1) {
						if(checkIt(new Point((int)p.getX(), (int)p.getY()+1), food, Type_Direction.up)) {
							lepj(Type_Direction.down);
							return true;
						}
					}
					
				}
				
			}
		}
		return false;
	}
	
	/**
	 * A p�ly�n megkeresi az alm�t
	 * @return - Az alma indexe a list�ban, ha nem tal�lt alm�t, -1-gyel t�r vissza
	 */
	public int findFood() {
		for(int i = 0; i < map.getMapSize()*map.getMapSize(); i++) {
			if(map.getMapElement(i).getFieldState() == FieldState.food) {
				return i;
			}
		}
		return -1;
	}
	
	//1 ... can move
	//0 ... food
	//-1 ... lose
	/**
	 * A robot kigy� l�p�s�nek lehet�s�g�t eld�nt� met�dus (elt�r a j�t�kosok�t�l)
	 * @param p - A vizsg�lt pont, ahonnan k�rbetekint
	 * @param td - A vizsg�lt ir�ny
	 * @return - A l�p�s lehet�s�g�nek eredm�nye.
	 * 				�rt�ke lehet:	-1:	A kigy� veszit
	 * 								0:	�telre l�pett
	 * 								1:	Szabad l�pni az adott mez�re 
	 */
	public synchronized int canMove(Point p, Type_Direction td) {
		int x = (int)p.getX();
		int y = (int)p.getY();
		if(td == Type_Direction.right) {
			x++;
		} else if(td == Type_Direction.left) {
			x--;
		} else if(td == Type_Direction.up) {
			y--;
		} else if(td == Type_Direction.down) {
			y++;
		} 
		
		
		if(x >= 0 && x < map.getMapSize()) {
			if(y >= 0 && y <map.getMapSize()) {
				FieldState state = map.getMap().get(x + y*map.getMapSize()).getFieldState();
				if(state == FieldState.empty){
				}	else if(state == FieldState.food) { return 1;}
				else if(state == FieldState.snake1_body || state == FieldState.snake2_body) { return 1;} //Az�rt, hogy ne legyen halhatatlan
				else if(state == FieldState.snake1_head || state == FieldState.snake2_head) {return 1;} //Az�rt, hogy ne legyen halhatatlan
			} else return -1; 	//y out
		} else return -1; 	//x out
		
		return 1 ;
	}
	
	/**
	 * A robotkigy�t l�ptet� met�dus
	 */
	public void erremegyek () {
		for(int i = erremenj.size()-1; i >= 0; i--) {
			if(map.getGame().getSettings().getGameRuns()) {
				try {
					Thread.sleep(map.getGame().getSettings().getSpeed());
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				if(erremenj.size() != 0)
					setMoveDir(erremenj.get(i));
				move();
			}
		}
		erremenj.clear();
	}
	/**
	 * Az utat t�rol� t�mbh�z hozz�ad egy �j l�p�st
	 * @param td - Az adott l�p�s
	 */
	public synchronized void lepj(Type_Direction td) {
		erremenj.add(td);
	}
	
}
