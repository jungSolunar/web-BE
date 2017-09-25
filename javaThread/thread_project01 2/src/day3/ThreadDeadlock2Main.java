package day3;

class Friend2{
	private String name;
	public Friend2(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public synchronized void bow(Friend2 f){
		
		if(!ThreadDeadlockMain.bow_able){
			try {
				System.out.println(f.getName()+"�� �λ���");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("waiting...");
		}
		ThreadDeadlockMain.bow_able = false;
		System.out.println(name+"��"+f.getName()+"���� �λ�");
		f.bowback(this);
	}
	
	public synchronized void bowback(Friend2 f){
		System.out.println(name+"��"+f.getName()+"���� �λ縦 �޾���");
		ThreadDeadlockMain.bow_able = true;
		notifyAll();
	}
}

class Thread2 extends Thread{

	private Friend2 f1;
	private Friend2 f2;
	
	public Thread2(Friend2 f1, Friend2 f2){
		this.f1 = f1;
		this.f2 = f2;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		f1.bow(f2);
	}
	
}
public class ThreadDeadlock2Main {
	
	public static volatile boolean bow_able = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Friend2 f1 = new Friend2("aaa");
		Friend2 f2 = new Friend2("bbb");
		new Thread2(f1, f2).start();
		new Thread2(f2, f1).start();
	}
}
