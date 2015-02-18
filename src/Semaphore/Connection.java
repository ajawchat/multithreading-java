package Semaphore;

import java.util.concurrent.Semaphore;

// This is a singleton object

public class Connection {
	
	private static Connection connInstance = new Connection();
	private int connCnt = 0;
	
	Semaphore sem = new Semaphore(10,true);
	
	
	private Connection(){
		
	}
	
	public static Connection getInstance(){
		return connInstance;
	}
	
	
	public void connect() throws InterruptedException{
		
		// The threads should acquire the permit from semaphore, which decreases its count by 1
		try {
			sem.acquire();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		synchronized (this) {
			connCnt++;
			System.out.println("Current Connections: "+connCnt);
		}
		
		Thread.sleep(2000);
		
		synchronized (this) {
			connCnt--;
		}
		
		// The threads should release the permit from semaphore, which increases its count by 1
		try {
			sem.release();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
