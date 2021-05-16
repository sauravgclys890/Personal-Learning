package com.automation.PageObject;

import static org.awaitility.Awaitility.await;

import java.util.concurrent.TimeUnit;

public abstract class Page {

public abstract boolean isAt();
	
	public Boolean isAt(long timeout, TimeUnit timeunit) {
		try {
		await().atMost(timeout, timeunit).ignoreExceptions().until(()-> isAt());
		return true;
		}catch(Exception e) {
			return false;
		}
	
	}
}
