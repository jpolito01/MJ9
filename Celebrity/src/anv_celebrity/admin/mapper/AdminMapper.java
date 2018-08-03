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

	String GET_ALL_COUNTRIES="SELECT * FROM country_master c";
	@Select(GET_ALL_COUNTRIES)
	public List<AdminModel> getAllCountries();
	
	String GET_ALL_STATES="SELECT * FROM state_master s ";
	@Select(GET_ALL_STATES)
	public List<AdminModel> getAllSttes();
	
	String GET_STATE_NAMES="SELECT * FROM state_master s where country_id=#{country_id}";
	@Select(GET_STATE_NAMES)
	public List<AdminModel> getStates_name(@Param("country_id") int country_id);
	
	String GET_CELEBRITY_CATEGORY="SELECT * FROM celebrity_category c where c.category_id!=6";
	@Select(GET_CELEBRITY_CATEGORY)
	public List<AdminModel> getCelebrity_category();
	
	String INSERT_CELEBRITY_INFO="insert into celebrity_info(celebrity_name, celebrity_bio, country_id, state_id,facebok_page_name,instagram_page_name,twitter_handle_name,is_active)" +
				" values(#{celebrity_name},#{celebrity_bio},  #{country_id}, #{state_id},#{facebok_page_name},#{instagram_page_name},#{twitter_handle_name},1)";
	@Insert(INSERT_CELEBRITY_INFO)
	public void insertCelebrityInfo(AdminModel ad);
	
	String GET_LAST_ADDED_CELEBRITY="select celebrity_id, celebrity_name FROM celebrity_info  order by celebrity_id desc  limit 1";
	@Select(GET_LAST_ADDED_CELEBRITY)
	public List<AdminModel> getLast_addedCelebrity();
	 
	String UPDATE_CELEBRITY_PHOTO="update celebrity_info set profile_photo_name=#{profile_photo_name}, profile_photo_path=#{profile_photo_path } where celebrity_id=#{celebrity_id}";
	@Update(UPDATE_CELEBRITY_PHOTO)
	public void UpdateCelebrity_Profile(AdminModel ad);
	
	String ADD_CELEBRITY_CATEGORY="insert into celebrity_category_map (celebrity_id, category_id)values(#{celebrity_id}, #{category_id})";
	@Insert(ADD_CELEBRITY_CATEGORY)
	public void addCelebrity_Category(AdminModel ad);
	
	String SHOW_CELEBRITY_INFO="SELECT c.*,cm.country_name,sm.state_name FROM celebrity_info c left join country_master cm on cm.country_id = c.country_id left join state_master sm on sm.state_id=c.state_id left join celebrity_category_map ccm on c.celebrity_id=ccm.celebrity_id where (ccm.category_id!=6 or ccm.category_id IS NULL) group by c.celebrity_id order by celebrity_id limit #{limit} offset #{offset}";
	@Select(SHOW_CELEBRITY_INFO)
	public List<AdminModel> showCelebrity_info(@Param("limit") int limit, @Param("offset") int offset);
	
	String GET_CELEBRITY_INFO_by_ID="SELECT c.*,cm.country_name,sm.state_name FROM celebrity_info c "+
			" left join country_master cm on cm.country_id = c.country_id "+
			" left join state_master sm on sm.state_id=c.state_id "+
			" where c.celebrity_id=#{celebrity_id}";
	@Select(GET_CELEBRITY_INFO_by_ID)
	public List<AdminModel> getCelebrityInfo_byId(@Param("celebrity_id") int celebrity_id);	
	
	
	String GET_CELEBRITY_CATEGORY_DETAILS="SELECT c.*,cc.category_name FROM celebrity_category_map c join celebrity_category cc on cc.category_id = c.category_id ";
	@Select(GET_CELEBRITY_CATEGORY_DETAILS)
	public List<AdminModel> getCelebrityCategory_details();
	
	String GET_CELEBRITY_CATEGORY_DETAILS_BY_ID="select * from celebrity_category c "+
											" left join celebrity_category_map ccm on ccm.category_id=c.category_id and celebrity_id=#{celebrity_id} where c.category_id!=6;";
	@Select(GET_CELEBRITY_CATEGORY_DETAILS_BY_ID)
	public List<AdminModel> getCelebrityCategory_details_ById(@Param("celebrity_id") int celebrity_id);
	
	String UPDATE_CELEBRITY_IFNO="update celebrity_info set celebrity_name =#{celebrity_name},celebrity_bio=#{celebrity_bio} ,celebrity_nick_name=#{celebrity_nick_name}, country_id=#{country_id}, state_id=#{state_id},facebok_page_name=#{facebok_page_name}, instagram_page_name=#{instagram_page_name},twitter_handle_name=#{twitter_handle_name} where celebrity_id=#{celebrity_id} ;";
	@Update(UPDATE_CELEBRITY_IFNO)
	public void updateCelebrity_info(AdminModel ad);

	String DELETE_CELEBRITY_CATEGORY="delete from celebrity_category_map where celebrity_id=#{celebrity_id}";
	@Delete(DELETE_CELEBRITY_CATEGORY)
	public void deleteCelebrity_category(@Param("celebrity_id") int celebrity_id);
	
	String ACTIVATE_DEACTIVATE_CELEBRITY="update celebrity_info set is_active = #{is_active} where celebrity_id=#{celebrity_id}";
	@Update(ACTIVATE_DEACTIVATE_CELEBRITY)
	public void ActivateDeactivate_celebrity(@Param("is_active") int is_active,@Param("celebrity_id") int celebrity_id);
	
	String DELETE_CELEBRITY_INFORMATION="delete from celebrity_info where celebrity_id=#{celebrity_id}";
	@Delete(DELETE_CELEBRITY_INFORMATION)
	public void DeleteCelebrity_info(@Param("celebrity_id") int celebrity_id);
	
	String GET_PROFILE_PHOTO_DETAILS="SELECT celebrity_id,profile_photo_name, profile_photo_path FROM celebrity_info c where celebrity_id=#{celebrity_id}";
	@Select(GET_PROFILE_PHOTO_DETAILS)
	public List<AdminModel> getCelebrity_Profile_photo(@Param("celebrity_id") int celebrity_id);
			
	String INSERT_NEW_CELEBRITYcATEGORY="insert into celebrity_category(category_name)value(#{category_name})";
	@Insert(INSERT_NEW_CELEBRITYcATEGORY)
	public void InsertCelebrity_Category(@Param("category_name") String category_name);
	
	String UPDATE_CATEGORY="update celebrity_category set category_name=#{category_name} where category_id=#{category_id}";
	@Update(UPDATE_CATEGORY)
	public void updateCateogry_name(@Param("category_name") String category_name,@Param("category_id") int category_id);
	
	String DELETE_CATEGORY="delete from celebrity_category where category_id=#{category_id}";
	@Delete(DELETE_CATEGORY)
	public void deleteCategory_fromMaster(@Param("category_id") int category_id);
	
	
	String ADD_COUNTRY_NAME="insert into country_master(country_name) values (#{country_name})";
	@Insert(ADD_COUNTRY_NAME)
	public void addCountry_Name(@Param("country_name")String country_name);
	
	String DELETE_CELEBRITY_LIKES="delete  FROM user_like_map  where (activity_type_id=1 and "+
								" post_id_or_news_id in (SELECT news_id FROM entity_news e where entity_id=#{celebrity_id} and entity_type_id=1)) or "+
								" (activity_type_id=2 and post_id_or_news_id in (SELECT post_id FROM entity_post e where entity_id=#{celebrity_id} and entity_type_id=1)) ";
	@Delete(DELETE_CELEBRITY_LIKES)
	public void DeleteCelebrity_Likes(@Param("celebrity_id") int celebrity_id);
	
	String DELETE_CELEBRITY_COMMENTS="delete FROM user_comments_map  where (activity_type_id=1 and "+
									" post_id_or_news_id in (SELECT news_id FROM entity_news e where entity_id=#{celebrity_id} and entity_type_id=1)) or "+
									" (activity_type_id=2 and post_id_or_news_id in (SELECT post_id FROM entity_post e where entity_id=#{celebrity_id} and entity_type_id=1))";
	@Delete(DELETE_CELEBRITY_COMMENTS)
	public void DeleteCelebrity_Comments(@Param("celebrity_id") int celebrity_id);
	
	String DELETE_CELEBRITIES_NEWS="DELETE FROM entity_news  where entity_id=#{celebrity_id} and entity_type_id=1";
	@Delete(DELETE_CELEBRITIES_NEWS)
	public void Delete_celebrity_News(@Param("celebrity_id") int celebrity_id);
	
	String DELETE_CELEBRITIES_POST="DELETE FROM entity_news  where entity_id=#{celebrity_id} and entity_type_id=1";
	@Delete(DELETE_CELEBRITIES_POST)
	public void DeleteCelebrity_Post(@Param("celebrity_id") int celebrity_id);
	
	String GET_USER_SEARCH_TERM="SELECT * FROM user_searches u";
	@Select(GET_USER_SEARCH_TERM)
	public List<AdminModel> getUserSearch_terms();
	
	String DELETE_USER_SEARCH_TERMS="delete from user_searches where id=#{search_id}";
	@Delete(DELETE_USER_SEARCH_TERMS)
	public void DeleteUserSearchTerms(@Param("search_id") int search_id);
	
	String UPDATE_USER_SEARCHED_TERMS="update user_searches set search_key=#{search_key} where id=#{search_id}";
	@Update(UPDATE_USER_SEARCHED_TERMS)
	public void UpdateUserSearchedTerms(@Param("search_key") String search_key,@Param("search_id") int search_id);
	
	String GET_SEARCH_TERM_BY_KEY="SELECT * FROM user_searches u where search_key =#{search_key}";
	@Select(GET_SEARCH_TERM_BY_KEY)
	public List<AdminModel> getSearchTerms_ByKey(@Param("search_key") String search_key);
	
	String INSERT_BLACKLIST_WEBSITE="insert into blacklist_website(website_name) values(#{website_name})";
	@Insert(INSERT_BLACKLIST_WEBSITE)
	public void InsertBlcakList_Website(@Param("website_name") String website_name);
	
	String GET_BLACKLIST_WEBSITENAME="SELECT * FROM blacklist_website b";
	@Select(GET_BLACKLIST_WEBSITENAME)
	public List<AdminModel> getBlacklist_Website();
	
	String GET_BLACKLIST_WEBSITENAMEs_BY_NAME="SELECT * FROM blacklist_website b where website_name=#{website_name}";
	@Select(GET_BLACKLIST_WEBSITENAMEs_BY_NAME)
	public List<AdminModel> getBlacklist_WebsiteByName(@Param("website_name") String website_name);
	
	String DELETE_WEBITE_BLACKLIST="DELETE FROM blacklist_website WHERE WEB_ID=#{web_id}";
	@Delete(DELETE_WEBITE_BLACKLIST)
	public void deleteWebsite_FromBlackList(@Param("web_id") int web_id);
	
	String UPDATE_WEBSITE_NAME="update blacklist_website set website_name=#{website_name} where web_id=#{web_id}";
	@Update(UPDATE_WEBSITE_NAME)
	public void updateWebsite_name(@Param("website_name") String website_name,@Param("web_id") int web_id);
	
	
	/*public String GET_MOVIE_LIST="SELECT c.celebrity_id,c.celebrity_name,c.is_active,cc.category_id FROM celebrity_info c "+
                                 " left join celebrity_category_map  cc on cc.celebrity_id = c.celebrity_id where (cc.category_id=6 or cc.category_id IS NULL) ";
	@Select(GET_MOVIE_LIST)
	public List<AdminModel> getMovies_list();*/
	
	public String GET_MOVIE_LIST="SELECT c.celebrity_id,c.celebrity_name,c.is_active,cc.category_id FROM celebrity_info c "+
            " left join celebrity_category_map  cc on cc.celebrity_id = c.celebrity_id where cc.category_id=6 ";
@Select(GET_MOVIE_LIST)
public List<AdminModel> getMovies_list();
	
	public String INSERT_BLOG="insert into movie_blogging (movie_blogs, user_id, create_date, blog_title) "+
											" values (#{movie_blogs}, #{user_id}, #{create_date}, #{blog_title}) ";
	@Insert(INSERT_BLOG)
	public void InsertBlog(AdminModel ad);
	
	public String GET_BLOG="SELECT blog_id,blog_title,blog_image FROM movie_blogging m;";
	@Select(GET_BLOG)
	public List<AdminModel> getBlogs_list();
	
	public String BLOG_DATA="SELECT * FROM movie_blogging m where blog_id=#{blog_id};";
	@Select(BLOG_DATA)
	public List<AdminModel> movie_blog_data(int blog_id);
	
	public String BLOG_UPDATE="Update movie_blogging m set blog_title=#{blog_title},movie_blogs=#{movie_blogs},blog_image=#{blog_image} where blog_id=#{blog_id};";
   @Update(BLOG_UPDATE)
	public void UpdateBlog(AdminModel ad);
   
	String GET_LAST_BLOG="select blog_id FROM movie_blogging order by blog_id desc limit 1";
	@Select(GET_LAST_BLOG)
public int lastAddedBlogRecord();
	
	String BLOG_IMAGE="select blog_image FROM movie_blogging where blog_id=#{blogId}";
	@Select(BLOG_IMAGE)
	public String getBlog_image(int blogId);
	
	String DELETE_BLOG="delete from movie_blogging where blog_id=#{blog_id}";
	@Delete(DELETE_BLOG)
	public void deleteBlog(int blog_id);
	
	public String GET_ALL_CELEBRITY_NAMES="select distinct c.celebrity_id,c.celebrity_name from anv_celebrity.celebrity_info c where celebrity_id not in (SELECT celebrity_id FROM celebrity_category_map c where category_id=6) order by celebrity_name";
	@Select(GET_ALL_CELEBRITY_NAMES)
	public List<AdminModel> getAllCelebrityNames();
	
	public String GET_ALL_CELEBRITY_CASTS="select * from anv_celebrity.movie_cast_master";
	@Select(GET_ALL_CELEBRITY_CASTS)
	public List<AdminModel> getAllCasts();
	
	public String ADD_CELEBRITY_CAST="insert into anv_celebrity.movie_cast_map(movie_id, celebrity_id, caracter) values(#{movie_id}, #{celebrity_id}, #{caracter})";
	@Insert(ADD_CELEBRITY_CAST)
	public int addCelebrityCast(@Param("movie_id") int movie_id,@Param("celebrity_id") int celebrity_id,@Param("caracter") String caracter);

	public String GET_MOVIE="select celebrity_id from anv_celebrity.celebrity_info where celebrity_name=#{celebrity_name} order by celebrity_id desc limit 1";
	@Select(GET_MOVIE)
	public List<AdminModel> getMovieId(@Param("celebrity_name") String  celebrity_name);
	
	public String GET_ROLES="select * from anv_celebrity.movie_cast_master";
	@Select(GET_ROLES)
	public List<AdminModel> getCasts();
	
	public String ADD_NEW_ROLE="insert into anv_celebrity.movie_cast_master(caracter_name) values(#{caracter_name})";
	@Insert(ADD_NEW_ROLE)
	public int addNewRole(@Param("caracter_name") String caracter_name);
	
	public String DELETE_ROLE="delete from anv_celebrity.movie_cast_master where id=#{id}";
	@Delete(DELETE_ROLE)
	public int deleteRole(@Param("id") int id);
	
	public String UPDATE_ROLE_NAME="update anv_celebrity.movie_cast_master set caracter_name=#{caracter_name} where id=#{id}";
	@Update(UPDATE_ROLE_NAME)
	public int updateRoleName(@Param("id") int id,@Param("caracter_name") String caracter_name);
	
	public String GET_MOVIE_CAST="select id,celebrity_id,caracter from anv_celebrity.movie_cast_map where movie_id=#{celebrity_id}";
	@Select(GET_MOVIE_CAST)
	public List<AdminModel> getMovie_cast(@Param("celebrity_id") int celebrity_id);
	
	public String GET_MOVIE_CAST_COUNT="select COUNT(movie_id) as follower_count from anv_celebrity.movie_cast_map where movie_id=#{movieId}";
	@Select(GET_MOVIE_CAST_COUNT)
	public List<AdminModel> getMovieCastCount(@Param("movieId") int movieId);
	
	public String DELETE_CAST="delete from anv_celebrity.movie_cast_map where movie_id=#{movieId}";
	@Delete(DELETE_CAST)
	public int movieCastDelete(@Param("movieId") int movieId);
	
	public String DELETE_CAST_ROLE="delete from anv_celebrity.movie_cast_map where id=#{delete_celebrity_id}";
	@Delete(DELETE_CAST_ROLE)
	public int deleteMovieCastRole(@Param("delete_celebrity_id") int delete_celebrity_id);
	
	public String DELETE_MAPPED_ROLE="delete from movie_cast_map where caracter=#{caracter}";
	@Delete(DELETE_MAPPED_ROLE)
	public int deleteMappedRole(@Param("caracter") int caracter);
	
	public String GET_CELEBRITIES_COUNT="SELECT count(DISTINCT c.celebrity_id) FROM celebrity_info c left join country_master cm on cm.country_id = c.country_id left join state_master sm on sm.state_id=c.state_id left join celebrity_category_map ccm on c.celebrity_id=ccm.celebrity_id where (ccm.category_id!=6 or ccm.category_id IS NULL)";
	@Select(GET_CELEBRITIES_COUNT)
	public int getCelebrityCount();
	
	public String GET_SEARCH_CELEBRITIES="SELECT c.*,cm.country_name,sm.state_name FROM celebrity_info c left join country_master cm on cm.country_id = c.country_id left join state_master sm on sm.state_id=c.state_id left join celebrity_category_map ccm on c.celebrity_id=ccm.celebrity_id where (ccm.category_id!=6 or ccm.category_id IS NULL) and celebrity_name like #{search_celebrity_key} group by c.celebrity_id order by celebrity_name";
	@Select(GET_SEARCH_CELEBRITIES)
	public List<AdminModel> getSearchCelebrity_info(@Param("search_celebrity_key") String search_celebrity_key);
	
	public String GET_SEARCH_CELEBRITIES_COUNT="SELECT count(DISTINCT c.celebrity_id) FROM celebrity_info c left join country_master cm on cm.country_id = c.country_id left join state_master sm on sm.state_id=c.state_id left join celebrity_category_map ccm on c.celebrity_id=ccm.celebrity_id where (ccm.category_id!=6 or ccm.category_id IS NULL) and celebrity_name like #{search_celebrity_key}";
	@Select(GET_SEARCH_CELEBRITIES_COUNT)
	public int getSearchedCelebrityCount(@Param("search_celebrity_key") String search_celebrity_key);
	
	String DELETE_NEWS_BLACKLIST="delete from entity_news where news_description_url like #{website_name}";
	@Delete(DELETE_NEWS_BLACKLIST)
	public int deleteBlacklistedNews(@Param("website_name") String website_name);
	
	public String GET_ALL_REQUESTED_CELEBRITY_INFO="SELECT * FROM requested_celebrity r;";
	@Select(GET_ALL_REQUESTED_CELEBRITY_INFO)
	public List<AdminModel> getAllRequestedCelebrityInfo();
	
	public String GET_REQUESTED_CELEBRITY_INFO_WITH_LIMIT="SELECT * FROM requested_celebrity r limit #{limit} offset #{offset};";
	@Select(GET_REQUESTED_CELEBRITY_INFO_WITH_LIMIT)
	public List<AdminModel> getRequestedCelebrityInfoWithLimit(@Param("limit") int limit,@Param("offset") int offset);
	
	public String GET_ALL_REQUESTED_CELEBRITY_CATEGORIES_INFO="SELECT * FROM requested_celebrity_category_map r left join celebrity_category c on c.category_id=r.category_id;";
	@Select(GET_ALL_REQUESTED_CELEBRITY_CATEGORIES_INFO)
	public List<AdminModel> getAllRequestedCelebrityCategoriesInfo();
	
	/*String DELETE_WEBITE_BLACKLIST="DELETE FROM blacklist_website WHERE WEB_ID=#{web_id}";
	@Delete(DELETE_WEBITE_BLACKLIST)
	public void deleteWebsite_FromBlackList(@Param("web_id") int web_id);*/
	
	//blacklist_website set website_name

}