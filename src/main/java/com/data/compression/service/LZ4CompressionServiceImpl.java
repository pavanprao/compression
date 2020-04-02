/**
 * Filename: LZ4CompressionServiceImpl.java
 * Package: com.data.compression.service.LZ4CompressionServiceImpl.java
 * Project: compression
 * Created On: Mar 30, 2020
 * 
 * Description: 
 */
package com.data.compression.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.compression.util.ServiceUtils;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4CompressorWithLength;
import net.jpountz.lz4.LZ4DecompressorWithLength;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;

/**
 * @author pavan.p
 *
 */
@Service
public class LZ4CompressionServiceImpl implements LZ4CompressionService {

	private static Logger logger = LoggerFactory.getLogger(LZ4CompressionServiceImpl.class);

	@Autowired
	private ServiceUtils serviceUtils;

	@Override
	public int compressWithLZ4() throws Exception {
		logger.info("Starting Compression Process...");

		byte[] fileBytes = serviceUtils.readFile();
		logger.info("File No.: {}", 1);
		logger.info("Data Size Before: " + fileBytes.length);

		byte[] compressed = compress(fileBytes);
		byte[] decompressed = decompress(compressed);

		if (decompressed.length == fileBytes.length) {
			logger.info("LZ4 Compression and Decompression successful for File No.: {}", 1);
			return 1;
		}
		return 0;
	}

	@Override
	public int compressFilesWithLZ4(String filesCount) throws Exception {
		logger.info("Starting Compression Process...");

		int count = 0;
		for (int i = 1; i <= Integer.valueOf(filesCount); i++) {
			byte[] fileBytes = serviceUtils.readFile();
			logger.info("File No.: {}", i);
			logger.info("Data Size Before: " + fileBytes.length);
			byte[] compressed = compress(fileBytes);
			byte[] decompressed = decompress(compressed);

			if (decompressed.length == fileBytes.length) {
				logger.info("LZ4 Compression and Decompression successful for File No.: {}", i);
				count++;
			}
		}
		
		if (count == Integer.valueOf(filesCount)) {
			return 1;
		}
		return 0;
	}

	/**
	 * This method implements LZ4 Compression.
	 *
	 * @param fileBytes
	 * @throws IOException
	 * @return byte[]
	 */
	private byte[] compress(byte[] fileBytes) throws IOException {
		LZ4Factory factory = LZ4Factory.fastestInstance();

		logger.info(">>> LZ4 Compression >>>");
		LZ4Compressor compressor = factory.fastCompressor();
		LZ4CompressorWithLength compressorWrapper = new LZ4CompressorWithLength(compressor);
		int maxCompressedLength = compressorWrapper.maxCompressedLength(fileBytes.length);

		long startTime1 = System.currentTimeMillis();
		byte[] compressed = compressorWrapper.compress(fileBytes);
		long endTime1 = System.currentTimeMillis();
		logger.info("Time Taken for Compression: {}", (endTime1 - startTime1));

		logger.info("Data Compressed: {}", maxCompressedLength);
		logger.info("Data Size After: {}", compressed.length);

		return compressed;
	}

	/**
	 * This method implements LZ4 Decompression.
	 *
	 * @param fileBytes
	 * @throws IOException
	 * @return byte[]
	 */
	private byte[] decompress(byte[] fileBytes) throws IOException {
		LZ4Factory factory = LZ4Factory.fastestInstance();

		logger.info("Data Size After Decode: {}", fileBytes.length);
		logger.info(">>> LZ4 Decompression >>>");
		LZ4FastDecompressor decompressor = factory.fastDecompressor();
		LZ4DecompressorWithLength decompressorWrapper = new LZ4DecompressorWithLength(decompressor);
		int maxDecompressedLength = LZ4DecompressorWithLength.getDecompressedLength(fileBytes);
		byte[] decompressed = new byte[maxDecompressedLength];
		logger.info("Decompressed Length: {}", decompressed.length);

		long startTime2 = System.currentTimeMillis();
		decompressed = decompressorWrapper.decompress(fileBytes);
		long endTime2 = System.currentTimeMillis();
		logger.info("Time Taken for Decompression: {}", (endTime2 - startTime2));

		logger.info("Data Bytes read for Decompression: {}", maxDecompressedLength);
		logger.info("Data Size After Decompression: {}", decompressed.length);

		return decompressed;
	}

}
