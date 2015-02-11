package Demo1;


class Runner extends Thread{
	
	public void executeLoop(){
		for(int i = 0; i < 100000; i++)
			System.out.println(" Hello "+i+" from "+ getName());
	}
	
	public void run(){
			
		executeLoop();
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}



public class App {	
	
	public static void main(String[] args){
		Runner runner1 = new Runner();
		//runner1.start();
		
		
		
		Runner runner2 = new Runner();
		//runner2.start();
		
		
		Runner runner3 = new Runner();
		//runner3.start();
		
		/*
		long start = System.nanoTime();
		runner1.executeLoop();
		runner2.executeLoop();
		runner3.executeLoop();
		long end = System.nanoTime() - start;
		System.out.println("Time elapse: "+ (System.nanoTime() - start));
		*/
		
		
		runner1.start();
		runner2.start();
		runner3.start();
		
		System.out.println("Returning to main thread");
		
		
	}

}
