package com.ibm.idoo.waitnotify;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class Athlete implements Runnable {
	private final int id;
	private Game game;

	public Athlete(int id, Game game) {
		this.id = id;
		this.game = game;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Athlete))
			return false;
		Athlete athlete = (Athlete) o;
		return id == athlete.id;
	}

	public String toString() {
		return "Athlete<" + id + ">";
	}

	public int hashCode() {
		return new Integer(id).hashCode();
	}

	public void run() {
		try {
			game.prepare(this);
		} catch (InterruptedException e) {
			System.out.println(this + " quit the game");
		}
	}
}

public class Game implements Runnable {
	private Set<Athlete> players = new HashSet<Athlete>();
	private volatile boolean start = false;
	private byte[] lock = new byte[0];
	private AtomicInteger num = new AtomicInteger(0);
	private volatile int count = 0;
	private static final int tc = 5;
	
	public void addPlayer(Athlete one) {
		players.add(one);
	}

	public void removePlayer(Athlete one) {
		players.remove(one);
	}

	public Collection<Athlete> getPlayers() {
		return Collections.unmodifiableSet(players);
	}

	public void prepare(Athlete athlete) throws InterruptedException {

		num.incrementAndGet();
		System.out.println(athlete + " ready!");
		synchronized (lock) {
			 System.out.println("this prepare = " + athlete);
			// count++;
			while (!start)
				lock.wait();
			if (start)
				System.out.println(athlete + " go!");
		}
	}

//	public void go() {
//		notifyAll();
//	}

	public void ready() {
		Iterator<Athlete> iter = getPlayers().iterator();
		while (iter.hasNext())
			new Thread(iter.next()).start();
	}

	public void run() {
//		start = false;
		System.out.println("Ready......");
		System.out.println("Ready......");
		System.out.println("Ready......");
		ready();
		while (num.intValue() < tc) {
			System.out.println("wait for " + num.intValue());
		}
		synchronized (lock) {
			start = true;
			System.out.println("Go!");
//			go();
			lock.notifyAll();
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		for (int i = 0; i < tc; i++)
			game.addPlayer(new Athlete(i, game));
		new Thread(game).start();
	}
}
