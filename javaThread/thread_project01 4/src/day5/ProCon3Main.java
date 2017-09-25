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
		
		//락설정
		lock.lock();
		//cnt와 data의 길이가 같으면 대기
		if(cnt == data.length){
			System.out.println("<<<<<<< data is full >>>>>>>>\n I'm sleep.......\n");
			try {
				end.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//깨어나면 숫자 생산하여data에 저장
		printData(data);
		data[idxl] = (int) (Math.random()*1000);
		System.out.println(">>> push data[" + idxl + "] = " + data[idxl] + ", cnt = " + cnt);
		printData(data);
		System.out.println();
		//idxl값 1증가한뒤 data의 길이와 같아지면 idxl을 0으로
		idxl++;
		if(idxl == data.length){
			idxl = 0;
		}
		//data에 저장된 숫자의 개수를 1증가
		cnt++;
		//대기중인 소비 태스크 깨움
		first.signalAll();
		//락해제
		lock.unlock();
		
	}

	public void pop() {
		//락설정
		lock.lock();
		//cnt가 0이면 대기
		while(true){
			System.out.println("if 전 : " + cnt + ", idfl = " + idxl + ", idfx = " + idxf);
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
		//깨어나면 숫자 소비
		printData(data);
		System.out.println("<<< pop data[" + idxf + "] = " + data[idxf] + ", cnt = " + cnt);
		data[idxf] = -1;
		printData(data);
		System.out.println();
		//idxf값 1증가한뒤 data의 길이와 같아지면 idxf을 0으로
		idxf++;
		if(idxf == data.length){
			idxf = 0;
		}
		//data에 저장된 숫자의 개수를 1감소
		cnt--;
		//대기중인 생산 태스크 깨움
		end.signalAll();
		//락해제
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
