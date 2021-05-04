package start;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import audio.AudioPlayer;
import bll.LeaderboardBLL;
import bll.RecordHistoryBLL;
import controllers.Handler;
import controllers.Spawner;
import database.objects.Leaderboard;
import database.objects.RecordHistory;
import gameObjects.FinalBoss;
import gameObjects.GameObjectFactory;
import gameObjects.PlayerBullet;
import graphicComponents.BossNotification;
import graphicComponents.HUD;
import graphicComponents.Menu;
import graphicComponents.Shop;
import graphicComponents.State;
import graphicComponents.Window;
import keyboardInput.KeyInput;
import observerPattern.Observer;

public class Game extends Canvas implements Runnable, Observer
{
	private static final long serialVersionUID = -2048925152051415822L;
	public static final int WIDTH = 1024, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	
	Random r = new Random();
	private Handler handler;
	private HUD hud;
	private Spawner spawner;
	private KeyInput keyInput;
	private Menu menu;
	private Shop shop;
	public Window window;
	private GameObjectFactory gameObjectFactory;
	
	public State gameState =  State.Menu;
	
	public Game()
	{
		gameObjectFactory = new GameObjectFactory();
		handler = new Handler();
		hud = new HUD();
		
		spawner = new Spawner(handler, gameObjectFactory, hud);
		keyInput = new KeyInput(this, gameObjectFactory, handler, spawner);
		this.addKeyListener(keyInput);
		shop = new Shop(this, handler, hud, keyInput);
		menu = new Menu(this, gameObjectFactory, handler);
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		LeaderboardBLL leaderboardBLL = new LeaderboardBLL();
		menu.leaderboardList = leaderboardBLL.selectLeaderboard();
		AudioPlayer.getMusic("menuMusic").loop();
		
		
		window = new Window(WIDTH, HEIGHT, "Rocket Odyssey", this);
		spawner.registerObserver(this);
		spawner.registerObserver(menu);
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 65.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				System.out.println("OBJECTS: " +handler.objectsList.size());
				frames = 0;
			}
		}
		
		stop();
	}
	
	private void refreshGame()
	{
		HUD.HEALTH = 150;
		FinalBoss.bossHealth = 200;
		
		PlayerBullet.damageToBoss = 0.5f;
		PlayerBullet.damageToEnemyShip = 4;
		PlayerBullet.damageToTracker = 4;
		
		spawner.setScoreKeep(0);
		spawner.setCoinsKeep(0);
	
		hud.setLevel(1);
		hud.setCoins(0);	
		hud.setBounds(0);
		hud.setScore(0);
		
		
		shop.setHealthCost(1000);
		shop.setFireRateCost(1000);
		shop.setMaxHealthCost(1000);
		shop.setBombCost(1000);
		shop.setDamageCost(1000);
		shop.setGunsCost(5000);
		
		shop.setFireRateLimit(5);
		shop.setMaxHealthLimit(5);
		shop.setBombLimit(5);
		shop.setDamageLimit(5);
		shop.setGunsLimit(2);
		
		keyInput.setBulletCondition(70);
		keyInput.setBombCondition(450);
		keyInput.setOneGun(true);
		keyInput.setTwoGuns(false);
		keyInput.setThreeGuns(false);
		keyInput.setUp(false);
		keyInput.setDown(false);
		keyInput.setLeft(false);
		keyInput.setRight(false);
		
		spawner.setOneTime(true);
		spawner.setTrackerTimer(2000);
	}
	
	private void tick()
	{
		if(gameState == State.Game)
		{
			if(!paused)
			{
				handler.tick();
				hud.tick();
				spawner.tick();
				keyInput.tick();
				shop.tick();
				if(hud.getLevel() == 1)
					menu.tickAsteroids();
				
				if(HUD.HEALTH <= 0.0)
				{			
					
					LeaderboardBLL leaderboardBLL = new LeaderboardBLL();
					Leaderboard leaderboard = new Leaderboard(menu.getUsername(), hud.getScore(), hud.getLevel());
					
					leaderboardBLL.insertLeaderboard(leaderboard);
					leaderboardBLL.updateLeaderboard(leaderboard, hud.getScore(), hud.getLevel());
					
					RecordHistoryBLL recordHistoryBLL = new RecordHistoryBLL();
					RecordHistory recordHistory = new RecordHistory(menu.getUsername(), hud.getScore(), hud.getLevel());	
					recordHistoryBLL.insertRecordHistory(recordHistory);
					if(menu.historyList.size()>=10)
						menu.historyList.remove(9);
					
					menu.historyList.add(0, recordHistory);
					menu.leaderboardList = leaderboardBLL.selectLeaderboard();
					
					handler.clearAll();
					gameState = State.End;
					menu.setFinalLevel(hud.getLevel());
					menu.setFinalScore(hud.getScore());
						
					
				}
				else
				if(FinalBoss.bossHealth <=0)
				{
					LeaderboardBLL leaderboardBLL = new LeaderboardBLL();
					Leaderboard leaderboard = new Leaderboard(menu.getUsername(), hud.getScore(), hud.getLevel());
					
					leaderboardBLL.insertLeaderboard(leaderboard);
					leaderboardBLL.updateLeaderboard(leaderboard, hud.getScore(), hud.getLevel());
					
					RecordHistoryBLL recordHistoryBLL = new RecordHistoryBLL();
					RecordHistory recordHistory = new RecordHistory(menu.getUsername(), hud.getScore(), hud.getLevel());	
					recordHistoryBLL.insertRecordHistory(recordHistory);
					
					menu.historyList.add(0, recordHistory);
					if(menu.historyList.size()>=10)
						menu.historyList.remove(9);
					
					menu.leaderboardList = leaderboardBLL.selectLeaderboard();
					
					menu.setYCredits(Game.HEIGHT);
					menu.setFinalScore(hud.getScore());
					gameState = State.Credits;
					AudioPlayer.getMusic("creditsMusic").play();
					handler.clearAll();	
					
				}
			}
			
		}
		else if(gameState == State.Menu )
		{
			handler.tick();
			menu.tick();
	    }
		else if(gameState == State.End)
		{
			menu.tick();
			AudioPlayer.getMusic("inGameMusic").stop();	
			
			refreshGame();
			
		}
		else if(gameState == State.Credits)
		{
			
			menu.tickCredits();
			refreshGame();
		}
	}
	
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused)
		{
			g.setFont(new Font("Arial", Font.BOLD, 70)); 
			g.setColor(Color.WHITE);
			if(gameState != State.Shop)
			g.drawString("Paused", 375, 250);
		}
		
		
		if(gameState == State.Game)
		{
			hud.render(g);
			handler.render(g);
		} 
		else if(gameState == State.Shop)
		{
			hud.render(g);
			shop.render(g);
		}
		else if(gameState == State.Menu ||
				gameState == State.End )
 	    {
			menu.render(g);
			handler.render(g);
 	    }
		else if(gameState == State.UserName || 
				gameState == State.Credits ||
				gameState == State.UserControls ||
				gameState == State.RecordHistory ||
				gameState == State.Leaderboard)
		{
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max)
	{
		if(var >= max)
			return var = max;
		else if(var <= min)
			return  var = min;
		else
			return var;
	}
	
	
	public State getGameState() {
		return gameState;
	}

	public void setGameState(State gameState) {
		this.gameState = gameState;
	}

	public static void main(String[] args)
	{
		new Game();
	}

	@Override
	public void update() 
	{
		paused = true;
		BossNotification bossNotification = new BossNotification();
		bossNotification.setVisible(true);
		keyInput.setUp(false);
		keyInput.setDown(false);
		keyInput.setLeft(false);
		keyInput.setRight(false);
	}
}
