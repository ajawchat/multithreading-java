package SyncBlock;

import java.util.ArrayList;
import java.util.Random;

public class Worker {
	
	Random random = new Random();
	
	private ArrayList<Integer> list1 = new ArrayList<Integer>();
	private ArrayList<Integer> list2 = new ArrayList<Integer>();
	
	
	public synchronized void stageOne(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		list1.add(random.nextInt(100));
	}
	
	public synchronized void stageTwo(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		list2.add(random.nextInt());
	}
	
	
	public void process(){
		for(int i=0;i < 1000; i++){
			stageOne();
			stageTwo();
		}
	}
	
	public void main(){
		System.out.println("Starting...");
		
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				process();
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run() {
				process();
			}
		});
		
		long start = System.currentTimeMillis();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time Taken: "+(end-start));
		System.out.println("List1: "+list1.size());
		System.out.println("List2: "+list2.size());
		
		
	}
	
	
}