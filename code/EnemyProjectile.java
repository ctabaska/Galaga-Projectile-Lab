import java.awt.Graphics;
import java.awt.Color;
public class EnemyProjectile{
	int x;
	int y;
	int type;
	int pSpeed = 3;
	int width = 10;
	int height = 10;

	boolean visible = false;

	Color enemy1 = new Color(61, 139, 105);
	Color enemy2 = new Color(118, 81, 179);
	Color enemy3 = new Color(179, 81, 141);
	Color enemy4 = new Color(179, 139, 81);
	public EnemyProjectile(int type){
		this.type = type;
	}
	public void draw(Graphics g){
		if (visible) {
			if(type == 1)
				g.setColor(enemy1);
			else if(type == 2){
				g.setColor(enemy2);
				pSpeed = 1;
			}
			else if(type == 3){
				g.setColor(enemy3);
				pSpeed = 6;
			}
			else if(type == 4)
				g.setColor(enemy4);
			g.fillOval(x,y,width,height);
		}
	}
	public void move(){
		if (visible) {
			x -= pSpeed;
			if (x <= -10) {
				visible = false;
			}
		}
	}
	public void fire(int x, int y){
		this.x = x;
		this.y = y+20;
		visible = true;
	}
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean getVisible(){
		return visible;
	}
	public void setVisible(){
		visible = false;
	}
}