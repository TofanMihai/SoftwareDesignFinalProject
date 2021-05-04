package gameObjects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import controllers.Handler;
import graphicComponents.HUD;
import start.Game;

public class PlayerShip extends GameObject
{
	
	private Handler handler;
	String imagePath;
	
	
	Random r = new Random();
	
	public PlayerShip(float x, float y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		

	}
	
	public void tick()
	{
		x += speedX;
		y += speedY;
		
		
		x = Game.clamp((int)x, -65, Game.WIDTH -140);
		y = Game.clamp((int)y, -40, Game.HEIGHT-173);
		
		
		
		
		
		collision();
		
		if(HUD.HEALTH <= 0.0)
		{
			handler.removeObject(this);
		}
		
	}
	
	

	private void collision()
	{
		for(int i = 0; i < handler.objectsList.size(); i++)
		{
			GameObject currentObject = handler.objectsList.get(i);
			if(currentObject.getId() == ID.Asteroid1 ||
		       currentObject.getId() == ID.Asteroid2 ||
		       currentObject.getId() == ID.Asteroid3 )
			{
				if(getBounds1().intersects(currentObject.getBounds1()) ||
				   getBounds2().intersects(currentObject.getBounds1()) ||
				   getBounds1().intersects(currentObject.getBounds2()) ||
				   getBounds2().intersects(currentObject.getBounds2()))
				{
					HUD.HEALTH -=10;
					
					handler.removeObject(currentObject);
				}
			}
			
			if(currentObject.getId() == ID.Tracker)
			{
				if(getBounds1().intersects(currentObject.getBounds1()) ||
				   getBounds2().intersects(currentObject.getBounds1()) ||
				   getBounds1().intersects(currentObject.getBounds2()) ||
				   getBounds2().intersects(currentObject.getBounds2()))
				{
					HUD.HEALTH -=50;
					handler.removeObject(currentObject);
				}
			}
			
			if(currentObject.getId() == ID.EnemyShip1 ||
			   currentObject.getId() == ID.EnemyShip2)
			{
				if(getBounds1().intersects(currentObject.getBounds1()) ||
				   getBounds2().intersects(currentObject.getBounds1()) ||
				   getBounds1().intersects(currentObject.getBounds2()) ||
				   getBounds2().intersects(currentObject.getBounds2()))
				{
					HUD.HEALTH -=20;
					handler.removeObject(currentObject);
				}
			}
			
			if(currentObject.getId() == ID.FinalBoss)
			{
				FinalBoss finalBoss = (FinalBoss) currentObject;
				
				if(getBounds1().intersects(finalBoss.getBounds1()) ||
				   getBounds1().intersects(finalBoss.getBounds2()) ||
				   getBounds1().intersects(finalBoss.getBounds3()) ||
				   getBounds1().intersects(finalBoss.getBounds4()) ||
				   getBounds1().intersects(finalBoss.getBounds5()) ||
				   getBounds2().intersects(finalBoss.getBounds1()) ||
				   getBounds2().intersects(finalBoss.getBounds2()) ||
				   getBounds2().intersects(finalBoss.getBounds3()) ||
				   getBounds2().intersects(finalBoss.getBounds4()) ||
				   getBounds2().intersects(finalBoss.getBounds5()))
				{
					HUD.HEALTH --;
				}
			}
		}
	}
	
	public void render(Graphics g)
	{

		imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\rocket.png";
		BufferedImage img = null;
		try 
		{
			 img = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, (int)x, (int)y, null);	
	}
	
	public Rectangle getBounds1()
	{
		return new Rectangle((int)x+68, (int)y+71, 64, 63);
		
	}
	
	public Rectangle getBounds2()
	{
		return new Rectangle((int)x+83, (int)y+40, 30, 95);
	}
	
}
