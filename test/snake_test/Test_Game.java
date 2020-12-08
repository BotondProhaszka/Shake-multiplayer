package snake_test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import application.Game;
import application.Map;
import application.Settings;
import application.Settings.GameMode;

/**
 * A Game osztályt tesztelõ tesztosztály. A tesztelõ metódusok 
 * mindenhol egyben a getter metódusok mûködését is ellenõrzik.
 * 
 * @author Prohászka Botond Bendegúz
 *
 */
public class Test_Game {
	/**
	 * A tesztelendõ objektum
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
		game = new Game(set);
	}
	
	/**
	 * A konstrukort tesztelõ metódus
	 */
	@Test
	public void testCtor() {
		Assert.assertSame(set, game.getSettings());
		Assert.assertNotNull(game.getMap());			
		Assert.assertNotNull(game.getSnakeList());
		Assert.assertNotNull(game.getThreadList());
		Assert.assertNotNull(game.getPointList());
	}
	
	/**
	 * A settereket tesztelõ metódus
	 */
	@Test
	public void testSetters() {
		Settings set2 = new Settings();
		game.setSettings(set2);
		Assert.assertSame(set2, game.getSettings());
		
		Map map = new Map(game);
		game.setMap(map);
		Assert.assertSame(map, game.getMap());
	}
	
	/**
	 * A kigyók pályához való hozzáadását tesztelõ meódus
	 */
	@Test
	public void testAddSnakes() {
		set.setMode(GameMode.singleplayer);
		game.addSnakes();
		
		Assert.assertNotNull(game.getSnakeList());
		Assert.assertNotNull(game.getSnakeList().get(0));
		Assert.assertEquals(1, game.getSnakeList().size());
		Assert.assertEquals(2,  game.getSnakeList().get(0).getBody().size());
	}
	
}
