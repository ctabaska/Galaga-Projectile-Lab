import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
public class PlayerHealth{
	int type;
	int x;
	int y;

	int health;
	int maxHealth;
	int healthF = 200;

	boolean visible = true;
	boolean dead = false;

	Color black = new Color(255,255,255);
	Color red = new Color(1f, 0.2f, 0.2f, 0.6f);
	Color green = new Color(0.2f, 1f, 0.2f, 0.6f);

	int projectileDamage = 25;
	public PlayerHealth(){
		health = 500;
		maxHealth = 500;
	}
	public void draw(Graphics g){
		g.setFont(new Font("Impact", Font.PLAIN, 12));
		if(visible){
			
			if(healthF <= 0){
				healthF = 0;
				dead = true;
			}
			g.setColor(red);
			g.fillRect(x,y,200,20);
			g.setColor(green);
			g.fillRect(x,y,healthF,20);
			g.setColor(black);
			g.drawString("Health: " + healthF  + "/200", 20,15);
		}
	}
	public void death(){
		visible = false;
	}
	public void takeDamage(int type){
		health -= projectileDamage;
		healthF = (int)(200*((double)health / (double)maxHealth));
	}
	public boolean getState(){
		
		if(healthF<= 0){
			return true;
		}
		else
			return false;
	}

	public void resetHealth(){
		health = 500;
		healthF = (int)(200*((double)health / (double)maxHealth));
	}
}