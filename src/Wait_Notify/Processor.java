package Wait_Notify;

import java.util.Scanner;

public class Processor {
	
	public void produce() throws InterruptedException{
		synchronized(this){
			System.out.println("Producer thread running");
			wait();
			System.out.println("Producer thread exiting");
		}
	}
	
	// Once the notify() wakes up the producer thread, it cannot resume unless it regains the monitor. So first
	// all the statements below notify() will execute, and then only will consumer thread release the lock and 
	// the producer thread will resume its execution. 
	
	public void consume() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		
		Thread.sleep(2000);
		synchronized(this){
			System.out.println("waiting for enter key");
			
			if(scanner.nextLine() != null){
				System.out.println("Calling notify()....Return key pressed");
				notify();
				System.out.println("Still in consumer thread");
			}
		}
	}
	
}
