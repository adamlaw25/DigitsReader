import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DigitReaderTest {

	private DigitReader.StaticTestHook testHook;
	final static List<String> userInput = new ArrayList<String>();
	static {
		userInput.add("    _  _     _  _  _  _  _ ");
		userInput.add("  | _| _||_||_ |_   ||_||_|");
		userInput.add("  ||_  _|  | _||_|  ||_| _|");
	}

	@Before
	public void setUp() throws Exception {
		/* Set up the object to be tested */
		testHook = new DigitReader.StaticTestHook();
	}
	
	@Test
	public void test_getDigitList() throws DigitException {
		List<Digit> digitList = DigitReader.getDigitList(userInput);
		assertEquals(digitList.size(), 9);
		
	}
	@Test
	public void test_verifyInputSegments() throws DigitException {
        List<String> inputDigitStringList = testHook.getInputDigitStringList(userInput);
		assertTrue(testHook.verifyInputSegments(inputDigitStringList));
	}
	
	@Test
	public void test_verifySegmentASpace() throws DigitException {
		assertTrue(testHook.verifySegmentASpace(' '));
	}
	
	@Test
	public void test_verifySegmentABarOrASapce() throws DigitException {
		assertTrue(testHook.verifySegmentABarOrASapce('|'));
	}
	
	@Test
	public void test_verifySegmentAnUnderscoreOrASpace() throws DigitException {
		assertTrue(testHook.verifySegmentAnUnderscoreOrASpace('_'));
	}
	
	@Test
	public void test_getInputDigitStringList(){
		List<String> stringList = testHook.getInputDigitStringList(userInput);
		assertEquals(stringList.size(), 9);
	}
	
	@Test
	public void test_verifyInputSize() throws DigitException {
		assertTrue(testHook.verifyInputSize(userInput));
	}
}

