package com.ibm.idoo.waitnotify;

public class RaceToPrintWithConditionLock implements Runnable{

	private volatile boolean isOnePrinted = false;
	private byte[] lock = new byte[0];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RaceToPrintWithConditionLock race = new RaceToPrintWithConditionLock();
		for(int i=0;i<3;i++){
			Thread t = new Thread(race);
			try{
				t.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			t.start();
		}
	}
	
	public void run(){
		while(true){
			synchronized(lock){
				if (!isOnePrinted){
					System.out.println("1");
					isOnePrinted = true;
				} else {
					System.out.println("2");
					isOnePrinted = false;
				}
			}
		}
	}
	

}
