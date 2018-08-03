package admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.controller.UserSession;

import admin.model.AdminModel;
import admin.services.AdminServices;

@Controller
public class AdminController {

	@Autowired
	private AdminServices adminServices;

	public AdminServices getAdminServices() {
		return adminServices;
	}

	public void setAdminServices(AdminServices adminServices) {
		this.adminServices = adminServices;
	}
//	ManagePoliticians
	
	@RequestMapping(value="admin-home",method=RequestMethod.GET)
	public String AdminHomeGet(ModelMap map, HttpSession session)
	{
		UserSession u=new UserSession(session);
		if(u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		// pagination start
		
				//int total=0;
				int limit=20;
				int offset=0;
				
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
				System.out.println("offset..."+offset);
		Calendar date= Calendar.getInstance();
		ArrayList<Integer> years = new ArrayList<>();
	//	ArrayList<Integer> next_years = new ArrayList<>();

		  int current_year=date.getWeekYear();
		  for(int i=68;i>0;i--)
		  {
			  years.add(years.size(),current_year-i);
		  }
		  for(int i=0;i<10;i++)
		  {
			  years.add(years.size(),current_year+i);
		  }
		  
		  map.addAttribute("years",years);
          map.addAttribute("current_year",current_year);
          
		List<AdminModel> mapped_categories=getAdminServices().getCelebrity_MappedCAtegories();
		List<AdminModel> party_info=getAdminServices().getPartyInfo();
		List<AdminModel> all_states=getAdminServices().getAllStates();
		List<AdminModel> all_politician_category=getAdminServices().getAllCelebrityCategories();
		List<AdminModel> all_celebrity_info=getAdminServices().getAllCelebrity_info(limit, offset);
		map.addAttribute("celebrity_info", all_celebrity_info);
		List<AdminModel> searched_celebrity=new ArrayList<>();

        int total_celebrity=getAdminServices().getCelebrityCount();
         if(map.get("search_celebrity_key")!=null && map.get("search_celebrity_key")!="")
        {
        	String search_celebrity_key=map.get("search_celebrity_key").toString();
        	search_celebrity_key="%"+search_celebrity_key+"%";
        	searched_celebrity=getAdminServices().getSearchCelebrity_info(search_celebrity_key);
        	map.addAttribute("celebrity_info", searched_celebrity);
        }
        	        	
        for(AdminModel a:all_celebrity_info)
        {
        	System.out.println("celebrity name...."+a.getCelebrity_name());
        }
        map.addAttribute("total_celebrity",total_celebrity);
		map.addAttribute("mapped_categories", mapped_categories);
		map.addAttribute("party_info",party_info);
		map.addAttribute("all_states",all_states);
		map.addAttribute("all_politician_category",all_politician_category);
		
		if(map.get("message")!=null)
		{
			map.addAttribute("message", map.get("message"));
		}
		
		return "Admin/ManagePoliticians";
	}
	
	@RequestMapping(value="admin-home", method=RequestMethod.POST)
	public String adminHomePost(HttpServletRequest request, HttpSession session, RedirectAttributesModelMap map)
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
		if(request.getParameter("search_celebrity_key")!=null)
		{
			map.addFlashAttribute("search_celebrity_key",request.getParameter("search_celebrity_key"));
		}
		
		return "redirect:admin-home";
	}
	
	
	@RequestMapping(value="manage-politician",method=RequestMethod.POST)
	public String ManagePolitician(HttpServletRequest request,HttpSession session,@Valid AdminModel ad,RedirectAttributesModelMap map)
	{
		if(request.getParameter("ActDctCelebrityId")!=null && request.getParameter("flag")!=null) // Activate deactivate celebrity
		{
			if(!(request.getParameter("ActDctCelebrityId")+"").equals("") && !(request.getParameter("flag")+"").equals(""))
			{
					int celebrity_id=Integer.parseInt(request.getParameter("ActDctCelebrityId")+"");
					int flag=Integer.parseInt(request.getParameter("flag")+"");
					if(flag==1)
					{
						getAdminServices().ActivateCelebrity(celebrity_id);
						map.addFlashAttribute("message", "Politician activated successfully");
					}
					else if(flag==0)
					{
						getAdminServices().DeactivateCelebrity(celebrity_id);
						map.addFlashAttribute("message", "Politician deactivated successfully");
					}
			}
			return "redirect:admin-home";
		}
		if(request.getParameter("delete_celebrity_id")!=null)
		{
			String id=request.getParameter("delete_celebrity_id");
			if(id!="")
			{
				int celebrity_id=Integer.parseInt(id);
				getAdminServices().deleteCelebrityNewsComments(celebrity_id);
				getAdminServices().deleteCelebrityNewsLikes(celebrity_id);
				getAdminServices().deleteCelebrityPostsComments(celebrity_id);
				getAdminServices().deleteCelebrityPostsLikes(celebrity_id);
				getAdminServices().deleteCelebrityFollowers(celebrity_id);
				getAdminServices().deletePoliticianInfo(celebrity_id);
				getAdminServices().deleteCelebrityCategory(celebrity_id);
				map.addFlashAttribute("message", "Politician deleted successfully");
				return "redirect:admin-home";
			}
		}
		
		return "redirect:admin-home";
	}
	
	
	@RequestMapping(value="add-politician",method=RequestMethod.POST)
	public String AdminHomePost(HttpServletRequest request,HttpSession session,@Valid AdminModel ad,@RequestParam("d_file") MultipartFile d_file,RedirectAttributesModelMap redirectAttributes) throws IllegalStateException, IOException
	{
		UserSession u=new UserSession(session);
		if(u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		
		if(session.getAttribute("edit_celebrity_id")!=null)
		{
			System.out.println("valuesssssssssssssssssssss  "+ad.getTwitter_handle());
			int celebrity_id=Integer.parseInt(session.getAttribute("edit_celebrity_id")+"");
			ad.setCelebrity_id(celebrity_id);
			if(ad.getCelebrity_name()!="" && ad.getCelebrity_name()!=null && !ad.getCelebrity_name().isEmpty())
			{
				getAdminServices().updateCelebrityInfo(ad);
			}
			
			if(ad.getCategroy_name()!=null)
			{
				getAdminServices().deleteCelebrityCategory(celebrity_id);
			String[] category_name=ad.getCategroy_name().split(",");
			for(int i=0;i< category_name.length ; i++)
			{
				int category_id=Integer.parseInt(category_name[i].toString());
				getAdminServices().addCelebrityCategory(celebrity_id,category_id);
			}
			}
			else{
				getAdminServices().deleteCelebrityCategory(celebrity_id);
			}
			
			if(ad.getTwitter_handle()!=null)
			{
				getAdminServices().deleteCelebrityTwitterAccounts(celebrity_id);
			String[] twitter_name=ad.getTwitter_handle().split(",");
			for(int i=0;i< twitter_name.length ; i++)
			{
				getAdminServices().addCelebrityTwitterAcnt(celebrity_id,twitter_name[i]);
			}
			}
			else{
				getAdminServices().deleteCelebrityTwitterAccounts(celebrity_id);
			}
			
			if(!d_file.getOriginalFilename().equals(""))
			{
				if(!d_file.getInputStream().equals(null))
					{
						List<AdminModel> celeb_profile=getAdminServices().getCelebrityInfo_byId(celebrity_id);
						for(AdminModel a:celeb_profile)
						{
							if(a.getProfile_photo_path()!=null)
							{
								if(!a.getProfile_photo_path().equals(""))
								{
									//String file_path="C:\\PoliticianPhotoes\\"+a.getProfile_photo_path();
									String file_path="/usr/share/apache-tomcat-7.0.75/webapps/PoliticianPhotoes/ProfilePhoto/"+a.getProfile_photo_path();
									File f=new File(file_path);
									f.delete();
								}
							}
						}
					
						Date d=new Date();
						String file_name="C"+celebrity_id+"_"+d.getTime()+"."+d_file.getContentType().split("/")[1];
						//String filePath="C:\\PoliticianPhotoes\\ProfilePhoto\\"+file_name;
						String filePath="/usr/share/apache-tomcat-7.0.75/webapps/PoliticianPhotoes/ProfilePhoto/"+file_name;
						File uploadedFile=new File(filePath);
							d_file.transferTo(uploadedFile);
							ad.setCelebrity_id(celebrity_id);
							ad.setProfile_photo_name(file_name);
							ad.setProfile_photo_path("\\ProfilePhoto\\"+file_name);
							getAdminServices().updateCelebrityProfile_photo(ad.getProfile_photo_name(), ad.getProfile_photo_path(),ad.getCelebrity_id());
					}
			}
			redirectAttributes.addFlashAttribute("message","Information updated successfully");
			session.removeAttribute("edit_celebrity_id");
			return "redirect:admin-home";
		}

		if(ad.getCelebrity_name()!=null && !ad.getCelebrity_name().isEmpty())
		{
			
		int celebrity_id=0;
		ad.setIsActive(1);
	
		getAdminServices().addPoliticianInfo(ad);
		
		List<AdminModel> celeb_info=getAdminServices().getLastAdded_celebrity();
			for(AdminModel a:celeb_info)
			{
				if(a.getCelebrity_name().equals(ad.getCelebrity_name()))
				{
					celebrity_id=a.getCelebrity_id();
				}
			}
			if(ad.getCategroy_name()!=null)
			{
			String[] category_name=ad.getCategroy_name().split(",");
			for(int i=0;i< category_name.length ; i++)
			{
				int category_id=Integer.parseInt(category_name[i]);
				getAdminServices().addCelebrityCategory(celebrity_id,category_id);
			}
			}
			
			if(ad.getTwitter_handle()!=null)
			{
			String[] twitter_name=ad.getTwitter_handle().split(",");
			for(int i=0;i< twitter_name.length ; i++)
			{
				getAdminServices().addCelebrityTwitterAcnt(celebrity_id,twitter_name[i]);
			}
			}
			
		if(!d_file.getOriginalFilename().equals(""))
		{
			if(!d_file.getInputStream().equals(null))
				{
					Date d=new Date();
					String file_name="C"+celebrity_id+"_"+d.getTime()+"."+d_file.getContentType().split("/")[1];
//					String filePath="C:\\PoliticianPhotoes\\ProfilePhoto\\"+file_name;
					String filePath="/usr/share/apache-tomcat-7.0.75/webapps/PoliticianPhotoes/ProfilePhoto/"+file_name;
					File uploadedFile=new File(filePath);
						d_file.transferTo(uploadedFile);
						ad.setCelebrity_id(celebrity_id);
						ad.setProfile_photo_name(file_name);
						ad.setProfile_photo_path("/ProfilePhoto/"+file_name);
						getAdminServices().updateCelebrityProfile_photo(ad.getProfile_photo_name(), ad.getProfile_photo_path(),ad.getCelebrity_id());
				}
		}
		/*if(session.getAttribute("delete_search_key_id")!=null)
		{*/
			//int id=Integer.parseInt(session.getAttribute("delete_search_key_id").toString());
			List<AdminModel> getAllSearchKeys=getAdminServices().getAllSearchKeys();
			for(AdminModel a:getAllSearchKeys)
			{
				if(a.getSearch_key().equals(ad.getCelebrity_name()))
				{
					getAdminServices().deleteSearchKey(a.getSearch_key_id());
					redirectAttributes.addFlashAttribute("message","Politician added successfully");
					return "redirect:search-keys";
				}
			}
			//session.removeAttribute("delete_search_key_id");
			//return "redirect:search-keys";
		//}
		redirectAttributes.addFlashAttribute("message","Politician added successfully");
		}
		
		
		return "redirect:admin-home";
	}
	
	@RequestMapping(value="set-attributes")
	@ResponseBody
	public String SetAttributes(HttpServletRequest request,HttpSession session)
	{
		String result="ok";
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		HashMap<String, List<String>> details=new HashMap<>();
		HashMap<String, String> category_data=new HashMap<>();
		HashMap<String, String> celebrity_data=new HashMap<>();
		HashMap<String, String> twitter_data=new HashMap<>();
		List<String> cat_list=new ArrayList<>();
		List<String> cel_list=new ArrayList<>();
		List<String> twitter_list=new ArrayList<>();
		if(request.getParameter("celebId_edit")!=null)
		{
			int celebrity_id=Integer.parseInt(request.getParameter("celebId_edit")+"");
			session.setAttribute("edit_celebrity_id", request.getParameter("celebId_edit"));
			List<AdminModel> celebrity_info=getAdminServices().getCelebrityInfo_byId(celebrity_id);
			List<AdminModel> celebrity_category=getAdminServices().getCelebrityCategory(celebrity_id);
			List<AdminModel> celebrity_twitter_accounts=getAdminServices().getCelebrityTwitterAccount(celebrity_id);

			for(AdminModel a:celebrity_info)
			{
				celebrity_data.put("calebrity_id", String.valueOf(a.getCelebrity_id()));
				//celebrity_data.put("calebrity_name", ci.getCelebrity_name());
				celebrity_data.put("celebrity_name",a.getCelebrity_name());
				celebrity_data.put("party_name",a.getParty_name());
				celebrity_data.put("facebook_page",a.getFacebook_page());
			//	celebrity_data.put("twitter_data",a.getTwitter_handle());
				celebrity_data.put("instagram_page",a.getInstagram_page());
				celebrity_data.put("party_id", String.valueOf(a.getParty_id()));
				celebrity_data.put("bio",a.getBio());
				celebrity_data.put("state",a.getState_name());
				celebrity_data.put("state_id", String.valueOf(a.getState_id()));
				celebrity_data.put("commity",a.getCommity());
				
				if(a.getProfile_photo_path()!=null)
				{
					String profile_photo=a.getProfile_photo_path();
					celebrity_data.put("profile_photo",profile_photo);
				}
				
				celebrity_data.put("date_of_birth", a.getDate_of_birth());
				celebrity_data.put("place_of_birth", a.getPlace_of_birth());
				celebrity_data.put("position", a.getPolitician_position());
				celebrity_data.put("net_worth", a.getNet_worth());
				celebrity_data.put("tenure_from", a.getTenure_from());
				celebrity_data.put("tenure_to", a.getTenure_to());
				celebrity_data.put("term_ends", a.getTerm_ends());
				celebrity_data.put("year", a.getYear_in_position());
				celebrity_data.put("predecessor", a.getPredecessor());
				celebrity_data.put("career", a.getCareer());
				
				String str=gson.toJson(celebrity_data);
				cel_list.add(str);
			}
			for(AdminModel cat:celebrity_category)
			{
				category_data.put("category_id", cat.getCategory_id()+"");
				category_data.put("category_name", cat.getCategroy_name());
				
				String str=gson.toJson(category_data);
				cat_list.add(str);
			}
			for(AdminModel twit:celebrity_twitter_accounts)
			{
				twitter_data.put("twitter_handle", twit.getTwitter_handle());
				
				String str=gson.toJson(twitter_data);
				twitter_list.add(str);
			}
		}
		details.put("\"basic_info\"", cel_list);
		details.put("\"category\"", cat_list);
		details.put("\"twitter\"", twitter_list);

		result=details+"";
		result=result.replace("=", ":");
		return result;
		
			/*String result="ok";
			HashMap<String, String> celebrity_data=new HashMap<>();
			String data="[";
			HashMap<String, String> category_data=new HashMap<>();
			int cnt=0;
			if(request.getParameter("celebId_edit")!=null)
			{
					int celebrity_id=Integer.parseInt(request.getParameter("celebId_edit")+"");
					session.setAttribute("edit_celebrity_id", request.getParameter("celebId_edit"));
					List<AdminModel> celebrity_info=getAdminServices().getCelebrityInfo_byId(celebrity_id);
					List<AdminModel> getCategoryData=getAdminServices().getCelebrityCategory(celebrity_id);
					for(AdminModel ap:getCategoryData)
					{
						cnt++;
						category_data.put("\"category_id\"", "\""+ap.getCategory_id()+"\"");
						category_data.put("\"category_name\"", "\""+ap.getCategroy_name()+"\"");
						String str=category_data+"";
						str=str.replace("=", ":");
						if(cnt<getCategoryData.size())
						{
							str=str+",";
						}
						data=data+str;
					}
					
					data=data+"]";
					for(AdminModel a:celebrity_info)
					{
								celebrity_data.put("\"celebrity_name\"","\""+ a.getCelebrity_name()+"\"");
								celebrity_data.put("\"party_name\"","\""+ a.getParty_name()+"\"");
								celebrity_data.put("\"facebook_page\"", "\""+a.getFacebook_page()+"\"");
								celebrity_data.put("\"twitter_data\"", "\""+a.getTwitter_handle()+"\"");
								celebrity_data.put("\"instagram_page\"", "\""+a.getInstagram_page()+"\"");
								celebrity_data.put("\"party_id\"", "\""+a.getParty_id()+"\"");
								celebrity_data.put("\"bio\"", "\""+a.getBio()+"\"");
								celebrity_data.put("\"state\"", "\""+a.getState_name()+"\"");
								celebrity_data.put("\"state_id\"", "\""+a.getState_id()+"\"");
								celebrity_data.put("\"category_details\"", data);
								
								celebrity_data.put("\"date_of_birth\"", "\""+a.getDate_of_birth()+"\"");
								celebrity_data.put("\"place_of_birth\"", "\""+a.getPlace_of_birth()+"\"");
								celebrity_data.put("\"position\"", "\""+a.getPolitician_position()+"\"");
								celebrity_data.put("\"net_worth\"", "\""+a.getNet_worth()+"\"");
								celebrity_data.put("\"tenure_from\"", "\""+a.getTenure_from()+"\"");
								celebrity_data.put("\"tenure_to\"", "\""+a.getTenure_to()+"\"");
								celebrity_data.put("\"term_ends\"", "\""+a.getTerm_ends()+"\"");
								celebrity_data.put("\"year\"", "\""+a.getYear_in_position()+"\"");
								celebrity_data.put("\"predecessor\"", "\""+a.getPredecessor()+"\"");
								celebrity_data.put("\"career\"", "\""+a.getCareer()+"\"");
								
								if(a.getProfile_photo_path()!=null)
								{
									String profile_photo=a.getProfile_photo_path();
									profile_photo=profile_photo.replace("\\", "\\\\");
									celebrity_data.put("\"profile_photo\"", "\""+profile_photo+"\"");	
								}
					}
			}
			result=celebrity_data+"";
			result=result.replace("=", ":");
			return result;*/
	}
	@RequestMapping(value="show-celebrity")
	@ResponseBody
	public String showCelebrity(HttpServletRequest request,HttpSession session)
	{
		String result="ok";
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		HashMap<String, List<String>> details=new HashMap<>();
		HashMap<String, String> category_data=new HashMap<>();
		HashMap<String, String> celebrity_data=new HashMap<>();
		List<String> cat_list=new ArrayList<>();
		List<String> cel_list=new ArrayList<>();
		if(request.getParameter("celebId_show")!=null)
		{
			int celebrity_id=Integer.parseInt(request.getParameter("celebId_show")+"");
			List<AdminModel> celebrity_info=getAdminServices().getCelebrityInfo_byId(celebrity_id);
			List<AdminModel> celebrity_category=getAdminServices().getCelebrityCategory(celebrity_id);
			for(AdminModel a:celebrity_info)
			{
				/*if(a.getCareer()!=null && a.getCareer()!="")
				{
					a.setCareer(a.getCareer().replaceAll("\n", "10"));
				}
				if(a.getBio()!=null && a.getBio()!="")
				{
					a.setBio(a.getBio().replaceAll("\n", "10"));
				}
				if(a.getCommity()!=null && a.getCommity()!="")
				{
					a.setCommity(a.getCommity().replaceAll("\n", "10"));
				}*/
				
				celebrity_data.put("calebrity_id", String.valueOf(a.getCelebrity_id()));
				//celebrity_data.put("calebrity_name", ci.getCelebrity_name());
				celebrity_data.put("celebrity_name",a.getCelebrity_name());
				celebrity_data.put("party_name",a.getParty_name());
				celebrity_data.put("facebook_page",a.getFacebook_page());
				celebrity_data.put("twitter_data",a.getTwitter_handle());
				celebrity_data.put("instagram_page",a.getInstagram_page());
				celebrity_data.put("party_id", String.valueOf(a.getParty_id()));
				celebrity_data.put("bio",a.getBio());
				celebrity_data.put("state",a.getState_name());
				if(a.getProfile_photo_path()!=null)
				{
					String profile_photo=a.getProfile_photo_path();
					celebrity_data.put("profile_photo",profile_photo);
				}
				
				celebrity_data.put("date_of_birth", a.getDate_of_birth());
				celebrity_data.put("place_of_birth", a.getPlace_of_birth());
				celebrity_data.put("position", a.getPolitician_position());
				celebrity_data.put("net_worth", a.getNet_worth());
				celebrity_data.put("tenure_from", a.getTenure_from());
				celebrity_data.put("tenure_to", a.getTenure_to());
				celebrity_data.put("term_ends", a.getTerm_ends());
				celebrity_data.put("year", a.getYear_in_position());
				celebrity_data.put("predecessor", a.getPredecessor());
				celebrity_data.put("career", a.getCareer());
				celebrity_data.put("commity",a.getCommity());
				
				String str=gson.toJson(celebrity_data);
				cel_list.add(str);
			}
			for(AdminModel cat:celebrity_category)
			{
				category_data.put("category_id", cat.getCategory_id()+"");
				category_data.put("category_name", cat.getCategroy_name());
				
				String str=gson.toJson(category_data);
				cat_list.add(str);
			}
		}
		details.put("\"basic_info\"", cel_list);
		details.put("\"category\"", cat_list);
		
		result=details+"";
		result=result.replace("=", ":");
		return result;
	}
	
	/*@RequestMapping(value="show-celebrity")
	@ResponseBody
	public String showCelebrity(HttpServletRequest request,HttpSession session)
	{
			String result="ok";
			String data="[";
			HashMap<String, String> category_data=new HashMap<>();
			HashMap<String, String> celebrity_data=new HashMap<>();
			int cnt=0;
			Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			if(request.getParameter("celebId_show")!=null)
			{
				int celebrity_id=Integer.parseInt(request.getParameter("celebId_show")+"");
				List<AdminModel> celebrity_info=getAdminServices().getCelebrityInfo_byId(celebrity_id);
				List<AdminModel> getCategoryData=getAdminServices().getCelebrityCategory(celebrity_id);
				for(AdminModel ap:getCategoryData)
				{
					cnt++;
					category_data.put("\"category_id\"", "\""+ap.getCategory_id()+"\"");
					category_data.put("\"category_name\"", "\""+ap.getCategroy_name()+"\"");
					String str=category_data+"";
					str=str.replace("=", ":");
					if(cnt<getCategoryData.size())
					{
						str=str+",";
					}
					data=data+str;
					
				}
				
				data=data+"]";
				
				for(AdminModel a:celebrity_info)
				{
							celebrity_data.put("\"celebrity_name\"", "\""+a.getCelebrity_name()+"\"");
							celebrity_data.put("\"party_name\"", "\""+a.getParty_name()+"\"");
							celebrity_data.put("\"facebook_page\"", "\""+a.getFacebook_page()+"\"");
							celebrity_data.put("\"twitter_data\"", "\""+a.getTwitter_handle()+"\"");
							celebrity_data.put("\"instagram_page\"", "\""+a.getInstagram_page()+"\"");
							celebrity_data.put("\"party_id\"", "\""+a.getParty_id()+"\"");
							celebrity_data.put("\"bio\"", "\""+a.getBio()+"\"");
							celebrity_data.put("\"state\"", "\""+a.getState_name()+"\"");
							
							celebrity_data.put("\"date_of_birth\"", "\""+a.getDate_of_birth()+"\"");
							celebrity_data.put("\"place_of_birth\"", "\""+a.getPlace_of_birth()+"\"");
							celebrity_data.put("\"position\"", "\""+a.getPolitician_position()+"\"");
							celebrity_data.put("\"net_worth\"", "\""+a.getNet_worth()+"\"");
							celebrity_data.put("\"tenure_from\"", "\""+a.getTenure_from()+"\"");
							celebrity_data.put("\"tenure_to\"", "\""+a.getTenure_to()+"\"");
							celebrity_data.put("\"term_ends\"", "\""+a.getTerm_ends()+"\"");
							celebrity_data.put("\"year\"", "\""+a.getYear_in_position()+"\"");
							celebrity_data.put("\"predecessor\"", "\""+a.getPredecessor()+"\"");
							celebrity_data.put("\"career\"", "\""+a.getCareer()+"\"");
							
							celebrity_data.put("\"category_details\"", data);
							if(a.getProfile_photo_path()!=null)
							{
								String profile_photo=a.getProfile_photo_path();
								profile_photo=profile_photo.replace("\\", "\\\\");
								celebrity_data.put("\"profile_photo\"", "\""+profile_photo+"\"");
							}
					
				}
				
			}

			result=celebrity_data+"";
			result=result.replace("=", ":");
			
			return result;
	}*/
	
	@RequestMapping(value="blacklist-website",method=RequestMethod.POST)
	public String blacklistWebsite_Post(@Valid AdminModel ad,RedirectAttributesModelMap map,HttpServletRequest request)
	{
		int flag=0;
		if(request.getParameter("edit_web_id")!=null)
		{
			String id=""+request.getParameter("edit_web_id");
			if(!id.equals(""))
			{
				String website_name=request.getParameter("website_name")+"";
				try{
					//URL u=new URL(website_name);
				}catch(Exception e){flag++;}
				if(flag>0)
				{
					map.addFlashAttribute("error", "This website is not valid. Please chekc it.!!");
					return "redirect:blacklist-website";
				}
				List<AdminModel> blacklist_website=getAdminServices().getBlacklist_WebsiteByName(website_name);
				if(!blacklist_website.isEmpty())
				{
					map.addFlashAttribute("error", "This Website already present");
					return "redirect:blacklist-website";
				}
				int web_id=Integer.parseInt(id);
				getAdminServices().updateWebsite_name(website_name, web_id);
				map.addFlashAttribute("message", "Url updated successfully");
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
				map.addFlashAttribute("message", "Website removed from black list successfully");
				return "redirect:blacklist-website";
			}
		}
		try{
			//URL u=new URL(ad.getWebsite_name());
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
		getAdminServices().InsertBlcakList_Website(ad.getWebsite_name());
		map.addFlashAttribute("message", "Url added successfully");
		return "redirect:blacklist-website";
	}
	
	@RequestMapping(value="blacklist-website",method=RequestMethod.GET)
	public String blacklistWebsite_Get(@Valid AdminModel ad,ModelMap map, HttpSession session)
	{
		UserSession u=new UserSession(session);
		if(u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		List<AdminModel> balcklist_website=getAdminServices().getBlacklist_Website();
		map.addAttribute("blacklist_website", balcklist_website);
		
		if(map.get("message")!=null)
        {
        	map.addAttribute("message",map.get("message"));
        }
        if(map.get("error")!=null)
        {
        	map.addAttribute("error",map.get("error"));
        }
        
		return "Admin/BlacklistWebsite";
	}
	@RequestMapping(value="manage-parties",method=RequestMethod.GET)
	public String managePartyGet(ModelMap map,HttpSession session)
	{
		UserSession u=new UserSession(session);
		if(u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		List<AdminModel> getAllPartyInfo=getAdminServices().getAllParties();
		map.addAttribute("getAllPartyInfo",getAllPartyInfo);
        if(map.get("message")!=null)
        {
        	map.addAttribute("message",map.get("message"));
        }
        if(map.get("error")!=null)
        {
        	map.addAttribute("error",map.get("error"));
        }
        
		return "Admin/ManageParties";
	}
	
	@RequestMapping(value="manage-parties",method=RequestMethod.POST)
	public String managePartyPost(@Valid AdminModel ad, RedirectAttributesModelMap redirectAttributes,HttpServletRequest request)
	{
		if(request.getParameter("edit_party_id")!=null)
		{
			String party_id=request.getParameter("edit_party_id");
			if(party_id!="")
			{
				String party_name=request.getParameter("party_name");
				List<AdminModel> getPartyNames=getAdminServices().getExistingPartyName(party_name);
				int id=Integer.parseInt(party_id);
				if(!getPartyNames.isEmpty())
				{
					for(AdminModel a:getPartyNames)
					{
						if(a.getParty_name().equals(party_name))
						{
							redirectAttributes.addFlashAttribute("error","This party is already present");
							return "redirect:manage-parties";
						}
						else{
							getAdminServices().updatePartyName(id, party_name);
							redirectAttributes.addFlashAttribute("message","Party name updated successfully");
						}
					}
					
				}
				
				getAdminServices().updatePartyName(id, party_name);
				redirectAttributes.addFlashAttribute("message","Party name updated successfully");
				return "redirect:manage-parties";
			}
		}
		if(request.getParameter("delete_party_id")!=null)
		{
			String party_id=request.getParameter("delete_party_id");
			if(party_id!="")
			{
				int id=Integer.parseInt(party_id);
				getAdminServices().deleteParty(id);
				redirectAttributes.addFlashAttribute("message","Party deleted successfully");
				return "redirect:manage-parties";
			}
		}
		
		if(ad.getParty_name()!=null)
		{
			String party_name=ad.getParty_name();
			List<AdminModel> getPartyNames=getAdminServices().getExistingPartyName(party_name);
			if(!getPartyNames.isEmpty())
			{
				redirectAttributes.addFlashAttribute("error","This party is already present");
				return "redirect:manage-parties";
			}
			getAdminServices().addNewParty(party_name);
			redirectAttributes.addFlashAttribute("message","Party added successfully");
		}
		
		return "redirect:manage-parties";
	}
	
	@RequestMapping(value="search-keys",method=RequestMethod.GET)
	public String addCelebrityGet(ModelMap map, HttpSession session)
	{
		UserSession u = new UserSession(session);
		if(u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		Calendar date= Calendar.getInstance();
		ArrayList<Integer> years = new ArrayList<>();

		int current_year=date.getWeekYear();
		  for(int i=68;i>0;i--)
		  {
			  years.add(years.size(),current_year-i);
		  }
		  for(int i=0;i<10;i++)
		  {
			  years.add(years.size(),current_year+i);
		  }
		  map.addAttribute("current_year",current_year);
		  map.addAttribute("years",years);
		  
		List<AdminModel> getAllSearchKeys=getAdminServices().getAllSearchKeys();
		List<AdminModel> party_info=getAdminServices().getPartyInfo();
		List<AdminModel> all_politician_category=getAdminServices().getAllCelebrityCategories();
        List<AdminModel> all_states=getAdminServices().getAllStates();
        
		map.addAttribute("all_states",all_states);
		map.addAttribute("all_politician_category",all_politician_category);
		map.addAttribute("party_info",party_info);
		map.addAttribute("getAllSearchKeys",getAllSearchKeys);
		
		if(map.get("message")!=null)
        {
        	map.addAttribute("message",map.get("message"));
        }
        if(map.get("error")!=null)
        {
        	map.addAttribute("error",map.get("error"));
        }
        
		return "Admin/SearchKeys";
	}
	
	@RequestMapping(value="search-keys",method=RequestMethod.POST)
	public String addCelebrityPost(HttpServletRequest request,RedirectAttributesModelMap redirectAttributes)
	{
		if(request.getParameter("edit_search_key_id")!=null)
		{
			
			int id=Integer.parseInt(request.getParameter("edit_search_key_id"));
			
			getAdminServices().updateSearchKey(id,request.getParameter("search_key_name"));
			redirectAttributes.addFlashAttribute("message","Search key updated successfully");
		}
		if(request.getParameter("delete_search_key_id")!=null)
		{
			int id=Integer.parseInt(request.getParameter("delete_search_key_id"));
	
            getAdminServices().deleteSearchKey(id);
			redirectAttributes.addFlashAttribute("message","Search key deleted successfully");
		}
		return "redirect:search-keys";
	}
	
	@RequestMapping(value="add-blogs",method=RequestMethod.GET)
	public String addBlogsGet(ModelMap map, HttpSession session)
	{
		UserSession u=new UserSession(session);
		if(u.getRole_id()!=1)
		{
			return "redirect:home";
		}
		List<AdminModel> get_blogs=getAdminServices().getBlogs();
		map.addAttribute("get_blogs",get_blogs);
		
		if(map.get("message")!=null)
		{
			map.addAttribute("message", map.get("message"));
		}
		if(map.get("error")!=null)
		{
		    map.addAttribute("error", map.get("error"));	
		}
		
		return "Admin/Blogs";
	}
	
	@RequestMapping(value="add-blogs",method=RequestMethod.POST)
	public String addBlogsPost(HttpServletRequest request,HttpSession session,@Valid AdminModel ad,@RequestParam("d_file") MultipartFile d_file, RedirectAttributesModelMap redirectAttributes)throws Exception
	{
	
		int blog_id=0;
		getAdminServices().addBlogs(ad);
		List<AdminModel> blog_info=getAdminServices().getLastBlog();
			for(AdminModel a:blog_info)
			{
				if(a.getBlog_title().equals(ad.getBlog_title()))
				{
					blog_id=a.getBlog_id();
				}
			}
		if(!d_file.getOriginalFilename().equals(""))
		{
			if(!d_file.getInputStream().equals(null))
				{
					Date d=new Date();
					String file_name="B"+blog_id+"_"+d.getTime()+"."+d_file.getContentType().split("/")[1];
					String filePath="/usr/share/apache-tomcat-7.0.75/webapps/PoliticianPhotoes/BlogPhotos/"+file_name;
					File uploadedFile=new File(filePath);
						d_file.transferTo(uploadedFile);
						ad.setBlog_id(blog_id);
						ad.setImage_path("/BlogPhotos/"+file_name);
						getAdminServices().updateBlogImage(ad.getImage_path(), ad.getBlog_id());
				}
		}
		redirectAttributes.addFlashAttribute("message","Blog added successfully");
		
		return "redirect:add-blogs";
	}
	
	@RequestMapping(value="manage-blogs", method=RequestMethod.POST)
	public String deleteBlog(@Valid AdminModel ad, HttpServletRequest request, RedirectAttributesModelMap redirectAttributes, HttpSession session)throws Exception
	{
		int blog_id=0;
		if(request.getParameter("delete_blog_id")!=null)
		{
			blog_id=Integer.parseInt(request.getParameter("delete_blog_id"));
			List<AdminModel> blogs=getAdminServices().getBlogsById(blog_id);
			for(AdminModel a:blogs)
			{
				if(a.getImage_path()!=null)
				{
					if(!a.getImage_path().equals(""))
					{
						String file_path="/usr/share/apache-tomcat-7.0.75/webapps/PoliticianPhotoes/BlogPhotos/"+a.getImage_path();
						File f=new File(file_path);
					f.delete();
					}
				}
			}
			getAdminServices().deleteBlogs(blog_id);
			redirectAttributes.addFlashAttribute("error","Blog deleted successfully");
		}
		
		return "redirect:add-blogs";
	}
	
	@RequestMapping(value="edit-blogs", method=RequestMethod.POST)
	@ResponseBody
	public String editBlogs(HttpServletRequest request, HttpSession session)
	{
		String result="ok";
		if(request.getParameter("edit_blog_id")!=null)
		{
		int blog_id=Integer.parseInt(request.getParameter("edit_blog_id"));
		session.setAttribute("edit_blog_id", request.getParameter("edit_blog_id"));
		HashMap<String, String> blog_data=new HashMap<>();
		List<AdminModel> get_blogs=getAdminServices().getBlogsById(blog_id);
		for(AdminModel a:get_blogs)
		{
			blog_data.put("blog_title", a.getBlog_title());
			blog_data.put("description", a.getDescription());
			blog_data.put("blog_image", a.getImage_path());
		}
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		String sent_data = gson.toJson(blog_data);
		result=sent_data;
		}
		return result;
	}
	
	@RequestMapping(value="edit-politician-blogs", method=RequestMethod.POST)
	public String editPoliticianBlogs(HttpServletRequest request,HttpSession session,@Valid AdminModel ad,@RequestParam("d_file") MultipartFile d_file, RedirectAttributesModelMap redirectAttributes)throws Exception
	{
		if(session.getAttribute("edit_blog_id")!=null)
		{
			
			int blog_id=Integer.parseInt(session.getAttribute("edit_blog_id").toString());
			
			getAdminServices().updateBlogs(blog_id, ad.getBlog_title(), ad.getDescription());
			if(!d_file.getOriginalFilename().equals(""))
			{
				if(!d_file.getInputStream().equals(null))
					{
						List<AdminModel> blogs=getAdminServices().getBlogsById(blog_id);
						for(AdminModel a:blogs)
						{
							if(a.getImage_path()!=null)
							{
								if(!a.getImage_path().equals(""))
								{
									String file_path="/usr/share/apache-tomcat-7.0.75/webapps/PoliticianPhotoes/BlogPhotos/"+a.getImage_path();
									File f=new File(file_path);
									f.delete();
								}
							}
						}
					
						Date d=new Date();
						String file_name="B"+blog_id+"_"+d.getTime()+"."+d_file.getContentType().split("/")[1];
						String filePath="/usr/share/apache-tomcat-7.0.75/webapps/PoliticianPhotoes/BlogPhotos/"+file_name;
						
						File uploadedFile=new File(filePath);
							d_file.transferTo(uploadedFile);
							ad.setBlog_id(blog_id);
							ad.setImage_path("/BlogPhotos/"+file_name);
							getAdminServices().updateBlogImage(ad.getImage_path(), ad.getBlog_id());
					}
			}
			redirectAttributes.addFlashAttribute("message","Blog updated successfully");
			session.removeAttribute("edit_blog_id");
		}
		return "redirect:add-blogs";
	}
	
	@RequestMapping(value="delete-added-search-key", method=RequestMethod.POST)
	@ResponseBody
	public String deleteAddedSearchKey(HttpSession session, HttpServletRequest request)
	{
		String result="ok";
		if(request.getParameter("delete_search_key_id")!=null)
		{
			session.setAttribute("delete_search_key_id", request.getParameter("delete_search_key_id"));
		}
		return result;
	}
	
	@RequestMapping(value="twitter-accounts-change",method=RequestMethod.GET)
	public String twitterGet()
	{
		List<AdminModel> twit=getAdminServices().getAllCelebrity_info(1000, 0);
		for(AdminModel twit1:twit){
			if(!twit1.getTwitter_handle().equals("")){
				System.out.println("celeb_id"+twit1.getCelebrity_id()+" name=="+twit1.getTwitter_handle());
			List<AdminModel> twitcheck=getAdminServices().gettwitter_account(twit1.getCelebrity_id(),twit1.getTwitter_handle());
			if(twitcheck.size()==0){
		    getAdminServices().addCelebrityTwitterAcnt(twit1.getCelebrity_id(),twit1.getTwitter_handle());
			}	
			}
		}
		return null;
	}
	
	@RequestMapping(value="user-accounts",method=RequestMethod.GET)
	public String getUserAccounts(ModelMap map, HttpSession session)
	{
		UserSession u=new UserSession(session);
		if(u.getRole_id()!=1)
		{
			return "redirect:home";
		}
				int limit=20;
				int offset=0;
				
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
				System.out.println("offset..."+offset);
				List<AdminModel> userAccounts=getAdminServices().getUserAccounts(limit,offset);
				List<AdminModel> userAccountsCount=getAdminServices().getUserAccountsCount();
				int totalCount=0;
				for(AdminModel userAccountsCount1:userAccountsCount){
					totalCount=userAccountsCount1.getFollower_count();
				}
        map.addAttribute("user_accounts",userAccounts);
        map.addAttribute("total_celebrity",totalCount);
        
		return "Admin/userAccounts";
	}
	
	@RequestMapping(value="user-accounts", method=RequestMethod.POST)
	public String getUserAccountsPost(HttpServletRequest request, HttpSession session, RedirectAttributesModelMap map)
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
		
		return "redirect:user-accounts";
	}
	
	
	/*@RequestMapping(value="twitter-accounts-change",method=RequestMethod.POST)
	public String twitterPost()
	{
		return "twitter-accounts-change";
	}*/
}
