import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public interface BaseUtility {
	public WebObj InitElementsByURL(String url, String[] elements);
	public boolean CheckElementIsTagged(Element element);
}
