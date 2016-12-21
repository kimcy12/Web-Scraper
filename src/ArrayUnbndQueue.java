public class ArrayUnbndQueue<T> implements UnboundedQueueInterface<T> 
{
  protected final int DEFCAP = 100; // default capacity
  protected T[] queue;              // array that holds queue elements
  protected int origCap;            // original capacity
  protected int numElements = 0;    // number of elements in the queue
  protected int front = 0;          // index of front of queue
  protected int rear;               // index of rear of queue

  public ArrayUnbndQueue() 
  {
    queue = (T[]) new Object[DEFCAP];
	 rear = DEFCAP - 1;
    origCap = DEFCAP;
  }

  public ArrayUnbndQueue(int origCap) 
  {
    queue = (T[]) new Object[origCap];
	 rear = origCap - 1;
	 this.origCap = origCap;
  }

  private void enlarge()
  // Increments the capacity of the queue by an amount 
  // equal to the original capacity.
  {
    // create the larger array
    T[] larger = (T[]) new Object[queue.length + origCap];
    
    // copy the contents from the smaller array into the larger array
    int currSmaller = front;
    for (int currLarger = 0; currLarger < numElements; currLarger++)
    {
      larger[currLarger] = queue[currSmaller];
      currSmaller = (currSmaller + 1) % queue.length;
    }
    
    // update instance variables
    queue = larger;
    front = 0;
    rear = numElements - 1;
  }

  public void enqueue(T element)
  // Adds element to the rear of this queue.
  {  
    if (numElements == queue.length) 
      enlarge();

    rear = (rear + 1) % queue.length;
    queue[rear] = element;
    numElements = numElements + 1;
  }

  public T dequeue()
  // Throws QueueUnderflowException if this queue is empty;
  // otherwise, removes front element from this queue and returns it.
  {       
    if (isEmpty())
      throw new QueueUnderflowException("Dequeue attempted on empty queue.");
    else
    {
      T toReturn = queue[front];
      queue[front] = null;
      front = (front + 1) % queue.length;
      numElements = numElements - 1;
      return toReturn;
    }
  }

  public boolean isEmpty()
  // Returns true if this queue is empty; otherwise, returns false
  {              
    return (numElements == 0);
  }
}