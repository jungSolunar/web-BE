package day2;

class famer extends Thread{
	private String name;
	
	public famer(String name){
		this.name = name;
	}

	@Override
	public void run(){
		while (GameTest.MY_HP > 0) {
			GameTest.MY_HP += 100;
			System.out.println("["+ name+ "]" + " " + Thread.currentThread().getName() + "; myHP gain:" 
			+ GameTest.MY_HP);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() +" " + name +"is close!");
	}
}

class enemy extends Thread{
	@Override
	public void run(){
		while (GameTest.MY_HP > 0) {
			GameTest.MY_HP -= 500;
			System.out.println(Thread.currentThread().getName() + "; myHP loss:" 
			+ GameTest.MY_HP);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + "; enemy Win!!");
	}
}

public class GameTest {
	
	public static volatile int MY_HP = 1000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new famer("fam1").start();
		new famer("fam2").start();
		new famer("fam3").start();
		new enemy().start();
		
		while(true){
			if(MY_HP < 0)
				break;
			
		}
	}

}
