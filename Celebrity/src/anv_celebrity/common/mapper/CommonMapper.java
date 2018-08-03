package common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import admin.model.AdminModel;

import common.model.CommonModel;

public interface CommonMapper {
	/*final String VALIDATE_LOGIN="SELECT u.*,ui.first_name,ui.last_name FROM anv_celebrity.user_login u left join anv_celebrity.user_information ui on ui.user_id=u.user_id where u.email_id=#{user_name} and u.password=#{password}";
	@Select(VALIDATE_LOGIN)
	public List<CommonModel> validateLogin(@Param("user_name") String user_name,@Param("password") String password);*/
	final String VALIDATE_LOGIN="SELECT u.*,ui.first_name,ui.last_name FROM anv_celebrity.user_login u left join anv_celebrity.user_information ui on ui.user_id=u.user_id where u.email_id=#{user_name}";
	@Select(VALIDATE_LOGIN)
	public List<CommonModel> validateLogin(@Param("user_name") String user_name);
	
	final String GET_CELEBRITY_NAMES="select celebrity_id,celebrity_name,facebok_page_name,instagram_page_name,twitter_handle_name from anv_celebrity.celebrity_info where is_active=1";
	@Select(GET_CELEBRITY_NAMES)
	public List<AdminModel> getCelebrity_name();
	
	final String SAVE_CELEBRITY_NEWS="insert into entity_news(entity_id, entity_type_id, news_source, news_author, news_title, news_description_url, news_description, publication_date)" +
								"	values(#{entity_id}, #{entity_type_id}, #{news_source}, #{news_author}, #{news_title}, #{news_description_url}, #{news_description}, #{publication_date})";
	@Insert(SAVE_CELEBRITY_NEWS)
	public int saveCelebrity_news(AdminModel ad);
	
	public String GET_LAST_NEWS_ID="SELECT news_id FROM entity_news e order by news_id desc limit 1;";
	@Select(GET_LAST_NEWS_ID)
	public int getLast_NewsId();
	
	//public String GET_NEWS="SELECT * FROM entity_news e order by news_id desc";
	/*public String GET_NEWS="SELECT e.*,u.user_id FROM entity_news e " +
							"left join user_like_map u on e.news_id =u.post_id_or_news_id and u.user_id = #{user_id} order by news_id desc";*/
	/*public String GET_NEWS="SELECT e.*,u.user_id,um.user_id as follower_id FROM entity_news e "+
					" left join user_like_map u on e.news_id =u.post_id_or_news_id and u.user_id = #{user_id} "+
					" left join user_follower_map um on um.entity_id=e.entity_id and um.user_id=#{user_id} "+
					" order by news_id desc";commented on 04/10/2017*/
	public String GET_NEWS="SELECT news_date,e.*,ci.profile_photo_path,uf.entity_id,uf.user_id as follower_id,uf.id,ul.user_id FROM entity_news e "+
						" join celebrity_info ci on ci.celebrity_id = e.entity_id and e.entity_type_id=1 "+
						" left join user_follower_map uf on uf.entity_id = e.entity_id and uf.user_id = #{user_id} and uf.entity_type_id=1 " +
						" left join user_like_map ul on ul.post_id_or_news_id=e.news_id and ul.user_id=#{user_id} "+
						" where e.entity_id in (select m.celebrity_id from celebrity_category_map m where m.category_id!=6) "+
						" group by news_date order by news_date desc limit 50";
	@Select(GET_NEWS)
	public List<AdminModel> getEntity_news(@Param("user_id") int user_id);
	
	public String REGISTER_USER="insert into user_information(first_name, last_name,email_id) values(#{first_name}, #{last_name}, #{email_id})";
	@Insert(REGISTER_USER)
	public void registerUser(CommonModel cm);
	
	public String INSERT_LOGIN_INFO="insert into user_login(user_id,email_id, password, role_id)values(#{user_id}, #{email_id}, #{password}, #{role_id})";
	@Insert(INSERT_LOGIN_INFO)
	public void insertLoginInfo(CommonModel cm);
	
	public String GET_LAST_ADDED_USER="SELECT user_id,email_id,first_name,last_name FROM user_information u order by user_id desc limit 1";
	@Select(GET_LAST_ADDED_USER)
	public List<CommonModel> getLast_addedUser();
	
	public String CHECK_EMAIL_ID="SELECT * FROM user_login where email_id = #{email_id}";
	@Select(CHECK_EMAIL_ID)
	public List<CommonModel> checkEmail_id(@Param("email_id") String email_id);
	
	public String GET_LATEST_NEWS="SELECT * FROM entity_news e "+
                                  " left join user_like_map u on u.post_id_or_news_id =e.news_id and user_id=#{user_id} and u.activity_type_id=1 "+
                                  " where e.entity_id in (select m.celebrity_id from celebrity_category_map m where m.category_id!=6) "+
                                  " group by e.news_title order by news_date desc limit 30 ";
    @Select(GET_LATEST_NEWS)
	public List<AdminModel> getLatestNews(@Param("user_id") int user_id); 
	
	/*public String GET_LATEST_NEWS="select * from (SELECT news_id, c.entity_id, news_source,  news_description_url, news_title, image_url,publication_date as news_date, publication_date ,null as celebrity_name,ulm.activity_type_id FROM anv_celebrity.entity_news c join anv_celebrity.celebrity_info ci on ci.celebrity_id=c.entity_id left join user_like_map ulm on ulm.post_id_or_news_id =c.news_id and user_id=#{user_id} group by news_title, image_url order by news_date desc limit 20)a union select * from (SELECT post_id, s.entity_id, post_source,  null as news_description_url,  message, picture as image_url,cretated_time as news_date, cretated_time, ci.celebrity_name,ulm.activity_type_id FROM anv_celebrity.entity_post s join anv_celebrity.celebrity_info ci on s.entity_id=ci.celebrity_id left join user_like_map ulm on ulm.post_id_or_news_id =s.post_id and user_id=#{user_id} group by message order by news_date desc limit 30)b";
	@Select(GET_LATEST_NEWS)
	public List<AdminModel> getLatestNews(@Param("user_id") int user_id);*/
	
	public String GET_NEWS_LIKE_COUNT="SELECT count(post_id_or_news_id) as like_count,post_id_or_news_id FROM user_like_map u"+
									" where activity_type_id = #{activity_type_id} group by post_id_or_news_id;";
	@Select(GET_NEWS_LIKE_COUNT)
	public List<AdminModel> getNewsLike_count(@Param("activity_type_id") int activity_type_id);
	
	public String GET_NEWS_COMMENTS="SELECT uc.*,uc.id as comment_id, concat(u.first_name,' ',u.last_name)as name FROM user_comments_map uc "+
							" join user_information u on uc.user_id = u.user_id "+
							" where post_id_or_news_id = #{post_id_or_news_id} and activity_type_id=#{activity_type_id} order by date desc";
	@Select(GET_NEWS_COMMENTS)
	public List<AdminModel> getNews_Comments(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_type_id") int activity_type_id);
	
	public String GET_COMMENT_COUNT="SELECT u.post_id_or_news_id,count(post_id_or_news_id) as comment_count,activity_type_id FROM user_comments_map u "+
									" group by post_id_or_news_id";
	@Select(GET_COMMENT_COUNT)
	public List<AdminModel> getComment_Count();
	
	//public String GET_SORTED_CELEBRITY_INFO="SELECT * FROM celebrity_info c where celebrity_name like #{latter}";
//	public String GET_SORTED_CELEBRITY_INFO="SELECT distinct(c.celebrity_id)as celebrity_id,c.*,u.user_id as follower_id FROM celebrity_info c "+
//				" left join user_follower_map u on c.celebrity_id=u.entity_id and u.user_id=#{user_id} "+
//				" where celebrity_name like #{latter}";
	public String GET_SORTED_CELEBRITY_INFO="SELECT count(c.celebrity_id)as follower_count, c.*,u.user_id as follower_id,u.id FROM celebrity_info c "+
								" left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} "+
								" left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 "+
								" where celebrity_name like #{latter}"+
								" group by c.celebrity_id";
	@Select(GET_SORTED_CELEBRITY_INFO)
	public List<AdminModel> getCelebrityInfo_bySorted(@Param("latter") String latter,@Param("user_id") int user_id);
	
	
	public String GET_SORTED_CELEBRITY_INFO_BY_CATEGORY_ID="SELECT count(c.celebrity_id)as follower_count, c.*,u.user_id as follower_id FROM celebrity_info c "+
			 												" left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} "+
			 												" left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 "+
			 												" join celebrity_category_map ccm on ccm.celebrity_id =c.celebrity_id "+
			 												" where ccm.category_id=#{category_id} and celebrity_name like #{latter}"+
			 												" group by c.celebrity_id;";
	@Select(GET_SORTED_CELEBRITY_INFO_BY_CATEGORY_ID)
	public List<AdminModel> getSortedCelebrityInfo_ByCategoryId(@Param("latter") String latter,@Param("user_id") int user_id,@Param("category_id") int category_id);
	
	public String GET_SORTED_CELEBRITY_FOLLOWER_COUNT="SELECT entity_id as celebrity_id,count(entity_id)as follower_count FROM user_follower_map u "+
											" join celebrity_info c on c.celebrity_id=u.entity_id and celebrity_name like #{letter}"+ 
											" group by entity_id;";
	@Select(GET_SORTED_CELEBRITY_FOLLOWER_COUNT)
	public List<AdminModel> getCelebrityFollowerCount_sorted(@Param("letter") String letter);
	
	public String GET_CELEBRITY_PROFILE="SELECT count(u1.entity_id)as follower_count, c.*,u.user_id as follower_id, u.id FROM celebrity_info c "+
									" left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} "+
									" left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 "+
									" where c.celebrity_id=#{celebrity_id}"+
									" group by c.celebrity_id";
	@Select(GET_CELEBRITY_PROFILE)
	List<AdminModel> getCelebrity_Profile(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id);
	
//	public String GET_CELEBRITY_NEWS_BY_ID="SELECT * FROM entity_news e where entity_id=#{entity_id};";
	/*public String GET_CELEBRITY_NEWS_BY_ID="SELECT e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.news_date, e.publication_date, e.image_url FROM entity_news e "+
											" where  e.entity_id=#{entity_id} "+
											" union "+
											" SELECT ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,null as news_description_url,date as news_date, cretated_time as publication_date, ep.picture as image_url "+
											" FROM entity_post ep "+
											" where ep.entity_id = #{entity_id} order by news_date desc" */;
	public String GET_CELEBRITY_NEWS_BY_ID="SELECT e.news_id,e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url,ul.user_id,ul.activity_type_id FROM entity_news e "+
										" left join user_like_map ul on ul.post_id_or_news_id=e.news_id and ul.activity_type_id=1 and ul.user_id=#{user_id} "+
											 	" where  e.entity_id=#{entity_id} "+
											 	" union "+
										" SELECT ep.post_id as news_id,ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,null as news_description_url,cretated_time as news_date, DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url,ul2.user_id,ul2.activity_type_id "+
										" FROM entity_post ep "+ 
										" left join user_like_map ul2 on ul2.post_id_or_news_id=ep.post_id and ul2.activity_type_id=2 and ul2.user_id=#{user_id} "+
											" where ep.entity_id = #{entity_id} order by news_date desc limit #{limit} offset #{offset}";
	@Select(GET_CELEBRITY_NEWS_BY_ID)
	public List<AdminModel> getCelebrityNews_byCelebrityId(@Param("entity_id") int entity_id,@Param("user_id") int user_id,@Param("limit") int limit,@Param("offset") int offset);
	
	public String GET_CELEBRITY_ONLY_NEWS="SELECT e.news_id,e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url,ul.user_id,ul.activity_type_id FROM entity_news e "+
									" left join user_like_map ul on ul.post_id_or_news_id=e.news_id and ul.activity_type_id=1 and ul.user_id=#{user_id} "+
										" where  e.entity_id=#{entity_id} order by publication_date desc limit #{limit} offset #{offset}";
	@Select(GET_CELEBRITY_ONLY_NEWS)
	public List<AdminModel> getOnlyNews(@Param("entity_id") int entity_id,@Param("user_id") int user_id,@Param("limit") int limit,@Param("offset") int offset);
	
	
	/*public String GET_TRENDING_CELEBRITY="SELECT count(entity_id) as like_count,entity_id, TRIM(publication_date) as news_date FROM entity_news e "+ 
										" where substring(publication_date,1,10) = curdate() "+
										" group by entity_id order by like_count desc";*/
	//public String GET_TRENDING_CELEBRITY="SELECT count(entity_id) as like_count,ci.celebrity_id FROM entity_news e join celebrity_info ci on e.entity_id=ci.celebrity_id where substring(publication_date,1,10) = (curdate() - INTERVAL 1 DAY) and (ci.celebrity_of_the_day_date IS NULL or DATEDIFF(curdate(),ci.celebrity_of_the_day_date) > #{celebCount}) group by entity_id order by like_count desc limit 1";
	public String GET_TRENDING_CELEBRITY="SELECT count(entity_id) as like_count,entity_id, TRIM(publication_date) as news_date FROM entity_news e "+
                                         " where date(publication_date)=date(now())- INTERVAL #{day_interval} DAY "+
                                         " group by entity_id order by like_count desc";
	@Select(GET_TRENDING_CELEBRITY)
	public List<AdminModel> getTrending_celebrities(@Param("day_interval") int day_interval);
	
	public String GET_TRENDING_NEWS="SELECT  e.*,ci.profile_photo_path,ul.user_id,ul.user_id, uf.user_id as follower_id FROM entity_news e " +
			" join celebrity_info ci on ci.celebrity_id= e.entity_id and e.entity_type_id=1"+
			" left join user_follower_map uf on uf.entity_id = e.entity_id and uf.user_id = #{user_id} and uf.entity_type_id=1"+
			" left join user_like_map ul on ul.post_id_or_news_id = e.news_id and ul.user_id=#{user_id} "+
			" where e.entity_id=#{celebrity_id} and publication_date =#{news_date} limit 1";
	@Select(GET_TRENDING_NEWS)
	public List<AdminModel> getTrendingCelebrity_news(@Param("celebrity_id") int celebrity_id,@Param("news_date") String news_date,@Param("user_id") int user_id);
	
	
//	public String CELEBRITY_MAY_KNOW="SELECT count(cm.entity_id) as follower_count, c.*,cm1.user_id as follower_id FROM celebrity_info c "+
//								" left join user_follower_map cm on c.celebrity_id = cm.entity_id "+
//								" left join user_follower_map cm1 on c.celebrity_id = cm1.entity_id and cm1.user_id=#{user_id} "+
//								" where celebrity_id !=#{celebrity_id} group by cm.entity_id  order by celebrity_id desc limit 7";
//	public String CELEBRITY_MAY_KNOW="SELECT  c.*, cm.user_id as follower_id FROM celebrity_info c "+
//			" left join user_follower_map cm on c.celebrity_id = cm.entity_id and cm.user_id=#{user_id} and entity_type_id=1 "+
//			" where celebrity_id !=#{celebrity_id};";
	public String CELEBRITY_MAY_KNOW="SELECT count(u1.entity_id)as follower_count, c.*,u.user_id as follower_id, u.id FROM celebrity_info c "+
								" left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} "+
								" left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 "+
								" where celebrity_id!=#{celebrity_id} and celebrity_id IN (select celebrity_id from anv_celebrity.celebrity_category_map where category_id=#{cat_id})"+
								" group by c.celebrity_id order by RAND() limit 5";
	@Select(CELEBRITY_MAY_KNOW)
	public List<AdminModel> Celebrity_youMayKnow(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id,@Param("cat_id") int cat_id);
	
	public String GET_FACEBOOK_POST="SELECT ep.post_id as news_id,ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,null as news_description_url,ep.date as news_date,DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url,ul.user_id "+
							" FROM entity_post ep "+
							" left join user_like_map ul on ul.post_id_or_news_id=ep.post_id and ul.activity_type_id = 2 and ul.user_id=#{user_id}"+
											" where ep.entity_id = #{entity_id} and entity_type_id=1 and ep.post_source=#{post_source}"+
										" order by news_date desc limit #{limit} offset #{offset}";
	@Select(GET_FACEBOOK_POST)
	public List<AdminModel> getSocialMedia_post(@Param("entity_id") int entity_id,@Param("user_id") int user_id,@Param("post_source") String post_source,@Param("limit") int limit,@Param("offset") int offset);
	
	public String GET_CELEBRITY_CATEGORY="SELECT * FROM celebrity_category_map c "+
									" join celebrity_category cc on cc.category_id = c.category_id "+
									" where celebrity_id = #{celebrity_id} ";
	@Select(GET_CELEBRITY_CATEGORY)
	public List<AdminModel> getCelebritycategory_profile(@Param("celebrity_id") int celebrity_id);
	
	public String GET_USER_SEARCHES_INFO="SELECT * FROM user_searches u where search_key=#{search_key}";
	@Select(GET_USER_SEARCHES_INFO)
	public List<AdminModel> getUser_Searches(@Param("search_key") String search_key);
	
	public String ADD_NEW_USER_SEARCH="insert into user_searches(search_key, search_count)values(#{search_key}, #{search_count})";
	@Insert(ADD_NEW_USER_SEARCH)
	public void insertNewUser_searches(@Param("search_key") String search_key,@Param("search_count") int search_count);
	
	public String UPDATE_NEW_USER_SEARCH="update user_searches set search_count=#{search_count} where id=#{search_id}";
	@Update(UPDATE_NEW_USER_SEARCH)
	public void updateNewUser_searches(@Param("search_count") int search_count,@Param("search_id") int search_id);
	
	
//	public String GET_MOVIE_BLOG="SELECT m.blog_id,m.blog_title,m.movie_blogs,ci.profile_photo_path FROM movie_blogging m join celebrity_info ci on m.movie_id=ci.celebrity_id order by m.create_date desc limit 3;";
	public String GET_MOVIE_BLOG="SELECT m.blog_id,m.blog_title,m.movie_blogs,m.blog_image,m.movie_blogs FROM movie_blogging m order by m.create_date desc limit 3;";
	@Select(GET_MOVIE_BLOG)
	public List<AdminModel> getMovies_blogs();
	
//	public String GET_ALL_BLOG="SELECT m.blog_id,m.blog_title,m.movie_blogs,ci.profile_photo_path FROM movie_blogging m join celebrity_info ci on m.movie_id=ci.celebrity_id order by m.create_date desc;";
	public String GET_ALL_BLOG="SELECT m.blog_id,m.blog_title,m.movie_blogs,m.blog_image FROM movie_blogging m order by m.create_date desc;";
	@Select(GET_ALL_BLOG)
	public List<AdminModel> getAllBlogs();
	
	//public String BLOG_DETAILS="SELECT m.blog_id,m.blog_title,m.movie_blogs,m.create_date,ci.profile_photo_path FROM movie_blogging m join celebrity_info ci on m.movie_id=ci.celebrity_id where blog_id=#{blog_id}";
	public String BLOG_DETAILS="SELECT m.blog_id,m.blog_title,m.movie_blogs,m.create_date,m.blog_image FROM movie_blogging m where blog_id=#{blog_id}";
	@Select(BLOG_DETAILS)
	public List<AdminModel> getBlogDetails(int blog_id);
	
	public String MORE_BLOGS="SELECT m.blog_id,m.blog_title,m.movie_blogs,m.blog_image FROM movie_blogging m where blog_id!=#{blog_id} order by m.create_date desc limit 3;";
//	public String MORE_BLOGS="SELECT m.blog_id,m.blog_title,m.movie_blogs,ci.profile_photo_path FROM movie_blogging m join celebrity_info ci on m.movie_id=ci.celebrity_id where blog_id!=#{blog_id} order by m.create_date desc limit 3;";
	@Select(MORE_BLOGS)
	public List<AdminModel> getMoreBlogs(int blog_id);
	
	public String GET_CELEBRITY_INFO_BY_CATEGORY_ID="SELECT count(c.celebrity_id)as follower_count, c.*,u.user_id as follower_id,u.id FROM celebrity_info c "+
				" left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} "+
				" left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 "+
				" join celebrity_category_map ccm on ccm.celebrity_id =c.celebrity_id "+
				" where ccm.category_id=#{category_id}"+
				" group by c.celebrity_id;";
@Select(GET_CELEBRITY_INFO_BY_CATEGORY_ID)
	public List<AdminModel> getCelebrityInfo_ByCategoryId(@Param("user_id") int user_id,
			@Param("category_id") int category_id);

public String GET_CELEBRITY_FOLLOWER_COUNT="SELECT entity_id as celebrity_id,count(entity_id)as follower_count FROM user_follower_map u "+
		" join celebrity_info c on c.celebrity_id=u.entity_id"+ 
		" group by entity_id;";
@Select(GET_CELEBRITY_FOLLOWER_COUNT)
public List<AdminModel> getCelebrityFollowerCount();


public String GET_SAVED_DATA="SELECT id FROM entity_post e where id=#{id}";
@Select(GET_SAVED_DATA)
public List<AdminModel> get_saved_data(@Param("id") String id);

public String INSERT_FEEDS="insert into entity_post(entity_id, entity_type_id, post_source, picture, name, message, story, cretated_time,id)" +
		" values(#{entity_id}, #{entity_type_id}, #{post_source}, #{picture}, #{name}, #{message}, #{story}, #{cretated_time}, #{id})";
@Insert(INSERT_FEEDS)
public int insert_feeds(@Param("entity_id") int entity_id, @Param("entity_type_id") int entity_type_id, @Param("post_source") String post_source, @Param("picture") String picture, @Param("name") String name, @Param("message") String message, @Param("story") String story, @Param("cretated_time") String cretated_time, @Param("id") String id);

public String GET_TOCKEN="SELECT token FROM access_token a";
@Select(GET_TOCKEN)
public List<AdminModel> getToken();

public String GET_INSTA_POST_ID="SELECT id as instagram_id FROM entity_post e where post_source='Instagram'";
@Select(GET_INSTA_POST_ID)
public List<AdminModel> getInstaPostId();

public String GET_FEED_SOURCE_ID="SELECT id FROM entity_post e where post_source=#{post_source} and (id =#{id}) or (message=#{message} and picture=#{pic})";
@Select(GET_FEED_SOURCE_ID)
public List<AdminModel> getFeedSourceId(@Param("post_source") String post_source,@Param("id") String id,@Param("message") String message,@Param("pic") String pic);

public String GET_BLACKLIST_WEBSITE="SELECT website_name FROM blacklist_website b";
@Select(GET_BLACKLIST_WEBSITE)
public List<AdminModel> getBlackListWebsite();

public String GET_NEWS_ID="SELECT news_id FROM entity_news e where news_description_url like #{news_source} or (news_title=#{msg} and image_url=#{pic})";
@Select(GET_NEWS_ID)
public List<AdminModel> getNewsId(@Param("news_source") String news_source,@Param("msg") String msg,@Param("pic") String pic);

public String ADD_ENTITY_NEWS="insert into entity_news(entity_id, entity_type_id, news_source, news_author, news_title, news_description_url, news_description, publication_date,image_url)" +
					        " values(#{entity_id}, #{entity_type_id}, #{news_source}, #{news_author}, #{news_title}, #{news_description_url}, #{news_description}, #{publication_date}, #{image_url})";
@Insert(ADD_ENTITY_NEWS)
public int addEntityNews(AdminModel ad);

public String UPDATE_ACCESS_TOKEN="update access_token set token=#{token} where token_id=1";
@Update(UPDATE_ACCESS_TOKEN)
public int updateAccessTocken(@Param("token") String token);

public String ALL_NEW_POST_COUNT="SELECT e.news_id FROM entity_news e where  e.entity_id=#{entity_id} union SELECT ep.post_id FROM entity_post ep where ep.entity_id = #{entity_id}";
@Select(ALL_NEW_POST_COUNT)
public List<AdminModel> getAllNewsPostCount(@Param("entity_id") int entity_id);

public String ALL_POSTS_COUNT="SELECT count(ep.post_id) FROM entity_post ep where ep.entity_id = #{entity_id} and ep.post_source=#{post_source}";
@Select(ALL_POSTS_COUNT)
public int getAllPostsCount(@Param("entity_id") int entity_id, @Param("post_source") String post_source);

public String ALL_NEWS_COUNT="select count(e.news_id) as news_count from entity_news e where e.entity_id = #{entity_id}";
@Select(ALL_NEWS_COUNT)
public List<AdminModel> getAllNewsCount(@Param("entity_id") int entity_id);

public String GET_ALL_CELEBRITY="SELECT count(c.celebrity_id)as follower_count, c.*,u.user_id as follower_id,u.id FROM celebrity_info c "+
								" left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} "+
								" left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 "+
								" group by c.celebrity_id";
@Select(GET_ALL_CELEBRITY)
public List<AdminModel> getAllCelebrities(@Param("user_id") int user_id);

public String CELEBRITY_NEWS="SELECT e.news_id,e.entity_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url FROM entity_news e where e.entity_id=#{user_id} and substring(publication_date,1,10) = DATE(NOW() - INTERVAL 1 DAY) union SELECT ep.post_id as news_id,ep.entity_id,ep.post_source as news_source,ep.message as news_title,null as news_description_url,cretated_time as news_date, DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url FROM entity_post ep where ep.entity_id=#{user_id} and substring(cretated_time,1,10) = DATE(NOW() - INTERVAL 1 DAY) order by news_date desc";
@Select(CELEBRITY_NEWS)
public List<AdminModel> getTodaysNewsforCelebrity(@Param("user_id") int user_id);

public String GET_FOLLOWER="SELECT u.user_id,u.email_id FROM user_login u join user_follower_map uf on u.user_id=uf.user_id group by user_id";
@Select(GET_FOLLOWER)
public List<AdminModel> getFollowers();

public String GET_CELEBRITY_BY_FOLLOWER="SELECT um.entity_id,ci.celebrity_name FROM user_follower_map um join celebrity_info ci on um.entity_id=ci.celebrity_id where user_id=#{user_id}";
@Select(GET_CELEBRITY_BY_FOLLOWER)
public List<AdminModel> getCelebrityListByFollower(int user_id);

public String GET_CELEBRITY_COUNT="select count(celebrity_id) from celebrity_info";
@Select(GET_CELEBRITY_COUNT)
public int getTotalCelebrityCount();

public String UPDATE_CELEBRITYOFTHEDAY="update celebrity_info set celebrity_of_the_day=1, celebrity_of_the_day_date=curdate() where celebrity_id=#{celeb_id}";
@Update(UPDATE_CELEBRITYOFTHEDAY)
public int insertCelebrityoftheDay(int celeb_id);

public String REMOVE_CELEBRITYOFTHEDAY="update celebrity_info set celebrity_of_the_day=0 where celebrity_of_the_day=1";
@Update(REMOVE_CELEBRITYOFTHEDAY)
public int removeCelebrityoftheDay();

public String GET_CELEBRITYOFTHEDAY_COUNT="SELECT count(entity_id) as like_count,ci.celebrity_id FROM entity_news e join celebrity_info ci on e.entity_id=ci.celebrity_id where substring(publication_date,1,10) = (curdate() - INTERVAL 1 DAY) and (ci.celebrity_of_the_day_date IS NULL or DATEDIFF(curdate(),ci.celebrity_of_the_day_date) > #{celebCount}) group by entity_id order by like_count desc limit 1";
@Select(GET_CELEBRITYOFTHEDAY_COUNT)
public List<AdminModel> getTrending_celebrities1(int totalCelebCount);

public String GET_CELEBRITY_OF_THE_DAY="select celebrity_id from anv_celebrity.celebrity_info where celebrity_of_the_day=1";
@Select(GET_CELEBRITY_OF_THE_DAY)
public List<AdminModel> getCelebrityOfTheDayId();

public String GET_USER_NAME="select first_name, last_name from anv_celebrity.user_information where email_id=#{email_id}";
@Select(GET_USER_NAME)
public List<CommonModel> getUserName(@Param("email_id") String email_id);

public String UPDATE_FORGOTED_PASSWORD="update anv_celebrity.user_login set password=#{password} where email_id=#{email_id}";
@Update(UPDATE_FORGOTED_PASSWORD)
public int updateForgotedPassword(@Param("email_id") String email_id,@Param("password") String password);

public String GET_ALL_LIKE="SELECT count(id) as like_count, u.* FROM user_like_map u group by u.post_id_or_news_id";
@Select(GET_ALL_LIKE)
public List<AdminModel> getAllLike_count();

public String GET_ALL_CELEBRITY_FOLLOWER_COUNT="SELECT entity_id as celebrity_id,count(entity_id)as follower_count FROM user_follower_map u join celebrity_info c on c.celebrity_id=u.entity_id group by entity_id";
@Select(GET_ALL_CELEBRITY_FOLLOWER_COUNT)
public List<AdminModel> getAllCelebrityFollowers();

public String GET_MOVIE_PROFILE_NEWS="SELECT e.news_id,e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url,ul.user_id,ul.activity_type_id FROM entity_news e left join user_like_map ul on ul.post_id_or_news_id=e.news_id and ul.activity_type_id=1 and ul.user_id=#{user_id} where  e.entity_id in (select celebrity_id from movie_cast_map where movie_id=#{cid}) union SELECT ep.post_id as news_id,ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,null as news_description_url,cretated_time as news_date, DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url,ul2.user_id,ul2.activity_type_id FROM entity_post ep left join user_like_map ul2 on ul2.post_id_or_news_id=ep.post_id and ul2.activity_type_id=2 and ul2.user_id=#{user_id} where ep.entity_id=#{cid} order by news_date desc limit #{limit} offset #{offset}";
@Select(GET_MOVIE_PROFILE_NEWS)
public List<AdminModel> getCelebrityNewsMovies_byCelebrityId(@Param("cid") int cid,
		@Param("user_id") int user_id,@Param("limit") int limit,@Param("offset") int offset);

public String GET_MOVIE_CAST="select ci.celebrity_id,ci.celebrity_name,ci.profile_photo_path,ufm.user_id as follower_id from celebrity_info ci join movie_cast_map mm on ci.celebrity_id=mm.celebrity_id left join user_follower_map ufm on ci.celebrity_id=ufm.entity_id and ufm.user_id=#{user_id} where mm.movie_id=#{cid} group by ci.celebrity_id";
@Select(GET_MOVIE_CAST)
public List<AdminModel> getMovieCast(@Param("cid") int cid,@Param("user_id") int user_id);

public String GET_MOVIE_CAST_CHARACTER="select caracter_name from movie_cast_map mm join movie_cast_master ci on mm.caracter=ci.id where mm.movie_id=#{cid} and mm.celebrity_id=#{celebrity_id}";
@Select(GET_MOVIE_CAST_CHARACTER)
public List<AdminModel> getMovieCastCharacter(@Param("cid") int cid,@Param("celebrity_id") int celebrity_id);

public String GET_FOLLOWER_COUNT="select count(entity_id) as follower_count  from user_follower_map where entity_id=#{celebrity_id} group by entity_id";
@Select(GET_FOLLOWER_COUNT)
public List<AdminModel> getUserFollowerCount(@Param("celebrity_id") int celebrity_id);

public String YOU_MAY_KNOW_FOR_MOVIE="SELECT count(u1.entity_id)as follower_count, c.*,u.user_id as follower_id, u.id FROM celebrity_info c left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 where celebrity_id!=#{cid} and celebrity_id not in (select celebrity_id from movie_cast_map where movie_id=#{cid}) group by c.celebrity_id limit 5";
@Select(YOU_MAY_KNOW_FOR_MOVIE)
public List<AdminModel> Celebrity_youMayKnowforMovie(@Param("cid") int cid,@Param("user_id") int user_id);

public String YOU_MAY_KNOW_RELATED_MOVIE="SELECT count(u1.entity_id)as follower_count, c.*,u.user_id as follower_id, u.id FROM celebrity_info c left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 where celebrity_id!=#{cid} and celebrity_id in (select celebrity_id from movie_cast_map where movie_id=#{movie_id}) group by c.celebrity_id limit 5";
@Select(YOU_MAY_KNOW_RELATED_MOVIE)
public List<AdminModel> Celebrity_youMayKnowRelatedMovie(@Param("cid") int cid,@Param("user_id") int user_id,@Param("movie_id") int movie_id);

public String ALL_NEWS_COUNT_MOVIE="select count(e.news_id) as news_count from entity_news e where e.entity_id in (select celebrity_id from movie_cast_map where movie_id=#{cid})";
@Select(ALL_NEWS_COUNT_MOVIE)
public List<AdminModel> getAllNewsCountMovie(@Param("cid") int cid);

public String GET_ONLY_NEWS_MOVIE="SELECT e.news_id,e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url,ul.user_id,ul.activity_type_id FROM entity_news e left join user_like_map ul on ul.post_id_or_news_id=e.news_id and ul.activity_type_id=1 and ul.user_id=#{user_id} where e.entity_id in (select celebrity_id from movie_cast_map where movie_id=#{cid}) order by news_date desc limit #{limit} offset #{offset}";
@Select(GET_ONLY_NEWS_MOVIE)
public List<AdminModel> getOnlyNewsMovie(@Param("cid") int cid,@Param("user_id") int user_id,@Param("limit") int limit,@Param("offset") int offset);

public String GET_SPECIFIC_ACTOR_MOVIES="select count(u1.entity_id)as follower_count,u.user_id as follower_id, u.id,c.* from celebrity_info c "+
										" left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=#{user_id} "+
										" left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 "+
										" where c.celebrity_id in (select m.movie_id from anv_celebrity.movie_cast_map m where m.celebrity_id=#{celebrity_id}) group by celebrity_name order by celebrity_id desc limit 5";
@Select(GET_SPECIFIC_ACTOR_MOVIES)
public List<AdminModel> getSpecificActorMovies(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id);

public String GET_CARACTERWISE_CELEBRITY="SELECT ci.celebrity_name,e.news_id,e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url,ul.user_id,ul.activity_type_id FROM entity_news e "+
                                         " left join user_like_map ul on ul.post_id_or_news_id=e.news_id and ul.activity_type_id=1 and ul.user_id=#{user_id} "+
		                                 " left join celebrity_info ci on ci.celebrity_id=e.entity_id "+
                                         " where  e.entity_id=#{entity_id} "+
                                         " union "+
		                                 " SELECT ci.celebrity_name,ep.post_id as news_id,ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,null as news_description_url,cretated_time as news_date, DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url,ul2.user_id,ul2.activity_type_id FROM entity_post ep "+
                                         " left join user_like_map ul2 on ul2.post_id_or_news_id=ep.post_id and ul2.activity_type_id=2 and ul2.user_id=#{user_id} "+
		                                 " left join celebrity_info ci on ci.celebrity_id=ep.entity_id "+
                                         " where ep.entity_id=#{entity_id} order by news_date desc limit 10";
@Select(GET_CARACTERWISE_CELEBRITY)
public List<AdminModel> getCaracterwiseCelebrityNews(@Param("entity_id") int entity_id,@Param("user_id") int user_id);

public String GET_CARACTERWISE_CELEBRITY_INFO="select m.celebrity_id,c.celebrity_name,mc.caracter_name from movie_cast_map m left join celebrity_info c on c.celebrity_id=m.celebrity_id left join movie_cast_master mc on mc.id=m.caracter where movie_id=#{movie_id} and caracter=#{caracter}";
@Select(GET_CARACTERWISE_CELEBRITY_INFO)
public List<AdminModel> getCaracterwiseCelebrityInfo(@Param("movie_id") int movie_id, @Param("caracter") int caracter);

public String GET_CARACTERWISE_OTHER_CELEBRITY_INFO="select m.celebrity_id,c.celebrity_name,mc.caracter_name from movie_cast_map m left join celebrity_info c on c.celebrity_id=m.celebrity_id join movie_cast_master mc on mc.id=m.caracter where m.movie_id=#{movie_id} and m.caracter!=1 and m.caracter!=2 and m.caracter!=3 and m.caracter!=4";
@Select(GET_CARACTERWISE_OTHER_CELEBRITY_INFO)
public List<AdminModel> getCaracterwiseOtherCelebrityInfo(@Param("movie_id") int movie_id);

public String CHECK_FOR_MOVIE="select category_id from anv_celebrity.celebrity_category_map where celebrity_id=#{celebrity_id} and category_id=6";
@Select(CHECK_FOR_MOVIE)
public List<AdminModel> checkForMovieCategory(@Param("celebrity_id") int celebrity_id);

public String CELEBRITY_SUGGESTIONS="SELECT celebrity_name FROM anv_celebrity.celebrity_info c where celebrity_name like #{searched} order by celebrity_name";
@Select(CELEBRITY_SUGGESTIONS)
public List<AdminModel> get_celebrity_suggestions(@Param("searched") String searched);

public String ALL_CELEBRITY_SUGGESTIONS="SELECT celebrity_name FROM anv_celebrity.celebrity_info c order by celebrity_name";
@Select(ALL_CELEBRITY_SUGGESTIONS)
public List<AdminModel> get_all_celebrity_suggestions();

public String GET_ALL_USERS="SELECT * FROM user_login";
@Select(GET_ALL_USERS)
public List<CommonModel> getAllUsers();

public static final String UPDATE_PASS="update user_login set password=#{password} where user_id=#{user_id}";
@Update(UPDATE_PASS)
public int updatePassword(@Param("user_id") int user_id,@Param("password") String password);

public static final String GET_ALL_CELEBRITY_CATEGORIES="SELECT * FROM anv_celebrity.celebrity_category c order by category_name";
@Select(GET_ALL_CELEBRITY_CATEGORIES)
public List<AdminModel> get_all_celebrity_categories();

public static final String add_requested_celebrity="insert into requested_celebrity(celebrity_name, added_by) values(#{celebrity_name}, #{user_id})";
@Insert(add_requested_celebrity)
public int add_requested_celebrity(@Param("celebrity_name") String celebrity_name,@Param("user_id") int user_id);

public static final String GET_LAST_REQUESTED_CELEBRITY="SELECT requested_celebrity_id FROM anv_celebrity.requested_celebrity c where added_by=#{user_id} and celebrity_name=#{celebrity_name} order by requested_celebrity_id desc limit 1";
@Select(GET_LAST_REQUESTED_CELEBRITY)
public int get_last_requested_celebrity_id(@Param("user_id") int user_id,@Param("celebrity_name") String celebrity_name);

public static final String add_requested_celebrity_categories="insert into requested_celebrity_category_map(requested_celebrity_id, category_id) values(#{requested_celebrity_id}, #{category_id})";
@Insert(add_requested_celebrity_categories)
public int add_requested_celebrity_categories(@Param("requested_celebrity_id") int requested_celebrity_id,@Param("category_id") int category_id);

//SELECT count(u1.entity_id)as follower_count, c.*,u.user_id as follower_id, u.id FROM celebrity_info c left join user_follower_map u on u.entity_id=c.celebrity_id and u.entity_type_id=1 and u.user_id=1 left join user_follower_map u1 on u1.entity_id=c.celebrity_id and u1.entity_type_id=1 where celebrity_id!=15 and celebrity_id not in (select celebrity_id from movie_cast_map where movie_id=15) group by c.celebrity_id limit 5

//select ci.celebrity_id,ci.celebrity_name,ci.profile_photo_name from movie_cast_map mm join celebrity_info ci on mm.celebrity_id=ci.celebrity_id where mm.movie_id=15 group by ci.celebrity_id


//SELECT e.news_id,e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url FROM entity_news e where e.entity_id=16 and substring(news_date,1,10) = curdate() union SELECT ep.post_id as news_id,ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,null as news_description_url,cretated_time as news_date, DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url FROM entity_post ep where ep.entity_id = 16 and substring(date,1,10) = curdate() order by news_date desc
//SELECT e.news_id,e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url FROM entity_news e where (e.entity_id=12 or e.entity_id = 13) and substring(publication_date,1,10) = curdate() union SELECT ep.post_id as news_id,ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,null as news_description_url,cretated_time as news_date, DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url FROM entity_post ep where (ep.entity_id = 12 or ep.entity_id = 13)  and substring(cretated_time,1,10) = curdate() order by news_date desc

//SELECT e.news_id,e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.publication_date as news_date,DATE_FORMAT(e.publication_date, '%d %b  %H:%i') publication_date, e.image_url FROM entity_news e where e.entity_id in('12','13') and substring(publication_date,1,10) = curdate() union SELECT ep.post_id as news_id,ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,null as news_description_url,cretated_time as news_date, DATE_FORMAT(cretated_time, '%d %b  %H:%i') as publication_date, ep.picture as image_url FROM entity_post ep where ep.entity_id in('12','13') and substring(cretated_time,1,10) = curdate() order by news_date desc


	/*
	 * New query for celebrity
	 * 
	 * public String GET_LATEST_NEWS="SELECT news_date,e.* FROM entity_news e "+
							" where substring(news_date,1,10) = curdate() "+
							" group by news_date order by news_date desc;";*/
	
	/*
	 * trending will be decided by count 
	 * 
	 * SELECT count(entity_id),entity_id FROM entity_news e
	where substring(news_date,1,10) = curdate()
	group by entity_id*/
	
	/*
	 * Get facebook & news at a time 02/10/2017
	 * 
	 * 
	 * 
	 * SELECT e.entity_id, e.entity_type_id, e.news_source, e.news_title, e.news_description_url ,e.news_date, e.publication_date, e.image_url, ci.profile_photo_path, uf.user_id
	 as follower_id FROM entity_news e
							 join celebrity_info ci on ci.celebrity_id = e.entity_id and e.entity_type_id=1
							 left join user_follower_map uf on uf.entity_id = e.entity_id and uf.user_id = 1 and uf.entity_type_id=1
							  where substring(e.news_date,1,10) = curdate()

	union
	SELECT ep.entity_id, ep.entity_type_id, ep.post_source as news_source,ep.message as news_title,ep.story as news_description_url,date as news_date, cretated_time as publication_date, ep.picture as image_url, name as profile_photo,entity_type_id as follower_id FROM entity_post ep
	group by news_date order by news_date desc;*/
	
}

