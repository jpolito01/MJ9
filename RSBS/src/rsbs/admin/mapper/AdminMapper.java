package admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.access.prepost.PostAuthorize;

import admin.model.AdminModel;

public interface AdminMapper {

		public String GET_PARTY_INFO="SELECT * FROM party_info ";
		@Select(GET_PARTY_INFO)
		public List<AdminModel> getPartyInfo();
		
		public String ADD_POLITICIAN="insert into celebrity_info (celebrity_name, party_id, facebook_page, instagram_page, isActive, profile_photo_name, profile_photo_path, bio, state_id, date_of_birth, place_of_birth, net_worth, politician_position, tenure_from, tenure_to, term_ends, year_in_position, predecessor, career, commity) values( #{celebrity_name}, #{party_id}, #{facebook_page}, #{instagram_page}, #{isActive}, #{profile_photo_name}, #{profile_photo_path}, #{bio}, #{state_id}, #{date_of_birth}, #{place_of_birth}, #{net_worth}, #{politician_position}, #{tenure_from}, #{tenure_to}, #{term_ends}, #{year_in_position}, #{predecessor}, #{career}, #{commity})";
		@Insert(ADD_POLITICIAN)
		public void addPoliticianInfo(AdminModel ad);
		
		public String GET_LAST_ADDED_CELEBRITY="SELECT celebrity_id, celebrity_name FROM celebrity_info c  order by celebrity_id desc limit 1";
		@Select(GET_LAST_ADDED_CELEBRITY)
		public List<AdminModel> getLastAdded_celebrity();
		
		public String UPDATE_CELEB_PROFILE_PHOTO="update  celebrity_info set  profile_photo_name=#{profile_photo_name}, profile_photo_path=#{profile_photo_path} where celebrity_id=#{celebrity_id}";
		@Update(UPDATE_CELEB_PROFILE_PHOTO)
		public void updateCelebrityProfile_photo(@Param("profile_photo_name") String profile_photo_name,@Param("profile_photo_path") String profile_photo_path,@Param("celebrity_id") int celebrity_id);
				
		public String GET_ALL_CELEBRITIES="SELECT c.celebrity_id,c.celebrity_name,c.isActive,c.party_id,p.party_name,c.twitter_handle FROM celebrity_info c left join party_info p on p.party_id = c.party_id order by c.celebrity_name asc limit #{limit} offset #{offset}";
		@Select(GET_ALL_CELEBRITIES)
		public List<AdminModel> getAllCelebrity_info(@Param("limit") int limit, @Param("offset") int offset);
		
		public String GET_CALABRITY_CATEGORIES="SELECT * FROM calebrity_category_map c join  celebrity_categories cc on cc.category_id = c.category_id;";
		@Select(GET_CALABRITY_CATEGORIES)
		public List<AdminModel> getCelebrity_MappedCAtegories();
		
		public String ACTIVATE_DEACTIVATE_CELEBRITY="update celebrity_info set isActive=#{isActive} where celebrity_id=#{celebrity_id}";
		@Insert(ACTIVATE_DEACTIVATE_CELEBRITY)
		public void ActivateDeactivate_celebrity(@Param("isActive") int isActive,@Param("celebrity_id") int celebrity_id);
		
		public String GET_CELEBRITY_INFO_BY_ID="SELECT c.*,p.party_name,p.party_id,a.state_name,a.state_id FROM celebrity_info c left join party_info p on p.party_id = c.party_id left join anv_rsbs.state_master a on a.state_id=c.state_id where celebrity_id=#{celebrity_id}";
		@Select(GET_CELEBRITY_INFO_BY_ID)
		public List<AdminModel> getCelebrityInfo_byId(@Param("celebrity_id") int celebrity_id);
		
		public String UPDATE_CELEBRITY_INFO="update celebrity_info set  celebrity_name =#{celebrity_name}, party_id=#{party_id}, facebook_page=#{facebook_page}, instagram_page=#{instagram_page}, bio=#{bio}, state_id=#{state_id}, date_of_birth=#{date_of_birth}, place_of_birth=#{place_of_birth}, net_worth=#{net_worth}, politician_position=#{politician_position}, tenure_from=#{tenure_from}, tenure_to=#{tenure_to}, term_ends=#{term_ends}, year_in_position=#{year_in_position}, predecessor=#{predecessor}, career=#{career}, commity=#{commity} where celebrity_id=#{celebrity_id}";
		@Update(UPDATE_CELEBRITY_INFO)
		public void updateCelebrityInfo(AdminModel ad);
		
		//8-11-2017 priti
		
		public String GET_BLACKLIST_WEBSITE_NAME="select * from blacklist_website where website_name=#{website_name}";
		@Select(GET_BLACKLIST_WEBSITE_NAME)
		public List<AdminModel> getBlacklist_WebsiteByName(@Param("website_name") String website_name);
		
		public String UPDATE_WEBSITE_NAME="update blacklist_website set website_name=#{website_name} where web_id=#{web_id}";
		@Update(UPDATE_WEBSITE_NAME)
		public int updateWebsite_name(@Param("website_name") String website_name,@Param("web_id") int web_id);
		
		public String DELETE_BLACKLIST_WEBSITE="delete from blacklist_website where web_id=#{web_id}";
		@Delete(DELETE_BLACKLIST_WEBSITE)
		public int deleteWebsite_FromBlackList(@Param("web_id") int web_id);
		
		public String GET_ALL_BLACKLIST_WEBSITES="select * from blacklist_website";
		@Select(GET_ALL_BLACKLIST_WEBSITES)
		public List<AdminModel> getBlacklist_Website();
		
		public String INSERT_BLACKLIST_WEBSITE="insert into blacklist_website(website_name) values(#{website_name})";
		@Insert(INSERT_BLACKLIST_WEBSITE)
		public int InsertBlcakList_Website(@Param("website_name") String website_name);
		
		public String GET_EXISTING_PARTY_NAMES="select * from anv_rsbs.party_info where party_name=#{party_name}";
		@Select(GET_EXISTING_PARTY_NAMES)
		public List<AdminModel> getExistingPartyName(@Param("party_name") String party_name);
		
		public String ADD_NEW_PARTY="insert into anv_rsbs.party_info(party_name) values(#{party_name})";
		@Insert(ADD_NEW_PARTY)
		public int addNewParty(@Param("party_name") String party_name);
		
		public String GET_ALL_PARTY_NAMES="select * from anv_rsbs.party_info";
		@Select(GET_ALL_PARTY_NAMES)
		public List<AdminModel> getAllParties();
		
		public String UPDATE_PARTY_NAME="update anv_rsbs.party_info set party_name=#{party_name} where party_id=#{party_id}";
		@Update(UPDATE_PARTY_NAME)
		public int updatePartyName(@Param("party_id") int party_id,@Param("party_name") String party_name);
		
		public String DELETE_PARTY="delete from anv_rsbs.party_info where party_id=#{party_id}";
		@Delete(DELETE_PARTY)
		public int deleteParty(@Param("party_id") int party_id);
		
		//delete celebrity
        public String DELETE_NEWS_COMMENTS="delete from anv_rsbs.user_comment_map where post_id_or_news_id in (select n.news_id from anv_rsbs.celebrity_news n where n.celebrity_id=#{celebrity_id})";
        @Delete(DELETE_NEWS_COMMENTS)
        public int deleteCelebrityNewsComments(@Param("celebrity_id") int celebrity_id);
		
        public String DELETE_NEWS_LIKES="delete from anv_rsbs.user_like_map where post_id_or_news_id in (select n.news_id from anv_rsbs.celebrity_news n where n.celebrity_id=#{celebrity_id})";
        @Delete(DELETE_NEWS_LIKES)
        public int deleteCelebrityNewsLikes(@Param("celebrity_id") int celebrity_id);
        
        public String DELETE_POSTS_COMMENTS="delete from anv_rsbs.user_comment_map where post_id_or_news_id in (select n.post_id from anv_rsbs.social_media_post n where n.celebrity_id=#{celebrity_id})";
        @Delete(DELETE_NEWS_COMMENTS)
        public int deleteCelebrityPostsComments(@Param("celebrity_id") int celebrity_id);
	
        public String DELETE_POSTS_LIKES="delete from anv_rsbs.user_like_map where post_id_or_news_id in (select n.post_id from anv_rsbs.social_media_post n where n.celebrity_id=#{celebrity_id})";
        @Delete(DELETE_POSTS_LIKES)
        public int deleteCelebrityPostsLikes(@Param("celebrity_id") int celebrity_id);
        
        public String DELETE_CELEBRITY_FOLLOWERS="delete from user_follower_map where celebrity_id=#{celebrity_id}";
        @Delete(DELETE_CELEBRITY_FOLLOWERS)
        public int deleteCelebrityFollowers(@Param("celebrity_id") int celebrity_id);
		
		public String DELETE_POLITICIAN_INFO="delete from anv_rsbs.celebrity_info where celebrity_id=#{celebrity_id}";
		@Delete(DELETE_POLITICIAN_INFO)
		public int deletePoliticianInfo(@Param("celebrity_id") int celebrity_id);
		
		public String GET_All_SEARCH_KEYS="SELECT * FROM anv_rsbs.user_searches u";
		@Select(GET_All_SEARCH_KEYS)
		public List<AdminModel> getAllSearchKeys();
		
		public String DELETE_SEARCH_KEY="delete from anv_rsbs.user_searches where search_key_id=#{search_key_id}";
		@Delete(DELETE_SEARCH_KEY)
		public int deleteSearchKey(@Param("search_key_id") int search_key_id);
		
		public String UPDATE_SEARCH_KEY="update anv_rsbs.user_searches set search_key=#{search_key} where search_key_id=#{search_key_id}";
		@Update(UPDATE_SEARCH_KEY)
		public int updateSearchKey(@Param("search_key_id") int search_key_id,@Param("search_key") String search_key);

		public String GET_ALL_STATES="select * from anv_rsbs.state_master order by state_name asc";
		@Select(GET_ALL_STATES)
		public List<AdminModel> getAllStates();
		
		public String GET_ALL_CELEBRITY_CATEGORIES="select * from celebrity_categories";
		@Select(GET_ALL_CELEBRITY_CATEGORIES)
		public List<AdminModel> getAllCelebrityCategories();
		
		public String ADD_CELEBRITY_CATEGORY="insert into calebrity_category_map(celebrity_id, category_id) values(#{celebrity_id}, #{category_id})";
		@Insert(ADD_CELEBRITY_CATEGORY)
		public int addCelebrityCategory(@Param("celebrity_id") int calebrity_id, @Param("category_id") int category_id);

		public String GET_CELEBRITY_CATEGORY="select c.category_id, a.categroy_name from anv_rsbs.calebrity_category_map c left join anv_rsbs.celebrity_categories a on a.category_id=c.category_id where celebrity_id=#{celebrity_id}";
		@Select(GET_CELEBRITY_CATEGORY)
		public List<AdminModel> getCelebrityCategory(@Param("celebrity_id") int celebrity_id);

		public String DELETE_CELEBRITY_CATEGORIES="delete from calebrity_category_map where celebrity_id=#{celebrity_id}";
		@Delete(DELETE_CELEBRITY_CATEGORIES)
		public int deleteCelebrityCategory(@Param("celebrity_id") int celebrity_id);
		
		public String ADD_BLOGS="insert into anv_rsbs.blog_details(blog_title, description, image_path) values(#{blog_title}, #{description}, #{image_path})";
		@Insert(ADD_BLOGS)
		public void addBlogs(AdminModel ad);
		
		public String GET__BLOG="SELECT blog_id, blog_title FROM anv_rsbs.blog_details c order by blog_id desc limit 1";
		@Select(GET__BLOG)
		public List<AdminModel> getLastBlog();
		
		public String UPDATE_BLOG_IMAGE="update anv_rsbs.blog_details set image_path=#{image_path} where blog_id=#{blog_id}";
		@Update(UPDATE_BLOG_IMAGE)
		public int updateBlogImage(@Param("image_path") String image_path, @Param("blog_id") int blog_id);
		
		public String GET_BLOGS="SELECT * FROM anv_rsbs.blog_details c";
		@Select(GET_BLOGS)
		public List<AdminModel> getBlogs();
		
		public String DELETE_BLOG="delete from anv_rsbs.blog_details where blog_id=#{blog_id}";
		@Delete(DELETE_BLOG)
		public int deleteBlogs(@Param("blog_id") int blog_id);
		
		public String UPDATE_BLOGS="update anv_rsbs.blog_details set blog_title=#{blog_title}, description=#{description} where blog_id=#{blog_id}";
		@Update(UPDATE_BLOGS)
		public int updateBlogs(@Param("blog_id") int blog_id, @Param("blog_title") String blog_title, @Param("description") String description);

		public String GET_BLOGS_BY_ID="SELECT * FROM anv_rsbs.blog_details c where blog_id=#{blog_id}";
		@Select(GET_BLOGS_BY_ID)
		public List<AdminModel> getBlogsById(@Param("blog_id") int blog_id);
		
		public String GET_CELEBRITIES_COUNT="SELECT count(c.celebrity_id) FROM celebrity_info c";
		@Select(GET_CELEBRITIES_COUNT)
		public int getCelebrityCount();
		
		public String GET_SEARCH_CELEBRITIES="SELECT c.celebrity_id,c.celebrity_name,c.isActive,c.party_id,p.party_name FROM celebrity_info c left join party_info p on p.party_id = c.party_id where c.celebrity_name like #{celebrity_name}";
		@Select(GET_SEARCH_CELEBRITIES)
		public List<AdminModel> getSearchCelebrity_info(@Param("celebrity_name") String celebrity_name);
		
		public String DELETE_TWITTER_ACCNT="delete from politician_twitter_accounts where celebrity_id=#{celebrity_id}";
		@Delete(DELETE_TWITTER_ACCNT)
		public int deleteCelebrityTwitterAccounts(@Param("celebrity_id") int celebrity_id);
		
		public String INSERT_TWITTER_ACCNT="insert into politician_twitter_accounts(celebrity_id, twitter_handle) values(#{celebrity_id}, #{twitterName})";
		@Insert(INSERT_TWITTER_ACCNT)
		public int addCelebrityTwitterAcnt(@Param("celebrity_id") int celebrity_id,@Param("twitterName") String twitterName);
		
		public String Politician_TWITTER_ACCNT="SELECT twitter_handle FROM politician_twitter_accounts where celebrity_id=#{celebrity_id}";
		@Select(Politician_TWITTER_ACCNT)
		public List<AdminModel> getCelebrityTwitterAccount(@Param("celebrity_id") int celebrity_id);
		
		public String Politician_TWITTER_ACCNT_CHECK="SELECT twitter_handle FROM politician_twitter_accounts where celebrity_id=#{celebrity_id} and twitter_handle=#{twitname}";
		@Select(Politician_TWITTER_ACCNT_CHECK)
		public List<AdminModel> gettwitter_account(@Param("celebrity_id") int id,@Param("twitname") String twitname);
		
		public String GET_USER_ACCOUNTS="SELECT u.first_name,u.last_name,u.email_id,uf.follow_id from user_information u left join user_follower_map uf on u.user_id=uf.user_id group by u.user_id limit #{limit} offset #{offset}";
		@Select(GET_USER_ACCOUNTS)
		public List<AdminModel> getUserAccounts(@Param("limit") int limit,@Param("offset") int offset);
		
		public String GET_USER_ACCOUNTS_COUNT="SELECT COUNT(user_id) as follower_count FROM user_information";
		@Select(GET_USER_ACCOUNTS_COUNT)
		public List<AdminModel> getUserAccountsCount();
		
}
