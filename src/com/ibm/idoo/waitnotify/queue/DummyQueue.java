package com.ibm.idoo.waitnotify.queue;

public interface DummyQueue<T> {
	public void put(T num) throws FailedToInqueueNumberException;
	public T get();

}
