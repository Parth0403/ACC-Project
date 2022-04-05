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

public class deletef 
{
	//Delete the text files from  the text folder after searching the word
	public static void deleteFiles() 
	{
		//File initialized from the path 
		File f = new File(Path.txtDirectoryPath);
	
		//Array of List of file is created
		File[] filearr = f.listFiles();
		
		//One by one the files are deleted from the folder
		for (int i = 0; i < filearr.length; i++) 
			filearr[i].delete();
		
		//html files initialized from the Path
		File htmlf= new File(Path.htmlDirectoryPath);

		//Array of list of html file is created
		File[] filearrh = htmlf.listFiles();

		//One by one the files are deleted from the folder
		for (int i = 0; i < filearrh.length; i++) 
			filearrh[i].delete();
	}
}
