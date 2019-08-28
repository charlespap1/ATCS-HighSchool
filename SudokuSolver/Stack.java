public interface Stack<E>
{
	/**
		abstract method for adding a value to the stack
		@param item the value being added
	*/
	public void push(E item);

	/**
		abstract method for removing the top(last) value from stack
		@return the value that was removed of type E
	*/
	public E pop();

	/**
		abstract method for getting or viewing the top value from stack
		@return the value that is being viewed
	*/
	public E peek();

	/**
		abstract method for checking if the stack is empty
		@return true if the stack is empty false if not
	*/
	public boolean isEmpty();
}