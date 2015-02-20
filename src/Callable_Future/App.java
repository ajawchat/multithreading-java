package Callable_Future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<Integer> future = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(4000);
				
				System.out.println("Starting");
				
				if(duration > 2000){
					throw new IOException("Thread is taking too long...");
				}
				
				// simulate some work. Make the thread sleep for 0-4 secs max
				try {
					Thread.sleep(duration);
				} catch (InterruptedException e) {
					System.out.println(e);
				}
				
				System.out.println("Finished");
				return duration;
			}
		});
		
		
		executor.shutdown();
		
		
		try {
			System.out.println("Duration is "+future.get());
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e);
		}
		
		

	}

}
