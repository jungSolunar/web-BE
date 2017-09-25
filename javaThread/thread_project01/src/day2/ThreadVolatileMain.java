package day2;

class Thread3 extends Thread {
	@Override
	public void run() {
		int local_value = ThreadVolatileMain.MY_INT;
		while (local_value < 5) {
			if (local_value != ThreadVolatileMain.MY_INT) {
				System.out.println(Thread.currentThread().getName() + ":" 
			+ ThreadVolatileMain.MY_INT);
				local_value = ThreadVolatileMain.MY_INT;
			}
		}
	}
}

class Thread4 extends Thread {
	@Override
	public void run() {
		int local_value = ThreadVolatileMain.MY_INT;
		while (ThreadVolatileMain.MY_INT < 5) {
			ThreadVolatileMain.MY_INT = ++local_value;
			System.out.println(Thread.currentThread().getName() + ":" 
			+ ThreadVolatileMain.MY_INT);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class ThreadVolatileMain {

	public static volatile int MY_INT = 0;		// for atomic => to value

	public static void main(String[] args) {
		new Thread3().start();
		new Thread4().start();
	}

}