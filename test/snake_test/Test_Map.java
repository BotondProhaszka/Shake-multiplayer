package snake_test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import application.Field;
import application.Game;
import application.Map;
import application.Settings;
import application.Settings.GameMode;
import application.Field.FieldState;

/**
 * A Map osztályt tesztelõ tesztosztály. A tesztelõ metódusok 
 * mindenhol egyben a getter metódusok mûködését is ellenõrzik.
 * 
 * @author Prohászka Botond Bendegúz
 *
 */
public class Test_Map {
	/**
	 * Tesztelendõ objektum
	 */
	private Map map;
	/**
	 * A teszteléshez szükséges Game objektum
	 */
	private Game game;
	/**
	 * A tesztekhez szükséges Settings objektum
	 */
	private Settings set;
	
	/**
	 * A tesztek elõtt lefutó inicializáló metódus
	 */
	@Before
	public void setUp() {
		set = new Settings();
		set.setMode(GameMode.singleplayer);
		game = new Game(set);
		map = new Map(game);
		map.addFood();
		game.addSnakes();
	}
	
	/**
	 * A konstrukort tesztelõ metódus
	 */
	@Test
	public void testCtor() {
		Assert.assertSame(game, map.getGame());
		Assert.assertNotNull(map.getMap());
		Assert.assertNotEquals(0, map.getMap().size());
		Assert.assertNotNull(map.getMap().get(0));
	}
	
	/**
	 * A settereket tesztelõ metódus
	 */
	@Test
	public void testSetters() {
		Game game2 = new Game(set);
		map.setGame(game2);
		Assert.assertEquals(game2, map.getGame());
		
		List<Field> list = new ArrayList<>();
		map.setMap(list);
		Assert.assertEquals(list, map.getMap());
	}
	
	/**
	 * A pályát reprezentáló lista elemeit és az almák számát tesztelõ metódus
	 */
	@Test
	public void testNumberOfFields() {
		int sum = 0;
		int apples = 0;
		
		for(Field m : map.getMap()){
			sum++;
			if(m.getFieldState() == FieldState.food)
				apples++;
		}
		
		Assert.assertEquals(map.getMapSize()*map.getMapSize(), sum);
		Assert.assertEquals(1, apples);
		
	}
	
}
