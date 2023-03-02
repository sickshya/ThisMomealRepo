package co.doeat.common.mapper;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.doeat.common.service.ImageVO;

public interface ImageMapper {
	
	int fileUpload(ImageVO files);
	int selectAtchNo();
}
