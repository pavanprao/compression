/**
 * Filename: CompressionController.java
 * Package: com.data.compression.controller.CompressionController.java
 * Project: compression
 * Created On: Mar 30, 2020
 * 
 * Description: 
 */
package com.data.compression.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.data.compression.service.LZ4CompressionService;

/**
 * @author pavan.p
 *
 */
@Component
public class CompressionController implements IController {

	@Autowired
	private LZ4CompressionService lz4CompressionService;
	
	@Override
	public String compressWithLZ4() throws Exception {
		int result = lz4CompressionService.compressWithLZ4();
		if (result > 0) {
			return ResponseEntity.ok("SUCCESS").toString();
		}
		return ResponseEntity.ok("FAILURE").toString();
	}

	@Override
	public String compressFilesWithLZ4(String filesCount) throws Exception {
		int result = lz4CompressionService.compressFilesWithLZ4(filesCount);
		if (result > 0) {
			return ResponseEntity.ok("SUCCESS").toString();
		}
		return ResponseEntity.ok("FAILURE").toString();
	}

}
