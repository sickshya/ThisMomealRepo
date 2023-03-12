package co.doeat.common.mapper;

import java.util.List;

import co.doeat.common.service.ImageVO;

public interface ImageMapper {

	int fileUpload(ImageVO files);

	int selectAtchNo();

	int adminGPIDelete(int postNo, String boardCode);

	int adminCHIDelete(int postNo, String boardCode);

	int adminEXIDelete(int postNo, String boardCode);

	List<ImageVO> imageList(String boardCode, int postNo);
}
