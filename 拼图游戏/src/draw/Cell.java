package draw;

import javax.swing.Icon;
import javax.swing.JButton;
/*
 * ͼƬС����
 * */
public class Cell extends JButton {
	//����ͼƬ��С����
	public Cell(Icon icon) {
		super(icon);
		//�����С
		this.setSize(160,180);
	}
	//����ͼƬ�����ֵ�С���� 
	public Cell(String text, Icon icon) {
		super(text, icon);
		this.setSize(160,180);
		this.setHorizontalTextPosition(CENTER);//����ˮƽ����
		this.setVerticalTextPosition(CENTER);//���ִ�ֱ����
	}
	
	//��ǰ�����ƶ�
	public void move(String direction) {
		switch (direction) {
		case "UP":
			this.setLocation(this.getBounds().x,this.getBounds().y-180);
			break;
		case "DOWN":
			this.setLocation(this.getBounds().x,this.getBounds().y+180);		
			break;
		case "LEFT":
			this.setLocation(this.getBounds().x-160,this.getBounds().y);
			break;		
		case "RIGHT":
			this.setLocation(this.getBounds().x+160,this.getBounds().y);
			break;
		default:
			break;
		}
	}

}





















