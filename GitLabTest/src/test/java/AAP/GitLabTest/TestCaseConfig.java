package AAP.GitLabTest;

public class TestCaseConfig {
	private String tcName;
    private String layer;


    public TestCaseConfig() {
    }

    public TestCaseConfig(String tcName, String layer) {
        this.tcName = tcName;
        this.layer = layer;
    }

  
	public String getTcName() {
        return tcName;
    }

    public void setTcName(String tcName) {
        this.tcName = tcName;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    
}
