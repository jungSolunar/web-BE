package day5;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Data {
	private int[] data = new int[5];
	private int cnt = 0;
	private int idxf = 0, idxl = 0;
	Lock lock = new ReentrantLock();
	Condition end = lock.newCondition();
	Condition first = lock.newCondition();

	public void push(int x) {
		
		//������
		lock.lock();
		//cnt�� data�� ���̰� ������ ���
		if(cnt == data.length){
			System.out.println("<<<<<<< data is full >>>>>>>>\n I'm sleep.......\n");
			try {
				end.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//����� ���� �����Ͽ�data�� ����
		printData(data);
		data[idxl] = (int) (Math.random()*1000);
		System.out.println(">>> push data[" + idxl + "] = " + data[idxl] + ", cnt = " + cnt);
		printData(data);
		System.out.println();
		//idxl�� 1�����ѵ� data�� ���̿� �������� idxl�� 0����
		idxl++;
		if(idxl == data.length){
			idxl = 0;
		}
		//data�� ����� ������ ������ 1����
		cnt++;
		//������� �Һ� �½�ũ ����
		first.signalAll();
		//������
		lock.unlock();
		
	}

	public void pop() {
		//������
		lock.lock();
		//cnt�� 0�̸� ���
		while(true){
			System.out.println("if �� : " + cnt + ", idfl = " + idxl + ", idfx = " + idxf);
			if(cnt <= 0){
				System.out.println("<<<<<<< data is empty >>>>>>>>\n I'm sleep.......\n");
				try {
					first.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				break;
			}
		}
		//����� ���� �Һ�
		printData(data);
		System.out.println("<<< pop data[" + idxf + "] = " + data[idxf] + ", cnt = " + cnt);
		data[idxf] = -1;
		printData(data);
		System.out.println();
		//idxf�� 1�����ѵ� data�� ���̿� �������� idxf�� 0����
		idxf++;
		if(idxf == data.length){
			idxf = 0;
		}
		//data�� ����� ������ ������ 1����
		cnt--;
		//������� ���� �½�ũ ����
		end.signalAll();
		//������
		lock.unlock();
	}
	
	private void printData(int[] data){
		System.out.print("data[");
		for(int i=0 ; i < data.length; i++){
			System.out.print(data[i]);
			if(i != data.length-1){
				System.out.print(", ");
			}
		}
		System.out.println("]");
		
	}
}

public class ProCon3Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService execService = Executors.newFixedThreadPool(3);
		Scanner sc = new Scanner(System.in);
		Data d = new Data();
		execService.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				while(true) {
					d.push(++i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		});
		execService.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					d.pop();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						return;
					}
				}
			}

		});
		execService.submit(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					sc.nextLine();
					d.pop();
				}
			}

		});
		execService.shutdown();
		
	}

}
