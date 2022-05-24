/*
RMIT University Vietnam
INTE2512 Final Project - News Aggregator App
Nguyen Anh Minh
S3911237
 */
package sample;

import java.io.UnsupportedEncodingException;
import java.util.Queue;
public class ParameterStringBuilder {
    public static String getParamsString(Queue<String> q) 
      throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        
        while(q.size()!=0)
        {
            result.append(q.remove());
            result.append("=");
            result.append(q.remove());
            result.append("&");
        }
        
        String resultString = result.toString();
        if(resultString.length()>0)
        {
            resultString=resultString.substring(0,resultString.length()-1);
            System.out.println(resultString);
            return resultString;
        }
        else
        {
            return resultString;
        }
    }
}
