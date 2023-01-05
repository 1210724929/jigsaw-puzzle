package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

/**
 * 子弹类
 * @author acer
 *
 */
public class Bullets {
	public static int speedX=10,speedY=10;
	
	public static final int width=10,height=10;
	private int x,y;
	Direction direction;
	private boolean live=true;//子弹状态
	private boolean isMine;//区别敌方子弹
	private  static Image[] bulletIamges=null;
	private TankeClient tc;
	
	static{
		bulletIamges=new Image[]{
			//不同方向子弹方向的图片转成对象
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
	//绘制 子弹
	public void draw(Graphics g){
		if (!live) {
			//子弹状态非的时候
			tc.bullets.remove(this);
			return;
		}
		switch (direction) {//根据方向选择图片 来 绘制
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
		
		//当子弹超出边界  改变状态
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
	//当子弹与坦克碰撞时
	public void hitTanks(List<Tank> tanks){
		for (int i = 0; i < tanks.size(); i++) {
			hitTank(tanks.get(i));//每一检测子弹撞击方法
		}
	}
	public boolean hitTank(Tank t){
		if (this.live&& this.getRect().intersects(t.getRect()) && t.isLive()
				&& this.isMine!=t.isMine) {//this.isMine!=t.isMine	敌己存一
			//发生碰撞 构造爆炸动画 事件
			BombTank b=new BombTank(t.getX(),t.getY(),tc);
			tc.bombs.add(b);
			
			if (t.isMine) {//可以自己判断己方坦克生命值  能挨多久  把t.setlive 放在if语句里
				t.setLive(false);
			}else{
				t.setLive(false);
			}
			this.live=false;
			return true;
		}
		return false;
	}
	
	//子弹撞击到墙   重置
	public void hitWall(CommonWall w){
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live=false;
			this.tc.others.remove(w);//撞击则清处
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
			this.tc.home.setLive(false);//boss死亡
		}
	}
}



















