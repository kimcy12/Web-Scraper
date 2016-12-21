import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class URLConnection {

	public static void post(String url) throws Exception {

	    
	    Document doc = Jsoup.connect(url).get();

	    Elements _elements = doc.getElementsByAttribute("href");
	    
	    System.out.println(_elements.toString());
	}
	
	public static void main(String[] args) {
		try {
			post("https://bodybuilding.com/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
