import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<E> implements Iterator<E>
{
	/**
		index currently at within the iteration through the LinkedList
	*/
	private int index;
	
	/**
		LinkedList that is being iterated through
	*/
	private LinkedList<E> linkL;
	
	/**
		constructor for the iterator that sets the index to 0 and linkL to the LinkedList parameter
		@param l LinkedList<E> that is being iterated through
	*/
	public LinkedListIterator(LinkedList<E> l)
	{
		index = 0;
		linkL = new LinkedList(l);
	}
	
	/**
		checks if there is another ListNode in the LinkedList linkL
		@return true if the current index is less than the size of linkL and false if not
	*/
	public boolean hasNext()
	{
		if(index < linkL.size())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
		calls hasNext() and depending on return value returns the next value in the LinkedList or throws an exception
		@return the value that is at the next index of type E
	*/
	@SuppressWarnings("unchecked")
	public E next()
	{
		if (hasNext())
		{
			E value = linkL.get(index);
			index++;
			return value;
		}
		else
		{
			throw new NoSuchElementException("There is no value at " + index);
		}
	}
	
	/**
		required to have this method but do not need to implement in this case
	*/
	public void remove()
	{
	}
}