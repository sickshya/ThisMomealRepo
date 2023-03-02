package co.doeat.common.service.impl;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.doeat.common.mapper.ImageMapper;
import co.doeat.common.service.ImageService;
import co.doeat.common.service.ImageVO;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageMapper imageMapper;
	
	@Value("${momeal.saveImg}")
	private String saveImg;


	@Override
	public int fileUpload(List<MultipartFile> files) {
		//파일 저장위치 설정
		int atchNo = 0;
		//파일 있으면 if문 실행
		if(files != null && !files.isEmpty()) {
			atchNo = imageMapper.selectAtchNo();
		//파일 개수만큼 for문
			for(MultipartFile file: files) {
				if(file.getSize()==0)
					continue;
			//insert할 atchVO
				ImageVO image = new ImageVO();
				
				 //파일이름 중복시 덮어쓰기 되는 거 방지하기 위해 uuid 생성
	            String fileName = UUID.randomUUID().toString();

	            // uuid에 원본파일명 붙이기
	            fileName = fileName + "_" + file.getOriginalFilename();

	            //  파일 실제 저장
	            File uploadFile = new File(saveImg, fileName);

	            try {
	               file.transferTo(uploadFile); // 실제파일저장
	            } catch (Exception e) {
	               e.printStackTrace();
	            }
	            //파일 저장하고 나서 atch 테이블에 insert할 vo에 값 담아주기
	            //no =>selectkey, board type=hidden으로 줌, 
	           image.setAtchNo(atchNo);
	           image.setAtchImg(file.getOriginalFilename());
	           image.setAtchPath("/upload/"+fileName);
	           image.setAtchExtn("jpg");
	           image.setAtchUUID(fileName);
	           image.setAtchSize(file.getSize());
	           imageMapper.fileUpload(image);
			}
			
		}
		
		return atchNo;
	}

	@Override
	public int selectAtchNo() {
		return 0;
	}
	
	
	
	
}
