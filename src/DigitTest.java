import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;



public class DigitTest {

	private Digit.StaticTestHook testHook;
	private String digitString1 =   " _ "
		       					+ "| |"
		       					+ "|_|";
	private String digitString2 =   "   "
									+ "| |"
									+ "|_|";
	private String digitString3 =  " _ "
								+ "|  "
								+ "|_|";
	@Before
	public void setUp() throws Exception {
		/* Set up the object to be tested */
		testHook = new Digit.StaticTestHook();
	}
	
	//test digitOf
	@Test
	public void test_digitOf_without_digitStandFor() {
		Digit digit = Digit.digitOf(" _ "
			       + "| |"
		           + "|_|");
		assertEquals(digit.getDigitStandFor().get(), Integer.valueOf(0));
	}
	
	@Test
	public void test_digitOf_with_digitStandFor() {
		Digit digit = Digit.digitOf(" _ "
			       + "| |"
		           + "|_|",Optional.of(0));
		assertEquals(digit.getDigitStandFor().get(), Integer.valueOf(0));
	}
	
	//test digitStringToBoolean
	@Test
	public void test_digitStringToBoolean() {
		Boolean[] segments = testHook.digitStringToBoolean(digitString1);
		assertTrue(segments[0]);
		assertTrue(segments[1]);
		assertFalse(segments[2]);
		assertTrue(segments[3]);
		assertTrue(segments[4]);
		assertTrue(segments[5]);
		assertTrue(segments[6]);
	}
	
	//test verifyDigit
	@Test
	public void test_verifyDigt() {
		Boolean[] segments = testHook.digitStringToBoolean(digitString1);
		assertEquals(testHook.verifyDigit(segments).get(), Integer.valueOf(0));

		Boolean[] segments2 = testHook.digitStringToBoolean(digitString2);
		assertEquals(testHook.verifyDigit(segments2).get(), Integer.valueOf(0));
	}

	//test getDigitFromResult
	@Test
	public void test_getDigitFromResult() {

		Boolean[] segments = testHook.digitStringToBoolean(digitString2);
		List<Integer> resultList = new ArrayList<Integer>();
		
		for(Digit.StandardDigit digit: Digit.StandardDigit.values()) {
			if(testHook.digitsFullyMatch(segments,digit.getDigit().getSegments())) {
				
			}
				
			if(testHook.digitsHalfMatch(segments, digit.getDigit().getSegments()))
					resultList.add(digit.getDigitStandFor());
		}
		
		assertEquals(testHook.getDigitFromResult(resultList).get(), Integer.valueOf(0));
		
		Boolean[] segments2 = testHook.digitStringToBoolean(digitString3);
		List<Integer> resultList2 = new ArrayList<Integer>();
		
		for(Digit.StandardDigit digit: Digit.StandardDigit.values()) {
			if(testHook.digitsFullyMatch(segments2,digit.getDigit().getSegments())) {
				
			}
				
			if(testHook.digitsHalfMatch(segments2, digit.getDigit().getSegments()))
					resultList2.add(digit.getDigitStandFor());
		}
		
		assertFalse(testHook.getDigitFromResult(resultList2).isPresent());
	}
	
	@Test
	public void test_digitsFullyMatch() {
		
		assertTrue(testHook.digitsFullyMatch(testHook.digitStringToBoolean(digitString1), testHook.digitStringToBoolean(digitString1)));
		assertFalse(testHook.digitsFullyMatch(testHook.digitStringToBoolean(digitString1), testHook.digitStringToBoolean(digitString2)));
	}
	
	@Test
	public void test_digitsHalfMatch() {
		
		assertTrue(testHook.digitsHalfMatch(testHook.digitStringToBoolean(digitString2), testHook.digitStringToBoolean(digitString1)));
		assertFalse(testHook.digitsFullyMatch(testHook.digitStringToBoolean(digitString1), testHook.digitStringToBoolean(digitString2)));
	}
	
	@Test
	public void test_isSegmentABar() {
		assertTrue(testHook.isSegmentABar('|'));
		assertFalse(testHook.isSegmentAnUnderscore(' '));
	}
	
	@Test
	public void test_isSegmentAnUnderscore() {
		assertTrue(testHook.isSegmentAnUnderscore('_'));
		assertFalse(testHook.isSegmentABar(' '));
	}
	
}
