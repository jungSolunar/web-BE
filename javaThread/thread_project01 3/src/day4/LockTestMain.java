package day4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Message {
	private String msg1;
	private String msg2;
	private String msg3;
	private String msg4;
	private Lock lock = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	public boolean setMsg(String msg1, String msg2, String msg3, String msg4) {
		if (lock2.tryLock()) {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "�� msg����");
			this.msg1 = msg1;
			this.msg2 = msg2;
			this.msg3 = msg3;
			this.msg4 = msg4;
			System.out.println(Thread.currentThread().getName() + "�� msg���� �Ϸ�");
			lock.unlock();
			return true;
		} else {
			System.out.println("�ٸ� ������ �۾���");
			return false;
		}
	}

	public void printMsg() {
		lock.lock();
		System.out.println(Thread.currentThread().getName() + "�� msg ���");
		System.out.println("msg1:" + msg1);
		System.out.println("msg2:" + msg2);
		System.out.println("msg3:" + msg3);
		System.out.println("msg4:" + msg4);
		System.out.println(Thread.currentThread().getName() + "�� msg ��¿Ϸ�");
		lock.unlock();
		lock2.unlock();
	}
}

class Thread1 extends Thread {
	private Message msg;
	private String str;

	public Thread1(Message msg, String str) {
		this.msg = msg;
		this.str = str;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (msg.setMsg(str, str, str, str))
				msg.printMsg();
			try {
				Thread.sleep((long) (Math.random()*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class LockTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Message msg = new Message();
		new Thread1(msg, "abc").start();
		new Thread1(msg, "������").start();
	}

}
