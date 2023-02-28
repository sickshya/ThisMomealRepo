package co.doeat.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.LikesMapper;
import co.doeat.management.service.LikesService;

@Service
public class LikesServiceImpl implements LikesService {

	@Autowired
	private LikesMapper likesMapper;

	@Override
	public List<Map<String, Object>> myLikeList() {
		return likesMapper.myLikeList();
	}

	@Override
	public Map<String, Object> mylikeSelect(String userId) {
		return likesMapper.mylikeSelect(userId);
	}

}
