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
import java.io.FileWriter;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class urlparse 
{

	//Creating a method to save the html files
	public static void doc(Document dm, String url) 
	{
		try 
		{
			//Initializing the PrintWriter for writing the html text into text file
			PrintWriter pw = new PrintWriter(new FileWriter(Path.htmlDirectoryPath + dm.title().replace('/', '_') + ".html"));
			pw.write(dm.toString());
			
			//Calling the html2text method to convert the html file into text file using jsoup
			html2text(Path.htmlDirectoryPath + dm.title().replace('/', '_') + ".html", url,dm.title().replace('/', '_') + ".txt");
			
			//Closing the PrintWriter object
			pw.close();

		} 
		//If any exception occurs
		catch (Exception ex) 
		{
			
		}

	}

	//Creating a method to convert the html file into the text file and store in the text folder
	public static void html2text(String fhtml, String url, String fname) throws Exception 
	{
		//Initializing the html files in the new temporary file
		File f = new File(fhtml);
		
		//Jsoup parsing the html file
		Document doc = Jsoup.parse(f, "UTF-8");

		//Adding that into string 
		String info = doc.text().toLowerCase();
		info = url + "::" + info;
		
		//Writing the text into the text file
		PrintWriter pw = new PrintWriter(Path.txtDirectoryPath + fname);
		pw.println(info);
		
		//Closing the print writer object
		pw.close();
	}
}
