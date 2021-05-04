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
import start.Game;
import strategyPattern.DiverStrategy;
import strategyPattern.StrategyContext;

public class FinalBoss extends GameObject
{
	private Handler handler;
	private GameObjectFactory gameObjectFactory;
	private StrategyContext strategyContext;
	
	private int timer1 = 300, timer2 = 50;
	public static float bossHealth = 200;
	private float damageTaken = 0;
	
	private int trackerTimer = 0, enemyShipTimer = 0;
	
	

	String imagePath;
	Random r = new Random();
	
	public FinalBoss(float x, float y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		speedX = 0;
		speedY = 1;
		
	}
	
	public int rndRange(int start, int finish)
	{
	  Random rnd = new Random();
	  int randomNumber = start + rnd.nextInt(finish + 1 - start);
	  return randomNumber;

	}
	
	public void tick()
	{
		x += speedX;
		y += speedY;
		
		trackerTimer++;
		enemyShipTimer++;
		
		
		bossHealth = Game.clamp(bossHealth, 0, 200);
		if(bossHealth <=0)
			handler.removeObject(this);
		
		if(timer1 <=0) speedY = 0;
		else timer1 --;
				
		if(timer1 <= 0 ) timer2--;
		if(timer2 <= 0)
		{
			if(speedX ==0) speedX = 2;
			
			if(speedX >0) speedX += 0.001;
			if(speedX <0) speedX -= 0.001;
			
			speedX = Game.clamp(speedX, -7, 7);
			
			int spawnBullet = r.nextInt(10);
			if(spawnBullet == 0)
			{
				handler.addObject(gameObjectFactory.makeGameObject((int)x+70, (int)y+190, ID.FinalBossBullet, handler));
				AudioPlayer.getSound("bossLaserSound").play();
			}
			
			if(trackerTimer >= 1000) 
			{	
				Tracker tracker = (Tracker)(gameObjectFactory.makeGameObject((int)x+70, (int)y+190, ID.Tracker, handler));
				strategyContext = new StrategyContext(new DiverStrategy(handler));
				tracker.setStrategyContext(strategyContext);
				handler.addObject(tracker);
				trackerTimer = 0;
			}
			if(enemyShipTimer >= 1000) 
			{
				EnemyShip enemyShip = (EnemyShip)(gameObjectFactory.makeGameObject(-100, rndRange(100, Game.HEIGHT-300), ID.EnemyShip1, handler));
				enemyShip.setGameObjectFactory(gameObjectFactory);
				handler.addObject(enemyShip);
				enemyShipTimer = 0;
			}
			
			int randomSpawn = r.nextInt(1000);
			if(randomSpawn == 0) 
			{
				Tracker tracker = (Tracker)(gameObjectFactory.makeGameObject((int)x+70, (int)y+190, ID.Tracker, handler));
				strategyContext = new StrategyContext(new DiverStrategy(handler));
				tracker.setStrategyContext(strategyContext);
				handler.addObject(tracker);
				
				EnemyShip enemyShip = (EnemyShip)(gameObjectFactory.makeGameObject(-100, rndRange(100, Game.HEIGHT-300), ID.EnemyShip1, handler));
				enemyShip.setGameObjectFactory(gameObjectFactory);
				handler.addObject(enemyShip);
			}
			
		}
		
		if(x <= 0 || x >= Game.WIDTH -200) speedX *= -1;
	}
	
	public void render(Graphics g)
	{
	
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)(Game.WIDTH-415), 15 , 400, 32);
		g.setColor(Color.RED);
		g.fillRect((int)(Game.WIDTH-415+damageTaken*2), 15, (int)(bossHealth*2), 32);
		g.setColor(Color.WHITE);
		g.drawRect((int)(Game.WIDTH-415+damageTaken*2), 15, (int)(bossHealth*2), 32);
	
		imagePath = "D:\\Eclipse\\Workspace\\SDFinalProject\\Images\\boss.png";
	
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
		return new Rectangle((int)x+3, (int)y+10, 194, 40);
	}
	
	public Rectangle getBounds2()
	{	
		return new Rectangle((int)x+20, (int)y+60, 155, 25);
	}
	
	public Rectangle getBounds3()
	{	
		return new Rectangle((int)x+43, (int)y+95, 112, 32);
	}
	
	public Rectangle getBounds4()
	{	
		return new Rectangle((int)x+60, (int)y+135, 78, 30);
	}
	public Rectangle getBounds5()
	{	
		return new Rectangle((int)x+73, (int)y+170, 55, 23);
	}
	
	public float getBossHealth() {
		return bossHealth;
	}

	public void setBossHealth(float bossHealth) {
		FinalBoss.bossHealth = bossHealth;
	}
	
	public float getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(float damageTaken) {
		this.damageTaken = damageTaken;
	}

	public GameObjectFactory getGameObjectFactory() {
		return gameObjectFactory;
	}

	public void setGameObjectFactory(GameObjectFactory gameObjectFactory) {
		this.gameObjectFactory = gameObjectFactory;
	}
	
	

	
	
}
