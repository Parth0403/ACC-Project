/*
 			ACC PROJECT 
 	SECTION 2 GROUP 6
 	Smitkumar Patel         -> SID: 110073218
	Venkata Sai Akhil Akula -> SID: 110086204
	Sai Prasad Pedapati     -> SID: 110077675
	Parth Rameshbhai Patel  -> SID: 110086151
*/
package src;

import java.net.HttpURLConnection;
import java.net.URL;

public class valid 
{
	public static boolean urlvalid(String url)
    {
        /* Try creating a valid URL */
        try 
        {
        	
        	System.out.println("Checking the URL whether it's valid or not...");
        	
        	//Creating the object of the URL
        	URL urlobj = new URL(url);
        	
        	//Creating a HTTP URL connections
            HttpURLConnection httpcon = (HttpURLConnection) urlobj.openConnection();
            
            //Sending the request Method
            httpcon.setRequestMethod("GET");
            
            //Taking the response from the httpcon
            int res = httpcon.getResponseCode();
            
            //If the response code is 200 then url is valid
            if(res == 200) 
            	 return true;
            else 
            	return false;
        }
          
        // Catches exceptions if any exceptions occurs
        catch (Exception ex) 
        {
        	System.out.println("Exception Generated : " + ex);
            return false;
        }
    }
}
