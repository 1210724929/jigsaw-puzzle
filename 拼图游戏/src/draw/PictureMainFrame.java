package draw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/*
 * ������
 * */
public class PictureMainFrame extends JFrame {
	private String[] items={"  ��ɫ��2  ","  ��3  ","  ѩ����4  ","  ����˪��5  ","  ��Ȼ6"};
	private JRadioButton addNumInfo;//������ʾ
	private JRadioButton clearNumInfo;//�����ʾ
	private PictureCanvas canvas;//ƴͼ����
	private PicturePreview preview;//Ԥ����
	private JComboBox<String> box;
	private JTextField name;//ͼƬ����
	public static JTextField step;//����  static ���ô�������Ϳ��Է���
	private JButton start;//��ʼ��ť
	
	//�ղ������췽��
	public PictureMainFrame(){
		//super();
		init();//�����ʼ��
		addComponent();//������
		addPreviewImage();//���Ԥ��ͼƬ��ƴͼͼƬ
		addActionListener();
		
	}
	
	//�¼�����
	private void addActionListener() {
		// ������ʾ
		addNumInfo.addActionListener(new ActionListener(){
			//�������
			public void actionPerformed(ActionEvent e) {
				canvas.reLoadPictureAddNumber();	
			}
		});
		//�����ʾ
		clearNumInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.reLoadPictureClearNumber();
				
			}
		});
		//������
		box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//��ȡ��ѡ��ͼƬ�����
				int num=box.getSelectedIndex();//Ĭ�ϴ�0��ʼ
				//����Ԥ����
				PictureCanvas.pictureID=num+2;
				preview.repaint();//���»���
				//����ƴͼ��
				canvas.reLoadPictureClearNumber();
				//������Ϸ״̬��
				name.setText("ͼƬ���ƣ�"+box.getSelectedItem());//����ͼƬ����
				int stepNum=PictureCanvas.stepNum=0;//��������
				step.setText("������"+stepNum);//���õ�ǰ����
				//���°�ť��
				//��ť���ó������ʾ״̬
				clearNumInfo.setSelected(true);
			}
		});
		//��ʼ��ť
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//��������
				PictureCanvas.stepNum=0;
				step.setText("������"+PictureCanvas.stepNum);
				//����С����
				canvas.start();
				
			}
		});
	}
	
	//���Ԥ��ͼƬ��ƴͼͼƬ
	private void addPreviewImage() {
		//����һ����壨���У� ���� ƴͼ����Ԥ����
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		//ƴͼ��
		canvas=new PictureCanvas();
		canvas.setBorder(new TitledBorder("ƴͼ��"));
		//Ԥ����
		preview=new PicturePreview();
		preview.setBorder(new TitledBorder("Ԥ����"));
		
		panel.add(canvas, BorderLayout.WEST);
		panel.add(preview, BorderLayout.EAST);
		
		this.add(panel,BorderLayout.CENTER);
	}

	//������
	private void addComponent() {
		JPanel panel=new JPanel();
		//panel.setBackground(Color.PINK);
		panel.setLayout(new GridLayout(1,2));
		
		//������߰�ť��--------------------
		JPanel leftPanel=new JPanel();
		leftPanel.setBorder(new TitledBorder("��ť��"));
		leftPanel.setBackground(Color.PINK);
		//��ӵ���ѡ��ť
		 addNumInfo=new JRadioButton("������ʾ",false);
		 clearNumInfo=new JRadioButton("�����ʾ",true);
		//��Ӱ�ť��
		ButtonGroup buttonGroup=new ButtonGroup();
		//���������
		box=new JComboBox<String>(items);
		//��ӿ�ʼ��ť
		start=new JButton("Start");
		
		//��ӵ���� 
		buttonGroup.add(addNumInfo);
		buttonGroup.add(clearNumInfo);
		//����ɫ
		addNumInfo.setBackground(Color.PINK);
		clearNumInfo.setBackground(Color.PINK);
		start.setBackground(Color.pink);
		
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNumInfo);
		leftPanel.add(new JLabel("        ѡ��ͼƬ"));
		leftPanel.add(box);
		leftPanel.add(start);
		
		panel.add(leftPanel,BorderLayout.WEST);
		
		//�ұ߰�ť��-----------------------------
		JPanel rightPanel=new JPanel();
		rightPanel.setBorder(new TitledBorder("��Ϸ״̬"));
		rightPanel.setBackground(Color.PINK);
		rightPanel.setLayout(new GridLayout(1, 2));//���
		
		name=new JTextField("ͼƬ���ƣ�  ��ɫ��2  ");
		step=new JTextField("������0");
		//�ı��� ���ܱ༭
		name.setEditable(false);
		step.setEditable(false);
		rightPanel.add(name,BorderLayout.WEST);
		rightPanel.add(step,BorderLayout.EAST);
		
		
		panel.add(rightPanel,BorderLayout.EAST);
		
		this.add(panel,BorderLayout.NORTH);
		
	}

	// �����ʼ������ �����ڣ�
	private void init() {
		this.setTitle("ƴͼ��Ϸ");
		this.setSize(1050,850);
		this.setLocation(150,100);
		this.setResizable(false);//���ڴ�С���ɱ�
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}





















