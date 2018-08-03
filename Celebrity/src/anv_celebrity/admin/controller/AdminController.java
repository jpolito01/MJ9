package admin.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.controller.EmailController;
import common.controller.UserSession;

import admin.model.AdminModel;
import admin.services.AdminServicesImpl;
@Controller
public class AdminController {
	@Autowired
	private AdminServicesImpl adminServices;

	public AdminServicesImpl getAdminServices() {
		return adminServices;
	}

	public void setAdminServices(AdminServicesImpl adminServices) {
		this.adminServices = adminServices;
	}
	
	@RequestMapping(value="admin-home",method=RequestMethod.GET)
	public String AdminHome_get(ModelMap map)
	{
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		return "redirect:celebrity";
		
	}
	@RequestMapping(value="admin-home",method=RequestMethod.POST)
	public String AdminHome_post(HttpServletRequest request,HttpSession session,RedirectAttributesModelMap map)
	{
		int celebrity_id_to_edit=0;
		if(request.getParameter("celebrity_id")!=null) //--------------------To edit celebrity Information
		{
			celebrity_id_to_edit=Integer.parseInt(request.getParameter("celebrity_id")+"");
			if(celebrity_id_to_edit!=0)
				session.setAttribute("edit_celebrity_id",celebrity_id_to_edit);
			map.addFlashAttribute("edit_celebrity", "yes");
		}
		if(request.getParameter("active_inactive_is")!=null && request.getParameter("status")!=null)
		{
			int is_active=Integer.parseInt(request.getParameter("status")+"");
			int celebrity_id=Integer.parseInt(request.getParameter("active_inactive_is")+"");
			if(is_active==1)
			{
				getAdminServices().Activate_celebrity(celebrity_id);
				map.addFlashAttribute("message","Celebrity activated successfully");
			}
			else if(is_active==0)
			{
				getAdminServices().Inactivate_celebrity(celebrity_id);
				map.addFlashAttribute("message","Celebrity deactivated successfully");
			}
			if(request.getParameter("redirectTo")!=null && request.getParameter("redirectTo")!="")
			{
				String to=request.getParameter("redirectTo");
				if(to.equals("celebrity"))
				{
					return "redirect:celebrity";
				}
				else if(to.equals("movie"))
				{
					return "redirect:released-movies";
				}
			}
		}
		if(request.getParameter("delete_celebrity_id")!=null)
		{
			int delete_celebrity_id=Integer.parseInt(request.getParameter("delete_celebrity_id")+"");
			
			getAdminServices().DeleteCelebrity_Likes(delete_celebrity_id);
			getAdminServices().DeleteCelebrity_Comments(delete_celebrity_id);
			getAdminServices().Delete_celebrity_News(delete_celebrity_id);
			getAdminServices().DeleteCelebrity_Comments(delete_celebrity_id);
			getAdminServices().DeleteCelebrity_info(delete_celebrity_id);
			map.addFlashAttribute("message","Celebrity deleted successfully");
		}
		if(request.getParameter("show_celebrity_details")!=null)
		{
			int cid=Integer.parseInt(request.getParameter("show_celebrity_details")+"");
			map.addFlashAttribute("show_celebrity_details", cid);
		}
		return "redirect:celebrity";
	}
	@RequestMapping(value="requested-celebrities",method=RequestMethod.GET)
	public String requested_celebrities(ModelMap map,HttpSession session)
	{
		int limit=20;
		int offset=0;
		int total_celebrity=0;

		if(session.getAttribute("limit")==null)
		{
			session.setAttribute("limit", 20);
		}
		limit=Integer.parseInt(session.getAttribute("limit").toString());
		
		if(map.get("offset")!=null)
		{
			offset=Integer.parseInt(map.get("offset")+"");
		}
		
		map.addAttribute("offset", offset);
		map.addAttribute("limit", limit);
		total_celebrity=getAdminServices().getAllRequestedCelebrityInfo().size();
		map.addAttribute("total_celebrity", total_celebrity);
		List<AdminModel> celebrity_info=getAdminServices().getRequestedCelebrityInfoWithLimit(limit, offset);
		map.addAttribute("celebrity_info",celebrity_info);
		List<AdminModel> mapped_category=getAdminServices().getAllRequestedCelebrityCategoriesInfo();
		map.addAttribute("mapped_category",mapped_category);
		return "Admin/dispRequestedCelebrity";
	}
	
	@RequestMapping(value="requested-celebrities",method=RequestMethod.POST)
	public String requestedCelebrities(HttpServletRequest request,HttpSession session,RedirectAttributesModelMap map)
	{
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
		return "redirect:requested-celebrities";
	}
	
	@RequestMapping(value="celebrity",method=RequestMethod.GET)
	public String celebrity_get(ModelMap map,HttpSession session)
	{
		/*TaskTest tt=new TaskTest();
		tt.run();*/
		
		UserSession u=new UserSession(session);
		if(u.getUser_id()==0)
		{
			return "redirect:home"; 
		}
		if(u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		if(map.get("edit_celebrity")!=null)
		{
			int celebrity_id_e=Integer.parseInt(session.getAttribute("edit_celebrity_id")+"");
			List<AdminModel> edit_celebrity=getAdminServices().getCelebrityInfo_byId(celebrity_id_e);
			List<AdminModel> state_list=null;
			for(AdminModel a:edit_celebrity)
			{
				int country_id=a.getCountry_id();
				state_list=getAdminServices().getStates_name(country_id);
			}
			List<AdminModel> category_edit=getAdminServices().getCelebrityCategory_details_ById(celebrity_id_e);
			
			map.addAttribute("category_edit", category_edit);
			map.addAttribute("state_list", state_list);
			map.addAttribute("edit_celebrity_info", edit_celebrity);
			map.addAttribute("edit_celebrity", "yes");
		}
		List<AdminModel> show_category = null;
		if(map.get("show_celebrity_details")!=null)
		{
			int s=Integer.parseInt(map.get("show_celebrity_details")+"");
			show_category=getAdminServices().getCelebrityCategory_details_ById(s);
			List<AdminModel> show_celebrity=getAdminServices().getCelebrityInfo_byId(s);
			
			map.addAttribute("show_celebrity_category", show_category);
			map.addAttribute("show_celebrity_details", show_celebrity);
			map.addAttribute("show_celebrity", "yes"); 
		}
		int limit=20;
		int offset=0;
		int total_celebrity=0;

		if(session.getAttribute("limit")==null)
		{
			session.setAttribute("limit", 20);
		}
		limit=Integer.parseInt(session.getAttribute("limit").toString());
		
		if(map.get("offset")!=null)
		{
			offset=Integer.parseInt(map.get("offset")+"");
		}
		
		map.addAttribute("offset", offset);
		map.addAttribute("limit", limit);
		List<AdminModel> mapped_category=getAdminServices().getCelebrityCategory_details();
		List<AdminModel> country_list=getAdminServices().getAllCountries();
		List<AdminModel> category=getAdminServices().getCelebrity_category();
        if(map.get("search_celebrity_key")!=null && map.get("search_celebrity_key")!="")
        {
        	String search_celebrity_key=map.get("search_celebrity_key").toString();
        	search_celebrity_key="%"+search_celebrity_key+"%";
            total_celebrity=getAdminServices().getSearchedCelebrityCount(search_celebrity_key);
        	List<AdminModel> celebrity_list=getAdminServices().getSearchCelebrity_info(search_celebrity_key);
    		map.addAttribute("celebrity_info", celebrity_list);
        }else{
            total_celebrity=getAdminServices().getCelebrityCount();
    		List<AdminModel> celebrity_list=getAdminServices().showCelebrity_info(limit,offset);
    		map.addAttribute("celebrity_info", celebrity_list);
        }
        map.addAttribute("total_celebrity",total_celebrity);
		map.addAttribute("mapped_category", mapped_category);
		map.addAttribute("celebrity_category", category);
		map.addAttribute("country_list", country_list);
		
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		return "Admin/Celebrity";
	}
	@RequestMapping(value="celebrity",method=RequestMethod.POST)
	public String celebrity_post(HttpServletRequest request,@Valid AdminModel ad,@RequestParam("d_file") MultipartFile d_file,RedirectAttributesModelMap map,HttpSession session) throws IllegalStateException, IOException
	{
		
		UserSession u=new UserSession(session);
		//int celebrity_id_to_edit=0;
		int celebrity_id=0; //-------------- for insertion purpose
		if(u.getUser_id()==0 || u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		
		if(ad.getCelebrity_name()!=null && !ad.getCelebrity_name().equals("")) //&& celebrity_id_to_edit==0)
		{
			getAdminServices().insertCelebrityInfo(ad);
			
			/* celebrity cast*/
			int movie_id=0;
			session.setAttribute("movie_name", ad.getCelebrity_name());
			if(session.getAttribute("movie_name")!=null)
			{
				List<AdminModel> movie=getAdminServices().getMovieId(session.getAttribute("movie_name").toString());
				for(AdminModel a:movie)
				{
					movie_id=a.getCelebrity_id();
				}
				session.setAttribute("movie_id", movie_id);
			}
			
			List<AdminModel> celebrity_info=getAdminServices().getLast_addedCelebrity();
			for(AdminModel ci:celebrity_info)
			{
				celebrity_id=ci.getCelebrity_id();
				ad.setCelebrity_id(celebrity_id);
			}
			if(celebrity_id==0)
			{
				map.addFlashAttribute("error_message","Somthing wrong.");
				return "redirect:celebrity";
			}
			if(!d_file.getOriginalFilename().equals(""))
			{
				if(!d_file.getInputStream().equals(null))
					{
						Date d=new Date();
						String file_name="C"+celebrity_id+"_"+d.getTime()+"."+d_file.getContentType().split("/")[1];
						
						String filePath="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/ProfilePhoto/"+file_name;
						File uploadedFile=new File(filePath);
							d_file.transferTo(uploadedFile);
							ad.setProfile_photo_name(file_name);
							
							ad.setProfile_photo_path("/CelebrityPhoto/ProfilePhoto/"+file_name);
							getAdminServices().UpdateCelebrity_Profile(ad);
					}
			}
			String category_name=ad.getCategory_name();
			String category_id[]=category_name.split(",");
			for(int i=0;i<category_id.length;i++)
			{
				ad.setCategory_id(Integer.parseInt(category_id[i]));
				getAdminServices().addCelebrity_Category(ad);
			}
			if(request.getParameter("search_id")!=null)
			{
				if(!(request.getParameter("search_id")+"").equals(""))
				{
					int search_id=Integer.parseInt(request.getParameter("search_id")+"");
					getAdminServices().DeleteUserSearchTerms(search_id);
					map.addFlashAttribute("message","Celebrity added successfully");
					return "redirect:user-search-result";
				}
				
			}
			map.addFlashAttribute("message","Celebrity added successfully");
			
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
		return "redirect:celebrity";
	}
	@RequestMapping(value="celebrity_pagination",method=RequestMethod.POST)
	public String celebrity_post1(HttpServletRequest request,RedirectAttributesModelMap map,HttpSession session) throws IllegalStateException, IOException
	{
		
		UserSession u=new UserSession(session);
		//int celebrity_id_to_edit=0;
		int celebrity_id=0; //-------------- for insertion purpose
		if(u.getUser_id()==0 || u.getRole_id()!=1)
		{
			return "redirect:home";
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
		if(request.getParameter("search_celebrity_key")!=null)
		{
			map.addFlashAttribute("search_celebrity_key",request.getParameter("search_celebrity_key"));
		}
		
		return "redirect:celebrity";
	}
	@RequestMapping(value="edit-celebrity",method=RequestMethod.POST)
	public String edit_celebrity_post(HttpServletRequest req,@Valid AdminModel ad,HttpSession session,@RequestParam("d_file") MultipartFile d_file,RedirectAttributesModelMap map) throws IllegalStateException, IOException
	{
		int categoryName=0;
		if(ad.getCategory_name().equals("6"))
		{
			categoryName=Integer.parseInt(ad.getCategory_name()+"");
		}
		/*if(req.getParameter("active_inactive_is")!=null && req.getParameter("status")!=null)
		{
			System.out.println("Is it working.....");
			int is_active=Integer.parseInt(req.getParameter("status")+"");
			int celebrity_id=Integer.parseInt(req.getParameter("active_inactive_is")+"");
			if(is_active==1)
			{
				getAdminServices().Activate_celebrity(celebrity_id);
			}
			else if(is_active==0)
			{
				getAdminServices().Inactivate_celebrity(celebrity_id);
			}
		}*/
		/*if(req.getParameter("delete_celebrity_id")!=null)
		{
			getAdminServices().DeleteCelebrity_info(Integer.parseInt(req.getParameter("delete_celebrity_id")+""));
		}*/
		if(session.getAttribute("edit_celebrity_id")!=null && session.getAttribute("edit_celebrity_id")!="" && ad.getCelebrity_name()!=null && ad.getCelebrity_name()!="")
		{
			
			int celebrity_id=Integer.parseInt(session.getAttribute("edit_celebrity_id")+"");
			ad.setCelebrity_id(Integer.parseInt(session.getAttribute("edit_celebrity_id")+""));
			getAdminServices().updateCelebrity_info(ad);
			
			getAdminServices().deleteCelebrity_category(ad.getCelebrity_id());
			String[] categories=ad.getCategory_name().split(",");
			for(int i=0;i<categories.length;i++)
			{
				ad.setCategory_id(Integer.parseInt(categories[i]));
				getAdminServices().addCelebrity_Category(ad);
			}
			if(!d_file.getOriginalFilename().equals(""))
			{
				if(!d_file.getInputStream().equals(null))
					{
						List<AdminModel> profile_photo=getAdminServices().getCelebrity_Profile_photo(celebrity_id);
						for(AdminModel a:profile_photo)
						{
							String file="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/ProfilePhoto/"+a.getProfile_photo_name();
							File f=new File(file);
							f.delete();
						}
						Date d=new Date();
						String file_name="C"+celebrity_id+"_"+d.getTime()+"."+d_file.getOriginalFilename().split("\\.")[1];
						
						String filePath="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/ProfilePhoto/"+file_name;
						File uploadedFile=new File(filePath);
							d_file.transferTo(uploadedFile);
							ad.setProfile_photo_name(file_name);
							
							ad.setProfile_photo_path("/CelebrityPhoto/ProfilePhoto/"+file_name);
							getAdminServices().UpdateCelebrity_Profile(ad);
					}
			}
			map.addFlashAttribute("message","Celebrity information updated successfully");
		}
		if(categoryName==6){
			session.setAttribute("movie_name", ad.getCelebrity_name());
			map.addFlashAttribute("edit_cast", "yes");
			return "redirect:add-cast";
		}
		return "redirect:celebrity";
	}
	@RequestMapping(value="band",method=RequestMethod.GET)
	public String band_get()
	{
		return "Admin/Band";
	}
	@RequestMapping(value="sport",method=RequestMethod.GET)
	public String SportGet()
	{
		return "Admin/Sport";
	}
	
	@RequestMapping(value="released-movies",method=RequestMethod.GET)
	public String ReleasedMoviesGet(ModelMap map)
	{
		List<AdminModel> movies_list=getAdminServices().getMovies_list();
		map.addAttribute("movies_list", movies_list);
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		return "Admin/ReleasedMovies";
	} 
	
	@RequestMapping(value="general-settings",method=RequestMethod.GET)
	public String generalSettings_Get(ModelMap map)
	{
		String tab_name="manage_category";
		
		
		if(map.get("tab_name")!=null)
		{
			tab_name=map.get("tab_name")+"";
		}
		
		if(tab_name.equals("manage_category"))
		{
			List<AdminModel> category=getAdminServices().getCelebrity_category();
			map.addAttribute("categories", category);
		}
		else if(tab_name.equals("manage_country"))
		{
			List<AdminModel> country_list=getAdminServices().getAllCountries();
			List<AdminModel> state_list=getAdminServices().getAllSttes();
			
			map.addAttribute("country_list", country_list);
			map.addAttribute("state_list", state_list);
			
		}
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		map.addAttribute("tab_name",tab_name);
		
		return "Admin/GeneralSettings";
	}
	@RequestMapping(value="general-settings",method=RequestMethod.POST)
	public String generalSettings_Post(HttpServletRequest request,RedirectAttributesModelMap map)
	{
		if(request.getParameter("category_name")!=null)
		{
			String categoryName=request.getParameter("category_name")+"";
			if(!categoryName.equals(""))
			{
				getAdminServices().InsertCelebrity_Category(categoryName);
				map.addFlashAttribute("message","Category added successfully");
			}
		}
		
		if(request.getParameter("edit_category_name")!=null && request.getParameter("edit_category_name")!="")
		{
			
			String cat_name=request.getParameter("edit_category_name")+"";
			String catId=request.getParameter("category_id")+"";
			int cat_id=Integer.parseInt(request.getParameter("category_id")+"");
			if((!cat_name.equals("") || !cat_name.equals("null")) && (!catId.equals("") || !catId.equals("null")))
			{
				getAdminServices().updateCateogry_name(cat_name, cat_id);
				map.addFlashAttribute("message","Category updated successfully");
			}
		}
		if(request.getParameter("delete_category_id")!=null)
		{
			String catId=request.getParameter("delete_category_id")+"";
			if(!catId.equals(""))
			{
				int cat_id=Integer.parseInt(catId);
				getAdminServices().deleteCategory_fromMaster(cat_id);
			}
			map.addFlashAttribute("message","Category deleted successfully");
		}
		if(request.getParameter("country_name")!=null)
		{
			String country_name=request.getParameter("country_name");
			if(!country_name.equals(""))
			{
				getAdminServices().addCountry_Name(country_name);
			}
		}
		String tab_name="manage_category";
		if(request.getParameter("tab_name")!=null)
		{
			tab_name=request.getParameter("tab_name")+"";
			if(tab_name.equals(""))
			{
				tab_name="manage_category";
			}
		}
		map.addFlashAttribute("tab_name", tab_name);
		 
		
		return "redirect:general-settings";
	}
	@RequestMapping(value="user-search-result",method=RequestMethod.GET)
	public String approveUserSearch_resultGet(ModelMap map)
	{
		List<AdminModel> search_terms=getAdminServices().getUserSearch_terms();
		List<AdminModel> country_list=getAdminServices().getAllCountries();
		List<AdminModel> celebrity_category=getAdminServices().getCelebrity_category();
		
		map.addAttribute("celebrity_category", celebrity_category);
		map.addAttribute("country_list", country_list);
		map.addAttribute("search_terms", search_terms);
		if(map.get("error")!=null)
		{
			map.addAttribute("error",map.get("error"));
		}
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		return "Admin/UserSearchResult";
	}
	@RequestMapping(value="user-search-result",method=RequestMethod.POST)
	public String approveUserSearch_resultPost(RedirectAttributesModelMap map,HttpServletRequest req)
	{
		if(req.getParameter("delete_id")!=null)
		{
			String id=req.getParameter("delete_id")+"";
			if(!id.equals(""))
			{
				getAdminServices().DeleteUserSearchTerms(Integer.parseInt(id));
				map.addFlashAttribute("message","Search key deleted successfully");
			}
		}
		if(req.getParameter("search_key")!=null && req.getParameter("search_id")!=null)
		{
			String search_key=req.getParameter("search_key")+"";
			String search_id =req.getParameter("search_id")+"";
			if(!search_id.equals("") && !search_key.equals(""))
			{
				int id=Integer.parseInt(search_id);
				List<AdminModel> search_terms=getAdminServices().getSearchTerms_ByKey(search_key);
				if(!search_terms.isEmpty())
				{
					map.addFlashAttribute("error", "This search term already present.");
					return "redirect:user-search-result";
				}
				getAdminServices().UpdateUserSearchedTerms(search_key, id);
				map.addFlashAttribute("message","Search key updated successfully");
			}
			
		}
		return "redirect:user-search-result";
	}
	
	@RequestMapping(value="blacklist-website",method=RequestMethod.POST)
	public String BlacklistWebsite_Post(@Valid AdminModel ad,RedirectAttributesModelMap map,HttpServletRequest request)
	{
		int flag=0;
		if(request.getParameter("edit_web_id")!=null)
		{
			String id=""+request.getParameter("edit_web_id");
			if(!id.equals(""))
			{
				String website_name=request.getParameter("website_name");
				try{
				//	URL u=new URL(website_name);
				}catch(Exception e){flag++;}
				if(flag>0)
				{
					map.addFlashAttribute("error", "This website is not valid.");
					return "redirect:blacklist-website";
				}
				List<AdminModel> blacklist_website=getAdminServices().getBlacklist_WebsiteByName(website_name);
				if(!blacklist_website.isEmpty())
				{
					map.addFlashAttribute("error", "This Website already present");
					return "redirect:blacklist-website";
				}
				int web_id=Integer.parseInt(id);
				if(website_name!=null && website_name!="")
				{
				getAdminServices().updateWebsite_name(website_name, web_id);
				String website_name_like="%"+website_name+"%";
				getAdminServices().deleteBlacklistedNews(website_name_like);
				map.addFlashAttribute("message", "Updated Successfully...!!");
				}
				return "redirect:blacklist-website";
			}
			
		}
		
		if(request.getParameter("delete_web_id")!=null)
		{
			String id=request.getParameter("delete_web_id")+"";
			if(!id.equals(""))
			{
				int web_id=Integer.parseInt(id);
				getAdminServices().deleteWebsite_FromBlackList(web_id);
				map.addFlashAttribute("message", "Website removed from black list successfully.!!");
				return "redirect:blacklist-website";
			}
		}
		try{
		//	URL u=new URL(ad.getWebsite_name());
		}catch(Exception e){flag++;}
		if(flag>0)
		{
			map.addFlashAttribute("error", "This website is not valid. Please check it.!!");
			return "redirect:blacklist-website";
		}
		String website_name=ad.getWebsite_name();
		List<AdminModel> blacklist_website=getAdminServices().getBlacklist_WebsiteByName(website_name);
		if(!blacklist_website.isEmpty())
		{
			map.addFlashAttribute("error", "This Website already present");
			return "redirect:blacklist-website";
		}
		if(ad.getWebsite_name()!=null && ad.getWebsite_name()!="")
		{
		getAdminServices().InsertBlcakList_Website(ad.getWebsite_name());
		map.addFlashAttribute("message", "Added successfully..!");
		String website_name_like="%"+website_name+"%";
		getAdminServices().deleteBlacklistedNews(website_name_like);
		}
		return "redirect:blacklist-website";
	}
	
	@RequestMapping(value="blacklist-website",method=RequestMethod.GET)
	public String BlacklistWebsite_Get(@Valid AdminModel ad,ModelMap map)
	{
		
		List<AdminModel> balcklist_website=getAdminServices().getBlacklist_Website();
		map.addAttribute("blacklist_website", balcklist_website);
		return "Admin/BlacklistWebsite";
	}
	@RequestMapping(value="get-state")
	@ResponseBody
	public String getState(HttpServletRequest req)
	{
		int country_id=0;
		String response="";
		country_id=Integer.parseInt(req.getParameter("country_id")+"");
		
		if(country_id==0)
		{
			response="Invalid State";
			return response;
		}
		else
		{
			List<AdminModel> state_list=getAdminServices().getStates_name(country_id);
			response="[";
			String state_info="";
		//	int count=1;
			Vector<String> v_state=new Vector<String>();
			for(AdminModel ad:state_list)
			{
				state_info="{"+"\"name\":"+"\""+ ad.getState_name()+"\","+"\"id\":"+"\""+ ad.getCountry_id()+"\""+"}";
				v_state.add(state_info);
			}
			response=response+state_info+"]";
		}
		return response;
	}
	
	
	@RequestMapping(value="movie-blogs",method=RequestMethod.POST)
	public String  movie_blogsPost(AdminModel ad,HttpSession session,@RequestParam("blog_file") MultipartFile blog_file,RedirectAttributesModelMap map){
		UserSession u=new  UserSession(session);
		ad.setUser_id(u.getUser_id());
		getAdminServices().InsertBlog(ad);
		int blog_id=getAdminServices().lastAddedBlogRecord();
		if(!blog_file.getOriginalFilename().equals(""))
		{
			try {
				if(!blog_file.getInputStream().equals(null))
					{
						Date d=new Date();
						String file_name="Blog"+blog_id+"_"+d.getTime()+"."+blog_file.getContentType().split("/")[1];			
						String filePath="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/BlogImages/"+file_name;
//						String filePath="C:\\CelebrityPhoto\\BlogImages\\"+file_name;

						File uploadedFile=new File(filePath);
						blog_file.transferTo(uploadedFile);
							ad.setBlog_image("/CelebrityPhoto/BlogImages/"+file_name);
							ad.setBlog_id(blog_id);
							getAdminServices().UpdateBlog(ad);
					}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		map.addFlashAttribute("message","Blog added successfully");
		return "redirect:movie-blogs";
	}
	
	@RequestMapping(value="movie-blogs",method=RequestMethod.GET)
	public String  movie_blogs_get(ModelMap map){
		List<AdminModel> blog_list=getAdminServices().getBlogs_list();
		//List<AdminModel> movies_list=getAdminServices().getMovies_list();
		map.addAttribute("blog_list", blog_list);
	//	map.addAttribute("movies_list", movies_list);
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		return "Admin/MovieBlogs";		
	}
	
	@RequestMapping(value="get_blog_data",method=RequestMethod.POST)
	@ResponseBody
	public String  movie_blog_data(HttpServletRequest req,HttpSession session){
		HashMap<String, String> blogs=new HashMap<>();
		String sent_data="";
		if(req.getParameter("blog_id")!=null)
		{
			int blog_id=Integer.parseInt(req.getParameter("blog_id")+"");
			session.setAttribute("blog_id", blog_id);
			List<AdminModel> blog_data=getAdminServices().movie_blog_data(blog_id);
			for(AdminModel a:blog_data)
			{
				blogs.put("blog_title", a.getBlog_title());
		//		blogs.put("movie_id", a.getMovie_id());
				blogs.put("blog_image", a.getBlog_image());
				blogs.put("movie_blogs", a.getMovie_blogs());
			}
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			 sent_data = gson.toJson(blogs);
		}
		return sent_data;
	}
	
	@RequestMapping(value="movie-blogs-update",method=RequestMethod.POST)
	public String  movie_blogsUpdate(AdminModel ad,HttpSession session,@RequestParam("blog_file") MultipartFile blog_file, RedirectAttributesModelMap map){
		int blogId = 0;
		if(session.getAttribute("blog_id")!=null){
			 blogId=(int) session.getAttribute("blog_id");
		}
		ad.setBlog_id(blogId);
		if(!blog_file.getOriginalFilename().equals(""))
		{
			try {
				if(!blog_file.getInputStream().equals(null))
					{
					     String blog_img=getAdminServices().getBlog_image(blogId);
					     if(blog_img!=null){
					//	String file="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/BlogImages/"+blog_img;
						String file="C:\\CelebrityPhoto\\BlogImages\\"+blog_img;
						File f=new File(file);
						f.delete();
					     }
						Date d=new Date();
						String file_name="Blog"+blogId+"_"+d.getTime()+"."+blog_file.getContentType().split("/")[1];			
				    	String filePath="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/BlogImages/"+file_name;
//						String filePath="C:\\CelebrityPhoto\\BlogImages\\"+file_name;
						File uploadedFile=new File(filePath);
						blog_file.transferTo(uploadedFile);
							ad.setBlog_image("/CelebrityPhoto/BlogImages/"+file_name);
					}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getAdminServices().UpdateBlog(ad);
		map.addFlashAttribute("message","blog updated successfully");
		session.removeAttribute("blog_id");
		return "redirect:movie-blogs";
	}
	
	@RequestMapping(value="delete-blog",method=RequestMethod.POST)
	@ResponseBody
	public String  deleteBlog(HttpServletRequest req){
		//System.out.println("blogImage"+req.getParameter("blog_image"));
		String file="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/BlogImages/"+req.getParameter("blog_image");
//		String file="C:\\CelebrityPhoto\\BlogImages\\"+blog_img;
		File f=new File(file);
		f.delete();
		int blog_id=Integer.parseInt(req.getParameter("blog_id")+"");
		getAdminServices().deleteBlog(blog_id);
		return "abc";
	}
	
	@RequestMapping(value="get_celebrity_data",method=RequestMethod.POST)
	@ResponseBody
	public String  celebrity_data(HttpServletRequest req,HttpSession session){
		HashMap<String, String> celebrity=new HashMap<>();
		String sent_data="";
		if(req.getParameter("celebrity_id")!=null)
		{
			int celebrity_id=Integer.parseInt(req.getParameter("celebrity_id")+"");
			session.setAttribute("edit_celebrity_id", celebrity_id);
			List<AdminModel> edit_celebrity=getAdminServices().getCelebrityInfo_byId(celebrity_id);
			for(AdminModel a:edit_celebrity)
			{
				celebrity.put("celebrity_name", a.getCelebrity_name());
				celebrity.put("celebrity_bio",a.getCelebrity_bio());
				celebrity.put("facebook_page", a.getFacebok_page_name());
				celebrity.put("twitter_page", a.getTwitter_handle_name());
				celebrity.put("insta_page", a.getInstagram_page_name());
				celebrity.put("celebrity_profile_photo", a.getProfile_photo_path());

			}
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			 sent_data = gson.toJson(celebrity);
		}
		return sent_data;
	}
	
	@RequestMapping(value="sent-text-mail", method=RequestMethod.POST)
	public String sentTextMailPost()
	{
		
		String[] mailFromAddress={"aishwarya.dhopate@anveshak.com"};
//		String htmlText = readEmailFromHtml("/usr/share/apache-tomcat-7.0.75/webapps/Celebrity/WEB-INF/jsp/Common/dailyNewsletterTemplate.html",input);
		//String[] mailFromAddress={"priti.sawant@anveshak.com"};
		String htmlText = "Hiiiiiiiiiii all";
		EmailController email1=new EmailController();
			email1.emailSending(mailFromAddress, htmlText);
			
		return "redirect:blacklist-website";
		
	}
	
	@RequestMapping(value="add-movie",method=RequestMethod.GET)
	public String addMovieGet(ModelMap map,HttpSession session)
	{
		if(map.get("edit_movie")!=null)
		{
			int celebrity_id_e=Integer.parseInt(session.getAttribute("edit_celebrity_id")+"");

			List<AdminModel> edit_celebrity=getAdminServices().getCelebrityInfo_byId(celebrity_id_e);
		    map.addAttribute("edit_celebrity_info", edit_celebrity);
			map.addAttribute("edit_movie", "yes");
		}
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		return "Admin/AddMovie";
	}
	
	@RequestMapping(value="add-movie",method=RequestMethod.POST)
	public String addMoviePost(HttpServletRequest request,@Valid AdminModel ad,@RequestParam("d_file") MultipartFile d_file,RedirectAttributesModelMap map,HttpSession session) throws IllegalStateException, IOException
	{
		UserSession u=new UserSession(session);
		//int celebrity_id_to_edit=0;
		int celebrity_id=0; //-------------- for insertion purpose
		if(u.getUser_id()==0 || u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		int movie_id=0;
		session.setAttribute("movie_name", ad.getCelebrity_name());
		if(!ad.getCelebrity_name().equals(null) && !ad.getCelebrity_name().equals("")) //&& celebrity_id_to_edit==0)
		{
			getAdminServices().insertCelebrityInfo(ad);
			
			/* celebrity cast*/
			
			if(session.getAttribute("movie_name")!=null)
			{
				List<AdminModel> movie=getAdminServices().getMovieId(session.getAttribute("movie_name").toString());
				for(AdminModel a:movie)
				{
					movie_id=a.getCelebrity_id();
				}
				session.setAttribute("movie_id", movie_id);
			}
			
			List<AdminModel> celebrity_info=getAdminServices().getLast_addedCelebrity();
			for(AdminModel ci:celebrity_info)
			{
				celebrity_id=ci.getCelebrity_id();
				ad.setCelebrity_id(celebrity_id);
			}
			if(celebrity_id==0)
			{
				map.addFlashAttribute("error_message","Somthing wrong.");
				return "redirect:celebrity";
			}
			if(!d_file.getOriginalFilename().equals(""))
			{
				if(!d_file.getInputStream().equals(null))
					{
						Date d=new Date();
						String file_name="C"+celebrity_id+"_"+d.getTime()+"."+d_file.getContentType().split("/")[1];
						
						String filePath="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/ProfilePhoto/"+file_name;
						File uploadedFile=new File(filePath);
							d_file.transferTo(uploadedFile);
							ad.setProfile_photo_name(file_name);
							
							ad.setProfile_photo_path("/CelebrityPhoto/ProfilePhoto/"+file_name);
							getAdminServices().UpdateCelebrity_Profile(ad);
					}
			}
			String category_name=ad.getCategory_name();
			String category_id[]=category_name.split(",");
			for(int i=0;i<category_id.length;i++)
			{
				ad.setCategory_id(Integer.parseInt(category_id[i]));
				getAdminServices().addCelebrity_Category(ad);
			}
			if(request.getParameter("search_id")!=null)
			{
				if(!(request.getParameter("search_id")+"").equals(""))
				{
					int search_id=Integer.parseInt(request.getParameter("search_id")+"");
					getAdminServices().DeleteUserSearchTerms(search_id);
				}
				
			}
			map.addFlashAttribute("message","Movie added succesfully");
			return "redirect:add-cast";
	
		}
		
		return "redirect:add-movie";
	}
	
	@RequestMapping(value="add-cast",method=RequestMethod.GET)
	public String addCastGet(ModelMap map,HttpSession session)
	{
		List<AdminModel> movie_cast=null;
		if(map.get("edit_cast")!=null)
		{
			int celebrity_id_e=Integer.parseInt(session.getAttribute("edit_celebrity_id")+"");
			movie_cast=getAdminServices().getMovie_cast(celebrity_id_e);
			map.addAttribute("edit_movie_cast",movie_cast);
			map.addAttribute("edit_cast","yes");
		}

		List<AdminModel> all_celebrities_info=getAdminServices().getAllCelebrityNames();
		List<AdminModel> all_casts=getAdminServices().getAllCasts();
		List<AdminModel> celebrity_category=getAdminServices().getCelebrity_category();
		map.addAttribute("edit_movie_cast",movie_cast);
		map.addAttribute("celebrity_category", celebrity_category);
		map.addAttribute("all_celebrities_info",all_celebrities_info);
		map.addAttribute("all_casts",all_casts);
		return "Admin/AddCelebrityCast";
	}
	
	@RequestMapping(value="add-cast",method=RequestMethod.POST)
	public String addCastPost(@Valid AdminModel ad, HttpSession sessions)
	{
		String movie_name=sessions.getAttribute("movie_name").toString();
		int movieId=0;
		if(sessions.getAttribute("movie_name")!=null)
		{
			List<AdminModel> movie_id=getAdminServices().getMovieId(movie_name);
			for(AdminModel a:movie_id)
			{
				movieId=a.getCelebrity_id();
			}
		}
		List<AdminModel> movie_id_check=getAdminServices().getMovieCastCount(movieId);
		for(AdminModel movie_id_check1:movie_id_check){
			if(movie_id_check1.getFollower_count()>0){
				int movie_cast_delete=getAdminServices().movieCastDelete(movieId);
			}
		}
		if(ad.getCasted_celebrity()!=null && ad.getCaracter_name()!=null)
		{
				String[] celeb_movie = ad.getCaracter_name().split(",");
				String[] celeb_id = ad.getCasted_celebrity().split(",");
				for (int i = 0; i < celeb_movie.length; i++) {
					getAdminServices().addCelebrityCast(movieId, Integer.parseInt(celeb_id[i]), celeb_movie[i]);
				}
			
		}
		return "redirect:released-movies";
	}
	
	@RequestMapping(value="add-cast-function",method=RequestMethod.POST)
	@ResponseBody
	public String addCastAjax(HttpSession session, HttpServletRequest request, RedirectAttributesModelMap map, @RequestParam("d_file") MultipartFile d_file)throws Exception
	{
		String result="ok";
	
		AdminModel ad=new AdminModel();
        ad.setCelebrity_name(request.getParameter("name"));
        ad.setCelebrity_bio(request.getParameter("bio"));
        ad.setFacebok_page_name(request.getParameter("facebook_page"));
        ad.setTwitter_handle_name(request.getParameter("twitter_page"));
        ad.setInstagram_page_name(request.getParameter("insta_page"));
        ad.setCategory_name(request.getParameter("category"));

        if(!ad.getCelebrity_name().equals(null)) //&& !ad.getCategory_name().equals("")) && celebrity_id_to_edit==0)
		{
			getAdminServices().insertCelebrityInfo(ad);
			
			int celebrity_id=0;
			List<AdminModel> celebrity_info=getAdminServices().getLast_addedCelebrity();
			for(AdminModel ci:celebrity_info)
			{
				celebrity_id=ci.getCelebrity_id();
				ad.setCelebrity_id(celebrity_id);
			}
			
			if(!d_file.getOriginalFilename().equals(""))
			{
				if(!d_file.getInputStream().equals(null))
					{
						Date d=new Date();
						String file_name="C"+celebrity_id+"_"+d.getTime()+"."+d_file.getContentType().split("/")[1];
						
						String filePath="/usr/share/apache-tomcat-7.0.75/webapps/CelebrityPhoto/ProfilePhoto/"+file_name;
						//String filePath="CelebrityPhoto/ProfilePhoto/"+file_name;
						File uploadedFile=new File(filePath);
							d_file.transferTo(uploadedFile);
							ad.setProfile_photo_name(file_name);
							
							ad.setProfile_photo_path("/CelebrityPhoto/ProfilePhoto/"+file_name);
							getAdminServices().UpdateCelebrity_Profile(ad);
					}
			}
			
			/*String category_name=ad.getCategory_name();
			String category_id[]=category_name.split(",");
			for(int i=0;i<category_id.length;i++)
			{
				ad.setCategory_id(Integer.parseInt(category_id[i]));
				getAdminServices().addCelebrity_Category(ad);
			}*/
			int celeb_id=0;
			List<AdminModel> celebrity=getAdminServices().getMovieId(ad.getCelebrity_name());
			for(AdminModel a:celebrity)
			{
				celeb_id=a.getCelebrity_id();
			}
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			List<AdminModel> all_celebrities_info=getAdminServices().getAllCelebrityNames();
			ArrayList<String> celebrity_data=new ArrayList<>();
			for(AdminModel ap:all_celebrities_info)
			{	
				HashMap<String, String> single_post=new HashMap<>();
				single_post.put("celebrity_id",String.valueOf(ap.getCelebrity_id()));
				single_post.put("celebrity_name",ap.getCelebrity_name());
				
				single_post.put("new_celeb_id",String.valueOf(celeb_id));
				String single_data=gson.toJson(single_post);
				celebrity_data.add(single_data+"");
			}
			
			result=celebrity_data+"";
			result="{\"celebrity_info\":"+result+"}";
		}
		return result;
	}
	
	@RequestMapping(value="add-cast-function-photo",method=RequestMethod.POST)
	@ResponseBody
	public String addCastAjax2(HttpSession session, HttpServletRequest request, RedirectAttributesModelMap map)
	{
		String result="ok";

		AdminModel ad=new AdminModel();
        ad.setCelebrity_name(request.getParameter("name"));
        ad.setCelebrity_bio(request.getParameter("bio"));
        ad.setFacebok_page_name(request.getParameter("facebook_page"));
        ad.setTwitter_handle_name(request.getParameter("twitter_page"));
        ad.setInstagram_page_name(request.getParameter("insta_page"));
        ad.setCategory_name(request.getParameter("category"));

        if(!ad.getCelebrity_name().equals(null)) //&& !ad.getCategory_name().equals("")) && celebrity_id_to_edit==0)
		{
			getAdminServices().insertCelebrityInfo(ad);
			
			int celebrity_id=0;
			List<AdminModel> celebrity_info=getAdminServices().getLast_addedCelebrity();
			for(AdminModel ci:celebrity_info)
			{
				celebrity_id=ci.getCelebrity_id();
				ad.setCelebrity_id(celebrity_id);
			}
			
			
			/*String category_name=ad.getCategory_name();
			String category_id[]=category_name.split(",");
			for(int i=0;i<category_id.length;i++)
			{
				ad.setCategory_id(Integer.parseInt(category_id[i]));
				getAdminServices().addCelebrity_Category(ad);
			}*/
			int celeb_id=0;
			List<AdminModel> celebrity=getAdminServices().getMovieId(ad.getCelebrity_name());
			for(AdminModel a:celebrity)
			{
				celeb_id=a.getCelebrity_id();
			}
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			List<AdminModel> all_celebrities_info=getAdminServices().getAllCelebrityNames();
			ArrayList<String> celebrity_data=new ArrayList<>();
			for(AdminModel ap:all_celebrities_info)
			{	
				HashMap<String, String> single_post=new HashMap<>();
				single_post.put("celebrity_id",String.valueOf(ap.getCelebrity_id()));
				single_post.put("celebrity_name",ap.getCelebrity_name());
				
				single_post.put("new_celeb_id",String.valueOf(celeb_id));
				String single_data=gson.toJson(single_post);
				celebrity_data.add(single_data+"");
			}
			
			result=celebrity_data+"";
			result="{\"celebrity_info\":"+result+"}";
		}
		return result;
	}
	
	@RequestMapping(value="manage-role",method=RequestMethod.GET)
	public String manageRoleGet(ModelMap map)
	{
		List<AdminModel> all_roles=getAdminServices().getAllCasts();
		map.addAttribute("all_roles",all_roles);
		if(map.get("message")!=null)
		{
			map.addAttribute("message",map.get("message"));
		}
		return "Admin/ManageCelebrityRole";
	}
	@RequestMapping(value="manage-role",method=RequestMethod.POST)
	public String manageRolePost(HttpServletRequest request, RedirectAttributesModelMap map)
	{
		String role_name=request.getParameter("caracter_name");
		if(!role_name.equals(null) && !role_name.equals(""))
		{
			getAdminServices().addNewRole(role_name);
			map.addFlashAttribute("message","Role added successfully");
		}
		if(request.getParameter("delete_role_id")!=null && request.getParameter("delete_role_id")!="")
		{
			int role_id=Integer.parseInt(request.getParameter("delete_role_id"));
			getAdminServices().deleteRole(role_id);
			getAdminServices().deleteMappedRole(role_id);
			map.addFlashAttribute("message","Role deleted successfully");

		}
		if(request.getParameter("edited_role_id")!=null && request.getParameter("edited_role_id")!="")
		{
			int role_id=Integer.parseInt(request.getParameter("edited_role_id"));
			if(request.getParameter("edit_role_name")!=null && request.getParameter("edit_role_name")!="")
			{
				getAdminServices().updateRoleName(role_id, request.getParameter("edit_role_name"));
				map.addFlashAttribute("message","Role updated successfully");

			}
		}
		return "redirect:manage-role";
	}
	@RequestMapping(value="delete-movie",method=RequestMethod.POST)
	public String deleteMovie(HttpServletRequest request, RedirectAttributesModelMap map)
	{
		if(request.getParameter("delete_celebrity_id")!=null)
		{
			int delete_celebrity_id=Integer.parseInt(request.getParameter("delete_celebrity_id")+"");
			
			getAdminServices().DeleteCelebrity_Likes(delete_celebrity_id);
			getAdminServices().DeleteCelebrity_Comments(delete_celebrity_id);
			getAdminServices().Delete_celebrity_News(delete_celebrity_id);
			getAdminServices().DeleteCelebrity_Comments(delete_celebrity_id);
			getAdminServices().DeleteCelebrity_info(delete_celebrity_id);
			
			map.addFlashAttribute("message","Movie deleted successfully");
		}
		return "redirect:released-movies";
	}
	
	@RequestMapping(value="edit-movie-display",method=RequestMethod.POST)
	public String edit_movie_display(HttpServletRequest request,HttpSession session,RedirectAttributesModelMap map)
	{
		int celebrity_id_to_edit=0;
		if(request.getParameter("celebrity_id")!=null) //--------------------To edit celebrity Information
		{
			celebrity_id_to_edit=Integer.parseInt(request.getParameter("celebrity_id")+"");
			if(celebrity_id_to_edit!=0)
				session.setAttribute("edit_celebrity_id",celebrity_id_to_edit);
			map.addFlashAttribute("edit_movie", "yes");
		}
		return "redirect:add-movie";
	}
	
	@RequestMapping(value="delete-cast-role",method=RequestMethod.POST)
	public String deleteMovieCastRole(HttpServletRequest request,RedirectAttributesModelMap map)
	{
		if(request.getParameter("delete_celebrity_id")!=null)
		{
			int delete_celebrity_id=Integer.parseInt(request.getParameter("delete_celebrity_id")+"");
			getAdminServices().deleteMovieCastRole(delete_celebrity_id);
			//session.setAttribute("movie_name", ad.getCelebrity_name());
			map.addFlashAttribute("edit_cast", "yes");
		}
		return "redirect:add-cast";
	}
	
}