package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllers.Handler;
import start.Game;

public class Asteroid extends GameObject
{
	private Handler handler;
	String imagePath;
	
	public Asteroid(float x, float y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
		if(id == ID.Asteroid1)
		{
			speedX = 0;
			speedY = 2;
		}
		else if (id == ID.Asteroid2)
		{
			speedX = 5;
			speedY = 10;
		}
		else if (id == ID.Asteroid3)
		{
			speedX = -3;
			speedY = 3;
		}
		
		 
		
	}
	
	public void tick()
	{
		x += speedX;
		y += speedY;
		
		if(x >= Game.WIDTH) handler.removeObject(this);
		if(y >= Game.HEIGHT-60) handler.removeObject(this);
	}
	
	public void render(Graphics g)
	{
	
		if(id == ID.Asteroid1)
			imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\asteroid1.png";
		else if (id == ID.Asteroid2)
			imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\asteroid2.png";
		else if (id == ID.Asteroid3)
			imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\asteroid3.png";
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
		if(id == ID.Asteroid1)
			return new Rectangle((int)x+20, (int)y+30, 53, 50);
		else if(id == ID.Asteroid2)
			return new Rectangle((int)x+25, (int)y+30, 55, 50);
		else if(id == ID.Asteroid3)
			return new Rectangle((int)x+10, (int)y+20, 70, 50);
		
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public Rectangle getBounds2()
	{
		if(id == ID.Asteroid1)
			return new Rectangle((int)x+30, (int)y+10, 30, 80);
		else if(id == ID.Asteroid2)
			return new Rectangle((int)x+7, (int)y+40, 85, 30);
		else if(id == ID.Asteroid3)
			return new Rectangle((int)x+5, (int)y+30, 85, 20);
		
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	
	
}
