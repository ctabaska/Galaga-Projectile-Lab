import java.awt.Graphics;
import java.awt.Color;

public class Projectile{
	int x;
	int y;

	int width;
	int height;

	int projectileSpeed;
	Color red = new Color(255,102,102);

	boolean visible;
	public Projectile(int x, int y){
		this.x = x;
		this.y = y;

		this.width = 10;
		this.height = 10;
		this.visible = false;
		this.projectileSpeed = 10;
	}
	public void draw(Graphics g){
		if (visible) {
			g.setColor(red);
			g.fillOval(x,y,width,height);
		}
	}
	public void move(){
		if( visible ){
			x += projectileSpeed;
			
			if( x > 800)
				visible = false;
		}
	}
	public void fire(int x, int y){
		this.x = (x + 40);
		this.y = (y + 20);
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