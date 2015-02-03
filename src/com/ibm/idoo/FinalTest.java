package com.ibm.idoo;

//not that clearly and directly shows final variables blocks compiler to re-order byte code

// �����ʣ�����

//��final���������������ȷ�����ڶ�һ�������final��֮ǰ��һ�����ȶ��������final��Ķ�������á�
//дfinal���������������ȷ�����ڶ�������Ϊ�����߳̿ɼ�֮ǰ�������final���Ѿ�����ȷ��ʼ�����ˣ�����ͨ�򲻾���������ϡ�

public class FinalTest {
	
	private class Dummy{
		public int num; 
		Dummy(int num){
//			for(int i=0;i<100;i++){
//				// a short delay
//			}
			this.num = num;
		}
		
		public int get(){
			return num;
		}
	}

	public  Dummy i;
	public  Dummy j;

	public static FinalTest obj;

	public FinalTest() {
		i = new Dummy(1); // Java compiler ensures the value of final field to be the one
				// as right after the field get initiated
		j = new Dummy(2); // non final field doesn't have that insurance, so it's possible
				// its non-initiated value can be accessed by other threads
				// before the constructor finished
		System.out.println("constructor finish");
	}

	public static void read() {
		for(int i=0;i<100000;i++){
		// a short delay
		}
		System.out.println("start read " + obj);
		FinalTest newObj = obj;
		int a = newObj.i.get();
		int b = newObj.j.get();
		System.out.println("a=" + a + ", b=" + b);
	}

	public static void read1() {
		FinalTest newObj = obj;
		int a = obj.i.get();
		int b = obj.j.get();
		System.out.println("a=" + a + ", b=" + b);
	}

	public static void create() {
		obj = new FinalTest();
		System.out.println(obj.i.get());
	}

	public static void main(String[] args) {
		Thread t1 = new Thread1();
		Thread t2 = new Thread2();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t1.start();
		t2.start();
	}

	public static class Thread1 extends Thread {
		public void run() {
			FinalTest.create();
		}
	}

	public static class Thread2 extends Thread {
		public void run() {
			FinalTest.read();
		}
	}
}
