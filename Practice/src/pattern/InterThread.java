package pattern;

class Data {
	int msg;
	boolean valueSet = false;

	public synchronized void get() {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		while (!valueSet) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		System.out.println("Consume:" + msg);
		valueSet = false;
		notify();

	}

	public synchronized void set(int msg) {
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		while (valueSet) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		this.msg = msg;
		System.out.println("Produce :" + msg);
		valueSet = true;
		notify();
	}

}

class Producer implements Runnable {
	Data d;

	Producer(Data d) {
		this.d = d;
		new Thread(this, "Producer").start();
	}

	public void run() {
		int i = 1;
		while (true) {
			d.set(i++);
		}
	}

}

class Consumer implements Runnable {
	Data d;
	boolean ValueSet = false;

	Consumer(Data d) {
		this.d = d;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		while (true) {
			d.get();
		}
	}

}

public class InterThread {

	public static void main(String[] args) {
// TODO Auto-generated method stub
		Data d = new Data();
		new Producer(d);
		new Consumer(d);

	}

}
