package common.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.model.CommonModel;

import admin.model.AdminModel;

public interface CommonServices {
/*Added By Snigdha 10/11/2017*/
	public List<AdminModel> getNewsCount();
	public List<AdminModel> getTop_news();
	public List<AdminModel> getPopularFour_news();
	public List<AdminModel> getAllPost_news();
	public List<AdminModel> getAllPostnews_ById(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id, @Param("limit") int limit, @Param("offset") int offset);
	public List<AdminModel> getFacebook_post(int celebrity_id,@Param("user_id") int user_id,@Param("limit") int limit, @Param("offset") int offset);
	public List<AdminModel> getInstagram_post( int celebrity_id,@Param("user_id") int user_id,@Param("limit") int limit, @Param("offset") int offset);
	public List<AdminModel> getTwitter(int celebrity_id,@Param("user_id") int user_id,@Param("limit") int limit, @Param("offset") int offset);
	public List<AdminModel> getAll_news( int celebrity_id,@Param("user_id") int user_id,@Param("limit") int limit, @Param("offset") int offset);
	public List<AdminModel> getCelebrity_info(int celebrity_id,int user_id);
	public List<AdminModel> getSuggested_politician(int celebrity_id,int user_id);
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	public List<AdminModel> validate_email(@Param("user_name") String user_name,@Param("password") String password);	
	public List<AdminModel> getAllPoliticians(@Param("celebrity_name") String celebrity_name,@Param("user_id") int user_id);
	 public List<AdminModel> getPoliticiansForSearch(@Param("celebrity_name") String celebrity_name, @Param("user_id") int user_id);
	public List<AdminModel> getPoliticiansFollowersCount(@Param("celebrity_name") String celebrity_name);
	public int addPoliticianFollowers(@Param("user_id") int user_id,@Param("celebrity_id") int celebrity_id,@Param("news_letter_type") int news_letter_type);
    public List<CommonModel> getUserInformation(@Param("user_id") int user_id);
    public List<CommonModel> getExistingUser(@Param("email_id") String email_id);
    public int updateUserInformation(@Param("user_id") int user_id,@Param("first_name") String first_name, @Param("last_name") String last_name,@Param("contact_no") String contact_no,@Param("email_id") String email_id);
    public int updateUserCredentials(@Param("user_id") int user_id,@Param("user_name") String email_id);
    public String getPassword(@Param("user_id") int user_id);
    public int updatePassword(@Param("user_id") int user_id,@Param("password") String password);
    public int addNewUser(CommonModel model);
    public int addUserCredentials(@Param("user_id") int user_id,@Param("user_name") String user_name,@Param("password") String password,@Param("role_id") int role_id);

    public List<AdminModel> getUserFollowerList(@Param("user_id") int user_id);
    public int unfollowCelebrity(@Param("follow_id") int follow_id);
    public int addUserSearches(@Param("search_key") String search_key, @Param("count") int count);
    public List<CommonModel> getUserSearches(@Param("search_key") String search_key);
    public int updateSearchCount(@Param("search_key") String search_key,@Param("count") int count);
    public int addUserLikes(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
    public int addUserComments(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id,@Param("comment") String comment);
    public int getUserLikeCount(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
    public List<CommonModel> getSpecificUserLikes(@Param("activity_id") int activity_id);
    public List<CommonModel> getUserComments(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
    public int deleteUserComments(@Param("comment_id") int comment_id);
    public int getUserCommentsCount(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
    public List<CommonModel> getNewsPostCommentsCount(@Param("activity_id") int activity_id);
    public List<CommonModel> getAllNPLikesCount();
    public List<CommonModel> getAllNPCommentsCount();
    public int deleteUserLikes(@Param("like_id") int like_id);
    public List<CommonModel> getCelebrityFollowersCount();
    public List<AdminModel> getAllCelebrities();
    public List<AdminModel> getBlacklistWebsites();
    public List<AdminModel> politicianOfTheDay(@Param("user_id") int user_id);
    //public List<AdminModel> getCelebYesterdayNews(@Param("celebrity_id") int celebrity_id);
    public List<AdminModel> getAllStates();
    public List<AdminModel> getAllCategories();
    public List<AdminModel> getStatewisePoliticians(@Param("state_id") int state_id, @Param("category_id") int category_id,@Param("user_id") int user_id);
    public List<AdminModel> getCelebrityCategory();
    public List<AdminModel> getNews(@Param("news_id") int news_id);
    public List<AdminModel> getPosts(@Param("post_id") int post_id);
    public List<AdminModel> getResentBlogs();
    public List<AdminModel> getAllBlogs();
    public List<AdminModel> getBlogsById(@Param("blog_id") int blog_id);
    public List<AdminModel> getResentThreeBlogs(@Param("blog_id") int blog_id);
	public List<AdminModel> getUserLikeId(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
	//public List<AdminModel> getYesterdayTop4News(@Param("celebrity_id") int celebrity_id, @Param("limit") int limit);
    public List<CommonModel> getUserTwoComments(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
    public List<AdminModel> getSuggestedUnfollowedPoliticians(@Param("celebrity_id") int celebrity_id, @Param("user_id") int user_id);
    public List<AdminModel> getAllPostsCount(@Param("celebrity_id") int celebrity_id);

    public List<AdminModel> getCelebrityInfo();
	public int updateAccessToken(String access_token);
	public String getToken();
	public int addCelebrityPost(AdminModel adminModel);
	public List<AdminModel> getSavesData(String id);
	public List<AdminModel> getSocialMediaPosts(String feed_source, String id, String message1);
	public int addCelebrityNews(AdminModel ad);
	public List<AdminModel> getNewsId(String titleRepeat, String img_url);
	
	public List<AdminModel> getPostsCount(@Param("celebrity_id") int celebrity_id,@Param("post_source") String post_source);
	public List<AdminModel> getAllNewsCount(@Param("celebrity_id") int celebrity_id);
	public List<AdminModel> getFollowers();
	public List<AdminModel> getCelebrityListByFollower(int user_id);
	public List<AdminModel> getTodaysNewsforCelebrity(int celebrity_id);
	
	public int getTotalCelebrityCount();
	public List<AdminModel> getTrending_celebrities1(int totalCelebCount);
	public int removeCelebrityoftheDay();
	public int insertCelebrityoftheDay(int celeb_id);
	//public List<AdminModel> getCelebrityOfTheDayId();
    public int updateForgotedPassword(@Param("user_name") String user_name,@Param("password") String password);
    public List<AdminModel> getIsLiked(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
    
    
    public List<AdminModel> getPopularNews(@Param("day_interval")int day_interval);
    public int getTotalNews_Count();
    public int addBillsInfo(AdminModel ad);
    public List<AdminModel> getBills();
    public int truncateBills();
    public List<AdminModel> getPrecidentPoliticians(@Param("category_id") int category_id);
	public List<AdminModel> getCelebrityTwitterAccount(@Param("celebrity_id") int celebrity_id);
	public List<AdminModel> searchByCelebrityName(String celebrity_name);
	public int checkforRepeatCount();
	public List<AdminModel> getAllUsers();

}
