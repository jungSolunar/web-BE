package day2;

public class MessageLoopMain {
	static void threadMessage(String msg){
		String threadName = Thread.currentThread().getName();
		System.out.format("%s: %s%n", threadName, msg);
	}

	private static class MessageLoop implements Runnable{
		@Override
		public void run(){
			String importantInfo[] = {"AAA", "BBB", "CCC", "DDD", "EEE"};
			
			try{
				for (int i=0 ; i < importantInfo.length; i++){
					Thread.sleep(4000);
					threadMessage(importantInfo[i]);
				}
			} catch(InterruptedException e){
				threadMessage("I wasn't done!");
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long patience = 1000 * 60 * 60;
		if(args.length > 0){
			try{
			patience = Long.parseLong(args[0]) * 1000;
			} catch(NumberFormatException e){
				System.err.println("Arg must be an int");
				System.exit(1);
			}
		}
		threadMessage("@@Start@@");
		long startTime = System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop());
		t.start();
		threadMessage("waiting for thread to finish");
		while(t.isAlive()){
			threadMessage("Still waiting....");
			try {
				t.join(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(((System.currentTimeMillis() - startTime) > patience) && t.isAlive()){
				threadMessage("tired of waiting!");
				t.interrupt();
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
