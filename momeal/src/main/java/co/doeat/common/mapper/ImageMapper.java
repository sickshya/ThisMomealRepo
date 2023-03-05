package co.doeat.common.mapper;

import co.doeat.common.service.ImageVO;

public interface ImageMapper {

	int fileUpload(ImageVO files);

	int selectAtchNo();

	int adminGPIDelete(int postNo, String boardCode);

	int adminCHIDelete(ImageVO vo);
}
