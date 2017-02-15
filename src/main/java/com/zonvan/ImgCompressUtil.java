package com.zonvan;

import java.awt.Image;
// 旧的jpeg处理类
// import com.sun.image.codec.jpeg.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageTypeSpecifier;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import com.sun.imageio.plugins.jpeg.JPEGImageWriter;

/**
 * @author zhouqz
 */
public class ImgCompressUtil {

	/**
	 * log4j
	 */
	private final static Logger logger = Logger.getLogger(ImgCompressUtil.class.getName());

	/**
	 * 图片压缩测试
	 *
	 * @param args
	 */
	public static void main(String args[]) {
		// 图片url，压缩后的宽和高
		String url = "C:/Users/tommy/Desktop/DSCI0138.JPG";
		int w = 400;
		int h = 400;

		String filePath = "C:/Users/tommy/Desktop/test/" + Math.random() + ".jpg";
		//压缩
		ImgCompress(filePath, url, "123", w, h, 0.7f);
		System.out.println("ok");
	}

	/**
	 *
	 * <pre>
	 * Method Name : ImgCompress
	 * Description : ImgCompress
	 * </pre>
	 *
	 * @param filePath
	 * @param url
	 * @param name
	 * @param w
	 * @param h
	 * @param JPEGcompression
	 * @return
	 */

	public static String ImgCompress(String filePath, String url, String name, int w, int h, float JPEGcompression) {
		File file = new File(url);

		try {
			BufferedImage bufferedImage = ImageIO.read(file);

			int new_w = w;
			int new_h = h;

			BufferedImage image_to_save = new BufferedImage(new_w, new_h, bufferedImage.getType());
			image_to_save.getGraphics().drawImage(bufferedImage.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
					0, null);
			FileOutputStream fos = new FileOutputStream(filePath); // 输出到文件流

			// 新的方法
			int dpi = 300;//分辨率





			saveAsJPEG(dpi, image_to_save, JPEGcompression, fos);
			//关闭输出流
			fos.close();
			//返回压缩后的图片地址
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}

		return filePath;

	}

	/**
	 * 以JPEG编码保存图片
	 *
	 * @param dpi
	 *            分辨率
	 * @param image_to_save
	 *            要处理的图像图片
	 * @param JPEGcompression
	 *            压缩比
	 * @param fos
	 *            文件输出流
	 * @throws IOException
	 */
	public static void saveAsJPEG(Integer dpi, BufferedImage image_to_save, float JPEGcompression, FileOutputStream fos)
			throws IOException {

		// Image writer
		JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
		ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
		imageWriter.setOutput(ios);
		// and metadata
		IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(new ImageTypeSpecifier(image_to_save), null);

		if (JPEGcompression >= 0 && JPEGcompression <= 1f) {

			// new Compression
			JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
			jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
			jpegParams.setCompressionQuality(JPEGcompression);

		}

		// new Write and clean up
		imageWriter.write(imageMetaData, new IIOImage(image_to_save, null, null), null);
		ios.close();
		imageWriter.dispose();

	}

}
