package day2;

public class ThreadYield implements Runnable{
	Thread t;
	ThreadYield(String str){
		t = new Thread(this, str);
		t.start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ThreadYield("Th1");
		new ThreadYield("Th2");
		new ThreadYield("Th3");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i=0; i<5 ; i++){
			if((i % 5) == 0){
				System.out.println(Thread.currentThread().getName() + "yielding control...");
				Thread.yield();
			}
		}
		System.out.println(Thread.currentThread().getName() + " has finished executing.");
	}
}
