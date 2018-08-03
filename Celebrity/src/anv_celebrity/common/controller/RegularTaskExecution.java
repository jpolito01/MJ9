package common.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
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
import twitter4j.conf.ConfigurationBuilder;
import admin.model.AdminModel;

//@EnableScheduling
//@Component
public class RegularTaskExecution{
	
Connection con=null;
//List<AdminModel> celebrity_info;
//List<String> celebrity_names;
int ENTITY_CELEBRITY=1;
//final int EBTITY_BAND=2;
//final int EBTITY_SPORT_TEAM=3;
//final int NEWS=1;
//final int POST=2;

//final int IMAGE=1;
//final int GIF=2;
//final int VIDEO=3;
//List<String> blacklist_website=null;



	public RegularTaskExecution() throws Exception
	{
//		celebrity_names=new ArrayList<String>();
//		celebrity_info=new ArrayList<AdminModel>();
//		blacklist_website=new ArrayList<>();
	   make_connection();
		getAccessToken();
	}
	public void make_connection() throws Exception
	{
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/anv_celebrity","root","root"); 
//		getCelebrity_list();
	}
	
	public void getCelebrity_list() throws SQLException
	{
		List<AdminModel> celebrity_info=new ArrayList<>();
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("select celebrity_id,celebrity_name,facebok_page_name,instagram_page_name,twitter_handle_name from anv_celebrity.celebrity_info where is_active=1");  
		while(rs.next())  
		{
			AdminModel ci=new AdminModel();
			ci.setCelebrity_id(rs.getInt("celebrity_id"));
			ci.setCelebrity_name(rs.getString("celebrity_name"));
			ci.setFacebok_page_name(rs.getString("facebok_page_name"));
			ci.setInstagram_page_name(rs.getString("instagram_page_name"));
			ci.setTwitter_handle_name(rs.getString("twitter_handle_name"));
			celebrity_info.add(ci);
//			String c_name=rs.getString("celebrity_name");
//			celebrity_names.add(c_name);
		}
		extract_celebrity_info(celebrity_info);
		celebrity_info=null;
		rs.close();
		stmt.close();
	}
	public List<String> getBlacklist_website()
	{
		List<String> blacklist_website=new ArrayList<>();
		String sql="SELECT  website_name FROM blacklist_website b";
		try{
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			String web_name="";
			while(rs.next())
			{
				web_name=rs.getString("website_name");
				blacklist_website.add(web_name);
			}
			rs.close();
			st.close();
		}catch(Exception e){System.out.println("error...blacklist website access.."+e);}
		return blacklist_website;
	}
	
	public void extract_celebrity_info(List<AdminModel> celebrity_info)
	{
		List<String> black_list=getBlacklist_website();
		for(AdminModel ad:celebrity_info)
		{
			getFacebookFeeds(ad);
			getInstgramFeeds(ad);
			getTwitterFeeds(ad);
			getCelebrity_news(ad,black_list);
		}
		black_list=null;
		celebrity_info=null;
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
	public void getCelebrity_news(AdminModel ad,List<String> blacklist_website)
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
			            	
			        		String sql="SELECT news_id FROM entity_news e where news_title like ?";
			        		PreparedStatement ps=con.prepareStatement(sql);
			        		ps.setString(1, "%"+ad.getNews_title()+"%");
			        		ResultSet rs=ps.executeQuery();
			        		int is_news_present=0;
			        		while(rs.next())
			        		{
			        			is_news_present=rs.getInt("news_id");
			        		}
			        		if(is_news_present==0)
			        		{
					        		sql="insert into entity_news(entity_id, entity_type_id, news_source, news_author, news_title, news_description_url, news_description, publication_date,image_url)" +
					        				" values(?,?,?,?,?,?,?,?,?)";
					        		 ps=con.prepareStatement(sql);
						        		ps.setInt(1, ad.getEntity_id());
						        		ps.setInt(2, ad.getEntity_type_id());
						        		ps.setString(3, ad.getNews_source());
						        		ps.setString(4, ad.getNews_author());
						        		ps.setString(5, ad.getNews_title());
						        		ps.setString(6, ad.getNews_description_url());
						        		ps.setString(7, ad.getNews_description());
						        		ps.setString(8, ad.getPublication_date());
						        		ps.setString(9, ad.getImage_url());
					        		    ps.executeUpdate();
					        		    ps.close();
					        		    rs.close();
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
	}
//	public void getCelebrity_news1(AdminModel ad)
//	{
//		String celebrity_name=ad.getCelebrity_name();
//	//	int celebrity_id=ad.getCelebrity_id();
//		try{
//			String query=celebrity_name.replace(" ", "+");
//			String url="https://news.google.com/news/?q="+query+"&output=rss";
//			String inputLine=hitURL(url);
//			InputSource is = new InputSource(new StringReader(inputLine));
//	         
//	         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//	         Document doc = dBuilder.parse(is);
//	         doc.getDocumentElement().normalize();
//	         NodeList nList = doc.getElementsByTagName("item");
//	         for (int temp = 0; temp < nList.getLength(); temp++) {
//		            Node nNode = nList.item(temp);
//		            
//		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//		            	Element eElement = (Element) nNode;
//		            	String title=eElement.getElementsByTagName("title").item(0).getTextContent();
//		            	String pub_date=eElement.getElementsByTagName("pubDate").item(0).getTextContent();
//		            	String news_des_url=eElement.getElementsByTagName("link").item(0).getTextContent();
//		            	String description=eElement.getElementsByTagName("description").item(0).getTextContent();
//		            	
//		            	String img_url="";
//		            	description=description.replace("\"", "\"");
//		            	Pattern pattern = Pattern.compile("img src=\"(.*?)\"");
//		        		Matcher matcher = pattern.matcher(description);
//		        		while (matcher.find()) {
//		        			img_url=matcher.group(1);
//		        		}
//		            	
//		            	ad.setNews_title(title);
//		            	pub_date=convertPublicationDate(pub_date);
//		            	ad.setPublication_date(pub_date);
//		            	ad.setNews_description_url(news_des_url);
//		            	ad.setNews_description(description);
//		            	ad.setEntity_id(ad.getCelebrity_id());
//		            	ad.setEntity_type_id(ENTITY_CELEBRITY);
//		            	ad.setImage_url(img_url);
//		            	
//		        		String sql="SELECT news_id FROM entity_news e where news_title like ?";
//		        		PreparedStatement ps=con.prepareStatement(sql);
//		        		ps.setString(1, "%"+ad.getNews_title()+"%");
//		        		ResultSet rs=ps.executeQuery();
//		        		int is_news_present=0;
//		        		while(rs.next())
//		        		{
//		        			is_news_present=rs.getInt("news_id");
//		        		}
//		        		if(is_news_present==0)
//		        		{
//				        		sql="insert into entity_news(entity_id, entity_type_id, news_source, news_author, news_title, news_description_url, news_description, publication_date,image_url)" +
//				        				" values(?,?,?,?,?,?,?,?,?)";
//				        		 ps=con.prepareStatement(sql);
//					        		ps.setInt(1, ad.getEntity_id());
//					        		ps.setInt(2, ad.getEntity_type_id());
//					        		ps.setString(3, ad.getNews_source());
//					        		ps.setString(4, ad.getNews_author());
//					        		ps.setString(5, ad.getNews_title());
//					        		ps.setString(6, ad.getNews_description_url());
//					        		ps.setString(7, ad.getNews_description());
//					        		ps.setString(8, ad.getPublication_date());
//					        		ps.setString(9, ad.getImage_url());
//				        		    ps.executeUpdate();
//				        		    rs.close();
//				        		    ps.close();
//		        		}
//		            }
//		         }
//		}catch(Exception e)
//		{
//			System.out.println("getCelebrity_news().."+e);
//		}
//	}
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
	public boolean isDuplicateFeed(String id,String feed_source)
	{
		boolean result=true;
		int flag=0;
		id=id.trim();
		try{
			String sql="SELECT id FROM entity_post e where post_source='"+feed_source+"' and id ='"+id+"'";
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				flag++;
			}
			rs.close();
			st.close();
		}catch(Exception e){System.out.println(e);}
		if(flag==0)
		{
			result=false;
		}
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
			try{
				String sql="SELECT id FROM entity_post e where post_source='Instagram'";
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery(sql);
				while(rs.next())
				{
					instagram_id.add(rs.getString("id"));
				}
				st.close();
				rs.close();
			}catch(Exception e){System.out.println("Check Instafeeds ....");}
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
					insta_id=(String) node.get("id");
					boolean res=isDuplicateFeed(insta_id+"","Instagram");
							if(!res)
							{
								ad.setEntity_id(ad.getCelebrity_id());
								ad.setEntity_type_id(1);
								ad.setPost_source("Instagram");
								ad.setPicture(image_url);
								message=message.replace("?", "");
								ad.setMessage(message);
								ad.setCretated_time(created_time);
								ad.setId(insta_id);
								SaveSocialMediaFeeds(ad);
							}
				}
			}catch(Exception e){System.out.println();}
		}
		instagram_id=null;
	}
	int counter=0;
	public int SaveSocialMediaFeeds(AdminModel insta_data)
	{
		counter++;
		int affected_rows=0;
		
				String sql="insert into entity_post (entity_id, entity_type_id, post_source, picture, name, message, story, cretated_time, id) "+
						" values(?,?,?,?,?,?,?,?,?)";
				try{
					PreparedStatement ps=con.prepareStatement(sql);
					ps.setInt(1, insta_data.getEntity_id());
					ps.setInt(2, insta_data.getEntity_type_id());
					ps.setString(3, insta_data.getPost_source());
					ps.setString(4, insta_data.getPicture());
					ps.setString(5, insta_data.getName());
					ps.setString(6, insta_data.getMessage());
					ps.setString(7, insta_data.getStory());
					ps.setString(8, insta_data.getCretated_time());
					ps.setString(9, insta_data.getId());
					affected_rows=ps.executeUpdate();
					ps.close();
				}catch(Exception e){System.out.println("Insert Instagram Data : "+e);}
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
	public boolean isDuplicateFeed(String id)
	{
		boolean result=true;
		int flag=0;
		id=id.trim();
		try{
			String sql="SELECT id FROM entity_post e where post_source='Twitter' and id ='"+id+"'";
			
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next())
			{
				flag++;
			}
			st.close();
			rs.close();
		}catch(Exception e){System.out.println(e);}
		if(flag==0)
		{
			result=false;
		}
		return result;
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
            	boolean res=isDuplicateFeed(st.getId()+"","Twitter");
            	
            	if(res==false)
            	{
            		ad.setId(st.getId()+"");
            		ad.setEntity_type_id(1);
            		ad.setPost_source("Twitter");
            		String message=st.getText().replace("?", "");
            		ad.setEntity_id(ad.getCelebrity_id());
            		message=message.replace("?", "");
            		ad.setMessage(message);
            		
            		ad.setCretated_time(getTwitterDate(st.getCreatedAt()+""));
            		
            		MediaEntity[] mt= st.getMediaEntities();
                	for(int i=0;i<mt.length;i++)
                	{
                		ad.setPicture(mt[i].getMediaURLHttps());
                		
                	}
                	mt=null;
                	SaveSocialMediaFeeds(ad);
            	}
            	
            }
            user_status=null;
        } catch (TwitterException te) {
            System.out.println("Failed to get timeline: " + te.getMessage());
        }
		return "success";
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
					        	 String get_saved_data="SELECT id FROM entity_post e where id=?";
					        	 PreparedStatement p=con.prepareStatement(get_saved_data);
					        	 p.setString(1, id);
					        	 ResultSet rs=p.executeQuery();
					        	 int save_flag=0;
					        	 while(rs.next())
					        	 {
					        		 if(rs.getString("id").equals(id))
					        		 {
					        			 save_flag=1;
					        		 }
					        	 }
					        	 p.close();
					        	 rs.close();
					        	 if(save_flag==0)
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
					        	 String message=json1.get("message")+"";
					        	 message=message.replace("?", "");
					        	 String picture=json1.get("picture")+"";
					        	 String story=json1.get("story")+"";
					        	 String cretated_time=json1.get("created_time")+"";
					        	 cretated_time=cretated_time.replace("T", " ");
					        	 String time[]=cretated_time.split("\\+");
					        	 cretated_time=time[0];
					        	 
					        	 
					        		 String insert_feeds="insert into entity_post(entity_id, entity_type_id, post_source, picture, name, message, story, cretated_time,id)values" +
							        	 		"(?,?,?,?,?,?,?,?,?)";
							        	 PreparedStatement ps=con.prepareStatement(insert_feeds);
							        	 ps.setInt(1, ad.getCelebrity_id());
							        	 ps.setInt(2, 1);
							        	 ps.setString(3, "Facebook");
							        	 ps.setString(4, picture);
							        	 ps.setString(5, name);
							        	 ps.setString(6, message);
							        	 ps.setString(7, story);
							        	 ps.setString(8, cretated_time);
							        	 ps.setString(9, id);
							        	 ps.executeUpdate();
							        	 ps.close();
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
		Statement st=con.createStatement();
		String sql="SELECT token FROM access_token a;";
		String token="";
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			token=rs.getString("token");
		}
		st.close();
		rs.close();
		return token;
	}
	
    @Scheduled(fixedDelay = 1000 * 60 * 90)
	public void getAccessToken() throws Exception
	{
		Date d=new Date();
    	System.out.println("Inside getAccessToken method : ..."+d);
		String access_token="";
		//			EAAZAsexLSRHoBADSkoEuOzZCVHlhlR5tRiLdKEdcqPKDFTOnoNXHyoJ0tmv3BeGOiDlPk9bdNLpF8FqqtYEdnZBAReFZBZBf1LPBiThw23Q7GDJ5rxE9V6kMChUIZAIVkgDm8zNB6TZCg67SRTiCctlDjGiHbd7sB9qTdZBtZCFZBO8gZDZD
//		String acc="EAAZAsexLSRHoBADSkoEuOzZCVHlhlR5tRiLdKEdcqPKDFTOnoNXHyoJ0tmv3BeGOiDlPk9bdNLpF8FqqtYEdnZBAReFZBZBf1LPBiThw23Q7GDJ5rxE9V6kMChUIZAIVkgDm8zNB6TZCg67SRTiCctlDjGiHbd7sB9qTdZBtZCFZBO8gZDZD";
		Statement st=con.createStatement();
		String sql="SELECT token FROM access_token a;";
		String token="";
		ResultSet rs=st.executeQuery(sql);
		while(rs.next())
		{
			token=rs.getString("token");
		}
		st.close();
		rs.close();
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
		        String update_token="update access_token set token=? where token_id=1";
		        PreparedStatement ps=con.prepareStatement(update_token);
		        ps.setString(1, access_token);
		        ps.executeUpdate();
		       ps.close();
	}
    //@Scheduled(fixedDelay = 1200000)
	public void run()
	{
		Date d=new Date();
		System.out.println("is it working --"+d);
		try{
					try {
						this.finalize();
					} catch (Throwable e) {
						e.printStackTrace();
					}
					getCelebrity_list();
		}
		catch(Exception e){System.out.println("Error..."+e);}
	}
	
	
}


/*
 * if(records>0)
				        		{
				        			sql="SELECT news_id FROM entity_news e order by news_id desc limit 1";
				        			Statement st=con.createStatement();
				        			rs=st.executeQuery(sql);
				        			while(rs.next())
				        			{
				        				int news_id=rs.getInt("news_id");
				        				ad.setNews_id(news_id);
				        				ad.setNews_id_or_post_id(ad.getNews_id());
				        			}
				        		}
 * ad.setIs_news_or_post(NEWS);
				            	ad.setContent_type_id(IMAGE);
				            	ad.setContent_path(img_url);
				        		if(ad.getNews_id()!=0)
				        		{
				        			sql="insert into news_post_image_map (news_id_or_post_id, is_news_or_post, content_type_id, content_path) " +
				        								"	values(?,?,?,?)";
				        			ps=con.prepareStatement(sql);
				        			ps.setInt(1, ad.getNews_id_or_post_id());
				        			ps.setInt(2, ad.getIs_news_or_post());
				        			ps.setInt(3, ad.getContent_type_id());
				        			ps.setString(4, ad.getContent_path());
				        			int img_details=ps.executeUpdate();
				        		}
 * 
 * 
 * */
