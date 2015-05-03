package exceptions;

public class IllegalTermException extends Exception {
	public IllegalTermException() { super(); } 
	public IllegalTermException(String message) { super(message); }
	public IllegalTermException(String message, Throwable cause) { super(message, cause); }
	public IllegalTermException(Throwable cause) { super(cause); }
}
