package com.domainsearch.index;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author athakur
 * Trie implementation for index
 */
public class Trie { 
	
	// Alphabet size (# of symbols) a-z
	static final int ALPHABET_SIZE = 26; 
	//0-9
	static final int NUMBERS_SIZE = 10;
	//.
	static final int OTHERS = 1;
	
	static int TRIE_CHILD_SIZE = ALPHABET_SIZE + NUMBERS_SIZE + OTHERS;
	// trie node 
	class TrieNode 
	{ 
		TrieNode[] children = new TrieNode[TRIE_CHILD_SIZE]; 
	
		// isEndOfWord is true if the node represents 
		// end of a word 
		boolean isEndOfWord; 
		
		TrieNode(){ 
			isEndOfWord = false; 
			for (int i = 0; i < TRIE_CHILD_SIZE; i++) 
				children[i] = null; 
		} 
	}; 
	
	TrieNode root = new TrieNode();  
	
	// If not present, inserts key into trie 
	// If the key is prefix of trie node, 
	// just marks leaf node 
	void insert(String key) 
	{ 
		
		System.out.println("Inserting key : " + key);
		
		int level; 
		int length = key.length(); 
		int index = 0; 
	
		TrieNode pCrawl = root; 
	
		for (level = 0; level < length; level++) 
		{ 
			
			char charAtLevel = key.charAt(level);
			int charAscii = (int) charAtLevel;
			

			if(charAscii >= 97 && charAscii <= 122) {
				//lower chars
				index = charAscii - 97;
			}
			else if(charAscii >= 48 && charAscii <= 57) {
				//numbers
				index = 26 + (charAscii - 48);
			}
			else if(charAscii == 46) {
				index = 26 + 10;
			}
			
			if (pCrawl.children[index] == null) 
				pCrawl.children[index] = new TrieNode(); 
	
			pCrawl = pCrawl.children[index]; 
		} 
	
		// mark last node as leaf 
		pCrawl.isEndOfWord = true; 
	} 
	
	// Returns true if key presents in trie, else false 
	boolean search(String key) 
	{ 
		int level; 
		int length = key.length(); 
		int index = 0; 
		TrieNode pCrawl = root; 
	
		for (level = 0; level < length; level++) 
		{ 
			
			char charAtLevel = key.charAt(level);
			int charAscii = (int) charAtLevel;
			

			if(charAscii >= 97 && charAscii <= 122) {
				//lower chars
				index = charAscii - 97;
			}
			else if(charAscii >= 48 && charAscii <= 57) {
				//numbers
				index = 26 + (charAscii - 48);
			}
			else if(charAscii == 46) {
				index = 26 + 10;
			}
	
			if (pCrawl.children[index] == null) 
				return false; 
	
			pCrawl = pCrawl.children[index]; 
		} 
	
		return (pCrawl != null && pCrawl.isEndOfWord); 
	} 
	
	
	//get all matched results for autocomplete
	List<String> searchAll(String prefix) 
	{ 
		System.out.println("Searching all for prefix: " + prefix);
		int level; 
		int length = prefix.length(); 
		int index = 0; 
		TrieNode pCrawl = root; 
		
		List<String> result = new ArrayList<String>();
	
		for (level = 0; level < length; level++) 
		{ 
			char charAtLevel = prefix.charAt(level);
			int charAscii = (int) charAtLevel;
			

			if(charAscii >= 97 && charAscii <= 122) {
				//lower chars
				index = charAscii - 97;
			}
			else if(charAscii >= 48 && charAscii <= 57) {
				//numbers
				index = 26 + (charAscii - 48);
			}
			else if(charAscii == 46) {
				index = 26 + 10;
			}
	
			if (pCrawl.children[index] == null) 
				return result; 
	
			pCrawl = pCrawl.children[index]; 
		} 
	
		if(pCrawl == null) {
			return result;
		}
		
		else if(pCrawl.isEndOfWord) {
			result.add(prefix);
			return result;
		}
		
		List<String> results = new ArrayList<>();
		findChildren(pCrawl, prefix, results);
		System.out.println("results : " + results);
		return results;
	}
	
	
	void findChildren(TrieNode pCrawl, String prefix, List<String> results) {
		for (int index = 0; index < TRIE_CHILD_SIZE; index++) {
			if(pCrawl.children[index] == null) {
				continue;
			}
			else if(pCrawl.children[index].isEndOfWord) {
				char c = '.';
				if(index >=0 && index <=25) {
					//chars
					c = (char)(97 + index);
				}
				else if(index >= 26 && index <= 35) {
					//numbers
					c = (char)(48 + index);
				}
				else if(index == 36) {
					//.
					c = (char)(46);
				}
				
				String resultToAdd = prefix + Character.toString(c);
				results.add(resultToAdd);
				findChildren(pCrawl.children[index],prefix + Character.toString(c), results);
			}
			else {
				char c = '.';
				if(index >=0 && index <=25) {
					//chars
					c = (char)(97 + index);
				}
				else if(index >= 26 && index <= 35) {
					//numbers
					c = (char)(48 + index);
				}
				else if(index == 36) {
					//.
					c = (char)(46);
				}
				findChildren(pCrawl.children[index],prefix + Character.toString(c), results);
			}
		}
	}
	
	public static void main(String args[]) {
		Trie trie = new Trie();
		trie.insert("google.com");
		System.out.println(trie.searchAll("go"));
	}
	
	
} 

