package org.ucm.tp1.Exceptions.ExecuteExceptions;

import org.ucm.tp1.Exceptions.CommandExecuteException;

public class NotEnoughCoinsException extends CommandExecuteException {

	private static final long serialVersionUID = 1L;

	public NotEnoughCoinsException() {
		super();
	}
	
	public NotEnoughCoinsException(String message) {
		super(message);
	}
	
	public NotEnoughCoinsException(Throwable cause) {
		super(cause);
	}
	
	public NotEnoughCoinsException(String message, Throwable cause) {
		super(message, cause);
	}
}