package admin.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import admin.model.AdminModel;

public interface AdminServices {
	public List<AdminModel> getAllCountries();
	public List<AdminModel> getStates_name(@Param("country_id") int country_id);
	public List<AdminModel> getCelebrity_category();
	public void insertCelebrityInfo(AdminModel ad);
	public List<AdminModel> getLast_addedCelebrity();
	public void UpdateCelebrity_Profile(AdminModel ad);
	public void addCelebrity_Category(AdminModel ad);
	public List<AdminModel> showCelebrity_info(int limit, int offset);
	
	public List<AdminModel> getCelebrityCategory_details();
	public List<AdminModel> getCelebrityCategory_details_ById(int celebrity_id);
	
	public List<AdminModel> getCelebrityInfo_byId(int celebrity_id);
	public void updateCelebrity_info(AdminModel ad);
	public void deleteCelebrity_category(@Param("celebrity_id") int celebrity_id);
	public void Activate_celebrity(int celebrity_id);
	public void Inactivate_celebrity(int celebrity_id);
	
	public void DeleteCelebrity_info(@Param("celebrity_id") int celebrity_id);
	public List<AdminModel> getCelebrity_Profile_photo(@Param("celebrity_id") int celebrity_id);
	public void InsertCelebrity_Category(@Param("category_name") String category_name);
	public void updateCateogry_name(@Param("category_name") String category_name,@Param("category_id") int category_id);
	public void deleteCategory_fromMaster(@Param("category_id") int category_id);
	public void addCountry_Name(@Param("country_name")String country_name);
	public List<AdminModel> getAllSttes();
	
	public void DeleteCelebrity_Likes(@Param("celebrity_id") int celebrity_id);
	public void DeleteCelebrity_Comments(@Param("celebrity_id") int celebrity_id);
	public void Delete_celebrity_News(@Param("celebrity_id") int celebrity_id);
	public void DeleteCelebrity_Post(@Param("celebrity_id") int celebrity_id);
	public List<AdminModel> getUserSearch_terms();
	public void DeleteUserSearchTerms(@Param("searched_id") int search_id);
	public void UpdateUserSearchedTerms(@Param("search_key") String search_key,@Param("search_id") int search_id);
	public List<AdminModel> getSearchTerms_ByKey(@Param("search_key") String search_key);
	public void InsertBlcakList_Website(@Param("website_name") String website_name);
	public List<AdminModel> getBlacklist_Website();
	public List<AdminModel> getBlacklist_WebsiteByName(@Param("website_name") String website_name);
	public void deleteWebsite_FromBlackList(@Param("web_id") int web_id);
	public void updateWebsite_name(String website_name,int web_id);
	
	public List<AdminModel> getMovies_list();
	public void InsertBlog(AdminModel ad);
	public List<AdminModel> getBlogs_list();
	public int lastAddedBlogRecord();
	public String getBlog_image(int blogId);
	public void deleteBlog(int blog_id);
	public List<AdminModel> getAllCelebrityNames();
	public List<AdminModel> getAllCasts();
	public int addCelebrityCast(@Param("movie_id") int movie_id,@Param("celebrity_id") int celebrity_id,@Param("caracter") String caracter);
	public List<AdminModel> getMovieId(@Param("celebrity_name") String celebrity_name);
	public List<AdminModel> getCasts();
	public int addNewRole(@Param("caracter_name") String caracter_name);
	public int deleteRole(@Param("id") int id);
	public int updateRoleName(@Param("id") int id,@Param("caracter_name") String caracter_name);
	
	public List<AdminModel> getMovie_cast(@Param("celebrity_id") int celebrity_id);
	public int deleteMappedRole(@Param("caracter") int caracter);
	public List<AdminModel> getAllRequestedCelebrityInfo();
	public List<AdminModel> getRequestedCelebrityInfoWithLimit(@Param("limit") int limit,@Param("offset") int offset);
	public List<AdminModel> getAllRequestedCelebrityCategoriesInfo();
}