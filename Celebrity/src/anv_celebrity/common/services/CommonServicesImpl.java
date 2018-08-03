package common.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;


import admin.model.AdminModel;

import common.mapper.CommonMapper;
import common.model.CommonModel;
//@Service
//@Configurable
public class CommonServicesImpl implements CommonServices{
private CommonMapper commonMapper;

public CommonMapper getCommonMapper() {
	return commonMapper;
}

public void setCommonMapper(CommonMapper commonMapper) {
	this.commonMapper = commonMapper;
}
	
	@Override
	public List<CommonModel> validateLogin(String user_name) {
		
		return getCommonMapper().validateLogin(user_name);
	}
	@Override
	public List<AdminModel> getCelebrity_name()
	{
		return getCommonMapper().getCelebrity_name();
	}
	@Override
	public int saveCelebrity_news(AdminModel ad)
	{
		return getCommonMapper().saveCelebrity_news(ad);
	}
	@Override
	public int getLast_NewsId()
	{
		return getCommonMapper().getLast_NewsId();
				
	}
	@Override
	public void test()
	{
		
		System.out.println("iNSIDE TEST FUNCTION ..... ");
	}
	@Override
	public List<AdminModel> getEntity_news(int user_id)
	{
		return getCommonMapper().getEntity_news(user_id);
	}
	@Override
	public void registerUser(CommonModel cm)
	{
		getCommonMapper().registerUser(cm);
	}
	@Override
	public List<CommonModel> getLast_addedUser()
	{
		return getCommonMapper().getLast_addedUser();
	}
	@Override
	public void insertLoginInfo(CommonModel cm)
	{
	getCommonMapper().insertLoginInfo(cm);
	}
	@Override
	public List<CommonModel> checkEmail_id(String email_id)
	{
		return getCommonMapper().checkEmail_id(email_id);
	}
	@Override
	public List<AdminModel> getLatestNews(int user_id)
	{
		return getCommonMapper().getLatestNews(user_id);
	}
	@Override
	public List<AdminModel> getNewsLike_count(int activity_type_id)
	{
		return getCommonMapper().getNewsLike_count(activity_type_id);
	}
	@Override
	public List<AdminModel> getNews_Comments(int post_id_or_news_id,int activity_type_id)
	{
		return getCommonMapper().getNews_Comments(post_id_or_news_id,activity_type_id);
	}
	@Override
	public List<AdminModel> getComment_Count()
	{
		return getCommonMapper().getComment_Count();
	}
	
	@Override
	public List<AdminModel> getCelebrityInfo_bySorted(String latter, int user_id)
	{
		return getCommonMapper().getCelebrityInfo_bySorted(latter,user_id);
	}
	@Override
	public List<AdminModel> getCelebrityFollowerCount_sorted(String letter)
	{
		return getCommonMapper().getCelebrityFollowerCount_sorted(letter);
	}
	@Override
	public List<AdminModel> getCelebrity_Profile(int celebrity_id,int user_id)
	{
		return getCommonMapper().getCelebrity_Profile(celebrity_id,user_id);
	}
	@Override
	public List<AdminModel> getCelebrityNews_byCelebrityId(int celebrity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getCelebrityNews_byCelebrityId(celebrity_id,user_id,limit,offset);
	}
	/*@Override
	public List<AdminModel> getTrending_celebrities()
	{
		return getCommonMapper().getTrending_celebrities();
	}*/
	@Override
	public List<AdminModel> getTrending_celebrities1(int totalCelebCount)
	{
		return getCommonMapper().getTrending_celebrities1(totalCelebCount);
	}
	@Override
	public List<AdminModel> getTrendingCelebrity_news(@Param("celebrity_id") int celebrity_id,@Param("news_date") String news_date,int user_id)
	{
		return getCommonMapper().getTrendingCelebrity_news(celebrity_id, news_date,user_id);
	}
	@Override
	public List<AdminModel> Celebrity_youMayKnow(int celebrity_id,int user_id,int cat_id)
	{
		return getCommonMapper().Celebrity_youMayKnow(celebrity_id,user_id,cat_id);
	}
	@Override
	public List<AdminModel> getFacebook_post(int entity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getSocialMedia_post(entity_id,user_id,"Facebook",limit,offset);
	}
	
	@Override
	public List<AdminModel> getTwitter_post(int entity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getSocialMedia_post(entity_id,user_id,"Twitter",limit,offset);
	}
	
	@Override
	public List<AdminModel> getInstagram_post(int entity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getSocialMedia_post(entity_id, user_id, "Instagram",limit,offset);
	}
	
	@Override
	public List<AdminModel> getOnlyNews(int entity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getOnlyNews(entity_id, user_id,limit,offset);
	}
	
	@Override
	public List<AdminModel> getCelebritycategory_profile(int celebrity_id)
	{
		return getCommonMapper().getCelebritycategory_profile(celebrity_id);
	}
	@Override
	public List<AdminModel> getUser_Searches(@Param("search_key") String search_key)
	{
		return getCommonMapper().getUser_Searches(search_key);
	}
	@Override
	public void insertNewUser_searches(String search_key,int search_count)
	{
		getCommonMapper().insertNewUser_searches(search_key, search_count);
	}
	@Override
	public void updateNewUser_searches(int search_count,int search_id)
	{
		getCommonMapper().updateNewUser_searches(search_count, search_id);
	}
	@Override
	public List<AdminModel> getSortedCelebrityInfo_ByCategoryId(String latter,int user_id,int category_id)
	{
		return getCommonMapper().getSortedCelebrityInfo_ByCategoryId(latter, user_id, category_id);
	}

	@Override
	public List<AdminModel> getMovies_blogs() {
		return getCommonMapper().getMovies_blogs();
	}

	@Override
	public List<AdminModel> getAllBlogs() {
		return getCommonMapper().getAllBlogs();
	}

	@Override
	public List<AdminModel> getBlogDetails(int blog_id) {
		return getCommonMapper().getBlogDetails(blog_id);
	}

	@Override
	public List<AdminModel> getMoreBlogs(int blog_id) {
		return getCommonMapper().getMoreBlogs(blog_id);

	}

	@Override
	public List<AdminModel> getCelebrityInfo_ByCategoryId(int user_id,
			int category_id) {
		return getCommonMapper().getCelebrityInfo_ByCategoryId(user_id,category_id);
	}

	@Override
	public List<AdminModel> getCelebrityFollowerCount() {
		return getCommonMapper().getCelebrityFollowerCount();
	}

	@Override
	public List<AdminModel> getTodaysNewsforCelebrity(int celebrity_id) {
		return getCommonMapper().getTodaysNewsforCelebrity(celebrity_id);
	}

	@Override
	public List<AdminModel> getFollowers() {
		return getCommonMapper().getFollowers();
	}

	@Override
	public List<AdminModel> get_saved_data(String id) {
		
		return getCommonMapper().get_saved_data(id);
	}

	@Override
	public int insert_feeds(int entity_id, int entity_type_id,
			String post_source, String picture, String name, String message,
			String story, String cretated_time, String id) {
		
		return getCommonMapper().insert_feeds(entity_id, entity_type_id, post_source, picture, name, message, story, cretated_time, id);
	}

	@Override
	public List<AdminModel> getToken() {
		
		return getCommonMapper().getToken();
	}

	@Override
	public List<AdminModel> getInstaPostId() {
		
		return getCommonMapper().getInstaPostId();
	}

	@Override
	public List<AdminModel> getFeedSourceId(String post_source, String id, String message, String pic) {
		
		return getCommonMapper().getFeedSourceId(post_source, id,message,pic);
	}

	@Override
	public List<AdminModel> getBlackListWebsite() {
		
		return getCommonMapper().getBlackListWebsite();
	}

	@Override
	public List<AdminModel> getNewsId(String news_source, String msg, String pic) {
		
		return getCommonMapper().getNewsId(news_source,msg,pic);
	}

	@Override
	public int addEntityNews(AdminModel ad) {
		
		return getCommonMapper().addEntityNews(ad);
	}

	@Override
	public int updateAccessTocken(String token) {
	
		return getCommonMapper().updateAccessTocken(token);
	}

	@Override
	public List<AdminModel> getAllNewsPostCount(int entity_id) {
		
		return getCommonMapper().getAllNewsPostCount(entity_id);
	}

	@Override
	public int getAllPostsCount(int entity_id, String post_source) {
	
		return getCommonMapper().getAllPostsCount(entity_id, post_source);
	}

	@Override
	public List<AdminModel> getAllNewsCount(int entity_id) {

		return getCommonMapper().getAllNewsCount(entity_id);
	}

	@Override
	public List<AdminModel> getAllCelebrities(int user_id) {
		return getCommonMapper().getAllCelebrities(user_id);
	}

	@Override
	public List<AdminModel> getCelebrityListByFollower(int user_id) {
		return getCommonMapper().getCelebrityListByFollower(user_id);
	}

	@Override
	public int getTotalCelebrityCount() {
		return getCommonMapper().getTotalCelebrityCount();
	}

	@Override
	public int removeCelebrityoftheDay() {
		return getCommonMapper().removeCelebrityoftheDay();
	}

	@Override
	public int insertCelebrityoftheDay(int celeb_id) {
		return getCommonMapper().insertCelebrityoftheDay(celeb_id);
	}

	/*@Override
	public List<AdminModel> getTrending_celebrities() {
		return getCommonMapper().getTrending_celebrities();
	}*/
	
	@Override
	public List<AdminModel> getTrending_celebrities(int day_interval) {
		return getCommonMapper().getTrending_celebrities(day_interval);
	}

	@Override
	public List<AdminModel> getCelebrityOfTheDayId() {
		
		return getCommonMapper().getCelebrityOfTheDayId();
	}

	@Override
	public List<CommonModel> getUserName(String email_id) {
		
		return getCommonMapper().getUserName(email_id);
	}

	@Override
	public int updateForgotedPassword(String email_id,String password) {
		
		return getCommonMapper().updateForgotedPassword(email_id,password);
	}

	@Override
	public List<AdminModel> getAllLike_count() {
		
		return getCommonMapper().getAllLike_count();
	}

	@Override
	public List<AdminModel> getAllCelebrityFollowers() {
		
		return getCommonMapper().getAllCelebrityFollowers();
	}

	@Override
	public List<AdminModel> getCelebrityNewsMovies_byCelebrityId(int cid,
			int user_id, int limit, int offset) {
		return getCommonMapper().getCelebrityNewsMovies_byCelebrityId(cid,user_id,limit,offset);
	}

	@Override
	public List<AdminModel> getMovieCast(int cid,int user_id) {
		return getCommonMapper().getMovieCast(cid,user_id);
	}

	@Override
	public List<AdminModel> getMovieCastCharacter(int cid, int celebrity_id) {
		return getCommonMapper().getMovieCastCharacter(cid,celebrity_id);
	}

	@Override
	public List<AdminModel> getUserFollowerCount(int celebrity_id) {
		return getCommonMapper().getUserFollowerCount(celebrity_id);
	}

	@Override
	public List<AdminModel> Celebrity_youMayKnowforMovie(int cid, int user_id) {
		return getCommonMapper().Celebrity_youMayKnowforMovie(cid,user_id);
	}

	@Override
	public List<AdminModel> getAllNewsCountMovie(int cid) {
		return getCommonMapper().getAllNewsCountMovie(cid);
	}

	@Override
	public List<AdminModel> getOnlyNewsMovie(int cid, int user_id, int limit,
			int offset) {
		return getCommonMapper().getOnlyNewsMovie(cid,user_id,limit,offset);
	}

	@Override
	public List<AdminModel> getSpecificActorMovies(int celebrity_id, int user_id) {
		
		return getCommonMapper().getSpecificActorMovies(celebrity_id, user_id);
	}

	@Override
	public List<AdminModel> Celebrity_youMayKnowRelatedMovie(int cid,
			int user_id, int movie_id) {
		
		return getCommonMapper().Celebrity_youMayKnowRelatedMovie(cid, user_id, movie_id);
	}

	@Override
	public List<AdminModel> getCaracterwiseCelebrityNews(int entity_id, int user_id) {
		
		return getCommonMapper().getCaracterwiseCelebrityNews(entity_id, user_id);
	}

	@Override
	public List<AdminModel> getCaracterwiseCelebrityInfo(int movie_id,
			int caracter) {
		
		return getCommonMapper().getCaracterwiseCelebrityInfo(movie_id, caracter);
	}

	@Override
	public List<AdminModel> getCaracterwiseOtherCelebrityInfo(int movie_id) {
		
		return getCommonMapper().getCaracterwiseOtherCelebrityInfo(movie_id);
	}

	@Override
	public List<AdminModel> checkForMovieCategory(int celebrity_id) {
		return getCommonMapper().checkForMovieCategory(celebrity_id);
	}

	@Override
	public List<AdminModel> get_celebrity_suggestions(String searched) {
		return getCommonMapper().get_celebrity_suggestions(searched);
	}
	@Override
		public List<AdminModel> get_all_celebrity_suggestions() {
			return getCommonMapper().get_all_celebrity_suggestions();
	}
	@Override
		public List<CommonModel> getAllUsers() {
			return commonMapper.getAllUsers();
		}
	@Override
		public int updatePassword(int user_id, String password) {
			return commonMapper.updatePassword(user_id, password);
		}
	@Override
		public List<AdminModel> get_all_celebrity_categories() {
			return commonMapper.get_all_celebrity_categories();
		}

	@Override
	public int add_requested_celebrity(String celebrity_name, int user_id) {
		return commonMapper.add_requested_celebrity(celebrity_name, user_id);
	}

	@Override
		public int get_last_requested_celebrity_id(int user_id,
				String celebrity_name) {
			return commonMapper.get_last_requested_celebrity_id(user_id, celebrity_name);
		}

	@Override
	public int add_requested_celebrity_categories(int requested_celebrity_id,
			int category_id) {
		return commonMapper.add_requested_celebrity_categories(requested_celebrity_id, category_id);
	}
}