import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class DigitDriver {
	public static void main(String[] args) throws DigitException {
		List<String> userInput = getUserInput();
		/*List<String> userInput = new ArrayList<String>();
		userInput.add("    _  _     _  _  _  _  _ ");
		userInput.add("  | _| _||_||_ |_   ||_||_|");
		userInput.add("  ||_  _|  | _||_|  ||_| _|");*/
		try {
		printDigits(userInput);
		}
		catch(DigitException e) {
			System.out.println(e.getErrorCode());
		}
	}
	
	private static List<String> getUserInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please type in 3 lines of 27 characters:");
		List<String> userInput = new ArrayList<String>();
		for(int i = 0; i < 3; i++) {
			userInput.add(scanner.nextLine());
		}
		scanner.close();
		
		return userInput;
	}
	
	private static String printDigits(List<String> userInput) throws DigitException {
		List<Digit> digitList = DigitReader.getDigitList(userInput);
		
		StringBuilder sb = new StringBuilder();
		int nullNum = 0;
		for(Digit digit: digitList) {
			if(digit.getDigitStandFor().isPresent())
				sb.append(digit.getDigitStandFor().get());
			else
				nullNum++;
		}
		// This is only case where ambiguous could be thrown
		if(nullNum >= 1) {
			DigitException.ErrorCode errorCode = nullNum == 1 ? DigitException.ErrorCode.AMBIGIOUS : DigitException.ErrorCode.FAILURE;
			throw new DigitException(errorCode);
		}
		
		System.out.println(sb.toString());
		return sb.toString();
	}

    static class StaticTestHook{
    		List<String> getUserInput() {
    			return DigitDriver.getUserInput();
    		}
    		
    		String printDigits(List<String> userInput) throws DigitException {
    			return DigitDriver.printDigits(userInput);
    		}
    		
    }
}
