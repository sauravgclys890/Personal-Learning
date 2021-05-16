package AAP.GitLabTest;

public abstract class AbstractPK {
 private String pKName;

 public AbstractPK() {
 }
 
 public AbstractPK(String pkName) {
     this.pKName = pkName;
     
 }
public String getpKName() {
	return pKName;
}

public void setpKName(String pKName) {
	this.pKName = pKName;
}
}
