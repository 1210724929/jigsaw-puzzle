package draw;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/*
 * 主界面
 * */
public class PictureMainFrame extends JFrame {
	private String[] items={"  冰色玉肌2  ","  冰3  ","  雪羽颜4  ","  辰星霜落5  ","  蓦然6"};
	private JRadioButton addNumInfo;//数字提示
	private JRadioButton clearNumInfo;//清除提示
	private PictureCanvas canvas;//拼图区域
	private PicturePreview preview;//预览区
	private JComboBox<String> box;
	private JTextField name;//图片名称
	public static JTextField step;//步数  static 不用创建对象就可以访问
	private JButton start;//开始按钮
	
	//空参数构造方法
	public PictureMainFrame(){
		//super();
		init();//界面初始化
		addComponent();//添加组件
		addPreviewImage();//添加预览图片和拼图图片
		addActionListener();
		
	}
	
	//事件处理
	private void addActionListener() {
		// 数字提示
		addNumInfo.addActionListener(new ActionListener(){
			//点击激活
			public void actionPerformed(ActionEvent e) {
				canvas.reLoadPictureAddNumber();	
			}
		});
		//清除提示
		clearNumInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.reLoadPictureClearNumber();
				
			}
		});
		//下拉框
		box.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//获取到选择图片的序号
				int num=box.getSelectedIndex();//默认从0开始
				//更新预览区
				PictureCanvas.pictureID=num+2;
				preview.repaint();//重新绘制
				//更新拼图区
				canvas.reLoadPictureClearNumber();
				//更新游戏状态区
				name.setText("图片名称："+box.getSelectedItem());//设置图片名称
				int stepNum=PictureCanvas.stepNum=0;//步数清零
				step.setText("步数："+stepNum);//设置当前步数
				//更新按钮区
				//按钮设置成清除提示状态
				clearNumInfo.setSelected(true);
			}
		});
		//开始按钮
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//步数清零
				PictureCanvas.stepNum=0;
				step.setText("步数："+PictureCanvas.stepNum);
				//打乱小方格
				canvas.start();
				
			}
		});
	}
	
	//添加预览图片和拼图图片
	private void addPreviewImage() {
		//创建一个面板（居中） 包含 拼图区和预览区
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		//拼图区
		canvas=new PictureCanvas();
		canvas.setBorder(new TitledBorder("拼图区"));
		//预览区
		preview=new PicturePreview();
		preview.setBorder(new TitledBorder("预览区"));
		
		panel.add(canvas, BorderLayout.WEST);
		panel.add(preview, BorderLayout.EAST);
		
		this.add(panel,BorderLayout.CENTER);
	}

	//添加组件
	private void addComponent() {
		JPanel panel=new JPanel();
		//panel.setBackground(Color.PINK);
		panel.setLayout(new GridLayout(1,2));
		
		//创建左边按钮区--------------------
		JPanel leftPanel=new JPanel();
		leftPanel.setBorder(new TitledBorder("按钮区"));
		leftPanel.setBackground(Color.PINK);
		//添加单项选择按钮
		 addNumInfo=new JRadioButton("数字提示",false);
		 clearNumInfo=new JRadioButton("清除提示",true);
		//添加按钮组
		ButtonGroup buttonGroup=new ButtonGroup();
		//添加下拉框
		box=new JComboBox<String>(items);
		//添加开始按钮
		start=new JButton("Start");
		
		//添加到面板 
		buttonGroup.add(addNumInfo);
		buttonGroup.add(clearNumInfo);
		//背景色
		addNumInfo.setBackground(Color.PINK);
		clearNumInfo.setBackground(Color.PINK);
		start.setBackground(Color.pink);
		
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNumInfo);
		leftPanel.add(new JLabel("        选择图片"));
		leftPanel.add(box);
		leftPanel.add(start);
		
		panel.add(leftPanel,BorderLayout.WEST);
		
		//右边按钮区-----------------------------
		JPanel rightPanel=new JPanel();
		rightPanel.setBorder(new TitledBorder("游戏状态"));
		rightPanel.setBackground(Color.PINK);
		rightPanel.setLayout(new GridLayout(1, 2));//表格
		
		name=new JTextField("图片名称：  冰色玉肌2  ");
		step=new JTextField("步数：0");
		//文本框 不能编辑
		name.setEditable(false);
		step.setEditable(false);
		rightPanel.add(name,BorderLayout.WEST);
		rightPanel.add(step,BorderLayout.EAST);
		
		
		panel.add(rightPanel,BorderLayout.EAST);
		
		this.add(panel,BorderLayout.NORTH);
		
	}

	// 界面初始化方法 （窗口）
	private void init() {
		this.setTitle("拼图游戏");
		this.setSize(1050,850);
		this.setLocation(150,100);
		this.setResizable(false);//窗口大小不可变
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}





















