
public class Node<TypeOf> {
	private TypeOf _dataType;
	private Node<TypeOf> next;

	public Node(TypeOf info) {
		_dataType = info;
		next = null;
	}
	
	public Node(TypeOf info, Node<TypeOf> a) {
		_dataType = info;
		next = a;
	}

	public Node(Node<TypeOf> nxt) {
		_dataType = nxt.get_dataType();
		next = null;
	}
	
	public TypeOf get_dataType() {
		return _dataType;
	}

	public void set_dataType(TypeOf _dataType) {
		this._dataType = _dataType;
	}

	public Node<TypeOf> getNext() {
		return next;
	}

	public void setNext(Node<TypeOf> next) {
		this.next = next;
	}
}