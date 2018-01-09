package com.dascom.product.serviceImpl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dascom.product.dao.CpUploadMapper;
import com.dascom.product.entity.CpUpload;
import com.dascom.product.service.UploadService;

@Service
public class UploadServiceimpl implements UploadService {
	
	@Autowired
	private CpUploadMapper cpUploadMapper;
	
	@Override
	public int add(String path, String size, String suffix) {
		CpUpload upload =new CpUpload();
		upload.setAbsolutePath(path );
		upload.setFileSize(size);
		upload.setName(path.substring(path.lastIndexOf("\\")+1));
		upload.setType(suffix);
		upload.setUpTime(new Date());
		return cpUploadMapper.insertSelective(upload);
	}

}
