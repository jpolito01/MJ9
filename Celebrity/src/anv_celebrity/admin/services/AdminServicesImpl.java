package admin.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import admin.mapper.AdminMapper;
import admin.model.AdminModel;

public class AdminServicesImpl implements AdminServices {

	private AdminMapper adminMapper;

	public AdminMapper getAdminMapper() {
		return adminMapper;
	}

	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	public List<AdminModel> getAllCountries()
	{
		return getAdminMapper().getAllCountries();
	}
	@Override
	public List<AdminModel> getStates_name(int country_id)
	{
		return getAdminMapper().getStates_name(country_id);
	}
	@Override
	public List<AdminModel> getCelebrity_category()
	{
		return getAdminMapper().getCelebrity_category();
	}
	@Override
	public void insertCelebrityInfo(AdminModel ad)
	{
		getAdminMapper().insertCelebrityInfo(ad);
	}
	@Override
	public List<AdminModel> getLast_addedCelebrity()
	{
		return getAdminMapper().getLast_addedCelebrity();
	}

	@Override
	public void UpdateCelebrity_Profile(AdminModel ad) {
		getAdminMapper().UpdateCelebrity_Profile(ad);
	}
	@Override
	public void addCelebrity_Category(AdminModel ad)
	{
		getAdminMapper().addCelebrity_Category(ad);
	}
	@Override
	public List<AdminModel> showCelebrity_info(int limit, int offset)
	{
		return getAdminMapper().showCelebrity_info(limit, offset);
	}
	@Override
	public List<AdminModel> getCelebrityCategory_details()
	{
		return getAdminMapper().getCelebrityCategory_details();
	}
	@Override
	public List<AdminModel> getCelebrityInfo_byId(int celebrity_id)
	{
		return getAdminMapper().getCelebrityInfo_byId(celebrity_id);		
	}
	@Override
	public List<AdminModel> getCelebrityCategory_details_ById(int celebrity_id)
	{
		return getAdminMapper().getCelebrityCategory_details_ById(celebrity_id);
	}
	@Override
	public void updateCelebrity_info(AdminModel ad)
	{
		 getAdminMapper().updateCelebrity_info(ad);
	}
	@Override
	public void deleteCelebrity_category(int celebrity_id)
	{
		getAdminMapper().deleteCelebrity_category(celebrity_id);
	}
	@Override
	public void Activate_celebrity(int celebrity_id)
	{
		getAdminMapper().ActivateDeactivate_celebrity(1, celebrity_id);
	}
	@Override
	public void Inactivate_celebrity(int celebrity_id)
	{
		getAdminMapper().ActivateDeactivate_celebrity(0, celebrity_id);
	}
	@Override
	public void DeleteCelebrity_info(int celebrity_id)
	{
		getAdminMapper().DeleteCelebrity_info(celebrity_id);
	}
	@Override
	public List<AdminModel> getCelebrity_Profile_photo(int celebrity_id)
	{
		return getAdminMapper().getCelebrity_Profile_photo(celebrity_id);
	}
	@Override
	public void InsertCelebrity_Category(String category_name)
	{
		getAdminMapper().InsertCelebrity_Category(category_name);
	}
	@Override
	public void updateCateogry_name(String category_name,int category_id)
	{
		getAdminMapper().updateCateogry_name(category_name, category_id);
	}
	@Override
	public void deleteCategory_fromMaster(int category_id)
	{
		getAdminMapper().deleteCategory_fromMaster(category_id);
	}
	@Override
	public void addCountry_Name(String country_name)
	{
		getAdminMapper().addCountry_Name(country_name);
		
	}
	@Override
	public List<AdminModel> getAllSttes()
	{
		return getAdminMapper().getAllSttes();
	}
	@Override
	public void DeleteCelebrity_Likes(@Param("celebrity_id") int celebrity_id)
	{
		getAdminMapper().DeleteCelebrity_Likes(celebrity_id);
	}
	
	@Override
	public void DeleteCelebrity_Comments(@Param("celebrity_id") int celebrity_id)
	{
		getAdminMapper().DeleteCelebrity_Comments(celebrity_id);
	}
	@Override
	public void Delete_celebrity_News(@Param("celebrity_id") int celebrity_id)
	{
		getAdminMapper().Delete_celebrity_News(celebrity_id);
	}
	@Override
	public void DeleteCelebrity_Post(@Param("celebrity_id") int celebrity_id)
	{
		getAdminMapper().DeleteCelebrity_Post(celebrity_id);
	}
	@Override
	public List<AdminModel> getUserSearch_terms()
	{
		return getAdminMapper().getUserSearch_terms();
	}
	@Override
	public void DeleteUserSearchTerms(@Param("searched_id") int search_id)
	{
		getAdminMapper().DeleteUserSearchTerms(search_id);
	}
	
	@Override
	public void UpdateUserSearchedTerms(String search_key,int search_id)
	{
		getAdminMapper().UpdateUserSearchedTerms(search_key, search_id);
	}
	@Override
	public List<AdminModel> getSearchTerms_ByKey(String search_key)
	{
		return getAdminMapper().getSearchTerms_ByKey(search_key);
	}
	@Override
	public void InsertBlcakList_Website(String website_name)
	{
		getAdminMapper().InsertBlcakList_Website(website_name);
	}
	@Override
	public List<AdminModel> getBlacklist_Website()
	{
		return getAdminMapper().getBlacklist_Website();
	}
	@Override
	public List<AdminModel> getBlacklist_WebsiteByName(String website_name)
	{
		return getAdminMapper().getBlacklist_WebsiteByName(website_name);
	}
	@Override
	public void deleteWebsite_FromBlackList(int web_id)
	{
		getAdminMapper().deleteWebsite_FromBlackList(web_id);
	}
	@Override
	public void updateWebsite_name(String website_name,int web_id)
	{
		getAdminMapper().updateWebsite_name(website_name, web_id);
	}
	@Override
	public List<AdminModel> getMovies_list()
	{
		return getAdminMapper().getMovies_list();
	}
	@Override
	public void InsertBlog(AdminModel ad)
	{
		getAdminMapper().InsertBlog(ad);
		
	}

	public List<AdminModel> getBlogs_list() {
		return getAdminMapper().getBlogs_list();
	}

	public List<AdminModel> movie_blog_data(int blog_id) {
		return getAdminMapper().movie_blog_data(blog_id);
	}

	public void UpdateBlog(AdminModel ad) {
		getAdminMapper().UpdateBlog(ad);

	}

	public int lastAddedBlogRecord() {
		return getAdminMapper().lastAddedBlogRecord();
	}

	public String getBlog_image(int blogId) {
		return getAdminMapper().getBlog_image(blogId);
	}

	public void deleteBlog(int blog_id) {
		getAdminMapper().deleteBlog(blog_id);
	}

	@Override
	public List<AdminModel> getAllCelebrityNames() {
		
		return getAdminMapper().getAllCelebrityNames();
	}

	@Override
	public List<AdminModel> getAllCasts() {
		
		return getAdminMapper().getAllCasts();
	}

	@Override
	public int addCelebrityCast(int movie_id, int celebrity_id, String caracter) {
		
		return getAdminMapper().addCelebrityCast(movie_id, celebrity_id, caracter);
	}

	@Override
	public List<AdminModel> getMovieId(String celebrity_name) {

		return getAdminMapper().getMovieId(celebrity_name);
	}

	@Override
	public List<AdminModel> getCasts() {
		
		return getAdminMapper().getCasts();
	}

	@Override
	public int addNewRole(String caracter_name) {
		
		return getAdminMapper().addNewRole(caracter_name);
	}

	@Override
	public int deleteRole(int id) {
	
		return getAdminMapper().deleteRole(id);
	}

	@Override
	public int updateRoleName(int id, String caracter_name) {
		return getAdminMapper().updateRoleName(id, caracter_name);
	}

	public List<AdminModel> getMovie_cast(int celebrity_id) {
		return getAdminMapper().getMovie_cast(celebrity_id);
	}

	public List<AdminModel> getMovieCastCount(int movieId) {
		return getAdminMapper().getMovieCastCount(movieId);
	}

	public int movieCastDelete(int movieId) {
		return getAdminMapper().movieCastDelete(movieId);
	}

	public int deleteMovieCastRole(int delete_celebrity_id) {
		return getAdminMapper().deleteMovieCastRole(delete_celebrity_id);
	}

	@Override
	public int deleteMappedRole(int caracter) {
		return getAdminMapper().deleteMappedRole(caracter);
	}

	public int getCelebrityCount() {
		return getAdminMapper().getCelebrityCount();
	}

	public List<AdminModel> getSearchCelebrity_info(String search_celebrity_key) {
		return getAdminMapper().getSearchCelebrity_info(search_celebrity_key);
	}

	public int getSearchedCelebrityCount(String search_celebrity_key) {
		return getAdminMapper().getSearchedCelebrityCount(search_celebrity_key);
	}

	public int deleteBlacklistedNews(String website_name) {
		return getAdminMapper().deleteBlacklistedNews(website_name);
	}

	@Override
	public List<AdminModel> getAllRequestedCelebrityInfo() {
		return getAdminMapper().getAllRequestedCelebrityInfo();
	}
	
	@Override
	public List<AdminModel> getRequestedCelebrityInfoWithLimit(int limit,
			int offset) {
		return adminMapper.getRequestedCelebrityInfoWithLimit(limit, offset);
	}

	@Override
	public List<AdminModel> getAllRequestedCelebrityCategoriesInfo() {
		return getAdminMapper().getAllRequestedCelebrityCategoriesInfo();
	}

}