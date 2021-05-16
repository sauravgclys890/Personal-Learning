package APIAutomation.Utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class HMAC_Conversion {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
		String secret="admin";
		String message= "message";
		
		Mac sha256= Mac.getInstance("HmacSHA256");
		
		SecretKeySpec s_Key= new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		
		sha256.init(s_Key);
		
		String hash=Base64.encodeBase64String(sha256.doFinal(message.getBytes()));
		
		System.out.println(hash);
		
		
		
	}

}
