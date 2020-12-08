package snake_test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import application.Settings;
import application.Settings.Difficulty;
import application.Settings.GameMode;

/**
 * A Settings oszt�lyt tesztel� tesztoszt�ly. A tesztel� met�dusok 
 * mindenhol egyben a getter met�dusok m�k�d�s�t is ellen�rzik.
 * 
 * @author Proh�szka Botond Bendeg�z
 *
 */
public class Test_Settings {
	/**
	 * A tesztelend� objektum
	 */
	private Settings set;
	
	/**
	 * A tesztek el�tt lefut� inicializ�l� met�dus
	 */
	@Before
	public void setUp() {
		set = new Settings();
	}
	
	/**
	 * A konstrukort tesztel� met�dus
	 */
	@Test
	public void testCtor() {
		Assert.assertNotNull(set.getScoreboard());
		Assert.assertNotNull(set.getMediaPlayers());
		Assert.assertNotNull(set.getDif());
	}
	
	/**
	 * A settereket tesztel� met�dus
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