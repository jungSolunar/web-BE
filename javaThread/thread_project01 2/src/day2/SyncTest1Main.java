package day2;

class Counter {
	private int c = 0;

	public synchronized void increment() {
		System.out.println(Thread.currentThread().getName() + "; 전 c=" + c);
		c++;
		System.out.println(Thread.currentThread().getName() + "; 후 c=" + c);
		System.out.println();
	}

	public synchronized void decrement() {
		System.out.println(Thread.currentThread().getName() + "; 전 c=" + c);
		c--;
		System.out.println(Thread.currentThread().getName() + "; 후 c=" + c);
		System.out.println();
	}

	public synchronized int value() {
		System.out.println(Thread.currentThread().getName() + "; value: c=" + c); 
		System.out.println();
		return c;
	}

}

class Thread1 extends Thread {
	private Counter cnt;

	public Thread1(Counter cnt) {
		this.cnt = cnt;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 30 ; i ++){
			cnt.increment();
		}
	
	}

}

class Thread2 extends Thread {
	private Counter cnt;

	public Thread2(Counter cnt) {
		this.cnt = cnt;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 30 ; i ++){
			cnt.decrement();
		}
	}

}

public class SyncTest1Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Counter cnt = new Counter();
		new Thread1(cnt).start();
		new Thread2(cnt).start();
		for (int i = 0 ; i < 30; i++){
			cnt.value();
		}
	}
}
