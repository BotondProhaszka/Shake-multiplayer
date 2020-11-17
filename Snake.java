package application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import application.Field.FieldState;
import javafx.scene.text.Text;

public class Snake extends Thread {
	protected enum Type_Direction {up, down, right, left};
	protected int snakeID;
	protected Type_Direction move_dir;
	protected Type_Direction last_dir;
	protected List<Point> body;
	protected Map map;
	protected int speed;
	protected int points = 0;
	protected Text point_text;
	protected Text name;
	
	protected Snake(int i, Map m) {
		snakeID = i;
		body = new ArrayList<>();
		move_dir = Type_Direction.right;
		last_dir = Type_Direction.right;
		map = m;
		speed = m.getSettings().getSpeed();
		name = new Text();
		point_text = new Text(String.valueOf(points));
	}
	
	protected void setSnakeName(String s) {
		name.setText(s);
	}
	
	
	protected void setMoveDir(Type_Direction td) {
		if(checkDirection(td))
			move_dir = td;
	}
	
	protected void setLastDir(Type_Direction td) {
		last_dir = td;
	}
	

	
	protected Text getSnakeName() {
		return name;
	}
	
	protected Type_Direction getMoveDir() {
		return move_dir;
	}
	
	protected Type_Direction getLastDir() {
		return last_dir;
	}
	
	
	/*
	protected String getMoveText() {
		if (move_dir == Type_Direction.up)
			return "up";
		else if(move_dir == Type_Direction.right)
			return "right";
		else if(move_dir == Type_Direction.down)
			return "down";
		else if(move_dir == Type_Direction.left)
			return "left";
		else
			return "tökömtudja";
	}
	
	protected String getLastText() {
		if (last_dir == Type_Direction.up)
			return "up";
		else if(last_dir == Type_Direction.right)
			return "right";
		else if(last_dir == Type_Direction.down)
			return "down";
		else if(last_dir == Type_Direction.left)
			return "left";
		else
			return "tökömtudja";
	}
	*/
	
	
	protected void placeSnake(Map map) {
		body.add(new Point(10, (snakeID *map.getMapSize()/3)));
		if(snakeID == 1) map.getMapElement(pointToInt(getHead(), map)).setFieldState(FieldState.snake1_head);
		else if(snakeID == 2) map.getMapElement(pointToInt(getHead(), map)).setFieldState(FieldState.snake2_head);
		body.add(new Point(11, (snakeID *map.getMapSize()/3)));
		if(snakeID == 1) map.getMapElement(pointToInt(getHead(), map)).setFieldState(FieldState.snake1_head);
		else if(snakeID == 2) map.getMapElement(pointToInt(getHead(), map)).setFieldState(FieldState.snake2_head);
	}
	
	protected void removeSnake(Map m) {
		for(int i = 0; i < body.size(); i++) {
			m.getMapElement(pointToInt(body.get(i), m)).setFieldState(FieldState.empty);
		}
		
		map.getMap().forEach((n) -> n.setFieldState(FieldState.empty));
	}
	
	
	public void run() {
		while(map.getSettings().getGameRuns()) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			move();
		}
	}
	
	protected synchronized void move() {
		int n = canMove(map);
		if(n == 1 || n == 0) {
			oneStep(map);
			if(n == 0) {
				increasePoints();
				map.addFood();
			}
			else
				remove(getTail(), map);
		} else if(n == -1) {
			if(map.getGame() == null)
				System.out.println("Hehe");
			map.getGame().stopGame();
		} else if(n == -2) {
			map.getGame().stopGame();
		} else if(n == 2) {
			//setMoveDir(getLastDir());
			oneStep(map);
			remove(getTail(), map);
		}
		setLastDir(getMoveDir());
	}
	
	protected synchronized void oneStep(Map map) {
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
		map.getMapElement(pointToInt(getHead(), map)).setFieldState(fs);
	}
	
	protected synchronized void remove(Point p, Map map) {
		map.map.get(pointToInt(p, map)).setFieldState(FieldState.empty);
		body.remove(0);
	}
	
	protected int pointToInt(Point p, Map map) {
		int size = map.getMapSize();
		return (int)p.getY() * size + (int)p.getX();
	}
	
	protected synchronized Point getHead() {
		return body.get(body.size()-1);
	}
	
	protected synchronized Point getTail() {
		return body.get(0);
	}
	
	///2 ... nothing
	///1 ... can move
	///0 ... food
	///-1 ... lose
	///-2 draw
	protected synchronized int canMove(Map map) {
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
				if(y >= 0 && (x + y*map.getMapSize()) < 2500) {
					FieldState state = map.map.get(x + y*map.getMapSize()).getFieldState();
					if(state == FieldState.empty){
					}	else if(state == FieldState.food) { return 0;}
						else if(state == FieldState.snake1_body || state == FieldState.snake2_body) { return -1;}
						else if(state == FieldState.snake1_head || state == FieldState.snake2_head) {return -2;}
				} else return -1; 	//y out
			} else return -1; 	//x out
		//} else return 2; //dir
		
		return 1 ;
	}
	
	protected boolean checkDirection(Type_Direction td) {
		if(		td == Type_Direction.right && getLastDir() == Type_Direction.left ||
				td == Type_Direction.left && getLastDir() == Type_Direction.right ||
				td == Type_Direction.up && getLastDir() == Type_Direction.down ||
				td == Type_Direction.down && getLastDir() == Type_Direction.up) {
			return false;
		}
		return true;
	}
	
	protected void increasePoints() {
		points += 10;
		point_text.setText(String.valueOf(points));
	}
	
}
