package ToolsQA.CucumberFW;

public class Fabonacci {

	public static void main(String[] args) {
		
		int num =10;
		int n1 =0;
		int n2 =0;
		
		System.out.println(n1);
		
		for(int i=1; i<10; i++) {
			System.out.println(fabonacci(i));
		}

	}
	
	public static int fabonacci(int num) {
		if(num ==1 || num ==2) {
			return 1;
		}else {
			return fabonacci(num -1)+ fabonacci(num-2);
		}
	}

}
