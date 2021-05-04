package controllers;

import java.util.ArrayList;
import java.util.Random;

import audio.AudioPlayer;
import gameObjects.EnemyShip;
import gameObjects.FinalBoss;
import gameObjects.GameObjectFactory;
import gameObjects.ID;
import gameObjects.Tracker;
import graphicComponents.HUD;
import observerPattern.Observer;
import observerPattern.Subject;
import start.Game;
import strategyPattern.BasicStrategy;
import strategyPattern.DiverStrategy;

import strategyPattern.StrategyContext;


public class Spawner implements Subject
{
	private Handler handler;
	private HUD hud;
	private GameObjectFactory gameObjectFactory;
	private StrategyContext strategyContext;
	
	private ArrayList<Observer> listOfObservers = new ArrayList<Observer>();
	
	Random r = new Random();
	
	private int scoreKeep = 0;
	private int coinsKeep = 0;
	
	private int asteroid1Timer = 0;
	private int asteroid2Timer = 0;
	private int asteroid3Timer = 0;
	private int trackerTimer = 2000;
	private int enemyShipTimer = 400;
	private int counter = 0;
	private boolean oneTime = true;
	
	public Spawner(Handler handler, GameObjectFactory gameObjectFactory, HUD hud)
	{
		this.handler = handler;
		this.gameObjectFactory = gameObjectFactory;
		this.hud = hud;
	}
	
	public void tick()
	{
		scoreKeep++;
		
		if(scoreKeep >= 2000)
		{
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() +1);			
		}
		
		if(hud.getLevel() == 2)
		{
			asteroid1Timer++;
			if(asteroid1Timer >=30)
			{
				handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
				asteroid1Timer = 0;
			}
		}
		else if(hud.getLevel() == 3)
		{
			asteroid1Timer++;
			asteroid3Timer++;
			
			if(asteroid1Timer >=50)
			{
				handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
				asteroid1Timer = 0;
			}
			if(asteroid3Timer >=80)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(Game.WIDTH/2,Game.WIDTH-100), -80, ID.Asteroid3, handler));
				handler.addObject(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT/2), ID.Asteroid3, handler));
				asteroid3Timer = 0;
			}			
		}
		else if(hud.getLevel() == 4)
		{
			asteroid1Timer++;
			asteroid2Timer++;
			asteroid3Timer++;
			if(asteroid1Timer >=50)
			{
				handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
				asteroid1Timer = 0;
			}
			if(asteroid3Timer >=80)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(Game.WIDTH/2,Game.WIDTH-100), -80, ID.Asteroid3, handler));
				handler.addObject(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT/2), ID.Asteroid3, handler));
				asteroid3Timer = 0;
			}
			if(asteroid2Timer >=80)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				asteroid2Timer = 0;
			}
		}
		else if(hud.getLevel() == 5)
		{
			asteroid1Timer++;
			asteroid2Timer++;
			asteroid3Timer++;
			enemyShipTimer++;
			
			if(asteroid1Timer >=50)
			{
				handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
				asteroid1Timer = 0;
			}
			if(asteroid3Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(Game.WIDTH/2,Game.WIDTH-100), -80, ID.Asteroid3, handler));
				handler.addObject(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT/2), ID.Asteroid3, handler));
				asteroid3Timer = 0;
			}
			if(asteroid2Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				asteroid2Timer = 0;
			}
			if(enemyShipTimer >=500)
			{				
				if(counter%2 == 0)
				{
					EnemyShip enemyShip = (EnemyShip) gameObjectFactory.makeGameObject(Game.WIDTH+20, randomRange(100, Game.HEIGHT-300), ID.EnemyShip2, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
					
				}
					
				else
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(-105, randomRange(100, Game.HEIGHT-300), ID.EnemyShip1, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
				}
					
					
					
				enemyShipTimer = 0;
				counter++;
			
			}
		}
		else if(hud.getLevel() == 6)
		{
			asteroid1Timer++;
			asteroid2Timer++;
			asteroid3Timer++;
			enemyShipTimer++;
			trackerTimer++;
			if(asteroid1Timer >=40)
			{
				handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
				asteroid1Timer = 0;
			}
			if(asteroid3Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(Game.WIDTH/2,Game.WIDTH-100), -80, ID.Asteroid3, handler));
				handler.addObject(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT/2), ID.Asteroid3, handler));
				asteroid3Timer = 0;
			}
			if(asteroid2Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				asteroid2Timer = 0;
			}
			if(enemyShipTimer >500)
			{				
				if(counter%2 == 0)
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(Game.WIDTH+20, randomRange(100, Game.HEIGHT-300), ID.EnemyShip2, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
				}
				else
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(-105, randomRange(100, Game.HEIGHT-300), ID.EnemyShip1, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
				}
					
						
				enemyShipTimer = 0;	
			}	
			
		}
		else if(hud.getLevel() == 7)
		{
			asteroid1Timer++;
			asteroid2Timer++;
			asteroid3Timer++;
			enemyShipTimer++;
			trackerTimer++;
			if(asteroid1Timer >=100)
			{
				handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
				asteroid1Timer = 0;
			}
			if(asteroid3Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(Game.WIDTH/2,Game.WIDTH-100), -80, ID.Asteroid3, handler));
				handler.addObject(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT/2), ID.Asteroid3, handler));
				asteroid3Timer = 0;
			}
			if(asteroid2Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				asteroid2Timer = 0;
			}
			if(enemyShipTimer >500)
			{				
				if(counter%2 == 0)
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(Game.WIDTH+20, randomRange(100, Game.HEIGHT-300), ID.EnemyShip2, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
				}
					
				else
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(-105, randomRange(100, Game.HEIGHT-300), ID.EnemyShip1, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
				}
					
						
				enemyShipTimer = 0;	
			}	
			if(trackerTimer >=500)
			{				
				if(counter%2 == 0)
				{
					Tracker tracker = (Tracker)(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT-200), ID.Tracker, handler));
					strategyContext = new StrategyContext(new BasicStrategy(handler));
					tracker.setStrategyContext(strategyContext);
					handler.addObject(tracker);
				}
					
				else
				{
					Tracker tracker = (Tracker)(gameObjectFactory.makeGameObject(-105, randomRange(0, Game.HEIGHT-200), ID.Tracker, handler));
					strategyContext = new StrategyContext(new BasicStrategy(handler));
					tracker.setStrategyContext(strategyContext);
					handler.addObject(tracker);
				}
					
						
				trackerTimer = 0;
				counter++;
			}	
		}
		else if(hud.getLevel() == 8)
		{
			asteroid1Timer++;
			asteroid2Timer++;
			asteroid3Timer++;
			enemyShipTimer++;
			trackerTimer++;
			if(asteroid1Timer >=100)
			{
				handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
				asteroid1Timer = 0;
			}
			if(asteroid3Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(Game.WIDTH/2,Game.WIDTH-100), -80, ID.Asteroid3, handler));
				handler.addObject(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT/2), ID.Asteroid3, handler));
				asteroid3Timer = 0;
			}
			if(asteroid2Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				asteroid2Timer = 0;
			}
			if(enemyShipTimer >700)
			{				
				if(counter%2 == 0)
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(Game.WIDTH+20, randomRange(100, Game.HEIGHT-300), ID.EnemyShip2, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
				}
					
				else
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(-105, randomRange(100, Game.HEIGHT-300), ID.EnemyShip1, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
				}
					
						
				enemyShipTimer = 0;	
			}	
			if(trackerTimer >=700)
			{				
				if(counter%2 == 0)
				{
					Tracker tracker = (Tracker)(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT-200), ID.Tracker, handler));
					strategyContext = new StrategyContext(new BasicStrategy(handler));
					tracker.setStrategyContext(strategyContext);
					handler.addObject(tracker);
				}
					
				else
				{
					Tracker tracker = (Tracker)(gameObjectFactory.makeGameObject(-105, randomRange(0, Game.HEIGHT-200), ID.Tracker, handler));
					strategyContext = new StrategyContext(new BasicStrategy(handler));
					tracker.setStrategyContext(strategyContext);

					handler.addObject(tracker);
				}
					
						
				trackerTimer = 0;
				counter++;
			}
		}
		else if(hud.getLevel() == 9)
		{
			asteroid1Timer++;
			asteroid2Timer++;
			asteroid3Timer++;
			enemyShipTimer++;
			trackerTimer++;
			if(asteroid1Timer >=100)
			{
				handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
				asteroid1Timer = 0;
			}
			if(asteroid3Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(Game.WIDTH/2,Game.WIDTH-100), -80, ID.Asteroid3, handler));
				handler.addObject(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT/2), ID.Asteroid3, handler));
				asteroid3Timer = 0;
			}
			if(asteroid2Timer >=200)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				asteroid2Timer = 0;
			}
			if(enemyShipTimer >700)
			{				
				if(counter%2 == 0)
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(Game.WIDTH+20, randomRange(100, Game.HEIGHT-300), ID.EnemyShip2, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);
				}
					
				else
				{
					EnemyShip enemyShip = (EnemyShip)gameObjectFactory.makeGameObject(-105, randomRange(100, Game.HEIGHT-300), ID.EnemyShip1, handler);
					enemyShip.setGameObjectFactory(gameObjectFactory);
					handler.addObject(enemyShip);	
				}
					
				enemyShipTimer = 0;	
			}	
			if(trackerTimer >=700)
			{				
				if(counter%2 == 0)
				{
					Tracker tracker = (Tracker)(gameObjectFactory.makeGameObject(Game.WIDTH, randomRange(0, Game.HEIGHT-200), ID.Tracker, handler));
					strategyContext = new StrategyContext(new DiverStrategy(handler));
					tracker.setStrategyContext(strategyContext);
					handler.addObject(tracker);
				}
					
				else
				{
					Tracker tracker = (Tracker)(gameObjectFactory.makeGameObject(-105, randomRange(0, Game.HEIGHT-200), ID.Tracker, handler));
					strategyContext = new StrategyContext(new DiverStrategy(handler));
					tracker.setStrategyContext(strategyContext);
					handler.addObject(tracker);
				}
					
						
				trackerTimer = 0;
				counter++;
			}
		}
		else if(hud.getLevel() == 10)
		{
			if(oneTime)
			{
				notifyObservers();
				AudioPlayer.getMusic("finalBossMusic").loop();
				FinalBoss finalBoss = (FinalBoss)(gameObjectFactory.makeGameObject(Game.WIDTH/2-130,-300,ID.FinalBoss, handler));
				finalBoss.setGameObjectFactory(gameObjectFactory);
				handler.addObject(finalBoss);
				oneTime = false;
			}
			
			
			asteroid2Timer++;
			if(asteroid2Timer >=1000)
			{
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(randomRange(0,Game.WIDTH/2), -80, ID.Asteroid2, handler));
				handler.addObject(gameObjectFactory.makeGameObject(0, randomRange(0, Game.HEIGHT/2), ID.Asteroid2, handler));
				asteroid2Timer = 0;
			}
		}		
		coinsKeep ++;
		if(coinsKeep >= 2000)
		{
			coinsKeep = 0;
			hud.setCoins(hud.getCoins() + 3000);
//			hud.setCoins(hud.getCoins() + 10000);
		}
	}
	
	public int randomRange(int start, int finish)
	{
	  Random rnd = new Random();
	  int randomNumber = start + rnd.nextInt(finish + 1 - start);
	  return randomNumber;

	}
	
	
	public void setScoreKeep(int scoreKeep)
	{
		this.scoreKeep = scoreKeep;
	}
	
	public int getScoreKeep()
	{
		return this.scoreKeep;
	}
	
	public void setCoinsKeep(int coinsKeep)
	{
		this.coinsKeep = coinsKeep;
	}
	
	public int getCoinsKeep()
	{
		return this.coinsKeep;
	}
	
	public void setOneTime(boolean oneTime)
	{
		this.oneTime = oneTime;
	}

	@Override
	public void registerObserver(Observer o) 
	{
		listOfObservers.add(o);
		
	}

	@Override
	public void notifyObservers()
	{
		for(Observer o : listOfObservers)
		{
			o.update();
		}	
	}

	public int getTrackerTimer() {
		return trackerTimer;
	}

	public void setTrackerTimer(int trackerTimer) {
		this.trackerTimer = trackerTimer;
	}
	
	
}
