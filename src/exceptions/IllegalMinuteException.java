package exceptions;

public class IllegalMinuteException extends Exception {
	  public IllegalMinuteException() { super(); } 
	  public IllegalMinuteException(String message) { super(message); }
	  public IllegalMinuteException(String message, Throwable cause) { super(message, cause); }
	  public IllegalMinuteException(Throwable cause) { super(cause); }
} 
