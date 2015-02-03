package com.ibm.idoo.waitnotify.queue;

public class SProSConQueue<T> implements DummyQueue<T>{
	private T element; //a simple queue with just only 1 element space
	
	private boolean isFull = false;
	private byte[] lock = new byte[0];
	
	public void put (T n) throws FailedToInqueueNumberException{
		synchronized(lock){
			if(isFull){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					throw new FailedToInqueueNumberException();
				}
				
			}
			
			element = n;
			isFull = true;
			lock.notify();
			System.out.println("put() to notify");
		}
	}
	
	public T get (){
		T result;
		synchronized (lock){
			if(!isFull){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			result = element;
			System.out.println("inqueue     : " + result);
			isFull = false;
			lock.notify();
			System.out.println("get() to notify");
		}
		return result;
	}
	
	public static void main(String[] args){
		DummyQueue<Integer> q_int = new SProSConQueue<Integer>();
		DummyQueue<String> q_str = new SProSConQueue<String>();
		Thread t_pro_int = new Thread(new IntegerProducer(q_int));
		Thread t_pro_str = new Thread(new StringProducer(q_str));
		Thread t_con_int = new Thread(new Consumer(q_int));
		Thread t_con_str = new Thread(new Consumer(q_str));
		try {
			t_pro_int.join();
			t_con_int.join();
//			t_pro_str.join();
//			t_con_str.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t_pro_int.start();
		t_con_int.start();
//		t_pro_str.start();
//		t_con_str.start();
		
	}

}
