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
 * A Snake oszt�lyt tesztel� tesztoszt�ly. A tesztel� met�dusok 
 * mindenhol egyben a getter met�dusok m�k�d�s�t is ellen�rzik.
 * 
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Test_Snake {
	/**
	 * A tesztelend� objektum
	 */
	private Snake snake;
	/**
	 * A tesztekhez sz�ks�ges Settings objektum
	 */
	private Settings set;
	/**
	 * A tesztekhez sz�ks�ges Game objektum
	 */
	private Game game;
	/**
	 * A tesztekhez sz�ks�ges Map objektum
	 */
	private Map map;
	
	
	/**
	 * A tesztek el�tt lefut� inicializ�l� met�dus
	 */
	@Before
	public void setUp() {
		set = new Settings();
		game = new Game(set);
		map = new Map(game);
		snake = new Snake(1, map);
	}
	
	/**
	 * A konstrukort tesztel� met�dus
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
	 * A settereket tesztel� met�dus
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
	 * A kigy� lehelyez�s�t tesztel� met�dus
	 */
	@Test 
	public void testPlaceSnake() {
		
		snake.placeSnake();
		
		Assert.assertEquals(2, snake.getBody().size());
	}
	
	/**
	 * A kigy� alaphelyzetbe�llit�s�t ellen�rz� met�dus
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
	 * Leelen�rzi, hogy a kjgy� lev�tele sor�n elt�nik-e a p�ly�r�l
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
	 * Az ir�ny ellen�rz�s�t tesztel� met�dus
	 */
	@Test
	public void testCheckDirection() {
		snake.setLastDir(Type_Direction.right);
		Assert.assertTrue(snake.checkDirection(Type_Direction.up));
		Assert.assertFalse(snake.checkDirection(Type_Direction.left));
	}

}
