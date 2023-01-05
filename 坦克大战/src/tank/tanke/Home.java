package tank.tanke;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Boss
 * @author acer
 *
 */
public class Home {
	private static final int width = 30;
	private static final int height = 30;
	private boolean live=true;//�ж�BOSS�Ƿ����
	private static Image img;
	private int x,y;
	private TankeClient tc;//����̹�� �������  ������Ϸ����ʱ�������
	
	static{
		img=CommonWall.tk.getImage(Home.class.getResource("../../Images/home.jpg"));
	}
	public Home(int x,int y,TankeClient tc){
		this.x=x;
		this.y=y;
	}
	public void draw(Graphics g){
		if(live){
			//����boss�������л���
			g.drawImage(img,x,y,null);
		}else{
			gameOver(g);
		}
	}
	//��Ϸ������Ҫ���Ĳ���  ��Ϸ�������������
	private void gameOver(Graphics gImage) {
		/*tc.others.clear();
		tc.metaWall.clear();
		tc.trees.clear();
		tc.rivers.clear();	*/
		
		Font f=gImage.getFont();
		gImage.setFont(new Font("����",Font.BOLD,40));
		gImage.drawString("�����ˣ�", 310, 300);
		//tc.tanks.clear();����� TankClient ����ʵ�� ��ᱨ��
		//tc.bullets.clear();
		gImage.setFont(f);
	}
	public void setLive(boolean live){
		this.live=live;	
	}
	//�ж�boss�Ƿ���
	public boolean isLive(){
		return live;
	}
	//�����������ڱȽ�
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}













