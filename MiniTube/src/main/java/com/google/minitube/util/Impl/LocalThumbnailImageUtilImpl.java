package com.google.minitube.util.Impl;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.constants.Path;
import com.google.minitube.util.ThumbnailImageUtil;

public class LocalThumbnailImageUtilImpl implements ThumbnailImageUtil 
{
	@Override
	public String upload(MultipartFile file) 
	{
		String fileName = file.getOriginalFilename();
		String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replace("-", "");
		
		String saveFilePath = Path.UPLOAD_THUMBNAIL_IMAGE_PATH + "\\" + uniqueName + fileExtension;
		File saveFile = new File(saveFilePath);
		
		if(saveFile.exists() == false)
		{
			saveFile.mkdirs();
		}
		
		try
		{
			file.transferTo(saveFile);
			System.out.println("[LocalThumbnailImageUtilImpl] upload");
			return uniqueName + fileExtension;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
