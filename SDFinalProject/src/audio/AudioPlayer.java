package audio;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer 
{
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music> ();
	
	
	public static void loadSounds()
	{
		try 
		{
			soundMap.put("clickSound", new Sound("Sounds/MouseClick.ogg"));
			soundMap.put("bossLaserSound", new Sound("Sounds/BossLaser.ogg"));
			soundMap.put("playerLaserSound", new Sound("Sounds/PlayerLaser.ogg"));
			soundMap.put("explosionSound", new Sound("Sounds/Explosion.ogg"));
			
			musicMap.put("menuMusic", new Music("Sounds/Menu.ogg"));
			musicMap.put("inGameMusic", new Music("Sounds/InGame.ogg"));
			musicMap.put("finalBossMusic", new Music("Sounds/FinalBoss.ogg"));
			musicMap.put("creditsMusic", new Music("Sounds/Credits.ogg"));
			
						
		} 
		catch (SlickException e) 
		{

			e.printStackTrace();
		}
	}
	
	public static Sound getSound(String key)
	{
		return soundMap.get(key);
	}
	
	public static Music getMusic(String key)
	{
		return musicMap.get(key);
	}
	
	
	
	
	
	
	
	
	
	
	
}
