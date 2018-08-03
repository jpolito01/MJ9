package common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import common.model.CommonModel;

import admin.model.AdminModel;

public interface CommonMapper {
	
/*Added By Snigdha*/	  
			public String GET_TOTAL_NEWS_COUNT="SELECT count(news_id)as news_count FROM celebrity_news c;";
			@Select(GET_TOTAL_NEWS_COUNT)
			public int getTotalNews_Count();
			
			
			public String GET_LATEST_NEWS="SELECT count(news_id) as news_count,c.celebrity_id FROM celebrity_news c "+
						" where date(publication_date)=date(now())- INTERVAL #{day_interval} DAY group by celebrity_id order by news_count desc";
			@Select(GET_LATEST_NEWS)
			public List<AdminModel> getPopularNews(@Param("day_interval")int day_interval);

	
			public String GET_NEWS_COUNT="SELECT count(news_id) as news_count,c.celebrity_id FROM celebrity_news c "+
												"  where substr(publication_date,1,10)=substr(now(),1,10) group by celebrity_id order by news_count";
			@Select(GET_NEWS_COUNT)
			public List<AdminModel> getNewsCount();
	
			public String GET_TOP_NEWS="SELECT c.news_description,c.news_id,c.news_title,c.news_source,c.celebrity_id,ci.party_id,ci.profile_photo_path FROM celebrity_news c "+
						" join celebrity_info ci on ci.celebrity_id = c.celebrity_id "+
						" order by publication_date desc limit 4";
			@Select(GET_TOP_NEWS)
			public List<AdminModel> getTop_news();
			
			public String POPULAR_FOUR_NEWS="SELECT c.news_id,c.news_title,c.news_source,c.celebrity_id,ci.party_id,ci.profile_photo_path FROM celebrity_news c join celebrity_info ci on ci.celebrity_id = c.celebrity_id group by c.news_title order by publication_date desc limit 4 offset 4";
			@Select(POPULAR_FOUR_NEWS)
			public List<AdminModel> getPopularFour_news();
			
		/*	public String GET_ALL_POST_NEWS="SELECT ci.party_id,news_id, c.celebrity_id, news_source,  news_description_url, news_title, image_url,publication_date as news_date, publication_date FROM celebrity_news c "+
																" join celebrity_info ci on ci.celebrity_id=c.celebrity_id "+
																" where substr(publication_date,1,10)=substr(now(),1,10) "+
																" union "+
																" SELECT ci.party_id, post_id, s.celebrity_id, post_source,  null as news_description_url,  message, image_url,created_time as news_date, created_time FROM social_media_post s "+
																"join celebrity_info ci on s.celebrity_id=ci.celebrity_id"+
																" where substr(created_time,1,10)=substr(now(),1,10) "+
																" order by news_date desc limit 50" ;*/
			public String GET_ALL_POST_NEWS="select * from (SELECT ci.party_id,news_id, c.celebrity_id, news_source,  news_description_url, news_title, image_url,publication_date as news_date, publication_date,null as twitter_handle,null as celebrity_name FROM anv_rsbs.celebrity_news c join anv_rsbs.celebrity_info ci on ci.celebrity_id=c.celebrity_id group by news_title, image_url order by news_date desc limit 20)a union select * from (SELECT ci.party_id, post_id, s.celebrity_id, post_source,  null as news_description_url,  message, image_url,created_time as news_date, created_time, s.twitter_handle,ci.celebrity_name FROM anv_rsbs.social_media_post s join anv_rsbs.celebrity_info ci on s.celebrity_id=ci.celebrity_id group by message order by news_date desc limit 30)b";  //order by news_date desc
			@Select(GET_ALL_POST_NEWS)
			public List<AdminModel> getAllPost_news();
			
			public String GET_ALL_POST_NEWS_BY_ID="SELECT ul.like_id,um.user_id as commented_user_id, ul.activity_id, ul.user_id, ci.party_id,news_id, c.celebrity_id, news_source,  news_description_url, news_title, image_url,c.publication_date as news_date, DATE_FORMAT(publication_date, '%d %b  %H:%i') publication_date,null as twitter_handle FROM celebrity_news c "+
					" join celebrity_info ci on ci.celebrity_id=c.celebrity_id "+
					" left join user_like_map ul on ul.user_id=#{user_id} and c.news_id = ul.post_id_or_news_id "+
					" left join user_comment_map um on um.user_id=#{user_id} and c.news_id = um.post_id_or_news_id "+
					" where  c.celebrity_id=#{celebrity_id} group by c.news_title,c.image_url" +
					" union "+
					" SELECT ul.like_id,um.user_id as commented_user_id, ul.activity_id, ul.user_id, ci.party_id, post_id, s.celebrity_id, post_source, news_description_url,  message, image_url,s.created_time as news_date, DATE_FORMAT(created_time, '%d %b  %H:%i') as publication_date,s.twitter_handle  FROM social_media_post s "+
					" join celebrity_info ci on s.celebrity_id=ci.celebrity_id "+
					" left join user_like_map ul on ul.user_id=#{user_id} and s.post_id = ul.post_id_or_news_id "+
					" left join user_comment_map um on um.user_id=#{user_id} and s.post_id = um.post_id_or_news_id "+
					" where s.celebrity_id=#{celebrity_id} group by s.message"+
					" order by news_date desc limit #{limit} offset #{offset}" ;
			@Select(GET_ALL_POST_NEWS_BY_ID)
			public List<AdminModel> getAllPostnews_ById(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id, @Param("limit") int limit, @Param("offset") int offset);
			
			public String GET_ALL_SOCIAL_MEDIA_POST ="SELECT um.user_id as commented_user_id, ul.activity_id as liked_activity_id, ul.user_id, ci.party_id,s.post_id as news_id, s.celebrity_id, s.post_source as news_source, news_description_url,s.message as news_title, image_url,s.created_time as news_date,DATE_FORMAT(created_time, '%d %b  %H:%i') as publication_date,s.twitter_handle "+
							" from social_media_post s "+
							" join celebrity_info ci on ci.celebrity_id=s.celebrity_id "+
							" left join user_like_map ul on ul.user_id=#{user_id} and s.post_id = ul.post_id_or_news_id "+
							" left join user_comment_map um on um.user_id=#{user_id} and s.post_id = um.post_id_or_news_id "+
							" where s.post_source=#{post_source}  and s.celebrity_id=#{celebrity_id} group by s.message order by news_date desc limit #{limit} offset #{offset}";
			@Select(GET_ALL_SOCIAL_MEDIA_POST)
			public List<AdminModel> getAll_SocialMediaPost(@Param("post_source") String post_source,@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id, @Param("limit") int limit, @Param("offset") int offset);
			
			public String GET_ALL_NEWS="SELECT um.user_id as commented_user_id, ul.activity_id as liked_activity_id, ul.user_id, ci.party_id,c.news_id as news_id, c.celebrity_id, c.news_source, c.news_description_url, news_title, image_url,c.publication_date as news_date,DATE_FORMAT(publication_date, '%d %b  %H:%i') as publication_date FROM celebrity_news c "+
														"  join celebrity_info ci on ci.celebrity_id=c.celebrity_id  "+
														" left join user_like_map ul on ul.user_id=#{user_id} and c.news_id = ul.post_id_or_news_id "+
														" left join user_comment_map um on um.user_id=#{user_id} and c.news_id = um.post_id_or_news_id "+
														" where c.celebrity_id=#{celebrity_id} group by c.news_title,c.image_url order by news_date desc limit #{limit} offset #{offset}";
			@Select(GET_ALL_NEWS)
			public List<AdminModel> getAll_news(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id,@Param("limit") int limit, @Param("offset") int offset);
			
			public String GET_CELEBRITY_INFO="SELECT s.state_name,p.party_name,count(m.follow_id) as follower_count,m.user_id as follower_id,m.follow_id,c.* FROM celebrity_info c left join anv_rsbs.user_follower_map m on m.celebrity_id=c.celebrity_id and m.user_id=#{user_id} left join state_master s on s.state_id=c.state_id left join party_info p on p.party_id=c.party_id where c.celebrity_id=#{celebrity_id}";
			@Select(GET_CELEBRITY_INFO)
			public List<AdminModel> getCelebrity_info(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id);
			
			public String GET_SUGGESTED="SELECT distinct u.user_id as follower_id,u.follow_id,c.* FROM celebrity_info c left join user_follower_map u on u.celebrity_id=c.celebrity_id and u.user_id=#{user_id} where c.celebrity_id!=#{celebrity_id} order by u.user_id limit 5";
			@Select(GET_SUGGESTED)
			public List<AdminModel> getSuggested_politician(@Param("celebrity_id") int celebrity_id,@Param("user_id") int user_id);
/*Snigdha Kadam*/

		public String VALIDATE_LOGIN="SELECT ui.first_name,ui.last_name,u.* FROM user_login u left join user_information ui on ui.user_id=u.user_id where user_name=#{user_name} and password =#{password}";
		@Select(VALIDATE_LOGIN)
		public List<AdminModel> validate_email(@Param("user_name") String user_name,@Param("password") String password);
		
		public String ALL_POLITICIANS="select c.*,u.user_id as follower_id from celebrity_info c left join user_follower_map u on u.celebrity_id=c.celebrity_id and u.user_id=#{user_id} where celebrity_name like #{celebrity_name}";
		@Select(ALL_POLITICIANS)
		public List<AdminModel> getAllPoliticians(@Param("celebrity_name") String celebrity_name,@Param("user_id") int user_id);
		
		 public String GET_POLITICIAN_INFO_SEARCH="SELECT c.*,s.state_name,cc.categroy_name,m.user_id as follower_id,m.follow_id FROM anv_rsbs.celebrity_info c left join anv_rsbs.calebrity_category_map ca on ca.celebrity_id=c.celebrity_id left join anv_rsbs.state_master s on s.state_id=c.state_id left join anv_rsbs.celebrity_categories cc on cc.category_id=ca.category_id left join anv_rsbs.user_follower_map m on m.celebrity_id=c.celebrity_id and m.user_id=#{user_id} where c.celebrity_name like #{celebrity_name}";
	     @Select(GET_POLITICIAN_INFO_SEARCH)
	     public List<AdminModel> getPoliticiansForSearch(@Param("celebrity_name") String celebrity_name, @Param("user_id") int user_id);
		
		public String POLITITIAN_FOLLOWERS_COUNT="select count(c.celebrity_name) as follower_count,c.celebrity_id,u.user_id as follower_id from celebrity_info c left join user_follower_map u on u.celebrity_id=c.celebrity_id where celebrity_name like #{celebrity_name}";
		@Select(POLITITIAN_FOLLOWERS_COUNT)
		public List<AdminModel> getPoliticiansFollowersCount(@Param("celebrity_name") String celebrity_name);
		
		public String ADD_FOLLOWERS="insert into user_follower_map(user_id,celebrity_id,news_letter_type) values(#{user_id},#{celebrity_id},#{news_letter_type})";
		@Insert(ADD_FOLLOWERS)
		public int addPoliticianFollowers(@Param("user_id") int user_id,@Param("celebrity_id") int celebrity_id,@Param("news_letter_type") int news_letter_type);

		//10-11-2017 priti
		public String GET_USER_INFORMATION="select * from anv_rsbs.user_information where user_id=#{user_id}";
        @Select(GET_USER_INFORMATION)
        public List<CommonModel> getUserInformation(@Param("user_id") int user_id);
        
        public String GET_EXISTING_USER="select user_id,email_id,first_name,last_name from anv_rsbs.user_information where email_id=#{email_id}";
        @Select(GET_EXISTING_USER)
        public List<CommonModel> getExistingUser(@Param("email_id") String email_id);
        
        public String UPDATE_USER_INFORMATION="update anv_rsbs.user_information set first_name=#{first_name}, last_name=#{last_name}, contact_no=#{contact_no}, email_id=#{email_id} where user_id=#{user_id}";
        @Update(UPDATE_USER_INFORMATION)
        public int updateUserInformation(@Param("user_id") int user_id,@Param("first_name") String first_name, @Param("last_name") String last_name,@Param("contact_no") String contact_no,@Param("email_id") String email_id);

        public String UPDATE_USER_CREDENTIALS="update anv_rsbs.user_login set user_name=#{user_name} where user_id=#{user_id}";
        @Update(UPDATE_USER_CREDENTIALS)
        public int updateUserCredentials(@Param("user_id") int user_id,@Param("user_name") String user_name);
        
        public String GET_PASSWORD="select password from anv_rsbs.user_login where user_id=#{user_id}";
        @Select(GET_PASSWORD)
        public String getPassword(@Param("user_id") int user_id);
        
        public String UPDATE_PASSWORD="update anv_rsbs.user_login set password=#{password} where user_id=#{user_id}";
        @Update(UPDATE_PASSWORD)
        public int updatePassword(@Param("user_id") int user_id,@Param("password") String password);
        
        public String ADD_NEW_USER="insert into anv_rsbs.user_information(first_name, last_name, email_id) values(#{first_name}, #{last_name}, #{email_id})";
        @Insert(ADD_NEW_USER)
        public int addNewUser(CommonModel model);
        
        public String ADD_USER_CREDENTIALS="insert into anv_rsbs.user_login(user_id,user_name, password, role_id) values(#{user_id},#{user_name}, #{password}, #{role_id})";
        @Insert(ADD_USER_CREDENTIALS)
        public int addUserCredentials(@Param("user_id") int user_id,@Param("user_name") String user_name,@Param("password") String password,@Param("role_id") int role_id);

        public String FOLLOWER_CELEBRITY_LIST="SELECT m.*,c.celebrity_name,c.party_id from anv_rsbs.user_follower_map m left join anv_rsbs.celebrity_info c on c.celebrity_id=m.celebrity_id where m.user_id=#{user_id}";
        @Select(FOLLOWER_CELEBRITY_LIST)
        public List<AdminModel> getUserFollowerList(@Param("user_id") int user_id);
        
        public String UNFOLLOW_CELEBRITY="delete from anv_rsbs.user_follower_map where follow_id=#{follow_id}";
        @Delete(UNFOLLOW_CELEBRITY)
        public int unfollowCelebrity(@Param("follow_id") int follow_id);
        
        //user searches
        public String ADD_USER_SEARCHES="insert into anv_rsbs.user_searches(search_key,count) values(#{search_key},#{count})";
        @Insert(ADD_USER_SEARCHES)
        public int addUserSearches(@Param("search_key") String search_key, @Param("count") int count);

        public String GET_USER_SEARCHES="select * from anv_rsbs.user_searches where search_key=#{search_key}";
        @Select(GET_USER_SEARCHES)
        public List<CommonModel> getUserSearches(@Param("search_key") String search_key);
        
        public String UPDATE_SEARCH_COUNT="update anv_rsbs.user_searches set count=#{count} where search_key=#{search_key}";
        @Update(UPDATE_SEARCH_COUNT)
        public int updateSearchCount(@Param("search_key") String search_key,@Param("count") int count);
        
        public String ADD_USER_LIKES="insert into anv_rsbs.user_like_map(user_id, post_id_or_news_id, activity_id) values(#{user_id}, #{post_id_or_news_id}, #{activity_id})";
        @Insert(ADD_USER_LIKES)
        public int addUserLikes(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);

        public String ADD_USER_COMMENTS="insert into anv_rsbs.user_comment_map(user_id, post_id_or_news_id, activity_id, comment) values(#{user_id}, #{post_id_or_news_id}, #{activity_id}, #{comment})";
        @Insert(ADD_USER_COMMENTS)
        public int addUserComments(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id,@Param("comment") String comment);

        public String GET_USER_LIKES_COUNT="select count(like_id) as like_count from anv_rsbs.user_like_map where post_id_or_news_id=#{post_id_or_news_id} and activity_id=#{activity_id}";
        @Select(GET_USER_LIKES_COUNT)
        public int getUserLikeCount(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
        
        public String GET_SPECIFIC_USER_LIKES="select count(like_id) as like_count,post_id_or_news_id,activity_id from anv_rsbs.user_like_map where activity_id=#{activity_id} group by post_id_or_news_id";
        @Select(GET_SPECIFIC_USER_LIKES)
        public List<CommonModel> getSpecificUserLikes(@Param("activity_id") int activity_id);

        public String GET_SPECIFIC_USER_COMMENTS="select a.*,u.user_id,u.first_name,u.last_name from anv_rsbs.user_comment_map a left join anv_rsbs.user_information u on u.user_id=a.user_id where a.post_id_or_news_id=#{post_id_or_news_id} and a.activity_id=#{activity_id} order by comment_id desc";
        @Select(GET_SPECIFIC_USER_COMMENTS)
        public List<CommonModel> getUserComments(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);

        public String DELETE_COMMENTS="delete from anv_rsbs.user_comment_map where comment_id=#{comment_id}";
        @Delete(DELETE_COMMENTS)
        public int deleteUserComments(@Param("comment_id") int comment_id);
        
        public String GET_USER_COMMENTS_COUNT="select count(comment_id) from anv_rsbs.user_comment_map  where post_id_or_news_id=#{post_id_or_news_id} and activity_id=#{activity_id}";
        @Select(GET_USER_COMMENTS_COUNT)
        public int getUserCommentsCount(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
        
        public String GET_SPECIFIC_NEWSPOST_COMMENT_COUNT="select count(comment_id) as comment_count,post_id_or_news_id,activity_id from anv_rsbs.user_comment_map where activity_id=#{activity_id} group by post_id_or_news_id";
        @Select(GET_SPECIFIC_NEWSPOST_COMMENT_COUNT)
        public List<CommonModel> getNewsPostCommentsCount(@Param("activity_id") int activity_id);

        public String GET_ALL_NEWSPOST_LIKES_COUNT="select count(like_id) as like_count,post_id_or_news_id,activity_id from anv_rsbs.user_like_map group by post_id_or_news_id";
        @Select(GET_ALL_NEWSPOST_LIKES_COUNT)
        public List<CommonModel> getAllNPLikesCount();
        
        public String GET_ALL_NEWSPOST_COMMENTS_COUNT="select count(comment_id) as comment_count,post_id_or_news_id,activity_id from anv_rsbs.user_comment_map group by post_id_or_news_id";
        @Select(GET_ALL_NEWSPOST_COMMENTS_COUNT)
        public List<CommonModel> getAllNPCommentsCount();
        
        public String DELETE_USER_LIKE="delete from user_like_map where like_id=#{like_id}";
        @Delete(DELETE_USER_LIKE)
        public int deleteUserLikes(@Param("like_id") int like_id);
        
        public String GET_CELEBRITY_FOLLOWERS_COUNT="select count(follow_id) as follower_count,m.celebrity_id from anv_rsbs.user_follower_map m group by celebrity_id";
        @Select(GET_CELEBRITY_FOLLOWERS_COUNT)
        public List<CommonModel> getCelebrityFollowersCount();
        
        public String GET_ALL_CELEBRITIES="SELECT celebrity_id, celebrity_name,  facebook_page, twitter_handle, instagram_page, isActive FROM celebrity_info c where isActive =1";
        @Select(GET_ALL_CELEBRITIES)
        public List<AdminModel> getAllCelebrities();
        
        public String GET_BLACKLIST_WEBSITE="SELECT web_id, website_name FROM blacklist_website b";
        @Select(GET_BLACKLIST_WEBSITE)
        public List<AdminModel> getBlacklistWebsites();
        
        public String POLITICIAN_OF_THE_DAY="SELECT s.state_name,p.party_name,count(m.follow_id) as follower_count,m.follow_id,m.user_id as follower_id,c.celebrity_name,c.party_id,c.profile_photo_path,c.state_id,c.politician_position,c.celebrity_id FROM celebrity_info c left join anv_rsbs.user_follower_map m on m.celebrity_id=c.celebrity_id and m.user_id=#{user_id} left join state_master s on s.state_id=c.state_id left join party_info p on p.party_id=c.party_id where celebrity_of_the_day=1";
        @Select(POLITICIAN_OF_THE_DAY)
        public List<AdminModel> politicianOfTheDay(@Param("user_id") int user_id);
        
        public String GET_ALL_STATES="select * from anv_rsbs.state_master order by state_name asc";
        @Select(GET_ALL_STATES)
        public List<AdminModel> getAllStates();
        
        public String GET_ALL_CATEGORIES="select * from anv_rsbs.celebrity_categories";
        @Select(GET_ALL_CATEGORIES)
        public List<AdminModel> getAllCategories();
        
        public String GET_STATEWISE_POLITICIAN_INFO="SELECT c.*,s.state_name,cc.categroy_name,m.user_id as follower_id,m.follow_id FROM anv_rsbs.celebrity_info c left join anv_rsbs.calebrity_category_map ca on ca.celebrity_id=c.celebrity_id left join anv_rsbs.state_master s on s.state_id=c.state_id left join anv_rsbs.celebrity_categories cc on cc.category_id=ca.category_id left join anv_rsbs.user_follower_map m on m.celebrity_id=c.celebrity_id and m.user_id=#{user_id} where c.state_id=#{state_id} and ca.category_id=#{category_id}";
        @Select(GET_STATEWISE_POLITICIAN_INFO)
        public List<AdminModel> getStatewisePoliticians(@Param("state_id") int state_id, @Param("category_id") int category_id, @Param("user_id") int user_id);

        public String GET_CELEBRITY_CATEGORY="select c.celebrity_id, c.category_id, a.categroy_name from anv_rsbs.calebrity_category_map c left join anv_rsbs.celebrity_categories a on a.category_id=c.category_id";
		@Select(GET_CELEBRITY_CATEGORY)
		public List<AdminModel> getCelebrityCategory();
		
		public String GET_NEWS="select * from anv_rsbs.celebrity_news where news_id=#{news_id}";
		@Select(GET_NEWS)
		public List<AdminModel> getNews(@Param("news_id") int news_id);
		
		public String GET_POSTS="select * from anv_rsbs.social_media_post where post_id=#{post_id}";
		@Select(GET_POSTS)
		public List<AdminModel> getPosts(@Param("post_id") int post_id);
		
		public String GET_RECENT_BLOGS="select * from anv_rsbs.blog_details order by blog_id desc limit 1";
		@Select(GET_RECENT_BLOGS)
		public List<AdminModel> getResentBlogs();
		
		/*public String GET_ALL_BLOGS = "select * from anv_rsbs.blog_details where blog_id!=#{blog_id} order by blog_id desc";
		@Select(GET_ALL_BLOGS)
		public List<AdminModel> getAllBlogs(@Param("blog_id") int blog_id);*/
		public String GET_ALL_BLOGS = "select * from anv_rsbs.blog_details order by blog_id desc";
		@Select(GET_ALL_BLOGS)
		public List<AdminModel> getAllBlogs();
		
		public String GET_BLOGS_BY_ID="select * from anv_rsbs.blog_details where blog_id=#{blog_id}";
		@Select(GET_BLOGS_BY_ID)
		public List<AdminModel> getBlogsById(@Param("blog_id") int blog_id);
		
		public String GET_RECENT_THREE_BLOGS="select * from anv_rsbs.blog_details where blog_id!=#{blog_id} order by blog_id desc limit 3";
		@Select(GET_RECENT_THREE_BLOGS)
		public List<AdminModel> getResentThreeBlogs(@Param("blog_id") int blog_id);
		
		public String GET_USER_LIKE_ID="select * from anv_rsbs.user_like_map where user_id=#{user_id} and post_id_or_news_id=#{post_id_or_news_id} and activity_id=#{activity_id}";
		@Select(GET_USER_LIKE_ID)
		public List<AdminModel> getUserLikeId(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);

		public String GET_TWO_USER_COMMENTS="select a.*,u.user_id,u.first_name,u.last_name from anv_rsbs.user_comment_map a left join anv_rsbs.user_information u on u.user_id=a.user_id where a.post_id_or_news_id=#{post_id_or_news_id} and a.activity_id=#{activity_id} order by comment_id desc limit 2";
        @Select(GET_TWO_USER_COMMENTS)
        public List<CommonModel> getUserTwoComments(@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);

        public String GET_SUGGESTED_UNFOLLOWED_POLITICIANS="SELECT distinct c.* FROM celebrity_info c where c.celebrity_id!=#{celebrity_id} and c.celebrity_id not in(select celebrity_id from user_follower_map where user_id=#{user_id}) limit 10";
        @Select(GET_SUGGESTED_UNFOLLOWED_POLITICIANS)
        public List<AdminModel> getSuggestedUnfollowedPoliticians(@Param("celebrity_id") int celebrity_id, @Param("user_id") int user_id);

        public String GET_ALL_POSTS_COUNT="SELECT news_id FROM celebrity_news c "+
					" join celebrity_info ci on ci.celebrity_id=c.celebrity_id "+
					" where  c.celebrity_id=#{celebrity_id} group by c.news_title,c.image_url"+
					" union "+
					" SELECT post_id FROM social_media_post s "+
					" join celebrity_info ci on s.celebrity_id=ci.celebrity_id "+
					" where  s.celebrity_id=#{celebrity_id} group by s.message";
        @Select(GET_ALL_POSTS_COUNT)
        public List<AdminModel> getAllPostsCount(@Param("celebrity_id") int celebrity_id);
        
        //************ Regular Task Execution Queries
        
        public String GET_CELEBRITY_DATA="SELECT celebrity_id, celebrity_name,  facebook_page, twitter_handle, instagram_page, isActive FROM celebrity_info c where isActive =1";
        @Select(GET_CELEBRITY_DATA)
        public List<AdminModel> getCelebrityInfo();
        
        public String UPDATE_ACCESS_TOKEN="update access_token set token=#{token} where token_id=1";
        @Update(UPDATE_ACCESS_TOKEN)
        public int updateAccessToken(@Param("token") String token);  
        
        public String GET_ACCESS_TOKEN="SELECT token FROM access_token a";
        @Select(GET_ACCESS_TOKEN)
        public String getToken();
        
        public String ADD_CELEBRITY_POST="insert into social_media_post (celebrity_id, post_source, message, image_url, name, story, created_time, id,twitter_handle,news_description_url) "+
              " values(#{celebrity_id}, #{post_source}, #{message}, #{image_url}, #{name}, #{story}, #{created_time}, #{id},#{twitter_handle},#{news_description_url}) ";
        @Insert(ADD_CELEBRITY_POST)
		public int addCelebrityPost(AdminModel adminModel);

        public String GET_SAVED_DATA="SELECT id FROM social_media_post e where id=#{id}";
        @Select(GET_SAVED_DATA)
        public List<AdminModel> getSavesData(@Param("id") String id);
        
        public String GET_SOCIALMEDIA_POSTS="SELECT id FROM social_media_post e where (id =#{id}) or (message=#{message1} and image_url=#{pic})";
        @Select(GET_SOCIALMEDIA_POSTS)
        public List<AdminModel> getSocialMediaPosts(@Param("id") String id,@Param("message1") String message1,@Param("pic") String pic);
        
        public String ADD_CELEBRITY_NEWS="insert into celebrity_news(celebrity_id, news_source, news_description, news_description_url, news_title, image_url, publication_date) "+
				"  values(#{celebrity_id}, #{news_source}, #{news_description}, #{news_description_url}, #{news_title}, #{image_url}, #{publication_date}) ";
		@Insert(ADD_CELEBRITY_NEWS)
		public int addCelebrityNews(AdminModel ad);
		public String GET_NEWS_ID="SELECT news_id FROM celebrity_news e where news_title=#{title} and image_url=#{img_url}";
		@Select(GET_NEWS_ID)
		public List<AdminModel> getNewsId(@Param("title") String title,@Param("img_url") String img_url);
		
		public String POSTS_COUNT="SELECT count(post_id) as post_count FROM social_media_post s where s.celebrity_id=#{celebrity_id} and post_source=#{post_source} group by message";
		@Select(POSTS_COUNT)
		public List<AdminModel> getPostsCount(@Param("celebrity_id") int celebrity_id,@Param("post_source") String post_source);

		public String TOTAL_NEWS_COUNT="select count(news_id) as news_count from celebrity_news where celebrity_id=#{celebrity_id} group by news_title,image_url";
		@Select(TOTAL_NEWS_COUNT)
		public List<AdminModel> getAllNewsCount(@Param("celebrity_id") int celebrity_id);
		
		//public String GET_MOST_POPULAR_POLITICIAN="";
		
				/*public String CELEBRITY_NEWS="SELECT news_id, c.celebrity_id, news_source,  news_description_url, news_title, image_url, DATE_FORMAT(publication_date, '%d %b  %H:%i') publication_date FROM celebrity_news c where c.celebrity_id=#{celeb_id} and substring(publication_date,1,10) = DATE(NOW() - INTERVAL 1 DAY) group by c.news_title, c.image_url"+
				"union"+
				" SELECT post_id, s.celebrity_id, post_source, null as news_description_url,  message, image_url,  DATE_FORMAT(created_time, '%d %b  %H:%i') as publication_date  FROM social_media_post s where s.celebrity_id=#{celeb_id} and substring(created_time,1,10) = DATE(NOW() - INTERVAL 1 DAY) group by s.message order by publication_date desc";*/
		        public String CELEBRITY_NEWS="select * from (SELECT news_id, c.celebrity_id, news_source,  news_description_url, news_title, image_url, DATE_FORMAT(publication_date, '%d %b  %H:%i') publication_date FROM anv_rsbs.celebrity_news c where c.celebrity_id=#{celeb_id} and substring(publication_date,1,10) = DATE(NOW() - INTERVAL 1 DAY) group by news_title, image_url)a union select * from (SELECT post_id, s.celebrity_id, post_source, null as news_description_url,  message, image_url,  DATE_FORMAT(created_time, '%d %b  %H:%i') as publication_date  FROM anv_rsbs.social_media_post s where s.celebrity_id=#{celeb_id}  and substring(created_time,1,10) = DATE(NOW() - INTERVAL 1 DAY) group by message)b order by publication_date desc";
		        @Select(CELEBRITY_NEWS)
				public List<AdminModel> getTodaysNewsforCelebrity(@Param("celeb_id") int celeb_id);

				public String GET_FOLLOWER="SELECT u.user_id,u.user_name FROM user_login u join user_follower_map uf on u.user_id=uf.user_id group by user_id";
				@Select(GET_FOLLOWER)
				public List<AdminModel> getFollowers();
				
				public String GET_CELEBRITY_BY_FOLLOWER="SELECT um.celebrity_id,ci.celebrity_name,ci.party_id FROM user_follower_map um join celebrity_info ci on um.celebrity_id=ci.celebrity_id where user_id=#{user_id}";
				@Select(GET_CELEBRITY_BY_FOLLOWER)
				public List<AdminModel> getCelebrityListByFollower(int user_id);
				
				// Celebrity of the day
				
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
				
				public String UPDATE_FORGOTED_PASSWORD="update anv_rsbs.user_login set password=#{password} where user_name=#{user_name}";
		        @Update(UPDATE_FORGOTED_PASSWORD)
		        public int updateForgotedPassword(@Param("user_name") String user_name,@Param("password") String password);
		        
		        public String CHECK_IS_LIKED="select * from anv_rsbs.user_like_map where user_id=#{user_id} and post_id_or_news_id=#{post_id_or_news_id} and activity_id=#{activity_id}";
		        @Select(CHECK_IS_LIKED)
		        public List<AdminModel> getIsLiked(@Param("user_id") int user_id,@Param("post_id_or_news_id") int post_id_or_news_id,@Param("activity_id") int activity_id);
                

		        public String ADD_BILLS_INFO="insert into bills(bill_title,bill_url,bill_number,bill_published) values(#{bill_title},#{bill_url},#{bill_number},#{bill_published})";
				@Insert(ADD_BILLS_INFO)
		        public int addBillsInfo(AdminModel ad);
				
/*				public String GET_BILLS="SELECT b.*,b.bill_published as bill_date FROM bills b join (select bill_published,bill_id from bills order by bill_published desc limit 1)a on b.bill_published=a.bill_published";
*/				public String GET_BILLS="SELECT b.*,b.bill_published as bill_date FROM bills b group by bill_number order by bill_id";
				@Select(GET_BILLS)
				public List<AdminModel> getBills();
				
				public String TRUNCATE_BILLS="truncate table anv_rsbs.bills";
				@Delete(TRUNCATE_BILLS)
				public int truncateBills();
				
				public String GET_PRECIDENT_POLITICIAN_INFO="SELECT c.celebrity_id,c.celebrity_name,c.politician_position FROM anv_rsbs.celebrity_info c left join anv_rsbs.calebrity_category_map ca on ca.celebrity_id=c.celebrity_id where ca.category_id=#{category_id} order by c.celebrity_name asc";
		        @Select(GET_PRECIDENT_POLITICIAN_INFO)
		        public List<AdminModel> getPrecidentPoliticians(@Param("category_id") int category_id);

		        public String Politician_TWITTER_ACCNT="SELECT twitter_handle FROM politician_twitter_accounts where celebrity_id=#{celebrity_id}";
				@Select(Politician_TWITTER_ACCNT)
				public List<AdminModel> getCelebrityTwitterAccount(@Param("celebrity_id") int celebrity_id);
				
				
				public List<AdminModel> searchByCelebrityName(@Param("celebrity_name") String celebrity_name);

				public String REPEAT_COUNT="select count(celebrity_id) from celebrity_info where celebrity_of_the_day_date=curdate()";
				@Select(REPEAT_COUNT)
				public int checkforRepeatCount();

				public String ALL_USERS="select * from user_login";
				@Select(ALL_USERS)
				public List<AdminModel> getAllUsers();
				
				//select count(celebrity_id) from celebrity_info where celebrity_of_the_day_date=curdate()
}
