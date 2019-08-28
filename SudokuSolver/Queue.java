public interface Queue<E>
{
	/**
		abstract method for adding a value to the queue
		@param item the value being added
	*/
	public void offer(E item);

	/**
		abstract method for removing the end value(last) from queue
		@return the value that was removed of type E
	*/
	public E poll();
	
	/**
		abstract method for getting or viewing the top value from queue
		@return the value that is being viewed
	*/
	public E peek();
	
	/**
		abstract method for checking if the queue is empty
		@return true if the queue is empty false if not
	*/
	public boolean isEmpty();
}