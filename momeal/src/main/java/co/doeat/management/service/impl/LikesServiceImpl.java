package co.doeat.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.LikesMapper;
import co.doeat.management.service.LikesService;

@Service
public class LikesServiceImpl implements LikesService {

	@Autowired
	private LikesMapper likesMapper;

}
