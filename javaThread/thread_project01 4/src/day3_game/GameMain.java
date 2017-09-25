package day3_game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class Game {
	private boolean flag = true;
	private volatile String str = "��";
	private Scanner sc = null;

	public Game(Scanner sc) {
		this.sc = sc;
	}

	public synchronized boolean getMsg() {
		String tmpstr = null;
		//myFlag�� flag�� ������ ���
		if(isFlag() == ((Thread6) Thread.currentThread()).isMyFlag()){
			try {
				wait();
			} catch (InterruptedException e) {
				return false;
			}
		}
		System.out.println("���þ�: "+ str);
		//�ܾ� �ϳ��� �Է¹޾� üŷ�ϰ� flag���� �� �ٸ� ������ ����
		tmpstr = sc.nextLine();
		if(!str.equals(String.valueOf(tmpstr.charAt(0)))){
			System.out.println(Thread.currentThread().getName() + " is loose!!!");
			return false;
		}else{
			setFlag(isFlag());
			str = String.valueOf(tmpstr.charAt(tmpstr.length()-1));
			notifyAll();
			return true;//�ܾ� üŷ ����� ��ȯ
		}
	}

	public synchronized boolean isFlag() {
		return flag;
	}

	public synchronized void setFlag(boolean flag) {
		this.flag = flag;
	}

}

class Thread6 extends Thread {
	private Game g;
	private boolean myFlag;

	public Thread6(Game g, boolean myFlag) {
		this.g = g;
		this.myFlag = myFlag;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean flag = true;
		while (flag != isMyFlag()) {
			
				flag = g.getMsg();
			
		}
	}


	public boolean isMyFlag() {
		return myFlag;
	}
}

public class GameMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game g = new Game(new Scanner(System.in));
		Thread6 t1 = new Thread6(g, true);
		Thread6 t2 = new Thread6(g, false);
		t1.start();
		t2.start();
		while (t1.isAlive() && t2.isAlive()) {
			try {
				t1.join(1000);
				t2.join(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (t1.isAlive()) {
			t1.interrupt();
		}
		if (t2.isAlive()) {
			t2.interrupt();
		}
	}

}
