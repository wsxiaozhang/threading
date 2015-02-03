package com.ibm.idoo.waitnotify;

public class RaceToPrintWithWaitNotify implements Runnable{

	private static final byte[] lock = new byte[0];
	private int number;
	
	public RaceToPrintWithWaitNotify(int num){
		this.number = num;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new RaceToPrintWithWaitNotify(1));
		Thread t2 = new Thread(new RaceToPrintWithWaitNotify(2));
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t1.start();
		t2.start();
	}
	
	public void run(){
		while (true){
			synchronized (RaceToPrintWithWaitNotify.lock){
				System.out.println(number);
				lock.notify();
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
