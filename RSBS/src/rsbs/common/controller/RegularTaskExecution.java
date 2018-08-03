package common.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.conf.ConfigurationBuilder;

import common.services.CommonServices;

import admin.model.AdminModel;

@Configuration
@EnableScheduling
public class RegularTaskExecution{
	@Autowired
	private CommonServices commonServices;

	public CommonServices getCommonServices() {
		return commonServices;
	}

	public void setCommonServices(CommonServices commonServices) {
		this.commonServices = commonServices;
	}
	
//Code===0ba4a346743c4b2ea6b4b16d00a4f651
	/*@PostConstruct
	public void startupCelebrity_list() throws Exception{
		//getAccessToken();
		//getCelebrity_list();
	}*/
	//EAAZAsexLSRHoBAACCTaEEhKzGGFMhZBbdC830GZCVnZCzyuD12ncjDsZBb9j3pOa2Vf5KbqaQIl2SN5UFCOIayt61qq9ZAm81nt9ZBJNZADbSKOYusFILEfl9ZBPPXmA26UNygcH8MWGcXtqnfRuf8LUvtBhYeS6mUsIZD
	//@Scheduled(fixedDelay = 1000 * 60 * 150, initialDelay = 300000)
	//@Scheduled(fixedDelay = 1000 * 60 * 60)

	public void getCelebrity_list() throws Exception
	{
		List<AdminModel> celebrity_info=getCommonServices().getCelebrityInfo();
		
		extract_celebrity_info(celebrity_info);
	}
	public List<String> getBlacklist_website()
	{
		String web_name="";
		List<AdminModel> website_name=getCommonServices().getBlacklistWebsites();
		List<String> blacklist_website=new ArrayList<>();
		for(AdminModel a:website_name)
		{
			web_name=a.getWebsite_name();
			blacklist_website.add(web_name);
		}
		return blacklist_website;
	}
	
	public void extract_celebrity_info(List<AdminModel> celebrity_info)
	{
		List<String> blacklist_website=getBlacklist_website();
		for(AdminModel ad:celebrity_info)
		{
			getFacebookFeeds(ad); 
			//getInstgramFeeds(ad);
			getTwitterFeeds(ad);
			getCelebrity_news(ad,blacklist_website);
		}
		celebrity_info.clear();
		blacklist_website.clear();
	}
	public String hitURL(String url) throws Exception
	{
	    String result="";   
		BufferedReader in = null;
		        try {
		        	URL news=new URL(url);
		    	    URLConnection myURLConnection = news.openConnection();
		    	    myURLConnection.connect();
		    		in = new BufferedReader(new InputStreamReader(news.openStream()));
		    		        String inputLine="";
		    		        String data="";
		    		        while ((data = in.readLine()) != null)
		    		        {
		    		        	inputLine=inputLine+data;
		    		        }
		    		        result =inputLine;

		        } catch (Exception ex) {
		        } finally {
		            if (in != null) {
		                try {
		                    in.close();
		                } catch (IOException e) {
		                }
		            } 
		        }
		return result;
	}
	public void getCelebrity_news(AdminModel ad,List<String> blacklist_website)
	{
		String celebrity_name=ad.getCelebrity_name();
		try{
			String query=celebrity_name.replace(" ", "+");
			//query="\""+query+"\"";
			//https://newsapi.org/v2/everything?Language=en&domains=breitbart.com,bloomberg.com,wsj.com,apnews.com,washingtonpost.com,foxnews.com,cnn.com,thehill.com&q=donald%20trump&apiKey=43cceeca5c384d1daaf15c95475b5766
			//String feed_url="https://newsapi.org/v2/everything?Language=en&q="+query+"&apiKey=9ccdaece65b740ebbd3754cd3fb685de"; //15e4a082d13348e58b47f4330dc5e4e7
			String feed_url="https://newsapi.org/v2/everything?Language=en&q=\""+query+"\"&domains=breitbart.com,bloomberg.com,wsj.com,apnews.com,washingtonpost.com,foxnews.com,cnn.com,thehill.com&apiKey=23f9b7d847364efc88a86fa973c198fc&sortBy=publishedAt"; //43cceeca5c384d1daaf15c95475b5766  9ccdaece65b740ebbd3754cd3fb685de  15e4a082d13348e58b47f4330dc5e4e7
			String inputLine=hitURL(feed_url);
			JSONParser parser = new JSONParser();
			try{
				Object obj=parser.parse(inputLine);
				JSONObject json=(JSONObject)obj;
				JSONArray post_data=(JSONArray)json.get("articles");
				int cnt=post_data.size();
				for(int i=0;i<cnt;i++)
				{
					 JSONObject json1=(JSONObject)post_data.get(i);
		        		//boolean isConteins=false;
			        	 String source_url="";
		        		//=json1.get("source")+"";

			        	 @SuppressWarnings("rawtypes")
						Map source_url_obj = ((Map)json1.get("source"));
			             
			             // iterating address Map
			             @SuppressWarnings({ "rawtypes", "unchecked" })
						Iterator<Map.Entry> itr1 = source_url_obj.entrySet().iterator();
			             while (itr1.hasNext()) {
			                 @SuppressWarnings("rawtypes")
							Map.Entry pair = itr1.next();
			                 if(pair.getKey().equals("name")){
			                	 source_url=pair.getValue()+"";
			                 }
			             }
			            String url=json1.get("url").toString();
		            /*	for(String str:blacklist_website)
		            	{
		            		isConteins=url.toLowerCase().contains(str.toLowerCase());
		            		if(isConteins){
		            			break;
		            		}
		            	}*/
		            /*	if(!isConteins)
		            	{*/						        	 
		            		 String title=json1.get("title").toString();
				        	 String description=json1.get("description").toString();
				        	 String img_url=json1.get("urlToImage").toString();
				        	System.out.println("before===="+img_url);
				        	 if(img_url.contains("https") && url.contains("https")){
				        	//	 img_url=img_url.replace("http","https");
				        		 
				 //       	 }
		            		 String publishedAt=json1.get("publishedAt").toString();
		            		 publishedAt=publishedAt.replace("T", " ");
		            		 publishedAt=publishedAt.replace("Z", "");
				        	 
		            		ad.setNews_title(title);
			            	ad.setPublication_date(publishedAt);
			            	ad.setNews_description_url(url);
			            	ad.setNews_description(description);
			            	ad.setCelebrity_id(ad.getCelebrity_id());
			            	ad.setImage_url(img_url);
			            	ad.setNews_source(source_url);
			            	
			            	int is_news_present=0;
			            	String titleRepeat=title.replace("'", "\'");
			           // 	String descriptionRepeat=description.replace("'", "\'");
			            	//String news_source="%"+ad.getNews_description_url()+"%";
			            	//System.out.println("titlerepeat===="+titleRepeat);
			            	List<AdminModel> news_id=getCommonServices().getNewsId(titleRepeat,img_url);
			            	//System.out.println("news_id"+news_id.size());
			            	if(news_id.size()>0){
			            		//System.out.println("inside");
			            		is_news_present=1;
			            	/*for(AdminModel a:news_id)
			            	{
			            		is_news_present=a.getNews_id();
			            	}*/
			            	}
			        		if(is_news_present==0)
			        		{
			        		//	System.out.println("in insert");
			        			getCommonServices().addCelebrityNews(ad);
			        		}
				}
		            //	}
		            	json1.clear();
				}
				json.clear();
				post_data.clear();
			}catch(Exception e)
			{
				System.out.println("Issue in news api");
			}
			    
		}catch(Exception e)
		{
		}
	}
	
	public int getMonthByName(String month)
	{
		int month_no=0;
		String[] month_name={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		for(int i=0;i<month_name.length;i++)
		{
			if(month.equalsIgnoreCase(month_name[i]))
			{
				month_no=i+1;
				return month_no;
			}
		}
		return month_no;
	}
	public String convertPublicationDate(String pub_date)
	{
		String final_date="";
		if(pub_date !=null &&!pub_date.equals(""))
		{
			String day_date=pub_date;
			String[] date_array=day_date.split(",");
			String split_date=date_array[1].trim();
			String[] single_data=split_date.split(" ");
			int month_no=getMonthByName(single_data[1]);
			DecimalFormat formatter = new DecimalFormat("00");
			String month = formatter.format(month_no);

			if(single_data.length==5)
			{
				final_date=single_data[2]+"-"+month+"-"+single_data[0]+" "+single_data[3];
			}
				
		}
		return final_date;
	}
	public void getInstgramFeeds(AdminModel ad)
	{
	//	System.out.println("inside");
		Date d=new Date();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created_time=df.format(d);
		if(ad.getInstagram_page()!=null && !ad.getInstagram_page().equals(""))
		{
			String url="https://www.instagram.com/"+ad.getInstagram_page()+"/?__a=1";
			try{
				String insta_result=hitURL(url);
				JSONParser parser=new JSONParser();
				Object obj=parser.parse(insta_result);
				
/*				JSONObject main_obj=(JSONObject) obj;
*/				
				JSONObject main_obj=(JSONObject) obj;
				JSONObject main_obj1=(JSONObject) main_obj.get("graphql");
				JSONObject user_obj=(JSONObject) main_obj1.get("user");
				JSONObject media_obj=(JSONObject) user_obj.get("edge_owner_to_timeline_media");
				JSONArray node_arr=(JSONArray) media_obj.get("edges");
				/*JSONObject media_obj=(JSONObject) user_obj.get("media");
				JSONArray node_arr=(JSONArray) media_obj.get("nodes");*/
			
				String message="";
				String image_url="";
				String insta_id="";
				String message1="";
				for(int i=0;i<node_arr.size();i++)
				{
					//JSONObject node=(JSONObject) node_arr.get(i);
					JSONObject node1=(JSONObject) node_arr.get(i);
					JSONObject node=(JSONObject) node1.get("node");
                    insta_id=(String) node.get("id");
					//image_url=(String) node.get("display_src");
					image_url=(String) node.get("display_url");
					JSONObject edge_media_to_caption=(JSONObject) node.get("edge_media_to_caption");
					JSONArray edges1=(JSONArray) edge_media_to_caption.get("edges");
					for(int j=0;j<edges1.size();j++){
					JSONObject node2=(JSONObject) edges1.get(j);
					JSONObject node3=(JSONObject) node2.get("node");
					message=(String) node3.get("text");
					message1=message.replace("'", "\'");
					node2.clear();
					node3.clear();
				}
/*					message=(String) node.get("caption");
*/					
					
					boolean res=isDuplicateFeed(insta_id+"","Instagram",message1,image_url);
							if(res==false)
							{
								ad.setPost_source("Instagram");
								ad.setImage_url(image_url);
								message=message.replace("?", "");
								ad.setMessage(message);
								ad.setCreated_time(created_time);
								ad.setId(insta_id);
								SaveSocialMediaFeeds(ad);
							}
							node1.clear();
							node.clear();
							edges1.clear();
							
				}
				main_obj.clear();
				user_obj.clear();
				media_obj.clear();
				node_arr.clear();
			}catch(Exception e){System.out.println();}
			
		}
	}
	public int SaveSocialMediaFeeds(AdminModel insta_data)
	{
		int affected_rows=0;
		affected_rows=getCommonServices().addCelebrityPost(insta_data);
		
		return affected_rows;
	}
	public String getTwitterDate(String date)
	{
		String created_date="";
		String[] temp=date.split(" ");
		//2017-10-24 16:01:28
		String year=temp[5];
		int month_no=getMonthByName(temp[1]);
		String date1=temp[2];
		String time=temp[3];
		DecimalFormat formatter = new DecimalFormat("00");
		String month = formatter.format(month_no);
		
		created_date=year+"-"+month+"-"+date1+" "+time;
		
		return created_date;
	}
	public boolean isDuplicateFeed(String id,String feed_source, String message1, String picture)
	{
		boolean result=true;
		int flag=0;
		//id=id.trim();
		List<AdminModel> social_media_feeds=getCommonServices().getSocialMediaPosts(id,message1,picture);
		for(AdminModel a:social_media_feeds)
		{
			flag++;
		}
		if(flag==0)
		{
			result=false;
		}
		return result;
	}
	public String getTwitterFeeds(AdminModel ad)
	{
		/*if(ad.getTwitter_handle()==null || ad.getTwitter_handle().equals(""))
		{
			return "error..";			
		}*/
		try {
			
			ConfigurationBuilder cb=new ConfigurationBuilder();
			/*cb.setOAuthConsumerKey("KcmyfILatgF6NeLjIGhyN9jau");
			cb.setOAuthConsumerSecret("HUJoprOE86i0aSocwcxHQH9cnUMnUawGstZeoLdgtABx0vdfDf");
			cb.setOAuthAccessToken("912606343311237120-iA9P5IjvKbozQ0IZyUC1Q5hHmgbw0eS");
			cb.setOAuthAccessTokenSecret("pONohbpZ1xVAgnqtSBB5x5zVSppwHdOdXzupAHSInNUFf");*/
			
			cb.setOAuthConsumerKey("WnC04fyFPTxd306Ybax7QYEvy");
			cb.setOAuthConsumerSecret("yHSdkfL9AsiAPbgCuYnBfEZnXMY2sM51DP9xEcGvkEwkgNGZmy");
			cb.setOAuthAccessToken("977296654159159296-0kZzJ4pKoXFiRVch3kNchUT7sLU3r9m");
			cb.setOAuthAccessTokenSecret("k10FnA9h3i0ENuapM8AFn0SbgrHK2dFg88e5SqR3LHZ6I");
            Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            
			List<AdminModel> celebrity_twitter_accounts=getCommonServices().getCelebrityTwitterAccount(ad.getCelebrity_id());
			if(celebrity_twitter_accounts.size()>0){
			for(AdminModel celebrity_twitter_accounts1:celebrity_twitter_accounts){
            List<Status> user_status= twitter.getUserTimeline(celebrity_twitter_accounts1.getTwitter_handle());
            for(Status st:user_status)
            {
            	Status retweet = st.getRetweetedStatus();
            	if(retweet!=null){
            		st=retweet;
            	}
            	String message=st.getText().replace("?", "");
            	//System.out.println("Twitter feed="+message);
        		ad.getCelebrity_id();
        		message=message.replace("?", "");
				String message1=message.replace("'", "\'");
				MediaEntity[] mt= st.getMediaEntities();
				String pic="";
            	for(int i=0;i<mt.length;i++)
            	{
            		ad.setNews_description_url(mt[i].getURL());
            		ad.setImage_url(mt[i].getMediaURLHttps());
            		pic=mt[i].getMediaURLHttps();
            	}
            	
            
            	boolean res=isDuplicateFeed(st.getId()+"","Twitter",message1,pic);
            	if(res==false)
            	{
            		
            		ad.setId(st.getId()+"");
            		ad.setPost_source("Twitter");
            		//System.out.println("twitter"+st.getText());
            		ad.setMessage(message);
            		ad.setTwitter_handle(celebrity_twitter_accounts1.getTwitter_handle());
            		ad.setCreated_time(getTwitterDate(st.getCreatedAt().toString()));
            	URLEntity[] ur= st.getURLEntities();
            	for(int i=0;i<ur.length;i++)
            	{
            		ad.setNews_description_url(ur[i].getExpandedURL());
            	}
            		
                	SaveSocialMediaFeeds(ad);
                	
            	}
            	ad.setImage_url("");
            	ad.setNews_description_url("");
            }
			}
			}else{
				return "Error...";
			}
        } catch (TwitterException te) {
            System.out.println("Failed to get timeline: " + te.getMessage());
        }
		return "success";
	}
	
	
	public void getFacebookFeeds(AdminModel ad)
	{
		if(ad.getFacebook_page()!=null && !ad.getFacebook_page().equals(""))
		{
				try{
					String access_token=getAccessToken_fromDB();
					String feed_url="https://graph.facebook.com/"+ad.getFacebook_page()+"/feed?fields=picture,name,message,story,created_time&access_token="+access_token;
					String inputLine=hitURL(feed_url);
				//	System.out.println("inputline==="+inputLine);
					JSONParser parser = new JSONParser();
					JSONObject json=null;
					JSONArray post_data=null;
				      try{
					         Object obj = parser.parse(inputLine);
					         json=(JSONObject)obj;
					         post_data=(JSONArray) json.get("data");
					         int cnt=post_data.size();
					         for(int i=0;i<cnt;i++)
					         {
					        	 JSONObject json1=(JSONObject)post_data.get(i);
					        	 String id=json1.get("id")+"";
					        	 String message=json1.get("message")+"";
					        	 message=message.replace("?", "");
					        	 String picture=json1.get("picture")+"";
					        	 String message1=message.replace("'", "\'");
					        	 boolean save_flag=isDuplicateFeed(id+"","Facebook",message1,picture);
					        	 if(save_flag==false)
					        	 {
					        	 
					        	 String name=json1.get("name")+"";
					        	
					        	 String story=json1.get("story")+"";
					        	 String cretated_time=json1.get("created_time")+"";
					        	 cretated_time=cretated_time.replace("T", " ");
					        	 String time[]=cretated_time.split("\\+");
					        	 cretated_time=time[0];
					        	 
					        	 AdminModel adminModel=new AdminModel();
					        	 
					        	 adminModel.setCelebrity_id(ad.getCelebrity_id());
					        	 adminModel.setPost_source("Facebook");
					        	 adminModel.setMessage(message);
					        	 adminModel.setImage_url(picture);
					        	 adminModel.setName(name);
					        	 adminModel.setStory(story);
					        	 adminModel.setCreated_time(cretated_time);
					        	 adminModel.setId(id);
					        	 
					        	 getCommonServices().addCelebrityPost(adminModel);
					        		 
					        	 }
					        	 json1.clear();
					         }
					        
						}catch(Exception e)
						{
							System.out.println("Testing ..."+e);
						}finally{
							 json.clear();
					         post_data.clear();
						}
				}catch(Exception e)
				{
					System.out.println("access token error inside getFacebookFeeds() method.");
				}
		}
	}
	
	public String getAccessToken_fromDB()
	{
		String acccess_token="";
		try	{
			
			acccess_token=getCommonServices().getToken();

		}catch(Exception e){System.out.println("getAccessToken_fromDB() ..."+e);}
		return acccess_token;
	}
	
	@Scheduled(fixedDelay = 1000 * 60 * 90)
	//@Scheduled(cron = "0 4 0 * * *")	

	public void getAccessToken() throws Exception
	{
		BufferedReader in=null;
try {
	String access_token="";
	String token=getCommonServices().getToken();
	String token_query="https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id=1808125712811130&client_secret=39635124ad35c875485c229d889d87c8&fb_exchange_token="+token;
	//String token_query="https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id=157943735026114&client_secret=df2e85c3ef1f915439cc0c9a82bd0e2d&fb_exchange_token="+token;
	URL url=new URL(token_query);
	URLConnection ucon=url.openConnection();
	ucon.connect();
	in = new BufferedReader(new InputStreamReader(url.openStream()));
	        String inputLine="";
	        String data="";
	        while ((data = in.readLine()) != null)
	        {
	        	inputLine=inputLine+data;
	        }
	        in.close();
	        Object obj=JSONValue.parse(inputLine);  
	        JSONObject jsonObject = (JSONObject) obj;  
	        access_token = (String) jsonObject.get("access_token");  
	        
	        getCommonServices().updateAccessToken(access_token);

		        } catch (Exception ex) {
		        } finally {
		            if (in != null) {
		                try {
		                    in.close();
		                } catch (IOException e) {
		                }
		            } 
		        }
	}
	
 /* @Scheduled(cron = "0 20 13 * * *")
	//@Scheduled(cron = "0 38 12 * * *")	

		 public void sendDailyNewsLetter(){
		//  SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//	int checkforToday=getCommonServices().checkforRepeatCount();
		//System.out.println("checkforToday="+checkforToday);
		//if(checkforToday==0){
			System.out.println("insideeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
		//Insert celebrity of the day
		  int totalCelebCount=getCommonServices().getTotalCelebrityCount();
			List<AdminModel> trending_count=getCommonServices().getTrending_celebrities1(totalCelebCount);
for(AdminModel trending_count1:trending_count){
	int celeb_id=trending_count1.getCelebrity_id();
	getCommonServices().removeCelebrityoftheDay();
	getCommonServices().insertCelebrityoftheDay(celeb_id);
}
//Send daily updates
//int checkforToday1=getCommonServices().checkforRepeatCount();
//System.out.println("checkforToday1="+checkforToday1);
//if(checkforToday1==1){
		  List<AdminModel> followed_celebrities=commonServices.getFollowers();
					for(AdminModel followed_celebrities1:followed_celebrities){
						int user_id=followed_celebrities1.getUser_id();
						System.out.println("Follower="+user_id);
						String celebrityNewsLetterString="";
						List<AdminModel> followedCelebrityIds=commonServices.getCelebrityListByFollower(user_id);
						for(AdminModel followedCelebrityIds1:followedCelebrityIds){
							System.out.println("Celebrity_id="+followedCelebrityIds1.getCelebrity_id());

							String btnColor="";
							if(followedCelebrityIds1.getParty_id()==1){
								btnColor="red1";
							}if(followedCelebrityIds1.getParty_id()==2){
								btnColor="blue1";
							}
						String celebrityNewsLetterHeader="<tr><td align='center' valign='top' style='padding-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' width='600' id='templateContainer' style='margin-top:15px;'>"+
                        "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='610' id='templateHeader'>"+
                        "<tr><td valign='top' class='headerContainer' style='padding-top:8px; padding-bottom:8px;'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnImageBlock' style='min-width:100%;'>"+
                        "<tbody class='mcnImageBlockOuter'><tr><td valign='top' style='padding:5px' class='mcnImageBlockInner'><table align='left' width='100%' border='0' cellpadding='0' cellspacing='0' class='mcnImageContentContainer' style='min-width:100%;'>"+
                        "<tbody><tr><td valign='top' class='mcnTextContent' style='color: #222222;font-family:Roboto;font-size: 16px;line-height: 100%;vertical-align:middle;padding: 0 20px;'>"+followedCelebrityIds1.getCelebrity_name()+"</td><td class='mcnImageContent' valign='top' align='right' style='padding-right: 8px; padding-left: 9px; padding-top: 0; padding-bottom: 0;'>"+
                        "<button type='submit' class='"+btnColor+" btn-follow' name='celebrity_id' value='"+followedCelebrityIds1.getCelebrity_id()+"'>See all</button></td></tr></tbody></table>"+
                        "</td></tr></tbody></table></td></tr></table></td></tr></table></td></tr><tr id='myCheck'><td align='center' valign='top'>"+
                        "<table border='0' cellpadding='0' cellspacing='0' width='600' id='templateColumns'>"+
                        "<tr>";
						List<AdminModel> celebrity_news=commonServices.getTodaysNewsforCelebrity(followedCelebrityIds1.getCelebrity_id());
						System.out.println("size"+celebrity_news.size());
						if(celebrity_news.size()>0){
							celebrityNewsLetterString=celebrityNewsLetterString+celebrityNewsLetterHeader;
							
						for(int j=0;j<=1;j++){
							String boxSide="";
							String boxStyle="";
							if(j==0){
								boxSide="left";
								boxStyle="style='padding-right:10px;'";
							}else{
								boxSide="right";
							}
							int tempVar=j;
							int i=0;

							String innercelebrityNewsLetterHeader="<td align='"+boxSide+"' valign='top' "+boxStyle+">"+                   
                                         "<table border='0' cellpadding='0' cellspacing='0' width='300px' id='templateColumns'>";
							celebrityNewsLetterString=celebrityNewsLetterString+innercelebrityNewsLetterHeader;
							
						for(AdminModel celebrity_news1:celebrity_news){
							if(tempVar==i){
							String celebrityNewsLetterBody="";

							String post_source="";
							String imageStyle="";
								if(celebrity_news1.getNews_source().equalsIgnoreCase("Facebook")){
									post_source="fb.png";
									imageStyle="height:150px;width:150px;padding-top:10px";
								}else if(celebrity_news1.getNews_source().equalsIgnoreCase("Instagram")){
									post_source="instagram.png";
									imageStyle="width:100%;padding-top:10px;";
								}else if(celebrity_news1.getNews_source().equalsIgnoreCase("Twitter")){
									post_source="twitter.png";
									imageStyle="width:100%;padding-top:10px";
								}else{
									post_source="news.png";
									imageStyle="height:150px;width:150px;padding-top:10px";
								}
					    String tempHeader="";
					    String postImage="";
  	//			       if(!celebrity_news1.getImage_url().equals("null") && !celebrity_news1.getImage_url().equals("") && !celebrity_news1.getImage_url().equals("unknown")){
		//			    	postImage="<img alt='' src='"+celebrity_news1.getImage_url()+"' width='150px' style='"+imageStyle+"' class='mcnImage'>";
	
			//		    }else{
	
				//	    }
					    String imgUrl=celebrity_news1.getImage_url();
  				      if(imgUrl!="" && imgUrl!=null && imgUrl!="unknown" && imgUrl!="null"){
					    	postImage="<img alt='' src='"+imgUrl+"' width='150px' style='"+imageStyle+"' class='mcnImage'>";
						//System.out.println("in image");	
  				      }
					    String newsTitle=celebrity_news1.getNews_title();
					    newsTitle=newsTitle.replace("?", "");
					    celebrityNewsLetterBody=tempHeader+" <tr><td align='left' valign='top' class='columnsContainer' width='300px' style='padding-top:4px;padding-bottom:4px;'>"+
							"<table border='0' cellpadding='0' cellspacing='0' width='100%' id='templateLeftColumn'><tr><td align='center' valign='top' style='padding-top:4px; padding-bottom:4px;'>"+
							"<table border='0' cellpadding='0' cellspacing='0' width='100%' class='templateColumn'><tr><td valign='top' class='leftColumnContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnCaptionBlock'>"+
							"<tbody class='mcnCaptionBlockOuter'><tr><td class='mcnCaptionBlockInner' valign='top' style='padding:9px;'>"+
							"<table align='left' border='0' cellpadding='0' cellspacing='0' class='mcnCaptionBottomContent' width='false'><tbody><tr style='border-bottom: 2px solid #64f964;'><td class='mcnCaptionBottomImageContent' align='left' valign='top' style='padding:0 9px 9px 9px;width:10%'>"+
							"<img alt='' src='http://www.redsaidbluesaid.com/resources/assets/images/"+post_source+"' width='30' style='max-width:30px;' class='mcnImage'>"+
							"</td><td class='mcnCaptionBottomImageContent' align='left' valign='top' style='padding:0 9px 9px 0;width:80%'>"+
							newsTitle+"</td></tr><tr></tr></tbody></table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnButtonBlock' style='min-width:100%;'>"+
							"<tbody class='mcnButtonBlockOuter'><tr><td align='center' valign='middle' class='mcnButtonContent'>"+postImage+
							"</td></tr></tbody></table></td></tr></tbody></table></td></tr></table></td></tr></table></td></tr>";
					    	   					    
						celebrityNewsLetterString=celebrityNewsLetterString+celebrityNewsLetterBody; 
						tempVar=tempVar+2;

							}	
							i++;
						}
						String innercelebrityNewsLetterFooter="</table></td>";
					    celebrityNewsLetterString=celebrityNewsLetterString+innercelebrityNewsLetterFooter;
						}
						
						String celebrityNewsLetterFooter="</tr></table></td></tr>";
						celebrityNewsLetterString=celebrityNewsLetterString+celebrityNewsLetterFooter;

						}

						}
						if(celebrityNewsLetterString!=""){
						Map<String, String> input = new HashMap<String, String>();
						input.put("celebrityNewsLetterString", celebrityNewsLetterString);
						//String[] mailFromAddress={"kedar.khire@anveshak.com"};
						String[] mailFromAddress={followed_celebrities1.getUser_name()};
					    String htmlText = readEmailFromHtml("/usr/share/apache-tomcat-7.0.75/webapps/RSBS/WEB-INF/jsp/Common/dailyNewsletterTemplate.html",input);
						EmailController email1=new EmailController();
						String subject="Red Said Blue Said - Daily updates";
						//int checkforToday2=getCommonServices().checkforRepeatCount();
						//System.out.println("checkforToday2="+checkforToday2);
					//	if(checkforToday2==1){
							email1.emailSending(mailFromAddress, htmlText,subject);
							System.out.println("In mail...");
						//}
						input.clear();
						}
					}
			//}
		//}
		//return;
			   } */
	  //Method to replace the values for keys
		protected String readEmailFromHtml(String filePath, Map<String, String> input)
		{
		    String msg = readContentFromFile(filePath);
		    Set<Entry<String, String>> entries = input.entrySet();
		    try
		    {
		    for(Map.Entry<String, String> entry : entries) {
		        msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
		    }
		   
		    }
		    catch(Exception exception)
		    {
		        exception.printStackTrace();
		    } finally {
		    	entries.clear();
		      }
		    return msg;
		}
		String readContentFromFile(String fileName)
		{
		    StringBuffer contents = new StringBuffer();
		    
		    try {
		      //use buffering, reading one line at a time
		      BufferedReader reader =  new BufferedReader(new FileReader(fileName));
		      try {
		        String line = null; 
		        while (( line = reader.readLine()) != null){
		          contents.append(line);
		          contents.append(System.getProperty("line.separator"));
		        }
		      }
		      finally {
		          reader.close();
		      }
		    }
		    catch (IOException ex){
		      ex.printStackTrace();
		    }
		    return contents.toString();
		}
		
		@Scheduled(cron = "0 1 12 * * MON")
		//@Scheduled(cron = "0 11 17 * * MON")
		private void getBills() {
			try{
				getCommonServices().truncateBills();
				URLConnection connection = new URL("https://www.congress.gov/rss/most-viewed-bills.xml").openConnection();
				connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				connection.connect();
                BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                StringBuilder sb = new StringBuilder();
				String line;
				while ((line = r.readLine()) != null) {
					sb.append(line);
				}
			     InputSource is = new InputSource(new StringReader(sb.toString()));
		         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		         Document doc = dBuilder.parse(is);
		         doc.getDocumentElement().normalize();
		         NodeList nList = doc.getElementsByTagName("rss");
	  	         AdminModel ad=new AdminModel();
		         for (int temp = 0; temp < nList.getLength(); temp++) {
			            Node nNode = nList.item(temp);
			            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			            	Element eElement = (Element) nNode;
			            	//String title=eElement.getElementsByTagName("description").item(0).getTextContent();
			            	String pub_date=eElement.getElementsByTagName("pubDate").item(0).getTextContent();
			            	pub_date=convertPublicationDate(pub_date);
			            	String description=eElement.getElementsByTagName("description").item(1).getTextContent();
			            	org.jsoup.nodes.Document doc1 = Jsoup.parse(description);
			            	Elements allbills = doc1.select("ol li");
			            	for (org.jsoup.nodes.Element bill : allbills) {
			            		Elements billUrl = bill.select("a");
			            		String billNumber=billUrl.text();
			            		String[] billTitle=bill.text().split("-",2);
			            		
					        		ad.setBill_number(billNumber);
					            	ad.setBill_title(billTitle[1].trim());
					            	ad.setBill_url(billUrl.attr("href"));
					            	ad.setBill_published(pub_date);

					            	getCommonServices().addBillsInfo(ad);
			            	}
			              	
			            }
			         }
			}catch(Exception e)
			{
				System.out.println("getCelebrity_news().."+e);
			}
		}

	
	
	
}