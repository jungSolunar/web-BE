package day2;

class Counter2 {
	private int c = 0;

	public void increment() {
		c++;
	}

	public void decrement() {
		c--;
	}

	public int value() {
		return c;
	}

}

class Thread7 extends Thread {
	private Counter2 cnt;

	public Thread7(Counter2 cnt) {
		this.cnt = cnt;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(cnt){
			for(int i = 0 ; i < 30 ; i++){
				System.out.println(Thread.currentThread().getName() + "; 전 value: cnt=" + cnt.value());
				cnt.increment();
				System.out.println(Thread.currentThread().getName() + "; 후 value: cnt=" + cnt.value());
			}
		}
	
	}

}

class Thread8 extends Thread {
	private Counter2 cnt;

	public Thread8(Counter2 cnt) {
		this.cnt = cnt;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(cnt){
			for(int i = 0 ; i < 30 ; i++){
				System.out.println(Thread.currentThread().getName() + "; 전 value: cnt=" + cnt.value());
				cnt.decrement();
				System.out.println(Thread.currentThread().getName() + "; 후 value: cnt=" + cnt.value());
			}
		}
	}

}

public class SyncTest1Main2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Counter2 cnt = new Counter2();
		new Thread7(cnt).start();
		new Thread8(cnt).start();
		for (int i = 0 ; i < 30; i++){
			cnt.value();
		}
	}
}
