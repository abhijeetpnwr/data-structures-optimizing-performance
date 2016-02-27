package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
    //private String trainedon; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	private String prevworld;
	
	List<String> mywordlist;
	
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		
		Boolean firstchek = false;
		
	    String[] temparr = sourceText.trim().split("\\s+");
	    	
	    	
	    mywordlist = new ArrayList<String>();
	    
	    for (int i = 0; i < temparr.length; i++) 
	    {
	    	    if(temparr[i].trim().length()>0)
	    			{
	    		      mywordlist.add(temparr[i]);
	    			}
			
		}    
	    
	    
	    if(mywordlist.size()>0)
	    {
	   
	    	String firstword = mywordlist.get(0);
	    	    	
	    String lastword = mywordlist.get(mywordlist.size()-1);
	    
	    for(String word : mywordlist)
	    {
	    	
	    	
	    	if(!firstchek)
	    	{
	    		
	    		ListNode tempword = new  ListNode(word);
	    		
	    	
	    		wordList.add(tempword);
	    		
	    		
	    		firstchek = true;
	    	}
	    	
	    	else
	    	{
	    	   // compare if prev. word is already a node or not.
	    		
	    		boolean Listnodechk = false;
	    		
	    		////System.out.println("Word list :"+wordList);
	    		
	    		for(ListNode L_obj:wordList)
	    		{	
	    		   if( prevworld.equals(L_obj.getWord()) )
	    		   {
	    			   //It means node exists already
	    			   L_obj.addNextWord(word);
	    			   Listnodechk=true;
	    			   break;
	    		   }  
	    		}
	    		
	    		if(Listnodechk == false) //Means listnode does't wxist yet
	    		{
	    			ListNode tmp = new ListNode(prevworld);
	    			tmp.addNextWord(word);
	    			wordList.add(tmp);
	    		}	
	    	}
	    	prevworld = word;
	    }

	    
	    // ------------------------------------------ //
	    
	    boolean lastnode = false;
	    
	    //System.out.println("Last word is :"+lastword);
	    
	    // To attach start word with last node
	    for(ListNode L_obj:wordList)
		{	
		   if(lastword.equals(L_obj.getWord()) )
		   {
			   ////System.out.println(" Last node found in wordlist ");
			   //It means node exists already
			   L_obj.addNextWord(firstword);
			   lastnode=true;
			   break;
		   }  
		}
		
		if(lastnode == false) //Means listnode does't wxist yet
		{	
			ListNode tmp = new ListNode(lastword);
			
			tmp.addNextWord(firstword);
			
			wordList.add(tmp);
		}	
	    
	}
	    
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) 
	{
	    // TODO: Implement this method
		
		if(mywordlist==null || mywordlist.size()<=0 || numWords<=0)
		{
			
			return "";
		}
		
		String starter=mywordlist.get(0);
		
		String output=starter;
		
		String wordtoreplace = null;
		
		int noofwords = 1;
		
		while(true)
		{
			
			if(noofwords==numWords)
			{
				break;
			}
			
			for(ListNode l_obj : wordList)
			{
				if(starter.equals(l_obj.getWord()))
				{
					wordtoreplace = l_obj.getRandomNextWord(rnGenerator);
					//System.out.println(" "+starter+" should be replaced by :"+wordtoreplace);
					break;
				}
			}
			
			output = output+"  "+wordtoreplace;
			noofwords++;
			
			starter = wordtoreplace;
		}
		 
		////System.out.println("Genrated output is :"+output);
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
	
		
		// The starting "word"
		starter="";
		
		// The random number generator
		
		prevworld="";
		
		train(sourceText);
		// TODO: Implement this method.
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		
		//String textString = "hi there hi leo";
		////System.out.println(textString);
		gen.train(textString);
		////System.out.println(gen);
		//System.out.println(gen.generateText(20));
		
		//System.out.println();
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		////System.out.println(textString2);
		gen.retrain(textString2);
	
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		 
	     int random =generator.nextInt(((nextWords.size()-1) - 0) + 1);
	     

	     
	    return nextWords.get(random);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) 
		{
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


