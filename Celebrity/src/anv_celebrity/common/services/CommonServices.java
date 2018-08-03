package common.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import admin.model.AdminModel;

import common.model.CommonModel;

public interface CommonServices {
	public List<CommonModel> validateLogin(String user_name);
	public List<AdminModel> getCelebrity_name();
	public int saveCelebrity_news(AdminModel ad);
	public int getLast_NewsId();
	public void test();
	public List<AdminModel> getEntity_news(@Param("user_id") int user_id);
	public void registerUser(CommonModel cm);
	public List<CommonModel> getLast_addedUser();
	public void insertLoginInfo(CommonModel cm);
	public List<CommonModel> checkEmail_id(String email_id);
	public List<AdminModel> getLatestNews(int user_id);
	public List<AdminModel> getNewsLike_count(@Param("activity_type_id") int activity_type_id);
	public List<AdminModel> getNews_Comments(@Param("post_id_or_news_id") int post_id_or_news_id,int activity_type_id);
	public List<AdminModel> getComment_Count();
	public List<AdminModel> getCelebrityInfo_bySorted(@Param("latter") String latter,@Param("user_id") int user_id);
	public List<AdminModel> getCelebrityFollowerCount_sorted(@Param("letter") String letter);
	List<AdminModel> getCelebrity_Profile(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id);
	
	public List<AdminModel> getCelebrityNews_byCelebrityId(@Param("entity_id") int entity_id,int user_id,@Param("limit") int limit,@Param("offset") int offset);
	//public List<AdminModel> getTrending_celebrities();
	public List<AdminModel> getTrending_celebrities(@Param("day_interval") int day_interval);
	public List<AdminModel> getTrending_celebrities1(@Param("totalCelebCount") int totalCelebCount);
	public List<AdminModel> getTrendingCelebrity_news(@Param("celebrity_id") int celebrity_id,@Param("news_date") String news_date,int user_id);
	public List<AdminModel> Celebrity_youMayKnow(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id, int cat_id);
	public List<AdminModel> getFacebook_post(int entity_id,int user_id,@Param("limit") int limit,@Param("offset") int offset);
	public List<AdminModel> getTwitter_post(int entity_id,int user_id,@Param("limit") int limit,@Param("offset") int offset);
	public List<AdminModel> getInstagram_post(int entity_id,int user_id,@Param("limit") int limit,@Param("offset") int offset);
	public List<AdminModel> getOnlyNews(int entity_id,int user_id,@Param("limit") int limit,@Param("offset") int offset);
	
	
	public List<AdminModel> getCelebritycategory_profile(@Param("celebrity_id") int celebrity_id);
	public List<AdminModel> getUser_Searches(String search_key);
	public void insertNewUser_searches(String search_key,int search_count);
	public void updateNewUser_searches(int search_count,int search_id);
	public List<AdminModel> getSortedCelebrityInfo_ByCategoryId(@Param("latter") String latter,@Param("user_id") int user_id,@Param("category_id") int category_id);
	public List<AdminModel> getMovies_blogs();
	public List<AdminModel> getAllBlogs();
	public List<AdminModel> getBlogDetails(int blog_id);
	public List<AdminModel> getMoreBlogs(int blog_id);
	public List<AdminModel> getCelebrityInfo_ByCategoryId(int user_id,
			int category_id);
	public List<AdminModel> getCelebrityFollowerCount();
	public List<AdminModel> getTodaysNewsforCelebrity(int celebrity_id);
	public List<AdminModel> getFollowers();
	public List<AdminModel> get_saved_data(@Param("id") String id);
	public int insert_feeds(@Param("entity_id") int entity_id, @Param("entity_type_id") int entity_type_id, @Param("post_source") String post_source, @Param("picture") String picture, @Param("name") String name, @Param("message") String message, @Param("story") String story, @Param("cretated_time") String cretated_time, @Param("id") String id);
	public List<AdminModel> getToken();
	public List<AdminModel> getInstaPostId();
	public List<AdminModel> getFeedSourceId(@Param("post_source") String post_source,@Param("id") String id, String message1, String pic);
	public List<AdminModel> getBlackListWebsite();
	public List<AdminModel> getNewsId(@Param("news_source") String news_source, String titleRepeat, String img_url);
	public int addEntityNews(AdminModel ad);
	public int updateAccessTocken(@Param("token") String token);
	public List<AdminModel> getAllNewsPostCount(@Param("entity_id") int entity_id);
	public int getAllPostsCount(@Param("entity_id") int entity_id, @Param("post_source") String post_source);
	public List<AdminModel> getAllNewsCount(@Param("entity_id") int entity_id);
	public List<AdminModel> getAllCelebrities(@Param("user_id") int user_id);
	public List<AdminModel> getCelebrityListByFollower(int user_id);
	public int getTotalCelebrityCount();
	public int removeCelebrityoftheDay();
	public int insertCelebrityoftheDay(int celeb_id);
	public List<AdminModel> getCelebrityOfTheDayId();
	public List<CommonModel> getUserName(@Param("email_id") String email_id);
	public int updateForgotedPassword(@Param("email_id") String email_id,@Param("password") String password);
	public List<AdminModel> getAllLike_count();
	public List<AdminModel> getAllCelebrityFollowers();
	public List<AdminModel> getCelebrityNewsMovies_byCelebrityId(@Param("entity_id") int entity_id,
			int user_id,@Param("limit") int limit,@Param("offset") int offset);
	public List<AdminModel> getMovieCast(int cid, int user_id);
	public List<AdminModel> getMovieCastCharacter(int cid, int celebrity_id);
	public List<AdminModel> getUserFollowerCount(int celebrity_id);
	public List<AdminModel> Celebrity_youMayKnowforMovie(int cid, int user_id);
	public List<AdminModel> getAllNewsCountMovie(int cid);
	public List<AdminModel> getOnlyNewsMovie(int cid, int user_id, int limit,
			int offset);
	public List<AdminModel> getSpecificActorMovies(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id);
	public List<AdminModel> Celebrity_youMayKnowRelatedMovie(@Param("cid") int cid,@Param("user_id") int user_id,@Param("movie_id") int movie_id);
	public List<AdminModel> getCaracterwiseCelebrityNews(@Param("entity_id") int entity_id,@Param("user_id") int user_id);
	public List<AdminModel> getCaracterwiseCelebrityInfo(@Param("movie_id") int movie_id, @Param("caracter") int caracter);
	public List<AdminModel> getCaracterwiseOtherCelebrityInfo(@Param("movie_id") int movie_id);
	public List<AdminModel> checkForMovieCategory(int celebrity_id);
	public List<AdminModel> get_celebrity_suggestions(String searched);
	public List<AdminModel> get_all_celebrity_suggestions();
	
	public List<CommonModel> getAllUsers();
	public int updatePassword(@Param("user_id") int user_id,@Param("password") String password);
	public List<AdminModel> get_all_celebrity_categories();
	public int add_requested_celebrity(@Param("celebrity_name") String celebrity_name,@Param("user_id") int user_id);
	public int get_last_requested_celebrity_id(@Param("user_id") int user_id,@Param("celebrity_name") String celebrity_name);
	public int add_requested_celebrity_categories(@Param("requested_celebrity_id") int requested_celebrity_id,@Param("category_id") int category_id);
}