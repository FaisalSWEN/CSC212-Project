/*------------------------------------------------------------------------------------
  Node Class: Work as Element for LinkedList ADT
  ------------------------------------------------------------------------------------*/
class Node<T> 
{
  public T data;
  public Node<T> next;

  public Node() {
    data = null;
    next = null;
  }

  public Node(T val) {
    data = val;
    next = null;
  }
}



/*------------------------------------------------------------------------------------
  LinkedList Class
  ------------------------------------------------------------------------------------*/
public class LinkedList<T> 
{
  private int length;
  private Node<T> head;
  private Node<T> current;

  public LinkedList() {
    head = current = null;
    length = 0;
  }

  public boolean empty() {
    return head == null;
  }

  public boolean last() {
    return current.next == null;
  }

  public boolean full () {
    return false;
  }

  public void findFirst() {
    current = head;
  }

  public void findNext() {
    current = current.next;
  }

  public T retrieve() {
    return current.data;
  }

  public void update(T val) {
    current.data = val;
  }

  // # INSERT METHOD
  public void insert(T val) // # First Method
  {
    if ((val instanceof Comparable) && !empty())
    {

      if (((Comparable) val).precedes((Comparable) head.data)) // Precedes all Case
      {
        Node<T> tmp = new Node<T>(val);
        tmp.next = head;
        head = tmp;
      }

      else // Search Case
      {
        Node<T> tmp = head;
        findFirst();

        while(current != null) // 1: it's within the list
        {
          if(((Comparable) val).precedes((Comparable) retrieve()))
          {
            tmp.next = new Node<T>(val);
            (tmp.next).next = current;
            return;
          }

          tmp = current; 
          findNext();
        }

        tmp.next = new Node<T>(val); // 2: it's the last one
        findFirst();
      }
    }

    else 
    {
      Node<T> tmp;
      
      if (empty())
        current = head = new Node<T>(val);
      
      else 
      {
        tmp = current.next;
        current.next = new Node<T>(val);
        current = current.next;
        current.next = tmp;
      }
    }

    length++;
	}

  // # REMOVE METHOD
  public void remove() {
		if (current == head)
      head = head.next;
		
    else 
    {
		  Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
    else
			current = current.next;
    
    length--;
	}

  // # FIND METHOD: Check if the object key values already exist.
  public boolean find(T key) 
  {
    Node<T> tmp = head;

    while(tmp != null) 
    {
      if(tmp.data.equals(key)) 
      {
        current = tmp;
        return true;
      }

      else 
        tmp = tmp.next;
    }

    return false;
  }

  // # EXIST METHOD: Check if the object key refrence already exist.
  public boolean exist(T key) 
  {
    Node<T> tmp = head;

    while(tmp != null) 
    {
      if(tmp.data == key) 
      {
        current = tmp;
        return true;
      }

      else 
        tmp = tmp.next;
    }

    return false;
  }

  // Getter
  public int getLength(){
    return length;
  }
}