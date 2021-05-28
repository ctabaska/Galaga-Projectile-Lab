import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player{
	int x;
	int y;
	
	int width;
	int height;
	
	Color blue;

	int moveSpeed = 5;
	boolean visible = true;

    private BufferedImage spaceship;
    PlayerHealth ph = new PlayerHealth();
	
	public Player(int x, int y){
		
		this.x = x;
		this.y = y;
		
		this.width = 50;
		this.height = 50;
		
		this.blue = new Color(0,0,255);

		try{
            spaceship = ImageIO.read(new File("Images/Spaceship.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
		
	}
	

	public void drawMe(Graphics g){
		if (visible) {	
			g.setColor(blue);
			//g.fillRect(x,y,width,height);

			g.drawImage(spaceship, x, y, null);
			ph.draw(g);
		}

	}
	public void moveUp(){
		
		if (y -moveSpeed >= 0) {
			y -= moveSpeed;
		}
		else if( y-moveSpeed < 0){
			y = 0;
		}	
	}
	public void moveDown(){
		if ( y + moveSpeed <= 550) {
			y += moveSpeed;
		}
		else if( y + moveSpeed > 550){
			y = 550;
		}	
	}
	public void moveRight(){
		if ( x + moveSpeed <= 150) {
			x += moveSpeed;
		}
		else if( x + moveSpeed > 150){
			x = 150;
		}	
	}
	public void moveLeft(){
		if (x -moveSpeed >= 0) {
			x -= moveSpeed;
		}
		else if( x-moveSpeed < 0){
			x = 0;
		}
	}
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	public void checkCollision(EnemyProjectile ep, int type){
		int epX = ep.getX();
		int epY = ep.getY();
		int epWidth = ep.getWidth();
		int epHeight = ep.getHeight();
		if(visible == true && ep.getVisible() == true){
			if( epX+epWidth >= x && epX <= x + 50  &&  
			epY+epHeight >= y && epY <= y + 50 ){
				System.out.println("Collision");
				ph.takeDamage(type);
				if(ph.getState()){	
						
					visible = false;
				}
				ep.setVisible();		
			}
		}
		
	}
	public boolean getPlayerHealth(){
		return ph.getState();
	}
	public void resetPlayerHealth(){
		ph.resetHealth();
	}
	public void resetVisible(){
		visible = true;
	}
}
