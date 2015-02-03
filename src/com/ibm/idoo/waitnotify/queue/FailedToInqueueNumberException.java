package com.ibm.idoo.waitnotify.queue;

public class FailedToInqueueNumberException extends QueueException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8233809091121278225L;

	public String getMessage() {
		// TODO Auto-generated method stub
		return "Failed to insert number in to queue";
	}

	public String getCauseAsString() {
		// TODO Auto-generated method stub
		return "queue is full";
	}
}
