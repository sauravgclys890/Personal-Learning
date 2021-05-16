package AAP.GitLabTest;

public class Saurav {
	
	private String inputSQL;
	
	 public Saurav() {

     }

     public Saurav(String inputSQL, String testCaseName) {
         this.inputSQL = this.inputSQL +"_"+ testCaseName;
         this.inputSQL = inputSQL;
     }

	public String getInputSQL() {
		return inputSQL;
	}

	public void setInputSQL(String inputSQL) {
		this.inputSQL = inputSQL;
	}

}
