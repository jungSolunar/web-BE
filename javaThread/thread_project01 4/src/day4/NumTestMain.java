package day4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Num{
	private int num = 0;
	private final int MAX = 70;
	private final int MIN = 30;
	private Lock lock = new ReentrantLock();
	private Condition high = lock.newCondition();
	private Condition low = lock.newCondition();
	
	public void add(){
		lock.lock();
		if(num>=MAX){
			try {
				high.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		num++;
		System.out.println("add: " + num);
		low.signal();
		lock.unlock();
	}
	
	public void sub(){
		lock.lock();
		if(num<=MIN){
			try {
				low.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		num--;
		System.out.println("sub: " + num);
		high.signal();
		lock.unlock();
	}
}

class Thread3 extends Thread{

	private Num num;
	
	public Thread3(Num num){
		this.num = num;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			num.add();
		}
	}
}

class Thread4 extends Thread{

	private Num num;
	
	public Thread4(Num num){
		this.num = num;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			num.sub();
		}
	}
}

public class NumTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Num num = new Num();
		new Thread3(num).start();
		new Thread4(num).start();
	}

}
