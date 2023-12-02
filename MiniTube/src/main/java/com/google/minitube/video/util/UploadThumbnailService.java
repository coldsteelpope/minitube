package com.google.minitube.video.util;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadThumbnailService 
{
	public String upload(MultipartFile file) 
	{
		boolean result = false;
		
		String uploadDir = "C:\\library\\upload\\thumbs";
		
		String fileOriName = file.getOriginalFilename();
		String fileExtension = 
				fileOriName.substring(fileOriName.lastIndexOf("."), fileOriName.length());

		UUID uuid = UUID.randomUUID();
		String uniqueName = uuid.toString().replaceAll("-", "");
		
		File saveFile = new File(uploadDir + "\\" + uniqueName + fileExtension);
		
		if(!saveFile.exists())
		{
			saveFile.mkdirs();
		}
		
		try
		{
			file.transferTo(saveFile);
			result = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(result)
		{
			System.out.println("[UploadThumbnailService] FILE UPLOAD SUCCESS!!");
			return uniqueName + fileExtension;
		}
		else
		{
			System.out.println("[UploadThumbnailService] FILE UPLOAD FAIL!!");
			return null;
		}
	}

}
