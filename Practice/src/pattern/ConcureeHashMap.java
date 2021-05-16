package pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class ConcureeHashMap {

	static Map<String, AtomicLong> orders= new ConcurrentHashMap<String, AtomicLong>();
	
	static void processOrder() {
		for(String city: orders.keySet()) {
			for(int i=0; i<50; i++) {
			orders.get(city).getAndIncrement();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		orders.put("Bombay", new AtomicLong());
		orders.put("Beijing", new AtomicLong());
		orders.put("London", new AtomicLong());
		orders.put("New York", new AtomicLong());
		
		for(int i=0; i<10; i++) {
		ExecutorService service= Executors.newFixedThreadPool(2);
		service.submit(ConcureeHashMap::processOrder);
		service.submit(ConcureeHashMap::processOrder);
		
		service.awaitTermination(1, TimeUnit.SECONDS);
		service.shutdown();
		System.out.println(orders);
		}

	}

}
