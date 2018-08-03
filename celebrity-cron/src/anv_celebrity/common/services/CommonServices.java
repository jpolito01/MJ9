package common.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import common.model.CommonModel;

public interface CommonServices {
	public int getTotalCelebrityCount();
	public List<CommonModel> getTrending_celebrities1(int totalCelebCount);
	public int removeCelebrityoftheDay();
	public int insertCelebrityoftheDay(int celeb_id);
	public List<CommonModel> getFollowers();
	public List<CommonModel> getCelebrityListByFollower(int user_id);
	public List<CommonModel> getTodaysNewsforCelebrity(@Param("user_id") int user_id);
}
