package studentAPI;

import org.testng.annotations.Test;

import APIAutomation.Utils.JavaUtils;
import pojo.Credentails;

public class loginTest {
	
	@Test
	public void loginTest() {
		Credentails cred=JavaUtils.base64Encode("admin", "123");
		
		System.out.println(cred.getCredentails());
	}

}
