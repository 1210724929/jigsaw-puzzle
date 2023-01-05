package draw;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * 拼图区
 * */
public class PictureCanvas extends JPanel implements MouseListener {
	public static int pictureID=2;//图片ID
	public static int stepNum=0;//步数
	
	private Cell[] cell;//小方格
	private boolean hasAddActionListener=false;//是否给小方格添加事件监听
	private Rectangle nullCell;//指定一个空的小方格
	
	public PictureCanvas(){
		//设置拼图区布局
		this.setLayout(null);//帧布局
		//创建12个图片小方格 添加到拼图区
		cell=new Cell[12];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//加载图片 转化为对象
				ImageIcon icon=new ImageIcon("picture1\\2\\"+pictureID+"_"+(i*3+j+1)+".gif");
				//创建小方格
				cell[i*3+j]=new Cell(icon);
				//指定位置
				cell[i*3+j].setLocation(25+j*160,25+i*180);
				//将图片方块 添加打拼图区
				this.add(cell[i*3+j]);
			}
		}
		
		//删除第12个小方格
		this.remove(cell[11]);
		//指定一个空的小方格
		nullCell=new Rectangle(160*2+25,180*3+25,160, 180);
		hasAddActionListener=false;
	}
	
	//重新加载图片，添加数字提示
	public void reLoadPictureAddNumber(){
		//获取每一个图片小方格
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//cell[i*3+j];
				//设置显示的图片
				ImageIcon icon=new ImageIcon("picture1\\2\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
				//设置显示的数字提示
				cell[i*3+j].setText(""+(i*3+j+1));
				cell[i*3+j].setVerticalTextPosition(this.getY()/2);
				cell[i*3+j].setHorizontalTextPosition(this.getX()/2);
			}
		}
	}
	
	//重新加载图片，清除数字提示
	public void reLoadPictureClearNumber(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//cell[i*3+j];
				//设置显示的图片
				ImageIcon icon=new ImageIcon("picture1\\2\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
				//设置显示的数字提示
				cell[i*3+j].setText("");
			}
		}
	}
	
	//对小方格排序
	public void start() {
		//如果没有给小方格添加鼠标监听，则添加
		if (!hasAddActionListener) {
			for (int i = 0; i < 11; i++) {
				cell[i].addMouseListener(this);
			}
			//更新鼠标点击监听状态
			hasAddActionListener=true;
		}
		
		//判断当前第一个小方格距离左上角的位置比较近时，则与空格互换
		//如果第一个小方格在左上角的四个方格位置内，就不断循环，进行与空方格互换
		while(cell[0].getBounds().x<=(160+25) && cell[0].getBounds().y<=(180+25)){
			//获取到 空方格的位置
			int nullX=nullCell.getBounds().x;
			int nullY=nullCell.getBounds().y;
			//随即产生一个方向，进行空方格和与方格互换
			//产生随机数，对应空方格的上下左右移动
			int direction=(int)(Math.random()*4);
			switch (direction) {
			case 0://空方格  向左移动，即与左边方格进行位置互换
				nullX-=160;
				cellMove(nullX,nullY,"RIGHT");
				break;
			case 1://空方格向右移动，即与右边方格进行位置互换
				nullX+=160;
				cellMove(nullX,nullY,"LEFT");
				break;
			case 2://空方格向上移动，即与上边方格进行位置互换
				nullY-=180;
				cellMove(nullX,nullY,"DOWN");
				break;
			case 3://空方格向下移动，即与下边方格进行位置互换
				nullY+=180;
				cellMove(nullX,nullY,"UP");
				break;
				
			}
		}
	}
	//方格与空方格位置互换(空方格x坐标，空方格y坐标，要移动的方向)
	private void cellMove(int nullX, int nullY, String direction) {
		for (int i = 0; i < 11; i++) {
			//获取到与空方格位置相同的小方格
			if (cell[i].getBounds().x==nullX && cell[i].getBounds().y==nullY) {
				//当前方格移动
				cell[i].move(direction);
				//空方格移动
				nullCell.setLocation(nullX,nullY);
				//交换后，结束循环
				break;
			}
		}
	}

	public void mouseClicked(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	
	public void mousePressed(MouseEvent e) {//鼠标按下去
		//获取当前点击的小方格
		Cell button=(Cell)e.getSource();
		//获取点击小方格的的坐标
		int clickX=button.getBounds().x;
		int clickY=button.getBounds().y;
		//获取当前空方格的坐标
		int nullX=nullCell.getBounds().x;
		int nullY=nullCell.getBounds().y;
		
		//进行比较，满足条件进行交换
		if (clickX==nullX && clickY-nullY==180) {//点击的为空方格下的方格
			button.move("UP");//点击的方格向上移动	
		}else if(clickX==nullX && clickY-nullY==-180){//点击的为空方格上的方格
			button.move("DOWN");//点击的方格向下	移动
		}else if(clickX-nullX==160 && clickY==nullY){//点击的为空方格右的方格
			button.move("LEFT");//点击的方格向左	移动
		}else if(clickX-nullX==-160 && clickY==nullY){//点击的为空方格左的方格
			button.move("RIGHT");//点击的方格向右	移动
		}else{
			return ;//不满足条件 不移动
		}
		
		//空方格位置的更新
		nullCell.setLocation(clickX,clickY);
		
		//拼图区重新绘制
		this.repaint();
		
		
		//将当前游戏状态步数更新
		stepNum++;
		PictureMainFrame.step.setText("步数："+stepNum);
		
		//判断游戏是否完成，完成就给提示
		if (isFinish()) {
			//弹出窗口
			JOptionPane.showMessageDialog(this, "恭喜你游戏完成 加油！"+"\n" +"所用步数："+stepNum);
			//撤销每一个小方格的鼠标事件监听，点击不起作用
			for (int i = 0; i < 11; i++) {
				cell[i].removeMouseListener(this);
			}
			hasAddActionListener=false;
		}		
	}
	//判断游戏是否完成 根据坐标判断是否拼图成功
	private boolean isFinish() {
		for (int i = 0; i < 11; i++) {
			//获取每一方格的位置
			int x=cell[i].getBounds().x;
			int y=cell[i].getBounds().y;
			if (((y-25)/180*3+(x-25)/160) !=i) {
				return false;
			}
		}
		return true;
	}

}






















