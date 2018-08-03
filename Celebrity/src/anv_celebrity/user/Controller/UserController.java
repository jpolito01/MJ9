package user.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.itextpdf.text.log.SysoLogger;

import admin.model.AdminModel;

import user.Services.UserServices;

import common.controller.CommonController;
import common.controller.UserSession;
import common.model.CommonModel;

@Controller
public class UserController {
	@Autowired
	private UserServices userServices;

	public UserServices getUserServices() {
		return userServices;
	}

	public void setUserServices(UserServices userServices) {
		this.userServices = userServices;
	}
	@RequestMapping(value="user-like")
	public String user_like(HttpServletRequest request,HttpSession session,RedirectAttributesModelMap map)
	{
		UserSession u=new UserSession(session);
		int user_id=u.getUser_id();
		String news_id=request.getParameter("news_id");
		String like_flag=request.getParameter("like-dislike");
		String activityTypeId= request.getParameter("activity_type_id");
		
		if(user_id!=0 && news_id!=null && !news_id.equals("") && like_flag!=null && like_flag.equals("1") && activityTypeId!=null && !activityTypeId.equals(""))
		{
			int activity_type_id=Integer.parseInt(""+request.getParameter("activity_type_id"));
			getUserServices().insertUserLikeMap(user_id, Integer.parseInt(news_id),activity_type_id);
		}
		else 		if(user_id!=0 && news_id!=null && !news_id.equals("") && like_flag!=null && like_flag.equals("0") && activityTypeId!=null && !activityTypeId.equals(""))
		{
			
			int activity_type_id=Integer.parseInt(""+request.getParameter("activity_type_id"));
			getUserServices().dissLikeNews(user_id, Integer.parseInt(request.getParameter("news_id")), activity_type_id);
		}
		
		if(request.getParameter("tab_id")!=null)
		{
			
			if(!(request.getParameter("tab_id")+"").equals(""))
			{
				String tab_info=request.getParameter("tab_id")+"";
				String tab_id=tab_info.split(",")[0];
				if(tab_id.equals("celebrity_profile"))
				{
					String sub_tab=tab_info.split(",")[1];
					map.addFlashAttribute("show_news",news_id);
					map.addFlashAttribute("sub_tab_id",sub_tab);
					return "redirect:celebrity-profile";
				}
			}
			map.addFlashAttribute("type", request.getParameter("tab_id"));
		}
		
		return "redirect:home";
	}
	@RequestMapping(value="user-comments",method=RequestMethod.POST)
	public String user_comments_post(HttpServletRequest request,AdminModel  ad,RedirectAttributesModelMap map,HttpSession session)
	{
		UserSession u=new UserSession(session);
		int user_id=u.getUser_id();
		if(request.getParameter("comment_id") !=null && u.getUser_id()!=0)
		{ // Delete comment
			int comment_id=Integer.parseInt(""+request.getParameter("comment_id"));
			getUserServices().delete_comment(comment_id);
			map.addFlashAttribute("comment_news_id", session.getAttribute("news_id_of_comments"));
		}
		if(request.getParameter("news_id")!=null && u.getUser_id()!=0)
		{ // it is just for display modal & set news id in session
			session.setAttribute("news_id_of_comments", request.getParameter("news_id"));
			map.addFlashAttribute("comment_news_id", request.getParameter("news_id"));
		}
		
		if(session.getAttribute("comment_newsid")!=null && session.getAttribute("incomming_tab")!=null && ad.getComments()!=null && u.getUser_id()!=0)
		{
			int news_id=Integer.parseInt(session.getAttribute("comment_newsid")+"");
			int activity_type_id=Integer.parseInt(session.getAttribute("activity_type_id")+"");
			ad.setPost_id_or_news_id(news_id);
			ad.setActivity_type_id(activity_type_id);
			ad.setUser_id(user_id);
			getUserServices().InsertNews_comments(ad);
		}
		/*
		 * 
		 * 
		 * if(session.getAttribute("news_id_of_comments")!=null && request.getParameter("news_id")==null)
		{ // Insert comments
			String comments=ad.getComments();
			if(comments!=null &&!comments.equals("") && user_id!=0)
			{
				ad.setPost_id_or_news_id(Integer.parseInt(session.getAttribute("news_id_of_comments")+""));
				ad.setNews_id_or_post_id(Integer.parseInt(session.getAttribute("news_id_of_comments")+""));
				ad.setActivity_type_id(1);
				ad.setUser_id(user_id);
				getUserServices().InsertNews_comments(ad);
			}
			map.addFlashAttribute("comment_news_id", session.getAttribute("news_id_of_comments"));
		}*/
		if(session.getAttribute("incomming_tab")!=null) // it is important to go back in same tab 
		{
			String tab_info=session.getAttribute("incomming_tab")+"";
			map.addFlashAttribute("show_comments",session.getAttribute("comment_newsid"));
			if(!tab_info.equals(""))
			{
				
				if(tab_info.split(",").length>1)
				{
					String tab_id=tab_info.split(",")[0];
					if(tab_id.equals("celebrity_profile"))
					{
						String sub_tab=tab_info.split(",")[1];
						map.addFlashAttribute("show_news",session.getAttribute("news_id_of_comments"));
						map.addFlashAttribute("sub_tab_id",sub_tab);
						map.addAttribute("activity_type_id",session.getAttribute("activity_type_id"));
						return "redirect:celebrity-profile";
					}
				}
			}
			
			map.addFlashAttribute("type", tab_info);
		}
		return "redirect:home";
	}

	@RequestMapping(value="user-follow",method=RequestMethod.POST)
	public String user_follow_post(HttpServletRequest request,RedirectAttributesModelMap map,HttpSession session)
	{
		UserSession u=new UserSession(session);
		
		if(request.getParameter("tab_id")!=null) // it is important to go back in same tab 
		{
			map.addFlashAttribute("type", request.getParameter("tab_id"));
		}
		if(u.getUser_id()!=0 && request.getParameter("entity_id")!=null && request.getParameter("entity_type_id")!=null)
		{
			int user_id=u.getUser_id();
			int entity_type_id=Integer.parseInt(request.getParameter("entity_type_id")+"");
			int entity_id=Integer.parseInt(request.getParameter("entity_id"));
			/*int news_letter_type=Integer.parseInt(request.getParameter("news_letter_type"));
			getUserServices().addFollowers(user_id, entity_type_id, entity_id, news_letter_type);*/
			getUserServices().addFollowers(user_id, entity_type_id, entity_id, 1);
		}
//		&page="+"actor";
		if(request.getParameter("tab_id")!=null)
		{
			String p=request.getParameter("tab_id")+"";
			if(p.equals("actor"))
			{
				return "redirect:actors-models";
			}
			else if(p.equals("celebrity-profile"))
			{
				return "redirect:celebrity-profile";
			}
			else if(p.equals("athlets"))
			{
				return "redirect:athletes";
			}
			else if(p.equals("musician"))
			{
				return "redirect:musician";
			}else if(p.equals("movie"))
			{
				return "redirect:movie";
			}
			else if(p.equals("celebrity"))
			{
				return "redirect:all-celebrities";
			}
		}
		
		return "redirect:home";
	}
	@RequestMapping(value="user-setting",method=RequestMethod.GET)
	public String User_Setting_get(ModelMap map,HttpSession session)
	{
		UserSession u=new UserSession(session);
		if(u.getUser_id()==0)
		{
			return "redirect:home";
		}
		String activated_tab="update_profile";
		
		if(map.get("activated_tab")!=null)
		{
			activated_tab=""+map.get("activated_tab");
		}
		if(activated_tab.equals("update_profile"))
		{
			
			List<CommonModel> user_profile=getUserServices().getUserProfile(u.getUser_id());
			map.addAttribute("user_profile",user_profile);
		}
		else if(activated_tab.equals("change_password"))
		{
		}
		else if(activated_tab.equals("activate_acc"))
		{
			
		}
		else if(activated_tab.equals("follow_list"))
		{
			List<AdminModel> getUserFollowerList=getUserServices().getUserFollowerList(u.getUser_id());
			
			map.addAttribute("getUserFollowerList",getUserFollowerList);
		}
		
		
		map.addAttribute("activated_tab",activated_tab);
		
		
		return "User/UserSettings";
	}
	@RequestMapping(value="user-setting",method=RequestMethod.POST)
	public String User_Setting_Post(HttpServletRequest request,RedirectAttributesModelMap map)
	{
		
		if(request.getParameter("menu_name")!=null)
		{
			String tab_name=request.getParameter("menu_name")+"";
			map.addFlashAttribute("activated_tab", tab_name);
		}
		return "redirect:user-setting";
	}
	@RequestMapping(value="update-profile",method=RequestMethod.POST)
	public String updateProfilePost(RedirectAttributesModelMap map,@Valid CommonModel cm,HttpSession session)
	{
		UserSession u=new UserSession(session);
		cm.setUser_id(u.getUser_id());
		getUserServices().UpdateProfile(cm);
		map.addFlashAttribute("message", "Infomation Updates Successfully..");
		map.addFlashAttribute("activated_tab", "update_profile");
		return "redirect:user-setting";
	}
	@RequestMapping(value="change_password",method=RequestMethod.POST)
	public String ChangePassword(HttpServletRequest request,RedirectAttributesModelMap map,HttpSession session) throws Exception
	{
		UserSession u=new UserSession(session);
		
		String password=request.getParameter("confirm_password");
		password=CommonController.encrypt(password);
		getUserServices().changePassword(password, u.getUser_id());
		map.addFlashAttribute("activated_tab", "change_password");
		map.addFlashAttribute("message", "Password changed successfully.");
		return "redirect:user-setting";
	}
	
	@RequestMapping(value="comments",method=RequestMethod.POST)
	@ResponseBody
	public String AddComments(HttpServletRequest request,HttpSession session)
	{
		String result="ok";
		int comment_count=0;
		 int news_id=Integer.parseInt(request.getParameter("comment_newsid")+"");
			int activity_type_id=Integer.parseInt(request.getParameter("activity_type_id")+"");

		if(request.getParameter("comment_id") !=null)
		{ // Delete comment
			int comment_id=Integer.parseInt(""+request.getParameter("comment_id"));
			getUserServices().delete_comment(comment_id);
			/*map.addFlashAttribute("comment_news_id", session.getAttribute("news_id_of_comments"));*/
		}
		
		 if(request.getParameter("comment_newsid")!=null && request.getParameter("tab_id")!=null && request.getParameter("comments")!=null && request.getParameter("comments")!= "")
		 {
			 /*session.setAttribute("comment_newsid", request.getParameter("comment_newsid"));
			 session.setAttribute("incomming_tab", request.getParameter("tab_id"));
			 session.setAttribute("activity_type_id", request.getParameter("activity_type_id"));*/
			 AdminModel ad = new AdminModel();
			 UserSession u=new UserSession(session);
							String comments=request.getParameter("comments");
				ad.setPost_id_or_news_id(news_id);
				ad.setActivity_type_id(activity_type_id);
				ad.setUser_id(u.getUser_id());
				ad.setComments(comments);
				getUserServices().InsertNews_comments(ad);
			 
		 }
		 comment_count=getUserServices().getNewsCommentsCount(news_id, activity_type_id);

		 List<AdminModel> news_comments=getUserServices().getNews_Comments(Integer.parseInt(""+request.getParameter("comment_newsid")),Integer.parseInt(""+request.getParameter("activity_type_id")));
		 String data="{\"comments\":[";
		 ArrayList<String> comments_data=new ArrayList<>();
		int cnt=0;
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		 for(AdminModel a:news_comments)
		 {
			 cnt++;
			 HashMap<String, String> temp_st=new HashMap<>();
			 	temp_st.put("user_id",String.valueOf(a.getUser_id()));
			 	temp_st.put("user_name",a.getName());
			 	temp_st.put("comment_id",String.valueOf(a.getComment_id()));
			 	temp_st.put("comments_details",a.getComments());
			 	temp_st.put("comment_count",String.valueOf(comment_count));
			 	String single_data=gson.toJson(temp_st);
				comments_data.add(single_data+"");
			 	 /*String str=temp_st+"";
			 	 str=str.replace("=", ":");
			 	 if(cnt<news_comments.size())
			 	 {
			 		 str=str+",";
			 	 }
			 	 data=data+str;*/
		 }
		 /*data=data+"]}";
		 result=data;*/
		 result=comments_data+"";
		 result="{\"comments\":"+result+"}";
		return result;
	}
	
	
	@RequestMapping(value="check_password")
	@ResponseBody
	public String checkPassword(HttpServletRequest request,HttpSession session) throws Exception
	{
		String result="false";
		UserSession u=new UserSession(session);
		String pass="";
		if(request.getParameter("password")!=null)
		{
			pass=request.getParameter("password");
			List<CommonModel> login_info=getUserServices().getLoginInformation_ById(u.getUser_id());
			for(CommonModel cm:login_info)
			{
				if(pass.equals(CommonController.decrypt(cm.getPassword())))
				{
					result="true";
				}
			}
		}
		return result;
	}
	
	@RequestMapping(value="followers",method=RequestMethod.POST)
	public String followersPost(HttpServletRequest request, RedirectAttributesModelMap redirectAttributes)
	{
		if(request.getParameter("activeTab")!=null)
        {
        	redirectAttributes.addFlashAttribute("activeTab",request.getParameter("activeTab"));
        }
		if(request.getParameter("follow_id")!=null)
		{
			int follow_id=Integer.parseInt(request.getParameter("follow_id"));
			getUserServices().unfollowCelebrity(follow_id);
			redirectAttributes.addFlashAttribute("message","Unfollowed successfully.!!!");
			redirectAttributes.addFlashAttribute("activated_tab", "follow_list");
			if(request.getParameter("tab_id")!=null)
			{
				String p=request.getParameter("tab_id")+"";
				if(p.equals("actor"))
				{
					return "redirect:actors-models";
				}
				else if(p.equals("celebrity-profile"))
				{
					return "redirect:celebrity-profile";
				}
				else if(p.equals("athlets"))
				{
					return "redirect:athletes";
				}
				else if(p.equals("musician"))
				{
					return "redirect:musician";
				}else if(p.equals("movie"))
				{
					return "redirect:movie";
				}
				else if(p.equals("home-page"))
				{
					return "redirect:home";
				}
				else if(p.equals("celebrity"))
				{
					return "redirect:all-celebrities";
				}
			}
		}
		
		return "redirect:user-setting";
	}
	
	@RequestMapping(value="user-likes-function",method=RequestMethod.POST)
	@ResponseBody
	public String userLikesFunction(HttpServletRequest request,HttpSession session)
	{
		String result="";
		UserSession u=new UserSession(session);
		int user_id=u.getUser_id();
		String news_id=request.getParameter("news_id");
		String like_flag=request.getParameter("like-dislike");
		String activityTypeId= request.getParameter("activity_type_id");
		HashMap<String, String> like_data=new HashMap<>();
		session.setAttribute("activityTypeId", activityTypeId);
		
		int count=0;
		if(user_id!=0 && news_id!=null && !news_id.equals("") && like_flag!=null && like_flag.equals("1") && activityTypeId!=null && !activityTypeId.equals(""))
		{
			int flag=0;
			int activity_type_id=Integer.parseInt(""+request.getParameter("activity_type_id"));
			List<AdminModel> is_aulready_exist=getUserServices().getIsAulreadyLiked(user_id,Integer.parseInt(news_id),activity_type_id);
			if(is_aulready_exist.size()!=0)
			{
				flag=1;
			}
			if(flag==0)
			{
				getUserServices().insertUserLikeMap(user_id, Integer.parseInt(news_id),activity_type_id);
				
			}
			int newsId=Integer.parseInt(news_id);
			count = getUserServices().getUserLikeCount(newsId, activity_type_id);
		}
		else if(user_id!=0 && news_id!=null && !news_id.equals("") && like_flag!=null && like_flag.equals("0") && activityTypeId!=null && !activityTypeId.equals(""))
		{
			int activity_type_id=Integer.parseInt(""+request.getParameter("activity_type_id"));
			getUserServices().dissLikeNews(user_id, Integer.parseInt(request.getParameter("news_id")), activity_type_id);
			int newsId=Integer.parseInt(news_id);
			count = getUserServices().getUserLikeCount(newsId, activity_type_id);
		}
		
	    like_data.put("like_count",String.valueOf(count));
	    Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String sent_data = gson.toJson(like_data);
		result=sent_data;
		return result;
	}
}