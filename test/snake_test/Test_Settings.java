package snake_test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import application.Settings;
import application.Settings.Difficulty;
import application.Settings.GameMode;

/**
 * A Settings osztályt tesztelõ tesztosztály. A tesztelõ metódusok 
 * mindenhol egyben a getter metódusok mûködését is ellenõrzik.
 * 
 * @author Prohászka Botond Bendegúz
 *
 */
public class Test_Settings {
	/**
	 * A tesztelendõ objektum
	 */
	private Settings set;
	
	/**
	 * A tesztek elõtt lefutó inicializáló metódus
	 */
	@Before
	public void setUp() {
		set = new Settings();
	}
	
	/**
	 * A konstrukort tesztelõ metódus
	 */
	@Test
	public void testCtor() {
		Assert.assertNotNull(set.getScoreboard());
		Assert.assertNotNull(set.getMediaPlayers());
		Assert.assertNotNull(set.getDif());
	}
	
	/**
	 * A settereket tesztelõ metódus
	 */
	@Test
	public void testSetters() {
		double volume = 0.2;
		set.setVolume(volume);
		Assert.assertEquals(volume, set.getVolume(), 1e-3);
		
		GameMode gamemode = GameMode.multiplayer;
		set.setMode(gamemode);
		Assert.assertEquals(gamemode, set.getMode());
		
		Difficulty diff = Difficulty.hard;
		set.setDif(diff);
		Assert.assertEquals(diff, set.getDif());
		Assert.assertEquals(40, set.getSpeed());
	}
}