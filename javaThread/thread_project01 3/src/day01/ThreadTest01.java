package day01;

class TreadTest01 implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<26; i++){
			System.out.println(Thread.currentThread().getName() + "; i=" + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
}

class TreadTest02 extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(char i='A'; i<'Z'; i++){
			System.out.println(Thread.currentThread().getName() + "; i=" + (char)i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
public class ThreadTest01 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(new TreadTest01()).start();
		new TreadTest02().start();
		for(char i='a'; i<'z'; i++){
			System.out.println(Thread.currentThread().getName() + "; i=" + (char)i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
