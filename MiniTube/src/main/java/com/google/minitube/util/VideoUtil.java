package com.google.minitube.util;

import org.springframework.web.multipart.MultipartFile;

public interface VideoUtil 
{
	public String upload(MultipartFile file);
}
