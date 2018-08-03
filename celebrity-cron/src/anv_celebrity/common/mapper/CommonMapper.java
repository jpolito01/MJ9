package common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import common.model.CommonModel;


public interface CommonMapper {
	public String GET_CELEBRITY_COUNT="select count(celebrity_id) from celebrity_info";
	@Select(GET_CELEBRITY_COUNT)
	public int getTotalCelebrityCount();
	
	public String GET_CELEBRITYOFTHEDAY_COUNT="SELECT count(entity_id) as like_count,ci.celebrity_id FROM entity_news e join celebrity_info ci on e.entity_id=ci.celebrity_id where substring(publication_date,1,10) = (curdate() - INTERVAL 1 DAY) and (ci.celebrity_of_the_day_date IS NULL or DATEDIFF(curdate(),ci.celebrity_of_the_day_date) > #{celebCount}) group by entity_id order by like_count desc limit 1";
	@Select(GET_CELEBRITYOFTHEDAY_COUNT)
	public List<CommonModel> getTrending_celebrities1(int totalCelebCount);
	
	public String REMOVE_CELEBRITYOFTHEDAY="update celebrity_info set celebrity_of_the_day=0 where celebrity_of_the_day=1";
	@Update(REMOVE_CELEBRITYOFTHEDAY)
	public int removeCelebrityoftheDay();
	
	public String UPDATE_CELEBRITYOFTHEDAY="update celebrity_info set celebrity_of_the_day=1, celebrity_of_the_day_date=curdate() where celebrity_id=#{celeb_id}";
	@Update(UPDATE_CELEBRITYOFTHEDAY)
	public int insertCelebrityoftheDay(int celeb_id);
	
	public String GET_FOLLOWER="SELECT u.user_id,u.email_id FROM user_login u join user_follower_map uf on u.user_id=uf.user_id group by user_id";
	@Select(GET_FOLLOWER)
	public List<CommonModel> getFollowers();
	
	public String GET_CELEBRITY_BY_FOLLOWER="SELECT um.entity_id,ci.celebrity_name FROM user_follower_map um join celebrity_info ci on um.entity_id=ci.celebrity_id where user_id=#{user_id}";
	@Select(GET_CELEBRITY_BY_FOLLOWER)
	public List<CommonModel> getCelebrityListByFollower(int user_id);
	
	public String CELEBRITY_NEWS="SELECT e.news_id,e.entity_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url FROM entity_news e where e.entity_id=#{user_id} and substring(publication_date,1,10) = DATE(NOW() - INTERVAL 1 DAY) union SELECT ep.post_id as news_id,ep.entity_id,ep.post_source as news_source,ep.message as news_title,null as news_description_url,cretated_time as news_date, DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url FROM entity_post ep where ep.entity_id=#{user_id} and substring(cretated_time,1,10) = DATE(NOW() - INTERVAL 1 DAY) order by news_date desc";
	@Select(CELEBRITY_NEWS)
	public List<CommonModel> getTodaysNewsforCelebrity(@Param("user_id") int user_id);
}