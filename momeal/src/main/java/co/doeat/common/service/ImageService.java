package co.doeat.common.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	int fileUpload(List<MultipartFile> files);
	int selectAtchNo();
}
