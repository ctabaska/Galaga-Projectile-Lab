import java.awt.Color;
import java.awt.Graphics;
public class HealthBar{
	int type;
	int x;
	int y;

	int health;
	int maxHealth;
	int healthF = 70;

	boolean visible = true;
	boolean dead = false;

	Color red = new Color(255, 51, 51);
	Color green = new Color(51, 255, 51);

	int projectileDamage = 25;
	public HealthBar(int type){
		this.type = type;
		if(type == 1){
			health = 130;
			maxHealth = 130;
		}
		else if(type == 2){
			health = 200;
			maxHealth = 200;
		}
		else if(type == 3){
			health = 70;
			maxHealth = 70;
		}
		else if(type == 4){
			health = 100;
			maxHealth = 100;
		}
	}
	public void setPosition(int x, int y){

		this.x = x -10;
		this.y = y - 20;
	}
	public void draw(Graphics g){
		if(visible){
			
			if(healthF <= 0){
				healthF = 0;
				dead = true;
			}
			g.setColor(red);
			g.fillRect(x,y,70,5);
			g.setColor(green);
			g.fillRect(x,y,healthF,5);
		}
	}
	public void death(){
		visible = false;
	}
	public void takeDamage(){
	}
	public boolean getState(){
		health -= projectileDamage;
		healthF = (int)(70*((double)health / (double)maxHealth));
		if(healthF<= 0){
			return true;
		}
		else
			return false;
	}
}