/**
 * 
 */
package snake_test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Az összes tesztosztályt egybefoglaló osuztály
 * 
 * @author Prohászka Botond Bendegúz
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
	Test_Game.class,
	Test_Map.class,
	Test_Settings.class,
	Test_Snake.class
})
public class AllTests {
	///Az összes tesztet egyszerre lefuttató osztály
}
