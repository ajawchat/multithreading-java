package ProducerConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

public class App {

	private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	// For producer thread
	private static void producer() throws InterruptedException{
		Random random = new Random();
		while(true){
			int val = random.nextInt(100);
			queue.put(val);
			System.out.println("ENTERED value: "+val+" , Queue size is "+queue.size());
		}
	}
	
	private static void consumer() throws InterruptedException{
		Random random = new Random();
		while(true){
			Thread.sleep(100);
			
			if(random.nextInt(10) == 0){
				int val = queue.take();
			
				System.out.println("Taken value: "+val+" , Queue size is "+queue.size());
			}
			
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				try {
					producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				try {
					consumer();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		
		
	}
	

}
