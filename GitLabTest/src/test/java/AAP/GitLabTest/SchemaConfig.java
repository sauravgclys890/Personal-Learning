package AAP.GitLabTest;

import java.util.List;
import java.util.Map;

public class SchemaConfig {
	
	private List<TestCaseConfig> testCaseConfig;
    private List<Staging> stagings;
    private List<Raw> raws;
    private List<Interim> interims;
    private List<AbstractPK> stagingpkLists;
    private List<AbstractPK> refinedpkLists;
    private List<History> historyList;

    public void setTestCaseConfig(List<TestCaseConfig> testCaseConfig) {
        this.testCaseConfig = testCaseConfig;
    }

    public List<TestCaseConfig> getTestCaseConfig() {
        return testCaseConfig;
    }

    public void setStagings(List<Staging> stagings) {
        this.stagings = stagings;
    }

    public List<Staging> getStagings() {
        return stagings;
    }

    public void setRaws(List<Raw> raws) {
        this.raws = raws;
    }

    public List<Raw> getRaws() {
        return raws;
    }
    public void setInterims(List<Interim> interims) {
        this.interims = interims;
    }

    public List<Interim> getInterims() {
        return interims;
    }

	public List<AbstractPK> getStagingPkLists() {
		return stagingpkLists;
	}

	public void setStagingPkLists(List<AbstractPK> pkLists) {
		this.stagingpkLists = pkLists;
	}

	public List<AbstractPK> getRefinedPkLists() {
		return refinedpkLists;
	}

	public void setRefinedPkLists(List<AbstractPK> pkLists) {
		this.refinedpkLists = pkLists;
	}

	public List<History> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<History> historyList) {
		this.historyList = historyList;
	}

}
