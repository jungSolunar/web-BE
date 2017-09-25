package day4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Friend{
	private String name;
	private Lock lock = new ReentrantLock();

	public Friend(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean check(Friend f){
		boolean flag1 = false;
		boolean flag2 = false;
		
		try{
			flag1 = lock.tryLock();
			flag2 = f.lock.tryLock();
		}finally{
			if(!(flag1 && flag2)){
				if(flag1){
					lock.unlock();
				}
				if(flag2){
					f.lock.unlock();
				}
			}
		}
		return flag1&&flag2;
	}

	public void bow(Friend f){
		if(check(f)){
			System.out.println(name+"이 "+f.getName()+"에게 인사");
			f.bowback(this);
			lock.unlock();
			f.lock.unlock();
		}else{
			System.out.println(f.getName() + "이 인사중....");
		}

	}
	
	public void bowback(Friend f){
		System.out.println(name+"이 "+f.getName()+"에게 인사를 받아줌");
	}
}
class Thread2 extends Thread{
	private Friend f1;
	private Friend f2;
	public Thread2(Friend f1, Friend f2){
		this.f1 = f1;
		this.f2 = f2;
	}	
	@Override
	public void run() {
		while (true) {
			f1.bow(f2);
			try {
				Thread.sleep((long) (Math.random()*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
}
public class DeadlockMain {
	public static void main(String[] args) {
		Friend f1 = new Friend("aaa");
		Friend f2 = new Friend("bbb");
		new Thread2(f1, f2).start();
		new Thread2(f2, f1).start();
	}

}
