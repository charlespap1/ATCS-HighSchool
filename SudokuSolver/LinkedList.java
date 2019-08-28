import java.util.Iterator;

public class LinkedList<E> implements Stack<E>, Queue<E>, Iterable<E>
{
	/**
		stores the reference to the head ListNode which provides starting location
	*/
	private ListNode<E> head;
	
	/**
		stores the reference to the tail ListNode
	*/
	private ListNode<E> tail;
	
	/**
		stores the size of the LinkedList as type int
	*/
	private int size;
	
	/**
		Basic constructor that sets head and tail to null and size to 0
	*/
	public LinkedList()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
		Constructor that creates a LinkedList of size 1 that sets head and tail to the parameter ListNode
		@param n ListNode that head and tail are referencing
	*/
	public LinkedList(ListNode<E> n)
	{
		head = n;
		tail = n;
		size = 1;
	}
	
	/**
		Constructor that takes in a LinkedList and creates a new identical one
		@param other LinkedList that is being copied
	*/
	public LinkedList(LinkedList<E> other)
	{
		head = other.getHead();
		tail = other.getTail();
		size = other.size();
	}
	
	/**
		gets the head reference
		@return head of type ListNode<E>
	*/
	public ListNode<E> getHead()
	{
		return head;
	}
	
	/**
		gets the tail reference
		@return tail of type ListNode<E>
	*/
	public ListNode<E> getTail()
	{
		return tail;
	}
	
	/**
		adds a specified object to the end of the LinkedList
		@param obj value of type E being added to the LinkedList
	*/
	public void add(E obj)
	{
		if(size == 0)
		{
			ListNode<E> l = new ListNode<E>(obj);
			head = l;
			tail = l;
		}
		else
		{
			ListNode<E> l = new ListNode<E>(obj);
			tail.setNext(l);
			tail = l;
		}
		size++;
	}
	
	/**
		adds a specified item to the end of the LinkedList, used in stack
		@param item value being added of type E
	*/
	public void push(E item)
	{
		addLast(item);
	}
	
	//Queue - add
	/**
		adds a specified item to the beginning of the LinkedList, used in queue
		@param item value being added of type E 
	*/
	public void offer(E item)
	{
		addFirst(item);
	}
	
	/**
		adds a specified value to the beginning of the LinkedList
		@param obj value being added to the first location of type E
	*/
	public void addFirst(E obj)
	{
		ListNode<E> l = new ListNode<E>(obj);
		if(size == 0)
		{
			head = l;
			tail = l;
		}
		else
		{
			l.setNext(head);
			head = l;
		}
		size++;
	}
	
	/**
		adds a specified value to the end of the LinkedList
		@param obj value being added to the end location of type E
	*/
	public void addLast(E obj)
	{
		add(obj);
	}
	
	/**
		attempts to remove a ListNode with a specific value from the LinkedList
		@param obj value being checked for, what is trying to be removed
		@return true if the value is successfully removed, false if it is not found in the LinkedList
	*/
	public boolean remove(E obj)
	{
		//check if list is empty
		if(head == null)
		{
			return false;
		}
		//check if removing head if value is null
		else if(head.getValue() == null)
		{
		
			if(head.getValue() == obj)
			{
				head = head.getNext();
				size--;
				return true;
			}
		}
		//check if removing head if value is an object
		else
		{
			if(head.getValue().equals(obj))
			{
				head = head.getNext();
				size--;
				return true;
			}
		}
		//checking the rest of linked list
		ListNode<E> prev = head;
		for(ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if(curr.getValue() == null)
			{
				if(curr.getValue() == obj)
				{
					prev.setNext(curr.getNext());
					size--;
					return true;
				}
			}
			else
			{
				if(curr.getValue().equals(obj))
				{
					prev.setNext(curr.getNext());
					size--;
					return true;
				}
			}
			prev = curr;
		}
		//checking if remove tail
		if(tail.getValue() == null)
		{
			if(tail.getValue() == obj)
			{
				prev.setNext(null);
				size--;
				return true;
			}
		}
		else
		{
			if(tail.getValue().equals(obj))
			{
				prev.setNext(null);
				size--;
				return true;
			}
		}
		return false;
	}
	
	/**
		removes the ListNode at the first location in the LinkedList
		@return the value stored in the removed ListNode
	*/
	@SuppressWarnings("unchecked")
	public E removeFirst()
	{
		E rVal = head.getValue();
		if(head == tail)
		{
			tail = null;
		}
		head = head.getNext();
		size--;
		return rVal;
	}
	
	/**
		removes the ListNode at the last location in the LinkedList
		@return the value stored in the removed ListNode
	*/
	@SuppressWarnings("unchecked")
	public E removeLast()
	{
		E rVal = null;
		if(head == null)
		{
			throw new RuntimeException("The Linked List is empty");
		}
		else if(head == tail)
		{
			rVal = head.getValue();
			clear();
		}
		else
		{
			rVal = tail.getValue();
			ListNode<E> end = null;
			for(ListNode<E> curr = head; curr != tail; curr = curr.getNext())
			{
				end = curr;
			}
			tail = end;
		}
		return rVal;
	}
	
	/**
		gets the size of the LinkedList
		@return the size of type int
	*/
	public int size()
	{
		return size;
	}
	
	/**
		removes the ListNode at specified index
		@param index location of the ListNode being removed, type int
		@return the value that was in the previously removed ListNode, type E
	*/
	@SuppressWarnings("unchecked")
	public E remove(int index)
	{
		if(index == 0)
		{
			E rV = head.getValue();
			head = head.getNext();
			return rV;
		}
		else
		{
			ListNode<E> prev = head;
			for(int count = 0; count < index && prev.getNext() != null; count++)
			{
				if (count == 0)
				{
					prev = head;
				}
				else
				{
					prev = prev.getNext();
				}
			}
			if(prev.getNext() == null)
			{
				throw new IndexOutOfBoundsException("There is no ListNode at index " + index);
			}
			else
			{
				E rVal = prev.getNext().getValue();
				prev.setNext(prev.getNext().getNext());
				size--;
				return rVal;
			}
		}
	}
	
	/**
		checks to see if the LinkedList contains a specified value
		@param obj the value being checked for
		@return true if the object is found, false if not
	*/
	public boolean contains(E obj)
	{
		if(head == null)
		{
			return false;
		}
		for(ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if(curr.getValue() == null)
			{
				if(curr.getValue() == obj)
				{
					return true;
				}
			}
			else
			{
				if(curr.getValue().equals(obj))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	/**
		clears the LinkedList by setting head and tail to null and size to 0
	*/
	public void clear()
	{
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
		gets the value at a specified index
		@param index location that is being specified to find
		@return the value stored in the ListNode at the specified location
	*/
	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		ListNode<E> curr = head;
		for(int count = 0; count <= index && curr != null; count++)
		{
			if(count == 0)
			{
				curr = head;
			}
			else
			{
				curr = curr.getNext();
			}
		}
		if(curr == null)
		{
			throw new IndexOutOfBoundsException("There is no ListNode at index " + index);
		}
		else
		{
			return curr.getValue();
		}
	}
	
	/**
		sets the value of the ListNode at a specific index to the given value
		@param index location of type int 
		@param obj value that is being inserted into the LinkedList, type E
		@return the previous value stored in ListNode at the index, type E
	*/
	@SuppressWarnings("unchecked")
	public E set(int index, E obj)
	{
		ListNode<E> curr = head;
		for(int count = 0; count <= index && curr != null; count++)
		{
			if(count == 0)
			{
				curr = head;
			}
			else
			{
				curr = curr.getNext();
			}
		}
		if(curr == null)
		{
			throw new IndexOutOfBoundsException("There is no ListNode at index " + index);
		}
		else
		{
			E rVal = curr.getValue();
			curr.setValue(obj);
			return rVal;
		}
	}
	
	/**
		adds a ListNode with the given value at the specific index, shifting all following ListNodes back
		@param index location of type int that the value is being added to
		@param obj value that is being added to the LinkedList, type E
	*/
	public void add(int index, E obj)
	{
		if(index == size)
		{
			addLast(obj);
		}
		else
		{
			ListNode<E> curr = head;
			for(int count = 0; count < index && curr != null; count++)
			{
				if(count == 0)
				{
					curr = head;
				}
				else
				{
					curr = curr.getNext();
				}
			}
			if(curr == null)
			{
				throw new IndexOutOfBoundsException("There is no ListNode at index " + index);
			}
			E tempVal = curr.getValue();
			curr.setValue(obj);
			E tempVal2;
			while(curr.getNext() != null)
			{
				curr = curr.getNext();
				tempVal2 = curr.getValue();
				curr.setValue(tempVal);
				tempVal = tempVal2;
			}
			ListNode<E> ln = new ListNode<E>(tempVal);
			curr.setNext(ln);
			tail = ln;
			size++;
		}
	}
	
	/**
		gets the first index of a specified value
		@param obj value that is being checked for at each index
		@return the index of the value or -1 if the LinkedList does not contain the specified value
	*/
	public int indexOf(E obj)
	{
		int index = 0;
		for(ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			if(curr.getValue() == null)
			{
				if(curr.getValue() == obj)
				{
					return index;
				}
			}
			else
			{
				if(curr.getValue().equals(obj))
				{
					return index;
				}
			}
			index++;
		}
		return -1;
	}
	
	/**
		removes the last value in the LinkedList, used in Stack
		@return the value that was removed, type E
	*/
	public E pop()
	{
		return removeLast();
	}
	
	/**
		removes the last value in the LinkedList, used in Queue
		@return the value that was removed, type E
	*/
	public E poll()
	{
		return removeLast();
	}
	
	/**
		gets the value at the last location in the LinkedList\
		@return the value at the last location of type E
	*/
	public E peek()
	{
		if(tail == null)
		{
			throw new NullPointerException("There are no values in the LinkedList");
		}
		return tail.getValue();
	}

	/**
		checks if the LinkedList is empty
		@return true if the LinkedList is empty false if not
	*/
	public boolean isEmpty()
	{
		if(size == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
		How the LinkedList will be displayed once printed
		@return the String representation of the LinkedList
	*/
	public String toString()
	{
		String rVal = "";
		for(ListNode<E> curr = head; curr != null; curr = curr.getNext())
		{
			rVal += curr.toString() + " ";
		}
		return rVal;
	}
	
	/**
		Creates and returns an iterator
		@return Iterator<E>
	*/
	@SuppressWarnings("unchecked")
	public Iterator<E> iterator()
	{
		Iterator<E> iter = new LinkedListIterator<E>(this);
		return iter;
	}
}