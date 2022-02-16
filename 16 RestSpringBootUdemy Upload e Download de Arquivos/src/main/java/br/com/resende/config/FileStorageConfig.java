package br.com.resende.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

										//faz o valor de uploadDir ser o valor 
										//de  file.upload-dir em aplication properties(necessario 
@ConfigurationProperties(prefix="file")	//tambem fazer alteracao em Startup.java)
public class FileStorageConfig {

	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
	
	
}
