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
 * A Map oszt�lyt tesztel� tesztoszt�ly. A tesztel� met�dusok 
 * mindenhol egyben a getter met�dusok m�k�d�s�t is ellen�rzik.
 * 
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Test_Map {
	/**
	 * Tesztelend� objektum
	 */
	private Map map;
	/**
	 * A tesztel�shez sz�ks�ges Game objektum
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
		set.setMode(GameMode.singleplayer);
		game = new Game(set);
		map = new Map(game);
		map.addFood();
		game.addSnakes();
	}
	
	/**
	 * A konstrukort tesztel� met�dus
	 */
	@Test
	public void testCtor() {
		Assert.assertSame(game, map.getGame());
		Assert.assertNotNull(map.getMap());
		Assert.assertNotEquals(0, map.getMap().size());
		Assert.assertNotNull(map.getMap().get(0));
	}
	
	/**
	 * A settereket tesztel� met�dus
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
	 * A p�ly�t reprezent�l� lista elemeit �s az alm�k sz�m�t tesztel� met�dus
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
