package snake_test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import application.Game;
import application.Map;
import application.Settings;
import application.Settings.GameMode;

/**
 * A Game oszt�lyt tesztel� tesztoszt�ly. A tesztel� met�dusok 
 * mindenhol egyben a getter met�dusok m�k�d�s�t is ellen�rzik.
 * 
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Test_Game {
	/**
	 * A tesztelend� objektum
	 */
	private Game game;
	/**
	 * A tesztekhez sz�ks�ges Settings objektum
	 */
	private Settings set;
	
	/**
	 * A tesztek el�tt lefut� inicializ�l� met�dus
	 */
	@Before
	public void setUp() {
		set = new Settings();
		game = new Game(set);
	}
	
	/**
	 * A konstrukort tesztel� met�dus
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
	 * A settereket tesztel� met�dus
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
	 * A kigy�k p�ly�hoz val� hozz�ad�s�t tesztel� me�dus
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
