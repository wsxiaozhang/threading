package com.ibm.idoo;

public class VolatileTest2 extends Thread{
	private  volatile boolean notdone = true;
	private  int value = 0;

	@Override
	public void run() {
//		try {
//			Thread.sleep(300);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("notdone="+notdone);
		while (notdone) {//A
//			System.out.println("start !");
			System.out.println("!");
			//Thread.yield();
		}
		System.out.println(value); //D
	}

	public void done() {
		System.out.println("start done() = " + notdone);
		notdone = false;
//		try {
//			Thread.sleep(30);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		System.out.println("end done() = " + notdone);
	}

	public void setValue(int value) {
		System.out.println("start setValeu()");
		this.value = value;
		System.out.println("end setValeu()");
	}

	public static void main(String[] args) {
		VolatileTest2 r = new VolatileTest2();
		r.start();
		r.setValue(1); //B
		r.done(); //C
	}
}
