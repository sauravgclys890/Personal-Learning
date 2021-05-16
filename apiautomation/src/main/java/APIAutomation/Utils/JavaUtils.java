package APIAutomation.Utils;

import java.util.Base64;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

import com.aventstack.extentreports.model.Test;

import pojo.Credentails;

public class JavaUtils {
	
	public static Credentails base64Encode(final String user, final String password) {
		Credentails cr= new Credentails();
		String cred= user+":"+password;
		try {
		 cred= Base64.getEncoder().encodeToString(cred.getBytes());
		 cr.setCredentails(cred);
		
		}catch(Exception e) {
			
		}
		
		return cr;
	}
	
	public static String randomNumber() {
		Random num= new Random();
		int randomnumber= num.nextInt(1000);
		String id=Integer.toString(randomnumber);
		return id;
	}
	
	public static String randomString() {
		//random string value
				String random=RandomStringUtils.randomAlphabetic(3);
				return random;
	}

}
