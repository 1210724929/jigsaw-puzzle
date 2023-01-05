package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

/**
 * 坦克
 * @author acer
 *
 */
public class Tank {
	private boolean live=true;//坦克初始状态 为活
	private  static Image[] tankImage=null;
	
	private static final int width=40,height=35;
	private int x,y;//每次绘制的坐标
	private int oldx,oldy;//之前的坐标
	
	Direction direction=Direction.STOP;//初始状态  为停止
	Direction kdDirection=Direction.U;//初始方向  为上
	private TankeClient tc;
	
	public boolean isMine;//定义标记 是否是自己的坦克
	
	private static Random r=new Random();
	private int step=r.nextInt(10)+5;//产生随机数 模拟移动
			
	public static int speedX=10,speedY=10;//表示移动速度
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
		move();//移动的函数
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
		//移动超过边界问题
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
		//区分坦克 类别   是否是敌方  是可以自己动
		if (!isMine) {
			Direction[] directions=Direction.values();
			if (step==0) {//重新产生路径
				step=r.nextInt(12)+3;
				int rn=r.nextInt(directions.length);
				direction=directions[rn];
			} 
			step--;
			if (r.nextInt(40)>38) {//产生随机数 子弹不是一直发射
				this.fire();
			}
		}
		}
		
		

	public void keyPressed(KeyEvent e) {
		int key=e.getKeyCode();
		switch(key){
		case KeyEvent.VK_R://重新开始游戏
			
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
		decideDirection();//决定接下来移动方向
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
			direction=Direction.STOP;//没有按下就保持不动
		}	
	}

	public void keyReleased(KeyEvent e) {//键盘释放监听
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
	//发射子弹
	private Bullets fire() {
		if (!live) {
			return null;
		}
		int x=this.x+Tank.width/2-Bullets.width/2;//子弹发射位置
		int y=this.y+Tank.height/2-Bullets.height/2;
		Bullets m=new Bullets(x,y,isMine,kdDirection,this.tc);
		tc.bullets.add(m);
		return m;
	}

	//当坦克碰撞普通墙体时
	public boolean collideWitWall(CommonWall w){
		//用矩形中的碰撞方法检测intersects();
		if (this.live && this.getRect().intersects(w.getRect())) {
			changeToOldDir();
			return true;
		}
		return false;
	}
	//碰撞钢砖墙时
	public boolean collideWitWall(MetaWall m){
		//用矩形中的碰撞方法检测intersects();
		if (this.live && this.getRect().intersects(m.getRect())) {
			changeToOldDir();
			return true;
		}
		return false;
	}
	//碰撞到河流时
	public boolean collideWithRiver(River r){
		//用矩形中的碰撞方法检测intersects();
		if (this.live && this.getRect().intersects(r.getRect())) {
			changeToOldDir();
			return true;
		}
		return false;
	}
	//当坦克碰到Boss时
	public boolean collideHome(Home h){
		//用矩形中的碰撞方法检测intersects();
		if (this.live && this.getRect().intersects(h.getRect())) {
			changeToOldDir();
			return true;
		}
		return false;
	}
	//当发生碰撞时 回到原来状态
	private void changeToOldDir() {
		x=oldx;
		y=oldy;
	}

	//将坦克构建成一个矩形
	public Rectangle getRect(){
		return new Rectangle(x,y,width,height);
	}
	
	//坦克自己之间的碰撞
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
	//判断坦克是否存活
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


//碰撞方法是将对象生成一个矩形Rectangle，再使用getRect().intersects|(要比较的对象) 这个方法比较








