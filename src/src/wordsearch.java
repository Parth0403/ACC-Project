/*
 			ACC PROJECT 
 	SECTION 2 GROUP 6
 	Smitkumar Patel         -> SID: 110073218
	Venkata Sai Akhil Akula -> SID: 110086204
	Sai Prasad Pedapati     -> SID: 110077675
	Parth Rameshbhai Patel  -> SID: 110086151
*/
package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Importing the Resources File
import res.BoyerMooreBM;
import res.editdistance;

public class wordsearch 
{
	//Creating a arraylist and Hash table for storing keys and numbers
	public static ArrayList<String> k = new ArrayList<String>();
	public static HashMap<String, Integer> num = new HashMap<String, Integer>();
	
	public static void autosuggest(String word) 
	{
		String l = " ";
		String reg = "[a-z0-9]+";

		//Creating a Pattern object
		Pattern ptr = Pattern.compile(reg);
		
		//Creating a matcher object.
		Matcher m = ptr.matcher(l);
		int fnum = 0, i = 0;

		//Assigning the path of the text file directory
		File dtr = new File(Path.txtDirectoryPath);
		
		//Making a array of list of text files
		File[] arrf = dtr.listFiles();
		for (int j = 0; j < arrf.length; j++) 
		{
			try 
			{
				//Calling the findword method to find the word
				wordsug(arrf[i], fnum, m, word);
				fnum++;
			} 
			catch (FileNotFoundException ex) 
			{
				
			}
		}

		//To Check whether the edit distance is allowed or not
		Integer dis = 1; 
		
		//To use for the edit distance allowance
		boolean f = false;

		//Taking the map entries to search for the similar words
		for (Map.Entry et : num.entrySet()) 
		{
			//If similar word found then suggesting that words as well
			if (dis == et.getValue()) 
			{	
				i++;	
				if(i==1)
				System.out.println("You Mean this : ");
	
				System.out.print(" -> " + et.getKey() + "\n");
				f = true;
			}
		}
		if (!f)
			System.out.println("No Suggestion ...!!");
	}
	
	//Finding the similar words using the edit distance and suggesting the user
	public static void wordsug(File src, int fnum, Matcher m, String str) throws FileNotFoundException, ArrayIndexOutOfBoundsException 
	{
	try 
	{
		//Initializing the Buffered Reader with the file of comes in the Argument
		BufferedReader br = new BufferedReader(new FileReader(src));
		String len = null;

		while ((len = br.readLine()) != null) 
		{
			//Trying to match the word with the other words
			m.reset(len);
			while (m.find()) 
			{
				k.add(m.group());
			}
		}

		//Calling the Edit distance method for calculating the distance between 2 words..
		for (int p = 0; p < k.size(); p++) 
			num.put(k.get(p), editdistance.ed(str.toLowerCase(), k.get(p).toLowerCase()));
		//Closing the buffered reader
		br.close();			
	} 
		//If any exception generates
		catch (Exception ex) 
		{
			System.out.println("Exception Generated :" + ex);
		}
	}
		
	// Positions and Occurrences of the words
	public static int wordSearch(String info, String w, String fname) 
	{
		int n = 0, num = 0;
		//Calling the boyermoore algorithm method to calculate the number of occurence of the word
		BoyerMooreBM bm = new BoyerMooreBM(w);

		for (int i = 0; i <= info.length(); i += num + w.length()) 
		{
			//Searching the word in the text file using the boyermoore algorithm
			num = bm.search(w, info.substring(i));
			if ((num + i) < info.length()) 
				n++;
		}
		//Shows the Output 
		if (n != 0) 
			System.out.println(fname +" --> Occurences : " + n);																			
		return n;
	}

}
