package com.ibm.idoo.waitnotify.queue;

import java.util.Date;

public class StringProducer extends AbstractProducer<String> {

	public StringProducer(DummyQueue<String> q){
		super(q);
		
	}
	
	public String update(){
		return new Date(System.currentTimeMillis()).toString();
	}
}
