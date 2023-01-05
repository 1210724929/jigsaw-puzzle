package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;

public class BombTank {
	private int x,y;
	private boolean live=true;//初始状态
	private TankeClient tc;
	private static Image[] img;
	
	static{
		img=new Image[]{
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/1.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/2.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/3.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/4.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/5.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/6.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/7.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/8.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/9.gif")),
				CommonWall.tk.getImage(BombTank.class.getResource("../../Images/10.gif")),
		};
	}
	
	public BombTank(int x,int y,TankeClient tc){
		this.x=x;
		this.y=y;
		this.tc=tc;
	}
	int step=9;
	public void draw(Graphics g){
		if (!live) {
			tc.bombs.remove(this);
			return;
		}
		if (step<=0) {
			live=false;//十张图片 绘制完成 
			step=9;
			return;
		}
		g.drawImage(img[step], x, y, null);
		step--;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
