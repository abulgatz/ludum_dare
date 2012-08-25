package us.bulgatz_montgomery.evolution.level;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.AABB;
import us.bulgatz_montgomery.evolution.Texture;

public class Level {
	protected int width;
	protected Texture background;
	protected List<Platform>[] platformlist;
	protected List<Platform> platforms;

	public class TimePeriod {
		public static final int NUM_PERIODS = 1;
		public static final int TEST_PERIOD = 0;
	}
	
	@SuppressWarnings("unchecked")
	public Level() {
//		platforms = new List[TimePeriod.NUM_PERIODS];
//		for(int i = 0; i < TimePeriod.NUM_PERIODS; i++) {
//			platforms[i] = LevelDefs.getPlatforms(i);
//		}
		platforms = LevelDefs.getPlatforms(TimePeriod.TEST_PERIOD);
	}
	
	public void render(int startX, int startY) {
		for(Platform plat : platforms) {
			AABB aabb = plat.getAABB();
			if(aabb.x + aabb.width >= startX && aabb.x < Display.getWidth() + startX) {
				plat.render(startX, startY);
			}
		}
	}

	public boolean collidesPlatform(AABB aabb) {
		for(Platform plat : platforms) {
			if(aabb.collides(plat.getAABB())) {
				return true;
			}
		}
		
		return false;
	}
	
}
