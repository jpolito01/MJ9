package user.Services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.model.CommonModel;

import admin.model.AdminModel;

import user.Model.UserModel;

public interface UserServices {
	public List<UserModel> getNews_data(@Param("news_id") int news_id);
	public void insertUserLikeMap(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
	public void dissLikeNews(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
	public void InsertNews_comments(AdminModel ad);
	public void delete_comment(@Param("comment_id") int comment_id);
	public void addFollowers(@Param("user_id")int user_id,@Param("entity_type_id") int entity_type_id, @Param("entity_id")int entity_id, @Param("newsletter_type") int newsletter_type);
	public List<CommonModel> getUserProfile(@Param("user_id") int user_id);
	public void UpdateProfile(CommonModel cm);
	public List<CommonModel> getLoginInformation_ById(int user_id);
	public void changePassword(String password,int user_id);
	public List<AdminModel> getNews_Comments(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
    public List<AdminModel> getUserFollowerList(@Param("user_id") int user_id);
    public int unfollowCelebrity(@Param("id") int id);
    public int getUserLikeCount(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
    public List<AdminModel> getIsAulreadyLiked(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
    public int getNewsCommentsCount(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);

}
