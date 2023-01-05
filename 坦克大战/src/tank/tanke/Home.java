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
	private boolean live=true;//判断BOSS是否存在
	private static Image img;
	private int x,y;
	private TankeClient tc;//保存坦克 整体界面  用于游戏结束时清理操作
	
	static{
		img=CommonWall.tk.getImage(Home.class.getResource("../../Images/home.jpg"));
	}
	public Home(int x,int y,TankeClient tc){
		this.x=x;
		this.y=y;
	}
	public void draw(Graphics g){
		if(live){
			//若果boss存活则进行绘制
			g.drawImage(img,x,y,null);
		}else{
			gameOver(g);
		}
	}
	//游戏结束需要做的操作  游戏结束，清理界面
	private void gameOver(Graphics gImage) {
		/*tc.others.clear();
		tc.metaWall.clear();
		tc.trees.clear();
		tc.rivers.clear();	*/
		
		Font f=gImage.getFont();
		gImage.setFont(new Font("宋体",Font.BOLD,40));
		gImage.drawString("你输了！", 310, 300);
		//tc.tanks.clear();如果再 TankClient 中先实现 则会报错
		//tc.bullets.clear();
		gImage.setFont(f);
	}
	public void setLive(boolean live){
		this.live=live;	
	}
	//判断boss是否存活
	public boolean isLive(){
		return live;
	}
	//创建矩形用于比较
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}
}













