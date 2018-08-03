package common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import common.model.AdminModel;

public interface CommonMapper {
	public String REPEAT_COUNT="select count(celebrity_id) from celebrity_info where celebrity_of_the_day_date=curdate()";
	@Select(REPEAT_COUNT)
	public int checkforRepeatCount();
	
	public String GET_CELEBRITY_COUNT="select count(celebrity_id) from anv_rsbs.celebrity_info";
	@Select(GET_CELEBRITY_COUNT)
	public int getTotalCelebrityCount();
	
	public String GET_CELEBRITYOFTHEDAY_COUNT="SELECT count(ci.celebrity_id) as like_count,ci.celebrity_id FROM anv_rsbs.celebrity_news e join anv_rsbs.celebrity_info ci on e.celebrity_id=ci.celebrity_id where substring(publication_date,1,10) = (curdate() - INTERVAL 1 DAY) and (ci.celebrity_of_the_day_date IS NULL or DATEDIFF(curdate(),ci.celebrity_of_the_day_date) > #{celebCount}) group by celebrity_id order by like_count desc limit 1";
	@Select(GET_CELEBRITYOFTHEDAY_COUNT)
	public List<AdminModel> getTrending_celebrities1(int totalCelebCount);
	
	public String REMOVE_CELEBRITYOFTHEDAY="update anv_rsbs.celebrity_info set celebrity_of_the_day=0 where celebrity_of_the_day=1";
	@Update(REMOVE_CELEBRITYOFTHEDAY)
	public int removeCelebrityoftheDay();
	
	public String UPDATE_CELEBRITYOFTHEDAY="update anv_rsbs.celebrity_info set celebrity_of_the_day=1, celebrity_of_the_day_date=curdate() where celebrity_id=#{celeb_id}";
	@Update(UPDATE_CELEBRITYOFTHEDAY)
	public int insertCelebrityoftheDay(int celeb_id);
	
	public String GET_FOLLOWER="SELECT u.user_id,u.user_name FROM user_login u join user_follower_map uf on u.user_id=uf.user_id group by user_id";
	@Select(GET_FOLLOWER)
	public List<AdminModel> getFollowers();
	
	public String GET_CELEBRITY_BY_FOLLOWER="SELECT um.celebrity_id,ci.celebrity_name,ci.party_id FROM user_follower_map um join celebrity_info ci on um.celebrity_id=ci.celebrity_id where user_id=#{user_id}";
	@Select(GET_CELEBRITY_BY_FOLLOWER)
	public List<AdminModel> getCelebrityListByFollower(int user_id);
	
	public String CELEBRITY_NEWS="select * from (SELECT news_id, c.celebrity_id, news_source,  news_description_url, news_title, image_url, DATE_FORMAT(publication_date, '%d %b  %H:%i') publication_date FROM anv_rsbs.celebrity_news c where c.celebrity_id=#{celeb_id} and substring(publication_date,1,10) = DATE(NOW() - INTERVAL 1 DAY) group by news_title, image_url)a union select * from (SELECT post_id, s.celebrity_id, post_source, null as news_description_url,  message, image_url,  DATE_FORMAT(created_time, '%d %b  %H:%i') as publication_date  FROM anv_rsbs.social_media_post s where s.celebrity_id=#{celeb_id}  and substring(created_time,1,10) = DATE(NOW() - INTERVAL 1 DAY) group by message)b order by publication_date desc";
    @Select(CELEBRITY_NEWS)
	public List<AdminModel> getTodaysNewsforCelebrity(@Param("celeb_id") int celeb_id);
}