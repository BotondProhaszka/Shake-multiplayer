package application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import application.Field.FieldState;

public class Snake extends Thread {
	protected enum Type_Direction {up, down, right, left};
	protected int snakeID;
	protected Type_Direction move_direction;
	protected Type_Direction last_dir;
	protected List<Point> body;
	protected Map map;
	
	protected Snake(int i, Map m) {
		snakeID = i;
		body = new ArrayList<>();
		move_direction = Type_Direction.right;
		map = m;
	}
	
	protected void placeSnake(Map map) {
		body.add(new Point(10, (snakeID *map.getMapSize()/3)));
		if(snakeID == 1) map.getMapElement(pointToInt(getHead(), map)).setFieldState(FieldState.snake1_head);
		else if(snakeID == 2) map.getMapElement(pointToInt(getHead(), map)).setFieldState(FieldState.snake2_head);
	}
	
	public void run() {
		while(true) {
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
		last_dir = move_direction;
		if(n == 1 || n == 0) {
			oneStep(map);
			if(n == 1)
				remove(getTail(), map);
			else 
				map.addFood();
		} else if(n == -1) {
			
		} else if(n == -2) {
			
		} else {
			
		}
	}
	
	protected synchronized void oneStep(Map map) {
		int x = (int)getHead().getX();
		int y = (int)getHead().getY();

		if(move_direction == Type_Direction.right) {
			body.add(new Point(x + 1, y));
		} else if(move_direction == Type_Direction.left) {
			body.add(new Point(x - 1, y));
		} else if(move_direction == Type_Direction.up) {
			body.add(new Point(x, y - 1));
		} else if(move_direction == Type_Direction.down) {
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
	
	///1 ... can move
	///0 ... food
	///-1 ... lose
	///-2 draw
	protected int canMove(Map map) {
		int x = 0, y = 0;
		if(move_direction == Type_Direction.right) {
			x = (int)getHead().getX() + 1;
			y = (int)getHead().getY();
		} else if(move_direction == Type_Direction.left) {
			x = (int)getHead().getX() - 1;
			y = (int)getHead().getY();
		} else if(move_direction == Type_Direction.up) {
			x = (int)getHead().getX();
			y = (int)getHead().getY() - 1;
		} else if(move_direction == Type_Direction.down) {
			x = (int)getHead().getX();
			y = (int)getHead().getY() + 1;
		} 
		
		
		if(checkDirection()) {
			if(x >= 0 && x < map.getMapSize()) {
				if(y >= 0 && (x + y*map.getMapSize()) < 2500) {
					FieldState state = map.map.get(x + y*map.getMapSize()).getFieldState();
					if(state == FieldState.empty){
					}	else if(state == FieldState.food) { return 0;}
						else if(state == FieldState.snake1_body || state == FieldState.snake2_body) { return -1;}
						else if(state == FieldState.snake1_head || state == FieldState.snake2_head) {return -2;}
				} else return -1; 	//y out
			} 
			else return -1; 	//x out
		} else return -1;
		
		return 1 ;
	}
	
	protected boolean checkDirection() {
		if(		move_direction == Type_Direction.right && last_dir == Type_Direction.left ||
				move_direction == Type_Direction.left && last_dir == Type_Direction.right ||
				move_direction == Type_Direction.up && last_dir == Type_Direction.down ||
				move_direction == Type_Direction.down && last_dir == Type_Direction.up) {
			return false;
		}
		return true;
	}
	
}
