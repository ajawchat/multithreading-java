package Sync;

import java.util.Scanner;

class Processor extends Thread{
	
	// Use of volatile keyword declares that the value of this variable will be changed by different threads
	// Setting volatile confirms that the variable value will not be cached thread-locally : all reads & writes will go straight to the main memory
	// Access to the variable acts as though the variable is present in a synchronized block
	// In this case, there are two threads - main thread and the new spawned thread running this
	private volatile boolean running = true;
	
	public void run(){
		while(running){
			System.out.println("Hello");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void shutdown(){
		running = false;
	}
	
}

public class App {
	public static void main(String[] args){
		Processor proc1 = new Processor();
		proc1.start();
		
		System.out.println("Press return to stop..." +
				"");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		proc1.shutdown();
		
		
		
	}

}
