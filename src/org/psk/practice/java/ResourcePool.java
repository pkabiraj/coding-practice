package org.psk.practice.java;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ResourcePool<T> {

	private static final int MAX_RESOURCES = 10;

	private final Semaphore sem = new Semaphore(MAX_RESOURCES, true);
	private final Queue<T> resources = new ConcurrentLinkedQueue<T>();

	public T getResource(long maxWaitMillis) throws InterruptedException, ResourceCreationException {
		// First, get permission to take or create a resource
		sem.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS);

		// Then, actually take one if available...
		T res = resources.poll();
		if (res != null) {
			return res;
		}

		// ...or create one if none available
		try {
			return createResource();
		} catch (Exception e) {
			// Don't hog the permit if we failed to create a resource!
			sem.release();
			throw new ResourceCreationException(e);
		}
	}

	private T createResource() {
		// TODO Auto-generated method stub
		return null;
	}

	public void returnResource(T res) {
		resources.add(res);
		sem.release();
	}

	public static class ResourceCreationException extends Exception {

		private static final long serialVersionUID = -7053053156186520216L;

		public ResourceCreationException() {
			super();
		}

		public ResourceCreationException(Exception e) {
			super(e);
		}
	}
}
