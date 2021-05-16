package DeserializingPOJO;

import java.util.List;

public class ListUsersPOJO {
	
	private String page;
	private String per_page;
	private String total;
	private String total_page;
	private List<DataPOJO> data;
	private AdPOJO ad;
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getPer_page() {
		return per_page;
	}
	public void setPer_page(String per_page) {
		this.per_page = per_page;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTotal_page() {
		return total_page;
	}
	public void setTotal_page(String total_page) {
		this.total_page = total_page;
	}
	public List<DataPOJO> getData() {
		return data;
	}
	public void setData(List<DataPOJO> data) {
		this.data = data;
	}
	public AdPOJO getAd() {
		return ad;
	}
	public void setAd(AdPOJO ad) {
		this.ad = ad;
	}
	@Override
	public String toString() {
		return "ListUsersPOJO [page=" + page + ", per_page=" + per_page + ", total=" + total + ", total_page="
				+ total_page + ", data=" + data + ", ad=" + ad + "]";
	}
	

}
