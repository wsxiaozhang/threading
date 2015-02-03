package com.ibm.idoo.lock;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest implements Runnable {
	int counter = 0;
	ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		LockTest test = new LockTest();
		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(test);
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			t.start();
		}
	}

	public void run() {
		// set(0);
		// System.out.println(Thread.currentThread().getId() +
		// " before ->"+get());
		inc();
		synchronized (this) {
			System.out.println(Thread.currentThread().getId() + " after  ->"
					+ get());
		}
//		
//		lock.lock();
//		try{
//			System.out.println(Thread.currentThread().getId() + " after  ->"
//					+ get());
//		} finally {
//			lock.unlock();
//		}

	}

	public void set(int update) {
		lock.lock();
		try {
			counter = update;
		} finally {
			lock.unlock();
		}
	}

	public void inc() {
		lock.lock();
		try {
			counter++;
		} finally {
			lock.unlock();
		}
	}

	public int get() {
		lock.lock();
		try {
			return counter;
		} finally {
			lock.unlock();
		}
	}
}
