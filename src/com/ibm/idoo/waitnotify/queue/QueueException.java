package com.ibm.idoo.waitnotify.queue;

public abstract class QueueException extends Throwable{

	abstract public String getMessage();
	abstract public String getCauseAsString();	
	public Throwable getCause(){return super.getCause();}
	
	protected void outputException(){
		System.err.println("Dummy Queue Excpetion : [" + getMessage() + "], because [" +getCauseAsString() + "]");
	}
}
