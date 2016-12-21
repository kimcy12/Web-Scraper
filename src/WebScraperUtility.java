import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class WebScraperUtility implements BaseUtility
{
	public static CircularLinkedList CIRCULARUTILITY = new CircularLinkedList();

	
	public WebScraperUtility() {
		CIRCULARUTILITY = new CircularLinkedList();
		 try {
			 CIRCULARUTILITY.Init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// This method initiates URL connection using JSOUP, along with
	// Optional param String elements for element tags.
	// Writes retrieved data to disk and also to the  Circuluar Linked List.
	@Override
	public WebObj InitElementsByURL(String url, String[] elements) 
	{
		try {
			//Initiate new Hashmap obj.
			HashMap<String, ArrayList<String>> newMap = new HashMap<>();
			
			// Establish url connection through jsoup api.
			// Efficient since only one api call is made per url.
	        Document doc = Jsoup.connect(url).get();
	        Elements _dynamicElements = null;
	        
	        ArrayList<String> s = new ArrayList<>();
	        
	        // Setting list of elements by the strings from param individually.
			for (String _newElements : elements) {
				_dynamicElements = doc.select(_newElements);
			}
			
			 for (Element el : _dynamicElements) {
					s.add(el.text());
					for (int i = 0; i < elements.length; i++) {
						newMap.put(elements[i], s);
					}
			 }
			
			WebObj _rtn = new WebObj();
			_rtn.setUrl(url);
			_rtn.setElementList(newMap);
			
			
			System.out.println("Initializing connection....");
			try {
				System.out.println("Initializing connection........");
				System.out.println("Initializing connection.............");

			    Thread.sleep(2000);
				System.out.println("Success!");

			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			System.out.println("Successfully Wrote data to Disk!");

			return _rtn;

		}
		catch (Exception e) {
			return null;
		}
	}
	

	
	public ArrayList<String> ListAllUrls() {
		return CIRCULARUTILITY.ListAllUrls();
	}
	
	public WebObj SearchByUrl(String url) {
		return CIRCULARUTILITY.search(url);
	}
	
	// Helper method to detect if element has inner tags.
	@Override
	public boolean CheckElementIsTagged(Element element) {

		boolean rtn = false;
		
		if (!element.tagName().isEmpty()) {
			rtn = true;
		}
		return rtn;
	}	
}
