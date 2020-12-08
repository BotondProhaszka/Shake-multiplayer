/**
 * 
 */
package snake_test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Az �sszes tesztoszt�lyt egybefoglal� osuzt�ly
 * 
 * @author Proh�szka Botond Bendeg�z
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
	///Az �sszes tesztet egyszerre lefuttat� oszt�ly
}
