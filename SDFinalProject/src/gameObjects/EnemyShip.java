package gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import audio.AudioPlayer;
import controllers.Handler;
import graphicComponents.HUD;
import start.Game;

public class EnemyShip extends GameObject
{
	private Handler handler;
	private GameObjectFactory gameObjectFactory;
	String imagePath;
	
	private int bulletTimer1;
	private int bulletTimer2;
	private float enemyShipHealth = 40;
	

	Random r = new Random();
	
	public EnemyShip(float x, float y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
		if(id == ID.EnemyShip1)
		{
			speedX = 5;
			speedY = 0;
		}
		else if (id == ID.EnemyShip2)
		{
			speedX = -5;
			speedY = 0;
		}		 
	}

	public void tick()
	{
		x += speedX;
		y += speedY;
		bulletTimer1++;
		bulletTimer2++;
		
		if(id == ID.EnemyShip1)
		{
			if(bulletTimer1>= 50 && x <= Game.WIDTH-100)
			{
				
				handler.addObject(gameObjectFactory.makeGameObject((int)x +80, (int)y+5, ID.EnemyBullet1, handler));
				AudioPlayer.getSound("bossLaserSound").play();
				bulletTimer1 = 0;
			}	
		}
		else
		if(id == ID.EnemyShip2 && x >= 0)
		{
			if(bulletTimer2 >= 50)
			{
				handler.addObject(gameObjectFactory.makeGameObject((int)x-95, (int)y+27, ID.EnemyBullet2, handler));
				AudioPlayer.getSound("bossLaserSound").play();
				bulletTimer2 = 0;
			}
		}
		
		if(id == ID.EnemyShip1)
		{
			if(x >= Game.WIDTH+20)
			{
				
				EnemyShip enemyShip = (EnemyShip) gameObjectFactory.makeGameObject(Game.WIDTH+20, y, ID.EnemyShip2, handler);
				enemyShip.setGameObjectFactory(gameObjectFactory);
				enemyShip.setEnemyShipHealth(this.getEnemyShipHealth());
				handler.removeObject(this);
				handler.addObject(enemyShip);
			}
				
		}
		else if (id == ID.EnemyShip2)
		{
			if(x <= -105)
			{
				EnemyShip enemyShip = (EnemyShip) gameObjectFactory.makeGameObject(x-105, y, ID.EnemyShip1, handler);
				enemyShip.setGameObjectFactory(gameObjectFactory);
				enemyShip.setEnemyShipHealth(this.getEnemyShipHealth());
				handler.removeObject(this);
				handler.addObject(enemyShip);
			}
				
		}	
		
		if(enemyShipHealth <=0)
		{
			handler.removeObject(this);
			HUD.coins += 200;
		}
		
	}
	
	public void render(Graphics g)
	{
	
		if(id == ID.EnemyShip1)
			imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\enemyShip1.png";
		else if (id == ID.EnemyShip2)
			imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\enemyShip2.png";
		BufferedImage img = null;
		try 
		{
			 img = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, (int)x, (int)y, null);
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x+20, (int)y +5, 80, 5);
		g.setColor(Color.getHSBColor( (1f * enemyShipHealth) / 100, 1f, 1f));
		g.fillRect((int)x+20, (int)y +5, (int)enemyShipHealth *2, 5);
		g.setColor(Color.WHITE);
		g.drawRect((int)x+20, (int)y +5, (int)enemyShipHealth *2, 5);	
	}
		
	public Rectangle getBounds1()
	{
		if(id == ID.EnemyShip1)
			return new Rectangle((int)x+10, (int)y+41, 90, 28);
		else 
		if(id == ID.EnemyShip2)
			return new Rectangle((int)x+83, (int)y+29, 20, 81);

		
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public Rectangle getBounds2()
	{
		if(id == ID.EnemyShip1)
			return new Rectangle((int)x+20, (int)y+14, 21, 81);
		else
		if(id == ID.EnemyShip2)
			return new Rectangle((int)x+23, (int)y+55, 90, 28);
		
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public float getEnemyShipHealth() {
		return enemyShipHealth;
	}

	public void setEnemyShipHealth(float enemyShipHealth) {
		this.enemyShipHealth = enemyShipHealth;
	}

	public GameObjectFactory getGameObjectFactory() {
		return gameObjectFactory;
	}

	public void setGameObjectFactory(GameObjectFactory gameObjectFactory) {
		this.gameObjectFactory = gameObjectFactory;
	}

	
	
}
