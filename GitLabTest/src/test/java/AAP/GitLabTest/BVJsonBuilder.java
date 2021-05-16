package AAP.GitLabTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BVJsonBuilder {
	
	private String readMode;
    private String readType;
    private String tcId;
    private String name;
    private String type;
    private String tcName;
    private boolean mildUpdate;
    private LinkedList<BVCase> testCases;
    private LinkedList<BVServer> servers;
    private LinkedList<BVSource> sources;
    private LinkedList<BVValidation> validationObjects;
    private InputParameter inputParameters;

    public BVJsonBuilder(String tcName) {
        this.tcName = tcName;
    }

    public BVJsonBuilder(String name, String type, String sourcesName) {
        this.name = name;
        this.type = type;
        this.sources = new LinkedList<BVSource>();
        this.sources.add(new BVSource(sourcesName));
    }

    public BVJsonBuilder(String name, String nameServer, String source, String readType, String readMode) {
        this.readMode = readMode;
        this.readType = readType;
        this.testCases = new LinkedList<BVCase>();
        BVCase bvCase = new BVCase(name);
        this.testCases.add(bvCase);
        this.servers = new LinkedList<BVServer>();
        BVServer server = new BVServer(nameServer, source);
        this.servers.add(server);
    }

    public BVJsonBuilder() {
        this.testCases = new LinkedList<BVCase>();
        this.testCases.add(new BVCase());
        this.servers = new LinkedList<BVServer>();
        this.servers.add(new BVServer());
    }

    public BVJsonBuilder(String testCaseName, List<Map> erList, String sqlScripts){
        this.mildUpdate = true;
        this.tcName = testCaseName;
        this.inputParameters = new InputParameter(sqlScripts);
        int size = erList.size();
        this.validationObjects = new LinkedList<BVValidation>();
        int i=1;
        for(Map map: erList){
            BVValidation bvValidation = new BVValidation("Step_"+i, map);
            this.validationObjects.add(bvValidation);
            i++;
        }

    }

    public String getTcName() {
    	return tcName;
    }
    //region get/set
    public String getTcId() {
        return tcId;
    }

    public void setTcId(String tcId) {
        this.tcId = tcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<BVCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(LinkedList<BVCase> testCases) {
        this.testCases = testCases;
    }

    public LinkedList<BVServer> getServers() {
        return servers;
    }

    public void setServers(LinkedList<BVServer> servers) {
        this.servers = servers;
    }

    public String getReadMode() {
        return readMode;
    }

    public void setReadMode(String readMode) {
        this.readMode = readMode;
    }

    public String getReadType() {
        return readType;
    }

    public void setReadType(String readType) {
        this.readType = readType;
    }

    public LinkedList<BVSource> getSources() {
        return sources;
    }

//endregion

    public void setSources(LinkedList<BVSource> sources) {
        this.sources = sources;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static class BVCase {
        private String name;
        //endregion
        public BVCase() {
        }

        public BVCase(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public static class InputParameter {
        private String inputSQL;
        //endregion
        public InputParameter() {
        }

        public InputParameter(String inputSQL) {
            this.inputSQL = inputSQL;
        }

        public String getName() {
            return inputSQL;
        }

        public void setName(String name) {
            this.inputSQL = inputSQL;
        }

    }

    private class BVValidation{
        private String name;
        private LinkedList<BVValidation.BVChildren> children;

        public BVValidation() {
        }

        public BVValidation(String stepName, Map map) {
            this.name = stepName;
            this.children = new LinkedList<BVValidation.BVChildren>();
            for (Object o : map.entrySet()) {
                Map.Entry en = (Map.Entry)o;
                String value = en.getValue().toString();
                String key = en.getKey().toString();
                BVValidation.BVChildren bvChildren=  new BVValidation.BVChildren(key, value);
                this.children.add(bvChildren);
            }
        }

        private class BVChildren{
            private String name;
            private String value;

            public BVChildren(){
            }

            public BVChildren(String name, String value){
                this.name = name;
                this.value = value;
            }

            public String getName() { return name; }
            public String getValue() { return value; }

        }
    }
    private class BVServer {
        private String name;
        private LinkedList<String> sources;

        public BVServer() {
        }

        public BVServer(String name, String source) {
            this.name = name;
            this.sources = new LinkedList<String>();
            this.sources.add(source);
        }

        //region get/set
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public LinkedList<String> getSources() {
            return sources;
        }

        public void setSources(LinkedList<String> sources) {
            this.sources = sources;
        }
        //endregion

    }

    private class BVSource {
        private String sourceName;
        //region get/set

        public BVSource(String sourceName) {
            this.sourceName = sourceName;
        }

        public String getSourceName() {
            return sourceName;
        }

        //endregion

        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }
    }

}
