package strategyPattern;

import controllers.Handler;
import gameObjects.GameObject;
import gameObjects.ID;
import gameObjects.Tracker;
import start.Game;

public class BasicStrategy implements Strategy
{
	private Handler handler;
	private GameObject playerShip;

	public BasicStrategy(Handler handler)
	{
		
		this.handler = handler;
		
		for(int i = 0; i < handler.objectsList.size(); i++)
		{
			if(this.handler.objectsList.get(i).getId() == ID.PlayerShip)
				playerShip = this.handler.objectsList.get(i);
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
		
		tracker.setSpeedX(((-1.0f/distance) * diffX));
		tracker.setSpeedY(((-1.0f/distance) * diffY));
		
		tracker.setSpeedX(Game.clamp(tracker.getSpeedX(), -1f, 1f));
		tracker.setSpeedY(Game.clamp(tracker.getSpeedY(), -1f, 1f));
	}
	
}
