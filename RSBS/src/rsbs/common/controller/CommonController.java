package common.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.Key;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.RandomStringUtils;
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


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import admin.model.AdminModel;

import common.model.CommonModel;
import common.services.CommonServices;
import com.sun.java.swing.plaf.windows.resources.windows;


@Controller
public class CommonController {
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
	private static final String KEY = "95rs61adfMEk92bs";

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
	
	
	
	
	@RequestMapping(value="home",method=RequestMethod.GET)
	public String homeGet(HttpSession session,ModelMap map)
	{
		UserSession u = new UserSession(session);
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		if(map.get("error")!=null)
		{
			map.addAttribute("error",map.get("error"));
		}
		if(session.getAttribute("user_name")==null || session.getAttribute("user_name").equals(""))
		{
			session.setAttribute("user_name", "");
		}			
			List<AdminModel> top_news=getCommonServices().getTop_news();
			List<AdminModel> politician_of_the_day=getCommonServices().politicianOfTheDay(u.getUser_id());
			List<AdminModel> popular_news=getCommonServices().getPopularFour_news();
			List<AdminModel> all_post_news=getCommonServices().getAllPost_news();
			for(AdminModel a:top_news)
		    {
		    	String title=replaceSpecialCharacters(a.getNews_title());
		    	String description=replaceSpecialCharacters(a.getNews_description());
		        a.setNews_title(title);
		        a.setNews_description(description);
		    }
			
		    int news_id=0;
		    for(AdminModel a:popular_news)
		    { 
		    	news_id=a.getNews_id();
		    	String title=replaceSpecialCharacters(a.getNews_title());
		        a.setNews_title(title);
		        String emptyString="";
		        if(a.getNews_id()==news_id)
		        {
		        	String space_array[]=a.getNews_title().split(" ");
		        	if(space_array.length>17)
		        	{
		        		for(int i=0;i<17;i++)
				        {
				        	emptyString=emptyString +" "+ space_array[i];			        	 
				        }
		        		emptyString=emptyString+"...";
		        		a.setNews_title(emptyString);
		        	}
		        	else{
		        		a.setNews_title(a.getNews_title()+".");
		        	}
		        }
		    }
		    int all_post_news_id=0;
		    for(AdminModel a:all_post_news)
		    {
		    	all_post_news_id=a.getNews_id();
		    	String title=replaceSpecialCharacters(a.getNews_title());
		        a.setNews_title(title);
		        String emptyString="";
			    if(a.getNews_source().equals("Facebook") || a.getNews_source().equals("Twitter") || a.getNews_source().equals("Instagram"))
			    {
			        if(a.getNews_id()==all_post_news_id)
			        {
			        	String space_array[]=a.getNews_title().split(" ");
			        	if(space_array.length>12)
			        	{
			        		for(int i=0;i<12;i++)
					        {
					        	emptyString=emptyString +" "+ space_array[i];			        	 
					        }
			        		emptyString=emptyString+"...";
			        		a.setNews_title(emptyString);
			        	}
			        	else{
			        		a.setNews_title(a.getNews_title()+".");
			        	}
			        }
			    }
		        
		    }
			map.addAttribute("politician_of_the_day",politician_of_the_day);
			map.addAttribute("popular_news",popular_news);
			map.addAttribute("top_news", top_news);
			map.addAttribute("all_post", all_post_news);
			/* First part celebrity id : */
			
	/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
			if(session.getAttribute("all_states")==null)
			{
				List<AdminModel> all_states=getCommonServices().getAllStates();
				List<AdminModel> all_categories=getCommonServices().getAllCategories();
				List<AdminModel> precident=new ArrayList<>();
				List<AdminModel> vise_precident=new ArrayList<>();
				List<AdminModel> all_precidentian_cabinates=new ArrayList<>();
				
				List<AdminModel> precident_politicians=getCommonServices().getPrecidentPoliticians(4);
	            for(AdminModel a:precident_politicians)
	            {
	            	if(a.getPolitician_position().equals("President of the United States"))
	            	{
	            		precident.add(a);
	            	}
	            	if(a.getPolitician_position().equals("Vice President of the United States of America") || a.getPolitician_position().equals("Secretary of State"))
	            	{
	            		vise_precident.add(a);
	            	}
	            	if(!a.getPolitician_position().equals("President of the United States") && !a.getPolitician_position().equals("Vice President of the United States of America") && !a.getPolitician_position().equals("Secretary of State"))
	            	{
	            		all_precidentian_cabinates.add(a);
	            	}
	            }
				session.setAttribute("all_states", all_states);
				session.setAttribute("all_categories", all_categories);
				session.setAttribute("precident", precident);
				session.setAttribute("vise_precident", vise_precident);
				session.setAttribute("all_precidentian_cabinates", all_precidentian_cabinates);
			}
			return "Common/Home";
	}
	@RequestMapping(value="home1",method=RequestMethod.GET)
	public String homeGet1(HttpSession session,ModelMap map)
	{
		UserSession u = new UserSession(session);
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		if(map.get("error")!=null)
		{
			map.addAttribute("error",map.get("error"));
		}
		if(session.getAttribute("user_name")==null || session.getAttribute("user_name").equals(""))
		{
			session.setAttribute("user_name", "");
		}
	
			List<AdminModel> count=new ArrayList<>();
			
			count=getTopNewsCount();
			
			int populart_celebrity_id=0,celebrity_of_day_id=0;
			int cnt=0;
			List<AdminModel> politician_of_the_day=null;
			List<AdminModel> top_news=getCommonServices().getTop_news();
			if(top_news.isEmpty())
			{
				for(AdminModel ad:count)
				{
					if(cnt==0)
					{
						populart_celebrity_id=ad.getCelebrity_id();
						top_news=getCommonServices().getTop_news();
						if(top_news.isEmpty())
						{
							
						}
						else{
							cnt++;
						}
						
					}
				}
			}
			
			politician_of_the_day=getCommonServices().politicianOfTheDay(u.getUser_id());
			for(AdminModel pod:politician_of_the_day)
			{
				celebrity_of_day_id=pod.getCelebrity_id();
			}
			List<AdminModel> popular_news=getCommonServices().getPopularFour_news();
			
			List<AdminModel> all_post_news=getCommonServices().getAllPost_news();
			List<AdminModel> temp=new ArrayList<>();
			int flag1=0,flag2=1;
			for(AdminModel a:top_news)
		    {
		    	String title=replaceSpecialCharacters(a.getNews_title());
		    	String description=replaceSpecialCharacters(a.getNews_description());
		        a.setNews_title(title);
		        a.setNews_description(description);
		        
		    }
		    int index=0;
		    int news_id=0;
		    for(AdminModel a:temp)
		    { 
		    	news_id=a.getNews_id();
		    	String title=replaceSpecialCharacters(a.getNews_title());
		        a.setNews_title(title);
		        String emptyString="";
		       
		        if(a.getNews_id()==news_id)
		        {
		        	//System.out.println("news title...."+a.getNews_title());
			        char[] charArray = a.getNews_title().toCharArray();
			        for(int i=0;i<a.getNews_title().length();i++)
			        {
			        	//System.out.println("characters...."+charArray[i]);
				        if(Character.isWhitespace(charArray[i]))
				        {
				        	index=index+1;
				        	
				        }
				        if(index <= 12)
			        	{
			        	//System.out.println("inside ap if......");
			        	emptyString=emptyString += charArray[i];
			        	//System.out.println("emptyString...."+emptyString);
			        	
			        	}
				        
			        }
			        if(index > 12)
			        {
			        	emptyString=emptyString + "...";
			        }
		        }
		        a.setNews_title(emptyString);
		        index=0;
		        
		        
		    }
		    int all_post_index=0;
		    int all_post_news_id=0;
		    for(AdminModel a:all_post_news)
		    {
		    	all_post_news_id=a.getNews_id();
		    	String title=replaceSpecialCharacters(a.getNews_title());
		        a.setNews_title(title);
		        
		        String emptyString="";
			    if(a.getNews_source().equals("Facebook") || a.getNews_source().equals("Twitter") || a.getNews_source().equals("Instagram"))
			    {
		        if(a.getNews_id()==all_post_news_id)
		        {
		        	//System.out.println("news title...."+a.getNews_title());
			        char[] charArray = a.getNews_title().toCharArray();
			        for(int i=0;i<a.getNews_title().length();i++)
			        {
			        	//System.out.println("characters...."+charArray[i]);
				        if(Character.isWhitespace(charArray[i]))
				        {
				        	all_post_index=all_post_index+1;
				        	
				        }
				        if(all_post_index <= 12)
			        	{
			        	//System.out.println("inside ap if......");
			        	emptyString=emptyString += charArray[i];
			        	//System.out.println("emptyString...."+emptyString);
			        	
			        	}
				        
			        }
			        //System.out.println("index..."+index);
			        if(all_post_index > 12)
			        {
			        	emptyString=emptyString + "...";
			        }
		        }
		        a.setNews_title(emptyString);
		        all_post_index=0;
			    }
		        
		    }
			map.addAttribute("politician_of_the_day",politician_of_the_day);
			map.addAttribute("popular_news",popular_news);
			map.addAttribute("top_news", top_news);
			map.addAttribute("all_post", all_post_news);
			/* First part celebrity id : */
			
	/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
			List<AdminModel> all_states=getCommonServices().getAllStates();
			List<AdminModel> all_categories=getCommonServices().getAllCategories();
			List<AdminModel> precident=new ArrayList<>();
			List<AdminModel> vise_precident=new ArrayList<>();
			List<AdminModel> all_precidentian_cabinates=new ArrayList<>();
			
			List<AdminModel> precident_politicians=getCommonServices().getPrecidentPoliticians(4);
            for(AdminModel a:precident_politicians)
            {
            	if(a.getPolitician_position().equals("President of the United States"))
            	{
            		precident.add(a);
            	}
            	if(a.getPolitician_position().equals("Vice President of the United States of America") || a.getPolitician_position().equals("Secretary of State"))
            	{
            		vise_precident.add(a);
            	}
            	if(!a.getPolitician_position().equals("President of the United States") && !a.getPolitician_position().equals("Vice President of the United States of America") && !a.getPolitician_position().equals("Secretary of State"))
            	{
            		all_precidentian_cabinates.add(a);
            	}
            }
			session.setAttribute("all_states", all_states);
			session.setAttribute("all_categories", all_categories);
			session.setAttribute("precident", precident);
			session.setAttribute("vise_precident", vise_precident);
			session.setAttribute("all_precidentian_cabinates", all_precidentian_cabinates);
           
			return "Common/Home";
	}
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String loginPost(AdminModel ad,RedirectAttributesModelMap map,HttpSession session,HttpServletRequest request)
	{
		UserSession u=new UserSession(session);
		String user_name=ad.getUser_name();
		String pass=ad.getPassword();
		try {
			pass=CommonController.encrypt(pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<AdminModel> login_info=getCommonServices().validate_email(user_name, pass);
		if(login_info.size()==0)
		{
			//map.addFlashAttribute("error", "Invalid login credentials.");
			return "redirect:home";
		}
		for(AdminModel a:login_info)
		{
			u.setUser_id(a.getUser_id());
			u.setRole_id(a.getRole_id());
			u.setUser_name(a.getUser_name());
			u.setUser_first_name(a.getFirst_name());
			
		}
		if(u.getRole_id()==1) // 1 = Admin
		{ 
			return "redirect:admin-home";
		}
		if(u.getRole_id()==2) // 2 = User
		{
			return "redirect:home";
		}
		
		
		return "redirect:home";
	}
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String loginGet()
	{
		return "redirect:home";
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
	@RequestMapping(value="all-politicians",method=RequestMethod.GET)
	public String allPoliticiansGet(HttpSession session,HttpServletRequest request,ModelMap map)
	{
		String letter="A";
		int user_id=0;
		UserSession u=new UserSession(session);
		/*if(u.getRole_id()!=1 || u.getRole_id()!=2 || u.getUser_id()==0)
		{
			if(u.getRole_id()!=1)
			{
				return "redirect:home";
			}	
		}*/
		if(u.getUser_id()>0)
		{
			user_id=u.getUser_id();
		}
		if(session.getAttribute("letter")!=null && !(session.getAttribute("letter")+"").equals(""))
		{
			letter=session.getAttribute("letter").toString();
		}
		if(request.getParameter("q")!=null)
		{
			letter=request.getParameter("q");
			if(letter.equals(""))
			{
				letter="A";
			}
			session.setAttribute("letter", letter);
		}
		String q=letter+"%";
		List<AdminModel> politician_info=getCommonServices().getAllPoliticians(q,user_id);
		List<AdminModel> politician_follower_count=getCommonServices().getPoliticiansFollowersCount(q);
		
		map.addAttribute("politician_info",politician_info);
		map.addAttribute("politician_follower_count",politician_follower_count);
		map.addAttribute("latter",letter);
		
		return "Common/Politicians";
	}
	
	@RequestMapping(value="show-profile",method=RequestMethod.GET)
	public String ShowProfilePost(ModelMap map,HttpServletRequest request,HttpSession session)
	{
		UserSession u = new UserSession(session);
		String tab="all_post";
		boolean tabFlag=false;
		int celebrity_id=0;
		List<AdminModel> news_post=null;
		List<AdminModel> suggested_politician=null;
		List<CommonModel> getUserLikesCount=null;
		List<CommonModel> getUserCommentsCount=null;
		List<AdminModel> post_count=null;
		int activity_id=0;
		Calendar date= Calendar.getInstance();

		  int current_year=date.getWeekYear();
		  map.addAttribute("current_year",current_year);
		
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
		
		if(session.getAttribute("celebrity_profile_id")!=null)
		{
			celebrity_id=Integer.parseInt(session.getAttribute("celebrity_profile_id")+"");
			List<AdminModel> all_posts=getCommonServices().getAllPostnews_ById(celebrity_id,u.getUser_id(),limit,offset);
			
			map.addAttribute("all-info", all_posts);
		}
		boolean tabFlag1=false;
		// pagination end
		if(map.get("pagination_active_tab")!=null)
		{
			tab=map.get("pagination_active_tab").toString();
			tabFlag1=true;
		}
		else{
			if(request.getParameter("tab")!=null)
			{
				tab=request.getParameter("tab")+"";
				tabFlag1=true;
			}
		}
        String homepagePostedValue="";
		if(map.get("news_source")!=null && !tabFlag1){
			homepagePostedValue=map.get("news_source").toString();
			//System.out.println("homepagePostedValue=="+homepagePostedValue);
			//if(map.get("news_source").equals(homepagePostedValue)){
			tabFlag=true;
			//}
		}
		int user_id=0;
		if(u.getUser_id()>0)
		{
			user_id=u.getUser_id();
		}
		List<AdminModel> celebrity_info=getCommonServices().getCelebrity_info(celebrity_id,user_id);
		
		suggested_politician=getCommonServices().getSuggested_politician(celebrity_id,user_id);
		if(tab.equals("all_post") && !tabFlag) 
		{
			//System.out.println("inside all post");
			List<AdminModel> all_posts_count=getCommonServices().getAllPostsCount(celebrity_id);
			for(AdminModel a:all_posts_count)
			{
				total=total+1;
			}
			
				news_post=getCommonServices().getAllPostnews_ById(celebrity_id,u.getUser_id(),limit,offset);
					getUserLikesCount=getCommonServices().getAllNPLikesCount();
					getUserCommentsCount=getCommonServices().getAllNPCommentsCount();
				
		}
		else if(tab.equals("facebook") || homepagePostedValue.equals("facebook"))
		{ 
					post_count=getCommonServices().getPostsCount(celebrity_id, "Facebook");
					for(AdminModel a:post_count)
					{
							total=a.getPost_count();
				    }

					activity_id=2;
					news_post=getCommonServices().getFacebook_post(celebrity_id,u.getUser_id(),limit,offset);
						getUserLikesCount=getCommonServices().getSpecificUserLikes(activity_id);
						getUserCommentsCount=getCommonServices().getNewsPostCommentsCount(activity_id);	
		}
		else if(tab.equals("twitter") || homepagePostedValue.equals("twitter"))
		{
			System.out.println("inside twitter");
			post_count=getCommonServices().getPostsCount(celebrity_id, "Twitter");
			for(AdminModel a:post_count)
			{
					total=a.getPost_count();
		    }
	        
			activity_id=2;
					news_post=getCommonServices().getTwitter(celebrity_id,u.getUser_id(),limit,offset);
					getUserLikesCount=getCommonServices().getSpecificUserLikes(activity_id);
					getUserCommentsCount=getCommonServices().getNewsPostCommentsCount(activity_id);
		}
		else if(tab.equals("instagram") || homepagePostedValue.equals("instagram"))
		{   
			post_count=getCommonServices().getPostsCount(celebrity_id, "Instagram");
			for(AdminModel a:post_count)
			{
					total=a.getPost_count();
		    }
	        
			activity_id=2;
					news_post=getCommonServices().getInstagram_post(celebrity_id,u.getUser_id(),limit,offset);
					getUserLikesCount=getCommonServices().getSpecificUserLikes(activity_id);
					getUserCommentsCount=getCommonServices().getNewsPostCommentsCount(activity_id);
		}
		else if(tab.equals("news") || homepagePostedValue.equals("news")){
			post_count=getCommonServices().getAllNewsCount(celebrity_id);
			for(AdminModel a:post_count)
			{
					total=a.getNews_count();
		    }
			
			activity_id=1;
				news_post=getCommonServices().getAll_news(celebrity_id,u.getUser_id(),limit,offset);
				getUserLikesCount=getCommonServices().getSpecificUserLikes(activity_id);
				getUserCommentsCount=getCommonServices().getNewsPostCommentsCount(activity_id);
		}
		int all_post_index=0;
		int all_post_news_id=0;
		for(AdminModel a:news_post)
	    {
			all_post_news_id=a.getNews_id();
	    	String title=replaceSpecialCharacters(a.getNews_title());
	    /*	if(a.getNews_source().equals("Twitter"))
			{
			title=getTwitterlinks(title);
			}*/
	        a.setNews_title(title);
	        String emptyString="";
	        if(a.getNews_id()==all_post_news_id)
	        {
	        	String space_array[]=a.getNews_title().split(" ");
	        	if(space_array.length>12)
	        	{
	        		for(int i=0;i<12;i++)
			        {
			        	emptyString=emptyString +" "+ space_array[i];			        	 
			        }
	        		emptyString=emptyString+"...";
	        		a.setNews_title(emptyString);
	        	}
	        }
	        
	    }
		
		List<CommonModel> politician_suggested_follower_count=getCommonServices().getCelebrityFollowersCount();
		
		map.addAttribute("politician_suggested_follower_count",politician_suggested_follower_count);

		map.addAttribute("suggested_politician",suggested_politician);
		map.addAttribute("celebrity_info", celebrity_info);
		map.addAttribute("news_post", news_post);
		map.addAttribute("active_tab", tab);
		map.addAttribute("getUserLikesCount",getUserLikesCount);
		map.addAttribute("getUserCommentsCount",getUserCommentsCount);
		map.addAttribute("total_news_post",total);
		
		if(map.get("news_id")!=null)
		{
			map.addAttribute("show_news",map.get("news_id"));
		}
		if(map.get("politician_news_id")!=null)
		{
			map.addAttribute("politician_news_id",map.get("politician_news_id"));
			map.addAttribute("news_source_redirect",map.get("news_source"));
			session.setAttribute("politician_news_id_active",map.get("politician_news_id"));
			session.setAttribute("news_source_redirect_active",map.get("news_source"));
		}
		
		session.removeAttribute("pagination_active_tab");
		
		return "Common/PoliticianProfile";
		
	}
	@RequestMapping(value="show-profile",method=RequestMethod.POST)
	public String ShowProfileGet(RedirectAttributesModelMap map,HttpServletRequest request,HttpSession session)
	{
		
		if(request.getParameter("active_tab_name")!=null)
		{
			map.addFlashAttribute("pagination_active_tab",request.getParameter("active_tab_name"));
		}
		if(request.getParameter("btn_offset")!=null)
		{
			int offset=Integer.parseInt(request.getParameter("btn_offset")+"");
			map.addFlashAttribute("offset",offset);
		}
		if(request.getParameter("btn_next")!=null)
		{
			int offset=Integer.parseInt(request.getParameter("btn_next")+"");
			int limit=Integer.parseInt(session.getAttribute("limit")+"");
			offset=offset+limit;
			map.addFlashAttribute("offset",offset);
		}
		if(request.getParameter("btn_previous")!=null)
		{
			int offset=Integer.parseInt(request.getParameter("btn_previous")+"");
			int limit=Integer.parseInt(session.getAttribute("limit")+"");
			offset=offset-limit;
			map.addFlashAttribute("offset",offset);
		}
		if(request.getParameter("limit")!=null)
		{
			session.setAttribute("limit", request.getParameter("limit"));
		}
		map.addFlashAttribute("celebrity_id", request.getParameter("celebrity_id"));

		if(request.getParameter("celebrity_id")!=null)
		{
		session.setAttribute("celebrity_profile_id", request.getParameter("celebrity_id"));
		}
		
		map.addFlashAttribute("news_id",request.getParameter("news_id"));
		map.addFlashAttribute("politician_news_id",request.getParameter("politician_news_id"));
		map.addFlashAttribute("politician_id",request.getParameter("politician_id"));
		map.addFlashAttribute("news_source",request.getParameter("news_source"));
		return "redirect:show-profile";
	}
	
	@RequestMapping(value="follow-politician",method=RequestMethod.POST)
	public String allPoliticiansPost(HttpServletRequest request,HttpSession session)
	{
		UserSession u=new UserSession(session);
		int user_id=0;
		if(u.getUser_id()>0)
		{
			user_id=u.getUser_id();
		}
		if(request.getParameter("celebrity_id")!=null)
		{
			int news_letter_type=1;
			/*if(request.getParameter("news_letter_type")!=null)
			{
				news_letter_type=Integer.parseInt(request.getParameter("news_letter_type"));
			}*/
			int celebrity_id=Integer.parseInt(request.getParameter("celebrity_id"));
			getCommonServices().addPoliticianFollowers(user_id, celebrity_id, news_letter_type);
		}
		if(request.getParameter("redirectTo")!=null)
		{
			String to=request.getParameter("redirectTo").trim();
			if(to.equals("politician_profile"))
			{
				return "redirect:show-profile";
			}
			else if(to.equals("all_politician")){
				
				return "redirect:all-politicians";
			}
			else if(to.equals("statewise_politician"))
			{
				return "redirect:statewise-politician";
			}
			else if(to.equals("homePage"))
			{
				return "redirect:home";
			}
			else if(to.equals("search_politician"))
			{
				return "redirect:search-result";
			}
			else{
				return "redirect:politician-bio";
			}
	    }
		/*else{
			return "redirect:statewise-politician";
		}*/
		/*if(request.getParameter("statewise")!=null)
		{
	       if(request.getParameter("statewise").equals("statewise_politician"))
	       {
					return "redirect:statewise-politician";
		   }
		}*/
		return "redirect:all-politicians";
	}
	
	@RequestMapping(value="settings", method=RequestMethod.GET)
	public String settingsGet(@Valid CommonModel c, HttpSession session, ModelMap map)
	{
		UserSession u=new UserSession(session);
		/*if(u.getRole_id()!=1 || u.getRole_id()!=2)
		{
			if(u.getRole_id()!=1)
			{
				return"redirect:home";
			}
			if(u.getRole_id()!=2)
			{
				return"redirect:home";
			}
		}*/
		List<CommonModel> getUserInformation=getCommonServices().getUserInformation(u.getUser_id());
		List<AdminModel> getUserFollowerList=getCommonServices().getUserFollowerList(u.getUser_id());
		/*for(CommonModel getUserFollowerList1:getUserFollowerList){
			System.out.println(getUserFollowerList1.getCelebrity_name()+" "+getUserFollowerList1);
		}*/
		map.addAttribute("getUserFollowerList",getUserFollowerList);
		map.addAttribute("getUserInformation",getUserInformation);
		
		if(map.get("error")!=null)
		{
			map.addAttribute("error",map.get("error"));
		}
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		if(map.get("activeTab")!=null)
        {
        	map.addAttribute("activeTab",map.get("activeTab"));
        }
		if(map.get("successTabActive")!=null)
		{
			map.addAttribute("successTabActive",map.get("successTabActive"));
		}
		return "Common/Settings";
	}
	
	@RequestMapping(value="settings", method=RequestMethod.POST)
	public String settingsPost(@Valid CommonModel c, HttpSession session, RedirectAttributesModelMap redirectAttributes,HttpServletRequest request)
	{
		UserSession u=new UserSession(session);
		
		if(request.getParameter("activeTab")!=null)
        {
        	redirectAttributes.addFlashAttribute("activeTab",request.getParameter("activeTab"));
        }
		String first_name=c.getFirst_name();
		String last_name=c.getLast_name();
		String contact_no=c.getContact_no();
		String email_id=c.getEmail_id();
		String existing_email=null;
		
		if(c.getFirst_name()!=null && request.getParameter("activeTab")==null)
		{
			List<CommonModel> getUserInformation=getCommonServices().getUserInformation(u.getUser_id());
            for(CommonModel co:getUserInformation)
            {
            	if(co.getEmail_id().equals(email_id))
            	{
        			getCommonServices().updateUserInformation(u.getUser_id(), first_name, last_name, contact_no, email_id);
            	    redirectAttributes.addFlashAttribute("message","Profile updated successfully.!!!");
            	}
            	else{
            		List<CommonModel> getExistingEmail=getCommonServices().getExistingUser(email_id);
            		for(CommonModel a:getExistingEmail)
            		{
            			existing_email=a.getEmail_id();
            		}
        			if(email_id.equals(existing_email))
        			{
        				redirectAttributes.addFlashAttribute("error","This email id is already present");
        				redirectAttributes.addFlashAttribute("successTabActive",1);
        				return"redirect:settings";
        			}
        			getCommonServices().updateUserInformation(u.getUser_id(), first_name, last_name, contact_no, email_id);
                    getCommonServices().updateUserCredentials(u.getUser_id(), email_id);
            	    redirectAttributes.addFlashAttribute("message","Profile updated successfully.!!!");
            	}
            }
            redirectAttributes.addFlashAttribute("successTabActive",1);
		}
		return "redirect:settings";
	}
	
	@RequestMapping(value="change-password",method=RequestMethod.POST)
	public String changePasswordPost(@Valid CommonModel c, HttpSession session,RedirectAttributesModelMap redirectAttributes,HttpServletRequest request) throws Exception
	{
		if(request.getParameter("activeTab")!=null)
        {
        	redirectAttributes.addFlashAttribute("activeTab",request.getParameter("activeTab"));
        }
		UserSession u=new UserSession(session);
		
		String old_password=null,new_password=null,confirm_password=null;

		if(c.getOld_password()!=null && request.getParameter("activeTab")==null)
		{
			old_password=c.getOld_password();
			old_password=CommonController.encrypt(old_password);
			new_password=c.getNew_password();
			String password=getCommonServices().getPassword(u.getUser_id());
			new_password=CommonController.encrypt(new_password);
			if(!old_password.equals(password))
			{
				redirectAttributes.addFlashAttribute("error","Invalid Old Password");
				redirectAttributes.addFlashAttribute("successTabActive",2);
				return "redirect:settings";
			}
			getCommonServices().updatePassword(u.getUser_id(), new_password);
			redirectAttributes.addFlashAttribute("message","Password updated successfully.!!!");
			redirectAttributes.addFlashAttribute("successTabActive",2);
		}
		return "redirect:settings";
	}
	
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String registrationPost(@Valid CommonModel c,HttpSession session,RedirectAttributesModelMap redirectAttributes,HttpServletRequest request)
	{
		            int user_id=0;
            		List<CommonModel> getExistingEmail=getCommonServices().getExistingUser(c.getEmail_id());
        			if(!getExistingEmail.isEmpty())
        			{
        				redirectAttributes.addFlashAttribute("error","This email id is already present");
        				return"redirect:home";
        			}
        			getCommonServices().addNewUser(c);
        			List<CommonModel> getUserId=getCommonServices().getExistingUser(c.getEmail_id());
        			for(CommonModel co:getUserId)
        			{
        				user_id=co.getUser_id();
        				String pass="";
						try {
							pass = CommonController.encrypt(c.getNew_password());
						} catch (Exception e) {
							e.printStackTrace();
						}

        				getCommonServices().addUserCredentials(user_id,c.getEmail_id(), pass, 2);
        				
        				Map<String, String> input = new HashMap<String, String>();
						input.put("username", c.getFirst_name());
						//String[] mailFromAddress={"aishwarya.dhopate@anveshak.com"};
						String[] mailFromAddress={c.getEmail_id()};
					  String htmlText = readEmailFromHtml("/usr/share/apache-tomcat-7.0.75/webapps/RSBS/WEB-INF/jsp/Common/welcomeTemplate.html",input);
						//String htmlText = readEmailFromHtml("/home/anveshak/rsbsworkspace/RSBS/WebContent/WEB-INF/jsp/Common/welcomeTemplate.html",input);
						EmailController email1=new EmailController();
						String subject="Welcome to Red Said Blue Said";
						email1.emailSending(mailFromAddress, htmlText,subject);						
						input.clear();
                	    redirectAttributes.addFlashAttribute("message","Registered successfully!"); 
        			}
        			return "redirect:home";
	}
	
	@RequestMapping(value="followers",method=RequestMethod.POST)
	public String followersPost(HttpServletRequest request, RedirectAttributesModelMap redirectAttributes)
	{
		if(request.getParameter("activeTab")!=null)
        {
        	redirectAttributes.addFlashAttribute("activeTab",request.getParameter("activeTab"));
        }
		if(request.getParameter("activeTab")==null && request.getParameter("follow_id")!=null)
		{
			int follow_id=Integer.parseInt(request.getParameter("follow_id"));
			getCommonServices().unfollowCelebrity(follow_id);
			redirectAttributes.addFlashAttribute("message","Unfollowed successfully.!!!");
			redirectAttributes.addFlashAttribute("successTabActive",3);
			if(request.getParameter("redirectTo")!=null)
			{
				String to=request.getParameter("redirectTo").trim();
				if(to.equals("politician_profile"))
				{
					return "redirect:show-profile";
				}
				else if(to.equals("all_politician")){
					
					return "redirect:all-politicians";
				}
				else if(to.equals("statewise_politician"))
				{
					return "redirect:statewise-politician";
				}
				else if(to.equals("homePage"))
				{
					return "redirect:home";
				}
				else if(to.equals("search_politician"))
				{
					return "redirect:search-result";
				}
				else if(to.equals("politician_bio")){
					return "redirect:politician-bio";
				}
				else{
					return "redirect:settings";
				}
		    }
		}
		
		return "redirect:settings";
	}
	
	@RequestMapping(value="user-searches",method=RequestMethod.POST)
	public String userSearches(@Valid CommonModel c, HttpSession session)
	{
		UserSession u=new UserSession(session);
		int count=0;
		List<CommonModel> getAllUserSearches=getCommonServices().getUserSearches(c.getSearch_key());
		session.setAttribute("user-search-key", c.getSearch_key());
		if(getAllUserSearches.isEmpty())
		{
			if(c.getSearch_key()!=null && !c.getSearch_key().equals("Search"))
	        {
				count=count+1;
	        	getCommonServices().addUserSearches(c.getSearch_key(), count);
	        }
		}
		else{
			for(CommonModel com:getAllUserSearches)
			{
				count=com.getCount()+1;
				getCommonServices().updateSearchCount(c.getSearch_key(), count);
			}
			
		}
		
	//	List<AdminModel> getAllCelebrities=getCommonServices().getAllPoliticians(searched_key, 0);
	/*	if(!getAllCelebrities.isEmpty())
		{*/
		//	session.setAttribute("letter", c.getSearch_key());
		//	return "redirect:all-politicians";
		/*	return "redirect:search-result";
		}
		else{*/
			
			return "redirect:search-result";
	//	}
        /*if(u.getRole_id()!=0)
        {
        	return "redirect:search-keys";
        }*/
        
        
		//return"redirect:home";
	}
	
	@RequestMapping(value="login-redirection",method=RequestMethod.POST)
	@ResponseBody
	public String loginFrom(HttpServletRequest request)
	{
		String result="ok";
		return result;
	}
	
	@RequestMapping(value="user-likes-function",method=RequestMethod.POST)
	@ResponseBody
	public String userLikesFunction(HttpServletRequest request,HttpSession session)
	{
		String result="";
		UserSession u=new UserSession(session);
		HashMap<String, String> like_data=new HashMap<>();
		if(u.getUser_id()!=0)
		{
			if(request.getParameter("news_id")!=null)
			{
				int activity_id=0;
				int news_id=Integer.parseInt(request.getParameter("news_id"));
				if(request.getParameter("news_source").equals("news"))
				{
					activity_id=1;
				}
				else{
					activity_id=2;
				}
				session.setAttribute("liked_news_id", news_id);
				session.setAttribute("liked_news_source",request.getParameter("news_source"));
				session.setAttribute("activity_id", activity_id);
				int flag=0;
				List<AdminModel> aulready_liked=getCommonServices().getIsLiked(u.getUser_id(), news_id, activity_id);
				
				if(aulready_liked.size()==0)
				{
				getCommonServices().addUserLikes(u.getUser_id(),news_id, activity_id);
				}
			    int count = getCommonServices().getUserLikeCount(news_id, activity_id);
			    List<AdminModel> user_likes_info=getCommonServices().getUserLikeId(u.getUser_id(), news_id, activity_id);
			    for(AdminModel a:user_likes_info)
			    {
			    	like_data.put("like_id",String.valueOf(a.getLike_id()));
			    }
			    like_data.put("like_count",String.valueOf(count));
			    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				String sent_data = gson.toJson(like_data);
				result=sent_data;
			}
		}
		return result;
	}
	@RequestMapping(value="user-comments-function",method=RequestMethod.POST)
	@ResponseBody
	public String userCommentsFunction(HttpServletRequest request,HttpSession session)
	{
		String result="ok";
		UserSession u=new UserSession(session);
		if(u.getUser_id()!=0)
		{
			if(request.getParameter("comments")!=null && request.getParameter("comments")!="")
			{
				int activity_id=0;
				int news_id=Integer.parseInt(request.getParameter("news_id"));
				String comments=request.getParameter("comments");
				if(request.getParameter("news_source").equals("news"))
				{
					activity_id=1;
				}
				else{
					activity_id=2;
				}
				getCommonServices().addUserComments(u.getUser_id(), news_id, activity_id, comments);
			    
			}
			if(request.getParameter("delete_comment_id")!=null && request.getParameter("delete_comment_id")!="")
			{
				int comment_id=Integer.parseInt(request.getParameter("delete_comment_id"));
				getCommonServices().deleteUserComments(comment_id);
			}
		}
		
		if(request.getParameter("news_id")!=null && request.getParameter("news_id")!="")
		{
			int activity_id=0;
			int news_id=Integer.parseInt(request.getParameter("news_id"));
			if(request.getParameter("news_source").equals("news"))
			{
				activity_id=1;
			}
			else{
				activity_id=2;
			}
			
		    int count = getCommonServices().getUserCommentsCount(news_id, activity_id);
			
			ArrayList<String> comments_data=new ArrayList<>();
			//List<CommonModel> comments_info=getCommonServices().getUserComments(news_id, activity_id);
			List<CommonModel> comments_info=null;
			if(request.getParameter("see_all_posts")!=null)
			{
				int index=Integer.parseInt(request.getParameter("see_all_posts"));
				if(index==1)
				{
				comments_info=getCommonServices().getUserComments(news_id, activity_id);
				}
				else{
					comments_info=getCommonServices().getUserTwoComments(news_id, activity_id);
				}
			}
			else{
				comments_info=getCommonServices().getUserTwoComments(news_id, activity_id);
			}
			int cnt=0;
			String sent_data="";
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			for(CommonModel ap:comments_info)
			{	
				cnt++;
				HashMap<String, String> single_post=new HashMap<>();
				single_post.put("user_id",String.valueOf(ap.getUser_id()));
				single_post.put("user_name",ap.getFirst_name()+"\t"+ap.getLast_name());
				single_post.put("last_name",ap.getLast_name());
				single_post.put("comment_id",String.valueOf(ap.getComment_id()));
				single_post.put("comments", ap.getComment());
				single_post.put("comments_count",String.valueOf(count));
				String single_data=gson.toJson(single_post);
				comments_data.add(single_data+"");
			}
			result=comments_data+"";
			result="{\"comments_info\":"+result+"}";
		}
		
		return result;
	}
	
	@RequestMapping(value="delete-user-likes",method=RequestMethod.POST)
	@ResponseBody
	public String deleteUserLikes(HttpServletRequest request)
	{
		String result="ok";
		if(request.getParameter("delete_like_id")!=null && request.getParameter("delete_like_id")!="")
		{
			int news_id=Integer.parseInt(request.getParameter("news_id"));
			int activity_id=0;
			if(request.getParameter("news_source").equals("news"))
			{
				activity_id=1;
			}
			else{
				activity_id=2;
			}
			int like_id=Integer.parseInt(request.getParameter("delete_like_id"));
			getCommonServices().deleteUserLikes(like_id);
			
			int count = getCommonServices().getUserLikeCount(news_id, activity_id);
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			String sent_data = gson.toJson(String.valueOf(count));
			result=sent_data;
		}
		return result;
		
	}
	
	@RequestMapping(value="search-result", method=RequestMethod.POST)
	public String searchResultPost()
	{
		return "redirect:search-result";
	}
	@RequestMapping(value="search-result", method=RequestMethod.GET)
	public String searchResultsGet(HttpSession session, ModelMap map)throws Exception
	{
			UserSession u=new UserSession(session);
		    AdminModel ad=new AdminModel();
		    ad.setCelebrity_name(session.getAttribute("user-search-key").toString());
		    String celebrity_name=ad.getCelebrity_name();
		    map.addAttribute("search_key",ad.getCelebrity_name());
		    try{
		    	String searched_key="%"+celebrity_name+"%";
		    	List<AdminModel> politician_info=getCommonServices().getPoliticiansForSearch(searched_key, u.getUser_id());
				List<AdminModel> politician_categories=getCommonServices().getCelebrityCategory();
				List<CommonModel> politician_follower_count=getCommonServices().getCelebrityFollowersCount();
				
				map.addAttribute("politician_categories",politician_categories);
				map.addAttribute("politician_info",politician_info);
				map.addAttribute("politician_follower_count",politician_follower_count);
		    	/*List<AdminModel> getAllCelebrities=getCommonServices().getAllPoliticians(searched_key, 0);
				ArrayList<List<AdminModel>> user_searches_news_final=new ArrayList<>();
				List<AdminModel> user_searches_news=null;
				if(getAllCelebrities.size()>0)
				{
					for(AdminModel getAllCelebrities1:getAllCelebrities){
					user_searches_news=getCommonServices().getAll_news(getAllCelebrities1.getCelebrity_id(),0,30,0);
					for(AdminModel user_searches_news_final2:user_searches_news){
			    		String title=user_searches_news_final2.getNews_title();
			    		title=replaceSpecialCharacters(title);
			    		user_searches_news_final2.setNews_title(title);
			    	}
					user_searches_news_final.add(user_searches_news);
					}
				}else{
					 user_searches_news=getCelebrityNewsfromAPI(celebrity_name);
					    if(user_searches_news.size()==0){
					    	 map.addAttribute("error_message","No results found for this search.");
					    }else{
					    	for(AdminModel user_searches_news_final2:user_searches_news){
					    		String title=user_searches_news_final2.getNews_title();
					    		title=replaceSpecialCharacters(title);
					    		user_searches_news_final2.setNews_title(title);
					    	}
					    	user_searches_news_final.add(user_searches_news);
			            	//String titleRepeat=title.replace("'", "\'");

					    }
					    
				}
				if(!user_searches_news.isEmpty() && user_searches_news!=null)
			    {
			    map.addAttribute("user_searches_news",user_searches_news_final);
			    }*/
		    	//List<AdminModel> user_searches_news1=getCommonServices().searchByCelebrityName(celebrity_name);
		   
		    }catch(Exception e){
		    	
		    }
		   // session.setAttribute("user-search-key",null);
		return "Common/user_searches";
	}
	
	
	public List<AdminModel> getSearchedCelebrity_news(AdminModel ad)throws Exception
	{
		List<AdminModel> searched_celebrity_info= new ArrayList<AdminModel>();
		List<String> blacklist_website=new ArrayList<>();
		String celebrity_name=ad.getCelebrity_name();
		try{
			    String web_name="";
				List<AdminModel> getBlacklistWebsite=getCommonServices().getBlacklistWebsites();
				for(AdminModel ab:getBlacklistWebsite)
				{
					web_name=ab.getWebsite_name();
					blacklist_website.add(web_name);
				}
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
			        		AdminModel adminModel = new AdminModel();
			        		adminModel.setNews_title(title);
			            	//pub_date=convertPublicationDate(pub_date);
			        		adminModel.setPublication_date(pub_date);
			        		adminModel.setNews_description_url(news_des_url);
			        		adminModel.setNews_description(description);
			        		adminModel.setImage_url(img_url);
			        		adminModel.setNews_source(source_url);
			            	searched_celebrity_info.add(adminModel);
			            	
		            	}
		            	
		            }
		         }
	         
		}catch(Exception e)
		{
			System.out.println("getCelebrity_news().."+e);
		}
		
		return searched_celebrity_info;
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
	
	@RequestMapping(value="statewise-politician", method=RequestMethod.GET)
	public String manageAllStatesGet(ModelMap map, HttpSession session)
	{
		UserSession u=new UserSession(session);
		if(session.getAttribute("state_id")!=null)
		{
			int state_id=Integer.parseInt(session.getAttribute("state_id").toString());
			int category_id=Integer.parseInt(session.getAttribute("category_id").toString());
			String state_name="";
			int index=0;
			List<AdminModel> politician_info=getCommonServices().getStatewisePoliticians(state_id, category_id, u.getUser_id());
			for(AdminModel a:politician_info)
			{
				if(index==0)
				{
					state_name=a.getState_name();
					map.addAttribute("politician_state_name",state_name);
					//session.setAttribute("politician_state_name", state_name);
				}
			}
			
			List<AdminModel> politician_categories=getCommonServices().getCelebrityCategory();
			List<CommonModel> politician_follower_count=getCommonServices().getCelebrityFollowersCount();
			
			map.addAttribute("politician_categories",politician_categories);
			map.addAttribute("politician_info",politician_info);
			map.addAttribute("politician_follower_count",politician_follower_count);
		}
		return"Common/StatewisePoliticiansInfo";
	}
	
	@RequestMapping(value="statewise-politician", method=RequestMethod.POST)
	public String manageAllStatesPost(HttpServletRequest request, ModelMap map, RedirectAttributesModelMap redirectAttributes, HttpSession session)
	{
		UserSession u=new UserSession(session);
		if(request.getParameter("state_id")!=null)
		{
			session.setAttribute("state_id", request.getParameter("state_id"));
			session.setAttribute("category_id", request.getParameter("category_id"));
		}
		return "redirect:statewise-politician";
	}
	
	@RequestMapping(value="readmore-function", method=RequestMethod.POST)
	@ResponseBody
	public String readMoreFunction(HttpServletRequest request)
	{
		String result="ok", news_source=null, data=null;
		int news_id=0;
		System.out.println("news_id"+request.getParameter("news_id"));
		if(request.getParameter("news_id")!=null)
		{
	    news_id=Integer.parseInt(request.getParameter("news_id"));
		news_source=request.getParameter("news_source");
		HashMap<String, String> readmore_data=new HashMap<>();
		
		List<AdminModel> getNewsData=null;
		if(news_source.equals("news"))
		{
			getNewsData=getCommonServices().getNews(news_id);
			for(AdminModel ap:getNewsData)
			{
			    String title=replaceSpecialCharacters(ap.getNews_title());
			    System.out.println("abc=="+ap.getNews_title());
			    ap.setNews_title(title);
			    readmore_data.put("news_title", ap.getNews_title());
				readmore_data.put("news_image", ap.getImage_url());
				readmore_data.put("news_date", ap.getPublication_date());
			}
		}
		else{
			getNewsData=getCommonServices().getPosts(news_id);
			for(AdminModel ap:getNewsData)
			{
				String title=replaceSpecialCharacters(ap.getMessage());
				/*if(news_source.equals("twitter"))
				{
				title=getTwitterlinks(title);
				}*/
		        ap.setMessage(title);
		        
				readmore_data.put("news_title", ap.getMessage());
				readmore_data.put("news_image", ap.getImage_url());
				readmore_data.put("news_date", ap.getCreated_time());
			}
		}
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String sent_data = gson.toJson(readmore_data);
		result=sent_data;
		}
		return result;
	}
	
	@RequestMapping(value="blogs", method=RequestMethod.GET)
	public String blogsGet(ModelMap map)
	{
		/*int count=0, blog_id=0;
		List<AdminModel> getResentBlog=getCommonServices().getResentBlogs();
		for(AdminModel a:getResentBlog)
		{
			blog_id=a.getBlog_id();
		}*/
		/*List<AdminModel> all_blogs=getCommonServices().getAllBlogs(blog_id);*/
		List<AdminModel> all_blogs=getCommonServices().getAllBlogs();
		for(AdminModel blog_details1:all_blogs){
			System.out.println("abc====================="+blog_details1.getBlog_title());
			}
		map.addAttribute("all_blogs",all_blogs);
		/*map.addAttribute("getResentBlog",getResentBlog);*/
		return "Common/ManageBlogs";
	}
	
	@RequestMapping(value="blogs", method=RequestMethod.POST)
	public String blogsPost()
	{
		return "redirect:blogs";
	}
	
	@RequestMapping(value="blog-details", method=RequestMethod.GET)
	public String blogDetailsGet(ModelMap map, HttpSession session)
	{
		if(session.getAttribute("blog_id")!=null)
		{
			int blog_id=Integer.parseInt(String.valueOf(session.getAttribute("blog_id")));
			List<AdminModel> blog_details=getCommonServices().getBlogsById(blog_id);
			for(AdminModel blog_details1:blog_details){
			System.out.println("abc====================="+blog_details1.getDescription().replaceAll("\\?{2,}", ""));
            String des=blog_details1.getDescription().replaceAll("\\?{2,}", "");
            blog_details1.setDescription(des);
			}
			List<AdminModel> getAllBlogs=getCommonServices().getResentThreeBlogs(blog_id);
			
			map.addAttribute("getAllBlogs",getAllBlogs);
			map.addAttribute("blog_details",blog_details);
			//session.removeAttribute("blog_id");
		}
		return "Common/BlogDetails";
	}

	@RequestMapping(value="blog-details", method=RequestMethod.POST)
	public String blogDetailsPost(HttpServletRequest request, RedirectAttributesModelMap redirectAttributes, HttpSession session)
	{
		if(request.getParameter("blog_id")!=null)
		{
			session.setAttribute("blog_id",request.getParameter("blog_id"));
		}
		return "redirect:blog-details";
	}
	
	@RequestMapping(value="politician-bio", method=RequestMethod.GET)
	public String politicianBioGet(HttpSession session, HttpServletRequest request, ModelMap map)
	{
		UserSession u=new UserSession(session);
		int celebrity_id=0;
		Calendar date= Calendar.getInstance();
		  int current_year=date.getWeekYear();
		  map.addAttribute("current_year",current_year);
		  
		if(session.getAttribute("politician_id")!=null)
		{
		celebrity_id=Integer.parseInt(session.getAttribute("politician_id").toString());
		List<AdminModel> politician_info=getCommonServices().getCelebrity_info(celebrity_id, u.getUser_id());
		for(AdminModel a:politician_info)
		{
			if(a.getCareer()!=null && a.getCareer()!="")
			{
				a.setCareer(a.getCareer().replaceAll("\n", "<br />"));
			}
			if(a.getBio()!=null && a.getBio()!="")
			{
				a.setBio(a.getBio().replaceAll("\n", "<br />"));
			}
			if(a.getCommity()!=null && a.getCommity()!="")
			{
				a.setCommity(a.getCommity().replaceAll("\n", "<br />"));
			}
		}
		map.addAttribute("politician_info",politician_info);

		}
				
		return "Common/PoliticianBio";
	}
	
	@RequestMapping(value="politician-bio", method=RequestMethod.POST)
	public String politicianBioPost(HttpServletRequest request, RedirectAttributesModelMap redirectAttributes, HttpSession session)
	{
		if(request.getParameter("politician_id")!=null)
		{
			//redirectAttributes.addFlashAttribute("politician_id",request.getParameter("politician_id"));
			session.setAttribute("politician_id", request.getParameter("politician_id"));
		}
		return "redirect:politician-bio";
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
	@RequestMapping(value="validate-login",method=RequestMethod.POST)
	@ResponseBody
	public String validateLogin(HttpServletRequest request, HttpSession session)
	{
		String result="ok";
		if(request.getParameter("user_name")!=null)
		{
			String user_name=request.getParameter("user_name").trim();
			String password=request.getParameter("password").trim();
			
			try {
				password=CommonController.encrypt(password);
			} catch (Exception e) {
				e.printStackTrace();
			}
			List<AdminModel> login_info=getCommonServices().validate_email(user_name, password);
			if(login_info.size()==0)
			{
				result="wrong";
			}
			
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

			List<CommonModel> user_information=getCommonServices().getExistingUser(email);
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
			
			getCommonServices().updateForgotedPassword(email, CommonController.encrypt(password));
			redirectAttributes.addFlashAttribute("message","Your new password has been sent on your email. Please check your email");
		}
		return "redirect:home";
	}
	
	public List<AdminModel> getTopNewsCount()
	{
		List<AdminModel> final_news_cnt=null;
		
		int day_interval=0;
		final_news_cnt=getCommonServices().getPopularNews(day_interval);
		while(final_news_cnt.size()<1)
		{
			day_interval++;
			final_news_cnt=getCommonServices().getPopularNews(day_interval);
		}
		
		return final_news_cnt;
	}
	
	public List<AdminModel> getCelebrityNewsfromAPI(String celebrity_name){
		try{
			List<AdminModel> celebrity_news=new ArrayList<>();
			String query=celebrity_name.replace(" ", "+");
				//	String feed_url="https://newsapi.org/v2/everything?q="+query+"&apiKey=43cceeca5c384d1daaf15c95475b5766";//43cceeca5c384d1daaf15c95475b5766	
			String feed_url="https://newsapi.org/v2/everything?Language=en&q=\""+query+"\"&domains=breitbart.com,bloomberg.com,wsj.com,apnews.com,washingtonpost.com,foxnews.com,cnn.com,thehill.com&apiKey=23f9b7d847364efc88a86fa973c198fc&sortBy=publishedAt"; //9ccdaece65b740ebbd3754cd3fb685de    15e4a082d13348e58b47f4330dc5e4e7
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
						            	//ad.setEntity_id(ad.getCelebrity_id());
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
	
	public String replaceSpecialCharacters(String title)
	{
		String c="";
		if(title!=null && title!="")
		{
			c= title;
	        //Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
	        
	        Pattern pt = Pattern.compile("[^'_/.,:;#-$\"A-Za-z0-9 ]");
	        Matcher match= pt.matcher(c);
	        while(match.find())
	        {
	            String s= match.group();
	        c=c.replaceAll("\\"+s, "");
	        }
	        
		}
        return c;
	}
	/*public String getTwitterlinks(String title)
	{
		String c="";
		if(title!=null && title!="")
		{
			c= title;
			String pt = "\\(?\\b(https://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
			Pattern pt1 = Pattern.compile(pt);
	        Matcher match= pt1.matcher(c);
	        while(match.find())
	        {
	        String s= match.group();
	        c=c.replaceAll(s, "<a href='"+s+"' target='_blank'>"+s+"</a>");
	        }
		}
        return c;
	}*/
	
	@RequestMapping(value="bills",method=RequestMethod.GET)
	public String billsGet(ModelMap map)
	{
		List<AdminModel> bills=getCommonServices().getBills();
		map.addAttribute("bills",bills);
		
		return "Common/Bills";
	}
	
	@RequestMapping(value="bills",method=RequestMethod.POST)
	public String billsPost()
	{
		return "redirect:bills";
	}
	
	@RequestMapping(value="privacy-policy",method=RequestMethod.GET)
	public String showPrivacyPolicy()
	{
		return "Common/privacy_policy";
	}
	
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
			@SuppressWarnings("restriction")
			String encryptedValue64 = new BASE64Encoder().encode(encryptedByteValue);
			return encryptedValue64;
		}
		
		/*@RequestMapping(value="encrypt-passwords-rsbs",method=RequestMethod.GET)
		public String encryptPassword() throws Exception
		{
			List<AdminModel> allUsers=getCommonServices().getAllUsers();
			for(AdminModel allUsers1:allUsers){
				String pass=allUsers1.getPassword();
				pass=CommonController.encrypt(pass);
            getCommonServices().updatePassword(allUsers1.getUser_id(), pass);
			}
			return "Common/privacy_policy";
		}*/
		
}
