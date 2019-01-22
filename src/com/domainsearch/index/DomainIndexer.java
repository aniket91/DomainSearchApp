package com.domainsearch.index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class DomainIndexer {
	
	Trie index = new Trie();
	
	public DomainIndexer() {
		try {
			ClassLoader cl = getClass().getClassLoader();
			File domainsFile = new File(cl.getResource("./top-1m.csv").getFile());
			//System.out.println(domainsFile.exists());
			BufferedReader reader = new BufferedReader(new FileReader(domainsFile));
			String readLine = null;
			while( (readLine = reader.readLine()) != null ) {
				if(readLine.length() > 0 && readLine.contains(",")) {
					index.insert(readLine.toLowerCase().split(",")[1]);
				}
				System.out.println(readLine);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public Object[] getMatchedDomains(String prefix) {
		List<String> matchedResults = index.searchAll(prefix);
		return (Object[]) matchedResults.toArray();
	}
	

}
