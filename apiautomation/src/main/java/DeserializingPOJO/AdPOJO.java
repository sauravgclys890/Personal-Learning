package DeserializingPOJO;

public class AdPOJO {

	private String Company;
	private String url;
	private String text;
	public String getCompany() {
		return Company;
	}
	public void setCompany(String company) {
		Company = company;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "AdPOJO [Company=" + Company + ", url=" + url + ", text=" + text + "]";
	}
	
	
}
