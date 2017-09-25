package day4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallalbleMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exe = Executors.newSingleThreadExecutor();
		Future<String> future = (Future) exe.submit(new Callable<String>(){

			@Override
			public String call() throws Exception {
				// TODO Auto-generated method stub
				System.out.println("callable½ÇÇà");
				return "callable msg";
			}
		});
			String str;
			try {
				str = future.get();
				System.out.println(str);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			exe.shutdown();
	}

}
