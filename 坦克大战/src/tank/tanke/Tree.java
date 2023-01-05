package tank.tanke;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Ê÷
 * @author acer
 *
 */
public class Tree {
	private static Image img;
	private int x,y;
	public Tree(int x,int y){
		this.x=x;
		this.y=y;
	}
	static {
		img=CommonWall.tk.getImage(Tree.class.getResource("../../Images/tree.gif"));
	}
	public void draw(Graphics g){
		g.drawImage(img,x,y,null);
	}
}
