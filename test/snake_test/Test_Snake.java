package snake_test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import application.Field.FieldState;
import application.Game;
import application.Map;
import application.MyText;
import application.Settings;
import application.Snake;
import application.Snake.Type_Direction;

/**
 * A Snake osztályt tesztelõ tesztosztály. A tesztelõ metódusok 
 * mindenhol egyben a getter metódusok mûködését is ellenõrzik.
 * 
 * @author Prohászka Botond Bendegúz
 *
 */
public class Test_Snake {
	/**
	 * A tesztelendõ objektum
	 */
	private Snake snake;
	/**
	 * A tesztekhez szükséges Settings objektum
	 */
	private Settings set;
	/**
	 * A tesztekhez szükséges Game objektum
	 */
	private Game game;
	/**
	 * A tesztekhez szükséges Map objektum
	 */
	private Map map;
	
	
	/**
	 * A tesztek elõtt lefutó inicializáló metódus
	 */
	@Before
	public void setUp() {
		set = new Settings();
		game = new Game(set);
		map = new Map(game);
		snake = new Snake(1, map);
	}
	
	/**
	 * A konstrukort tesztelõ metódus
	 */
	@Test
	public void testCtor() {
		Assert.assertSame(1, snake.getSnakeID());
		Assert.assertNotNull(snake.getBody());
		Assert.assertEquals(Type_Direction.right, snake.getMoveDir());
		Assert.assertEquals(Type_Direction.right, snake.getLastDir());
		Assert.assertSame(map, snake.getMap());
		Assert.assertEquals("", snake.getSnakeName());
	}
	
	/**
	 * A settereket tesztelõ metódus
	 */
	@Test
	public void testSetters() {
		
		int i = 2;
		snake.setSnakeID(i);
		Type_Direction td = Type_Direction.up;
		snake.setMoveDir(td);
		Map map2 = new Map(game);
		snake.setMap(map2);
		int points = 20;
		snake.setPoints(points);
		MyText mytext = new MyText("test name mytext", "small");
		snake.setSnakeNameMyText(mytext);
		String name = "test name";
		snake.setSnakeName(name);
		
		Assert.assertSame(i, snake.getSnakeID());
		Assert.assertSame(td, snake.getMoveDir());
		Assert.assertSame(map2, snake.getMap());
		Assert.assertSame(points, snake.getPoints());
		Assert.assertSame(mytext, snake.getSnakeNameMyText());
		Assert.assertSame(name, snake.getSnakeName());
	}
	
	/**
	 * A kigyó lehelyezését tesztelõ metódus
	 */
	@Test 
	public void testPlaceSnake() {
		
		snake.placeSnake();
		
		Assert.assertEquals(2, snake.getBody().size());
	}
	
	/**
	 * A kigyó alaphelyzetbeállitását ellenõrzõ metódus
	 */
	@Test
	public void testResetSnake() {
		snake.placeSnake();
		snake.resetSnake();
		Assert.assertEquals(2, snake.getBody().size());
		Assert.assertEquals(0, snake.getPoints());
		Assert.assertEquals(Type_Direction.right, snake.getMoveDir());
	}
	
	/**
	 * Leelenõrzi, hogy a kjgyó levétele során eltûnik-e a pályáról
	 */
	@Test
	public void testRemoveSnake() {
		snake.placeSnake();
		List<Point> points = new ArrayList<>();
		for(Point p : snake.getBody()) {
			points.add(p);
		}
		
		snake.removeSnake();
		
		Assert.assertEquals(FieldState.empty, map.getMapElement(snake.pointToInt(points.get(0))).getFieldState());
		Assert.assertEquals(FieldState.empty, map.getMapElement(snake.pointToInt(points.get(1))).getFieldState());
		
	}
	
	/**
	 * Az irány ellenõrzését tesztelõ metódus
	 */
	@Test
	public void testCheckDirection() {
		snake.setLastDir(Type_Direction.right);
		Assert.assertTrue(snake.checkDirection(Type_Direction.up));
		Assert.assertFalse(snake.checkDirection(Type_Direction.left));
	}

}
