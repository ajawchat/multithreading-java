package Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class App {

	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executors = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 200; i++){
			executors.submit(new Runnable(){
				public void run(){
					try {
						Connection.getInstance().connect();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} // end of for
		
		executors.shutdown();
		
		// Wait for 1 day before force shutdown
		executors.awaitTermination(1,TimeUnit.DAYS);
		
	}

}
