package co.doeat.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	int fileUpload(List<MultipartFile> files, int no, String boardCode);

	int selectAtchNo();

	int adminGPIDelete(int postNo, String boardCode);

	int adminCHIDelete(ImageVO vo);

	List<ImageVO> imageList(String boardCode, int postNo);
}
