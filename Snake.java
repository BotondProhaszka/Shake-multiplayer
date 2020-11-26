package application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import application.Field.FieldState;
import javafx.scene.text.Text;





public class Snake extends Thread {
	public enum Type_Direction {up, down, right, left};
	
	private int snakeID;
	private Type_Direction move_dir;
	private Type_Direction last_dir;
	private List<Point> body;
	private Map map;
	private int points = 0;
	private MyText point_text;
	private MyText name;
	private boolean winner = true;
	
	private ArrayList<Type_Direction> erremenj = new ArrayList<>();
	
	public Snake(int i, Map m) {
		snakeID = i;
		body = new ArrayList<>();
		move_dir = Type_Direction.right;
		last_dir = Type_Direction.right;
		map = m;
		name = new MyText("", "medium");
		point_text = new MyText(String.valueOf(points), "medium");
	}
	
	public int getSnakeID() {
		return snakeID;
	}
	public Type_Direction getMoveDir() {
		return move_dir;
	}
	
	public Type_Direction getLastDir() {
		return last_dir;
	}
	public List<Point> getBody(){
		return body;
	}
	public Map getMap() {
		return map;
	}
	public int getPoints() {
		return points;
	}
	public MyText getPointMyText() {
		return point_text;
	}
	public String getSnakeName() {
		return name.getText();
	}
	public MyText getSnakeNameMyText() {
		return name;
	}
	public  boolean getWinner() {
		return winner;
	}
	public List<Type_Direction> getErremenj(){
		return erremenj;
	}
	
	
	public void setSnakeID(int i) {
		snakeID = i;
	}
	public void setMoveDir(Type_Direction td) {
		if(checkDirection(td)) {
			setLastDir(move_dir);
			move_dir = td;
		}
	}
	
	public void setLastDir(Type_Direction td) {
		last_dir = td;
	}
	public void setBody(List<Point> l){
		body = l;
	}
	public void setMap(Map m) {
		map = m;
	}
	public void setPoints(int i) {
		points = i;
		point_text.setText(String.valueOf(points));
	}
	public void setPointMyText(MyText mt) {
		point_text = mt;
	}
	public void setSnakeName(String s) {
		name.setText(s);
	}
	public void setSnakeNameMyText(MyText mt) {
		name = mt;
	}
	public void setWinner(boolean b) {
		winner = b;
	}
	
	
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
	
	public void removeSnake() {
		for(int i = 0; i < body.size(); i++) {
			map.getMapElement(pointToInt(body.get(i))).setFieldState(FieldState.empty);
		}
		//map.getMap().forEach((n) -> n.setFieldState(FieldState.empty));
	}
	
	public void resetSnake() {
		removeSnake();
		body.clear();
		setPoints(0);
		placeSnake();
		setLastDir(null);
		setMoveDir(Type_Direction.right);
	}
	
	public void run() {
		while(map.getSettings().getGameRuns()) {
			try {
				Thread.sleep(map.getSettings().getSpeed());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			move();
			if(snakeID == 3) 
				robotControl();
		}
	}
	
	public synchronized void move() {
		int n = canMove();
		
		if(n == 1 || n == 0) {
			
			oneStep();
			if(n == 0) {
		        Music m = new Music("point", map.getSettings());
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
		} else if(n == -2) {
			winner = false;
			map.getGame().draw();
			map.getGame().stopGame();
		} else if(n == 2) {
			oneStep();
			remove(getTail());
		}
		setLastDir(getMoveDir());
	}
	
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
	
	public synchronized void remove(Point p) {
		map.getMap().get(pointToInt(p)).setFieldState(FieldState.empty);
		body.remove(0);
	}
	
	public int pointToInt(Point p) {
		int size = map.getMapSize();
		return (int)p.getY() * size + (int)p.getX();
	}
	
	public synchronized Point getHead() {
		return body.get(body.size()-1);
	}
	
	public synchronized Point getTail() {
		return body.get(0);
	}
	
	///2 ... nothing
	///1 ... can move
	///0 ... food
	///-1 ... lose
	///-2 draw
	public synchronized int canMove() {
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
		
		
		//if(checkDirection()) {
			if(x >= 0 && x < map.getMapSize()) {
				if(y >= 0 && y < map.getMapSize()) {
					FieldState state = map.getMap().get(x + y*map.getMapSize()).getFieldState();
					if(state == FieldState.empty){
					}	else if(state == FieldState.food) { return 0;}
						else if(state == FieldState.snake1_body || state == FieldState.snake2_body) { return -1;}
						else if(state == FieldState.snake1_head || state == FieldState.snake2_head) {return -2;}
				} else return -1; 	//y out
			} else return -1; 	//x out
		//} else return 2; //dir
		
		return 1 ;
	}
	
	public boolean checkDirection(Type_Direction td) {
		if(		td == Type_Direction.right && getLastDir() == Type_Direction.left ||
				td == Type_Direction.left && getLastDir() == Type_Direction.right ||
				td == Type_Direction.up && getLastDir() == Type_Direction.down ||
				td == Type_Direction.down && getLastDir() == Type_Direction.up) {
			return false;
		}
		return true;
	}
	
	
	public void increasePoints() {
		points += 10;
		point_text.setText(String.valueOf(points));
	}
	
	public void robotControl() {
		Point snake = getHead();
		int foodInt = findFood();
		Point food = new Point(foodInt % map.getMapSize(), (foodInt - (foodInt % map.getMapSize()))/map.getMapSize());
		checkIt(snake, food, null);
		erremegyek();
	}
/*	
	public boolean checkIt(Point p, Point food, Type_Direction td) {
		if(p.getX() == food.getX() && p.getY() == food.getY()) {
			return true;
		} else {
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
			
		}
		return false;
	}
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
	
	public int findFood() {
		for(int i = 0; i < map.getMapSize()*map.getMapSize(); i++) {
			if(map.getMapElement(i).getFieldState() == FieldState.food) {
				return i;
			}
		}
		return -1;
	}
	
		///2 ... nothing
		///1 ... can move
		///0 ... food
		///-1 ... lose
		///-2 draw
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
		
		
		//if(checkDirection()) {
			if(x >= 0 && x < map.getMapSize()) {
				if(y >= 0 && y <map.getMapSize()) {
					FieldState state = map.getMap().get(x + y*map.getMapSize()).getFieldState();
					if(state == FieldState.empty){
					}	else if(state == FieldState.food) { return 1;}
						else if(state == FieldState.snake1_body || state == FieldState.snake2_body) { return 1;}
						else if(state == FieldState.snake1_head || state == FieldState.snake2_head) {return 1;}
				} else return -1; 	//y out
			} else return -1; 	//x out
		//} else return 2; //dir
		
		return 1 ;
	}
	
	public void erremegyek () {
		for(int i = erremenj.size()-1; i >= 0; i--) {
			if(map.getSettings().getGameRuns()) {
				try {
					Thread.sleep(map.getSettings().getSpeed());
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
	
	public synchronized void lepj(Type_Direction td) {
		erremenj.add(td);
	}
	
}
