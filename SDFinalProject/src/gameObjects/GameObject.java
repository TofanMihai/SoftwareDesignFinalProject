package gameObjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject 
{
	protected float x, y;
	protected ID id;
	protected float speedX, speedY;
	
	public GameObject(float x, float y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics graphics);
	public abstract Rectangle getBounds1();
	public abstract Rectangle getBounds2();

	public float getX() 
	{
		return x;
	}

	public void setX(float x) 
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y) 
	{
		this.y = y;
	}

	public ID getId() 
	{
		return id;
	}

	public void setId(ID id)
	{
		this.id = id;
	}

	public float getSpeedX()
	{
		return speedX;
	}

	public void setSpeedX(float speedX)
	{
		this.speedX = speedX;
	}

	public float getSpeedY()
	{
		return speedY;
	}

	public void setSpeedY(float speedY) 
	{
		this.speedY = speedY;
	}
	
	
	
	
	
	
}
