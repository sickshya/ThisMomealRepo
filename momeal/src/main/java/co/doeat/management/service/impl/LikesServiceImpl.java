package co.doeat.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.LikesMapper;
import co.doeat.management.service.LikesService;
import co.doeat.management.service.LikesVO;

@Service
public class LikesServiceImpl implements LikesService {

	@Autowired
	private LikesMapper likesMapper;

	@Override
	public List<Map<String, Object>> myLikeList(String userId) {
		return likesMapper.myLikeList(userId);
	}

	@Override
	public Map<String, Object> mylikeSelect(LikesVO vo) {
		return likesMapper.mylikeSelect(vo);
	}

	@Override
	public int myLikeDel(LikesVO vo) {
		return likesMapper.myLikeDel(vo);
	}

	@Override
	public List<Map<String, Object>> myChallList(String userId) {
		return likesMapper.myChallList(userId);
	}
//
//	@Override
//	public int insertLikeMeal(LikesVO vo) {
//		return likesMapper.insertLikeMeal(vo);
//	}

	@Override
	public int insertLikeChall(LikesVO vo) {
		return likesMapper.insertLikeChall(vo);
	}
}
