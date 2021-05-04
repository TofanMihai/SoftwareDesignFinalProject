package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import audio.AudioPlayer;
import controllers.Handler;
import graphicComponents.HUD;
import start.Game;

public class EnemyBullet extends GameObject
{
	private Handler handler;

	String imagePath;
	
	public EnemyBullet(float x, float y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
		if(id == ID.EnemyBullet1)
		{
			speedX = 15;
			speedY = 0;
		}
		else if (id == ID.EnemyBullet2)
		{
			speedX = -15;
			speedY = 0;
		}	
		
	}
	
	public void tick()
	{
		x += speedX;
		y += speedY;
		
		if(id == ID.EnemyBullet1)
		{
			if(x >= Game.WIDTH-45)
				handler.removeObject(this);
		}
		else if (id == ID.EnemyBullet2)
		{
			if(x <= -105)
				handler.removeObject(this);
		}	
		
		collision();
		if(y < -100) handler.removeObject(this);
		
	}
	
	public void collision()
	{
		for(int i = 0; i < handler.objectsList.size(); i++)
		{
			GameObject currentObject = handler.objectsList.get(i);
			if(currentObject.getId() == ID.PlayerShip)
			{
				if(getBounds1().intersects(currentObject.getBounds1()) ||
				   getBounds2().intersects(currentObject.getBounds1()) ||
				   getBounds1().intersects(currentObject.getBounds2()) ||
				   getBounds2().intersects(currentObject.getBounds2()))
				{	
					HUD.HEALTH -= 2;
					handler.removeObject(this);
				}
			}
			
			if(currentObject.getId() == ID.Bomb)
			{
				if(getBounds1().intersects(currentObject.getBounds1()) ||
				   getBounds2().intersects(currentObject.getBounds1()) ||
				   getBounds1().intersects(currentObject.getBounds2()) ||
				   getBounds2().intersects(currentObject.getBounds2()))
				{	
					handler.removeObject(currentObject);
					handler.removeObject(this);
					AudioPlayer.getSound("explosionSound").play();
				}
			}
			
			
		}
	}
	
	public void render(Graphics g)
	{
	
		if(id == ID.EnemyBullet1)
			imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\enemyBullet1.png";
		else
		if (id == ID.EnemyBullet2)
			imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\enemyBullet2.png";
	
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
		if(id == ID.EnemyBullet1)
			return new Rectangle((int)x+52, (int)y+33, 53, 27);
		else 
		if(id == ID.EnemyBullet2)
			return new Rectangle((int)x+36, (int)y+29, 53, 27);
		
		return new Rectangle((int)x, (int)y, 0, 0);
	}
	
	public Rectangle getBounds2()
	{

		if(id == ID.EnemyBullet1)
			return new Rectangle((int)x+21, (int)y+41, 30, 15);
		else
		if(id == ID.EnemyBullet2)
			return new Rectangle((int)x+90, (int)y+33, 26, 15);
		
		return new Rectangle((int)x, (int)y, 0, 0);
	}

	
	
}
