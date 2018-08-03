package user.Services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.model.CommonModel;

import admin.model.AdminModel;

import user.Mapper.UserMapper;
import user.Model.UserModel;

public class UserServicesImpl implements UserServices {

	private UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	@Override
	public List<UserModel> getNews_data(int news_id)
	{
		return getUserMapper().getNews_data(news_id);
	}
	@Override
	public void insertUserLikeMap(int user_id,int post_id_or_news_id,int activity_type_id)
	{
		getUserMapper().insertUserLikeMap(user_id, post_id_or_news_id, activity_type_id);
	}
	@Override
	public void dissLikeNews(int user_id,int post_id_or_news_id,int activity_type_id)
	{
		getUserMapper().dissLikeNews(user_id, post_id_or_news_id, activity_type_id);
	}
	@Override
	public void InsertNews_comments(AdminModel ad)
	{
		getUserMapper().InsertNews_comments(ad);
	}
	@Override
	public void delete_comment(int comment_id)
	{
		getUserMapper().delete_comment(comment_id);
	}
	@Override
	public void addFollowers(int user_id,int entity_type_id, int entity_id, int newsletter_type)
	{
		getUserMapper().addFollowers(user_id, entity_type_id, entity_id, newsletter_type);
	}
	@Override
	public List<CommonModel> getUserProfile(int user_id)
	{
		return getUserMapper().getUserProfile(user_id);
	}
	@Override
	public void UpdateProfile(CommonModel cm)
	{
		getUserMapper().UpdateProfile(cm);
	}
	@Override
	public List<CommonModel> getLoginInformation_ById(int user_id)
	{
		return getUserMapper().getLoginInformation_ById(user_id);
	}

	@Override
	public void changePassword(String password, int user_id) {
		getUserMapper().changePassword(password, user_id);
		
	}
	@Override
	public List<AdminModel> getNews_Comments(int post_id_or_news_id,int activity_type_id)
	{
		return getUserMapper().getNews_Comments(post_id_or_news_id, activity_type_id);
	}

	@Override
	public List<AdminModel> getUserFollowerList(int user_id) {
	
		return getUserMapper().getUserFollowerList(user_id);
	}

	@Override
	public int unfollowCelebrity(int id) {
		
		return getUserMapper().unfollowCelebrity(id);
	}

	@Override
	public int getUserLikeCount(int post_id_or_news_id, int activity_type_id) {
		
		return getUserMapper().getUserLikeCount(post_id_or_news_id, activity_type_id);
	}

	@Override
	public List<AdminModel> getIsAulreadyLiked(int user_id,
			int post_id_or_news_id, int activity_type_id) {
		
		return getUserMapper().getIsAulreadyLiked(user_id, post_id_or_news_id, activity_type_id);
	}

	@Override
	public int getNewsCommentsCount(int post_id_or_news_id, int activity_type_id) {
		
		return getUserMapper().getNewsCommentsCount(post_id_or_news_id, activity_type_id);
	}
}
