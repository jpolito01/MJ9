package common.services;

import java.util.List;

import common.mapper.CommonMapper;
import common.model.AdminModel;

public class CommonServiceImpl implements CommonServices{
	CommonMapper commonMapper;

	public CommonMapper getCommonMapper() {
		return commonMapper;
	}

	public void setCommonMapper(CommonMapper commonMapper) {
		this.commonMapper = commonMapper;
	}

	@Override
	public int checkforRepeatCount() {
		return commonMapper.checkforRepeatCount();
	}

	@Override
	public int getTotalCelebrityCount() {
		return commonMapper.getTotalCelebrityCount();
	}

	@Override
	public List<AdminModel> getTrending_celebrities1(int totalCelebCount) {
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
	public List<AdminModel> getFollowers() {
		return commonMapper.getFollowers();
	}

	@Override
	public List<AdminModel> getCelebrityListByFollower(int user_id) {
		return commonMapper.getCelebrityListByFollower(user_id);
	}

	@Override
	public List<AdminModel> getTodaysNewsforCelebrity(int celeb_id) {
		return commonMapper.getTodaysNewsforCelebrity(celeb_id);
	}
}