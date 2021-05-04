package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controllers.Handler;
import graphicComponents.HUD;
import start.Game;
import strategyPattern.StrategyContext;

public class Tracker extends GameObject
{
	
	private float trackerHealth = 40;
	private GameObject playerShip;
	private Handler handler;
	String imagePath;
	
	public StrategyContext strategyContext;

	public Tracker(float x, float y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
		for(int i = 0; i < handler.objectsList.size(); i++)
		{
			if(this.handler.objectsList.get(i).getId() == ID.PlayerShip)
				setPlayerShip(this.handler.objectsList.get(i));
		}		
	}
	
	public void tick()
	{
		strategyContext.executeStrategy(this);

		trackerHealth = Game.clamp(trackerHealth, 0, 40);
		if(trackerHealth <= 0)
		{
			handler.removeObject(this);
			HUD.coins += 100;
		}
			
	}
	
	public void render(Graphics g)
	{
	
		imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\tracker.png";
		BufferedImage img = null;
		try 
		{
			 img = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, (int)x, (int)y, null);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x, (int)y - 10, 80, 5);
		g.setColor(Color.getHSBColor( (1f * trackerHealth) / 100, 1f, 1f));
		g.fillRect((int)x, (int)y - 10, (int)trackerHealth *2, 5);
		g.setColor(Color.WHITE);
		g.drawRect((int)x, (int)y - 10, (int)trackerHealth *2, 5);
	}
			
	public Rectangle getBounds1()
	{
		return new Rectangle((int)x+20, (int)y, 40, 50);
	}
	
	public Rectangle getBounds2()
	{
		return new Rectangle((int)x, (int)y+25, 84, 45);
	}
	
	public void setHealth(float health)
	{
		this.trackerHealth = health;
	}
	
	public float getHealth()
	{
		return this.trackerHealth;
	}

	public StrategyContext getContext() 
	{
		return strategyContext;
	}

	public void setStrategyContext(StrategyContext context)
	{
		this.strategyContext = context;
	}

	
	public GameObject getPlayerShip() {
		return playerShip;
	}

	public void setPlayerShip(GameObject playerShip) {
		this.playerShip = playerShip;
	}
	
	
	
}
