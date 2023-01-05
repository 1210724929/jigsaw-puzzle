package draw;

import javax.swing.Icon;
import javax.swing.JButton;
/*
 * 图片小方格
 * */
public class Cell extends JButton {
	//带有图片的小方格
	public Cell(Icon icon) {
		super(icon);
		//方格大小
		this.setSize(160,180);
	}
	//带有图片和文字的小方格 
	public Cell(String text, Icon icon) {
		super(text, icon);
		this.setSize(160,180);
		this.setHorizontalTextPosition(CENTER);//文字水平居中
		this.setVerticalTextPosition(CENTER);//文字垂直居中
	}
	
	//当前方格移动
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





















