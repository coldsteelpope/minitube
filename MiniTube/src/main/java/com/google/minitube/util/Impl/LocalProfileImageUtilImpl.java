package com.google.minitube.util.Impl;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.google.minitube.constants.Path;
import com.google.minitube.util.ProfileImageUtil;

@Component
public class LocalProfileImageUtilImpl implements ProfileImageUtil
{
	public String upload(MultipartFile file)
	{
		String fileName = file.getOriginalFilename();
		String fileExtension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replace("-", "");
		
		String saveFilePath = Path.UPLOAD_PROFILE_IMAGE_PATH + "\\" + uniqueName + fileExtension;
		File saveFile = new File(saveFilePath);
		
		if(saveFile.exists() == false)
		{
			saveFile.mkdir();
		}
		
		try
		{
			file.transferTo(saveFile);
			System.out.println("[ProfileImageUtil] upload");
			return uniqueName + fileExtension;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
