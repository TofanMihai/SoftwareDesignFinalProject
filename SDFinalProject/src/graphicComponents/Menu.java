package graphicComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;

import audio.AudioPlayer;
import controllers.Handler;
import database.objects.Leaderboard;
import database.objects.RecordHistory;
import gameObjects.GameObjectFactory;
import gameObjects.ID;
import gameObjects.PlayerShip;
import observerPattern.Observer;
import start.Game;

public class Menu extends MouseAdapter implements Observer
{
	private Game game;
	private Handler handler;
	private GameObjectFactory gameObjectFactory;
	private PlayerShip playerShip;
	
	public String username = null;
	private int finalScore;
	private int finalLevel;
	
	private int spawn = 250;
	private int asteroidTimer = 0;
	private int xCredits = 250, yCredits = Game.HEIGHT;
	
	public List<Leaderboard> leaderboardList;
	public List<RecordHistory> historyList;
	Random r = new Random();
	
	public Menu(Game game,GameObjectFactory gameObjectFactory, Handler handler)
	{
		this.game = game;
		this.gameObjectFactory = gameObjectFactory;
		this.handler = handler;

		AudioPlayer.loadSounds();	
	}
	
	public void mousePressed(MouseEvent event)
	{		
		int mx = event.getX();
		int my = event.getY();
		
		// MENU
		if(game.gameState == State.Menu)
		{	
			
			// MENU -- Play Button
			if(mouseOver(mx, my, Game.WIDTH/2 -225, 200, 440, 70))
			{
				// USERNAME
				if(username == null || username.length() == 0)
				{
					game.gameState = State.UserName;
					AudioPlayer.getSound("clickSound").play();
				}
				else
				{
					// GAMEPLAY
					AudioPlayer.getSound("clickSound").play();
					AudioPlayer.getMusic("inGameMusic").loop();
					
					game.gameState = State.Game;
					playerShip = (PlayerShip) gameObjectFactory.makeGameObject(Game.WIDTH/2-120,Game.HEIGHT-170, ID.PlayerShip, handler);
					handler.addObject(playerShip);
					handler.clearEnemies();
				}	
			}
			
			if(mouseOver(mx, my, Game.WIDTH/2 -225, 300, 440, 70)) // MENU -- User Controls 
			{
				AudioPlayer.getSound("clickSound").play();
				game.gameState = State.UserControls;
			}
			
			if(mouseOver(mx, my, Game.WIDTH/2 -225, 400, 440, 70)) // MENU -- Record History
			{
				AudioPlayer.getSound("clickSound").play();
				game.gameState = State.RecordHistory;
			}
			
			if(mouseOver(mx, my, Game.WIDTH/2 -225, 500, 440, 70)) // MENU -- Leaderboard
			{
				AudioPlayer.getSound("clickSound").play();
				game.gameState = State.Leaderboard;
			}
			
			// MENU -- Exit Button
			if(mouseOver(mx, my,Game.WIDTH/2 -225, 600, 440, 70))
				
				System.exit(1);
			
		} 
		
		// USER CONTROLS || RECORD HISTORY || LEADERBOARD
		else if(game.gameState == State.UserControls)
		{
			// Back Button
			if(mouseOver(mx, my, Game.WIDTH/2 -210, 600, 410, 70))
			{
				AudioPlayer.getSound("clickSound").play();
				game.gameState = State.Menu;
			}
		}
		else if(game.gameState == State.RecordHistory || 
				game.gameState == State.Leaderboard)
		{
			if(mouseOver(mx, my, Game.WIDTH/2 -210, 630, 410, 70))
			{
				AudioPlayer.getSound("clickSound").play();
				game.gameState = State.Menu;
			}
		}
		
		
		// END
		else if(game.gameState == State.End)
		{
			// END -- Try Again Button
			if(mouseOver(mx, my, Game.WIDTH/2 -225, 500, 440, 70))
			{
				AudioPlayer.getSound("clickSound").play();
				AudioPlayer.getMusic("inGameMusic").loop();
				game.gameState = State.Game;
				playerShip = (PlayerShip) gameObjectFactory.makeGameObject(Game.WIDTH/2-120,Game.HEIGHT-170, ID.PlayerShip, handler);
				handler.addObject(playerShip);
				handler.clearEnemies();
			}
			
			// END -- Menu Button
			if(mouseOver(mx, my, Game.WIDTH/2 -225, 600, 440, 70))
			{
				AudioPlayer.getSound("clickSound").play();
				AudioPlayer.getMusic("menuMusic").loop();
				game.gameState = State.Menu;
				
			}
			
		}
		// USERNAME
		else if(game.gameState == State.UserName)
		{
			
			//USERNAME - Choose Name
			if(mouseOver(mx, my, Game.WIDTH/2 -260, 400, 510, 70))
			{
				new UsernameForm(this);
			}
			
			// USERNAME - START
			if(mouseOver(mx, my, Game.WIDTH/2 -260, 500, 510, 70) && username != null && username.length() != 0)
			{
				AudioPlayer.getSound("clickSound").play();
				AudioPlayer.getMusic("inGameMusic").loop();
				
				game.gameState = State.Game;
				playerShip = (PlayerShip) gameObjectFactory.makeGameObject(Game.WIDTH/2-120,Game.HEIGHT-170, ID.PlayerShip, handler);
				handler.addObject(playerShip);
				handler.clearEnemies();
			}
			
			//USERNAME - Back
			if(mouseOver(mx, my, Game.WIDTH/2 -210, 600, 410, 70))
			{
				game.gameState = State.Menu;
				AudioPlayer.getSound("clickSound").play();
				
			}
		}
		else
		if(game.gameState == State.Credits)
		{
			// CREDITS -- SKIP
			if(mouseOver(mx, my,Game.WIDTH - 240, 650, 230, 70))
			{
				game.gameState = State.Menu;
				AudioPlayer.getMusic("menuMusic").loop();		
			}
		}
	}
	
	public void mouseReleased(MouseEvent event)
	{
		
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
	
	public void tick()
	{
		spawn++;	
	}
	
	public void tickAsteroids()
	{
		asteroidTimer++;
		
		if(asteroidTimer >=50)
		{
			handler.addObject(gameObjectFactory.makeGameObject(r.nextInt(Game.WIDTH-100), -80, ID.Asteroid1, handler));
			asteroidTimer = 0;
		}
	}
	
	
	
	public int rndRange(int start, int finish)
	{
	  Random rnd = new Random();
	  int randomNumber = start + rnd.nextInt(finish + 1 - start);
	  return randomNumber;

	}
	
	
	public void render(Graphics g)
	{
		if(game.gameState == State.Menu)
		{
			if(spawn >= 250)
			{
				spawn = 0;
				handler.addObject(gameObjectFactory.makeGameObject(rndRange(Game.WIDTH-300, Game.WIDTH-100), rndRange(-300,-100), ID.Asteroid1,handler));
				handler.addObject(gameObjectFactory.makeGameObject(rndRange(-30, Game.WIDTH-800), rndRange(-300,-100), ID.Asteroid1,handler));
				
				handler.addObject(gameObjectFactory.makeGameObject(rndRange(-30, Game.WIDTH-100), rndRange(-300,-100), ID.Asteroid2,handler));
				handler.addObject(gameObjectFactory.makeGameObject(rndRange(-30, Game.WIDTH-100), rndRange(-300,-100), ID.Asteroid2,handler));
				
				handler.addObject(gameObjectFactory.makeGameObject(rndRange(Game.WIDTH-300, Game.WIDTH-100), rndRange(-300,-100), ID.Asteroid3,handler));
			
			}
			
			
			g.setFont(new Font("Arial", Font.BOLD, 70)); 
			
			g.setColor(Color.ORANGE);
			g.drawString("Rocket Odyssey", Game.WIDTH/2 - 270, 100);
			
			g.setColor(Color.DARK_GRAY.brighter());
			g.draw3DRect(Game.WIDTH/2 -225, 200, 440, 70, false);
			g.draw3DRect(Game.WIDTH/2 -225, 300, 440, 70, false);
			g.draw3DRect(Game.WIDTH/2 -225, 400, 440, 70,false);
			g.draw3DRect(Game.WIDTH/2 -225, 500, 440, 70,false);
			g.draw3DRect(Game.WIDTH/2 -225, 600, 440, 70,false);
			
			g.fillRect(Game.WIDTH/2 -210, 200, 410, 70);
			g.fillRect(Game.WIDTH/2 -210, 300, 410, 70);
			g.fillRect(Game.WIDTH/2 -210, 400, 410, 70);
			g.fillRect(Game.WIDTH/2 -210, 500, 410, 70);
			g.fillRect(Game.WIDTH/2 -210, 600, 410, 70);
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,50));
			g.drawString("Play",Game.WIDTH/2 -55, 255);
			g.drawString("User Controls",Game.WIDTH/2 -165, 355);
			g.drawString(" Record History",Game.WIDTH/2 -195, 455);
			g.drawString(" Leaderboards",Game.WIDTH/2 -180, 555);
			g.drawString("Quit",Game.WIDTH/2 -55, 655);
		}
		else if(game.gameState == State.End)
		{
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,100));
			g.drawString("GAME OVER",Game.WIDTH/2 -315, 255);
			g.setFont(new Font("Arial", Font.BOLD,50));
			g.drawString("  Final Score : " + getFinalScore(),Game.WIDTH/2 -315, 355);
			g.drawString("  Final Level : " + getFinalLevel(),Game.WIDTH/2 -315, 420);
			
			g.setColor(Color.DARK_GRAY.brighter());
			g.draw3DRect(Game.WIDTH/2 -225, 500, 440, 70,false);
			g.draw3DRect(Game.WIDTH/2 -225, 600, 440, 70,false);
			g.fillRect(Game.WIDTH/2 -210, 500, 410, 70);
			g.fillRect(Game.WIDTH/2 -210, 600, 410, 70);
			g.setColor(Color.ORANGE);
			g.drawString("   Try Again",Game.WIDTH/2 -160, 555);
			g.drawString("Menu",Game.WIDTH/2 -65, 655);
		}
		else if(game.gameState == State.UserControls)
		{
			
			g.setFont(new Font("Arial", Font.BOLD, 70)); 
			g.setColor(Color.ORANGE);
			g.drawString(" User Controls", Game.WIDTH/2 - 270, 100);
		
			g.setFont(new Font("Arial", Font.BOLD,40));
	
			g.drawString(" Move Up : ",100, 250);
			g.drawString(" Move Right :",100, 300);
			g.drawString(" Move Down :",100, 350);
			g.drawString(" Move Left :",100, 400);
			g.drawString(" Shoot :",100, 450);
			g.drawString(" Place Bomb : ",100, 500);
			
			g.drawString("W", 380, 250);
			g.drawString("R", 384, 300);
			g.drawString("S", 384, 350);
			g.drawString("A", 384, 400);
			g.drawString("P", 384, 450);
			g.drawString("O", 380, 500);
			
			
			g.drawString(" Pause Game :", 500, 250);
			g.drawString(" Open Shop :", 500, 300);
			g.drawString(" Exit Game :",500, 350);
			
			g.drawString("SPACE", 800, 250);
			g.drawString("ENTER", 800, 300);
			g.drawString("ESCAPE",800, 350);
			
			g.setColor(Color.DARK_GRAY.brighter());
			g.draw3DRect(Game.WIDTH/2 -210, 600, 410, 70,false);
			g.fillRect(Game.WIDTH/2 -195, 600, 380, 70);
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,50));
			g.drawString("Back",Game.WIDTH/2 -65, 654);
		}
		else if(game.gameState == State.UserName)
		{
			
			g.setFont(new Font("Arial", Font.BOLD, 50)); 
			g.setColor(Color.ORANGE);
			g.drawString("Choose Your Username", Game.WIDTH/2 - 280, 100);
			
			
			g.setColor(Color.DARK_GRAY.brighter());
			g.draw3DRect(Game.WIDTH/2 -350, 220, 690, 70,false);
			g.fillRect(Game.WIDTH/2 -350, 220, 690, 70);
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,50));
			if(username == null || username.length() == 0)
				g.drawString("No Username Selected", 250, 270);
			else
				g.drawString(username, Game.WIDTH/2 - 340 , 270);
			
			g.setColor(Color.DARK_GRAY.brighter());
			g.draw3DRect(Game.WIDTH/2 -260, 400, 510, 70,false);
			g.fillRect(Game.WIDTH/2 -245, 400, 480, 70);
			g.draw3DRect(Game.WIDTH/2 -260, 500, 510, 70,false);
			g.fillRect(Game.WIDTH/2 -245, 500, 480, 70);
			g.draw3DRect(Game.WIDTH/2 -260, 600, 510, 70,false);
			g.fillRect(Game.WIDTH/2 -245, 600, 480, 70);
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,50));
			g.drawString("Choose Username", Game.WIDTH/2 - 225, 454);
			g.drawString("Start", Game.WIDTH/2 - 65, 554);
			g.drawString("Back",Game.WIDTH/2 -65, 654);
		}
		else if(game.gameState == State.Credits)
		{
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,70));
			g.drawString("CONGRATULATIONS!", xCredits-110, yCredits);
			g.drawString("YOU ARE A TRUE CHAMPION", xCredits-240, yCredits+70);
			
			g.drawString("This game was developed by", xCredits-230, yCredits+400);
			g.drawString("Tofan Emil - Mihai", xCredits-60, yCredits+470);
			g.drawString("as a final project for the", xCredits-140, yCredits+540);
			g.drawString("Software Design Laboratory", xCredits-210, yCredits+610);
			
			g.setFont(new Font("Arial", Font.BOLD,70));
			g.drawString("Thank you for playing", xCredits-120, yCredits+900);
			g.drawString("ROCKET ODYSSEY", xCredits-80, yCredits+970);
			g.drawString("Champion Score: " + getFinalScore(), xCredits-150, yCredits+1040);
			g.setColor(Color.DARK_GRAY.brighter());
			g.draw3DRect(Game.WIDTH - 240, 650, 230, 70,false);
			g.fillRect(Game.WIDTH - 230, 650, 210, 70);
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,50));
			g.drawString("Skip",Game.WIDTH - 180, 703);
		}	
		else if(game.gameState == State.RecordHistory)
		{
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,70));
			g.drawString("Record History",Game.WIDTH/2 - 265, 70);
			
			if(username == null || username.length() == 0)
			{
				g.setColor(Color.ORANGE);
				g.setFont(new Font("Arial", Font.BOLD,40));
				g.drawString("It seems you have yet to select an username.",90, 230);
				g.drawString("Please do so, and return here to view", 155, 290);
				g.drawString("the history of your most recent 10 games.", 120, 350);
				
			}
			else
			if(historyList.isEmpty())
			{
				g.setColor(Color.ORANGE);
				g.setFont(new Font("Arial", Font.BOLD,40));
				g.drawString("Welcome, new challenger,",255, 230);
				g.drawString("It seems that you have not played any games yet", 50, 290);
				g.drawString("Enjoy the game and return here to bask", 135, 350);
				g.drawString("in your stories of glory.", 290, 410);
				
			}
			else
			{
				String s = "           ";
				int i = 1;
				int y = 180;
				g.setColor(Color.ORANGE);
				g.setFont(new Font("Arial", Font.BOLD,30));
				g.drawString("Game ID" + s + "Username" + s + "Score" + s + "Level", 150, y-30);
				
				for(RecordHistory r : historyList)
				{
					
					g.drawString("___________________________________________", 130, y+26);
					
					if(i == 10)
						g.drawString(i +"",190, y+20);
					else
						g.drawString(i +"",200, y+20);
					if(username.length()>=18)
						g.setFont(new Font("Arial", Font.BOLD,20));
					g.drawString(username, 410-username.length()*6, y+20);
					g.setFont(new Font("Arial", Font.BOLD,30));
					if(r.getScore()>=1000)
						g.drawString(r.getScore()+"", 600, y+20);
					else
					if(r.getScore()>=100)
						g.drawString(r.getScore()+"", 610, y+20);
					else
						g.drawString(r.getScore()+"", 620, y+20);
					if(r.getLevel() < 10)
						g.drawString(r.getLevel()+"", 800, y+20);
					else
						g.drawString(r.getLevel()+"", 790, y+20);
					i++;
					y += 45;
				}
				
			}
				
			g.setColor(Color.DARK_GRAY.brighter());
			g.draw3DRect(Game.WIDTH/2 -210, 630, 410, 70,false);
			g.fillRect(Game.WIDTH/2 -195, 630, 380, 70);
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,50));
			g.drawString("Back",Game.WIDTH/2 -65, 684);
				
		}
		else if(game.gameState == State.Leaderboard)
		{
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,70));
			g.drawString("Leaderboards",Game.WIDTH/2 - 240, 70);
			
			String s = "           ";
			int i = 1;
			int y = 180;
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,30));
			g.drawString("Ranking" + s + "Username" + s + "Score" + s + "Level", 150, y-30);
			
			for(Leaderboard l : leaderboardList)
			{
				
				g.drawString("___________________________________________", 130, y+26);
				
				if(i == 10)
					g.drawString(i +"",190, y+20);
				else
					g.drawString(i +"",200, y+20);
				
//				if(l.getUsername().length() <= 5)
//					g.drawString(l.getUsername(), 390, y+20);
//				else
//				if(l.getUsername().length() <=10)
//					g.drawString(l.getUsername(), 365, y+20);
//				else
//				if(l.getUsername().length() <15)
//					g.drawString(l.getUsername(), 330, y+20);
//				else
//					g.drawString(l.getUsername(), 300, y+20);
				
				if(l.getUsername().length()>=18)
					g.setFont(new Font("Arial", Font.BOLD,20));
				g.drawString(l.getUsername(), 410-l.getUsername().length()*6, y+20);
				g.setFont(new Font("Arial", Font.BOLD,30));

				if(l.getHighestScore() >=1000)
					g.drawString(l.getHighestScore()+"", 600, y+20);
				else
				if(l.getHighestScore() >=100)
					g.drawString(l.getHighestScore()+"", 610, y+20);
				else
					g.drawString(l.getHighestScore()+"", 620, y+20);
				if(l.getHighestLevel() < 10)
					g.drawString(l.getHighestLevel()+"", 800, y+20);
				else
					g.drawString(l.getHighestLevel()+"", 790, y+20);
				i++;
				y += 45;
			}
			g.setColor(Color.DARK_GRAY.brighter());
			g.draw3DRect(Game.WIDTH/2 -210, 630, 410, 70,false);
			g.fillRect(Game.WIDTH/2 -195, 630, 380, 70);
			
			g.setColor(Color.ORANGE);
			g.setFont(new Font("Arial", Font.BOLD,50));
			g.drawString("Back",Game.WIDTH/2 -65, 684);
		}
			
			
}
	

	
	public void tickCredits()
	{
		if(yCredits > -700)
			yCredits --;
		 
		 if((AudioPlayer.getMusic("creditsMusic").getPosition() >= 30))
		 {
			 game.gameState = State.Menu;
			 AudioPlayer.getMusic("menuMusic").loop();		
				
		 }
	}
	
	public void setYCredits(int yCredits) 
	{
		this.yCredits = yCredits;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	public int getFinalLevel() {
		return finalLevel;
	}

	public void setFinalLevel(int finalLevel) {
		this.finalLevel = finalLevel;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public void update() {
		playerShip.setSpeedX(0);
		playerShip.setSpeedY(0);
		
	}
	
	
	

}
