package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.print.StreamPrintService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    private TrieNode curr;
    
    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		
		if(word.trim().length()<=0)
		{
			return false;
		}
		
		curr = root; //In start  curent node is always root
		
		String wordinlower = word.toLowerCase();
		
		char[] charar =  wordinlower.toCharArray();

		for(int charstat = 0 ; charstat<charar.length;charstat++)
		{
			if(curr.getChild(charar[charstat])==null)
			{
				curr = curr.insert(charar[charstat]);
			}
			else
			{
				curr = curr.getChild(charar[charstat]);
			}
			
			if(charstat== charar.length-1)
			{
				curr.setEndsWord(true);
			}
		}
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		
		Queue<TrieNode> restnodes = new LinkedList<>(); 
		
		int size= 0 ;
		
		for(char init:root.getValidNextCharacters())
		{
			
			if(root.getChild(init).endsWord())
			{
				size++;
			}
			
			restnodes.add(root.getChild(init));
	       	 
	       	 while(true)
	       	 {
	       		TrieNode currtrienode = restnodes.peek(); 
	       		Set<Character> nextchars = currtrienode.getValidNextCharacters();
	   			
	       	
	       		
	   			for(Character c : nextchars)
	   			{
	   				TrieNode nodetoadd = currtrienode.getChild(c);
	   			    
	   				
	   				
	   				if(nodetoadd.endsWord())
	   				{
	   					System.out.println(" word found :"+nodetoadd.getText());
	   					size++;
	   				}
	   				
	   				else
	   				{
	   					System.out.println(" Non word :"+nodetoadd.getText());
	   				}
	   				
	   				
	   				restnodes.add(currtrienode.getChild(c));
	   			}	
	   			restnodes.poll(); //Remove current node
	   			
	   			if(restnodes.isEmpty())
	   			{
	   				break;
	   			}
	       	 }
		}
		
		System.out.println("size to return :"+size);
		
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		
		if(Findstemnode(s)==null)
		{
			return false;
		}
		
		 return Findstemnode(s).endsWord();
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 
    	 System.out.println("checking for :"+prefix);	
    	 
    	 List<String> probablewords = new ArrayList<String>();
    	 
    	 
    	 if(numCompletions<=0)
    	 {
    		 return probablewords; 
    	 }
    	 
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    
    	 TrieNode currtrienode;
    	 
   	 
    	 int totalwords = 0;
    	 
    	 // --- Code to find stem in trie ---
    	 
    	 curr = root; //In start current node is root
       	 
       	 Queue<TrieNode> nodequeue = new LinkedList<>();
       	 
       	 nodequeue.add(curr);
       	 
       	 TrieNode stemonode = null;
       	 
       	 boolean stemnodefound = false;
       	 
       	 while(nodequeue.size()!=0 && stemnodefound == false)
       	 {
       		    currtrienode = nodequeue.peek();
       		
       			if(stemnodefound == true)
       			{
       				break;
       			}
       			
       			Set<Character> nextchars = currtrienode.getValidNextCharacters();
       			
       			for(Character c : nextchars)
       			{
       				TrieNode nodetoadd = currtrienode.getChild(c);
       					
       				if(nodetoadd.getText().equalsIgnoreCase(prefix))
       				{
       					stemonode = nodetoadd;
       					
       					stemnodefound = true;
       					
       					System.out.println("Stem node found for :"+prefix);
       					
       					break;
       				}
       				
       				nodequeue.add(currtrienode.getChild(c));
       				
       			}		
       			
       			nodequeue.remove();
       		
       	 }
    	 
    	 // ----------------------------- //
    	 
    	
       	 // ------------------------------- Now we need to do breadth first search for other words -----------//
       	
       	 
       	 if(stemnodefound || prefix.trim().length()==0)
       	 {
       	 
       		 
       		 
       		 
       	System.out.println("checking for  --- :"+prefix);	 
       	 
   	     Queue<TrieNode> restnodes = new LinkedList<>(); 
       	 
   	     
   	     if(stemnodefound)
   	     {
   	     curr = stemonode; //Curent node assigned to stem node
   	     }
   	     else
   	     {
   	    	 stemonode = root;
   	     }
   	     
   	     
       	 restnodes.add(stemonode);
       	 
       	 
       	 if(stemonode.endsWord())
       	 {
       		 probablewords.add(stemonode.getText());
       		 totalwords++;
       	 }
       	 
       	 while(!restnodes.isEmpty() && totalwords!=numCompletions)
       	 {
       		 currtrienode = restnodes.peek();
       		 
       		 
       		 
       		Set<Character> nextchars = currtrienode.getValidNextCharacters();
   			
   			for(Character c : nextchars)
   			{
   				if(totalwords == numCompletions)
   				{
   					break;
   				}
   				
   				TrieNode nodetoadd = currtrienode.getChild(c);
   				
   				if(nodetoadd.endsWord())
   				{
   				  probablewords.add(nodetoadd.getText());	
     			    totalwords++;
   				}
   			  
   				restnodes.add(currtrienode.getChild(c));
   			}	
   			restnodes.poll(); //Remove current node
       	 }
       	 
       	 System.out.println("Probable words are:"+probablewords);
       	 
       	 // --------------------------------------------------------------------------------------------------//
         //return probablewords;
       	}
       	 
       	 return probablewords;
     }
     
     
    public boolean Findstem(String st) 
    {
		return(Findstemnode(st)!=null);
	} 
     
     
    public TrieNode Findstemnode(String prefix)
    {
   	 
   	 curr = root; //In start current node is root
   	 
   	 Queue<TrieNode> nodequeue = new LinkedList<>();
   	 
   	 nodequeue.add(curr);
   	 
   	 TrieNode stemonode = null;
   	 
   	 boolean stemnodefound = false;
   	 
   	 while(nodequeue.size()!=0 && stemnodefound == false)
   	 {
   		   TrieNode currtrienode = nodequeue.peek();
   		
   			if(stemnodefound == true)
   			{
   				break;
   			}
   			
   			Set<Character> nextchars = currtrienode.getValidNextCharacters();
   			
   			for(Character c : nextchars)
   			{
   				TrieNode nodetoadd = currtrienode.getChild(c);
   					
   				if(nodetoadd.getText().equalsIgnoreCase(prefix))
   				{
   					stemonode = nodetoadd;
   					
   					stemnodefound = true;
   					
   					System.out.println("Stem node found for :"+prefix);
   					
   					break;
   				}
   				
   				nodequeue.add(currtrienode.getChild(c));
   				
   			}		
   			
   			nodequeue.remove();
   		
   	 }
   	 
   	return stemonode;
   	 
    }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}