public class ListNode<E>
{
	/**
		pointer or reference to next ListNode<E>
	*/
	private ListNode<E> next;
	
	/**
		value stored in current ListNode<E>
	*/
	private E val;
	
	/**
		constructor initializing val and next both to null
	*/
	public ListNode()
	{
		val = null;
		next = null;
	}
	
	/**
		constructor initializing val and next to the passed in values for each
		@param item value for val
		@param n reference to next ListNode
	*/
	public ListNode(E item, ListNode<E> n)
	{
		val = item;
		next = n;
	}
	
	/**
		constructor initializing val to passed in value and next to null
		@param item value for val
	*/
	public ListNode(E item)
	{
		val = item;
		next = null;
	}
	
	/**
		sets val to passed in value
		@param v value for val
	*/
	public void setValue(E v)
	{
		val = v;
	}
	
	/**
		sets next to passed in reference
		@param n reference to next ListNode that the next field is being set to
	*/
	public void setNext(ListNode<E> n)
	{
		next = n;
	}
	
	/**
		gets the value stored in val
		@return the value stored in the current ListNode of type E
	*/
	public E getValue()
	{
		return val;
	}
	
	/**
		gets the reference stored in next
		@return the reference stored in the current ListNode of type ListNode<E>
	*/
	public ListNode<E> getNext()
	{
		return next;
	}
	
	/**
		How the ListNode will be displayed once printed
		@return the String representation of the ListNode
	*/
	public String toString()
	{
		return "[" + val + "]";
	}
}