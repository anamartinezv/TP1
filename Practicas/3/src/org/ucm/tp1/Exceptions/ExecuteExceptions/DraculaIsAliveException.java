package org.ucm.tp1.Exceptions.ExecuteExceptions;

import org.ucm.tp1.Exceptions.CommandExecuteException;

public class DraculaIsAliveException extends CommandExecuteException {

	private static final long serialVersionUID = 1L;

	public DraculaIsAliveException() {
		super();
	}
	
	public DraculaIsAliveException(String message) {
		super(message);
	}
	
	public DraculaIsAliveException(Throwable cause) {
		super(cause);
	}
	
	public DraculaIsAliveException(String message, Throwable cause) {
		super(message, cause);
	}
}