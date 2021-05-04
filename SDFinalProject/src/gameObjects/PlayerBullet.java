package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllers.Handler;
import graphicComponents.HUD;

public class PlayerBullet extends GameObject
{
	private Handler handler;

	String imagePath;
	
	public static float damageToTracker = 4;
	public static float damageToBoss = 0.5f;
	public static float damageToEnemyShip = 4;
	
	public PlayerBullet(float x, float y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
		speedX = 0;
		speedY = -10;
		
	}
	
	public void tick()
	{
		x += speedX;
		y += speedY;
		
		collision();
		if(y <= -50) handler.removeObject(this);
		
	}
	
	public void collision()
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
					handler.removeObject(currentObject);
					handler.removeObject(this);
					HUD.coins +=10;
					
				}
			}
			
			if(currentObject.getId() == ID.Tracker )
			{
				if(getBounds1().intersects(currentObject.getBounds1()) ||
				   getBounds2().intersects(currentObject.getBounds1()) ||
				   getBounds1().intersects(currentObject.getBounds2()) ||
				   getBounds2().intersects(currentObject.getBounds2()))
				{	
					Tracker tracker = (Tracker) currentObject;
					tracker.setHealth(tracker.getHealth()- damageToTracker);
					handler.removeObject(this);
				}
			}
			
			if(currentObject.getId() == ID.EnemyShip1 ||
			   currentObject.getId() == ID.EnemyShip2	)
			{
				if(getBounds1().intersects(currentObject.getBounds1()) ||
				   getBounds2().intersects(currentObject.getBounds1()) ||
				   getBounds1().intersects(currentObject.getBounds2()) ||
				   getBounds2().intersects(currentObject.getBounds2()))
				{	
					EnemyShip enemyShip = (EnemyShip) currentObject;
					enemyShip.setEnemyShipHealth(enemyShip.getEnemyShipHealth() - damageToEnemyShip);
					handler.removeObject(this);
				}
			}
			
			if(currentObject.getId() == ID.FinalBoss )
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
					finalBoss.setBossHealth(finalBoss.getBossHealth()-damageToBoss);
					finalBoss.setDamageTaken(finalBoss.getDamageTaken()+damageToBoss);
					handler.removeObject(this);
					
				}
			}
		}
	}
	
	public void render(Graphics g)
	{
	
		imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\playerBullet.png";
	
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
		return new Rectangle((int)x+18, (int)y+15, 32, 75);
	}
	
	public Rectangle getBounds2()
	{

		return new Rectangle((int)x+18, (int)y+15, 32, 75);
	}	
}
