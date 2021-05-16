package com.automation.PageObject;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.*;

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
