package common.services;

import java.util.List;

import common.mapper.CommonMapper;
import common.model.CommonModel;

public class CommonServicesImpl implements CommonServices{
	CommonMapper commonMapper;

	public CommonMapper getCommonMapper() {
		return commonMapper;
	}

	public void setCommonMapper(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	@Override
	public int getTotalCelebrityCount() {
		return commonMapper.getTotalCelebrityCount();
	}

	@Override
	public List<CommonModel> getTrending_celebrities1(int totalCelebCount) {
		return commonMapper.getTrending_celebrities1(totalCelebCount);
	}

	@Override
	public int removeCelebrityoftheDay() {
		return commonMapper.removeCelebrityoftheDay();
	}

	@Override
	public int insertCelebrityoftheDay(int celeb_id) {
		return commonMapper.insertCelebrityoftheDay(celeb_id);
	}

	@Override
	public List<CommonModel> getFollowers() {
		return commonMapper.getFollowers();
	}

	@Override
	public List<CommonModel> getCelebrityListByFollower(int user_id) {
		return commonMapper.getCelebrityListByFollower(user_id);
	}

	@Override
	public List<CommonModel> getTodaysNewsforCelebrity(int user_id) {
		return commonMapper.getTodaysNewsforCelebrity(user_id);
	}
}