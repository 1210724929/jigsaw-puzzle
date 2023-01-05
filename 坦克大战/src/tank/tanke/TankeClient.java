package tank.tanke;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
public class TankeClient extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 161368997557176373L;
	public static  final int WIDTH=800;
	public static  final int HEIGHT=600;
	public static int tankshu=20;
	
	boolean paintable=true;
	
	JMenuBar jmb=null;
	JMenu jm1,jm2,jm3,jm4;
	JMenuItem jmi1,jmi2,jmi3,jmi4,jmi5,jmi6,jmi7,jmi8,jmi9;
	
	List<CommonWall> others=new ArrayList<>();//普通砖墙，砖集合
	//River river;一条河流
	List<River> rivers=new ArrayList<>();
	List<MetaWall> metaWall=new ArrayList<>();//钢砖集合
	List<Tree> trees=new ArrayList<>();//树的集合
	List<CommonWall> homeWall=new ArrayList<>();//boss旁边的砖墙
	List<Tank> tanks=new ArrayList<>();//保存敌方坦克
	
	List<Bullets> bullets=new ArrayList<>();//子弹集合
	List<BombTank> bombs=new ArrayList<>();//坦克爆炸图
	
	Home home=new Home(351,550,this);//boss对象
	Tank homeTank=new Tank(270,550,true,Direction.STOP,this);//boss身旁的坦克
	
	public TankeClient(){
		jmb=new JMenuBar();
		
		jm1=new JMenu("游戏");
		jm2=new JMenu("暂停--继续");
		jm3=new JMenu("游戏级别");
		jm4=new JMenu("帮助");
		//jm1.setFont(new Font("TimesToman",Font.BOLD,15));
		jmi1=new JMenuItem("开始游戏");
		jmi2=new JMenuItem("退出");
		jmi3=new JMenuItem("暂停");
		jmi4=new JMenuItem("继续");
		jmi5=new JMenuItem("级别1");
		jmi6=new JMenuItem("级别2");
		jmi7=new JMenuItem("级别3");
		jmi8=new JMenuItem("级别4");
		jmi9=new JMenuItem("游戏说明");
		//注册监听事件
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi5.addActionListener(this);
		jmi6.addActionListener(this);
		jmi7.addActionListener(this);
		jmi8.addActionListener(this);
		jmi9.addActionListener(this);
		//用于区分Ttem
		jmi1.setActionCommand("NewGame");
		jmi2.setActionCommand("Exit");
		jmi3.setActionCommand("Stop");
		jmi4.setActionCommand("Continue");
		jmi5.setActionCommand("Level1");
		jmi6.setActionCommand("Level2");
		jmi7.setActionCommand("Level3");
		jmi8.setActionCommand("Level4");
		jmi9.setActionCommand("Help");
		
		
		jm1.add(jmi1);jm1.add(jmi2);
		jm2.add(jmi3);jm2.add(jmi4);
		jm3.add(jmi5);jm3.add(jmi6);jm3.add(jmi7);jm3.add(jmi8);
		jm4.add(jmi9);
		jmb.add(jm1);jmb.add(jm2);jmb.add(jm3);jmb.add(jm4);
		this.setJMenuBar(jmb);
		
		//给敌方坦克初始化化
		for (int i = 0; i < tankshu; i++) {
			if (i<9) {
				tanks.add(new Tank(150+70*i,40,false,Direction.D,this));
			}else if(i<15){
				tanks.add(new Tank(700,140+50*(i-6),false,Direction.U,this));
			}else{
				tanks.add(new Tank(10,50*(i-12),false,Direction.L,this));
			}	
		}
		//添加钢砖
		for(int i=0;i<20;i++){
			if(i<10){
				metaWall.add(new MetaWall(40+30*i,100));
				metaWall.add(new MetaWall(600,440+20*i));
			}else if(i<20)
				metaWall.add(new MetaWall(0+30*(i-10),400));
			else metaWall.add(new MetaWall(500+30*(i-10),160));
		}
		//树木的布局
		for(int i=0;i<4;i++){
			trees.add(new Tree(0+30*i,360));
			trees.add(new Tree(220+30*i,360));
			trees.add(new Tree(440+30*i,360));
			trees.add(new Tree(660+30*i,360));
			trees.add(new Tree(670+30*i,260));
			trees.add(new Tree(670+30*i,160));
			trees.add(new Tree(690+30,200));
			trees.add(new Tree(680+30,100));
			trees.add(new Tree(0+30*i,560));
			trees.add(new Tree(40+30*i,525));
		}
		//给河流对象初始化
		rivers.add(new River(285,180));
		//给boss添加围墙
		for(int i=0;i<10;i++){
			if(i<4){
				homeWall.add(new CommonWall(330,592-21*i));
			}else if(i<7){
				homeWall.add(new CommonWall(352+21*(i-4),529));
			}else{
				homeWall.add(new CommonWall(393,538+(i-7)*21));
			}
			
		}
		//给墙体添加对象
		for(int i=0;i<32;i++){
			if(i<10){
				others.add(new CommonWall(60+20*i,140));
				others.add(new CommonWall(60+20*i,200));
				others.add(new CommonWall(60+20*i,260));
				others.add(new CommonWall(400,100+20*i));
				others.add(new CommonWall(420,100+20*i));
				for(int j=0;j<9;j++)
				others.add(new CommonWall(480+20*j,100+20*i));
				if(i<5){others.add(new CommonWall(160,140+20*i));}
				others.add(new CommonWall(200,240));
				
			}else if(i<32){
				others.add(new CommonWall(480+20*(i-16),400));
				others.add(new CommonWall(440+20*(i-16),340));
				others.add(new CommonWall(200+20*(i-16),500));
				others.add(new CommonWall(200+20*(i-16),480));
				
				
			}
		}
		
		this.setSize(WIDTH,HEIGHT);
		this.setLocation(280, 50);
		this.setTitle("坦克大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.addKeyListener(new keyMonitor());//键盘监听
		new Thread(new PaintThread()).start();//线程 用于实现坦克移动；也就是不断仔细repaint()方法
	}
	
	Image screenImage=null;
	//重新绘制 的方法
	public void paint(Graphics g){
		//在绘制过程中 获得图片对象
		screenImage=this.createImage(WIDTH,HEIGHT);
		//获取截取出来的图片中的画布
		Graphics gImage=screenImage.getGraphics();
		gImage.setColor(gImage.getColor());
		super.paint(gImage);//解决界面闪烁的精髓
		
		if (homeTank.isLive()==false || home.isLive()==false) {
			Font f=gImage.getFont();
			gImage.setFont(new Font("宋体",Font.BOLD,40));
			gImage.drawString("你输了！", 310, 300);
			tanks.clear();
			bullets.clear();
			gImage.setFont(f);
		}
		
		
		//绘制砖墙
		paintWall(gImage);
		//绘制河流
		rivers.get(0).draw(gImage);
		//绘制钢砖
		paintMetalWall(gImage);
		//绘制树
		paintTrees(gImage);
		//绘制boss
		home.draw(gImage);
		//绘制boss周围的墙
		paintHomeWall(gImage);
		//绘制boss边坦克 		己方坦克
		homeTank.draw(gImage);
		//绘制敌方他坦克
		paintTanks(gImage);
		//绘制子弹
		paintBullets(gImage);
		//绘制爆炸图
		paintBombTank(gImage);
		
		//碰撞事件
		homeTank.collideHome(home);//Boss撞击检测
		//自己撞到敌方坦克时
		homeTank.colideWithTanks(tanks);
		homeTank.collideWithRiver(rivers.get(0));//河流撞击
		
		g.drawImage(screenImage,0,0,null);
	}
	
	//绘制爆炸
	private void paintBombTank(Graphics gImage) {
		for (int i = 0; i <bombs.size() ; i++) {
			bombs.get(i).draw(gImage);
		}
		
	}

	//绘制子弹
	private void paintBullets(Graphics gImage) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullets b=bullets.get(i);
			b.hitTanks(tanks);//子弹撞击到敌方坦克
			b.hitTank(homeTank);//子弹撞击到己方坦克
			
			b.hitHome(home);
			//子弹撞击到墙
			for (int j = 0; j <others.size(); j++) {
				b.hitWall(others.get(j));
			}
			//子弹撞击到boss旁边的墙
			for (int j = 0; j <homeWall.size(); j++) {
				b.hitWall(homeWall.get(j));
			}
			//子弹撞击到钢砖
			for (int j = 0; j <metaWall.size(); j++) {
				b.hitWall(metaWall.get(j));
			}
			
			b.draw(gImage);
		}
		
	}
	//绘制敌方坦克
	private void paintTanks(Graphics gImage) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank tank=tanks.get(i);
			//撞到boss旁的砖
			for (int j = 0; j < homeWall.size(); j++) {
				tank.collideWitWall(homeWall.get(j));
				homeWall.get(j).draw(gImage);
			}
			//撞到钢砖
			for (int j = 0; j < metaWall.size(); j++) {
				tank.collideWitWall(metaWall.get(j));
				metaWall.get(j).draw(gImage);
			}
			//撞到普通砖墙
			for (int j = 0; j < others.size(); j++) {
				tank.collideWitWall(others.get(j));
				others.get(j).draw(gImage);
			}
			//撞到河流
			River river=rivers.get(0);
			tank.collideWithRiver(river);
			river.draw(gImage);
			//撞到自己人(敌方之间)
			tank.colideWithTanks(tanks);
			//撞到boss时
			tank.collideHome(home);
			tank.draw(gImage);
		}
		
	}
	/*碰撞事件 单个检测 若果都要检测 则又要遍历  于是在绘制的时候就进行矩形框添加
	private void collideMethod(Graphics gImage) {
		for (int i = 0; i <others.size(); i++) {//遍历CommonWall则检测是否与砖墙相撞	
			homeTank.collideWitWall(others.get(i));
		}
	}
	*/
	//绘制boss
	private void paintHomeWall(Graphics gImage) {
		for(int i=0;i<homeWall.size();i++){
			homeTank.collideWitWall(homeWall.get(i));
			homeWall.get(i).draw(gImage);
		}
	}
	//绘制树
	private void paintTrees(Graphics gImage) {
		for(int i=0;i<trees.size();i++){
			trees.get(i).draw(gImage);
		}
	}//绘制钢砖
	private void paintMetalWall(Graphics gImage) {
		for(int i=0;i<metaWall.size();i++){
			homeTank.collideWitWall(metaWall.get(i));
			metaWall.get(i).draw(gImage);
		}
	}
	//绘制墙体
	private void paintWall(Graphics gImage){
		for(int i=0;i<others.size();i++){
			homeTank.collideWitWall(others.get(i));
			others.get(i).draw(gImage);
		}
	}
	
	public static void main(String[] args) {
		new TankeClient();
	}
	
	private class keyMonitor extends KeyAdapter{
		//键盘按下
		public void keyPressed(KeyEvent e){
			homeTank.keyPressed(e);
		}
		//键盘释放
		public void keyReleased(KeyEvent e){	
			homeTank.keyReleased(e);
		}
	}
	
	
	
	private class PaintThread implements Runnable{
		@Override
		public void run() {
			while(paintable){
				//调用绘制方法 死循环 一直绘制中
				repaint();
				try{
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}	
		}
	}
	
	
	
	//点击事件触发
	public void actionPerformed(ActionEvent e){
		Object []options={"确定","取消"};
		switch(e.getActionCommand()){
		case "NewGame":
			int response=JOptionPane.showOptionDialog(this, "是否确定新游戏？", "新游戏", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			//根据响应结果来操作
			if(response==0){//开始新游戏，否则不开始
				this.dispose();
				new TankeClient();
			}else{
				//
			}
			break;
		case "Exit":
			int res=JOptionPane.showOptionDialog(this, "是否确定退出？", "新游戏", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			//根据响应结果来操作
			if(res==0){//退出，否则不是
				System.exit(0);
			}else{
				//不开始游戏
			}
			break;
		case "Stop":
			paintable=false;
			break;
		case "Continue":
			if (!paintable) {
				paintable=true;
				//最开始的线程被停止了  所以重新开启一条线程
				new Thread(new PaintThread()).start();
			}
			break;
		case "Help":
			JOptionPane.showMessageDialog(this, "用↑ → ↓   操作 F发射子弹       R重新开始");
			break;
			
		case "Level1":
			/*Tank.speedX=4;
			Tank.speedY=4;
			太麻烦 于是抽取方法*/
			setLevel(3,5,5);
			//速度更改变  窗口则重新开启 控制
			this.dispose();
			new TankeClient();
			break;
		case "Level2":
			setLevel(5,6,9);
			//速度更改变  窗口则重新开启 控制
			this.dispose();
			new TankeClient();
			break;
		case "Level3":
			setLevel(8,9,14);
			//速度更改变  窗口则重新开启 控制
			this.dispose();
			new TankeClient();
			break;
		case "Level4":
			setLevel(18,15,20);
			//速度更改变  窗口则重新开启 控制
			this.dispose();
			new TankeClient();
			break;
			
			
		default:
			break;
		}
	}
	public void setLevel(int tankSpeed,int bulletsSpeed,int tankshu){
		Tank.speedX=tankSpeed;
		Tank.speedY=tankSpeed;
		Bullets.speedX=bulletsSpeed;
		Bullets.speedY=bulletsSpeed;
		TankeClient.tankshu=tankshu;
	}
		
}





















