/*
 			ACC PROJECT 
 	SECTION 2 GROUP 6
 	Smitkumar Patel         -> SID: 110073218
	Venkata Sai Akhil Akula -> SID: 110086204
	Sai Prasad Pedapati     -> SID: 110077675
	Parth Rameshbhai Patel  -> SID: 110086151
*/
package src;

import java.io.File;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.regex.Pattern;

import res.In;

public class SearchEngine 
{
	//Initializing the scanner method and creating it's object
	private static Scanner sc = new Scanner(System.in);
	
	//---------- Main Function ----------
	public static void main(String[] args) 
	{
		System.out.println("             Search Engine             ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
		System.out.println("             Team Members :            ");
		System.out.println("Smitkumar Patel         -> SID: 110073218");
		System.out.println("Venkata Sai Akhil Akula -> SID: 110086204");
		System.out.println("Sai Prasad Pedapati     -> SID: 110077675");
		System.out.println("Parth Rameshbhai Patel  -> SID: 110086151\n");

		System.out.println(" a.) Type 1 for entering URL");
		System.out.println(" b.) Type 2 to Quit ");
		System.out.println("By Default, it will use 'https://www.infoplease.com/'...");
		System.out.println("Enter your Option : ");
		
		//Assigning the integer to take input of option picked by user
		int op = sc.nextInt();

		//Using the Switch case
		switch (op) 
		{
			//For case '1'
			case 1:
				System.out.println("Enter complete URL(including https://) : ");
				//Taking input of url
				String url = sc.next();
				
				//Passing the url for the next process
				src.SearchEngine.searchWord(url);
				break;

			//For Case '2'
			case 2:
				System.out.println("QUITING...");
				break;

			default:
				//If invalid input then the default url is considered
				url = "https://www.infoplease.com/";
				
				//Passing the url for the next process
				src.SearchEngine.searchWord(url);
				break;
		}
	}

	//------- searchWord Method -------
	public static void searchWord(String URL) 
	{	
		//Using the Hashtable as it allows the null value
		Hashtable<String, Integer> fileht = new Hashtable<String, Integer>();
				
		try 
		{	
			//Checking whether the URL is valid or not
			if(!valid.urlvalid(URL)) 
			{
				//If URL is not valid
				 System.out.println("The URL is not valid at all..");
				 System.out.println("You can run it again....\n");
			}
		} 
		//If any exception occurs
		catch (Exception ex) 
		{
			
		}
		
		//Initialized to calculate the Time
		long stime = 0, etime = 0, ttime = 0;
		
		System.out.println("Crawling Started...!!");
		
		//Crawling of the URL is started here..
		
		//Time Starts
		stime = System.currentTimeMillis();
		WebCrawler.crawl(URL, 0); 	
		
		//Time Ends
		etime = System.currentTimeMillis();
		System.out.println("Crawling Done...!!");
		
		//Calculating the actual crawling time
		ttime = etime - stime;
		System.out.println("Total Time Taken for Web Crawling : " + ttime + " milli seconds.");
		
		String op = "yes";
		//Using do..while
		do {
			System.out.println("\nEnter a word : ");
			//Taking the input of word to find
			String findword = sc.next();
			
			int wordf =0, tfile = 0;
			//Clearing the hashtable before using it for further operations
			fileht.clear();
			
			try 
			{
				System.out.println("\nSearching Started. Please Wait...!!");
				//Calling the file directory from its path
				File f = new File(Path.txtDirectoryPath);

				//Making a list of files in the form of Array
				File[] filearr = f.listFiles();

				for (int i = 0; i < filearr.length; i++) 
				{
					//Taking a input of a files one by one
					In inpf = new In(filearr[i].getAbsolutePath());
					
					//Reading all the lines from the text file
					String all = inpf.readAll();
					
					//Trying to find a pattern
					Pattern p = Pattern.compile("::");
					
					//Spliting the .txt from its name for displaying in the terminal
					String[] fname = p.split(all);
					
					//Passing everything to search word from the text file
					wordf = wordsearch.wordSearch(all, findword.toLowerCase(), fname[0]);
					
					//if word found at anywhere 
					if (wordf != 0) 
					{	
						//Then it is put in the hashtable with the file name
						fileht.put(fname[0], wordf);
						
						//Pointer is incremented
						tfile++;
					}
					//Closing the in method object
					inpf.close();
					
				}
				//If word found then shows from how many files contains that word
				if(tfile > 0) 
					System.out.println("\nTotal Number of Files containing word is : " + tfile);
				else 
					System.out.println("\n File not found! containing word : "+ findword);
				
				//Shows the AutoSuggestion of the words
				wordsearch.autosuggest(findword.toLowerCase());
			} 
			//If any exceptions generated
			catch (Exception ex) 
			{
				System.out.println("Exception Generated :" + ex);
			}
			System.out.println("\n Do you want return to search another word(yes/no)?");
			op = sc.next();
		} while (op.equalsIgnoreCase("yes"));
		
		//Delete method is called to delete the files of the web crawler
		deletef.deleteFiles();
	}
}
