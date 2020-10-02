
// users input lists 
package common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import admin.model.AdminModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.java.swing.plaf.windows.resources.windows;
import common.model.CommonModel;
import common.services.CommonServices;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Controller
public class CommonController extends DataManipulation {
		@Autowired
		private CommonServices commonServices;
		
		public CommonServices getCommonServices() {
			return commonServices;
		}
		
		public void setCommonServices(CommonServices commonServices) {
			this.commonServices = commonServices;
		}
		

		public Logger logger = Logger.getLogger(this.toString());
		/**
		 * This method logs entry for a method
		 * @param methodName
		 */
		protected void methodIn(final String methodName) {
			if(logger.isDebugEnabled()){
				logger.debug(String.format(IN,this.getClass().getName(), methodName));
			}
		}
		
		private static final String ALGORITHM = "AES";
		private static final String KEY = "59sr16adhMEk92gs";
		
		private static Key generateKey() throws Exception
		{
			Key key = new SecretKeySpec(CommonController.KEY.getBytes(),CommonController.ALGORITHM);
			return key;
		}

		public static String encrypt(String value) throws Exception
		{
			Key key = generateKey();
			Cipher cipher = Cipher.getInstance(CommonController.ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
			String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
			return encryptedValue64;

		}

		public static String decrypt(String value) throws Exception
		{
			Key key = generateKey();
			Cipher cipher = Cipher.getInstance(CommonController.ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(value);
			byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
			String decryptedValue = new String(decryptedByteValue,"utf-8");
			return decryptedValue;

		}


		/**
		 * This method logs out for a method
		 * @param methodName
		 */
		protected void methodOut(final String methodName) {
			if(logger.isDebugEnabled()){
				logger.debug(String.format(OUT,this.getClass().getName(), methodName));
			}
		}
		/**
		 * IN represents log in method
		 */
		private static final String IN="%s::%s: IN ";

		/**
		 * OUT represents log out from method
		 */
		private static final String OUT="%s::%s: OUT ";
		
	@SuppressWarnings("unused")
	@RequestMapping(value="home",method=RequestMethod.GET)
	public String home_page(ModelMap map,HttpSession session) throws IOException
	{
//		String str1="Get yours now! ?? #ForcaGoa";
//		str1=str1.replace("?", "");
//		System.out.println("After Replace.str1.."+str1);
		if(session.getAttribute("user_name")==null)
		{
			session.setAttribute("user_name", "");
		}
		
		UserSession u=new UserSession(session);
		List<AdminModel> entity_news=null;
		List<AdminModel> like_cout=null;
		List<AdminModel> comment_cout=null;
		int user_id=u.getUser_id();
		
		int highest_news_count=0;
		int celebrity_id_tr=0;
		int celebrity_of_the_day=0;
		String news_date="";
		List<AdminModel> trending_count=getTopNewsCount();
		//List<AdminModel> trending_count=getCommonServices().getTrending_celebrities(0);
		List<AdminModel> trendingCelebrity_news=new ArrayList<>();
		int last_index=0;
		for(AdminModel a:trending_count)
		{
			last_index++;
			
				celebrity_id_tr=a.getEntity_id();
				news_date=a.getNews_date();
				List<AdminModel> temp=getCommonServices().getTrendingCelebrity_news(celebrity_id_tr, news_date,u.getUser_id());
				
				for(AdminModel t:temp)
				{
					trendingCelebrity_news.add(t);
				}
				
			
		}
		List<AdminModel> celebrity_of_the_day_id=getCommonServices().getCelebrityOfTheDayId();
		for(AdminModel a:celebrity_of_the_day_id)
		{
			celebrity_of_the_day=a.getCelebrity_id();
		}
		List<AdminModel> celebrity_ofthe_day= getCommonServices().getCelebrity_Profile(celebrity_of_the_day,u.getUser_id());
		List<AdminModel> celebrity_cat=getCommonServices().getCelebritycategory_profile(celebrity_of_the_day);
		//List<AdminModel> trendingCelebrity_news=getCommonServices().getTrendingCelebrity_news(celebrity_id_tr, news_date,u.getUser_id());
		
		int day_interval=0;
		//entity_news=getCommonServices().getEntity_news(user_id);
		entity_news=getCommonServices().getLatestNews(user_id);
		/*while(entity_news.size()<1)
		{
			day_interval++;
			entity_news=getCommonServices().getEntity_news(user_id,day_interval);
		}*/
		
		like_cout=getCommonServices().getNewsLike_count(1);
		comment_cout=getCommonServices().getComment_Count();
		for(AdminModel tc:trendingCelebrity_news)
		{
			for(AdminModel a:like_cout)
			{
				if(a.getPost_id_or_news_id()==tc.getNews_id())
				{
					tc.setLike_count(a.getLike_count());
				}
			}
			for(AdminModel cc:comment_cout)
			{
				if(cc.getPost_id_or_news_id()==tc.getNews_id())
				{
					tc.setComment_count(cc.getComment_count());
				}
			}
		}
			
		
		String type="li-featured";
		if(map.get("type")!=null)
		{
			type=map.get("type")+"";
			type=type.trim();
			if(type.equals("featured"))
			{
				type="li-featured";
			}
			else if(type.equals("trending"))
			{
				entity_news=null;
				type="li-trending";
			}
			else if(type.equals("latest"))
			{
				
				entity_news=getCommonServices().getLatestNews(user_id);
				type="li-latest";
			}
		}
		if(map.get("show_comments")!=null)
		{
			int news_id=Integer.parseInt(map.get("show_comments")+"");
			int activity_type_id=Integer.parseInt(session.getAttribute("activity_type_id")+"");
			List<AdminModel> news_comments=getCommonServices().getNews_Comments(news_id,activity_type_id);
			map.addAttribute("news_comments", news_comments);
		}
		List<AdminModel> movie_blogs= getCommonServices().getMovies_blogs();
		int last_blog_id=0;
		for(AdminModel movie_blogs1:movie_blogs){
			last_blog_id=movie_blogs1.getBlog_id();
		}
		
		for(AdminModel a:trendingCelebrity_news)
	    {
	    	String title=replaceSpecialCharacters(a.getNews_title());
	    	String description=replaceSpecialCharacters(a.getNews_description());
	        a.setNews_title(title);
	        a.setNews_description(description);
	    }
	    
	    for(AdminModel a:entity_news)
	    {
	    	String title=replaceSpecialCharacters(a.getNews_title());
	        a.setNews_title(title);
	    }
		session.setAttribute("last_blog_id", last_blog_id);
		
        map.addAttribute("movie_blogs", movie_blogs);
		map.addAttribute("celebrity_ofthe_day", celebrity_ofthe_day);
		map.addAttribute("celebrity_cat", celebrity_cat);
		map.addAttribute("trending_news", trendingCelebrity_news);
		map.addAttribute("comment_cout", comment_cout);
		map.addAttribute("like_cout", like_cout);
		map.addAttribute("entity_news", entity_news);
		map.addAttribute("type",type);

		
		if(map.get("error_message")!=null)
		{
			map.addAttribute("error_message", map.get("error_message")+"");
		}
		
		return "Common/home";
//		return "test";
	}
	@RequestMapping(value="home",method=RequestMethod.POST)
	public String home_page_post(HttpSession session,HttpServletRequest request,RedirectAttributesModelMap map)
	{
		
		if(request.getParameter("type")!=null)
		{
			map.addFlashAttribute("type", request.getParameter("type"));
		}
		
		return "redirect:home";  
	}
	
	@RequestMapping(value="new-release",method=RequestMethod.GET)
	public String new_ReleaseGet()
	{
		System.out.println("NewReleased.jsp");
		return  "Common/NewReleased";
	}
	@RequestMapping(value="hottest-band",method=RequestMethod.GET)
	public String HottestBandGet()
	{
		
		return "Common/HottestBand";
	}
//	@RequestMapping(value="actors-models",method=RequestMethod.GET)
	@RequestMapping(value="all-celebrities",method=RequestMethod.GET)
	public String AllCelebrities(ModelMap map,HttpServletRequest request,HttpSession session)
	{
		UserSession u=new UserSession(session);
		int user_id=0;
		if(u.getUser_id()>0)
		{
			user_id=u.getUser_id();
		}
		String letter="A";
		
		if(request.getParameter("q")==null)
		{
			List<AdminModel> celebrity_info=getCommonServices().getAllCelebrities(user_id);
			List<AdminModel> follower_count=getCommonServices().getAllCelebrityFollowers();
			
			map.addAttribute("celebrity_follower_count", follower_count);
			map.addAttribute("celebrity_info", celebrity_info);
			return "Common/ActorCelebrity";
		}
		if(request.getParameter("q")!=null)
		{
			letter=request.getParameter("q")+"";
			if(letter.equals(""))
			{
				letter="A";
			}
			//session.setAttribute("letter", letter);
		}
		String q=letter+"%";
		List<AdminModel> celebrity_info=getCommonServices().getCelebrityInfo_bySorted(q,user_id);
		
		List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount_sorted(q);
		map.addAttribute("celebrity_follower_count", follower_count);
		map.addAttribute("celebrity_info", celebrity_info);
		map.addAttribute("latter", letter);
		return "Common/ActorCelebrity";
	}
	@RequestMapping(value="actors-models",method=RequestMethod.GET)
	public String ActorsActresssGet(ModelMap map,HttpServletRequest request,HttpSession session)
	{
		UserSession u=new UserSession(session);
		int user_id=0;
		if(u.getUser_id()>0)
		{
			user_id=u.getUser_id();
		}
		String letter="A";
		int category_id=1; // category id 3 is for Actress 1 for Actor
		if(request.getParameter("q")==null)
		{
			List<AdminModel> Actor=getCommonServices().getCelebrityInfo_ByCategoryId(user_id, category_id);
			category_id=3; 
			List<AdminModel> Actress=getCommonServices().getCelebrityInfo_ByCategoryId(user_id, category_id);
			List<AdminModel> celebrity_info=Actor;
			for(AdminModel a:Actress)
			{
				celebrity_info.add(a);
			}
			List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount();
			map.addAttribute("celebrity_follower_count", follower_count);
			map.addAttribute("celebrity_info", celebrity_info);
			map.addAttribute("latter", "");
			return "Common/ActorActress";
		}

//		if(session.getAttribute("letter")!=null)
//		{
//			if(!(session.getAttribute("letter")+"").equals(""))
//			{
//				letter=session.getAttribute("letter")+"";
//			}
//		}
		
		if(request.getParameter("q")!=null)
		{
			letter=request.getParameter("q")+"";
			if(letter.equals(""))
			{
				letter="A";
			}
			//session.setAttribute("letter", letter);
		}
		String q=letter+"%";
		List<AdminModel> Actor=getCommonServices().getSortedCelebrityInfo_ByCategoryId(q, user_id, category_id);
		category_id=3; 
		List<AdminModel> Actress=getCommonServices().getSortedCelebrityInfo_ByCategoryId(q, user_id, category_id);
		List<AdminModel> celebrity_info=Actor;
		for(AdminModel a:Actress)
		{
			
			celebrity_info.add(a);
		}
		
		List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount_sorted(q);
		
		map.addAttribute("celebrity_follower_count", follower_count);
		map.addAttribute("celebrity_info", celebrity_info);
		map.addAttribute("latter", letter);
		
		return "Common/ActorActress";
	}
	@RequestMapping(value="athletes",method=RequestMethod.GET)
	public String AthletesGet(HttpServletRequest request,HttpSession session,ModelMap map)
	{
		UserSession u=new UserSession(session);
		int user_id=0;
		if(u.getUser_id()>0)
		{
			user_id=u.getUser_id();
		}
		String letter="A";
		int category_id=5; // category id 5 is for Athletes
		if(request.getParameter("q")==null)
		{
			List<AdminModel> celebrity_info=getCommonServices().getCelebrityInfo_ByCategoryId(user_id, category_id);
			List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount();
			map.addAttribute("celebrity_follower_count", follower_count);
			map.addAttribute("celebrity_info", celebrity_info);
			map.addAttribute("latter", "");
			return "Common/Athletes";
		}
//		if(session.getAttribute("letter")!=null)
//		{
//			if(!(session.getAttribute("letter")+"").equals(""))
//			{
//				letter=session.getAttribute("letter")+"";
//			}
//		}
		if(request.getParameter("q")!=null)
		{
			letter=request.getParameter("q")+"";
			if(letter.equals(""))
			{
				letter="A";
			}
			//session.setAttribute("letter", letter);
		}
		String q=letter+"%";
		List<AdminModel> celebrity_info=getCommonServices().getSortedCelebrityInfo_ByCategoryId(q, user_id, category_id);
		List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount_sorted(q);
		
		map.addAttribute("celebrity_follower_count", follower_count);
		map.addAttribute("celebrity_info", celebrity_info);
		map.addAttribute("latter", letter);
		return "Common/Athletes";
	}
	@RequestMapping(value="musician",method=RequestMethod.GET)
	public String musician(HttpServletRequest request,HttpSession session,ModelMap map)
	{
		UserSession u=new UserSession(session);
		int user_id=0;
		if(u.getUser_id()>0)
		{
			user_id=u.getUser_id();
		}
		String letter="A";
		int category_id=2; // category id 2 is for musician
		if(request.getParameter("q")==null)
		{
			List<AdminModel> celebrity_info=getCommonServices().getCelebrityInfo_ByCategoryId(user_id, category_id);
			List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount();
			map.addAttribute("celebrity_follower_count", follower_count);
			map.addAttribute("celebrity_info", celebrity_info);
			map.addAttribute("latter", "");
			return "Common/Musician";
		}
//		if(session.getAttribute("letter")!=null)
//		{
//			if(!(session.getAttribute("letter")+"").equals(""))
//			{
//				letter=session.getAttribute("letter")+"";
//			}
//		}
		if(request.getParameter("q")!=null)
		{
			letter=request.getParameter("q")+"";
			if(letter.equals(""))
			{
				letter="A";
			}
			//session.setAttribute("letter", letter);
		}
		String q=letter+"%";
		List<AdminModel> celebrity_info=getCommonServices().getSortedCelebrityInfo_ByCategoryId(q, user_id, category_id);
		List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount_sorted(q);
		
		map.addAttribute("celebrity_follower_count", follower_count);
		map.addAttribute("celebrity_info", celebrity_info);
		map.addAttribute("latter", letter);
		return "Common/Musician";
	}
	
	
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register_get(ModelMap map)
	{
		
		map.addAttribute("register", "yes");
		return "redirect:home"; 
	}
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register_post(CommonModel cm,HttpSession session,RedirectAttributesModelMap map) throws Exception
	{
		UserSession u=new UserSession(session);
		if(cm.getFirst_name().equals("") || cm.getLast_name().equals("") || cm.getPassword().equals("") || cm.getEmail_id().equals(""))
		{
			return "redirect:home"; 
		}
		List<CommonModel>check_existing=getCommonServices().checkEmail_id(cm.getEmail_id());
		if(!check_existing.isEmpty())
		{
			map.addFlashAttribute("error_message", "This email id is already present.");
			return "redirect:home"; 
		}
		//cm.setEmail_id(cm.getUser_name());
		cm.setUser_name(cm.getEmail_id());
		getCommonServices().registerUser(cm);
		
		List<CommonModel> user_info=getCommonServices().getLast_addedUser();
		for(CommonModel c:user_info)
		{
			if(c.getEmail_id().equals(cm.getEmail_id()))
			{
				cm.setUser_id(c.getUser_id());
				cm.setRole_id(2);
				cm.setPassword(CommonController.encrypt(cm.getPassword()));
				getCommonServices().insertLoginInfo(cm);
			}
		}
		
		u.setUser_id(cm.getUser_id());
		u.setRole_id(cm.getRole_id());
		u.setUser_name(cm.getUser_name());
		u.setUser_first_name(cm.getFirst_name());
		
		map.addFlashAttribute("message", "Welcome to NowYaNo!!! Your account has been created successfully.");
		return "redirect:home";  
	}
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login_page_get()
	{
		return "redirect:home"; 
	}
		
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login_page(@Valid CommonModel model,HttpSession session,RedirectAttributesModelMap map) throws Exception
	{
		UserSession u=new UserSession(session);
		int role_id=0;
		int user_id=0;
		List<CommonModel> login_info=commonServices.validateLogin(model.getUser_name());
		for(CommonModel cm:login_info)
		{
				if(!model.getPassword().equals(CommonController.decrypt(cm.getPassword()))){
					map.addFlashAttribute("error_message", "Invalid login.");
					return "redirect:home";
				}
				role_id=cm.getRole_id();
				user_id=cm.getUser_id();
				
				u.setUser_id(user_id);
				u.setRole_id(role_id);
				u.setUser_name(model.getUser_name());
				u.setUser_first_name(cm.getFirst_name());
				
		}
		if(role_id==1)//Admin
		{
			return "redirect:admin-home";
		}
		else if(role_id==2) //User
		{
			
		}
		else{
			map.addFlashAttribute("error_message", "Invalid login.");
			return "redirect:home";
		}
		return "redirect:home";
	}
	
	@RequestMapping(value="commonSearch",method=RequestMethod.GET)
	public String CommonSearch(ModelMap map,HttpSession session,RedirectAttributesModelMap redirect)
	{
		UserSession u=new UserSession(session);
		int user_id=u.getUser_id();
		if(map.get("search_query")!=null)
		{
			String q=map.get("search_query")+"";
			if(!q.equals(""))
			{
				char letter=Character.toUpperCase(q.charAt(0));
				q="%"+q+"%";
				List<AdminModel> celebrity_info=getCommonServices().getCelebrityInfo_bySorted(q,user_id);
				List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount_sorted(q);
				map.addAttribute("celebrity_follower_count", follower_count);
				map.addAttribute("celebrity_info", celebrity_info);
				map.addAttribute("latter", letter);
				session.setAttribute("letter", letter);
				if(celebrity_info.size()==0)
				{
					redirect.addFlashAttribute("search_key", map.get("search_query")+"");
					return "redirect:user-searches";
				}
				
			}
			
			
		}
		
		return "Common/ActorCelebrity";
	}
	
	@RequestMapping(value="commonSearch",method=RequestMethod.POST)
	public String CommonSearchPost(@Valid CommonModel c, HttpServletRequest request,RedirectAttributesModelMap map)
	{
		if(c.getTxt_search()!=null)
		{
			map.addFlashAttribute("search_query", c.getTxt_search());
		}
		else{
			String str=request.getParameter("search_query")+"";
			if(!str.equals(""))
			{
				map.addFlashAttribute("search_query", str);
			}
		}
		return "redirect:commonSearch";
	}
	@RequestMapping(value="user-searches",method=RequestMethod.GET)
	public String UserSearches(ModelMap map)
	{
		if(map.get("search_key")!=null)
		{
			int search_count=1;
			int id=1;
			String search_key=map.get("search_key").toString();
			List<AdminModel> previous_searches=getCommonServices().getUser_Searches(search_key);
			for(AdminModel a:previous_searches)
			{
				search_count=a.getSearch_count()+1;
				id=Integer.parseInt(a.getId());
			}
			if(search_count==1)
			{
				//Insert
				getCommonServices().insertNewUser_searches(search_key, search_count);
			}
			else
			{
				getCommonServices().updateNewUser_searches(search_count, id);
				//Update Count
			}
			List<AdminModel> user_searches_news=getCelebrityNewsfromAPI(map.get("search_key")+"");
			map.addAttribute("user_searches_news", user_searches_news);
		}
		return "Common/user_searches"; 
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
	
	public List<AdminModel> RandomSearchNews(String celebrity_name)
	{
		List<AdminModel> celebrity_news=new ArrayList<>();
		
		String query=celebrity_name.replace(" ", "+");
		String url="https://news.google.com/news/?q="+query+"&output=rss";
		
		try{
		String inputLine=hitURL(url);
		InputSource is = new InputSource(new StringReader(inputLine));
         
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(is);
         doc.getDocumentElement().normalize();
         AdminModel ad=new AdminModel();
         NodeList nList = doc.getElementsByTagName("item");
         for (int temp = 0; temp < nList.getLength(); temp++) {
        	 ad=new AdminModel();
	            Node nNode = nList.item(temp);
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	            	Element eElement = (Element) nNode;
	            	String title=eElement.getElementsByTagName("title").item(0).getTextContent();
	            	String pub_date=eElement.getElementsByTagName("pubDate").item(0).getTextContent();
	            	String news_des_url=eElement.getElementsByTagName("link").item(0).getTextContent();
	            	String description=eElement.getElementsByTagName("description").item(0).getTextContent();
	            	
	            	String img_url="";
	            	description=description.replace("\"", "\"");
	            	Pattern pattern = Pattern.compile("img src=\"(.*?)\"");
	        		Matcher matcher = pattern.matcher(description);
	        		while (matcher.find()) {
	        			img_url=matcher.group(1);
	        		}
	            	ad.setNews_title(title);
	            	ad.setPublication_date(pub_date);
	            	ad.setNews_description_url(news_des_url);
	            	ad.setNews_description(description);
	            	ad.setImage_url(img_url);
	            	celebrity_news.add(ad);
	            }
         }
		}catch(Exception e){System.out.println("userSearchesNews...error.."+e);}
	       return celebrity_news;
	}
	
	@RequestMapping(value="celebrity-profile",method=RequestMethod.GET)
	public String celebrityProfileGet(ModelMap map,HttpSession session,HttpServletRequest request)
	{
		UserSession u=new UserSession(session);
		
		// pagination start
		
				int total=0;
				int limit=50;
				int offset=0;
				
				if(session.getAttribute("limit")==null)
				{
					session.setAttribute("limit", 50);
				}
				limit=Integer.parseInt(session.getAttribute("limit").toString());
				
				if(map.get("offset")!=null)
				{
					offset=Integer.parseInt(map.get("offset")+"");
				}
				map.addAttribute("offset", offset);
				map.addAttribute("limit", limit);
				
				// pagination end
				
				List<AdminModel> all_posts_count=null;
				int total_count=0;
				
					List<AdminModel> like_cout=getCommonServices().getAllLike_count();
					
					
					map.addAttribute("like_cout",like_cout);
				
				
		if(session.getAttribute("celebrity_id")!=null)
		{
			String query="all_post";
			boolean tabFlag=false;
			boolean tabFlag1=false;
			if(map.get("pagination_active_tab")!=null)
			{
				query=map.get("pagination_active_tab").toString();
				tabFlag1=true;
			}
			else{
				if(request.getParameter("tab")!=null)
				{
					query="all_post";
				}
			}
			String celebrity_id=session.getAttribute("celebrity_id")+"";
			boolean celebrity_is_movie=false;
			if(!celebrity_id.equals(""))
			{
				int cid=Integer.parseInt(celebrity_id);
				List<AdminModel> celebrity_list=getCommonServices().getCelebrity_Profile(cid,u.getUser_id());
				map.addAttribute("celebrity_list", celebrity_list);
				List<AdminModel> celebrity_post=null;
				List<AdminModel> movieCast=null;
				List<AdminModel> celebrity_cat=getCommonServices().getCelebritycategory_profile(cid);
				List<AdminModel> comment_cout=getCommonServices().getComment_Count();
				List<AdminModel> celebrity_may_know=new ArrayList<>();
				
				
				List<AdminModel> movie_producer=new ArrayList<>();
				List<AdminModel> movie_director=new ArrayList<>();
				List<AdminModel> movie_actor=new ArrayList<>();
				List<AdminModel> movie_actress=new ArrayList<>();
				List<AdminModel> movie_others=new ArrayList<>();
				
				/*List<AdminModel> movie_producer_news=new ArrayList<>();
				List<AdminModel> movie_director_news=new ArrayList<>();
				List<AdminModel> movie_actor_news=new ArrayList<>();
				List<AdminModel> movie_actress_news=new ArrayList<>();
				List<AdminModel> movie_others_news=new ArrayList<>();*/
				
				if(request.getParameter("q")!=null)
				{
					query=request.getParameter("q")+"";
				}
				if(map.get("sub_tab_id")!=null)
				{
					query=map.get("sub_tab_id")+"";
				}
				String homepagePostedValue="";
				if(map.get("news_source")!=null && !tabFlag1){
					homepagePostedValue=map.get("news_source").toString();
					//System.out.println("homepagePostedValue=="+homepagePostedValue);
					//if(map.get("news_source").equals(homepagePostedValue)){
					tabFlag=true;
					//}
				}
				boolean movieFlag=false;
				int cat_id=0;
				for(AdminModel celebrity_cat1:celebrity_cat){
					cat_id=celebrity_cat1.getCategory_id();
					if(cat_id==6){
						movieFlag=true;							
						}
				}
				
				map.addAttribute("tab",query);
				
					if(query.equals("all_post") && !tabFlag)
					{
						all_posts_count=getCommonServices().getAllNewsPostCount(cid);
						for(AdminModel a:all_posts_count)
						{
							total_count=total_count+1;
						}
						
						celebrity_post=getCommonServices().getCelebrityNews_byCelebrityId(cid,u.getUser_id(),limit,offset);
							
						/*if(movieFlag){
							
							celebrity_post=getCommonServices().getCelebrityNewsMovies_byCelebrityId(cid,u.getUser_id(),limit,offset);
							celebrity_is_movie=true;
							movieCast=getCommonServices().getMovieCast(cid,u.getUser_id());
for(AdminModel movieCast1:movieCast){
	String movieChara="";
	List<AdminModel> movieCastCharacter=getCommonServices().getMovieCastCharacter(cid,movieCast1.getCelebrity_id());
	List<AdminModel> followCount=getCommonServices().getUserFollowerCount(movieCast1.getCelebrity_id());
for(AdminModel followCount1:followCount){
		movieCast1.setFollower_count(followCount1.getFollower_count());
}
//	movieCast1.setFollower_count(followCount);
	int j=0;
	for(AdminModel movieCastCharacter1:movieCastCharacter){
	//	System.out.println("Character"+movieCastCharacter1.getCaracter_name());
		movieChara=movieChara+movieCastCharacter1.getCaracter_name();
		if(j<movieCastCharacter.size()-1){
			movieChara=movieChara+"/ ";
		}
		j++;
		}
	movieCast1.setCaracter_name(movieChara);
}

						}else{
						celebrity_post=getCommonServices().getCelebrityNews_byCelebrityId(cid,u.getUser_id(),limit,offset);
						}
						for(AdminModel a:celebrity_post)
						{
							
							if(a.getNews_description()!=null)
							{
								String s=a.getNews_description();
								Pattern pattern = Pattern.compile("<font size=\"-1\">(.*?)</font>");
								Matcher matcher = pattern.matcher(s);
								System.out.println("matcher.find() : "+"\tCount  : "+matcher.groupCount());
								
								while (matcher.find()) {
									String str=matcher.group(1);
									a.setNews_description(str);
								}
							}
						}*/
					}
					else if(query.equals("twitter"))
					{
						total_count=getCommonServices().getAllPostsCount(cid,"Twitter");

						celebrity_post=getCommonServices().getTwitter_post(cid, u.getUser_id(),limit,offset);
						
						map.addAttribute("tab", "twitter");
					}
					else if(query.equals("facebook"))
					{
						total_count=getCommonServices().getAllPostsCount(cid,"Facebook");
						
						celebrity_post=getCommonServices().getFacebook_post(cid,u.getUser_id(),limit,offset);
						
						map.addAttribute("tab", "facebook");

					}
					else if(query.equals("news") || homepagePostedValue.equals("news"))
					{
						/*if(movieFlag){
							all_posts_count=getCommonServices().getAllNewsCountMovie(cid);
							for(AdminModel a:all_posts_count)
							{
								total_count=a.getNews_count();
							}
							celebrity_post=getCommonServices().getOnlyNewsMovie(cid, u.getUser_id(),limit,offset);

						}else{*/
						all_posts_count=getCommonServices().getAllNewsCount(cid);
						for(AdminModel a:all_posts_count)
						{
							total_count=a.getNews_count();
						}
						
//						celebrity_post=getCommonServices().getCelebrityNews_byCelebrityId(cid,u.getUser_id());
						celebrity_post=getCommonServices().getOnlyNews(cid, u.getUser_id(),limit,offset);
						
						map.addAttribute("tab", "news");
						//}
					}
					else if(query.equals("instagram"))
					{
						total_count=getCommonServices().getAllPostsCount(cid,"Instagram");
						
						celebrity_post=getCommonServices().getInstagram_post(cid, u.getUser_id(),limit,offset);
						
						map.addAttribute("tab", "instagram");
					}
				
					if(map.get("show_comments")!=null)
					{
						int news_id=Integer.parseInt(map.get("show_comments")+"");
						int activity_type_id=Integer.parseInt(session.getAttribute("activity_type_id")+"");
						List<AdminModel> news_comments=getCommonServices().getNews_Comments(news_id,activity_type_id);
						map.addAttribute("news_comments", news_comments);
					}
					for(AdminModel a:celebrity_post)
				    {
				    	String title=replaceSpecialCharacters(a.getNews_title());
				        a.setNews_title(title);
				    }
				map.addAttribute("celebrity_cat", celebrity_cat);
				map.addAttribute("celebrity_post", celebrity_post);
				map.addAttribute("comment_cout",comment_cout);
				
				if(movieFlag){
					
					//celebrity_post=getCommonServices().getCelebrityNewsMovies_byCelebrityId(cid,u.getUser_id(),limit,offset);
					celebrity_is_movie=true;
					movieCast=getCommonServices().getMovieCast(cid,u.getUser_id());
					for(AdminModel movieCast1:movieCast){
					String movieChara="";
					List<AdminModel> movieCastCharacter=getCommonServices().getMovieCastCharacter(cid,movieCast1.getCelebrity_id());
					List<AdminModel> followCount=getCommonServices().getUserFollowerCount(movieCast1.getCelebrity_id());
					for(AdminModel followCount1:followCount){
					movieCast1.setFollower_count(followCount1.getFollower_count());
					}
					//movieCast1.setFollower_count(followCount);
					int j=0;
					for(AdminModel movieCastCharacter1:movieCastCharacter){
					//	System.out.println("Character"+movieCastCharacter1.getCaracter_name());
					movieChara=movieChara+movieCastCharacter1.getCaracter_name();
					if(j<movieCastCharacter.size()-1){
						movieChara=movieChara+"/ ";
					}
					j++;
					}
					movieCast1.setCaracter_name(movieChara);
					}

				}
				for(AdminModel a:celebrity_post)
				{
					
					if(a.getNews_description()!=null)
					{
						String s=a.getNews_description();
						Pattern pattern = Pattern.compile("<font size=\"-1\">(.*?)</font>");
						Matcher matcher = pattern.matcher(s);
						
						while (matcher.find()) {
							String str=matcher.group(1);
							a.setNews_description(str);
						}
					}
				}
				if(movieFlag)
				{
					
					ArrayList<List<AdminModel>> all_data=new ArrayList<>();
					List<AdminModel> meta_data=new ArrayList<>();
//					all_data.add(movie_director_news);
//					all_data.add(movie_producer_news);
//					all_data.add(movie_actor_news);
//					map.addAttribute("all_data",all_data);
					/*************** producer news ***************/
					movie_producer=getCommonServices().getCaracterwiseCelebrityInfo(cid, 2);
					for(AdminModel producer:movie_producer)
					{
						if(query.equals("twitter"))
						{
							List<AdminModel> twitter_data=getCommonServices().getTwitter_post(producer.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:twitter_data)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(twitter_data);
							meta_data.add(producer);
							
//							movie_producer_news.addAll(getCommonServices().getTwitter_post(producer.getCelebrity_id(), u.getUser_id(),10,10));
						}
						else if(query.equals("facebook"))
						{
							List<AdminModel> facebook_data=getCommonServices().getFacebook_post(producer.getCelebrity_id(),u.getUser_id(),10,10);
							for(AdminModel a:facebook_data)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(facebook_data);
							meta_data.add(producer);
//							movie_producer_news.addAll(getCommonServices().getFacebook_post(producer.getCelebrity_id(),u.getUser_id(),10,10));

						}
						else if(query.equals("news"))
						{
//							movie_producer_news.addAll(getCommonServices().getOnlyNews(producer.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> news=getCommonServices().getOnlyNews(producer.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:news)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(news);
							meta_data.add(producer);
						}
						else if(query.equals("instagram"))
						{
//							movie_producer_news.addAll(getCommonServices().getInstagram_post(producer.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> news=getCommonServices().getInstagram_post(producer.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:news)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(news);
							meta_data.add(producer);
						}
						else{
							List<AdminModel> news=getCommonServices().getCaracterwiseCelebrityNews(producer.getCelebrity_id(),u.getUser_id());
							for(AdminModel a:news)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(news);
							meta_data.add(producer);
//						movie_producer_news.addAll(getCommonServices().getCaracterwiseCelebrityNews(producer.getCelebrity_id(),u.getUser_id()));
						}
					}
					/*************** director news ***************/
					movie_director=getCommonServices().getCaracterwiseCelebrityInfo(cid, 3);
					for(AdminModel director:movie_director)
					{
						if(query.equals("twitter"))
						{
//							movie_director_news.addAll(getCommonServices().getTwitter_post(director.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> dirtweet=getCommonServices().getTwitter_post(director.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:dirtweet)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(dirtweet);
							meta_data.add(director);
						}
						else if(query.equals("facebook"))
						{
//							movie_director_news.addAll(getCommonServices().getFacebook_post(director.getCelebrity_id(),u.getUser_id(),10,10));
							List<AdminModel> dri_facebook=getCommonServices().getFacebook_post(director.getCelebrity_id(),u.getUser_id(),10,10);
							for(AdminModel a:dri_facebook)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(dri_facebook);
							meta_data.add(director);
						}
						else if(query.equals("news"))
						{
//							movie_director_news.addAll(getCommonServices().getOnlyNews(director.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> dir_news=getCommonServices().getOnlyNews(director.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:dir_news)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(dir_news);
							meta_data.add(director);
						}
						else if(query.equals("instagram"))
						{
							//movie_director_news.addAll(getCommonServices().getInstagram_post(director.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> dir_insta=getCommonServices().getInstagram_post(director.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:dir_insta)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(dir_insta);
							meta_data.add(director);
						}
						else{
						//movie_director_news.addAll(getCommonServices().getCaracterwiseCelebrityNews(director.getCelebrity_id(),u.getUser_id()));
							List<AdminModel> dir_insta=getCommonServices().getCaracterwiseCelebrityNews(director.getCelebrity_id(),u.getUser_id());
							for(AdminModel a:dir_insta)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }		
							all_data.add(dir_insta);
							meta_data.add(director);
						}
						
					}
					/*************** Actor news ***************/
					movie_actor=getCommonServices().getCaracterwiseCelebrityInfo(cid, 1);
					for(AdminModel actor:movie_actor)
					{
						if(query.equals("twitter"))
						{
//							movie_actor_news.addAll(getCommonServices().getTwitter_post(actor.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> actor_info=getCommonServices().getTwitter_post(actor.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:actor_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actor_info);
							meta_data.add(actor);
						}
						else if(query.equals("facebook"))
						{
							//movie_actor_news.addAll(getCommonServices().getFacebook_post(actor.getCelebrity_id(),u.getUser_id(),10,10));
							List<AdminModel> actor_info=getCommonServices().getFacebook_post(actor.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:actor_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actor_info);
							meta_data.add(actor);
						}
						else if(query.equals("news"))
						{
//							movie_actor_news.addAll(getCommonServices().getOnlyNews(actor.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> actor_info=getCommonServices().getOnlyNews(actor.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:actor_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actor_info);
							meta_data.add(actor);
						}
						else if(query.equals("instagram"))
						{
//							movie_actor_news.addAll(getCommonServices().getInstagram_post(actor.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> actor_info=getCommonServices().getInstagram_post(actor.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:actor_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actor_info);
							meta_data.add(actor);
						}
						else{
//						movie_actor_news.addAll(getCommonServices().getCaracterwiseCelebrityNews(actor.getCelebrity_id(),u.getUser_id()));
							List<AdminModel> actor_info=getCommonServices().getCaracterwiseCelebrityNews(actor.getCelebrity_id(),u.getUser_id());
							for(AdminModel a:actor_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actor_info);
							meta_data.add(actor);
						}
					}
					
					/*************** Actress news ***************/
					movie_actress=getCommonServices().getCaracterwiseCelebrityInfo(cid, 4);
					for(AdminModel actress:movie_actress)
					{
						if(query.equals("twitter"))
						{
							List<AdminModel> actress_info=getCommonServices().getTwitter_post(actress.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:actress_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actress_info);
							meta_data.add(actress);
//							movie_actress_news.addAll(getCommonServices().getTwitter_post(actress.getCelebrity_id(), u.getUser_id(),10,10));
						}
						else if(query.equals("facebook"))
						{
//							movie_actress_news.addAll(getCommonServices().getFacebook_post(actress.getCelebrity_id(),u.getUser_id(),10,10));
							List<AdminModel> actress_info=getCommonServices().getFacebook_post(actress.getCelebrity_id(),u.getUser_id(),10,10);
							for(AdminModel a:actress_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actress_info);
							meta_data.add(actress);
						}
						else if(query.equals("news"))
						{
//							movie_actress_news.addAll(getCommonServices().getOnlyNews(actress.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> actress_info=getCommonServices().getOnlyNews(actress.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:actress_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actress_info);
							meta_data.add(actress);
						}
						else if(query.equals("instagram"))
						{
//							movie_actress_news.addAll(getCommonServices().getInstagram_post(actress.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> actress_info=getCommonServices().getInstagram_post(actress.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:actress_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(actress_info);
							meta_data.add(actress);
						}
						else{
						//movie_actress_news.addAll(getCommonServices().getCaracterwiseCelebrityNews(actress.getCelebrity_id(),u.getUser_id()));
						List<AdminModel> actress_info=getCommonServices().getCaracterwiseCelebrityNews(actress.getCelebrity_id(),u.getUser_id());
						for(AdminModel a:actress_info)
					    {
					    	String title=replaceSpecialCharacters(a.getNews_title());
					        a.setNews_title(title);
					    }
						all_data.add(actress_info);
						meta_data.add(actress);
						}
					}
					/*************** others news ***************/
					movie_others=getCommonServices().getCaracterwiseOtherCelebrityInfo(cid);
					for(AdminModel others:movie_others)
					{
						if(query.equals("twitter"))
						{
//							movie_others_news.addAll(getCommonServices().getTwitter_post(others.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> other_info=getCommonServices().getTwitter_post(others.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:other_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(other_info);
							meta_data.add(others);
						}
						else if(query.equals("facebook"))
						{
//							movie_others_news.addAll(getCommonServices().getFacebook_post(others.getCelebrity_id(),u.getUser_id(),10,10));
							List<AdminModel> other_info=getCommonServices().getFacebook_post(others.getCelebrity_id(),u.getUser_id(),10,10);
							for(AdminModel a:other_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(other_info);
							meta_data.add(others);
						}
						else if(query.equals("news"))
						{
//							movie_others_news.addAll(getCommonServices().getOnlyNews(others.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> other_info=getCommonServices().getOnlyNews(others.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:other_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(other_info);
							meta_data.add(others);
						}
						else if(query.equals("instagram"))
						{
//							movie_others_news.addAll(getCommonServices().getInstagram_post(others.getCelebrity_id(), u.getUser_id(),10,10));
							List<AdminModel> other_info=getCommonServices().getInstagram_post(others.getCelebrity_id(), u.getUser_id(),10,10);
							for(AdminModel a:other_info)
						    {
						    	String title=replaceSpecialCharacters(a.getNews_title());
						        a.setNews_title(title);
						    }
							all_data.add(other_info);
							meta_data.add(others);
						}
						else{
//						movie_others_news.addAll(getCommonServices().getCaracterwiseCelebrityNews(others.getCelebrity_id(),u.getUser_id()));
						List<AdminModel> other_info=getCommonServices().getCaracterwiseCelebrityNews(others.getCelebrity_id(),u.getUser_id());
						for(AdminModel a:other_info)
					    {
					    	String title=replaceSpecialCharacters(a.getNews_title());
					        a.setNews_title(title);
					    }
						all_data.add(other_info);
						meta_data.add(others);
						}
					}
					
					map.addAttribute("all_data",all_data);
					map.addAttribute("meta_data", meta_data);
				}
//				map.addAttribute("movie_producer_news", movie_producer_news);
//				map.addAttribute("movie_producer",movie_producer);
//				
//				map.addAttribute("movie_director_news", movie_director_news);
//				map.addAttribute("movie_director",movie_director);
//				
//				map.addAttribute("movie_actor_news", movie_actor_news);
//				map.addAttribute("movie_actor",movie_actor);
//				
//				map.addAttribute("movie_actress_news", movie_actress_news);
//				map.addAttribute("movie_actress",movie_actress);
//				
//				map.addAttribute("movie_others_news", movie_others_news);
//				map.addAttribute("movie_others",movie_others);
				
				if(movieFlag){
				celebrity_may_know=getCommonServices().Celebrity_youMayKnowforMovie(cid,u.getUser_id());
				
				}else{
					List<AdminModel> specific_actor_movies=getCommonServices().getSpecificActorMovies(cid,u.getUser_id());
					//List<AdminModel> all_actors=new ArrayList<>();
					for(AdminModel ap:specific_actor_movies)
					{
						List<AdminModel> getAllActorsInMovie=getCommonServices().Celebrity_youMayKnowRelatedMovie(cid, u.getUser_id(), ap.getCelebrity_id());
						for(AdminModel ac:getAllActorsInMovie)
						{
							celebrity_may_know.add(ac);
						}
					}
					
				//celebrity_may_know=getCommonServices().Celebrity_youMayKnow(cid,u.getUser_id());
				}
				if(celebrity_may_know.size()==0)
				{
					celebrity_may_know=getCommonServices().Celebrity_youMayKnow(cid,u.getUser_id(),cat_id);
				}
				
				
				map.addAttribute("celebrity_may_know", celebrity_may_know);
				//map.addAttribute("celebrity_is_movie",celebrity_is_movie);
				session.setAttribute("celebrity_is_movie",celebrity_is_movie);
				map.addAttribute("movieCast",movieCast);

				List<AdminModel> specific_actor_movies=getCommonServices().getSpecificActorMovies(cid,u.getUser_id());
				map.addAttribute("specific_actor_movies",specific_actor_movies);
				
				
				
			}
			/*map.addAttribute("tab",query);*/
			map.addAttribute("total_news_post",total_count);
			
			
			
			if(celebrity_is_movie)
			{
				return "Common/MovieProfile";
			}
			else{
				return "Common/celebrityProfile";
			}
		}
		
		
		return "Common/celebrityProfile";
	}
	

	@RequestMapping(value="celebrity-profile",method=RequestMethod.POST)
	public String celebrityProfilePost(HttpServletRequest request,RedirectAttributesModelMap map,HttpSession session)
	{
		if(request.getParameter("news_source")!=null)
		{
			map.addFlashAttribute("news_source",request.getParameter("news_source"));
		}
		if(request.getParameter("news_id")!=null)
		{
			map.addFlashAttribute("show_news",request.getParameter("news_id"));
		}
		if(request.getParameter("celebrity_id")!=null)
		{
			session.setAttribute("celebrity_id", request.getParameter("celebrity_id")+"");
		}
		if(request.getParameter("activate_profile_tab")!=null)
		{
			map.addFlashAttribute("pagination_active_tab",request.getParameter("activate_profile_tab"));
		}
		if(request.getParameter("btn_offset")!=null && request.getParameter("btn_offset")!="" && !request.getParameter("btn_offset").equals("undefined") && !request.getParameter("btn_offset").equals("null"))
		{
			int offset=Integer.parseInt(request.getParameter("btn_offset"));
			map.addFlashAttribute("offset",offset);
		}
		
		if(request.getParameter("btn_next")!=null && request.getParameter("btn_next")!="" && !request.getParameter("btn_next").equals("undefined") && !request.getParameter("btn_next").equals("null"))
		{
			int offset=Integer.parseInt(request.getParameter("btn_next"));
			int limit=Integer.parseInt(session.getAttribute("limit")+"");    
			offset=offset+limit;
			map.addFlashAttribute("offset",offset);
		}
		if(request.getParameter("btn_previous")!=null && request.getParameter("btn_previous")!="" && !request.getParameter("btn_previous").equals("undefined") && !request.getParameter("btn_previous").equals("null"))
		{
			
			int offset=Integer.parseInt(request.getParameter("btn_previous"));
			int limit=Integer.parseInt(session.getAttribute("limit")+"");
			offset=offset-limit;
			map.addFlashAttribute("offset",offset);
		}
		if(request.getParameter("limit")!=null && request.getParameter("limit")!="" && !request.getParameter("limit").equals("undefined") && !request.getParameter("limit").equals("null"))
		{
			session.setAttribute("limit", request.getParameter("limit"));
		}
		
		return "redirect:celebrity-profile";
	}
	
	
	@RequestMapping(value="validate_emailid",method=RequestMethod.POST)
	@ResponseBody
	public String Validate_emailId(HttpServletRequest req)
	{
		String response="ok"; 
		if(req.getParameter("emailid")!=null)
		{
			String email_id=req.getParameter("emailid")+"";
			List<CommonModel> login_info=getCommonServices().checkEmail_id(email_id);
			for(CommonModel c:login_info)
			{
				response="not_ok";
			}
		}

		return response;
	}
	
	
	@RequestMapping(value="logout" ,method={RequestMethod.POST,RequestMethod.GET})
	String logout(ModelMap map,HttpSession session)
	{
		methodIn("logout");
		session.invalidate();
		windows.clearCache();
		Runtime.getRuntime().freeMemory();
		methodOut("logout");
		return "redirect:home";

	}
	
	@RequestMapping(value="blogs",method=RequestMethod.GET)
	public String AllBlogs(ModelMap map)
	{
		List<AdminModel> all_blogs=getCommonServices().getAllBlogs();
		map.addAttribute("all_blogs", all_blogs);
		return "Common/Blogs";
	}
	@RequestMapping(value="blog-details",method=RequestMethod.GET)
	public String BlogDetailsGet(ModelMap map)
	{
		if(map.get("blog_id")!=null)
		{
			int blog_id=Integer.parseInt(""+map.get("blog_id"));
			List<AdminModel> blog_details=getCommonServices().getBlogDetails(blog_id);	
			List<AdminModel> more_blogs=getCommonServices().getMoreBlogs(blog_id);	
			map.addAttribute("blog_details", blog_details);
			map.addAttribute("more_blogs", more_blogs);
		}
		
		return "Common/BlogDetails";
	}
	
	@RequestMapping(value="blog-details",method=RequestMethod.POST)
	public String BlogDetailsPost(RedirectAttributesModelMap map,HttpServletRequest req)
	{
		if(req.getParameter("blog_id")!=null)
		{
			int blog_id=Integer.parseInt(req.getParameter("blog_id")+"");
			map.addFlashAttribute("blog_id", blog_id);
		}
		return "redirect:blog-details";
	}
	
	@RequestMapping(value="movie",method=RequestMethod.GET)
	public String MoviesGet(HttpServletRequest request,HttpSession session,ModelMap map)
	{
		UserSession u=new UserSession(session);
		int user_id=0;
		if(u.getUser_id()>0)
		{
			user_id=u.getUser_id();
		}
		String letter="A";
		int category_id=6; // category id 6 is for Movies

		if(request.getParameter("q")==null)
		{
			List<AdminModel> celebrity_info=getCommonServices().getCelebrityInfo_ByCategoryId(user_id, category_id);
			List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount();
			map.addAttribute("celebrity_follower_count", follower_count);
			map.addAttribute("celebrity_info", celebrity_info);
			map.addAttribute("latter", "");
			return "Common/Movies";
		}
//		if(session.getAttribute("letter")!=null)
//		{
//			if(!(session.getAttribute("letter")+"").equals(""))
//			{
//				letter=session.getAttribute("letter")+"";
//			}
//		}
		if(request.getParameter("q")!=null)
		{
			letter=request.getParameter("q")+"";
			if(letter.equals(""))
			{
				letter="A";
			}
			//session.setAttribute("letter", letter);
		}
		String q=letter+"%";
		List<AdminModel> celebrity_info=getCommonServices().getSortedCelebrityInfo_ByCategoryId(q, user_id, category_id);
		List<AdminModel> follower_count=getCommonServices().getCelebrityFollowerCount_sorted(q);
		
		map.addAttribute("celebrity_follower_count", follower_count);
		map.addAttribute("celebrity_info", celebrity_info);
		map.addAttribute("latter", letter);
		return "Common/Movies";
	}
	
	@RequestMapping(value="daily-news-updates",method=RequestMethod.GET)
	public String dailyNewsUpdatesGet()
	{
		return "Common/DailyNewsUpdate";
	}
	
	@RequestMapping(value="daily-news-updates",method=RequestMethod.POST)
	public String dailyNewsUpdatesPost()
	{
		return "redirect:daily-news-updates";
	}
	
	@RequestMapping(value="pagination-active-tab",method=RequestMethod.POST)
	@ResponseBody
	public String profilePagination(HttpServletRequest request, HttpSession session)
	{
		String result="ok";
		if(request.getParameter("activeTab")!=null)
		{
			session.setAttribute("pagination_active_tab", request.getParameter("activeTab"));
		}
		return result;
	}
	
	@RequestMapping(value="forgot-password", method=RequestMethod.POST)
	public String forgotPasswordPost(HttpServletRequest request, RedirectAttributesModelMap redirectAttributes) throws Exception
	{
		if(request.getParameter("forgot_pass_email")!=null)
		{
			String user_name=null;
			String email=request.getParameter("forgot_pass_email");
			String password = RandomStringUtils.random(10, new char[] { 'A', 'B',
					'C', 'D', 'E', 'f', 'g', 'h', 'i', 'j', 'k', '@', '1',
					'2', '3', '4', '4', '6', '7' });

			List<CommonModel> user_information=getCommonServices().getUserName(email);
			if(user_information.isEmpty()){
				redirectAttributes.addFlashAttribute("forgot_p_invalid","Please enter valid email id.");
				return "redirect:home";
			}
			for(CommonModel c:user_information)
			{
				user_name=c.getFirst_name();
			}
			String[] mailToAddress={email};
			String web_url="http://ec2-18-221-38-54.us-east-2.compute.amazonaws.com:8080/RSBS/home";
//			String htmlText = readEmailFromHtml("/usr/share/apache-tomcat-7.0.75/webapps/Celebrity/WEB-INF/jsp/Common/dailyNewsletterTemplate.html",input);
			String htmlText = "Dear   "+user_name+","+"<br /> <br />"+"Your updated password is - "+"\t"+password+"<br />"+"<br /> <br /> <br />"+"Thanks and Regards"+"<br />"+"Red Said Blue Said Team";
			
			EmailController email1=new EmailController();
			email1.sendingForgotPasswordMail(mailToAddress, htmlText);
			
			
			int success=getCommonServices().updateForgotedPassword(email, CommonController.encrypt(password));
			
			redirectAttributes.addFlashAttribute("message","Your updated password have been sent on your mail. Please check your mail.");
		}
		return "redirect:home";
	}
	
	public List<AdminModel> getCelebrityNewsfromAPI(String celebrity_name){
		try{
			List<AdminModel> celebrity_news=new ArrayList<>();
			String query=celebrity_name.replace(" ", "+");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			long DAY_IN_MS = 1000 * 60 * 60 * 24;
			Date date=new Date(System.currentTimeMillis() - (7 * DAY_IN_MS));
			String finalDate=dateFormat.format(date);
            //System.out.println("date="+dateFormat.format(date));
			String feed_url="https://newsapi.org/v2/everything?Language=en&q=\""+query+"\"&apiKey=23f9b7d847364efc88a86fa973c198fc&sortBy=publishedAt,relevancy&from="+finalDate;
			//String feed_url="https://newsapi.org/v2/everything?q=\""+query+"\"&apiKey=23f9b7d847364efc88a86fa973c198fc&sortBy=publishedAt"; // 43cceeca5c384d1daaf15c95475b5766
					
					String inputLine=hitURL(feed_url);
			         AdminModel ad=new AdminModel();
					JSONParser parser = new JSONParser();
				      try{
					         Object obj = parser.parse(inputLine);
					         JSONObject json=(JSONObject)obj;
					         JSONArray post_data=(JSONArray) json.get("articles");
					         int cnt=post_data.size();
					         for(int i=0;i<cnt;i++)
					         {
					        	 ad=new AdminModel();
					        	 JSONObject json1=(JSONObject)post_data.get(i);
						        	 String source_url="";
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
						              
					            		 String title=json1.get("title")+"";
							        	 String url=json1.get("url")+"";
							        	 String description=json1.get("description")+"";
							        	 String img_url=json1.get("urlToImage")+"";
					            		 String publishedAt=json1.get("publishedAt")+"";
					            		 publishedAt=publishedAt.replace("T", " ");
					            		 publishedAt=publishedAt.replace("Z", "");
							        	 
					            		ad.setNews_title(title);
						            	ad.setPublication_date(publishedAt);
						            	ad.setNews_description_url(url);
						            	ad.setNews_description(description);
						            	ad.setEntity_id(ad.getCelebrity_id());
						            	ad.setImage_url(img_url);
						            	ad.setNews_source(source_url);
						            	celebrity_news.add(ad);
						    	
					         }
						}catch(Exception e)
						{
							System.out.println("Testing ..."+e);
						}
						return celebrity_news;

				}catch(Exception e)
				{
					System.out.println("error inside getCelebrityNewsfromAPI() method.");
				}
	//	}
		return null;
	}
	
	public List<AdminModel> getTopNewsCount()
	{
       List<AdminModel> final_news_cnt=null;
		
		int day_interval=0;
		final_news_cnt=getCommonServices().getTrending_celebrities(day_interval);
		while(final_news_cnt.size()<1)
		{
			day_interval++;
			final_news_cnt=getCommonServices().getTrending_celebrities(day_interval);
		}
		return final_news_cnt;
	}
	public String replaceSpecialCharacters(String title)
	{
		String c="";
		if(title!=null && title!="")
		{
			c= title;
	        //Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
	        
	        Pattern pt = Pattern.compile("[^'_/.,:;#$\"A-Za-z0-9 ]");
	        Matcher match= pt.matcher(c);
	        while(match.find())
	        {
	            String s= match.group();
	        c=c.replaceAll("\\"+s, "");
	        }
		}
        return c;
	}
	/*@RequestMapping(value="privacy-policy",method=RequestMethod.GET)
	public String showPrivacyPolicy()
	{
		return "Common/privacy_policy";
	}*/
	@RequestMapping(value="get-celebrity-suggestions",method=RequestMethod.POST)
	@ResponseBody
	public String get_celebrity_suggestions(HttpServletRequest req,HttpSession session){
		ArrayList<String> list = new ArrayList<String>();
	//	JSONArray list = new JSONArray();

		String sent_data="";
	//	if(req.getParameter("search_term")!=null)
	//	{
			/*String searched=req.getParameter("search_term");
			searched="%"+searched+"%";
			List<AdminModel> sugg_data=getCommonServices().get_celebrity_suggestions(searched);*/
			List<AdminModel> sugg_data=getCommonServices().get_all_celebrity_suggestions();
			for(AdminModel a:sugg_data)
			{
				/*HashMap<String, String> cel_suggestions=new HashMap<>();
				cel_suggestions.put("cel_name", a.getCelebrity_name());*/
				/*Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				sent_data = gson.toJson(data);*/
				list.add(a.getCelebrity_name());
			}
			Gson gson = new Gson();
			String json = gson.toJson(list);
		   // JSONArray jsArray = new JSONArray(list);
		    return json;
		
	}
	
	@RequestMapping(value="get-all-celebrity-categories",method=RequestMethod.POST)
	@ResponseBody
	public String get_get_all_celebrity_categories(HttpServletRequest req,HttpSession session){
			String category_id="";
			String category_name="";
			List<AdminModel> sugg_data=getCommonServices().get_all_celebrity_categories();
			for (AdminModel adminModel : sugg_data) {
				if(category_id.isEmpty()){
					category_id=String.valueOf(adminModel.getCategory_id());
				}else{
					category_id=category_id+","+adminModel.getCategory_id();
				}
				if(category_name.isEmpty()){
					category_name=adminModel.getCategory_name();
				}else{
					category_name=category_name+","+adminModel.getCategory_name();
				}
			}
		    return category_id+"~"+category_name;
		
	}
	
	@RequestMapping(value="add-celebrity-reuest",method=RequestMethod.POST)
	public String add_celebrity_request(@Valid AdminModel am,HttpSession session,RedirectAttributesModelMap redirectAttributes){
		UserSession u=new UserSession(session);
		String category_id[]=null;
		String celebrity_name=am.getCelebrity_name();
		if(am.getCelebrity_category_ids()!=null && !am.getCelebrity_category_ids().isEmpty()){
			category_id=am.getCelebrity_category_ids().split(",");
		}
		getCommonServices().add_requested_celebrity(celebrity_name, u.getUser_id());
		if(category_id!=null && category_id.length!=0){
			int req_celebrity_id=getCommonServices().get_last_requested_celebrity_id(u.getUser_id(), celebrity_name);
			for (int i = 0; i < category_id.length; i++) {
				getCommonServices().add_requested_celebrity_categories(req_celebrity_id, Integer.parseInt(category_id[i]));
			}
		}
		
		redirectAttributes.addFlashAttribute("message", "Celebrity requested successfully.");
		return "redirect:home";
	}
	
	/*@RequestMapping(value="encrypt-passwords-rsbs",method=RequestMethod.GET)
	public String encryptPassword() throws Exception
	{
		List<CommonModel> allUsers=getCommonServices().getAllUsers();
		for(CommonModel allUsers1:allUsers){
			String pass=allUsers1.getPassword();
			pass=CommonController.encrypt(pass);
        getCommonServices().updatePassword(allUsers1.getUser_id(), pass);
		}
		return "redirect:home";
	}*/
}
