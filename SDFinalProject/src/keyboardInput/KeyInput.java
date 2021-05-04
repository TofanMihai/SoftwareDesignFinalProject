package keyboardInput;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import audio.AudioPlayer;
import controllers.Handler;
import controllers.Spawner;
import gameObjects.FinalBoss;
import gameObjects.GameObject;
import gameObjects.GameObjectFactory;
import gameObjects.ID;
import graphicComponents.HUD;
import graphicComponents.State;
import start.Game;

public class KeyInput extends KeyAdapter
{
	private Game game;
	private Handler handler;
	private Spawner spawner;
	private GameObjectFactory gameObjectFactory;
	
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	
	private int bulletTimer = 70;
	private int bombTimer = 450;
	private int bulletCondition = 70;
	private int bombCondition = 450;
	
	private int playerSpeed = 6;
	
	private boolean oneGun = true, twoGuns = false, threeGuns = false;


	public KeyInput(Game game, GameObjectFactory gameObjectFactory, Handler handler, Spawner spawner)
	{
		this.game = game;
		this.gameObjectFactory = gameObjectFactory;
		this.handler = handler;
		this.spawner = spawner;
	}
	
	public void tick()
	{
		this.bulletTimer++;
		this.bombTimer++;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
	
		for(int i = 0; i < handler.objectsList.size(); i++)
		{
			GameObject currentObject = handler.objectsList.get(i);
			
			
			if(currentObject.getId() == ID.PlayerShip  && !Game.paused)
			{
				if(key == KeyEvent.VK_W)
				{
					up = true;
					currentObject.setSpeedY(-playerSpeed);
				}
				if(key == KeyEvent.VK_D)
				{
					right = true;
					currentObject.setSpeedX(playerSpeed);
				}
				if(key == KeyEvent.VK_S)
				{	down = true;
					currentObject.setSpeedY(playerSpeed);
				}
				if(key == KeyEvent.VK_A)
				{	left = true;
					currentObject.setSpeedX(-playerSpeed);
				}
				if(key == KeyEvent.VK_P && this.bulletTimer >=this.bulletCondition && game.gameState == State.Game)
				{
					if(oneGun)
					{
						handler.addObject(gameObjectFactory.makeGameObject(currentObject.getX()+70,currentObject.getY()-50,ID.PlayerBullet, handler));
						AudioPlayer.getSound("playerLaserSound").play();
						this.bulletTimer = 0;
					}
					else if(twoGuns)
					{
						handler.addObject(gameObjectFactory.makeGameObject(currentObject.getX()+49,currentObject.getY()-25,ID.PlayerBullet, handler));
						handler.addObject(gameObjectFactory.makeGameObject(currentObject.getX()+95,currentObject.getY()-25,ID.PlayerBullet, handler));
						AudioPlayer.getSound("playerLaserSound").play();
						this.bulletTimer = 0;
					}
					else if(threeGuns)
					{
						handler.addObject(gameObjectFactory.makeGameObject(currentObject.getX()+49,currentObject.getY()-25,ID.PlayerBullet, handler));
						handler.addObject(gameObjectFactory.makeGameObject(currentObject.getX()+95,currentObject.getY()-25,ID.PlayerBullet, handler));
						handler.addObject(gameObjectFactory.makeGameObject(currentObject.getX()+70,currentObject.getY()-50,ID.PlayerBullet, handler));
						AudioPlayer.getSound("playerLaserSound").play();
						this.bulletTimer = 0;
					}
					
					
				}
				if(key == KeyEvent.VK_O && this.bombTimer >=bombCondition && game.gameState == State.Game)
				{	
					handler.addObject(gameObjectFactory.makeGameObject(currentObject.getX()+50,currentObject.getY()+150,ID.Bomb, handler));
					this.bombTimer = 0;
				}
				
			}
		}
		
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
		if(key == KeyEvent.VK_SPACE)
		{
			if(game.gameState == State.Game)
			{

		    	if(Game.paused == false)
		    	    Game.paused = true;
		    	else 
		    		Game.paused = false;
		    	System.out.println(Game.paused);
			}
			
		}
		
		if(key == KeyEvent.VK_ENTER)
		{
			if(game.gameState == State.Game)
			{
				game.gameState = State.Shop;
			}
			else
			if(game.gameState == State.Shop)
				game.gameState = State.Game;
		}
		
		
		// DEGEEEEEEEEEEEEEEEEABA
		if(key == KeyEvent.VK_R) HUD.HEALTH = 150;
		if(key == KeyEvent.VK_K) 
		{
			HUD.HEALTH -=5;
		}
		if(key == KeyEvent.VK_6) FinalBoss.bossHealth -= 50;
		
		if(key == KeyEvent.VK_9) {
			spawner.setCoinsKeep(spawner.getCoinsKeep()+2000);
			spawner.setScoreKeep(spawner.getScoreKeep()+2000);
		}

		// DEGEEEEEEEEEEEEEEEEABA
	}
	

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0 ; i < handler.objectsList.size(); i++)
		{
			GameObject currentObject = handler.objectsList.get(i);
			
			if(currentObject.getId() == ID.PlayerShip )
			{
				if(key == KeyEvent.VK_W)
				{
					up = false;
					currentObject.setSpeedY(0);
					if(down)
						currentObject.setSpeedY(playerSpeed);
					else
						currentObject.setSpeedY(0);
				}
				if(key == KeyEvent.VK_D)
				{
					right = false;
					currentObject.setSpeedX(0);
					if(left)
						currentObject.setSpeedX(-playerSpeed);
					else
						currentObject.setSpeedX(0);
				}
				if(key == KeyEvent.VK_S)
				{
					down = false;
					currentObject.setSpeedY(0);
					if(up)
						currentObject.setSpeedY(-playerSpeed);
					else
						currentObject.setSpeedY(0);
				}
				if(key == KeyEvent.VK_A)
				{
					left = false;
					currentObject.setSpeedX(0);
					if(right)
						currentObject.setSpeedX(playerSpeed);
					else
						currentObject.setSpeedX(0);
				}
				
			}
		}
	}

	public int getBulletCondition() {
		return bulletCondition;
	}

	public void setBulletCondition(int bulletCondition) {
		this.bulletCondition = bulletCondition;
	}
	
	public int getPlayerSpeed() {
		return playerSpeed;
	}

	public void setPlayerSpeed(int playerSpeed) {
		this.playerSpeed = playerSpeed;
	}
	
	public int getBombCondition() {
		return bombCondition;
	}

	public void setBombCondition(int bombCondition) {
		this.bombCondition = bombCondition;
	}
	
	public boolean isOneGun() {
		return oneGun;
	}

	public void setOneGun(boolean oneGun) {
		this.oneGun = oneGun;
	}

	public boolean isTwoGuns() {
		return twoGuns;
	}

	public void setTwoGuns(boolean twoGuns) {
		this.twoGuns = twoGuns;
	}

	public boolean isThreeGuns() {
		return threeGuns;
	}

	public void setThreeGuns(boolean threeGuns) {
		this.threeGuns = threeGuns;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}
	
	

	

}
