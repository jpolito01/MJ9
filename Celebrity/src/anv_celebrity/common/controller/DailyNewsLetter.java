package common.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
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


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import twitter4j.MediaEntity;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import admin.model.AdminModel;

import common.services.CommonServices;

@Configuration
@EnableScheduling
public class DailyNewsLetter {
  private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
  int ENTITY_CELEBRITY=1;
	   
        @Autowired
		private CommonServices commonServices;
		
        //@PostConstruct
		public CommonServices getCommonServices() {
			return commonServices;
		}
		//EAAZAsexLSRHoBAK0reAZATH2GBTC1iEYpE0VSGTzCC6Fk6GJ3JYvXKeUuAuZC4qfeRxPyagm0t6eamswUn3BUfnKHPGzi8uG8DlqTry0qm93bZAgXfmCvfDUZAGZC87y1iOiaDEUyiD8ytDhPPEHBAVI1wczZCKUocZD
//		public void setCommonServices(CommonServices commonServices) {
//			this.commonServices = commonServices;
//		} 
       
		/*@PostConstruct
        public void startupGetCelebrity() throws Exception{
          	getAccessToken();
        	getCelebrity_list();
        }*/
        
        
		//@Scheduled(fixedDelay = 1000 * 60 * 150, initialDelay = 300000)
        @Scheduled(fixedDelay = 1200000)

		public void getCelebrity_list() throws Exception
		{
			//getAccessToken();
        	//SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			long DAY_IN_MS = 1000 * 60 * 60 * 24;
			Date date=new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
			String finalDate=dateFormat.format(date);
            System.out.println("date="+dateFormat.format(date));
        	//System.out.println("in get celebrity news time..."+dateFormat.format(new Date()));
			List<AdminModel> celebrity_name=getCommonServices().getCelebrity_name();
			
			extract_celebrity_info(celebrity_name,finalDate);
		}
		public void extract_celebrity_info(List<AdminModel> celebrity_info, String finalDate)
		{
			List<String> black_list=getBlacklist_website();
			for(AdminModel ad:celebrity_info)
			{
				
				getFacebookFeeds(ad);
				//getInstgramFeeds(ad);
				getTwitterFeeds(ad);
				getCelebrityNewsfromAPI(ad,black_list,finalDate);
			}
//			black_list=null;
			celebrity_info=null;
		}
		
		public void getCelebrityNewsfromAPI(AdminModel ad,List<String> blacklist_website,String finalDate){
			String celebrity_name=ad.getCelebrity_name();
			try{
				List<AdminModel> isMovie=getCommonServices().checkForMovieCategory(ad.getCelebrity_id());
	if(isMovie.size()==0){

				String query=celebrity_name.replace(" ", "+");
					//	String feed_url="https://newsapi.org/v2/everything?Language=en&q=\""+query+"\"&apiKey=23f9b7d847364efc88a86fa973c198fc&sortBy=publishedAt"; //43cceeca5c384d1daaf15c95475b5766;

				String feed_url="https://newsapi.org/v2/everything?Language=en&q=\""+query+"\"&apiKey=23f9b7d847364efc88a86fa973c198fc&sortBy=publishedAt,relevancy&from="+finalDate;
				System.out.println(feed_url);        
				String inputLine=hitURL(feed_url);
						JSONParser parser = new JSONParser();
					      try{
						         Object obj = parser.parse(inputLine);
						         JSONObject json=(JSONObject)obj;
						         JSONArray post_data=(JSONArray) json.get("articles");
						         int cnt=post_data.size();
						         for(int i=0;i<cnt;i++)
						         {
						        	 JSONObject json1=(JSONObject)post_data.get(i);
						        		boolean isConteins=false;
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
							             String url=json1.get("url")+"";
						            	for(String str:blacklist_website)
						            	{
						            		isConteins=url.toLowerCase().contains(str.toLowerCase());
						            		if(isConteins){
						            			break;
						            		}
						            	}
						            	if(!isConteins)
						            	{	
						            		 String title=json1.get("title")+"";
						            		 title=title.replace("?", "");
								        	 String description=json1.get("description")+"";
								        	 description=description.replace("?", "");

						            		// System.out.println("description=="+description);
								        	 String img_url=json1.get("urlToImage")+"";
						            		 String publishedAt=json1.get("publishedAt")+"";
						            		 publishedAt=publishedAt.replace("T", " ");
						            		 publishedAt=publishedAt.replace("Z", "");
								        	 
						            		ad.setNews_title(title);
							            	ad.setPublication_date(publishedAt);
							            	ad.setNews_description_url(url);
							            	ad.setNews_description(description);
							            	ad.setEntity_id(ad.getCelebrity_id());
							            	ad.setEntity_type_id(ENTITY_CELEBRITY);
							            	ad.setImage_url(img_url);
							            	ad.setNews_source(source_url);
							            	
							            	int is_news_present=0;
							            	String titleRepeat=title.replace("'", "\'");
							            	String news_source_url="%"+ad.getNews_description_url()+"%";
							            	List<AdminModel> news_id=getCommonServices().getNewsId(news_source_url,titleRepeat,img_url);
							            	//System.out.println("in fun fun......");
							            	for(AdminModel a:news_id)
							            	{
							            		is_news_present=a.getNews_id();
							            	}
							            	
							        		if(is_news_present==0)
							        		{
								            	//System.out.println("in insert......");
							        			getCommonServices().addEntityNews(ad);
							        		}
						        
						            	}
						 
						         }
							}catch(Exception e)
							{
								System.out.println("Testing ..."+e);
							}
}

					}catch(Exception e)
					{
						System.out.println("error inside getCelebrityNewsfromAPI() method.");
					}
		//	}
		}
	//	@Scheduled(cron = "0 6,8 13 * * *")	
		 //@Scheduled(cron = "0 0/40 1 * * *")	
//		 public void cronTest() {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//        	System.out.println("cron test successful..."+dateFormat.format(new Date()));
//			 sendDailyNewsLetter();
//		 }
		
//		 @Scheduled(cron = "0 0/1 * * * *")
/*		@Scheduled(cron = "0 1 0 * * *")	
			 public void sendDailyNewsLetter() {
		        	System.out.println("cron test successful1..."+dateFormat.format(new Date()));
			  SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			  System.out.println("inside daily news letter");
			  //Insert celebrity of the day
			  int totalCelebCount=getCommonServices().getTotalCelebrityCount();
				List<AdminModel> trending_count=getCommonServices().getTrending_celebrities1(totalCelebCount);
	for(AdminModel trending_count1:trending_count){
		int celeb_id=trending_count1.getCelebrity_id();
		getCommonServices().removeCelebrityoftheDay();
		getCommonServices().insertCelebrityoftheDay(celeb_id);
	}
	//Send daily updates
					List<AdminModel> followed_celebrities=commonServices.getFollowers();
					System.out.println("followed_celebrities count"+followed_celebrities.size());
						for(AdminModel followed_celebrities1:followed_celebrities){
							System.out.println("inside followed celebrities for");
						//	System.out.println("celebrity"+followed_celebrities1.getCelebrity_id());
							int user_id=followed_celebrities1.getUser_id();
							System.out.println("user_id"+user_id);
							System.out.println("celebrity"+followed_celebrities1.getEmail_id());
							String celebrityNewsLetterString="";
							List<AdminModel> followedCelebrityIds=commonServices.getCelebrityListByFollower(user_id);
							for(AdminModel followedCelebrityIds1:followedCelebrityIds){
								System.out.println("entity id==========="+followedCelebrityIds1.getEntity_id());

							String celebrityNewsLetterHeader="<tr><td align='center' valign='top' style='padding-bottom:10px;'><table border='0' cellpadding='0' cellspacing='0' width='600' id='templateContainer' style='margin-top:15px;'>"+
	                        "<tr><td align='center' valign='top'><table border='0' cellpadding='0' cellspacing='0' width='610' id='templateHeader'>"+
	                        "<tr><td valign='top' class='headerContainer' style='padding-top:8px; padding-bottom:8px;'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnImageBlock' style='min-width:100%;'>"+
	                        "<tbody class='mcnImageBlockOuter'><tr><td valign='top' style='padding:5px' class='mcnImageBlockInner'><table align='left' width='100%' border='0' cellpadding='0' cellspacing='0' class='mcnImageContentContainer' style='min-width:100%;'>"+
	                        "<tbody><tr><td valign='top' class='mcnTextContent' style='color: #222222;font-family:Roboto;font-size: 16px;line-height: 100%;vertical-align:middle;padding: 0 20px;'>"+followedCelebrityIds1.getCelebrity_name()+"</td><td class='mcnImageContent' valign='top' align='right' style='padding-right: 8px; padding-left: 9px; padding-top: 0; padding-bottom: 0;'>"+
	                        "<button type='submit' class='btn-follow' name='celebrity_id' value='"+followedCelebrityIds1.getEntity_id()+"'>See all</button></td></tr></tbody></table>"+
	                        "</td></tr></tbody></table></td></tr></table></td></tr></table></td></tr><tr id='myCheck'><td align='center' valign='top'>"+
	                        "<table border='0' cellpadding='0' cellspacing='0' width='600' id='templateColumns'>"+
	                        "<tr>";
							
							List<AdminModel> celebrity_news=commonServices.getTodaysNewsforCelebrity(followedCelebrityIds1.getEntity_id());
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
										imageStyle="width:100%;padding-top:10px;";
									}
						    String tempHeader="";
//						    if(i % 2==0){
//						    	tempHeader="<tr id='myCheck'><td align='center' valign='top' style='padding-top:10px; padding-bottom:10px;'>"+
//	                        "<table border='0' cellpadding='0' cellspacing='0' width='600' id='templateColumns'><tr>";
//						    }
						    String postImage="";
						    if(!celebrity_news1.getImage_url().equals("null") && !celebrity_news1.getImage_url().equals("") && !celebrity_news1.getImage_url().equals("unknown")){
						    	postImage="<img alt='' src='"+celebrity_news1.getImage_url()+"' width='150px' style='"+imageStyle+"' class='mcnImage'>";
								}
						    String newsTitle=celebrity_news1.getNews_title();
						    newsTitle=newsTitle.replace("?", "");
						    celebrityNewsLetterBody=tempHeader+" <tr><td align='left' valign='top' class='columnsContainer' width='300px' style='padding-top:4px;padding-bottom:4px;'>"+
								"<table border='0' cellpadding='0' cellspacing='0' width='100%' id='templateLeftColumn'><tr><td align='center' valign='top' style='padding-top:4px; padding-bottom:4px;'>"+
								"<table border='0' cellpadding='0' cellspacing='0' width='100%' class='templateColumn'><tr><td valign='top' class='leftColumnContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnCaptionBlock'>"+
								"<tbody class='mcnCaptionBlockOuter'><tr><td class='mcnCaptionBlockInner' valign='top' style='padding:9px;'>"+
								"<table align='left' border='0' cellpadding='0' cellspacing='0' class='mcnCaptionBottomContent' width='false'><tbody><tr style='border-bottom: 2px solid #64f964;'><td class='mcnCaptionBottomImageContent' align='left' valign='top' style='padding:0 9px 9px 9px;width:10%'>"+
								"<img alt='' src='http://www.nowyano.com/resources/assets/images/"+post_source+"' width='30' style='max-width:30px;' class='mcnImage'>"+
								"</td><td class='mcnCaptionBottomImageContent' align='left' valign='top' style='padding:0 9px 9px 0;width:80%'>"+
								newsTitle+"</td></tr><tr></tr></tbody></table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnButtonBlock' style='min-width:100%;'>"+
								"<tbody class='mcnButtonBlockOuter'><tr><td align='center' valign='middle' class='mcnButtonContent'>"+postImage+
								"</td></tr></tbody></table></td></tr></tbody></table></td></tr></table></td></tr></table></td></tr>";
						    	   
//							 if(i % 2!=0 || celebrity_news.size()==i+1){
//							 celebrityNewsLetterBodyFooter="</tr></table></td></tr>";
//							 }
						    
							celebrityNewsLetterString=celebrityNewsLetterString+celebrityNewsLetterBody; 
						//	System.out.println("tempVar"+tempVar);
							tempVar=tempVar+2;

								}	
								//System.out.println("i===="+i);
								i++;
							}

							String innercelebrityNewsLetterFooter="</table></td>";
						    celebrityNewsLetterString=celebrityNewsLetterString+innercelebrityNewsLetterFooter;
							}
							
							String celebrityNewsLetterFooter="</tr></table></td></tr>";
							celebrityNewsLetterString=celebrityNewsLetterString+celebrityNewsLetterFooter;
							
							}
							
							}
							//System.out.println("celebrityNewsLetterString"+celebrityNewsLetterString);
							if(celebrityNewsLetterString!=""){
							Map<String, String> input = new HashMap<String, String>();
							input.put("celebrityNewsLetterString", celebrityNewsLetterString);
							String[] mailFromAddress={followed_celebrities1.getEmail_id()};
						//	String[] mailFromAddress={"aishwarya.dhopate@anveshak.com","shiwani.thakur@anveshak.com","snigdha.kadam@anveshak.com","namita.khond@anveshak.com"};
						//	String[] mailFromAddress={"aishwarya.dhopate@anveshak.com"};

							//String htmlText = readEmailFromHtml("/home/anveshak/celebrityworkspace/Celebrity/WebContent/WEB-INF/jsp/Common/dailyNewsletterTemplate.html",input);
						    String htmlText = readEmailFromHtml("/usr/share/apache-tomcat-7.0.75/webapps/Celebrity/WEB-INF/jsp/Common/dailyNewsletterTemplate.html",input);
							//	String htmlText = readEmailFromHtml("home/anveshak/celebrityworkspace/Celebrity/WebContent/WEB-INF/jsp/Common/DailyNewsUpdate.html",input);
							EmailController email1=new EmailController();
							email1.emailSending(mailFromAddress, htmlText);
							}
						}
				   	System.out.println("The time is now {}"+dateFormat.format(new Date()));
				   }*/	
						//Method to replace the values for keys
		protected String readEmailFromHtml(String filePath, Map<String, String> input)
		{
		    String msg = readContentFromFile(filePath);
		    try
		    {
		    Set<Entry<String, String>> entries = input.entrySet();
		    for(Map.Entry<String, String> entry : entries) {
		        msg = msg.replace(entry.getKey().trim(), entry.getValue().trim());
		    }
		    }
		    catch(Exception exception)
		    {
		        exception.printStackTrace();
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

    public static void main(String[] args) throws Exception {
    	//getAccessToken();
    }
    
    public void getFacebookFeeds(AdminModel ad)
	{
		if(ad.getFacebok_page_name()!=null && !ad.getFacebok_page_name().equals(""))
		{
				try{
					String access_token=getAccessTokenfromDatabase();
					String feed_url="https://graph.facebook.com/"+ad.getFacebok_page_name()+"/feed?fields=picture,name,message,story,created_time&access_token="+access_token;
					String inputLine=hitURL(feed_url);
					JSONParser parser = new JSONParser();
				      try{
					         Object obj = parser.parse(inputLine);
					         JSONObject json=(JSONObject)obj;
					         JSONArray post_data=(JSONArray) json.get("data");
					         int cnt=post_data.size();
					         for(int i=0;i<cnt;i++)
					         {
					        	 JSONObject json1=(JSONObject)post_data.get(i);
					        	 String id=json1.get("id")+"";
					        	 int save_flag=0;
					        	 String message=json1.get("message")+"";
					        	 message=message.replace("?", "");
					        	 String picture=json1.get("picture")+"";
					        	 String message1=message.replace("'", "\'");
					        	 boolean get_saved_data=isDuplicateFeed(id, "Facebook",message1,picture);
					        	 /*for(AdminModel a:get_saved_data)
					        	 {
					        		 if(a.getId().equals(id))
					        		 {
					        			 save_flag=1;
					        		 } 
					        	 }*/
					        	 
					        	 if(get_saved_data==false)
					        	 {
					        	// String id_for_img=id.split("_")[1];
//					        	 String images_rul="https://graph.facebook.com/"+id_for_img+"?fields=images&access_token="+access_token;
//					        	 System.out.println("URL  :   "+images_rul);
//					        	 String images_data=hitURL(images_rul);
//					        	 System.out.println("Image Data : "+images_data);
//								 JSONParser parse_img=new JSONParser();
//								 Object img_obj=parse_img.parse(images_data);
//								 JSONObject img_json=(JSONObject)img_obj;
//								 JSONArray img_arr=(JSONArray) img_json.get("images");
//								 String img_src="";
//								 for(int x=0;x<img_arr.size();)
//								 {
//									 JSONObject src=(JSONObject) img_arr.get(x);
//									 img_src=(String) src.get("source");
//									 System.out.println("Image Src : "+img_src);
//									 break;
//								 }
					        	 
					        	 
					        	 String name=json1.get("name")+"";
					        	 String story=json1.get("story")+"";
					        	 String cretated_time=json1.get("created_time")+"";
					        	 cretated_time=cretated_time.replace("T", " ");
					        	 String time[]=cretated_time.split("\\+");
					        	 cretated_time=time[0];
					        	 String source="Facebook";
					        	 getCommonServices().insert_feeds(ad.getCelebrity_id(), 1, source, picture, name, message, story, cretated_time, id);
					        		 
					        	 }
					         }
						}catch(Exception e)
						{
							System.out.println("Testing ..."+e);
						}
				}catch(Exception e)
				{
					System.out.println("access token error inside getFacebookFeeds() method.");
				}
		}
	}
    
    public String getAccessTokenfromDatabase() throws SQLException{

    	String token="";
    	List<AdminModel> getToken=getCommonServices().getToken();
    	for(AdminModel a:getToken)
    	{
    		token=a.getToken();
    	}
		return token;
	}
    
    public String hitURL(String url) throws Exception
	{
		String result="";
		URL news=new URL(url);
	    URLConnection myURLConnection = news.openConnection();
	    myURLConnection.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(news.openStream()));
		        String inputLine="";
		        String data="";
		        while ((data = in.readLine()) != null)
		        {
		        	inputLine=inputLine+data;
		        }
		        result =inputLine;
		        in.close();
		return result;
	}
    
    public void getInstgramFeeds(AdminModel ad)
	{
		Date d=new Date();
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String created_time=df.format(d);
		List<String> instagram_id=new ArrayList<>();
		if(ad.getInstagram_page_name()!=null && !ad.getInstagram_page_name().equals(""))
		{
			
//			try{
//				System.out.println("in try");
//				List<AdminModel> insta_post_id=getCommonServices().getInstaPostId();
//				
//				for(AdminModel a:insta_post_id)
//				{
//					System.out.println("in for...");
//					//System.out.println("a.getInstagram_id().."+a.getInstagram_id()+"");
//					//instagram_id.add(a.getInstagram_id()+"");
//				}
//			}catch(Exception e){System.out.println("Check Instafeeds ....");}
			String url="https://www.instagram.com/"+ad.getInstagram_page_name()+"/?__a=1";
			try{
				String insta_result=hitURL(url);
				JSONParser parser=new JSONParser();
				Object obj=parser.parse(insta_result);
				
				JSONObject main_obj=(JSONObject) obj;
				JSONObject user_obj=(JSONObject) main_obj.get("user");
				JSONObject media_obj=(JSONObject) user_obj.get("media");
				JSONArray node_arr=(JSONArray) media_obj.get("nodes");
				String message="";
				String image_url="";
				String insta_id="";
				
				for(int i=0;i<node_arr.size();i++)
				{
					JSONObject node=(JSONObject) node_arr.get(i);
					image_url=(String) node.get("display_src");
					message=(String) node.get("caption");
					String message1=message.replace("'", "\'");
					insta_id=(String) node.get("id");
					boolean res=isDuplicateFeed(insta_id+"","Instagram",message1,image_url);
							if(!res)
							{
								ad.setEntity_id(ad.getCelebrity_id());
								ad.setEntity_type_id(1);
								ad.setPost_source("Instagram");
								ad.setPicture(image_url);
								System.out.println("message...."+message);
								ad.setMessage("");
								if(message!=null)
								{
								message=message.replace("?", "");
								ad.setMessage(message);
								}
								
								ad.setCretated_time(created_time);
								ad.setId(insta_id);
								SaveSocialMediaFeeds(ad);
							}
				}
			}catch(Exception e){System.out.println("save...insta.."+e);}
		}
		instagram_id=null;
	}
    
    public boolean isDuplicateFeed(String id,String feed_source,String message1,String pic)
	{
		boolean result=true;
		int flag=0;
		id=id.trim();
		try{
			
			List<AdminModel> feed_source_id=getCommonServices().getFeedSourceId(feed_source, id,message1,pic);
			for(AdminModel a:feed_source_id)
			{
				flag++;
			}
			
		}catch(Exception e){System.out.println("Check Duplicate : "+e);}
		if(flag==0)
		{
			result=false;
		}
		return result;
	}
    
    int counter=0;
	public int SaveSocialMediaFeeds(AdminModel insta_data)
	{
		counter++;
		int affected_rows=0;
		affected_rows=getCommonServices().insert_feeds(insta_data.getEntity_id(), insta_data.getEntity_type_id(), insta_data.getPost_source(), insta_data.getPicture(), insta_data.getName(), insta_data.getMessage(), insta_data.getStory(), insta_data.getCretated_time(), insta_data.getId());
		
		return affected_rows;
	}
	public String getTwitterFeeds(AdminModel ad)
	{
		if(ad.getTwitter_handle_name()==null || ad.getTwitter_handle_name().equals(""))
		{
			return "error..";			
		}
		try {
			ConfigurationBuilder cb=new ConfigurationBuilder();
			cb.setOAuthConsumerKey("KcmyfILatgF6NeLjIGhyN9jau");
			cb.setOAuthConsumerSecret("HUJoprOE86i0aSocwcxHQH9cnUMnUawGstZeoLdgtABx0vdfDf");
			cb.setOAuthAccessToken("912606343311237120-iA9P5IjvKbozQ0IZyUC1Q5hHmgbw0eS");
			cb.setOAuthAccessTokenSecret("pONohbpZ1xVAgnqtSBB5x5zVSppwHdOdXzupAHSInNUFf");
            Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            
            List<Status> user_status= twitter.getUserTimeline(ad.getTwitter_handle_name());
            for(Status st:user_status)
            {
            	Status retweet = st.getRetweetedStatus();
            	if(retweet!=null){
            		st=retweet;
            	}
            	String message=st.getText().replace("?", "");
            	String message1=message.replace("'", "\'");
            	MediaEntity[] mt= st.getMediaEntities();
            	String pic="";
            	for(int i=0;i<mt.length;i++)
            	{
            		ad.setPicture(mt[i].getMediaURLHttps());
            		pic=mt[i].getMediaURLHttps();
            	}
            	boolean res=isDuplicateFeed(st.getId()+"","Twitter",message1,pic);
            	
            	if(res==false)
            	{
            		ad.setId(st.getId()+"");
            		ad.setEntity_type_id(1);
            		ad.setPost_source("Twitter");
            		
            		ad.setEntity_id(ad.getCelebrity_id());
            		
            		ad.setMessage(message);
            		
            		ad.setCretated_time(getTwitterDate(st.getCreatedAt()+""));
            		SaveSocialMediaFeeds(ad);
                	
            	}
            	mt=null;
            	ad.setPicture("");
            }
            user_status=null;
        } catch (TwitterException te) {
            System.out.println("Failed to get timeline: " + te.getMessage());
        }
		return "success";
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
	public List<String> getBlacklist_website()
	{
		List<String> blacklist_website=new ArrayList<>();
		String web_name="";
		List<AdminModel> website_name=getCommonServices().getBlackListWebsite();
		for(AdminModel a:website_name)
		{
			web_name=a.getWebsite_name();
			blacklist_website.add(web_name);
		}
		return blacklist_website;
	}
	/*public void getCelebrity_news(AdminModel ad,List<String> blacklist_website)
	{
		String celebrity_name=ad.getCelebrity_name();
		//int celebrity_id=ad.getCelebrity_id();
		try{
			String query=celebrity_name.replace(" ", "+");
			
			String url="https://news.google.com/news/?q="+query+"&output=rss";
			String inputLine=hitURL(url);
			
			InputSource is = new InputSource(new StringReader(inputLine));
	         
	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(is);
	         doc.getDocumentElement().normalize();
	         NodeList nList = doc.getElementsByTagName("item");
	         for (int temp = 0; temp < nList.getLength(); temp++) {
		            Node nNode = nList.item(temp);
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		            	Element eElement = (Element) nNode;
		            	String title=eElement.getElementsByTagName("title").item(0).getTextContent();
		            	String pub_date=eElement.getElementsByTagName("pubDate").item(0).getTextContent();
		            	String news_des_url=eElement.getElementsByTagName("link").item(0).getTextContent();
		            	String description=eElement.getElementsByTagName("description").item(0).getTextContent();
		            	String source_url=eElement.getElementsByTagName("guid").item(0).getTextContent();
		            	String itmes[]=source_url.split("=");
		            	source_url=itmes[1];
		            	boolean isConteins=false;
		            	for(String str:blacklist_website)
		            	{
		            		isConteins=source_url.contains(str);
		            	}
		            	if(!isConteins)
		            	{
		            		String img_url="";
			            	description=description.replace("\"", "\"");
			            	Pattern pattern = Pattern.compile("img src=\"(.*?)\"");
			            	Matcher matcher = pattern.matcher(description);
			        		while (matcher.find()) {
			        			img_url=matcher.group(1);
			        		}
			        		ad.setNews_title(title);
			            	pub_date=convertPublicationDate(pub_date);
			            	ad.setPublication_date(pub_date);
			            	ad.setNews_description_url(news_des_url);
			            	ad.setNews_description(description);
			            	ad.setEntity_id(ad.getCelebrity_id());
			            	ad.setEntity_type_id(ENTITY_CELEBRITY);
			            	ad.setImage_url(img_url);
			            	ad.setNews_source(source_url);
			            	
			            	int is_news_present=0;
			            	String news_source="%"+ad.getNews_source()+"%";
			            	List<AdminModel> news_id=getCommonServices().getNewsId(news_source);
			            	for(AdminModel a:news_id)
			            	{
			            		is_news_present=a.getNews_id();
			            	}
			        		if(is_news_present==0)
			        		{
			        			getCommonServices().addEntityNews(ad);
			        		}
			        	//	blacklist_website=null;
		            	}
//		            	System.out.println(isConteins+"---"+source_url);
		            }
		         }
		}catch(Exception e)
		{
			System.out.println("getCelebrity_news().."+e);
		}
	}*/
	
	public String convertPublicationDate(String pub_date)
	{
		// Convert Mon, 09 Oct 2017 14:51:42 GMT type of date
		//		   Mon Jun 12 21:59:02 IST 2017
		// Neeed data in 2017-10-10 15:35:43
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
	
	@Scheduled(fixedDelay = 1000 * 60 * 90)
	public void getAccessToken() throws Exception
	{
		Date d=new Date();
    	System.out.println("Inside getAccessToken method : ..."+d);
		String access_token="";
		//			EAAZAsexLSRHoBADSkoEuOzZCVHlhlR5tRiLdKEdcqPKDFTOnoNXHyoJ0tmv3BeGOiDlPk9bdNLpF8FqqtYEdnZBAReFZBZBf1LPBiThw23Q7GDJ5rxE9V6kMChUIZAIVkgDm8zNB6TZCg67SRTiCctlDjGiHbd7sB9qTdZBtZCFZBO8gZDZD
//		String acc="EAAZAsexLSRHoBADSkoEuOzZCVHlhlR5tRiLdKEdcqPKDFTOnoNXHyoJ0tmv3BeGOiDlPk9bdNLpF8FqqtYEdnZBAReFZBZBf1LPBiThw23Q7GDJ5rxE9V6kMChUIZAIVkgDm8zNB6TZCg67SRTiCctlDjGiHbd7sB9qTdZBtZCFZBO8gZDZD";
		String token="";
		List<AdminModel> accessToken=getCommonServices().getToken();
		for(AdminModel a:accessToken)
		{
			token=a.getToken();
		}

		
		String token_query="https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id=1808125712811130&client_secret=39635124ad35c875485c229d889d87c8&fb_exchange_token="+token;
		URL url=new URL(token_query);
		URLConnection ucon=url.openConnection();
		ucon.connect();
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
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
		        getCommonServices().updateAccessTocken(access_token);
	}
//    @Scheduled(fixedDelay = 120000)
//	public void run()
//	{
//		Date d=new Date();
//		System.out.println("is it working --"+d);
//		try{
//					try {
//						//this.finalize();
//					} catch (Throwable e) {
//						e.printStackTrace();
//					}
//					getCelebrity_list();
//		}
//		catch(Exception e){System.out.println("Error..."+e);}
//	}
}
