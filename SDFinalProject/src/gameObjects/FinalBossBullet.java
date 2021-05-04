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

public class FinalBossBullet extends GameObject
{
	private Handler handler;

	String imagePath;
	
	public FinalBossBullet(float x, float y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
		speedX = 0;
		speedY = 10;
		
	}
	
	public void tick()
	{
		x += speedX;
		y += speedY;
		
		collision();
		if(y >= Game.HEIGHT-110) handler.removeObject(this);
		
	}
	
	public void collision()
	{
		for(int i = 0; i < handler.objectsList.size(); i++)
		{
			GameObject currentObject = handler.objectsList.get(i);
			
			if(currentObject.getId() == ID.PlayerShip )
			{
				if(getBounds1().intersects(currentObject.getBounds1()) ||
				   getBounds2().intersects(currentObject.getBounds1()) ||
				   getBounds1().intersects(currentObject.getBounds2()) ||
				   getBounds2().intersects(currentObject.getBounds2()))
				{	
					HUD.HEALTH -= 5;
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
	
		imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\bossBullet.png";
	
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
		return new Rectangle((int)x+15, (int)y+11, 30, 78);
	}
	
	public Rectangle getBounds2()
	{

		return new Rectangle((int)x+15, (int)y+11, 30, 78);
	}

	
	
}
