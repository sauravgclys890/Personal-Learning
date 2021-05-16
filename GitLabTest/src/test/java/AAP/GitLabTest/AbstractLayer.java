package AAP.GitLabTest;

public abstract class AbstractLayer {
	
	 private String colName;
	    private String dataType;
	    private String comment;
	    

	    public AbstractLayer() {
	    }

	    public AbstractLayer(String colName, String dataType, String comment) {
	        this.colName = colName;
	        this.dataType = dataType;
	        this.comment = comment;
	    }

	    public String getColName() {
	        return colName;
	    }

	    public void setColName(String colName) {
	        this.colName = colName;
	    }

	    public String getDataType() {
	        return dataType;
	    }

	    public void setDataType(String dataType) {
	        this.dataType = dataType;
	    }

	    public String getComment() {
	        return comment;
	    }

	    public void setComment(String comment) {
	        this.comment = comment;
	    }


}
