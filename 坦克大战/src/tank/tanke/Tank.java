package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

/**
 * ̹��
 * @author acer
 *
 */
public class Tank {
	private boolean live=true;//̹�˳�ʼ״̬ Ϊ��
	private  static Image[] tankImage=null;
	
	private static final int width=40,height=35;
	private int x,y;//ÿ�λ��Ƶ�����
	private int oldx,oldy;//֮ǰ������
	
	Direction direction=Direction.STOP;//��ʼ״̬  Ϊֹͣ
	Direction kdDirection=Direction.U;//��ʼ����  Ϊ��
	private TankeClient tc;
	
	public boolean isMine;//������ �Ƿ����Լ���̹��
	
	private static Random r=new Random();
	private int step=r.nextInt(10)+5;//��������� ģ���ƶ�
			
	public static int speedX=10,speedY=10;//��ʾ�ƶ��ٶ�
	private boolean bU,bD,bR,bL;
	
	public Tank(int x,int y,boolean isMine,Direction dir,TankeClient tc){
		this.x=x;
		this.y=y;
		this.isMine=isMine;
		this.direction=dir;
		this.tc=tc;	
	}
	
	static{
		tankImage=new Image[]{
				CommonWall.tk.getImage(Tank.class.getResource("../../Images/tankD.gif")),
				CommonWall.tk.getImage(Tank.class.getResource("../../Images/tankU.gif")),
				CommonWall.tk.getImage(Tank.class.getResource("../../Images/tankL.gif")),
				CommonWall.tk.getImage(Tank.class.getResource("../../Images/tankR.gif"))
		};
	}
	
	public void draw(Graphics g){
		if(!live){
			return;
		}
		switch(kdDirection){
		case D:
			g.drawImage(tankImage[0], x, y, null);
			break;
		case U:
			g.drawImage(tankImage[1], x, y, null);
			break;
		case L:
			g.drawImage(tankImage[2], x, y, null);
			break;
		case R:
			g.drawImage(tankImage[3], x, y, null);
			break;
		default:
			break;
		}
		move();//�ƶ��ĺ���
	}

	private void move() {
		this.oldx=x;
		this.oldy=y;
		if(this.direction!=Direction.STOP){
			this.kdDirection=this.direction;
		}
		switch(direction){
		case L:
			x-=speedX;
			break;
		case U:
			y-=speedY;
			break;
		case R:
			x+=speedX;
			break;
		case D:
			y+=speedY;
			break;
		case STOP:
			break;
		}		
		//�ƶ������߽�����
		if (x<0) {
			x=0;
		}
		if(y<40){
			y=40;
		}
		if (x+Tank.width>TankeClient.WIDTH) {
			x=TankeClient.WIDTH-Tank.width;
		}
		if (y+Tank.height>TankeClient.HEIGHT) {
			y=TankeClient.HEIGHT-Tank.height;
		}
		//����̹�� ���   �Ƿ��ǵз�  �ǿ����Լ���
		if (!isMine) {
			Direction[] directions=Direction.values();
			if (step==0) {//���²���·��
				step=r.nextInt(12)+3;
				int rn=r.nextInt(directions.length);
				direction=directions[rn];
			} 
			step--;
			if (r.nextInt(40)>38) {//��������� �ӵ�����һֱ����
				this.fire();
			}
		}
		}
		
		

	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_R://���¿�ʼ��Ϸ
			
			break;
		case KeyEvent.VK_RIGHT://
			bR=true;
			break;
		case KeyEvent.VK_LEFT://
			bL=true;
			break;
		case KeyEvent.VK_UP://
			bU=true;
			break;
		case KeyEvent.VK_DOWN://
			bD=true;
			break;
		}
		decideDirection();//�����������ƶ�����
	}

	private void decideDirection() {
		if(bD){
			direction=Direction.D;
		}else if(bU){
			direction=Direction.U;
		}
		else if(bR){
			direction=Direction.R;
		}
		else if(bL){
			direction=Direction.L;
		}else if(!bL && !bR && !bD && !bU){
			direction=Direction.STOP;//û�а��¾ͱ��ֲ���
		}	
	}

	public void keyReleased(KeyEvent e) {//�����ͷż���
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_F:
			fire();
			break;
		case KeyEvent.VK_RIGHT://
			bR=false;
			break;
		case KeyEvent.VK_LEFT://
			bL=false;
			break;
		case KeyEvent.VK_UP://
			bU=false;
			break;
		case KeyEvent.VK_DOWN://
			bD=false;
			break;
		}
		decideDirection();
	}
	//�����ӵ�
	private Bullets fire() {
		if (!live) {
			return null;
		}
		int x=this.x+Tank.width/2-Bullets.width/2;//�ӵ�����λ��
		int y=this.y+Tank.height/2-Bullets.height/2;
		Bullets m=new Bullets(x,y,isMine,kdDirection,this.tc);
		tc.bullets.add(m);
		return m;
	}

	//��̹����ײ��ͨǽ��ʱ
	public boolean collideWitWall(CommonWall w){
		//�þ����е���ײ�������intersects();
		if (this.live && this.getRect().intersects(w.getRect())) {
			changeToOldDir();
			return true;
		}
		return false;
	}
	//��ײ��שǽʱ
	public boolean collideWitWall(MetaWall m){
		//�þ����е���ײ�������intersects();
		if (this.live && this.getRect().intersects(m.getRect())) {
			changeToOldDir();
			return true;
		}
		return false;
	}
	//��ײ������ʱ
	public boolean collideWithRiver(River r){
		//�þ����е���ײ�������intersects();
		if (this.live && this.getRect().intersects(r.getRect())) {
			changeToOldDir();
			return true;
		}
		return false;
	}
	//��̹������Bossʱ
	public boolean collideHome(Home h){
		//�þ����е���ײ�������intersects();
		if (this.live && this.getRect().intersects(h.getRect())) {
			changeToOldDir();
			return true;
		}
		return false;
	}
	//��������ײʱ �ص�ԭ��״̬
	private void changeToOldDir() {
		x=oldx;
		y=oldy;
	}

	//��̹�˹�����һ������
	public Rectangle getRect(){
		return new Rectangle(x,y,width,height);
	}
	
	//̹���Լ�֮�����ײ
	public boolean colideWithTanks(List<Tank> tanks){
		for (int i = 0; i < tanks.size(); i++) {
			Tank t=tanks.get(i);
			if (this !=t) {
				if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					this.changeToOldDir();
					t.changeToOldDir();
					return true;
				}
			}
		}
		
		return false;
	}
	//�ж�̹���Ƿ���
	public boolean isLive() {
		return live;
	}
	
	public void setLive(boolean live){
		this.live=live;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}


//��ײ�����ǽ���������һ������Rectangle����ʹ��getRect().intersects|(Ҫ�ȽϵĶ���) ��������Ƚ�








