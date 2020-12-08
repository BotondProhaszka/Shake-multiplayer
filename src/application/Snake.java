package application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import application.Field.FieldState;



/**
 * A játékban szereplõ kjgyók osztálya. A felelõsségük reprezentálni a kigyót, mozogni a pályán,
 * enni az almákat , nõni és meghalni. A robot kigyó képes magát irányitani. A robotkigyónak 3-as
 * a snakeID-ja.
 * @author Prohászka Botond Bendegúz
 *
 */
public class Snake extends Thread {
	//Enum
	/**
	 * A mozgás irányát reprezentáló tipus 
	 */
	public enum Type_Direction {up, down, right, left};
	
	//Adattagok
	/**
	 * A kigyó azonositója
	 */
	private int snakeID;
	/**
	 * A jelenlegi mozgási irány
	 */
	private Type_Direction move_dir;
	/**
	 * Az elõzõ lépés iránya
	 */
	private Type_Direction last_dir;
	/**
	 * A kigyó teste, Point-ok listája
	 */
	private List<Point> body;
	/**
	 * Az aktuális pálya
	 */
	private Map map;
	/**
	 * A játékos pontjai
	 */
	private int points = 0;
	/**
	 * A játékos pontjait megjelenitõ MyText objektum
	 */
	private MyText point_text;
	/**
	 * A játékos nevét megjelenitõ MyText objektum
	 */
	private MyText name;
	/**
	 * A gyõzelem tényét tároló adattag
	 */
	private boolean winner = true;
	/**
	 * A robotkigyó mozgásához szükséges lista, ami tárolja a mozgási irányokat
	 */
	private ArrayList<Type_Direction> erremenj = new ArrayList<>();
	
	
	//Konstruktor
	/**
	 * Létrehozza a szükséges objektumokat, beállitja az alapérrtelmezett értékeket
	 * @param i - A kigyó ID-je
	 * 				Értéke lehet:	1:	1es jétkos
	 * 								2:	2es játékos
	 * 								3:	Robot
	 * @param m - Az adott pálya
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
	 * Visszaadja a kigyó ID-ját
	 * @return - SnakeID
	 */
	public int getSnakeID() {
		return snakeID;
	}
	/**
	 * Visszaadja a jelenlegi mozgási irányt
	 * @return - Aktuális irány
	 */
	public Type_Direction getMoveDir() {
		return move_dir;
	}
	/**
	 * Visszaadja az elõzõ lépés irányát
	 * @return - Az elõzõ lépés iránya
	 */
	public Type_Direction getLastDir() {
		return last_dir;
	}
	/**
	 * Visszaadja a kigyó testének pointjait tároló listát
	 * @return - A test listája
	 */
	public List<Point> getBody(){
		return body;
	}
	/**
	 * Visszaadja az aktuális pályát
	 * @return - Az aktuális map
	 */
	public Map getMap() {
		return map;
	}
	/**
	 * Visszaadja a pontok számát
	 * @return - A pontok száma
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * Visszaadja a pontot megjelenitõ MyText objektumot
	 * @return - A pontot megjelenitõ MyText objektum
	 */
	public MyText getPointMyText() {
		return point_text;
	}
	/**
	 * Visszaadja a játékos nevét
	 * @return - A játékos neve
	 */
	public String getSnakeName() {
		return name.getText();
	}
	/**
	 * Visszaadja a játékos nevét megjelenitõ MyText objektumot
	 * @return - A nevet megjelenitõ MyText objektum
	 */
	public MyText getSnakeNameMyText() {
		return name;
	}
	/**
	 * Visszaadja, hogy nyert-e a játékos
	 * @return - Az eredmény
	 */
	public  boolean getWinner() {
		return winner;
	}
	/**
	 * A robotkigyó útjánka listája
	 * @return - Az itányokat tartalmazó lista
	 */
	public List<Type_Direction> getErremenj(){
		return erremenj;
	}
	
	
	
	//Setterek
	/**
	 * Beállitja a kigyó ID-ját
	 * @param i - A beállitandó érték
	 */
	public void setSnakeID(int i) {
		snakeID = i;
	}
	/**
	 * Beállitja a jelenlegi irányt és módositja az utolsó lépés irányának értékét is
	 * @param td - A beállitandó érték
	 */
	public void setMoveDir(Type_Direction td) {
		if(checkDirection(td)) {
			setLastDir(move_dir);
			move_dir = td;
		}
	}
	/**
	 * Beállitja az udolsó lépés irányát
	 * @param td - A beállitandó érték
	 */
	public void setLastDir(Type_Direction td) {
		last_dir = td;
	}
	/**
	 * Beállitja a kigyó testét tároló listát
	 * @param l - A beállitandó objektum
	 */
	public void setBody(List<Point> l){
		body = l;
	}
	/**
	 * Beállitja a pályát
	 * @param m - A beállitandó objektum
	 */
	public void setMap(Map m) {
		map = m;
	}
	/**
	 * Beállitja a pontokat
	 * @param i - A beállitandó érték
	 */
	public void setPoints(int i) {
		points = i;
		point_text.setText(String.valueOf(points));
	}
	/**
	 * Beáéllitja a pontokat megjelenitõ MyTextet
	 * @param mt - A beállitandó objektum
	 */
	public void setPointMyText(MyText mt) {
		point_text = mt;
	}
	/**
	 * Beállitja a kigyó nevét
	 * @param s - A beállitandó érték
	 */
	public void setSnakeName(String s) {
		name.setText(s);
	}
	/**
	 * Beállitja a nevet megjelenitõ MyText-et
	 * @param mt - A beállitandó objektum
	 */
	public void setSnakeNameMyText(MyText mt) {
		name = mt;
	}
	/**
	 * Beállitja a nyerés értékét
	 * @param b - A beállitandó érték
	 */
	public void setWinner(boolean b) {
		winner = b;
	}
	
	
	
	
	//Algoritmusok
	/**
	 * Felhelyezi a kigyót a pályára
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
	 * Leveszi a kigyót a pályáról
	 */
	public void removeSnake() {
		for(int i = 0; i < body.size(); i++) {
			map.getMapElement(pointToInt(body.get(i))).setFieldState(FieldState.empty);
		}
		//map.getMap().forEach((n) -> n.setFieldState(FieldState.empty));
	}
	/**
	 * VIsszaállitja a kigyót eredeti helyzetbe
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
	 * A lépést intézõ függvény (vár, majd lép)
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
	 * A kigyó mozgásáért felelõs metódus
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
	 * Egy lépés megtételéért felelõs metódus.
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
	 * Kivesz egy pontot a testbõl
	 * @param p - A kivevendõ pont
	 */
	public void remove(Point p) {
		map.getMap().get(pointToInt(p)).setFieldState(FieldState.empty);
		body.remove(p);
	}
	/**
	 * Egy adott pont (x,y) helyét (indexét) számolja ki az adot pálya mezõ listájában
	 * @param p - A keresett pont
	 * @return - A pont indexe
	 */
	public int pointToInt(Point p) {
		int size = map.getMapSize();
		return (int)p.getY() * size + (int)p.getX();
	}
	/**
	 * Visszaadja a kigyó fejét
	 * @return - A kigyó feje
	 */
	public Point getHead() {
		return body.get(body.size()-1);
	}
	/**
	 * VIsszaadja a kigyó farkát
	 * @return - A kigyó testének utolsó eleme
	 */
	public Point getTail() {
		return body.get(0);
	}
	
	///1 ... can move
	///0 ... food
	///-1 ... lose
	/**
	 * A lépés lehetõségét eldöntõ metódus
	 * @return - A lépés lehetõségének eredménye.
	 * 				Értéke lehet:	-1:	A kigyó veszit
	 * 								0:	Ételre lépett
	 * 								1:	Szabad lépni az adott mezõre 
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
	 * Az irányváltást ellenõrzi (azért, hogy ellenkezõ irányba ne tudjon lépni)
	 * @param td - Az ellenõrizendõ irány
	 * @return - A kiértékelés eredménye
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
	 * Pontokat növeli, ha ételre lépett
	 */
	public void increasePoints() {
		points += 10;
		point_text.setText(String.valueOf(points));
	}
	
	
	
	
	//A robotot irányitó metódusok
	/**
	 * Az almához  rekurzivan megkeresi az utat, majd elmegy oda
	 */
	public void robotControl() {
		Point snake = getHead();
		int foodInt = findFood();
		Point food = new Point(foodInt % map.getMapSize(), (foodInt - (foodInt % map.getMapSize()))/map.getMapSize());
		checkIt(snake, food, null);
		erremegyek();
	}
	
	//Sajnos az optimális út megtalálásának érdekében a függvény kicsit hosszú lett, ezért elnézést kérek!
	/**
	 * Az útkeresést rekurzivan keresõ függvény
	 * @param p - A vizsgált pont
	 * @param food - Az alma helye
	 * @param td - Az irány, ahonnan érkezett (hogy arra már ne keressen újra)
	 * @return - Ha megtalálta az utat, igazzal tér vissza
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
	 * A pályán megkeresi az almát
	 * @return - Az alma indexe a listában, ha nem talált almát, -1-gyel tér vissza
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
	 * A robot kigyó lépésánek lehetõségét eldöntõ metódus (eltér a játékosokától)
	 * @param p - A vizsgált pont, ahonnan körbetekint
	 * @param td - A vizsgált irány
	 * @return - A lépés lehetõségének eredménye.
	 * 				Értéke lehet:	-1:	A kigyó veszit
	 * 								0:	Ételre lépett
	 * 								1:	Szabad lépni az adott mezõre 
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
				else if(state == FieldState.snake1_body || state == FieldState.snake2_body) { return 1;} //Azért, hogy ne legyen halhatatlan
				else if(state == FieldState.snake1_head || state == FieldState.snake2_head) {return 1;} //Azért, hogy ne legyen halhatatlan
			} else return -1; 	//y out
		} else return -1; 	//x out
		
		return 1 ;
	}
	
	/**
	 * A robotkigyót léptetõ metódus
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
	 * Az utat tároló tömbhöz hozzáad egy új lépést
	 * @param td - Az adott lépés
	 */
	public synchronized void lepj(Type_Direction td) {
		erremenj.add(td);
	}
	
}
