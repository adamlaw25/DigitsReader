import java.util.ArrayList;
import java.util.List;

// A barricade class separates user input which could be bad data with Digit
public class DigitReader {

    static List<Digit> getDigitList(final List<String> stringInput) throws DigitException {

    		// verify the input size
        verifyInputSize(stringInput);
        
        List<String> inputDigitStringList = getInputDigitStringList(stringInput);

        // verify the input segments
        verifyInputSegments(inputDigitStringList);
        
		List<Digit> digitList = new ArrayList<Digit>();
        
		for(String digitString: inputDigitStringList) {
			digitList.add(Digit.digitOf(digitString));
		}
        return digitList;
    }
    
    private static Boolean verifyInputSize(final List<String> stringInput) throws DigitException {
		if(stringInput.size() != 3) 
			throw new DigitException(DigitException.ErrorCode.FAILURE);
		
		// check each line if it has exactly 27 characters
    for(String inputLine: stringInput)
     	if( inputLine.length() != 27) 
        		throw new DigitException(DigitException.ErrorCode.FAILURE);
    
    return true;
     	
    }
    
    private static Boolean verifyInputSegments(List<String> inputDigitStringList) throws DigitException {
    		for(String digitString: inputDigitStringList) {
    			
    			assert digitString.length() == 9 : "Digit is not composited by 9 segments";
    			
    			//check left top and right top
    			verifySegmentASpace(digitString.charAt(0));
    			verifySegmentASpace(digitString.charAt(2));
    			
    			//check the middle top
    			verifySegmentAnUnderscoreOrASpace(digitString.charAt(1));
    			
    			for(int i = 3; i <= 6  ; i = i + 3) {
    				verifySegmentABarOrASapce(digitString.charAt(i));
    				verifySegmentAnUnderscoreOrASpace(digitString.charAt(i + 1));
    				verifySegmentABarOrASapce(digitString.charAt(i + 2));
        		}
    		}
    		return true;
    }
    
    private static Boolean verifySegmentASpace(char segment) throws DigitException {
    		if(!Character.isWhitespace(segment)) 
    			throw new DigitException(DigitException.ErrorCode.FAILURE);
    		return true;
    		
    }
    
	private static Boolean verifySegmentABarOrASapce(char segment) throws DigitException {
		if(segment != '|' && !Character.isWhitespace(segment)) 
			throw new DigitException(DigitException.ErrorCode.FAILURE);
		return true;
		
	}
	
	private static Boolean verifySegmentAnUnderscoreOrASpace(char segment) throws DigitException {
		if(segment != '_' && !Character.isWhitespace(segment))
			throw new DigitException(DigitException.ErrorCode.FAILURE);
		return true;
		
	}
    
    private static List<String> getInputDigitStringList(final List<String> stringInput){
    		List<String> inputDigitStringList = new ArrayList<String>();
    		// create 9 digits
        for(int i = 0; i < 9; i++) {
        		StringBuilder builder = new StringBuilder();
        		for(int j = 0; j < 3; j++) {
        			builder.append(stringInput.get(j).substring(3 * i, 3 * i + 3));
        		}
        		inputDigitStringList.add(builder.toString());
        		
        }
        return inputDigitStringList;
    }
   
    
    static class StaticTestHook{
    		Boolean verifyInputSegments(List<String> inputDigitStringList) throws DigitException{
    			return DigitReader.verifyInputSegments(inputDigitStringList);
    		}
    		
    		Boolean verifySegmentASpace(char segment) throws DigitException {
    			return DigitReader.verifySegmentASpace(segment);
    		}
    		
    		Boolean verifySegmentABarOrASapce(char segment) throws DigitException {
    			return DigitReader.verifySegmentABarOrASapce(segment);
    		}
    		
    		Boolean verifySegmentAnUnderscoreOrASpace(char segment) throws DigitException {
    			return DigitReader.verifySegmentAnUnderscoreOrASpace(segment);
    		}
    		
    		List<String> getInputDigitStringList(final List<String> stringInput){
    			return DigitReader.getInputDigitStringList(stringInput);
    		}
    		
    		Boolean verifyInputSize(final List<String> stringInput) throws DigitException {
    			return DigitReader.verifyInputSize(stringInput);
    		}
    		
    }
}
