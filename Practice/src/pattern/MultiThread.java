package pattern;

 class Mythread implements Runnable{
	String name;
	Thread t;
	
	Mythread(String name){
		this.name= name;
		t= new Thread(this, name);
		System.out.println("New thread is ="+ t);
		t.start();
	}
	@Override
	public void run() {
		try {
		for(int i=0; i<100; i++) {
			System.out.println(name +"; "+ i);
			
				Thread.sleep(1000);
		}
		}catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}
public class MultiThread {
	public static void main(String[] args) {
		new Mythread("one");
		new Mythread("two");
		new Mythread("three");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

}
