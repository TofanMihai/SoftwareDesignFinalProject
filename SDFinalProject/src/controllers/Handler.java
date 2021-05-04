package controllers;

import java.awt.Graphics;
import java.util.LinkedList;

import gameObjects.GameObject;
import gameObjects.ID;

public class Handler
{
	public LinkedList<GameObject> objectsList = new LinkedList<GameObject>();
	
	public void tick()
	{
		for(int i = 0; i < objectsList.size(); i ++)
		{
			GameObject currentObject = objectsList.get(i);
			
			currentObject.tick();
		}
	}
	
	
	public void render(Graphics graphics)
	{
		for(int i = 0; i < objectsList.size(); i ++)
		{
			GameObject currentObject = objectsList.get(i);
			
			currentObject.render(graphics);
		}
	}
	
	public void addObject(GameObject newObject)
	{
		this.objectsList.add(newObject);
	}
	
	public void removeObject(GameObject object)
	{
		this.objectsList.remove(object);
	}
	
	public void clearEnemies()
	{
		for(int i = 0; i < objectsList.size(); i ++)
		{
			GameObject currentObject = objectsList.get(i);

			if(currentObject.getId() != ID.PlayerShip)
			{
				removeObject(currentObject);
				i--;	
			}
		}
	}
	
	public void clearAll()
	{
		objectsList.clear();
	}
	
	
}
