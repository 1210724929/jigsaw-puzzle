package draw;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/*
 * ƴͼ��
 * */
public class PictureCanvas extends JPanel implements MouseListener {
	public static int pictureID=2;//ͼƬID
	public static int stepNum=0;//����
	
	private Cell[] cell;//С����
	private boolean hasAddActionListener=false;//�Ƿ��С��������¼�����
	private Rectangle nullCell;//ָ��һ���յ�С����
	
	public PictureCanvas(){
		//����ƴͼ������
		this.setLayout(null);//֡����
		//����12��ͼƬС���� ��ӵ�ƴͼ��
		cell=new Cell[12];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//����ͼƬ ת��Ϊ����
				ImageIcon icon=new ImageIcon("picture1\\2\\"+pictureID+"_"+(i*3+j+1)+".gif");
				//����С����
				cell[i*3+j]=new Cell(icon);
				//ָ��λ��
				cell[i*3+j].setLocation(25+j*160,25+i*180);
				//��ͼƬ���� ��Ӵ�ƴͼ��
				this.add(cell[i*3+j]);
			}
		}
		
		//ɾ����12��С����
		this.remove(cell[11]);
		//ָ��һ���յ�С����
		nullCell=new Rectangle(160*2+25,180*3+25,160, 180);
		hasAddActionListener=false;
	}
	
	//���¼���ͼƬ�����������ʾ
	public void reLoadPictureAddNumber(){
		//��ȡÿһ��ͼƬС����
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//cell[i*3+j];
				//������ʾ��ͼƬ
				ImageIcon icon=new ImageIcon("picture1\\2\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
				//������ʾ��������ʾ
				cell[i*3+j].setText(""+(i*3+j+1));
				cell[i*3+j].setVerticalTextPosition(this.getY()/2);
				cell[i*3+j].setHorizontalTextPosition(this.getX()/2);
			}
		}
	}
	
	//���¼���ͼƬ�����������ʾ
	public void reLoadPictureClearNumber(){
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				//cell[i*3+j];
				//������ʾ��ͼƬ
				ImageIcon icon=new ImageIcon("picture1\\2\\"+pictureID+"_"+(i*3+j+1)+".gif");
				cell[i*3+j].setIcon(icon);
				//������ʾ��������ʾ
				cell[i*3+j].setText("");
			}
		}
	}
	
	//��С��������
	public void start() {
		//���û�и�С��������������������
		if (!hasAddActionListener) {
			for (int i = 0; i < 11; i++) {
				cell[i].addMouseListener(this);
			}
			//�������������״̬
			hasAddActionListener=true;
		}
		
		//�жϵ�ǰ��һ��С����������Ͻǵ�λ�ñȽϽ�ʱ������ո񻥻�
		//�����һ��С���������Ͻǵ��ĸ�����λ���ڣ��Ͳ���ѭ����������շ��񻥻�
		while(cell[0].getBounds().x<=(160+25) && cell[0].getBounds().y<=(180+25)){
			//��ȡ�� �շ����λ��
			int nullX=nullCell.getBounds().x;
			int nullY=nullCell.getBounds().y;
			//�漴����һ�����򣬽��пշ�����뷽�񻥻�
			//�������������Ӧ�շ�������������ƶ�
			int direction=(int)(Math.random()*4);
			switch (direction) {
			case 0://�շ���  �����ƶ���������߷������λ�û���
				nullX-=160;
				cellMove(nullX,nullY,"RIGHT");
				break;
			case 1://�շ��������ƶ��������ұ߷������λ�û���
				nullX+=160;
				cellMove(nullX,nullY,"LEFT");
				break;
			case 2://�շ��������ƶ��������ϱ߷������λ�û���
				nullY-=180;
				cellMove(nullX,nullY,"DOWN");
				break;
			case 3://�շ��������ƶ��������±߷������λ�û���
				nullY+=180;
				cellMove(nullX,nullY,"UP");
				break;
				
			}
		}
	}
	//������շ���λ�û���(�շ���x���꣬�շ���y���꣬Ҫ�ƶ��ķ���)
	private void cellMove(int nullX, int nullY, String direction) {
		for (int i = 0; i < 11; i++) {
			//��ȡ����շ���λ����ͬ��С����
			if (cell[i].getBounds().x==nullX && cell[i].getBounds().y==nullY) {
				//��ǰ�����ƶ�
				cell[i].move(direction);
				//�շ����ƶ�
				nullCell.setLocation(nullX,nullY);
				//�����󣬽���ѭ��
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
	
	public void mousePressed(MouseEvent e) {//��갴��ȥ
		//��ȡ��ǰ�����С����
		Cell button=(Cell)e.getSource();
		//��ȡ���С����ĵ�����
		int clickX=button.getBounds().x;
		int clickY=button.getBounds().y;
		//��ȡ��ǰ�շ��������
		int nullX=nullCell.getBounds().x;
		int nullY=nullCell.getBounds().y;
		
		//���бȽϣ������������н���
		if (clickX==nullX && clickY-nullY==180) {//�����Ϊ�շ����µķ���
			button.move("UP");//����ķ��������ƶ�	
		}else if(clickX==nullX && clickY-nullY==-180){//�����Ϊ�շ����ϵķ���
			button.move("DOWN");//����ķ�������	�ƶ�
		}else if(clickX-nullX==160 && clickY==nullY){//�����Ϊ�շ����ҵķ���
			button.move("LEFT");//����ķ�������	�ƶ�
		}else if(clickX-nullX==-160 && clickY==nullY){//�����Ϊ�շ�����ķ���
			button.move("RIGHT");//����ķ�������	�ƶ�
		}else{
			return ;//���������� ���ƶ�
		}
		
		//�շ���λ�õĸ���
		nullCell.setLocation(clickX,clickY);
		
		//ƴͼ�����»���
		this.repaint();
		
		
		//����ǰ��Ϸ״̬��������
		stepNum++;
		PictureMainFrame.step.setText("������"+stepNum);
		
		//�ж���Ϸ�Ƿ���ɣ���ɾ͸���ʾ
		if (isFinish()) {
			//��������
			JOptionPane.showMessageDialog(this, "��ϲ����Ϸ��� ���ͣ�"+"\n" +"���ò�����"+stepNum);
			//����ÿһ��С���������¼������������������
			for (int i = 0; i < 11; i++) {
				cell[i].removeMouseListener(this);
			}
			hasAddActionListener=false;
		}		
	}
	//�ж���Ϸ�Ƿ���� ���������ж��Ƿ�ƴͼ�ɹ�
	private boolean isFinish() {
		for (int i = 0; i < 11; i++) {
			//��ȡÿһ�����λ��
			int x=cell[i].getBounds().x;
			int y=cell[i].getBounds().y;
			if (((y-25)/180*3+(x-25)/160) !=i) {
				return false;
			}
		}
		return true;
	}

}






















