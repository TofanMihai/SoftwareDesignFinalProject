package strategyPattern;

import controllers.Handler;
import gameObjects.ID;
import gameObjects.PlayerShip;
import gameObjects.Tracker;
import start.Game;

public class DiverStrategy implements Strategy
{

	private Handler handler;
	private PlayerShip playerShip;
	
	private boolean dive = false, track = true;
	private int trackerCounter = 500;

	public DiverStrategy(Handler handler)
	{
		
		this.handler = handler;
		
		for(int i = 0; i < handler.objectsList.size(); i++)
		{
			if(this.handler.objectsList.get(i).getId() == ID.PlayerShip)
				playerShip = (PlayerShip) this.handler.objectsList.get(i);
		}	
	}
	
	@Override
	public void createTrackerStrategy(Tracker tracker) 
	{
		tracker.setX((tracker.getX() + tracker.getSpeedX())) ;
		tracker.setY((tracker.getY() + tracker.getSpeedY())) ;
		
		float diffX = (tracker.getX() -60) - playerShip.getX();
		float diffY = (tracker.getY()-60) - playerShip.getY();

		float distance = (float) Math.sqrt( Math.pow(tracker.getX() - playerShip.getX(), 2) + Math.pow(tracker.getY() - playerShip.getY(), 2));
	
		
		if(trackerCounter >= 200)
		{
			trackerCounter--;
			tracker.setSpeedX(((-1.0f/distance) * diffX));
			tracker.setSpeedY(((-1.0f/distance) * diffY));
		}
		if(trackerCounter <200 && track)
		{
			trackerCounter--;
			tracker.setSpeedX(((1.0f/distance) * diffX));
			tracker.setSpeedY(((1.0f/distance) * diffY));	
			if(trackerCounter <=0)
			{
				track = false;
				dive = true;
			}
				
		}
		
		if(dive)
		{
			tracker.setSpeedX(((-1.0f/distance) * diffX *3));
			tracker.setSpeedY(((-1.0f/distance) * diffY *3));
		}
		
		tracker.setSpeedX(Game.clamp(tracker.getSpeedX(), -3f, 3f));
		tracker.setSpeedY(Game.clamp(tracker.getSpeedY(), -3f, 3f));
		
	}

}
