package Sync;

public class App2 {
	
	private int count = 0;
	
	public static void main(String[] args) {
		
		App2 app = new App2();
		app.doWork();
	}
	
	// Keyword synchronized is used so that threads have to acquire the mutex key before entering this 
	// method. There is no need to give the volatile keyword for this variable, as 'synchronized' covers that as well
	public synchronized void increment(){
		count++;
	}
	
	
	// create a method to be called for doing work
	public void doWork(){
		
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				for(int i = 0; i<10000; i++)
					//count++;
					increment();
			}
		});	
		
			
		Thread t2 = new Thread(new Runnable(){
			public void run() {
				for(int i = 0; i < 10000; i++)
					//count++;
					increment();
			}
		});	
		
			
		// start the threads
		t1.start();
		t2.start();
		
		// Join the spawned threads to the main thread. The main thread waits till the two threads are done
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("Count = "+count);
		
	}
	

}
