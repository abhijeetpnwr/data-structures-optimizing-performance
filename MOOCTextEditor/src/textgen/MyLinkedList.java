package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	@SuppressWarnings("unchecked")
	public MyLinkedList() 
	{
		//Empty Link list created with two sentinal nodes
		
	    E e = null;
	     
		head = new LLNode<E>(null);
		
	    tail = new LLNode<E>(null);
	    
	    head.next=tail;
	    
	    tail.prev=head;
	    
		// TODO: Implement this method
	
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		
		if(element == null)
		{
			throw  new NullPointerException();
		}
		
		
		LLNode<E> node = new LLNode<E>(element);
	
		LLNode<E> startnode = head;
		
		if(size==0)
		{
			
			startnode.next=node;
			tail.prev=node;
			
			
			node.prev = head;
		    node.next=tail;	
		}
		
		else
		{
	        while(startnode.next != tail)
			{
				startnode=startnode.next;
			}
	        
	        startnode.next = node;
	        node.next=tail;
	        node.prev=startnode;
	        tail.prev=node;
		}
		
		size++;
		
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	
	public E get(int index) 
	{
		
		
		LLNode<E> nextnode = head;
		int count = -1;
		
		// TODO: Implement this method.
		if(index<0 || index>=size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		while(index != count)
		{
			nextnode = nextnode.next;
			count++;
		}
		return nextnode.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		
		if(element == null)
		{
			throw  new NullPointerException();
		}
		
		
		// TODO: Implement this method
		LLNode<E> node = new LLNode<E>(element);
		
		LLNode<E> nextnode = new LLNode<E>(element);
		
		LLNode<E> currentnode = head;
		int count = -1;
		
		// TODO: Implement this method.
		if(index<0 || index>size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		while(count < index-1)
		{
			currentnode = currentnode.next;
			count++;
		}
		
        		
		nextnode = currentnode.next;
		
		currentnode.next=node;
		node.prev=currentnode;
		
		node.next=nextnode;
		nextnode.prev=node;

		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index<0 || index>size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		// TODO: Implement this method

		LLNode<E> curr = head;
		
		int elemcount = 0;
			
		while(elemcount<index)
		{
			curr = curr.next;
			elemcount++;
		}
		
		E e = curr.next.data;
		
		LLNode<E> newnextnode = curr.next.next;
		
		curr.next=newnextnode;
		newnextnode.prev=curr;
		
		size--;
		
		return e;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		LLNode<E> curr = head;
		
		if(index<0 || index>=size)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(element == null)
		{
			throw new NullPointerException();
		}
		
		int elemcount = -1;
		
		while(elemcount<index-1)
		{
			curr = curr.next;
			elemcount++;
		}
		
		LLNode<E> nodetoreplace = curr.next;
		
	    E e=nodetoreplace.data;
	    
	    nodetoreplace.data=element;
		
		return e;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
