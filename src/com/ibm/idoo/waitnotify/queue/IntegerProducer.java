package com.ibm.idoo.waitnotify.queue;

import java.util.concurrent.atomic.AtomicInteger;

public class IntegerProducer extends AbstractProducer<Integer> {
	
	AtomicInteger i= new AtomicInteger(0);
	
	public IntegerProducer(DummyQueue<Integer> q){
		super(q);
	}
	
	public Integer update(){
		return i.incrementAndGet();
		
	}
}
