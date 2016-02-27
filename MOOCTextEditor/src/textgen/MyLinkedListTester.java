/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) 
		{
		     System.out.println("Correct. Index out of bound was expected");	
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Correct. Index out of bound was expected");
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("Correct. out of bound was expected");
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		try {
			list1.remove(-5);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e)
		{
		}	
		try
		{
			list1.remove(50);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		
		
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		
		MyLinkedList<String> testatend = new MyLinkedList<String>();
		
		try {
		       testatend.add(null);
			   fail("It should throw null pointer exception");
		}
		catch (NullPointerException e) 
		{
		   //  System.out.println("Correct. Index ou of bound was expected");	
		}
		
		testatend.add("a");
		
		System.out.println("List :"+testatend);
		assertEquals("testing last add func","a",testatend.get(testatend.size-1));
		
		testatend.add("b");
		assertEquals("testing last add func","b",testatend.get(testatend.size-1));
			
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		
		MyLinkedList<String> sizetest_list=new MyLinkedList<String>();
		
		assertEquals("size check",0,sizetest_list.size);
		
		sizetest_list.add("A");
		sizetest_list.add("B");
		
		assertEquals("size check after add",2,sizetest_list.size);
		
		sizetest_list.add(2,"D");
		assertEquals("afer add in middle", 3, sizetest_list.size);
		
		sizetest_list.remove(2);
		assertEquals("after removing",2,sizetest_list.size);
			
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		
		try {
			list1.add(-1,50);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) 
		{
		     System.out.println("Correct. Index out of bound was expected");	
		}
		
		try {
			list1.add(10,12);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) 
		{
		     System.out.println("Correct. Index out of bound was expected");	
		}
		
		
		try {
		       list1.add(2,null);
			   fail("It should throw null pointer exception");
		}
		
		catch (NullPointerException e) 
		{
		   //  System.out.println("Correct. Index ou of bound was expected");	
		}
	
		try {
		       list1.add(15,3);
			   //fail("It should throw null pointer exception");
		}
		catch (IndexOutOfBoundsException e) 
		{
		   //  System.out.println("Correct. Index ou of bound was expected");	
		}
		
		
		list1.add(2, 100);
        // TODO: implement this test
		assertEquals("2nd element check", (Integer)100 , list1.get(2));
		
		list1.add(2,25);
		assertEquals("2nd elem . again after one more add. ", (Integer)25, list1.get(2));
		assertEquals("3nd elem . again after one more add. ", (Integer)100, list1.get(3));
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		
		
	    // TODO: implement this test
		MyLinkedList<String> settest_list=new MyLinkedList<String>();
		
		

		
		
		try {
		       settest_list.set(-2,"test");
			   fail("It should throw  index out of bound exception");
		}
		catch (IndexOutOfBoundsException e) 
		{
		   //  System.out.println("Correct. Index ou of bound was expected");	
		}
		
		
		try {
		       settest_list.set(50,"test");
			   fail("It should throw  index out of bound exception");
		}
		catch (IndexOutOfBoundsException e) 
		{
		   //  System.out.println("Correct. Index ou of bound was expected");	
		}
		
		
		settest_list.add("A");
		settest_list.add("B");
		settest_list.add("D");
		
		System.out.println(settest_list);
		
		settest_list.set(2,"C");
		System.out.println(settest_list);
		
		
		assertEquals("check",settest_list.get(2),"C");
		
		
		try {
		       settest_list.set(2,null);
			   fail("It should throw null pointer exception");
		}
		catch (NullPointerException e) 
		{
		   //  System.out.println("Correct. Index ou of bound was expected");	
		}
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
