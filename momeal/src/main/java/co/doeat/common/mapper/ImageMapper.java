package co.doeat.common.mapper;

import co.doeat.common.service.ImageVO;

public interface ImageMapper {
	
	int fileUpload(ImageVO files);
	int selectAtchNo();
}
