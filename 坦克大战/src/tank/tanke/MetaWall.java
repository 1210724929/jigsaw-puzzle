package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * ��ש
 * @author acer
 *
 */
public class MetaWall {
	private static final int width = 40,height=40;
	static Image img;
	private int x,y;
	//private static Toolkit tk=Toolkit.getDefaultToolkit();��ש��CommonWallʱ������Ϊ��Public
	
	//��̬����� ��ʼ������
	
	static{
		//���ļ��е�ͼƬת����Image�����Թ�ʹ��
		img=CommonWall.tk.getImage(River.class.getResource("../../Images/metalWall.gif"));
	}
	
	public MetaWall(int x,int y){
		this.x=x;
		this.y=y;
	}
	public void draw(Graphics g){
		g.drawImage(img, x, y, null);
	}
	//�������� ���ڱȽ�
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}

