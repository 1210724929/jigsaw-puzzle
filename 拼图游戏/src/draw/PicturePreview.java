package draw;

import java.awt.*;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 *ͼƬԤ��
 * */
public class PicturePreview extends JPanel {
	//��д �����������ʾͼƬ
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//ָ��ͼƬ·��
		//String filename="picture1\\2.jpg";
		String filename="picture1\\"+PictureCanvas.pictureID+".jpg";
		//��ͼƬ��ɶ���
		ImageIcon icon=new ImageIcon(filename);
		Image image=icon.getImage();
		//����ͼ��
		g.drawImage(image, 25, 25, 160*3,180*4,this);
	}
}
