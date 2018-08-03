package admin.model;

import java.io.Serializable;
import java.util.Date;

public class AdminModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  celebrity_name,  facebook_page, twitter_handle, instagram_page, profile_photo_name, profile_photo_path,state_name;
	private int user_id,news_id,isActive,celebrity_id,party_id,post_id,role_id,web_id,follower_count,follower_id;
	private int post_count,news_count,count,search_key_id,post_id_or_news_id, activity_id, like_count,commented_user_id,state_id;
	private int category_id,liked_activity_id,like_id,comment_id, blog_id;
	private String  post_source, message, image_url, name, story, created_time, id, news_description;
	private String  news_source, news_description_url, news_url, news_title, publication_date;
	private String  user_name, password, first_name,last_name,email_id;
	private String website_name,commity;
	private String bio,search_key, categroy_name, blog_title, description, image_path;
	private Date blog_date,bill_date;
	private String year_in_position, date_of_birth, place_of_birth, politician_position, tenure_from, tenure_to, term_ends, net_worth, predecessor, career;
	private int celebrity_of_the_day,follow_id,bill_id;
	private Date celebrity_of_the_day_date;
	private String bill_title, bill_url, bill_number, bill_published;
	private Date news_date;
	

public Date getNews_date() {
		return news_date;
	}
	public void setNews_date(Date news_date) {
		this.news_date = news_date;
	}
public Date getBill_date() {
		return bill_date;
	}
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	public String getBill_title() {
		return bill_title;
	}
	public void setBill_title(String bill_title) {
		this.bill_title = bill_title;
	}
	public String getBill_url() {
		return bill_url;
	}
	public void setBill_url(String bill_url) {
		this.bill_url = bill_url;
	}
	public String getBill_number() {
		return bill_number;
	}
	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}
	public String getBill_published() {
		return bill_published;
	}
	public void setBill_published(String bill_published) {
		this.bill_published = bill_published;
	}
public String getCommity() {
		return commity;
	}
	public void setCommity(String commity) {
		this.commity = commity;
	}
public int getFollow_id() {
		return follow_id;
	}
	public void setFollow_id(int follow_id) {
		this.follow_id = follow_id;
	}
public int getCelebrity_of_the_day() {
		return celebrity_of_the_day;
	}
	public void setCelebrity_of_the_day(int celebrity_of_the_day) {
		this.celebrity_of_the_day = celebrity_of_the_day;
	}
	public Date getCelebrity_of_the_day_date() {
		return celebrity_of_the_day_date;
	}
	public void setCelebrity_of_the_day_date(Date celebrity_of_the_day_date) {
		this.celebrity_of_the_day_date = celebrity_of_the_day_date;
	}
public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
public String getPolitician_position() {
		return politician_position;
	}
	public void setPolitician_position(String politician_position) {
		this.politician_position = politician_position;
	}
public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getPlace_of_birth() {
		return place_of_birth;
	}
	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}
	
	public String getTenure_from() {
		return tenure_from;
	}
	public void setTenure_from(String tenure_from) {
		this.tenure_from = tenure_from;
	}
	public String getTenure_to() {
		return tenure_to;
	}
	public void setTenure_to(String tenure_to) {
		this.tenure_to = tenure_to;
	}
	public String getTerm_ends() {
		return term_ends;
	}
	public void setTerm_ends(String term_ends) {
		this.term_ends = term_ends;
	}
	public String getNet_worth() {
		return net_worth;
	}
	public void setNet_worth(String net_worth) {
		this.net_worth = net_worth;
	}
	public String getPredecessor() {
		return predecessor;
	}
	public void setPredecessor(String predecessor) {
		this.predecessor = predecessor;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getYear_in_position() {
		return year_in_position;
	}
	public void setYear_in_position(String year_in_position) {
		this.year_in_position = year_in_position;
	}
public Date getBlog_date() {
		return blog_date;
	}
	public void setBlog_date(Date blog_date) {
		this.blog_date = blog_date;
	}
public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
public int getLike_id() {
		return like_id;
	}
	public void setLike_id(int like_id) {
		this.like_id = like_id;
	}
public int getLiked_activity_id() {
		return liked_activity_id;
	}
	public void setLiked_activity_id(int liked_activity_id) {
		this.liked_activity_id = liked_activity_id;
	}
public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategroy_name() {
		return categroy_name;
	}
	public void setCategroy_name(String categroy_name) {
		this.categroy_name = categroy_name;
	}
public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
public int getCommented_user_id() {
		return commented_user_id;
	}
	public void setCommented_user_id(int commented_user_id) {
		this.commented_user_id = commented_user_id;
	}
public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
public int getPost_id_or_news_id() {
		return post_id_or_news_id;
	}
	public void setPost_id_or_news_id(int post_id_or_news_id) {
		this.post_id_or_news_id = post_id_or_news_id;
	}
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
public int getSearch_key_id() {
		return search_key_id;
	}
	public void setSearch_key_id(int search_key_id) {
		this.search_key_id = search_key_id;
	}
public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getSearch_key() {
		return search_key;
	}
	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}
public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
public int getPost_count() {
		return post_count;
	}
	public void setPost_count(int post_count) {
		this.post_count = post_count;
	}
	public int getNews_count() {
		return news_count;
	}
	public void setNews_count(int news_count) {
		this.news_count = news_count;
	}
public int getFollower_id() {
		return follower_id;
	}
	public void setFollower_id(int follower_id) {
		this.follower_id = follower_id;
	}
public int getFollower_count() {
		return follower_count;
	}
	public void setFollower_count(int follower_count) {
		this.follower_count = follower_count;
	}
public int getWeb_id() {
		return web_id;
	}
	public void setWeb_id(int web_id) {
		this.web_id = web_id;
	}
public String getWebsite_name() {
		return website_name;
	}
	public void setWebsite_name(String website_name) {
		this.website_name = website_name;
	}
private String  party_name;
	
	
	public String getProfile_photo_name() {
	return profile_photo_name;
}
public void setProfile_photo_name(String profile_photo_name) {
	this.profile_photo_name = profile_photo_name;
}
public String getProfile_photo_path() {
	return profile_photo_path;
}
public void setProfile_photo_path(String profile_photo_path) {
	this.profile_photo_path = profile_photo_path;
}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getNews_source() {
		return news_source;
	}
	public void setNews_source(String news_source) {
		this.news_source = news_source;
	}

	public String getNews_description_url() {
		return news_description_url;
	}
	public void setNews_description_url(String news_description_url) {
		this.news_description_url = news_description_url;
	}
	public String getNews_url() {
		return news_url;
	}
	public void setNews_url(String news_url) {
		this.news_url = news_url;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getPost_source() {
		return post_source;
	}
	public void setPost_source(String post_source) {
		this.post_source = post_source;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCelebrity_name() {
		return celebrity_name;
	}
	public void setCelebrity_name(String celebrity_name) {
		this.celebrity_name = celebrity_name;
	}
	public String getFacebook_page() {
		return facebook_page;
	}
	public void setFacebook_page(String facebook_page) {
		this.facebook_page = facebook_page;
	}
	public String getTwitter_handle() {
		return twitter_handle;
	}
	public void setTwitter_handle(String twitter_handle) {
		this.twitter_handle = twitter_handle;
	}
	public String getInstagram_page() {
		return instagram_page;
	}
	public void setInstagram_page(String instagram_page) {
		this.instagram_page = instagram_page;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public int getCelebrity_id() {
		return celebrity_id;
	}
	public void setCelebrity_id(int celebrity_id) {
		this.celebrity_id = celebrity_id;
	}
	public int getParty_id() {
		return party_id;
	}
	public void setParty_id(int party_id) {
		this.party_id = party_id;
	}
	public String getParty_name() {
		return party_name;
	}
	public void setParty_name(String party_name) {
		this.party_name = party_name;
	}
	public String getNews_description() {
		return news_description;
	}
	public void setNews_description(String news_description) {
		this.news_description = news_description;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	
}
