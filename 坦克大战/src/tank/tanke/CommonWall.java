package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
/**
 * שǽ��
 * @author acer
 *
 */
public class CommonWall {
	private static final int width = 20;
	private static final int heighr = 20;
	private int x,y;
	private  static Image img;
	public static Toolkit tk=Toolkit.getDefaultToolkit();
	//��̬����� ��ʼ������
	
	static{
		img=tk.getImage(CommonWall.class.getResource("../../Images/commonWall.gif"));
	}
	
	public CommonWall(int x,int y){
		this.x=x;
		this.y=y;
	}
	//����ǽ�巽��
	public void draw(Graphics g){
		g.drawImage(img, x, y, null);
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,width,heighr);
	}
}
