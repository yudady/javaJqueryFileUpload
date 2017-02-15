package com.zonvan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class Test01 {

	public static void main(String[] args) throws IOException {

		BufferedImage image1 = ImageIO.read(new File("C:/Users/tommy/Desktop/DSCI0138.JPG")); // 读入文件
		BufferedImage image2 = ImageIO.read(new File("C:/Users/tommy/Desktop/Snap1.png")); // 读入文件
		BufferedImage image3 = ImageIO.read(new File("C:/Users/tommy/Desktop/Snap3.gif")); // 读入文件

		BufferedImage thumbnail1 = Scalr.resize(image1, 420);
		BufferedImage thumbnail2 = Scalr.resize(image2, 420);
		BufferedImage thumbnail3 = Scalr.resize(image3, 420);

		ImageIO.write(thumbnail1, "JPEG", new File("C:/Users/tommy/Desktop/test/" + Math.random() + ".JPG"));// 输出到文件流
		ImageIO.write(thumbnail2, "png", new File("C:/Users/tommy/Desktop/test/" + Math.random() + ".png"));// 输出到文件流
		ImageIO.write(thumbnail3, "gif", new File("C:/Users/tommy/Desktop/test/" + Math.random() + ".gif"));// 输出到文件流

		System.out.println("ok");
	}
}
