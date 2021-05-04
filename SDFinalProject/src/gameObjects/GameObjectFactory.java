package gameObjects;

import controllers.Handler;

public class GameObjectFactory
{
	public GameObject makeGameObject(float x, float y, ID id, Handler handler)
	{
		if(id == ID.Asteroid1) 
			return new Asteroid(x, y , id, handler);
		else
		if(id == ID.Asteroid2) 
			return new Asteroid(x, y , id, handler);
		else
		if(id == ID.Asteroid3) 
			return new Asteroid(x, y , id, handler);
		else
		if(id == ID.Bomb) 	 
			return new Bomb(x, y , id, handler);
		else
		if(id == ID.EnemyBullet1) 
			return new EnemyBullet(x, y , id, handler);
		else
		if(id == ID.EnemyBullet2)
			return new EnemyBullet(x, y , id, handler);
		else
		if(id == ID.EnemyShip1)
			return new EnemyShip(x, y , id, handler);
		else
		if(id == ID.EnemyShip2)
			return new EnemyShip(x, y , id, handler);
		else
		if(id == ID.FinalBoss)
			return new FinalBoss(x, y, id, handler);
		else
		if(id == ID.FinalBossBullet) 
			return new FinalBossBullet(x, y , id, handler);
		else
		if(id == ID.PlayerBullet)
			return new PlayerBullet(x, y , id, handler);
		else
		if(id == ID.PlayerShip) 
			return new PlayerShip(x, y , id, handler);
		else
		if(id == ID.Tracker) 
			return new Tracker(x, y , id, handler);
		else
			return null;
	}
}
