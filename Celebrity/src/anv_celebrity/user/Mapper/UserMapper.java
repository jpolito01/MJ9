package user.Mapper;
import java.util.List;

import user.Model.UserModel;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PostAuthorize;

import common.model.CommonModel;

import admin.model.AdminModel;

public interface UserMapper {

	public String GET_NEWS_DATA="SELECT news_id, entity_id, entity_type_id FROM entity_news e where news_id=#{news_id}";
	@Select(GET_NEWS_DATA)
	public List<UserModel> getNews_data(@Param("news_id") int news_id);
	
	public String USER_LIKE_MAP="insert into user_like_map(user_id, post_id_or_news_id, activity_type_id)values(#{user_id}, #{post_id_or_news_id}, #{activity_type_id})";
	@Insert(USER_LIKE_MAP)
	public void insertUserLikeMap(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);

	
	public String DISS_LIKE_NEWS="delete from user_like_map where user_id=#{user_id} and  post_id_or_news_id=#{post_id_or_news_id} and activity_type_id =#{activity_type_id}";
	@Delete(DISS_LIKE_NEWS)
	public void dissLikeNews(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
	
	public String INSERT_USER_COMMENTS="insert into user_comments_map(user_id, post_id_or_news_id, activity_type_id, comments) "+
										" values (#{user_id}, #{post_id_or_news_id}, #{activity_type_id}, #{comments})";
	@Insert(INSERT_USER_COMMENTS)
	public void InsertNews_comments(AdminModel ad);
	
	public String DELETE_COMMENT="delete from user_comments_map where id=#{comment_id}";
	@Delete(DELETE_COMMENT)
	public void delete_comment(@Param("comment_id") int comment_id);
	
	public String ADD_FOLLOWER="insert into user_follower_map(user_id, entity_type_id, entity_id, newsletter_type) values(#{user_id}, #{entity_type_id}, #{entity_id}, #{newsletter_type})";
	@Insert(ADD_FOLLOWER)
	public void addFollowers(@Param("user_id")int user_id,@Param("entity_type_id") int entity_type_id, @Param("entity_id")int entity_id, @Param("newsletter_type") int newsletter_type);
	
	public String GET_USER_PROFILE="SELECT * FROM user_information u where user_id=#{user_id}";
	@Select(GET_USER_PROFILE)
	public List<CommonModel> getUserProfile(@Param("user_id") int user_id);
	
	public String UPDATE_PROFILE="update user_information set first_name=#{first_name}, last_name=#{last_name}, address=#{address} where user_id=#{user_id}";
	@Update(UPDATE_PROFILE)
	public void UpdateProfile(CommonModel cm);
	
	public String GET_LOGININFO_BY_USER_ID="SELECT * FROM user_login u where user_id=#{user_id}";
	@Select(GET_LOGININFO_BY_USER_ID)
	public List<CommonModel> getLoginInformation_ById(@Param("user_id") int user_id);
	
	public String CHANGE_PASSWORD="update user_login set password=#{password} where user_id=#{user_id}";
	@Update(CHANGE_PASSWORD)
	public void changePassword(@Param("password") String password,@Param("user_id") int user_id);
	
	public String GET_NEWS_COMMENTS="SELECT uc.*,uc.id as comment_id, concat(u.first_name,' ',u.last_name)as name FROM user_comments_map uc "+
			" join user_information u on uc.user_id = u.user_id "+
			" where post_id_or_news_id = #{post_id_or_news_id} and activity_type_id=#{activity_type_id} order by date desc";
	@Select(GET_NEWS_COMMENTS)
	public List<AdminModel> getNews_Comments(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);

    public String FOLLOWER_CELEBRITY_LIST="SELECT m.id,c.celebrity_name from anv_celebrity.user_follower_map m left join anv_celebrity.celebrity_info c on c.celebrity_id=m.entity_id where m.user_id=#{user_id}";
    @Select(FOLLOWER_CELEBRITY_LIST)
    public List<AdminModel> getUserFollowerList(@Param("user_id") int user_id);
    
    public String UNFOLLOW_CELEBRITY="delete from anv_celebrity.user_follower_map where id=#{id}";
    @Delete(UNFOLLOW_CELEBRITY)
    public int unfollowCelebrity(@Param("id") int id);
    
    public String GET_USER_LIKES_COUNT="select count(id) from user_like_map where post_id_or_news_id=#{post_id_or_news_id} and activity_type_id=#{activity_type_id}";
    @Select(GET_USER_LIKES_COUNT)
    public int getUserLikeCount(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
 
    public String GET_IS_AULREADY_LIKED="SELECT * FROM anv_celebrity.user_like_map u where user_id=#{user_id} and post_id_or_news_id=#{post_id_or_news_id} and activity_type_id=#{activity_type_id}";
    @Select(GET_IS_AULREADY_LIKED)
    public List<AdminModel> getIsAulreadyLiked(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);

    public String GET_NEWS_COMMENT_COUNT="select count(id) from anv_celebrity.user_comments_map where post_id_or_news_id=#{post_id_or_news_id} and activity_type_id=#{activity_type_id}";
    @Select(GET_NEWS_COMMENT_COUNT)
    public int getNewsCommentsCount(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
}
