package draw;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 *图片预览
 * */
public class PicturePreview extends JPanel {
	//重写 绘制组件，显示图片
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//指定图片路径
		//String filename="picture1\\2.jpg";
		String filename="picture1\\"+PictureCanvas.pictureID+".jpg";
		//将图片变成对象
		ImageIcon icon=new ImageIcon(filename);
		Image image=icon.getImage();
		//绘制图形
		g.drawImage(image, 25, 25, 160*3,180*4,this);
	}
}
