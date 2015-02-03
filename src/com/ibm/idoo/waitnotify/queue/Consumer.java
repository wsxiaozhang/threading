package com.ibm.idoo.waitnotify.queue;

public class Consumer implements Runnable{

	private DummyQueue<?> q;
	
	public Consumer(DummyQueue<?> q){
		this.q = q;
	}
		
	public void run(){
		if(q==null)
			return;
		while(true){
			System.out.println("Consumer get: "+ q.get());
		}
	}
}
