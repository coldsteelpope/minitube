package com.google.minitube.util;

import org.springframework.web.multipart.MultipartFile;

public interface ThumbnailImageUtil 
{
	public String upload(MultipartFile file);
}
