import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Enemy{
	int startX;
	int startY;

	int count = 0;
	int hitDelay = 0;
	int delayTest = 10;

	private BufferedImage enemy1;
	private BufferedImage enemy2;
	private BufferedImage enemy3;
	private BufferedImage enemy4;

	int rand;
	int randMoveX;
	int randMoveY;
	Color black = new Color(0,0,0);
	boolean visible = true;

	boolean[] eProj;

	HealthBar hb;
	EnemyProjectile[] eProjectile;
	

	public Enemy(){


		
		startY = (int)(Math.random()*530 + 10);
		startX = (int)(Math.random()*390 +360);
		rand = (int)(Math.random()*4 + 1);
		randMoveX = (int)(Math.random()*2);
		randMoveY = (int)(Math.random()*2);

		try{
            enemy1 = ImageIO.read(new File("Images/Enemy1.png"));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }  
        try{
            enemy4 = ImageIO.read(new File("Images/Enemy4.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try{
            enemy2 = ImageIO.read(new File("Images/Enemy2.png"));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }
        try{
            enemy3 = ImageIO.read(new File("Images/Enemy3.png"));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }

        hb  = new HealthBar(rand);
        eProjectile = new EnemyProjectile[10];
        for (int i = 0; i<eProjectile.length ; i++ ) {
        	eProjectile[i] = new EnemyProjectile(rand);
        }

        eProj = new boolean[10];
        for ( int i = 0 ; i< eProj.length ; i++ ) {
			eProj[i] = false;
		}
	}
	public void draw(Graphics g){
		if(visible){
			if(rand == 1){
				g.drawImage(enemy1, startX, startY, null);
				hitDelay = 40;
			}
			if(rand == 2){
				g.drawImage(enemy2, startX, startY, null);
				hitDelay = 70;
			}
			if(rand == 3){
				g.drawImage(enemy3, startX, startY, null);
				hitDelay = 50;
			}
			if(rand == 4){
				g.drawImage(enemy4, startX, startY, null);
				hitDelay = 45;
			}
			count++;
		}
		if (count >= (delayTest + hitDelay)) {
			for (int i = 0; i < eProjectile.length ; i++) {
				if (eProj[i] == false) {
					eProjectile[i].fire(startX,startY);
					eProj[i] = true;
					delayTest = count;
					break;
				}
			}
		}
		for (int i = 0; i < eProjectile.length ; i++) {
			eProjectile[i].draw(g);
			eProjectile[i].move();
			eProj[i] = eProjectile[i].getVisible();
		}
		hb.draw(g);
		
	}
	public void move(){
		if(visible){
			if (randMoveX == 0) {
				if (startX >= 750) {
					randMoveX = 1;
				}
				else
					startX += 1;
			}
			else if (randMoveX == 1) {
				if (startX <= 360) {
					randMoveX = 0;
				}
				else
					startX -= 1;
			}
			if (randMoveY == 0){
				if (startY >= 550) {
					randMoveY = 1;
				}
				
				else{
					if (rand == 1) {
						startY += 2;
					}
					else if (rand == 2) {
						startY += 1;
					}
					else if (rand == 3) {
						startY += 4;
					}
					else if (rand == 4) {
						startY += 3;
					}
					
				}
			}
			else if (randMoveY == 1){
				if (startY <= 0) {
					randMoveY = 0;
				}
				else{
					if (rand == 1) {
						startY -= 2;
					}
					else if (rand == 2) {
						startY -= 1;
					}
					else if (rand == 3) {
						startY -= 4;
					}
					else if (rand == 4) {
						startY -= 3;
					}
				}
			}
		}
		hb.setPosition(startX,startY);
	}
	public void checkCollision(Projectile p){
		int pX = p.getX();
		int pY = p.getY();
		int pWidth = p.getWidth();
		int pHeight = p.getHeight();
		if(visible == true && p.getVisible() == true){
			if( pX+pWidth >= startX && pX <= startX + 50  &&  
			pY+pHeight >= startY && pY <= startY + 50 ){
				hb.takeDamage();
				if(hb.getState()){	
						
					visible = false;
					hb.death();
				}
				p.setVisible();		
			}
		}
		
	}
	public int getType(){
		return rand;
	}
	public void checkECollision(Player player){
		player.checkCollision(eProjectile[0], rand);
		player.checkCollision(eProjectile[1], rand);
		player.checkCollision(eProjectile[2], rand);
		player.checkCollision(eProjectile[3], rand);
		player.checkCollision(eProjectile[4], rand);
		player.checkCollision(eProjectile[5], rand);
		player.checkCollision(eProjectile[6], rand);
		player.checkCollision(eProjectile[7], rand);
		player.checkCollision(eProjectile[8], rand);
		player.checkCollision(eProjectile[9], rand);
	}
	public boolean getVisible(){
		return visible;
	}
	public void resetProjectiles(){
		for (int i = 0; i<eProjectile.length ; i++ ) {
			eProjectile[i].setVisible();
		}
	}
}