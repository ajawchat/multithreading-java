package ReEntrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	
	private int count = 0;
	
	// Re-entrant locks can be used instead of synchronized keyword
	private Lock lock = new ReentrantLock();
	
	private void increment(){
		for(int i =0; i<10000; i++){
			count++;
		}
	}
	
	public void firstThread(){
		lock.lock();
		increment();
		lock.unlock();
	}
	
	public void secondThread(){
		lock.lock();
		increment();
		lock.unlock();
	}
	
	
	public void finished(){
		System.out.println("Count : "+count);
	}
	

}
