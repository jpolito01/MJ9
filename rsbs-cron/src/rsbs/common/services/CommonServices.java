package common.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.model.AdminModel;

public interface CommonServices {
	public int checkforRepeatCount();
	public int getTotalCelebrityCount();
	public List<AdminModel> getTrending_celebrities1(int totalCelebCount);
	public int removeCelebrityoftheDay();
	public int insertCelebrityoftheDay(int celeb_id);
	public List<AdminModel> getFollowers();
	public List<AdminModel> getCelebrityListByFollower(int user_id);
	public List<AdminModel> getTodaysNewsforCelebrity(@Param("celeb_id") int celeb_id);
}