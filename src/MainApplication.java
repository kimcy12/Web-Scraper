import java.util.ArrayList;
import java.util.Scanner;

public class MainApplication {
	
	private static int userSelectedInt = 0;
	private static boolean quit = true;
	private static ArrayList<WebObj> _SaveData = new ArrayList<>();
	private static WebScraperUtility _WebScraperUtil = new WebScraperUtility();

	
	public static void main(String[] args) {

		
		while (quit) {
			menu();
		}
	}
	
	public static void AddWebObj() {
		String url = InitUrl();
		
		String[] elements = InitElements();
			
		WebObj _webObj = _WebScraperUtil.InitElementsByURL(url, elements);
		_SaveData.add(_webObj);

		WebScraperUtility.CIRCULARUTILITY.addToHead(_webObj);
		
	}
	
	public static String InitUrl() {
	     Scanner keyboard = new Scanner(System.in);
	     boolean flag = true;

		String urlInput = "";
		while (flag) {
			try {
				System.out.println("Please Enter a Url to scrape from");
				urlInput = keyboard.next();
				flag = false;
			}
			catch (Exception e) {
				System.out.println("Please try again!");
				keyboard.reset();
			}
		}
		
		return urlInput;
	}
	
	public static String[] InitElements() {
	     Scanner keyboard = new Scanner(System.in);

		String elements = "";
		String[] newElements = new String[100];
		boolean flag = true;

		
		while (flag) {
			try {
				System.out.println("Please enter the elements you would like to retrieve. (example: a, div, ...etc..)");
				elements = keyboard.nextLine();
				flag = false;
			}
			catch (Exception e) {
				System.out.println("Please try again!");
				keyboard.reset();
			}
		}
		newElements = elements.trim().split(",");

		return newElements;
	}
	
	public static void ListAllUrls() {
	     Scanner keyboard = new Scanner(System.in);

		ArrayList<String> _urlList =  WebScraperUtility.CIRCULARUTILITY.ListAllUrls();
		ArrayList<WebObj> _objList = new ArrayList<>();
		
		for (int i = 0; i < _urlList.size(); i++) {
			_objList.add(_WebScraperUtil.SearchByUrl(_urlList.get(i)));
			System.out.println(i + ". " + _urlList.get(i));

		}
//		
		
		
		
		try {
			options();
		}
		catch (Exception e) {
			System.out.println("Please try again!");
			keyboard.reset();
		}
		
	}
	
	
	public static void FilterByElement(int input) {
	     Scanner keyboard = new Scanner(System.in);

		ArrayList<String> _urlList =  WebScraperUtility.CIRCULARUTILITY.ListAllUrls();
		ArrayList<WebObj> _objList = new ArrayList<>();
		int i = 0;
		for (String urls : _urlList) {
			System.out.println(i+1 + ". " + urls);
			_objList.add(WebScraperUtility.CIRCULARUTILITY.search(urls));
			i++;
		}
		
	String userInput = "";
		
		try {
			System.out.println("Select an element to filter by.");
			userInput = keyboard.nextLine();
		}
		catch (Exception e) {
			System.out.println("Please try again!");
			keyboard.reset();
		}
		

		WebObj _webObj = _objList.get(userSelectedInt);
		
		
		
		for (int j = 0; j < _webObj.getElementList().get(userInput).size(); j ++) {
			System.out.println("Element: " + userInput + " || Data: " + _webObj.getElementList().get(userInput).get(j) );

		}
			
	}
	
	public static void saveData() {
		
		WebScraperUtility.CIRCULARUTILITY.SerializeWebObj(_SaveData);
	}
	
	public static void options() {
	     Scanner keyboard = new Scanner(System.in);
			boolean flag = true;
			int userInput = 0;
			
				try {
					  System.out.println("Please select the number from the following list to manage.");
					  userInput = keyboard.nextInt();
					  flag = false;
				}
				catch (Exception e) {
					System.out.println("Please try again!");
					keyboard.reset();
				}
				
				
	     		FilterByElement(userInput);

		  
   	  switch (userInput) {
     	case 1: 
     		FilterByElement(userInput);
     		break;
	   	case 2:
	   		break;
	   		
	   	case 3:
      		AddWebObj();
	   		break;
	
	   	case 4:
      		menu();
	   		break;
	   	default:
	   		break;
	   	  }
		}
	
	
	public static void menu() {
	     Scanner keyboard = new Scanner(System.in);
	     
		int userInput = 0;
	 	boolean flag = true;
		
		
			try {
				System.out.println("Please Choose from the following."
	            		+ "\n1. Scrape and Add From new URL"
	            		+ "\n2. Go To Saved List"
	            		+ "\n3. Save And Quit"
					  		);
				userInput = keyboard.nextInt();
				flag = false;
			}
			catch (Exception e) {
				System.out.println("Please try again!");
				keyboard.reset();
			}
		

		  
    	  switch (userInput) {
      	case 1: 
      		AddWebObj();
      		break;
    	case 2:
	   		ListAllUrls();
    		break;

    	case 3:
	   		quit = false;
	   		saveData();
    		break;
    	default:
    		break;
    	  }
	}
}