package action.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

public class ThumbnailMaker {

	public static void createThumbnail(String directory, String file, byte[] image, int width, int height) throws IOException {
		String extension = file.substring(file.lastIndexOf(".")  + 1);

		// load image (convert inputstream to bufferedImage)
		ByteArrayInputStream is = new ByteArrayInputStream(image);
		BufferedImage originImg = ImageIO.read(is);
		
		BufferedImage thumbImg = Scalr.resize(originImg, Method.QUALITY, Mode.FIT_TO_HEIGHT, width, height, Scalr.OP_ANTIALIAS);
		// write image (convert bufferedImage to outpurstream)
		//ByteArrayOutputStream os = new ByteArrayOutputStream();
		//ImageIO.write(thumbImg, extension, os);
		
		// write image to a file
		File thumbFile = new File(directory, file);
		ImageIO.write(thumbImg, extension, thumbFile);
	}
	
	public static void createThumbnail(File directory, File file, int width, int height) throws IOException {
		String name = file.getName();
		String extension = name.substring(name.lastIndexOf(".")  + 1);

		// load image (convert file to bufferedImage)
		BufferedImage originImg = ImageIO.read(file);		
		BufferedImage thumbImg = Scalr.resize(originImg, Method.QUALITY, Mode.FIT_TO_HEIGHT, width, height, Scalr.OP_ANTIALIAS);
		
		// write image to a file
		File thumbFile = new File(directory, "th_" + name);
		ImageIO.write(thumbImg, extension, thumbFile);
	}
	
}
