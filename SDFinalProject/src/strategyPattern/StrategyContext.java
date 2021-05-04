package strategyPattern;

import gameObjects.Tracker;

public class StrategyContext
{
	private Strategy strategy;
	
	public StrategyContext(Strategy strategy)
	{
		this.strategy = strategy;
	}
	
	public void executeStrategy(Tracker tracker)
	{
		this.strategy.createTrackerStrategy(tracker);
	}
}
