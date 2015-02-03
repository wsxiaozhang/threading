package com.ibm.idoo;

public class SynchronizationTest implements Runnable {
	
	int count = 0;
	boolean flag = false;
	
//	static int c = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SynchronizationTest test = new SynchronizationTest();
		for (int i = 0; i < 100; i++) {
			Thread t = new Thread(test);
			t.start();

		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("--------------------------");
		System.out.println("result = " + test.count);
//		System.out.println("c = " + c);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// System.out.println("--------------------------");
		// System.out.println("before = "+this.get());
//		this.increment();
//		 System.out.println("after  = "+this.get());
//		stopHello();
		increment();
		flag = true;
		increment();
		while(!flag){
			System.out.println("waiting");
		}

		sayHello();
	
	}
	

	public int get() {
		return count;
	}

	public void set(int count) {
		this.count = count;
	}
	
	public void stopHello(){
		increment();
		flag = true;
	}
	
	public void sayHello(){
		System.out.println(Thread.currentThread().getId()+":"+count);
		flag = false;
	}

	public  void increment() {
		for (int i = 0; i < 1000; i++) {
			count++;
//			c++;
		}
		// int temp = count;
		// temp = temp + 1;
		// count = temp;
	}

}
