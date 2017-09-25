package day4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exe = Executors.newSingleThreadExecutor();
		//ExecutorService exe = Executors.newFixedThreadPool(2);
		//ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i=1;i<=26;i++){
					System.out.println(Thread.currentThread().getName()+": " + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		exe.execute(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(char i='a';i<='z';i++){
					System.out.println(Thread.currentThread().getName()+": " + i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		exe.shutdown();
	}

}
