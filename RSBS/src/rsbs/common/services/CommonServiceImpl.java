package common.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import admin.model.AdminModel;
import common.mapper.CommonMapper;
import common.model.CommonModel;

//@Service
//@Configurable
public class CommonServiceImpl implements CommonServices {

	private CommonMapper commonMapper;

	public CommonMapper getCommonMapper() {
		return commonMapper;
	}

	public void setCommonMapper(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}
	
	@Override
	public List<AdminModel> getNewsCount()
	{
		return getCommonMapper().getNewsCount();
				
	}
	@Override
	public List<AdminModel> getTop_news()
	{
		return getCommonMapper().getTop_news();
	}
	@Override
	public List<AdminModel> getPopularFour_news()
	{
		return getCommonMapper().getPopularFour_news();
	}
	@Override
	public List<AdminModel> getAllPost_news()
	{
		return getCommonMapper().getAllPost_news();
	}
	@Override
	public List<AdminModel> getAllPostnews_ById(int celebrity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getAllPostnews_ById(celebrity_id,user_id,limit,offset);
	}
	@Override
	public List<AdminModel> getFacebook_post(int celebrity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getAll_SocialMediaPost("Facebook",celebrity_id,user_id,limit,offset);
	}
	@Override
	public List<AdminModel> getInstagram_post(int celebrity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getAll_SocialMediaPost("Instagram",celebrity_id,user_id,limit,offset);
	}
	@Override
	public List<AdminModel> getTwitter(int celebrity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getAll_SocialMediaPost("Twitter",celebrity_id,user_id,limit,offset);
	}
	
	@Override
	public List<AdminModel> getAll_news(int celebrity_id,int user_id,int limit,int offset)
	{
		return getCommonMapper().getAll_news(celebrity_id,user_id,limit,offset);
	}
	@Override
	public List<AdminModel> getCelebrity_info(int celebrity_id,int user_id)
	{
		return getCommonMapper().getCelebrity_info(celebrity_id,user_id);
	}
	@Override
	public List<AdminModel> getSuggested_politician(int celebrity_id,int user_id)
	{
		return getCommonMapper().getSuggested_politician(celebrity_id,user_id);
	}
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
	
	
	@Override
	public List<AdminModel> validate_email(String user_name,String password)
	{
		
		return getCommonMapper().validate_email(user_name, password);
	}

	@Override
	public List<AdminModel> getAllPoliticians(String celebrity_name, int user_id) {
	
		return getCommonMapper().getAllPoliticians(celebrity_name,user_id);
	}

	@Override
	public List<AdminModel> getPoliticiansFollowersCount(String celebrity_name) {
		
		return getCommonMapper().getPoliticiansFollowersCount(celebrity_name);
	}
	
	@Override
	public List<AdminModel> getPoliticiansForSearch(String celebrity_name,
			int user_id) {
		return commonMapper.getPoliticiansForSearch(celebrity_name, user_id);
	}

	@Override
	public int addPoliticianFollowers(int user_id, int celebrity_id, int news_letter_type) {
		
		return getCommonMapper().addPoliticianFollowers(user_id, celebrity_id, news_letter_type);
	}

	@Override
	public List<CommonModel> getUserInformation(int user_id) {
		
		return getCommonMapper().getUserInformation(user_id);
	}

	@Override
	public List<CommonModel> getExistingUser(String email_id) {
		
		return getCommonMapper().getExistingUser(email_id);
	}

	@Override
	public int updateUserInformation(int user_id, String first_name,
			String last_name, String contact_no, String email_id) {
		
		return getCommonMapper().updateUserInformation(user_id, first_name, last_name, contact_no, email_id);
	}

	@Override
	public int updateUserCredentials(int user_id, String user_name) {

		return getCommonMapper().updateUserCredentials(user_id, user_name);
	}

	@Override
	public String getPassword(int user_id) {
		
		return getCommonMapper().getPassword(user_id);
	}

	@Override
	public int updatePassword(int user_id, String password) {
		
		return getCommonMapper().updatePassword(user_id, password);
	}

	@Override
	public int addNewUser(CommonModel model) {
		
		return getCommonMapper().addNewUser(model);
	}

	@Override
	public int addUserCredentials(int user_id,String user_name, String password, int role_id) {

		return getCommonMapper().addUserCredentials(user_id,user_name, password, role_id);
	}

	@Override
	public List<AdminModel> getUserFollowerList(int user_id) {
		
		return getCommonMapper().getUserFollowerList(user_id);
	}

	@Override
	public int unfollowCelebrity(int follow_id) {

		return getCommonMapper().unfollowCelebrity(follow_id);
	}

	@Override
	public int addUserSearches(String search_key, int count) {
		
		return getCommonMapper().addUserSearches(search_key, count);
	}

	@Override
	public List<CommonModel> getUserSearches(String search_key) {
		
		return getCommonMapper().getUserSearches(search_key);
	}

	@Override
	public int updateSearchCount(String search_key, int count) {

		return getCommonMapper().updateSearchCount(search_key, count);
	}

	@Override
	public int addUserLikes(int user_id, int post_id_or_news_id, int activity_id) {
		
		return getCommonMapper().addUserLikes(user_id, post_id_or_news_id, activity_id);
	}

	@Override
	public int addUserComments(int user_id, int post_id_or_news_id,
			int activity_id, String comment) {

		return getCommonMapper().addUserComments(user_id, post_id_or_news_id, activity_id, comment);
	}

	@Override
	public int getUserLikeCount(int post_id_or_news_id, int activity_id) {
		
		return getCommonMapper().getUserLikeCount(post_id_or_news_id, activity_id);
	}

	@Override
	public List<CommonModel> getSpecificUserLikes(int activity_id) {

		return getCommonMapper().getSpecificUserLikes(activity_id);
	}

	@Override
	public List<CommonModel> getUserComments(int post_id_or_news_id,
			int activity_id) {
		
		return getCommonMapper().getUserComments(post_id_or_news_id, activity_id);
	}

	@Override
	public int deleteUserComments(int comment_id) {
	
		return getCommonMapper().deleteUserComments(comment_id);
	}

	@Override
	public int getUserCommentsCount(int post_id_or_news_id, int activity_id) {
		
		return getCommonMapper().getUserCommentsCount(post_id_or_news_id, activity_id);
	}

	@Override
	public List<CommonModel> getNewsPostCommentsCount(int activity_id) {
	
		return getCommonMapper().getNewsPostCommentsCount(activity_id);
	}

	@Override
	public List<CommonModel> getAllNPLikesCount() {

		return getCommonMapper().getAllNPLikesCount();
	}

	@Override
	public List<CommonModel> getAllNPCommentsCount() {

		return getCommonMapper().getAllNPCommentsCount();
	}

	@Override
	public int deleteUserLikes(int like_id) {
		
		return getCommonMapper().deleteUserLikes(like_id);
	}

	@Override
	public List<CommonModel> getCelebrityFollowersCount() {
	
		return getCommonMapper().getCelebrityFollowersCount();
	}

	@Override
	public List<AdminModel> getAllCelebrities() {
		
		return getCommonMapper().getAllCelebrities();
	}

	@Override
	public List<AdminModel> getBlacklistWebsites() {

		return getCommonMapper().getBlacklistWebsites();
	}

	@Override
	public List<AdminModel> politicianOfTheDay(int user_id) {
	
		return getCommonMapper().politicianOfTheDay(user_id);
	}

	@Override
	public List<AdminModel> getAllStates() {
		
		return getCommonMapper().getAllStates();
	}

	@Override
	public List<AdminModel> getAllCategories() {
		
		return getCommonMapper().getAllCategories();
	}

	@Override
	public List<AdminModel> getStatewisePoliticians(int state_id,
			int category_id, int user_id) {
		
		return getCommonMapper().getStatewisePoliticians(state_id, category_id, user_id);
	}

	@Override
	public List<AdminModel> getCelebrityCategory() {
		
		return getCommonMapper().getCelebrityCategory();
	}

	@Override
	public List<AdminModel> getNews(int news_id) {
		
		return getCommonMapper().getNews(news_id);
	}

	@Override
	public List<AdminModel> getPosts(int post_id) {

		return getCommonMapper().getPosts(post_id);
	}

	@Override
	public List<AdminModel> getResentBlogs() {
		
		return getCommonMapper().getResentBlogs();
	}

	@Override
	public List<AdminModel> getAllBlogs() {
		
		return getCommonMapper().getAllBlogs();
	}

	@Override
	public List<AdminModel> getBlogsById(int blog_id) {
		
		return getCommonMapper().getBlogsById(blog_id);
	}

	@Override
	public List<AdminModel> getResentThreeBlogs(int blog_id) {
		
		return getCommonMapper().getResentThreeBlogs(blog_id);
	}

	@Override
	public List<AdminModel> getUserLikeId(int user_id, int post_id_or_news_id,
			int activity_id) {
	
		return getCommonMapper().getUserLikeId(user_id, post_id_or_news_id, activity_id);
	}

	@Override
	public List<CommonModel> getUserTwoComments(int post_id_or_news_id,
			int activity_id) {

		return getCommonMapper().getUserTwoComments(post_id_or_news_id, activity_id);
	}

	@Override
	public List<AdminModel> getSuggestedUnfollowedPoliticians(int celebrity_id,
			int user_id) {

		return getCommonMapper().getSuggestedUnfollowedPoliticians(celebrity_id, user_id);
	}

	@Override
	public List<AdminModel> getAllPostsCount(int celebrity_id) {
		
		return getCommonMapper().getAllPostsCount(celebrity_id);
	}

	@Override
	public List<AdminModel> getCelebrityInfo() {
		
		return getCommonMapper().getCelebrityInfo();
	}

	@Override
	public int updateAccessToken(String access_token) {
		return getCommonMapper().updateAccessToken(access_token);
	}

	@Override
	public String getToken() {
		return getCommonMapper().getToken();

	}

	@Override
	public int addCelebrityPost(AdminModel adminModel) {
		return getCommonMapper().addCelebrityPost(adminModel);
	}

	@Override
	public List<AdminModel> getSavesData(String id) {
		return getCommonMapper().getSavesData(id);
	}

	@Override
	public List<AdminModel> getSocialMediaPosts(String id, String message1, String pic) {
		return getCommonMapper().getSocialMediaPosts(id,message1,pic);
	}

	@Override
	public int addCelebrityNews(AdminModel ad) {	
		return getCommonMapper().addCelebrityNews(ad);
	}

	@Override
	public List<AdminModel> getNewsId(String title,String img_url) {
		return getCommonMapper().getNewsId(title,img_url);
	}

	@Override
	public List<AdminModel> getPostsCount(int celebrity_id, String post_source) {
		
		return getCommonMapper().getPostsCount(celebrity_id, post_source);
	}

	@Override
	public List<AdminModel> getAllNewsCount(int celebrity_id) {
		
		return getCommonMapper().getAllNewsCount(celebrity_id);
	}

	@Override
	public List<AdminModel> getFollowers() {
		return getCommonMapper().getFollowers();
	}

	@Override
	public List<AdminModel> getCelebrityListByFollower(int user_id) {
		return getCommonMapper().getCelebrityListByFollower(user_id);

	}

	@Override
	public List<AdminModel> getTodaysNewsforCelebrity(int celebrity_id) {
		return getCommonMapper().getTodaysNewsforCelebrity(celebrity_id);
	}

	@Override
	public int getTotalCelebrityCount() {
		
		return getCommonMapper().getTotalCelebrityCount();
	}

	@Override
	public List<AdminModel> getTrending_celebrities1(int totalCelebCount) {
		return getCommonMapper().getTrending_celebrities1(totalCelebCount);
	}

	@Override
	public int removeCelebrityoftheDay() {
		return getCommonMapper().removeCelebrityoftheDay();
	}

	@Override
	public int insertCelebrityoftheDay(int celeb_id) {
		return getCommonMapper().insertCelebrityoftheDay(celeb_id);
	}

	@Override
	public int updateForgotedPassword(String user_name, String password) {
		
		return getCommonMapper().updateForgotedPassword(user_name, password);
	}

	@Override
	public List<AdminModel> getIsLiked(int user_id, int post_id_or_news_id,
			int activity_id) {
		
		return getCommonMapper().getIsLiked(user_id, post_id_or_news_id, activity_id);
	}
	
	@Override
	public List<AdminModel> getPopularNews(@Param("day_interval")int day_interval)
	{
		return getCommonMapper().getPopularNews(day_interval);
	}
	
	@Override
	public int getTotalNews_Count()
	{
		return getCommonMapper().getTotalNews_Count();
	}

	@Override
	public int addBillsInfo(AdminModel ad) {
		
		return getCommonMapper().addBillsInfo(ad);
	}

	@Override
	public List<AdminModel> getBills() {
		
		return getCommonMapper().getBills();
	}

	@Override
	public int truncateBills() {
		
		return getCommonMapper().truncateBills();
	}

	@Override
	public List<AdminModel> getPrecidentPoliticians(int category_id) {
		
		return getCommonMapper().getPrecidentPoliticians(category_id);
	}

	@Override
	public List<AdminModel> getCelebrityTwitterAccount(int celebrity_id) {
		
		return getCommonMapper().getCelebrityTwitterAccount(celebrity_id);
	}

	@Override
	public List<AdminModel> searchByCelebrityName(String celebrity_name) {
		return getCommonMapper().searchByCelebrityName(celebrity_name);
	}

	@Override
	public int checkforRepeatCount() {
		return getCommonMapper().checkforRepeatCount();
	}

	@Override
	public List<AdminModel> getAllUsers() {
		return getCommonMapper().getAllUsers();
	}

}
