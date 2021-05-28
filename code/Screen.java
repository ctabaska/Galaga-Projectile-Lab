import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Screen extends JPanel implements KeyListener{


	Player player;
	Projectile[] projectile;
	Enemy[] enemy;

	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	boolean space = false;

	boolean start = false;

	boolean tempBoolean;

	boolean dead = false;

	boolean[] proj;

	int fireDelay = 20;
	int timer = 0;
	int delayTest = 0;

	Background background = new Background();

	private BufferedImage startScreen;

	int score = 0;
	Color white = new Color(255, 255, 255);
	String score1 = "0";

	Color black = new Color(0,0,0);

	public Screen(){

		try{
            startScreen = ImageIO.read(new File("Images/StartScreen.png"));
            
        }
        catch(IOException e){
            e.printStackTrace();
        } 
		
		player = new Player(50,275);
		projectile = new Projectile[10];
		for (int i = 0; i< projectile.length ; i++) {
			projectile[i] = new Projectile(50, 300);
		}
		proj = new boolean[10];
		for ( int i = 0 ; i< proj.length ; i++ ) {
			proj[i] = false;
		}
		enemy = new Enemy[5];
		for (int i = 0; i<enemy.length ; i++ ) {
			enemy[i] = new Enemy();
		}

		addKeyListener(this);
		setFocusable(true);
	}

	public Dimension getPreferredSize() {
		//Sets the size of the panel
        return new Dimension(800,600);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(start == false && dead == false){
        	g.drawImage(startScreen,0,0,null);
        }
        else if(start == true && dead == false){
	        background.draw(g);

			g.setColor(Color.black);
			if(up)
				player.moveUp();
			if(down)
				player.moveDown();
			if(right)
				player.moveRight();
			if(left)
				player.moveLeft();
			if(space){
				if (timer >= (delayTest + fireDelay)) {
					for (int i = 0; i < proj.length ; i++) {
						if (proj[i] == false) {
							projectile[i].fire(player.getX() , player.getY());
							proj[i] = true;
							delayTest = timer;
							break;
						}
					}
					
				}
			}
			for (int i = 0; i<proj.length ; i++ ) {
				proj[i] = projectile[i].getVisible();
				projectile[i].move();
				projectile[i].draw(g);
			}
			//Draw player
			player.drawMe(g);

			
			for (int i = 0; i <enemy.length ; i++ ) {
				enemy[i].checkCollision(projectile[0]);
				enemy[i].checkCollision(projectile[1]);
				enemy[i].checkCollision(projectile[2]);
				enemy[i].checkCollision(projectile[3]);
				enemy[i].checkCollision(projectile[4]);
				enemy[i].checkCollision(projectile[5]);
				enemy[i].checkCollision(projectile[6]);
				enemy[i].checkCollision(projectile[7]);
				enemy[i].checkCollision(projectile[8]);
				enemy[i].checkCollision(projectile[9]);
				enemy[i].move();
				enemy[i].draw(g);
				enemy[i].checkECollision(player);
			}

			timer++;
			score = 0;
			for(int i= 0; i<enemy.length; i++){
				if(enemy[i].getVisible() == false){
				score++;
				}
			}
			score1 = Integer.toString(score);
			g.setColor(white);
			g.drawString(score1, 50,40);
			g.drawString("Score:", 10, 40);
			if (player.getPlayerHealth()) {
				start = false;
				dead = true;
			}
		}
		else if(dead == true){
			g.setFont(new Font("Impact", Font.PLAIN, 60));
			g.setColor(white);
			g.fillRect(0,0,800,600);

			g.setColor(black);
			g.drawString("YOU HAVE DIED", 240, 300);
			g.drawString("press R to restart", 120, 380);
		}
	} 
	public void keyPressed(KeyEvent e){
		//System.out.println("KeyCode:" + e.getKeyCode());
		if( e.getKeyCode() == 87){
			up = true;
		}
		if( e.getKeyCode() == 83){
			down = true;
		}
		if( e.getKeyCode() == 68){
			right = true;
		}
		if( e.getKeyCode() == 65){
			left = true;
		}
		if( e.getKeyCode() == 32){
			if(start == false)
				start = true;

			else if(start == true && dead == false){
				space = true;
			}
		}
		if( e.getKeyCode() == 82){
			if(start == false && dead == true){
				start = false;
				dead = false;
				player.resetPlayerHealth();
				player.resetVisible();
				for (int i = 0; i<enemy.length ; i++ ) {
					enemy[i].resetProjectiles();
				}
			}
		}
	}
	public void keyReleased(KeyEvent e){
		if( e.getKeyCode() == 87){
			up = false;
		}
		if( e.getKeyCode() == 83){
			down = false;
		}
		if( e.getKeyCode() == 68){
			right = false;
		}
		if( e.getKeyCode() == 65){
			left = false;
		}
		if( e.getKeyCode() == 32){
			space = false;
		}
	}
	public void keyTyped(KeyEvent e){}

	public void animate(){ 
		while(true){
			//wait for .01 second
			try {
			    Thread.sleep(16);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}

			
			
			//repaint the graphics drawn
			repaint();
		}
	}
}
