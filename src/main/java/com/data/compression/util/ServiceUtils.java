/**
 * Filename: ServiceUtils.java
 * Package: com.data.compression.util.ServiceUtils.java
 * Project: compression
 * Created On: Mar 30, 2020
 * 
 * Description: 
 */
package com.data.compression.util;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.data.compression.service.LZ4CompressionServiceImpl;

/**
 * @author pavan.p
 *
 */
@Component
public class ServiceUtils {

	private static Logger logger = LoggerFactory.getLogger(LZ4CompressionServiceImpl.class);
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public byte[] readFile() throws IOException {
		byte[] result = null;
		try {
			
			Resource resource = resourceLoader.getResource("classpath:static/files/CompressSampleFile.txt");
			InputStream inputStream = resource.getInputStream();
			result = FileCopyUtils.copyToByteArray(inputStream);
		}
		catch (Exception ex) {
			logger.error("Error occurred while reading sample file", ex);
		}
		return result;
	}
}
