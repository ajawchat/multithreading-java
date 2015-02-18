package ReEntrantLock;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count = 0;
	
	// Re-entrant locks can be used instead of synchronized keyword
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	
	private void increment(){
		for(int i =0; i<10000; i++){
			count++;
		}
	}
	
	public void firstThread() throws InterruptedException{
		
		System.out.println("Starting first");
		
		lock.lock();
		// It releases the lock
		System.out.println("t1 acquires lock");
		cond.await();
		
		
		try{
			increment();
			}
		finally{
			lock.unlock();
		}
	}
	
	public void secondThread() throws InterruptedException{
		
		// This sleep is to make sure that thread t1 acquires the lock first
		Thread.sleep(1000);
		System.out.println("Starting second thread");
		
		// Thread 2 acquires it after it is awaiting 
		lock.lock();
		
		System.out.println("Press enter key: ");
		new Scanner(System.in).nextLine();
		System.out.println("Received enter key");
		
		cond.signal();
		
		try{
		increment();
		}
		finally{
			lock.unlock();
		}
	}
	
	
	public void finished(){
		System.out.println("Count : "+count);
	}
	

}
