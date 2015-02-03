package com.ibm.idoo.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
	private static final AtomicInteger ai = new AtomicInteger(0);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<1000;i++){
			Thread t = new Thread(new Runnable(){
				public void run (){
					int result = AtomicIntegerTest.ai.incrementAndGet();
					System.out.println(result);
				}
			});
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.start();
		}
		
		// another thread doing opposite operation, may cause ABA problem
		// e.g. in this case, the largest number of ai could be 999 but not 1000, 
		// meanwhile, there is one specific number appearing twice
		Thread t2 = new Thread ( new Runnable() {
			public void run(){
				AtomicIntegerTest.ai.decrementAndGet();
			}
		});
		try {
			t2.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		t2.start();
	}

}
