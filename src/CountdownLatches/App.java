package CountdownLatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Processor implements Runnable{
	
	private CountDownLatch latch;
	
	public Processor(CountDownLatch latch){
		this.latch = latch;
	}
	
	public void run(){
		System.out.println("Started");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("completed");
		latch.countDown();
		
	}
}


public class App {
	public static void main(String[] args) {
		
		// This latch value has to be decremented to zero by different threads. Once it is done, we can 
		// make it execute a different logic. It can be any no of threads which are working on the code.
		// But each call to countDown() decrements the value by 1, and when we hit zero, immediately 
		// the control is passed back to the 
		CountDownLatch countDownLatch = new CountDownLatch(4);
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		for(int i = 0; i < 3; i++){
			executor.submit(new Processor(countDownLatch));
		}
		
		System.out.println("Spawning new thread");
		Thread newThread = new Thread(new Processor(countDownLatch));
		newThread.start();
		
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Process completed...Latch has been counted down");
		
	}

}
