package graphicComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import start.Game;

public class HUD 
{
	public static float HEALTH = 150;
	public static int coins = 0;
	
	public int bounds = 0;
	private int score = 0;
	private int level = 1;
	
	
	public void tick()
	{
		HEALTH = Game.clamp(HEALTH, -1, 150 + (bounds/2));
		score++;
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(15, 15, 300 + bounds, 32);
		g.setColor(Color.getHSBColor( (1f * HEALTH) / 360, 1f, 1f));
		g.fillRect(15, 15, (int)HEALTH *2, 32);
		g.setColor(Color.WHITE);
		g.drawRect(15, 15, (int)HEALTH *2, 32);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("SCORE: " + score, 15, 70);
		g.drawString("LEVEL: " + level, 15, 90);
		g.drawString("COINS: " + coins, 15, 110);
		
	}
	
	public int getBounds() {
		return bounds;
	}

	public void setBounds(int bounds) {
		this.bounds = bounds;
	}

	public void setScore(int score)
	{
		this.score = score;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public void setLevel(int level)
	{
		this.level = level;
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public void setCoins(int coins)
	{
		HUD.coins = coins;
	}
	
	public int getCoins()
	{
		return HUD.coins;
	}
	
}
