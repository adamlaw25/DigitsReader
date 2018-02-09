import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DigitDriverTest {


	private static DigitDriver.StaticTestHook testHook;
	@Before
	public void setUp() throws Exception {
		/* Set up the object to be tested */
		testHook = new DigitDriver.StaticTestHook();
	}
	
	/*
	@Test
	public static void getUserInput() {
		 testHook.getUserInput();
	}*/
	
	@Test
	public void printDigits() throws DigitException {
		List<String> userInput = new ArrayList<String>();
		userInput.add("    _  _     _  _  _  _  _ ");
		userInput.add("  | _| _||_||_ |_   ||_||_|");
		userInput.add("  ||_  _|  | _||_|  ||_| _|");
		assertEquals(testHook.printDigits(userInput), "123456789");
	}

}
