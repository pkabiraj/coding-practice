package org.psk.practice.ds;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueue {

	public static void main(String[] args) {
		System.out.println("START---------STACK");
		MyStack<Integer> myStack = new MyStack<>();
		System.out.println(myStack.push(1));
		System.out.println(myStack.push(2));
		System.out.println(myStack.push(3));
		System.out.println(myStack.peek());
		System.out.println(myStack.pop());
		System.out.println(myStack.push(4));
		while (!myStack.isEmpty()) {
			System.out.println(myStack.pop());
		}
		System.out.println("END---------STACK");
		System.out.println("START---------QUEUE");
		MyQueue<Integer> myQueue = new MyQueue<>();
		System.out.println(myQueue.offer(1));
		System.out.println(myQueue.offer(2));
		System.out.println(myQueue.offer(3));
		System.out.println(myQueue.peek());
		System.out.println(myQueue.poll());
		System.out.println(myQueue.offer(4));
		while (!myQueue.isEmpty()) {
			System.out.println(myQueue.poll());
		}
		System.out.println("END---------QUEUE");
	}

	public static class MyStack<T> {

		private Queue<T> q1, q2;

		public MyStack() {
			q1 = new LinkedList<T>();
			q2 = new LinkedList<T>();
		}

		public T push(T t) {
			return q1.offer(t) ? t : null;
		}

		public T peek() {
			if (isEmpty()) {
				return null;
			}

			// Move last but all to second queue
			while (q1.size() != 1) {
				q2.offer(q1.poll());
			}

			// Now q1 contains the one inserted last
			return q1.peek();
		}

		public T pop() {
			if (isEmpty()) {
				return null;
			}

			// Move last but all to second queue
			while (q1.size() != 1) {
				q2.offer(q1.poll());
			}

			// Now q1 contains the one inserted last
			T t = q1.poll();

			// Swap the queues
			Queue<T> temp = q1;
			q1 = q2;
			q2 = temp;

			return t;
		}

		public int size() {
			return q1.size() + q2.size();
		}

		public boolean isEmpty() {
			return q1.isEmpty() && q2.isEmpty();
		}
	}

	public static class MyQueue<T> {

		private Stack<T> s1, s2;

		public MyQueue() {
			s1 = new Stack<T>();
			s2 = new Stack<T>();
		}

		public boolean offer(T t) {
			return s1.push(t) != null;
		}

		public T poll() {
			if (!s2.isEmpty()) {
				return s2.pop();
			}

			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}

			return s2.pop();
		}

		public T peek() {
			if (!s2.isEmpty()) {
				return s2.peek();
			}

			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}

			return s2.peek();
		}

		public int size() {
			return s1.size() + s2.size();
		}

		public boolean isEmpty() {
			return s1.isEmpty() && s2.isEmpty();
		}
	}
}
