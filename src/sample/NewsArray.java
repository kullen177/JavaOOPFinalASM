/*
RMIT University Vietnam
INTE2512 Final Project - News Aggregator App
Nguyen Anh Minh
S3911237
 */

package sample;

import java.util.ArrayList;

import org.json.JSONObject;

public class NewsArray
{
    String title;
    String image;
    String desc;
    String hyperl; //vandita all the time
    
    @Override
    public String toString()
    {
        String str=new String();
        str+="Title : "+this.title+"\n";
        str+="Description : "+this.desc+"\n";
        return str;
    }
    
    public static ArrayList<NewsArray> convert(ArrayList<JSONObject> arr)
    {
        ArrayList<NewsArray> response=new ArrayList<NewsArray>();
        for(int i=0;i<arr.size();i++)
        {
            NewsArray news=new NewsArray();
            news.desc=arr.get(i).get("description").toString();
            news.title=arr.get(i).getString("title");
            news.image=arr.get(i).get("urlToImage").toString();
            news.hyperl=arr.get(i).getString("url");
            response.add((NewsArray) news);
        }
        return response;
    }
}
