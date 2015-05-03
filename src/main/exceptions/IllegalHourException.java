package exceptions;

public class IllegalHourException extends Exception {
	  public IllegalHourException() { super(); } 
	  public IllegalHourException(String message) { super(message); }
	  public IllegalHourException(String message, Throwable cause) { super(message, cause); }
	  public IllegalHourException(Throwable cause) { super(cause); }
} 
