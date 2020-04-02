/**
 * Filename: LZ4CompressionService.java
 * Package: com.data.compression.service.LZ4CompressionService.java
 * Project: compression
 * Created On: Mar 30, 2020
 * 
 * Description: 
 */
package com.data.compression.service;

import org.springframework.stereotype.Component;

/**
 * @author pavan.p
 *
 */
@Component
public interface LZ4CompressionService {

	public int compressWithLZ4() throws Exception;
	
	public int compressFilesWithLZ4(String filesCount) throws Exception;
}
