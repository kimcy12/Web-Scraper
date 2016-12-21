import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;



public class WebObj implements Serializable,  Comparable<WebObj>{
	private String Url;
	private HashMap<String, ArrayList<String>> ElementList;

	
	public HashMap<String, ArrayList<String>> getElementList() {
		return ElementList;
	}

	public void setElementList(HashMap<String, ArrayList<String>> test) {
		ElementList = test;
	}

	public WebObj () {
		ElementList = new HashMap<>();
	}

	@Override
	public int compareTo(WebObj o) {
		
		return this.getUrl().compareTo(o.getUrl());
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}



	
}


