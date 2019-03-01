package customExceptions;

@SuppressWarnings("serial")
public class NotOddException extends Exception {

	public final static String NOT_ODD = "ODD. ";
	public final static String NEGATIVE = "POSITIVE. ";
	public final static String ZERO = "DIFFERENT FROM ZERO. ";
	private String customMessage;
	private String type;
	
	public NotOddException(int number) {
		super("The order number must be ");
		typeOf(number);
		customMessage = type + "\nEntered number: "+number;
	}
	
	private void typeOf(int number) {
		if(number % 2 == 0 && number != 0){
			type = NOT_ODD;
		}else if(number < 0){
			type = NEGATIVE;
		}else if(number == 0) {
			type = ZERO;
		}
	}
	
	public String getMessage() {
		String msg;
		msg = super.getMessage() + customMessage;
		return msg;
	}

}
