package my_turn;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import reference.file_RW;

public class Image_component extends Component {
	private BufferedImage bufferedImage;
	private File file;
	public int height = 250;
	public int width = 500;
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File setfile) throws Exception {
		this.file = setfile;
		set_BufferedImage();
	}
	public void set_BufferedImage() throws Exception {
		// bufferedImage == ImageIO.read(file)
	//System.out.println("set_bufferedimage");
		this.bufferedImage=ImageIO.read(this.getFile());
	}
	public BufferedImage getBufferedImage() {
		return this.bufferedImage;
	}
	
	
	
	 public Dimension getPreferredSize() {
	        if (bufferedImage == null) {
	             return new Dimension(100,100);
	        } else {
//	        	System.out.println("bufferedimage != null");
	           return new Dimension(bufferedImage.getWidth(null), bufferedImage.getHeight(null));
	       }
	    }
	 public void printinfo() {
		System.out.println("printinfo");
		System.out.println("Max size=" + this.getMaximumSize());
		System.out.println("size of image ="+bufferedImage.getWidth()+"*"+bufferedImage.getHeight());
	
	}
	public void paint(Graphics g) {
		
		g.drawImage(bufferedImage,0,0 ,null);
	//	System.out.println("paint triggered");
	}
	
	public Point mouse_position () {
		return this.getMousePosition();
	}
	
	
	
	
	

	
	
}
