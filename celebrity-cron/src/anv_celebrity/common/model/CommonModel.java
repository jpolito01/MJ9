package common.model;

import java.util.Date;

public class CommonModel {
	private String id;
	private String  country_name;
	private int country_id;
	private String state_name;
	private String  category_name;
	private int category_id;
	private String  celebrity_name, celebrity_nick_name, profile_photo_name, profile_photo_path;
	private int  state_id,celebrity_id;
	private int is_active;
	private String  news_source, news_author, news_title, news_description_url, news_description, news_date,publication_date;
	private String  content_path;
	private int content_type_id,news_id_or_post_id,is_news_or_post;
	private String image_url;
	private int like_count;
	private int  post_id_or_news_id;
	private int user_id;
	private int activity_type_id;
	private String comments;
	private String name;
	private int comment_id;
	private int comment_count;
	private int follower_id;
	private int follower_count;
	private String facebok_page_name;
	private String post_source, picture,message, story, cretated_time;
	private int post_id;
	private String instagram_page_name;
	private String twitter_handle_name;
	private String celebrity_bio;
	private String search_key;
	private int search_count;
	private String website_name;
	private int web_id;
	private  String  movie_blogs, blog_title,blog_image;
	private int blog_id;
	private Date create_date;
	private String token;
	private int post_count,news_count;
	private String email_id;
	private int celebrity_of_the_day;
	private Date celebrity_of_the_day_date;
	private int movie_id;
	private String caracter;
	private String caracter_name;
	private String casted_celebrity;
	private String celebrity_category_ids;
	private int requested_celebrity_id;
	private int entity_type_id,entity_id,news_id;
	private String instagram_id;
	
	public int getEntity_type_id() {
		return entity_type_id;
	}
	public void setEntity_type_id(int entity_type_id) {
		this.entity_type_id = entity_type_id;
	}
	public int getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(int entity_id) {
		this.entity_id = entity_id;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getInstagram_id() {
		return instagram_id;
	}
	public void setInstagram_id(String instagram_id) {
		this.instagram_id = instagram_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCelebrity_name() {
		return celebrity_name;
	}
	public void setCelebrity_name(String celebrity_name) {
		this.celebrity_name = celebrity_name;
	}
	public String getCelebrity_nick_name() {
		return celebrity_nick_name;
	}
	public void setCelebrity_nick_name(String celebrity_nick_name) {
		this.celebrity_nick_name = celebrity_nick_name;
	}
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
	public int getState_id() {
		return state_id;
	}
	public void setState_id(int state_id) {
		this.state_id = state_id;
	}
	public int getCelebrity_id() {
		return celebrity_id;
	}
	public void setCelebrity_id(int celebrity_id) {
		this.celebrity_id = celebrity_id;
	}
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	public String getNews_source() {
		return news_source;
	}
	public void setNews_source(String news_source) {
		this.news_source = news_source;
	}
	public String getNews_author() {
		return news_author;
	}
	public void setNews_author(String news_author) {
		this.news_author = news_author;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_description_url() {
		return news_description_url;
	}
	public void setNews_description_url(String news_description_url) {
		this.news_description_url = news_description_url;
	}
	public String getNews_description() {
		return news_description;
	}
	public void setNews_description(String news_description) {
		this.news_description = news_description;
	}
	public String getNews_date() {
		return news_date;
	}
	public void setNews_date(String news_date) {
		this.news_date = news_date;
	}
	public String getPublication_date() {
		return publication_date;
	}
	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}
	public String getContent_path() {
		return content_path;
	}
	public void setContent_path(String content_path) {
		this.content_path = content_path;
	}
	public int getContent_type_id() {
		return content_type_id;
	}
	public void setContent_type_id(int content_type_id) {
		this.content_type_id = content_type_id;
	}
	public int getNews_id_or_post_id() {
		return news_id_or_post_id;
	}
	public void setNews_id_or_post_id(int news_id_or_post_id) {
		this.news_id_or_post_id = news_id_or_post_id;
	}
	public int getIs_news_or_post() {
		return is_news_or_post;
	}
	public void setIs_news_or_post(int is_news_or_post) {
		this.is_news_or_post = is_news_or_post;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getActivity_type_id() {
		return activity_type_id;
	}
	public void setActivity_type_id(int activity_type_id) {
		this.activity_type_id = activity_type_id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
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
	public String getFacebok_page_name() {
		return facebok_page_name;
	}
	public void setFacebok_page_name(String facebok_page_name) {
		this.facebok_page_name = facebok_page_name;
	}
	public String getPost_source() {
		return post_source;
	}
	public void setPost_source(String post_source) {
		this.post_source = post_source;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getCretated_time() {
		return cretated_time;
	}
	public void setCretated_time(String cretated_time) {
		this.cretated_time = cretated_time;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getInstagram_page_name() {
		return instagram_page_name;
	}
	public void setInstagram_page_name(String instagram_page_name) {
		this.instagram_page_name = instagram_page_name;
	}
	public String getTwitter_handle_name() {
		return twitter_handle_name;
	}
	public void setTwitter_handle_name(String twitter_handle_name) {
		this.twitter_handle_name = twitter_handle_name;
	}
	public String getCelebrity_bio() {
		return celebrity_bio;
	}
	public void setCelebrity_bio(String celebrity_bio) {
		this.celebrity_bio = celebrity_bio;
	}
	public String getSearch_key() {
		return search_key;
	}
	public void setSearch_key(String search_key) {
		this.search_key = search_key;
	}
	public int getSearch_count() {
		return search_count;
	}
	public void setSearch_count(int search_count) {
		this.search_count = search_count;
	}
	public String getWebsite_name() {
		return website_name;
	}
	public void setWebsite_name(String website_name) {
		this.website_name = website_name;
	}
	public int getWeb_id() {
		return web_id;
	}
	public void setWeb_id(int web_id) {
		this.web_id = web_id;
	}
	public String getMovie_blogs() {
		return movie_blogs;
	}
	public void setMovie_blogs(String movie_blogs) {
		this.movie_blogs = movie_blogs;
	}
	public String getBlog_title() {
		return blog_title;
	}
	public void setBlog_title(String blog_title) {
		this.blog_title = blog_title;
	}
	public String getBlog_image() {
		return blog_image;
	}
	public void setBlog_image(String blog_image) {
		this.blog_image = blog_image;
	}
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
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
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getCaracter() {
		return caracter;
	}
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
	public String getCaracter_name() {
		return caracter_name;
	}
	public void setCaracter_name(String caracter_name) {
		this.caracter_name = caracter_name;
	}
	public String getCasted_celebrity() {
		return casted_celebrity;
	}
	public void setCasted_celebrity(String casted_celebrity) {
		this.casted_celebrity = casted_celebrity;
	}
	public String getCelebrity_category_ids() {
		return celebrity_category_ids;
	}
	public void setCelebrity_category_ids(String celebrity_category_ids) {
		this.celebrity_category_ids = celebrity_category_ids;
	}
	public int getRequested_celebrity_id() {
		return requested_celebrity_id;
	}
	public void setRequested_celebrity_id(int requested_celebrity_id) {
		this.requested_celebrity_id = requested_celebrity_id;
	}
}