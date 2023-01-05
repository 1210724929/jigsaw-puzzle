package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 钢砖
 * @author acer
 *
 */
public class MetaWall {
	private static final int width = 40,height=40;
	static Image img;
	private int x,y;
	//private static Toolkit tk=Toolkit.getDefaultToolkit();在砖块CommonWall时候设置为了Public
	
	//静态代码块 初始化数据
	
	static{
		//把文件中的图片转化成Image对象，以供使用
		img=CommonWall.tk.getImage(River.class.getResource("../../Images/metalWall.gif"));
	}
	
	public MetaWall(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void draw(Graphics g){
		g.drawImage(img, x, y, null);
	}
	//创建矩形 用于比较
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}

