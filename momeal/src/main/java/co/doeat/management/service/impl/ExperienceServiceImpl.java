package co.doeat.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.doeat.management.mapper.ExperienceMapper;
import co.doeat.management.service.ExperienceService;
import co.doeat.management.service.ExperienceVO;

@Service
public class ExperienceServiceImpl implements ExperienceService {

	@Autowired
	private ExperienceMapper experienceMapper;

	@Override
	public List<ExperienceVO> getExperList() {
		return experienceMapper.getExperList();
	}

	@Override
	public List<ExperienceVO> getExperiencing() {
		return experienceMapper.getExperiencing();
	}

	// 관리자 +++++++++++++++++++++++++++++++++++
	@Override
	public List<ExperienceVO> adminExperienceGroup() {
		return experienceMapper.adminExperienceGroup();
	}

}
