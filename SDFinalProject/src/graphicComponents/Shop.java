package graphicComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controllers.Handler;
import gameObjects.PlayerBullet;
import keyboardInput.KeyInput;
import start.Game;

public class Shop extends MouseAdapter
{
	Handler handler;
	HUD hud;
	Game game;
	KeyInput keyInput;
	
	private int healthCost = 1000;
	private int fireRateCost = 1000;
	private int maxHealthCost = 1000;
	private int bombCost = 1000;
	private int damageCost = 1000;
	private int gunsCost = 5000;
	
	private int fireRateLimit = 5;
	private int maxHealthLimit = 5;
	private int bombLimit = 5;
	private int gunsLimit = 2;
	private int damageLimit = 5;
	
	
	public Shop(Game game,Handler handler, HUD hud, KeyInput keyInput)
	{
		this.game = game;
		this.handler = handler;
		this.hud = hud;	
		this.keyInput = keyInput;
	}
	
	
	public void tick()
	{
			
	}
	
	
	public void renderRefillHealth(Graphics g)
	{
		g.setColor(Color.DARK_GRAY.brighter());
		g.fillRect(Game.WIDTH/2 -435, 200, 380, 60);
		g.fillRect(Game.WIDTH/2 -340 , 275, 170, 40);
		
		if(hud.getCoins() < healthCost)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GREEN);
		g.draw3DRect(Game.WIDTH/2 -435, 200, 380, 120, false);
		g.draw3DRect(Game.WIDTH/2 -355 , 275, 200, 40, false);
	
		
		g.setFont(new Font("arial", Font.BOLD, 30)); 
		g.drawString("Refill Health",Game.WIDTH/2 -340 , 245);
		g.drawString(healthCost + " Coins",Game.WIDTH/2 -335 , 305);
		
	}
	
	public void renderIncreaseMaxHealth(Graphics g)
	{
		g.setColor(Color.DARK_GRAY.brighter());
		g.fillRect(Game.WIDTH -450, 200, 380, 60);
		g.fillRect(Game.WIDTH -340 , 275, 170, 40);
		
		if(hud.getCoins() < maxHealthCost)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GREEN);
		g.draw3DRect(Game.WIDTH -450, 200, 380, 120, false);
		g.draw3DRect(Game.WIDTH -355 , 275, 200, 40, false);
	
		
		g.setFont(new Font("arial", Font.BOLD, 30)); 
		g.drawString("Increase Max Health",Game.WIDTH -400, 245);
		g.drawString(maxHealthCost + " Coins",Game.WIDTH -335, 305);
	}
	
	public void renderIncreaseMaxHealthLimit(Graphics g)
	{

		g.setColor(Color.RED);
		g.draw3DRect(Game.WIDTH -450, 200, 380, 120, false);
		g.drawString("Update Limit Reached",Game.WIDTH -420, 270);
		
	}
	
	
	
	public void renderFireRate(Graphics g)
	{
		
		if(hud.getLevel()>=5)
		{
			g.setColor(Color.DARK_GRAY.brighter());
			g.fillRect(Game.WIDTH/2 -435, 350, 380, 60);
			g.fillRect(Game.WIDTH/2 -340 , 425, 170, 39);
			
			if(hud.getCoins() < fireRateCost)
				g.setColor(Color.RED);
			else
				g.setColor(Color.GREEN);
			
			g.draw3DRect(Game.WIDTH/2 -435, 350, 380, 120, false);
			g.draw3DRect(Game.WIDTH/2 -355 , 425, 200, 40, false);
		
			
			g.setFont(new Font("arial", Font.BOLD, 30)); 
			g.drawString("Increase Fire Rate",Game.WIDTH/2 -375 , 390);
			g.drawString(fireRateCost + " Coins",Game.WIDTH/2 -335 , 456);
		}
		else
		{
			g.setColor(Color.RED);
			g.draw3DRect(Game.WIDTH/2 -435, 350, 380, 120, false);
			g.drawString("Unlocks at LVL. 5",Game.WIDTH/2 - 370 , 420);
		}
		
		
			
	}
	
	public void renderFireRateLimit(Graphics g)
	{

		g.setColor(Color.RED);
		g.draw3DRect(Game.WIDTH/2 -435, 350, 380, 120, false);
		g.drawString("Update Limit Reached",Game.WIDTH/2 - 405 , 420);
		
	}
	
	private void renderBombRate(Graphics g)
	{
		
		if(hud.getLevel() >=5)
		{
			g.setColor(Color.DARK_GRAY.brighter());
			g.fillRect(Game.WIDTH -450, 350, 380, 60);
			g.fillRect(Game.WIDTH -340 , 425, 170, 40);
			
			if(hud.getCoins() < bombCost)
				g.setColor(Color.RED);
			else
				g.setColor(Color.GREEN);
			g.draw3DRect(Game.WIDTH -450, 350, 380, 120, false);
			g.draw3DRect(Game.WIDTH -355 , 425, 200, 40, false);
		
			
			g.setFont(new Font("arial", Font.BOLD, 30)); 
			g.drawString("Increase Bomb Rate",Game.WIDTH -400, 390);
			g.drawString(bombCost + " Coins",Game.WIDTH -335 , 456);
		}
		else
		{
			g.setColor(Color.RED);
			g.draw3DRect(Game.WIDTH -450, 350, 380, 120, false);
			g.drawString("Unlocks at LVL. 5",Game.WIDTH - 390 , 420);
		}
		
		
	}


	private void renderBombRateLimit(Graphics g)
	{
		g.setColor(Color.RED);
		g.draw3DRect(Game.WIDTH -450, 350, 380, 120, false);
		g.drawString("Update Limit Reached",Game.WIDTH - 420 , 420);
		
	}
	
	private void renderIncreaseDamage(Graphics g)
	{
		if(hud.getLevel() >=8)
		{
		g.setColor(Color.DARK_GRAY.brighter());
		g.fillRect(Game.WIDTH/2 -435, 500, 380, 60);
		g.fillRect(Game.WIDTH/2 -340 , 575, 170, 39);
		
		if(hud.getCoins() < damageCost)
			g.setColor(Color.RED);
		else
			g.setColor(Color.GREEN);
		g.draw3DRect(Game.WIDTH/2 -435, 500, 380, 120, false);
		g.draw3DRect(Game.WIDTH/2 -355 , 575, 200, 40, false);
	
		
		g.setFont(new Font("arial", Font.BOLD, 30)); 
		g.drawString("Increase Damage Output",Game.WIDTH/2 -420 , 540);
		g.drawString(damageCost + " Coins",Game.WIDTH/2 -335 , 605);
	
		}
		
		else
		{
			g.setColor(Color.RED);
			g.draw3DRect(Game.WIDTH/2 -435, 500, 380, 120, false);
			g.drawString("Unlocks at LVL. 8",Game.WIDTH/2 - 380 , 575);
		}
		
	}
	
	private void renderDamageLimit(Graphics g)
	{
		g.setColor(Color.RED);
		g.draw3DRect(Game.WIDTH/2 -435, 500, 380, 120, false);
		g.drawString("Update Limit Reached",Game.WIDTH/2 - 400 , 575);
	}
	
	private void renderGuns(Graphics g)
	{
		if(hud.getLevel() >= 8)
		{
			g.setColor(Color.DARK_GRAY.brighter());
			g.fillRect(Game.WIDTH -450, 500, 380, 60);
			g.fillRect(Game.WIDTH -340 , 575, 170, 40);
			
			if(hud.getCoins() < gunsCost)
				g.setColor(Color.RED);
			else
				g.setColor(Color.GREEN);
			g.draw3DRect(Game.WIDTH -450, 500, 380, 120, false);
			g.draw3DRect(Game.WIDTH -355 , 575, 200, 40, false);
		
			
			g.setFont(new Font("arial", Font.BOLD, 30)); 
			g.drawString("Increase Guns Number",Game.WIDTH -420 , 540);
			g.drawString(gunsCost + " Coins",Game.WIDTH -335 , 605);
		}
		else
		{
			g.setColor(Color.RED);
			g.draw3DRect(Game.WIDTH -450, 500, 380, 120, false);
			g.drawString("Unlocks at LVL. 8",Game.WIDTH - 395 , 575);
		}
	}
	
	private void renderGunsLimit(Graphics g)
	{
		g.setColor(Color.RED);
		g.draw3DRect(Game.WIDTH -450, 500, 380, 120, false);
		g.drawString("Update Limit Reached",Game.WIDTH - 416 , 575);
	
	}

	
	public void render(Graphics g)
	{
		g.setFont(new Font("Arial", Font.BOLD, 70)); 
		g.setColor(Color.WHITE);
		g.drawString("SHOP", 410, 110);
		
	
		renderRefillHealth(g);
		
		if(fireRateLimit == 0)
			renderFireRateLimit(g);
		else
			renderFireRate(g);
		
		if(maxHealthLimit == 0)
			renderIncreaseMaxHealthLimit(g);
		else
			renderIncreaseMaxHealth(g);
		
		if(bombLimit == 0)
			renderBombRateLimit(g);
		else
			renderBombRate(g);
		
		if(damageLimit == 0)
			renderDamageLimit(g);
		else
			renderIncreaseDamage(g);
		
		
		if(gunsLimit == 0)
			renderGunsLimit(g);
		else
			renderGuns(g);
	
	}
	
	


	public boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if(mx > x && mx < x + width)
		{
			if(my > y && my < y + height)
				return true;
			else
				return false;
		}
		else return false;
	}
	
	public void mousePressed(MouseEvent event)
	{
		
		int mx = event.getX();
		int my = event.getY();
		
		if(game.gameState == State.Shop)
		{	
			
			// Refill Health
			if(mouseOver(mx, my, Game.WIDTH/2 -355 , 275, 200, 40) && hud.getCoins() >= healthCost)
			{
				System.out.println("CLICK");
				hud.setCoins(hud.getCoins()-healthCost);
				HUD.HEALTH = 150 + (hud.bounds/2);
				System.out.println("HEALTH=" + HUD.HEALTH);
				healthCost = healthCost + 1000;
			}
			
			// Increase Max Health
			if(mouseOver(mx, my, Game.WIDTH -450, 200, 380, 120) && hud.getCoins() >= maxHealthCost && maxHealthLimit > 0)
			{
				maxHealthLimit --;
				System.out.println("Increase Max Health");
				hud.setCoins(hud.getCoins()- maxHealthCost);
				
				hud.bounds += 40;
				HUD.HEALTH = 150 + hud.bounds/2;
				
				System.out.println("HEALTH= " + HUD.HEALTH);
				
				maxHealthCost += 500;
			}
			
			// Increase Fire Rate
			if(mouseOver(mx, my,Game.WIDTH/2 -355 , 425, 200, 40) && hud.getCoins() >= fireRateCost && fireRateLimit >0)
			{
				
				fireRateLimit --;
				
				System.out.println("Fire Rate Limit " + fireRateLimit);
				System.out.println("CLICK");
				hud.setCoins(hud.getCoins()-fireRateCost);
				
				keyInput.setBulletCondition(keyInput.getBulletCondition()-10);
				
				System.out.println(keyInput.getBulletCondition());
				
				fireRateCost += 500;
			}
			
			
			
			if(mouseOver(mx, my,Game.WIDTH -450, 350, 380, 120) && hud.getCoins() >= bombCost  && bombLimit >0)
			{
				System.out.println("Bomb");
				bombLimit --;
				hud.setCoins(hud.getCoins()-bombCost);
				keyInput.setBombCondition(keyInput.getBombCondition()-50);
				
				bombCost += 500;
			}
			
			if(mouseOver(mx, my, Game.WIDTH/2 -435, 500, 380, 120) && hud.getCoins() >= damageCost && damageLimit >0)
			{	
				hud.setCoins(hud.getCoins()-damageCost);
				damageLimit --;
				PlayerBullet.damageToBoss += 0.1;
				PlayerBullet.damageToEnemyShip += 0.4;
				PlayerBullet.damageToTracker += 0.4;
				
				
				damageCost += 1000;	
			}
			
			if(mouseOver(mx, my, Game.WIDTH -450, 500, 380, 120) && hud.getCoins() >= gunsCost && gunsLimit > 0)
			{
				hud.setCoins(hud.getCoins()-gunsCost);
				
				if(gunsLimit == 2)
				{
					gunsLimit--;
					keyInput.setOneGun(false);
					keyInput.setTwoGuns(true);
					gunsCost +=5000;
				}
				else
				if(gunsLimit == 1)
				{
					gunsLimit--;
					keyInput.setTwoGuns(false);
					keyInput.setThreeGuns(true);
				}
				
			}
			
		}
	}


	public int getGunsCost() {
		return gunsCost;
	}


	public void setGunsCost(int gunsCost) {
		this.gunsCost = gunsCost;
	}


	public int getGunsLimit() {
		return gunsLimit;
	}


	public void setGunsLimit(int gunsLimit) {
		this.gunsLimit = gunsLimit;
	}


	public int getHealthCost() {
		return healthCost;
	}


	public void setHealthCost(int healthCost) {
		this.healthCost = healthCost;
	}
	
	


	public int getMaxHealthCost() {
		return maxHealthCost;
	}


	public void setMaxHealthCost(int maxHealthCost) {
		this.maxHealthCost = maxHealthCost;
	}


	public int getMaxHealthLimit() {
		return maxHealthLimit;
	}


	public void setMaxHealthLimit(int maxHealthLimit) {
		this.maxHealthLimit = maxHealthLimit;
	}


	public int getFireRateCost() {
		return fireRateCost;
	}


	public void setFireRateCost(int fireRateCost) {
		this.fireRateCost = fireRateCost;
	}


	public int getFireRateLimit() {
		return fireRateLimit;
	}


	public void setFireRateLimit(int fireRateLimit) {
		this.fireRateLimit = fireRateLimit;
	}


	public int getBombCost() {
		return bombCost;
	}


	public void setBombCost(int bombCost) {
		this.bombCost = bombCost;
	}


	public int getBombLimit() {
		return bombLimit;
	}


	public void setBombLimit(int bombLimit) {
		this.bombLimit = bombLimit;
	}
	
	public int getDamageCost() {
		return damageCost;
	}


	public void setDamageCost(int damageCost) {
		this.damageCost = damageCost;
	}
	
	public void setDamageLimit(int damageLimit) {
		this.damageLimit = damageLimit;
	}
	
	
	
}
	
