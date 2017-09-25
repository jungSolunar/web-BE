package day3_wait;

public class ProducerMain {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		 Drop drop = new Drop();
	        (new Thread(new Producer(drop))).start();
	        (new Thread(new Consumer(drop))).start();

	}

}
