import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Background{
	int randX;
	int randY;

	int moonX;
	int moonY;

	int earthX;
	int earthY;

	Color space = new Color(0,0,0);
	Color moon = new Color(255,255,220);

	int[] starsX = new int[200];
	int[] starsY = new int[200];

	private BufferedImage earth;
	public Background(){

		for (int i = 0; i<starsX.length ; i++ ) {		
			starsX[i] = (int)(Math.random()*800);
			starsY[i] = (int)(Math.random()*600);
		}
		moonX = (int)(Math.random()*800);
		moonY = (int)(Math.random()*600);

		earthX = (int)(Math.random()*740);
		earthY = (int)(Math.random()*540);

		try{
            earth = ImageIO.read(new File("Images/Planet1.png"));
            
        }
        catch(IOException e){
            e.printStackTrace();
        }  
	}
	public void draw(Graphics g){
		
		g.setColor(space);
		g.fillRect(0,0,800,600);
		for (int i = 0; i<200 ; i++ ) {
			Color random = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));	
			g.setColor(random);

			g.fillRect(starsX[i], starsY[i], 1, 1);
		}
		g.setColor(moon);
		g.fillOval(moonX, moonY, 10, 10);
		g.drawImage(earth, earthX, earthY, null);


	}
}