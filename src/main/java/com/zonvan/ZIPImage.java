package com.zonvan;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片工具类 压缩图片大小
 *
 * @author Cyw
 * @version 1.0
 */
public class ZIPImage {
	private File file = null;
	private String outPutFilePath;
	private String inPutFilePath;
	private String inPutFileName;
	private boolean autoBuildFileName;
	private String outPutFileName;
	private int outPutFileWidth = 100; // 默认输出图片宽
	private int outPutFileHeight = 100; // 默认输出图片高
	private static boolean isScaleZoom = true; // 是否按比例缩放

	public ZIPImage() {
		outPutFilePath = "";
		inPutFilePath = "";
		inPutFileName = "";
		autoBuildFileName = true;
		outPutFileName = "";
	}

	/**
	 *
	 * @param ipfp
	 *            源文件夹路径
	 * @param ipfn
	 *            源文件名
	 * @param opfp
	 *            目标文件路径
	 * @param opfn
	 *            目标文件名
	 */
	public ZIPImage(String ipfp, String ipfn, String opfp, String opfn) {
		outPutFilePath = opfp;
		inPutFilePath = ipfp;
		inPutFileName = ipfn;
		autoBuildFileName = true;
		outPutFileName = opfn;
	}

	/**
	 *
	 * @param ipfp
	 *            源文件夹路径
	 * @param ipfn
	 *            源文件名
	 * @param opfp
	 *            目标文件路径
	 * @param opfn
	 *            目标文件名
	 * @param aBFN
	 *            是否自动生成目标文件名
	 */
	public ZIPImage(String ipfp, String ipfn, String opfp, String opfn, boolean aBFN) {
		outPutFilePath = opfp;
		inPutFilePath = ipfp;
		inPutFileName = ipfn;
		autoBuildFileName = aBFN;
		outPutFileName = opfn;
	}

	public boolean isAutoBuildFileName() {
		return autoBuildFileName;
	}

	public void setAutoBuildFileName(boolean autoBuildFileName) {
		this.autoBuildFileName = autoBuildFileName;
	}

	public String getInPutFilePath() {
		return inPutFilePath;
	}

	public void setInPutFilePath(String inPutFilePath) {
		this.inPutFilePath = inPutFilePath;
	}

	public String getOutPutFileName() {
		return outPutFileName;
	}

	public void setOutPutFileName(String outPutFileName) {
		this.outPutFileName = outPutFileName;
	}

	public String getOutPutFilePath() {
		return outPutFilePath;
	}

	public void setOutPutFilePath(String outPutFilePath) {
		this.outPutFilePath = outPutFilePath;
	}

	public int getOutPutFileHeight() {
		return outPutFileHeight;
	}

	public void setOutPutFileHeight(int outPutFileHeight) {
		this.outPutFileHeight = outPutFileHeight;
	}

	public int getOutPutFileWidth() {
		return outPutFileWidth;
	}

	public void setOutPutFileWidth(int outPutFileWidth) {
		this.outPutFileWidth = outPutFileWidth;
	}

	public boolean isScaleZoom() {
		return isScaleZoom;
	}

	public void setScaleZoom(boolean isScaleZoom) {
		this.isScaleZoom = isScaleZoom;
	}

	public String getInPutFileName() {
		return inPutFileName;
	}

	public void setInPutFileName(String inPutFileName) {
		this.inPutFileName = inPutFileName;
	}

	/**
	 * 压缩图片大小
	 *
	 * @return boolean
	 */
	public boolean compressImage() {
		boolean flag = false;
		try {
			if (inPutFilePath.trim().equals("")) {
				throw new NullPointerException("源文件夹路径不存在。");
			}
			if (inPutFileName.trim().equals("")) {
				throw new NullPointerException("图片文件路径不存在。");
			}
			if (outPutFilePath.trim().equals("")) {
				throw new NullPointerException("目标文件夹路径地址为空。");
			} else {
				if (!ZIPImage.mddir(outPutFilePath)) {
					throw new FileNotFoundException(outPutFilePath + " 文件夹创建失败！");
				}
			}
			if (this.autoBuildFileName) { // 自动生成文件名
				String tempFile[] = getFileNameAndExtName(inPutFileName);
				outPutFileName = tempFile[0] + "_cyw." + tempFile[1];
				compressPic();
			} else {
				if (outPutFileName.trim().equals("")) {
					throw new NullPointerException("目标文件名为空。");
				}
				compressPic();
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
			return flag;
		}
		return flag;
	}

	// 图片处理
	private void compressPic() throws Exception {
		try {
			// 获得源文件
			file = new File(inPutFilePath + inPutFileName);
			if (!file.exists()) {
				throw new FileNotFoundException(inPutFilePath + inPutFileName + " 文件不存在！");
			}
			Image img = ImageIO.read(file);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1) {
				throw new Exception("文件不可读！");
			} else {
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (ZIPImage.isScaleZoom == true) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null)) / (double) outPutFileWidth + 0.1;
					double rate2 = ((double) img.getHeight(null)) / (double) outPutFileHeight + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {
					newWidth = outPutFileWidth; // 输出的图片宽度
					newHeight = outPutFileHeight; // 输出的图片高度
				}
				BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
				/* Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢 */
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
//				FileOutputStream out = new FileOutputStream(outPutFilePath + outPutFileName);
//				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//				encoder.encode(tag);
//				out.close();

				ImageIO.write(tag,  "jpeg" , new File(outPutFilePath + outPutFileName));
				tag.flush();


			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 创建文件夹目录
	 *
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static boolean mddir(String filePath) throws Exception {
		boolean flag = false;
		File f = new File(filePath);
		if (!f.exists()) {
			flag = f.mkdirs();
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * 获得文件名和扩展名
	 *
	 * @param fullFileName
	 * @return
	 * @throws Exception
	 */
	private String[] getFileNameAndExtName(String fullFileName) throws Exception {
		String[] fileNames = new String[2];
		if (fullFileName.indexOf(".") == -1) {
			throw new Exception("源文件名不正确！");
		} else {
			fileNames[0] = fullFileName.substring(0, fullFileName.lastIndexOf("."));
			fileNames[1] = fullFileName.substring(fullFileName.lastIndexOf(".") + 1);
		}
		return fileNames;
	}

	public Image getSourceImage() throws IOException {
		//获得源文件
		file = new File(inPutFilePath + inPutFileName);
		if (!file.exists()) {
			throw new FileNotFoundException(inPutFilePath + inPutFileName + " 文件不存在！");
		}
		Image img = ImageIO.read(file);
		return img;
	}

	/* 获得图片大小
	 *
	 * @path ：图片路径 */
	public long getPicSize(String path) {
		File file = new File(path);
		return file.length();
	}







	public static void main(String[] args) throws IOException {

		String filePath = "C:/Users/tommy/Desktop/test/" + Math.random() + ".jpg";

		ZIPImage zip = new ZIPImage("C:/Users/tommy/Desktop/", "DSCI0138.JPG", "C:/Users/tommy/Desktop/test/", "处理后的图片.jpg", false);
		zip.setOutPutFileWidth(500);
		zip.setOutPutFileHeight(500);

		Image image = zip.getSourceImage();
		long size = zip.getPicSize("C:/Users/tommy/Desktop/test/1.jpg");
		System.out.println("处理前的图片大小 width:" + image.getWidth(null));
		System.out.println("处理前的图片大小 height:" + image.getHeight(null));
		System.out.println("处理前的图片容量:" + size + " bit");

		zip.compressImage();
	}
}
