package com.ibm.idoo;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileTest {

	private volatile static boolean started = false;
	private static AtomicInteger hit = new AtomicInteger(0);

	public static void main(String[] args) {
		VolatileTest.startup();
		for (int i = 0; i < 2; i++) {
			Thread worker = new Thread(new Runnable() {
				@Override
				public void run() {
					VolatileTest.doWork();
				}
			});
			System.out.println(worker.getId() + " is created");
			worker.setDaemon(false);
			worker.start();

			// try {
			// worker.join();
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// Thread.yield();
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out
				.println("------------------------------------------------------------");
		Thread terminator = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (hit.get() >= 10000) {
//						System.out.println("let's stop");
//						started = false;
						VolatileTest.shutdown();
						break;
					}
				}
			}

		});
		terminator.setDaemon(true);

		terminator.start();

		// try {
		// terminator.join();
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// while(Thread.activeCount()>1)
		// Thread.yield();
		System.out
				.println("===============================================================");
	}

	public static void shutdown() {
		started = false;
	}

	public static void startup() {
		started = true;
	}

	public static void doWork() {
		while (started) {

			int count = 0;
			// for (int i = 0; i < 1000; i++) {
			// count++;
			// }
			synchronized (hit) {
				System.out.println(Thread.currentThread().getId()
						+ " is working, hit = " + hit.incrementAndGet());
			}

		}
	}
}
