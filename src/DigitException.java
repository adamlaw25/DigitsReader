
public class DigitException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum ErrorCode {
		AMBIGIOUS, FAILURE;
	}

	private final ErrorCode errorCode;
	
	DigitException(ErrorCode errorCode){
		this.errorCode = errorCode;
	}
	
	public final ErrorCode getErrorCode() {
		return errorCode;
	}
	
}
