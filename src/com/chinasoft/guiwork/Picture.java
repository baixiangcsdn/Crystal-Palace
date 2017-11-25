package com.chinasoft.guiwork;

import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Picture {

	public ImageIcon getIco(String number) throws Exception{
		File f=new File("pic\\"+number+".jpg");
		ImageIcon icon=new ImageIcon(ImageIO.read(new FileInputStream(f)));
		return icon;
	}
}
