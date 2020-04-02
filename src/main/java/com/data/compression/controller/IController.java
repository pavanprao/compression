/**
 * Filename: IController.java
 * Package: com.data.compression.controller.IController.java
 * Project: compression
 * Created On: Mar 30, 2020
 * 
 * Description: 
 */
package com.data.compression.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pavan.p
 *
 */
@RestController
@RequestMapping("/compress")
public interface IController {

	@GetMapping("/v1/compressWithLZ4")
	public String compressWithLZ4() throws Exception;
	
	@GetMapping("/v1/compressFilesWithLZ4")
	public String compressFilesWithLZ4(@RequestParam String filesCount) throws Exception;
}
