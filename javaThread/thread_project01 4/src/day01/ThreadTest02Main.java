package day01;

class ThreadTest02 extends Thread{
	@Override
	public void run(){
		for(int i=0; i<40; i++){
			System.out.println(Thread.currentThread().getName() + "; i=" + i + "; priority=" + Thread.currentThread().getPriority());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+ " stop" + "; priority=" + Thread.currentThread().getPriority());
	}
}

public class ThreadTest02Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=10; i>=1; i--){
			ThreadTest02 t = new ThreadTest02();
			t.setPriority(i);
			t.start();
		}
	}

}
