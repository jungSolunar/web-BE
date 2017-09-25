package day3;

class Friend{
	private String name;
	public Friend(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public synchronized boolean bow(Friend f){
		if(!ThreadDeadlockMain.bow_able){
			System.out.println(f.getName()+"이 bow중 입니다.");
			return true;
		}else{
			ThreadDeadlockMain.bow_able = false;
			System.out.println(name+"이"+f.getName()+"에게 인사");
			f.bowback(this);
			ThreadDeadlockMain.bow_able = true;
			return false;
		}
		
	}
	
	public synchronized void bowback(Friend f){
		System.out.println(name+"이"+f.getName()+"에게 인사를 받아줌");
	}
}

class Thread1 extends Thread{

	private Friend f1;
	private Friend f2;
	
	public Thread1(Friend f1, Friend f2){
		this.f1 = f1;
		this.f2 = f2;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(f1.bow(f2)){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			f1.bow(f2);
		}
		
	}
	
}
public class ThreadDeadlockMain {
	
	public static volatile boolean bow_able = true;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Friend f1 = new Friend("aaa");
		Friend f2 = new Friend("bbb");
		new Thread1(f1, f2).start();
		new Thread1(f2, f1).start();
	}

}
