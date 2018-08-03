package admin.services;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import admin.model.AdminModel;

public interface AdminServices {
	public List<AdminModel> getPartyInfo();
	public void addPoliticianInfo(AdminModel ad);
	public List<AdminModel> getLastAdded_celebrity();
	public void updateCelebrityProfile_photo(String profile_photo_name,String profile_photo_path,int celebrity_id);
	public List<AdminModel> getAllCelebrity_info(@Param("limit") int limit, @Param("offset") int offset);
	public void ActivateCelebrity(int celebrity_id);
	public void DeactivateCelebrity(int celebrity_id);
	public List<AdminModel> getCelebrityInfo_byId( int celebrity_id);
	public void updateCelebrityInfo(AdminModel ad);
	
	public List<AdminModel> getBlacklist_WebsiteByName(@Param("website_name") String website_name);
	public int updateWebsite_name(@Param("website_name") String website_name,@Param("web_id") int web_id);
	public int deleteWebsite_FromBlackList(@Param("web_id") int web_id);
	public List<AdminModel> getBlacklist_Website();
	public int InsertBlcakList_Website(@Param("website_name") String website_name);
	public List<AdminModel> getExistingPartyName(@Param("party_name") String party_name);
	public int addNewParty(@Param("party_name") String party_name);
	public List<AdminModel> getAllParties();
	public int updatePartyName(@Param("party_id") int party_id,@Param("party_name") String party_name);
	public int deleteParty(@Param("party_id") int party_id);
    
	public int deleteCelebrityNewsComments(@Param("celebrity_id") int celebrity_id);
    public int deleteCelebrityNewsLikes(@Param("celebrity_id") int celebrity_id);
    public int deleteCelebrityPostsComments(@Param("celebrity_id") int celebrity_id);
    public int deleteCelebrityPostsLikes(@Param("celebrity_id") int celebrity_id);
    public int deleteCelebrityFollowers(@Param("celebrity_id") int celebrity_id);
	public int deletePoliticianInfo(@Param("celebrity_id") int celebrity_id);

	public List<AdminModel> getAllSearchKeys();
	public int deleteSearchKey(@Param("search_key_id") int search_key_id);
	public int updateSearchKey(@Param("search_key_id") int search_key_id,@Param("search_key") String search_key);

	public List<AdminModel> getAllStates();
	public List<AdminModel> getAllCelebrityCategories();
	public int addCelebrityCategory(@Param("celebrity_id") int calebrity_id, @Param("category_id") int category_id);
	public List<AdminModel> getCelebrityCategory(@Param("celebrity_id") int celebrity_id);
	public int deleteCelebrityCategory(@Param("celebrity_id") int celebrity_id);
	public List<AdminModel> getCelebrity_MappedCAtegories();
	public void addBlogs(AdminModel ad);
	public List<AdminModel> getLastBlog();
	public int updateBlogImage(@Param("image_path") String image_path, @Param("blog_id") int blog_id);
	public List<AdminModel> getBlogs();
	public int deleteBlogs(@Param("blog_id") int blog_id);
	public int updateBlogs(@Param("blog_id") int blog_id, @Param("blog_title") String blog_title, @Param("description") String description);
	public List<AdminModel> getBlogsById(@Param("blog_id") int blog_id);
	public int getCelebrityCount();
	public List<AdminModel> getSearchCelebrity_info(String celebrity_name);
	public int deleteCelebrityTwitterAccounts(int celebrity_id);
	public int addCelebrityTwitterAcnt(int celebrity_id,String twitterName);
	public List<AdminModel> getCelebrityTwitterAccount(int celebrity_id);
	public List<AdminModel> gettwitter_account(int i, String string);
	public List<AdminModel> getUserAccountsCount();
	public List<AdminModel> getUserAccounts(int limit, int offset);
	

}
