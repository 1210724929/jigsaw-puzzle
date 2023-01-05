package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
/**
 * 砖墙类
 * @author acer
 *
 */
public class CommonWall {
	private static final int width = 20;
	private static final int heighr = 20;
	private int x,y;
	private  static Image img;
	public static Toolkit tk=Toolkit.getDefaultToolkit();
	//静态代码块 初始化数据
	
	static{
		img=tk.getImage(CommonWall.class.getResource("../../Images/commonWall.gif"));
	}
	
	public CommonWall(int x,int y){
		this.x=x;
		this.y=y;
	}
	//绘制墙体方法
	public void draw(Graphics g){
		g.drawImage(img, x, y, null);
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,width,heighr);
	}
}
