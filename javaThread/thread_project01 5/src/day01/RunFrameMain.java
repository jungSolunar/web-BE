package day01;

import java.awt.Frame;

class RunFrame extends Frame implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		System.out.println("스레드 시작!");
		while(i < 20){
			System.out.println(i);
			this.setTitle("스레드 동작중" + i++);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class RunFrameMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RunFrame r = new RunFrame();
		r.setSize(300, 100);
		r.setVisible(true);
		Thread t = new Thread(r);
		t.start();
	}

}
