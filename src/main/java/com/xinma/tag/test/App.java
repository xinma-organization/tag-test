package com.xinma.tag.test;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xinma.base.tag.CloudTag;
import com.xinma.base.tag.TagBuilder;
import com.xinma.base.tag.TagConstants;
import com.xinma.base.tag.TagParser;

/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = LoggerFactory.getLogger(App.class);

	private static int groupCnt = 10000000;
	private static BigInteger startTagId = BigInteger.valueOf(1);

	public static void main(String[] args) {

		try {
			for (int i = 0; i < 100000; i++) {
				BigInteger groupStartId = startTagId.add(BigInteger.valueOf(groupCnt).multiply(BigInteger.valueOf(i)));

				logger.info(groupStartId.toString() + " checked.");

				long tagCount = groupCnt;
				TagBuilder builder = new TagBuilder(TagConstants.CODE_VERSION_1, groupStartId, tagCount);

				CloudTag cloudTag = builder.nextCloudTag();
				while (cloudTag != null) {
					CloudTag decodeTag = TagParser.decodeTagByHiddenCode(cloudTag.getHiddenCode());
					if (!cloudTag.getTagId().equals(decodeTag.getTagId())) {
						logger.error("failed decode by hidden code，当前标签序号 {}，编码tagId is {}, 解码 tagId is {}",
								groupStartId.add(BigInteger.valueOf(builder.getTagIndex())), cloudTag.getTagId(),
								decodeTag.getTagId());
						System.exit(0);
					}
					if (!cloudTag.getCodeVersion().equals(decodeTag.getCodeVersion())) {
						logger.error("failed decode by hidden code，当前标签序号 {}，编码codeVersion is {}, 解码 codeVersion is {}",
								groupStartId.add(BigInteger.valueOf(builder.getTagIndex())), cloudTag.getCodeVersion(),
								decodeTag.getCodeVersion());
						System.exit(0);
					}
					if (!cloudTag.getHiddenCode().equals(decodeTag.getHiddenCode())) {
						logger.error("failed decode by hidden code，当前标签序号 {}，编码hiddenCode is {}, 解码 hiddenCode is {}",
								groupStartId.add(BigInteger.valueOf(builder.getTagIndex())), cloudTag.getHiddenCode(),
								decodeTag.getHiddenCode());
						System.exit(0);
					}
					if (!cloudTag.getHiddenRandomCode().equals(decodeTag.getHiddenRandomCode())) {
						logger.error(
								"failed decode by hidden code，当前标签序号 {}，编码hiddenRandomCode is {}, 解码 hiddenRandomCode is {}",
								groupStartId.add(BigInteger.valueOf(builder.getTagIndex())),
								cloudTag.getHiddenRandomCode(), decodeTag.getHiddenRandomCode());
						System.exit(0);
					}

					decodeTag = TagParser.decodeTagByDisplayCode(cloudTag.getDisplayCode());
					if (!cloudTag.getTagId().equals(decodeTag.getTagId())) {
						logger.error("failed decode by display code，当前标签序号 {}，编码tagId is {}, 解码 tagId is {}",
								groupStartId.add(BigInteger.valueOf(builder.getTagIndex())), cloudTag.getTagId(),
								decodeTag.getTagId());
						System.exit(0);
					}
					if (!cloudTag.getCodeVersion().equals(decodeTag.getCodeVersion())) {
						logger.error(
								"failed decode by display code，当前标签序号 {}，编码codeVersion is {}, 解码 codeVersion is {}",
								groupStartId.add(BigInteger.valueOf(builder.getTagIndex())), cloudTag.getCodeVersion(),
								decodeTag.getCodeVersion());
						System.exit(0);
					}
					if (!cloudTag.getDisplayCode().equals(decodeTag.getDisplayCode())) {
						logger.error(
								"failed decode by display code，当前标签序号 {}，编码displayCode is {}, 解码 displayCode is {}",
								groupStartId.add(BigInteger.valueOf(builder.getTagIndex())), cloudTag.getDisplayCode(),
								decodeTag.getDisplayCode());
						System.exit(0);
					}
					if (!cloudTag.getDisplayRandomCode().equals(decodeTag.getDisplayRandomCode())) {
						logger.error(
								"failed decode by display code，当前标签序号 {}，编码displayRandomCode is {}, 解码 displayRandomCode is {}",
								groupStartId.add(BigInteger.valueOf(builder.getTagIndex())),
								cloudTag.getDisplayRandomCode(), decodeTag.getDisplayRandomCode());
						System.exit(0);
					}

					cloudTag = builder.nextCloudTag();
				}

			}

			logger.info("test program finished.");
		} catch (Throwable e) {
			logger.error("标签测试程序运行异常。", e);
		}
	}
}
