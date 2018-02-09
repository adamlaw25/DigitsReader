import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// Represent a digit by 7 booleans (each segment will be true if it is a bar or underscore or false if it is a space)
public class Digit {

	Boolean[] segments;
	
	Optional<Integer> digitStandFor;
	
	private Digit(Boolean[] segments, Optional<Integer> digitStandFor) {
		this.segments = segments;
		this.digitStandFor = digitStandFor;
	}
	
	// The constructor for the enums initialization(signed digitStandFor instead of verify to get the digitStandFor)
	public static Digit digitOf(final String digitString, Optional<Integer> digitStandFor) {
		assert digitString.length() == 9 : "input digit string size incorrect";
		
        Boolean[] segments = digitStringToBoolean(digitString);
        	
        return new Digit(segments, digitStandFor);
    }
	
	// The constructor for all the input except for the enums
	public static Digit digitOf(final String digitString) {
		assert digitString.length() == 9 : "input digit string size incorrect";
		
        	Boolean[] segments = digitStringToBoolean(digitString);
        	
        	Optional<Integer> digitStandFor = verifyDigit(segments);
        	
        return new Digit(segments, digitStandFor);
    }
	
	private static Boolean[] digitStringToBoolean(String digitString) {

        assert digitString.length() == 9 : "Digit is not composited by 9 segments";
        
		//check left top and right top
		assert Character.isWhitespace(digitString.charAt(0)) && Character.isWhitespace(digitString.charAt(2)) : "Digit top segment incorrect";

		Boolean[] segments = new Boolean[7];
		
		segments[0] = isSegmentAnUnderscore(digitString.charAt(1));
		
    		for(int i = 3; i <= 6  ; i = i + 3) {
    			segments[i - 2] = isSegmentABar(digitString.charAt(i));

    			segments[i - 1] = isSegmentAnUnderscore(digitString.charAt(i + 1));

    			segments[i] = isSegmentABar(digitString.charAt(i + 2));
    		}
    		return segments;
	}
	
	private static Optional<Integer> verifyDigit(Boolean[] segments) {

		assert segments.length == 7 : "Segments count incorrect";
		
		List<Integer> resultList = new ArrayList<Integer>();
		
		for(StandardDigit digit: StandardDigit.values()) {
			if(digitsFullyMatch(segments,digit.getDigit().getSegments()))
					return Optional.of(digit.getDigitStandFor());
			if(digitsHalfMatch(segments, digit.getDigit().getSegments()))
					resultList.add(digit.getDigitStandFor());
		}
		
		return getDigitFromResult(resultList);
	}
	
	private static Optional<Integer> getDigitFromResult(List<Integer> resultList){
		
		assert !resultList.isEmpty() : "result list is empty";
		
		if(resultList.size() == 1) {
			return Optional.of(resultList.get(0)); // 8
		}
		else if(resultList.size() == 2) {
			return resultList.get(0) == 8 ? Optional.of(resultList.get(1)) : Optional.of(resultList.get(0));
		}
		else {
			return Optional.empty();
		}
	}
	
	private static Boolean digitsFullyMatch(Boolean[] digit1, Boolean[] digit2) {

		assert digit1.length == 7 && digit2.length == 7: "Segments count incorrect";
		
		Boolean allMatch = true;
		for(int i = 0; i < 7; i++) {
			if(digit1[i] != digit2[i]) {
				allMatch = false;
			}
		}
		return allMatch;
	}
	
	private static Boolean digitsHalfMatch(Boolean[] digit1, Boolean[] digit2) {

		assert digit1.length == 7 && digit2.length == 7: "Segments count is 7";
		
		Boolean halfMatch = true;
		for(int i = 0; i < 7; i++) {
			//if the first digit has a segment that the second digit(Standard digit) does not have
			if(digit1[i] && !digit2[i]) {
				halfMatch = false;
			}
		}
		return halfMatch;
	}
	
	private static Boolean isSegmentABar(char segment) {
		assert segment == '|' || Character.isWhitespace(segment) : "Segment is a bar or a space";
		
		return segment == '|' ? true : false;
	}
	
	private static Boolean isSegmentAnUnderscore(char segment) {
		assert segment == '_' || Character.isWhitespace(segment) : "Segment is an underscore or a space";
		
		return segment == '_' ? true : false;
	}
	
	public final Boolean[] getSegments() {
		return segments;
	}

	public final Optional<Integer> getDigitStandFor() {
		return digitStandFor;
	}

	public enum StandardDigit {
		ZERO(0,  " _ "
		       + "| |"
	           + "|_|"),
		
		ONE(1,   "   "
	           + "  |"
		       + "  |"),
		
		TWO(2,   " _ "
		       + " _|"
		       + "|_ "),
		
		THREE(3, " _ "
			   + " _|"
			   + " _|"),
		
		FOUR(4,  "   "
			   + "|_|"
		       + "  |"),
		
		FIVE(5,  " _ "
			   + "|_ "
			   + " _|"),
		
		SIX(6,   " _ "
			   + "|_ "
			   + "|_|"),
		
		SEVEN(7, " _ "
			   + "  |"
			   + "  |"),
		
		EIGHT(8, " _ "
			   + "|_|"
			   + "|_|"),
		
		NINE(9,  " _ "
			   + "|_|"
			   + " _|"),;

		private Integer digitStandFor;
		private Digit digit;

		private StandardDigit( Integer digitStandFor, String digitString) {
			
			this.digitStandFor = digitStandFor;
			this.digit = Digit.digitOf(digitString, Optional.of(digitStandFor));
			
		}

		public final Integer getDigitStandFor() {
				return digitStandFor;
		}

		public final Digit getDigit() {
				return digit;
		}

	}
	
	static class StaticTestHook{
        Boolean[] digitStringToBoolean(String digitString) {
            return Digit.digitStringToBoolean(digitString);
        }
        
        Optional<Integer> verifyDigit(Boolean[] segments){
        		return Digit.verifyDigit(segments);
        }
        
        Optional<Integer> getDigitFromResult(List<Integer> resultList){
        		return Digit.getDigitFromResult(resultList);
        }
        
        Boolean digitsFullyMatch(Boolean[] digit1, Boolean[] digit2) {
        		return Digit.digitsFullyMatch(digit1, digit2);
        }
        
        Boolean digitsHalfMatch(Boolean[] digit1, Boolean[] digit2) {
        		return Digit.digitsHalfMatch(digit1, digit2);
        }
        
        Boolean isSegmentABar(char segment) {
    			return Digit.isSegmentABar(segment);
    		}
        
        Boolean isSegmentAnUnderscore(char segment) {
        		return Digit.isSegmentAnUnderscore(segment);
        }
        
    }
}
