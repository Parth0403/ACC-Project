/*
 			ACC PROJECT 
 	SECTION 2 GROUP 6
 	Smitkumar Patel         -> SID: 110073218
	Venkata Sai Akhil Akula -> SID: 110086204
	Sai Prasad Pedapati     -> SID: 110077675
	Parth Rameshbhai Patel  -> SID: 110086151
*/

package src;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler 
{

	//For checking the url validation
	public static boolean urlvalid(String nextUrl) 
	{
		//Checks whether the crawl list has that link or nor
		if(crawledList.contains(nextUrl)) 
			return false;
		
		//Checks whether its html file or something else
		else if (nextUrl.endsWith(".jpeg") || nextUrl.endsWith(".jpg") || nextUrl.endsWith(".png")
				|| nextUrl.endsWith(".pdf") || nextUrl.contains("#") || nextUrl.contains("?")
				|| nextUrl.contains("mailto:") || nextUrl.startsWith("javascript:") || nextUrl.endsWith(".gif")
				||nextUrl.endsWith(".xml"))
			return false;
		else
			return true;
	}
	
	//HashSet is used as it prevents the duplicate value
	public static Set<String> crawledList = new HashSet<String>();
	
	//Currently keeping the depth to 2 as our system would take much more time for more than 2
	public static int max = 2; 
	
	//Initializing a pattern of regex
	public static String regex = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)";
	
	//Creating a crawl method
	public static void crawl(String url, int d) 
	{
		//Creating a Pattern object fom regex
		Pattern ptr = Pattern.compile(regex);
		
		//Crawling the website till max depth
		if (d <= max) 
		{
			try 
			{
				//Getting the url of the web site using rhe Jsoup
				Document doc = Jsoup.connect(url).get();
				
				//Parsing the each and every html pages that crawled from the url
				urlparse.doc(doc, url);
				d++;
				if (d < max) 
				{
					//Adding more inner depth html pages by crawling the web site
					Elements links = doc.select("a[href]");
					for (Element p : links) 
					{
						//Verifying whether its a valid html page or not
						if (urlvalid(p.attr("abs:href")) && ptr.matcher(p.attr("href")).find()) 
						{	
							//Showing the url which crawled
							System.out.println(p.attr("abs:href"));
							crawl(p.attr("abs:href"), d);
							
							//Adding the urls in the crawled list
							crawledList.add(p.attr("abs:href"));
						}
					}
				}
			} 
			//Try to catch the Exception
			catch (Exception ex) 
			{
				
			}
		}
	}
}
