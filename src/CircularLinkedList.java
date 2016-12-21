import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CircularLinkedList extends ArrayUnbndQueue<WebObj>{
	private Node<WebObj> head;
	private Node<WebObj> tail;
	public long size;
	
	
	public CircularLinkedList() {
		head = null;
		tail = null;
		size = 0;
		
		try {
			Init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isEmpty() {
		return head == null;
	}
	
	public long getSize()
	{
		return size;
	}
	
	// This method must be initialized first to populate the list from disk.
	public void Init() throws IOException{
		ArrayList<WebObj> _webObjList = readObj();
				
				for (WebObj _webObj : _webObjList) {
					addToHead(_webObj);
				}
	}
	
	// Must initialize the node, and it's data type before adding.
	public void addToHead(WebObj a) {
				
		Node<WebObj> temp = new Node<WebObj>(a, null);
		if (isEmpty()) {
			head = temp;
			tail = temp;
			temp.setNext(head);
			}
		else {	
				Node<WebObj> tt = head;
				temp.setNext(tt);
				head = temp;
				tail.setNext(head);
			}
			size++;
			
	}
	

	
	public void add(int index, WebObj a) {
		
	}
	
	// Seperate Method to serialize object to the disk.
	public void SerializeWebObj(ArrayList<WebObj> obj) {
		FileOutputStream _fileOutput = null;
		ObjectOutputStream _objOutput = null;
		
		try {
			// The method that I have incorporated in using the two FileOutputStream, and ObjectOutputStream
			// were mostly recommended in web to store data into disk, however,
			// To append data to the same file, the solution was not found.
			// Current code will rewrite the file each time to the disk instead of appending it.
			_fileOutput = new FileOutputStream("test1.ser", true);
			_objOutput = new ObjectOutputStream(_fileOutput);
			
			_objOutput.writeObject(obj);
			_objOutput.flush();
			_objOutput.close();
			_fileOutput.close();
			System.out.println("Success");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	// Seperate Method to deserialize the object and returns it.
		public ArrayList<WebObj> readObj() throws IOException {
			FileInputStream _fileInput = new FileInputStream("test1.ser");
			ObjectInputStream _objInput = new ObjectInputStream(_fileInput);
			
			ArrayList<WebObj> _list = new ArrayList<WebObj>();
				try {
					
					_list = (ArrayList<WebObj>) _objInput.readObject();
					_objInput.close();

				}

					catch (Exception e) {
						System.out.println(e);
				}
			return _list;
		}
	
	// Method to retrive full object by url.
	public WebObj search(String url) {
		Node<WebObj> temp =  null;

		temp = head;
				
		while (temp != null) {
			if (temp.get_dataType().getUrl().equalsIgnoreCase(url)) {
				return temp.get_dataType();
			}
			temp = temp.getNext();
		}
		return null;
	}
	
	public ArrayList<String> ListAllUrls() {
		Node<WebObj> temp =  null;

		
		ArrayList<String> _obj = new ArrayList<String>();
		temp = head;

		for (int i = 0 ; i < size; i++) {
			_obj.add(temp.get_dataType().getUrl());
			temp = temp.getNext();
		}
		
		return _obj;
	}
	
	public void addToEnd(WebObj a) {
		
		Node<WebObj> temp = new Node<WebObj>(a, null);
		
		if (isEmpty()) {
			addToHead(a);
		}
		else {
			tail.setNext(temp);
			tail = temp;
			tail.setNext(head);
			size++;
		}
	}
	
	  public void insertAtPos(Node<WebObj> a, int index) {
		  Node<WebObj> temp = head;
		  index -= 1;
		  for (int i = 0; i < size -1; i++) {
			  if (i == index) {
				  temp.setNext(a);
				  a.setNext(temp.getNext());
				  break;
			  }
			  temp = temp.getNext();
		  }
		  size++;
	  }
	  
	  
	  public WebObj get(int index) {
			//Gets the element at the specified position in this list but does not delete it.
				if(index < 0)
					return null;
				
				Node<WebObj> _currentNode = head;
				for(int i = 0; i < index; i++)
				{
					_currentNode = _currentNode.getNext();
				}
				
				//Disregard name convention. Auto Generated getter.
				return _currentNode.get_dataType();
		}
	  
	    public void deleteAtPos(int index)
	    {    
	        if (size == 1 && index == 1)
	        {
	            head = null;
	            tail = null;
	            size = 0;
	            return ;
	        }        
	        if (index == 1) 
	        {
	        	head = head.getNext();
	            tail.setNext(head);
	            size--; 
	            return ;
	        }
	        if (index == size) 
	        {
	            Node<WebObj> s = head;
	            Node<WebObj> t = head;
	            while (s != tail)
	            {
	                t = s;
	                s = s.getNext();
	            }
	            tail = t;
	            tail.setNext(head);
	            size --;
	            return;
	        }
	        Node<WebObj> ptr = head;
	        index -= 1 ;
	        for (int i = 1; i < size - 1; i++) 
	        {
	            if (i == index) 
	            {
	                Node<WebObj> tmp = ptr.getNext();
	                tmp = tmp.getNext();
	                ptr.setNext(tmp);
	                break;
	            }
	            ptr = ptr.getNext();
	        }
	        size-- ;
	    }
}
