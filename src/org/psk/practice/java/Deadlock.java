package org.psk.practice.java;

public class Deadlock {

	public static void main(String[] args) throws InterruptedException {
		final Object outerLock = new Object();
		OuterTask outerTask = new OuterTask(outerLock);
		Thread outer = new Thread(outerTask, "outer");
		outer.start();
		outer.join();
	}

	static class OuterTask implements Runnable {
		private final Object lock;

		public OuterTask(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			System.out.println("Outer launched");
			System.out.println("Obtaining lock");
			synchronized (lock) {
				Thread inner = new Thread(new InnerTask(lock), "inner");
				inner.start();
				try {
					inner.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class InnerTask implements Runnable {
		private final Object lock;

		public InnerTask(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			System.out.println("Inner launched");
			System.out.println("Obtaining lock");
			synchronized (lock) {
				System.out.println("Obtained");
			}
		}
	}

	// public static void main(String[] args) throws InterruptedException {
	// Lock lock = new ReentrantLock();
	// Res2 res = new Res2(lock);
	// Res1 res1 = new Res1(lock, res);
	//
	// res1.start();
	// res1.join();
	// }
	//
	// static class Res1 extends Thread {
	//
	// Lock lock;
	// private final Res2 res;
	//
	// public Res1(Lock lock, Res2 res) {
	// this.lock = lock;
	// this.res = res;
	// }
	//
	// @Override
	// public void run() {
	// System.out.println("In Res1... obtaining lock...");
	// synchronized (lock) {
	// System.out.println("In Res1... Beofre start");
	// res.start();
	// try {
	// System.out.println("In Res1... Beofre join");
	// res.join();
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// System.out.println("In Res1... After join.. done...");
	// }
	// }
	// }
	//
	// static class Res2 extends Thread {
	//
	// Lock lock;
	//
	// public Res2(Lock lock) {
	// this.lock = lock;
	// }
	//
	// @Override
	// public void run() {
	// System.out.println("In Res2... obtaining lock...");
	// synchronized (lock) {
	// System.out.println("In Res2... lock obtained...");
	// }
	// }
	// }
}
