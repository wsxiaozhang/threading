package com.ibm.idoo.waitnotify.queue;

public abstract class AbstractProducer<T> implements Runnable{
	private DummyQueue<T> q = null;

	public AbstractProducer(DummyQueue<T> q){
		this.q = q;
	}
	
	public abstract T update();
	
	public void run(){
		while(true){
			try {
				q.put(update());
			} catch (FailedToInqueueNumberException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
