package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

/**
 * �ӵ���
 * @author acer
 *
 */
public class Bullets {
	public static int speedX=10,speedY=10;
	
	public static final int width=10,height=10;
	private int x,y;
	Direction direction;
	private boolean live=true;//�ӵ�״̬
	private boolean isMine;//����з��ӵ�
	private  static Image[] bulletIamges=null;
	private TankeClient tc;
	
	static{
		bulletIamges=new Image[]{
			//��ͬ�����ӵ������ͼƬת�ɶ���
				CommonWall.tk.getImage(Bullets.class.getResource("../../Images/bulletL.gif")),
				CommonWall.tk.getImage(Bullets.class.getResource("../../Images/bulletU.gif")),
				CommonWall.tk.getImage(Bullets.class.getResource("../../Images/bulletR.gif")),
				CommonWall.tk.getImage(Bullets.class.getResource("../../Images/bulletD.gif"))
		};
	}
	public Bullets(){
		
	}
	public Bullets(int x,int y,boolean isMine,Direction dir,TankeClient tc){
		this.x=x;
		this.y=y;
		this.isMine=isMine;
		this.direction=dir;
		this.tc=tc;
	}
	//���� �ӵ�
	public void draw(Graphics g){
		if (!live) {
			//�ӵ�״̬�ǵ�ʱ��
			tc.bullets.remove(this);
			return;
		}
		switch (direction) {//���ݷ���ѡ��ͼƬ �� ����
		case L:
			g.drawImage(bulletIamges[0], x, y, null);
			x-=speedX;
			break;
		case U:
			g.drawImage(bulletIamges[1], x, y, null);
			y-=speedY;
			break;
		case R:
			g.drawImage(bulletIamges[2], x, y, null);
			x+=speedX;
			break;
		case D:
			g.drawImage(bulletIamges[3], x, y, null);
			y+=speedY;
		break;
		}
		
		//���ӵ������߽�  �ı�״̬
		if (x<0|| y<0|| x>TankeClient.WIDTH || y>TankeClient.HEIGHT) {
			live=false;
		} 
	}
	public boolean islive(){
		return live;
	}
	public Rectangle getRect(){
		return new Rectangle(x,y,width,height);
	}
	//���ӵ���̹����ײʱ
	public void hitTanks(List<Tank> tanks){
		for (int i = 0; i < tanks.size(); i++) {
			hitTank(tanks.get(i));//ÿһ����ӵ�ײ������
		}
	}
	public boolean hitTank(Tank t){
		if (this.live&& this.getRect().intersects(t.getRect()) && t.isLive()
				&& this.isMine!=t.isMine) {//this.isMine!=t.isMine	�м���һ
			//������ײ ���챬ը���� �¼�
			BombTank b=new BombTank(t.getX(),t.getY(),tc);
			tc.bombs.add(b);
			
			if (t.isMine) {//�����Լ��жϼ���̹������ֵ  �ܰ����  ��t.setlive ����if�����
				t.setLive(false);
			}else{
				t.setLive(false);
			}
			this.live=false;
			return true;
		}
		return false;
	}
	
	//�ӵ�ײ����ǽ   ����
	public void hitWall(CommonWall w){
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live=false;
			this.tc.others.remove(w);//ײ�����崦
			this.tc.homeWall.remove(w);
		}
	}
	public void hitWall(MetaWall w){
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live=false;
		}
	}
	public void hitHome(Home h){
		if(this.live && this.getRect().intersects(h.getRect())){
			this.live=false;
			this.tc.home.setLive(false);//boss����
		}
	}
}



















