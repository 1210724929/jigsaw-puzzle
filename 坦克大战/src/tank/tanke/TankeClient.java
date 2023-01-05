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
	
	List<CommonWall> others=new ArrayList<>();//��ͨשǽ��ש����
	//River river;һ������
	List<River> rivers=new ArrayList<>();
	List<MetaWall> metaWall=new ArrayList<>();//��ש����
	List<Tree> trees=new ArrayList<>();//���ļ���
	List<CommonWall> homeWall=new ArrayList<>();//boss�Աߵ�שǽ
	List<Tank> tanks=new ArrayList<>();//����з�̹��
	
	List<Bullets> bullets=new ArrayList<>();//�ӵ�����
	List<BombTank> bombs=new ArrayList<>();//̹�˱�ըͼ
	
	Home home=new Home(351,550,this);//boss����
	Tank homeTank=new Tank(270,550,true,Direction.STOP,this);//boss���Ե�̹��
	
	public TankeClient(){
		jmb=new JMenuBar();
		
		jm1=new JMenu("��Ϸ");
		jm2=new JMenu("��ͣ--����");
		jm3=new JMenu("��Ϸ����");
		jm4=new JMenu("����");
		//jm1.setFont(new Font("TimesToman",Font.BOLD,15));
		jmi1=new JMenuItem("��ʼ��Ϸ");
		jmi2=new JMenuItem("�˳�");
		jmi3=new JMenuItem("��ͣ");
		jmi4=new JMenuItem("����");
		jmi5=new JMenuItem("����1");
		jmi6=new JMenuItem("����2");
		jmi7=new JMenuItem("����3");
		jmi8=new JMenuItem("����4");
		jmi9=new JMenuItem("��Ϸ˵��");
		//ע������¼�
		jmi1.addActionListener(this);
		jmi2.addActionListener(this);
		jmi3.addActionListener(this);
		jmi4.addActionListener(this);
		jmi5.addActionListener(this);
		jmi6.addActionListener(this);
		jmi7.addActionListener(this);
		jmi8.addActionListener(this);
		jmi9.addActionListener(this);
		//��������Ttem
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
		
		//���з�̹�˳�ʼ����
		for (int i = 0; i < tankshu; i++) {
			if (i<9) {
				tanks.add(new Tank(150+70*i,40,false,Direction.D,this));
			}else if(i<15){
				tanks.add(new Tank(700,140+50*(i-6),false,Direction.U,this));
			}else{
				tanks.add(new Tank(10,50*(i-12),false,Direction.L,this));
			}	
		}
		//��Ӹ�ש
		for(int i=0;i<20;i++){
			if(i<10){
				metaWall.add(new MetaWall(40+30*i,100));
				metaWall.add(new MetaWall(600,440+20*i));
			}else if(i<20)
				metaWall.add(new MetaWall(0+30*(i-10),400));
			else metaWall.add(new MetaWall(500+30*(i-10),160));
		}
		//��ľ�Ĳ���
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
		//�����������ʼ��
		rivers.add(new River(285,180));
		//��boss���Χǽ
		for(int i=0;i<10;i++){
			if(i<4){
				homeWall.add(new CommonWall(330,592-21*i));
			}else if(i<7){
				homeWall.add(new CommonWall(352+21*(i-4),529));
			}else{
				homeWall.add(new CommonWall(393,538+(i-7)*21));
			}
			
		}
		//��ǽ����Ӷ���
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
		this.setTitle("̹�˴�ս");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		this.addKeyListener(new keyMonitor());//���̼���
		new Thread(new PaintThread()).start();//�߳� ����ʵ��̹���ƶ���Ҳ���ǲ�����ϸrepaint()����
	}
	
	Image screenImage=null;
	//���»��� �ķ���
	public void paint(Graphics g){
		//�ڻ��ƹ����� ���ͼƬ����
		screenImage=this.createImage(WIDTH,HEIGHT);
		//��ȡ��ȡ������ͼƬ�еĻ���
		Graphics gImage=screenImage.getGraphics();
		gImage.setColor(gImage.getColor());
		super.paint(gImage);//���������˸�ľ���
		
		if (homeTank.isLive()==false || home.isLive()==false) {
			Font f=gImage.getFont();
			gImage.setFont(new Font("����",Font.BOLD,40));
			gImage.drawString("�����ˣ�", 310, 300);
			tanks.clear();
			bullets.clear();
			gImage.setFont(f);
		}
		
		
		//����שǽ
		paintWall(gImage);
		//���ƺ���
		rivers.get(0).draw(gImage);
		//���Ƹ�ש
		paintMetalWall(gImage);
		//������
		paintTrees(gImage);
		//����boss
		home.draw(gImage);
		//����boss��Χ��ǽ
		paintHomeWall(gImage);
		//����boss��̹�� 		����̹��
		homeTank.draw(gImage);
		//���Ƶз���̹��
		paintTanks(gImage);
		//�����ӵ�
		paintBullets(gImage);
		//���Ʊ�ըͼ
		paintBombTank(gImage);
		
		//��ײ�¼�
		homeTank.collideHome(home);//Bossײ�����
		//�Լ�ײ���з�̹��ʱ
		homeTank.colideWithTanks(tanks);
		homeTank.collideWithRiver(rivers.get(0));//����ײ��
		
		g.drawImage(screenImage,0,0,null);
	}
	
	//���Ʊ�ը
	private void paintBombTank(Graphics gImage) {
		for (int i = 0; i <bombs.size() ; i++) {
			bombs.get(i).draw(gImage);
		}
		
	}

	//�����ӵ�
	private void paintBullets(Graphics gImage) {
		for (int i = 0; i < bullets.size(); i++) {
			Bullets b=bullets.get(i);
			b.hitTanks(tanks);//�ӵ�ײ�����з�̹��
			b.hitTank(homeTank);//�ӵ�ײ��������̹��
			
			b.hitHome(home);
			//�ӵ�ײ����ǽ
			for (int j = 0; j <others.size(); j++) {
				b.hitWall(others.get(j));
			}
			//�ӵ�ײ����boss�Աߵ�ǽ
			for (int j = 0; j <homeWall.size(); j++) {
				b.hitWall(homeWall.get(j));
			}
			//�ӵ�ײ������ש
			for (int j = 0; j <metaWall.size(); j++) {
				b.hitWall(metaWall.get(j));
			}
			
			b.draw(gImage);
		}
		
	}
	//���Ƶз�̹��
	private void paintTanks(Graphics gImage) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank tank=tanks.get(i);
			//ײ��boss�Ե�ש
			for (int j = 0; j < homeWall.size(); j++) {
				tank.collideWitWall(homeWall.get(j));
				homeWall.get(j).draw(gImage);
			}
			//ײ����ש
			for (int j = 0; j < metaWall.size(); j++) {
				tank.collideWitWall(metaWall.get(j));
				metaWall.get(j).draw(gImage);
			}
			//ײ����ͨשǽ
			for (int j = 0; j < others.size(); j++) {
				tank.collideWitWall(others.get(j));
				others.get(j).draw(gImage);
			}
			//ײ������
			River river=rivers.get(0);
			tank.collideWithRiver(river);
			river.draw(gImage);
			//ײ���Լ���(�з�֮��)
			tank.colideWithTanks(tanks);
			//ײ��bossʱ
			tank.collideHome(home);
			tank.draw(gImage);
		}
		
	}
	/*��ײ�¼� ������� ������Ҫ��� ����Ҫ����  �����ڻ��Ƶ�ʱ��ͽ��о��ο����
	private void collideMethod(Graphics gImage) {
		for (int i = 0; i <others.size(); i++) {//����CommonWall�����Ƿ���שǽ��ײ	
			homeTank.collideWitWall(others.get(i));
		}
	}
	*/
	//����boss
	private void paintHomeWall(Graphics gImage) {
		for(int i=0;i<homeWall.size();i++){
			homeTank.collideWitWall(homeWall.get(i));
			homeWall.get(i).draw(gImage);
		}
	}
	//������
	private void paintTrees(Graphics gImage) {
		for(int i=0;i<trees.size();i++){
			trees.get(i).draw(gImage);
		}
	}//���Ƹ�ש
	private void paintMetalWall(Graphics gImage) {
		for(int i=0;i<metaWall.size();i++){
			homeTank.collideWitWall(metaWall.get(i));
			metaWall.get(i).draw(gImage);
		}
	}
	//����ǽ��
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
		//���̰���
		public void keyPressed(KeyEvent e){
			homeTank.keyPressed(e);
		}
		//�����ͷ�
		public void keyReleased(KeyEvent e){	
			homeTank.keyReleased(e);
		}
	}
	
	
	
	private class PaintThread implements Runnable{
		@Override
		public void run() {
			while(paintable){
				//���û��Ʒ��� ��ѭ�� һֱ������
				repaint();
				try{
					Thread.sleep(100);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}	
		}
	}
	
	
	
	//����¼�����
	public void actionPerformed(ActionEvent e){
		Object []options={"ȷ��","ȡ��"};
		switch(e.getActionCommand()){
		case "NewGame":
			int response=JOptionPane.showOptionDialog(this, "�Ƿ�ȷ������Ϸ��", "����Ϸ", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			//������Ӧ���������
			if(response==0){//��ʼ����Ϸ�����򲻿�ʼ
				this.dispose();
				new TankeClient();
			}else{
				//
			}
			break;
		case "Exit":
			int res=JOptionPane.showOptionDialog(this, "�Ƿ�ȷ���˳���", "����Ϸ", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			//������Ӧ���������
			if(res==0){//�˳���������
				System.exit(0);
			}else{
				//����ʼ��Ϸ
			}
			break;
		case "Stop":
			paintable=false;
			break;
		case "Continue":
			if (!paintable) {
				paintable=true;
				//�ʼ���̱߳�ֹͣ��  �������¿���һ���߳�
				new Thread(new PaintThread()).start();
			}
			break;
		case "Help":
			JOptionPane.showMessageDialog(this, "�á� �� ��   ���� F�����ӵ�       R���¿�ʼ");
			break;
			
		case "Level1":
			/*Tank.speedX=4;
			Tank.speedY=4;
			̫�鷳 ���ǳ�ȡ����*/
			setLevel(3,5,5);
			//�ٶȸ��ı�  ���������¿��� ����
			this.dispose();
			new TankeClient();
			break;
		case "Level2":
			setLevel(5,6,9);
			//�ٶȸ��ı�  ���������¿��� ����
			this.dispose();
			new TankeClient();
			break;
		case "Level3":
			setLevel(8,9,14);
			//�ٶȸ��ı�  ���������¿��� ����
			this.dispose();
			new TankeClient();
			break;
		case "Level4":
			setLevel(18,15,20);
			//�ٶȸ��ı�  ���������¿��� ����
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





















