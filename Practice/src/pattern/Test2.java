package pattern;

public class Test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("A");
			badmethod();
			System.out.println("B");
		}catch(Error e) {
			System.out.println("c");
		}finally {
			System.out.println("D");
		}
		
		

	}
	
	public static void badmethod() {
		throw new Error();
	}

}
