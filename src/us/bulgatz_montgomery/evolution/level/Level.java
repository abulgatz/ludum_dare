package us.bulgatz_montgomery.evolution.level;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.AABB;
import us.bulgatz_montgomery.evolution.Assets;
import us.bulgatz_montgomery.evolution.Clock;
import us.bulgatz_montgomery.evolution.Player;
import us.bulgatz_montgomery.evolution.Texture;

public class Level {
	final int TIMEPERIOD_WIDTH = 640*10;
	
	final int REPEAT_WIDTH = 144;
	
	protected int width;
	protected Texture background;
	protected List<Platform>[] platformlist;
	protected List<Platform> platforms;
	protected List<Clock> entities;

	public class TimePeriod {
		public static final int NUM_PERIODS = 1;
		public static final int TEST_PERIOD = 0;
	}
	
	public Level() {
//		platforms = new List[TimePeriod.NUM_PERIODS];
//		for(int i = 0; i < TimePeriod.NUM_PERIODS; i++) {
//			platforms[i] = LevelDefs.getPlatforms(i);
//		}
		genPast();
		background = Assets.TEX_BG_PAST;
	}
	
	public void render(int startX, int startY) {
		background.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 1);
			glVertex2d(0, 0);
			glTexCoord2d(1, 1);
			glVertex2d(Display.getWidth() - 1, 0);
			glTexCoord2d(1, 0);
			glVertex2d(Display.getWidth() - 1, Display.getHeight() - 1);
			glTexCoord2d(0, 0);
			glVertex2d(0, Display.getHeight() - 1);
		glEnd();
		glDisable(GL_TEXTURE_2D);
		for(Platform plat : platforms) {
			AABB aabb = plat.getAABB();
			if(aabb.x + aabb.width >= startX && aabb.x < Display.getWidth() + startX) {
				plat.render(startX, startY);
			}
		}
		for(Clock ent : entities) {
			AABB aabb = ent.getAABB();
			if(aabb.x + aabb.width >= startX && aabb.x < Display.getWidth() + startX) {
				ent.render(startX, startY);
			}
		}
	}

	public boolean collidesPlatform(AABB aabb) {
		for(Platform plat : platforms) {
			if(aabb.collides(plat.getAABB())) {
//				System.out.println((aabb.x + aabb.width) + ": " + plat.getAABB().x);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean collectClock(Player ply) {
		for(Clock ent : entities) {
			if(ply.getAABB().collides(ent.getAABB())) {
				entities.remove(ent);
				return true;
			}
		}
		return false;
	}
	
	private int platformWidth(int multiple) {
		return REPEAT_WIDTH * multiple;
	}
	
	private void genPast() {
		platforms = new ArrayList<Platform>();
		platforms.add(new Platform(0, 10, platformWidth(4), 80, Assets.TEX_PLATFORM_REPEAT_PAST, Assets.TEX_PLATFORM_LEFT_PAST, Assets.TEX_PLATFORM_RIGHT_PAST));
		platforms.add(new Platform(platformWidth(5), 150, platformWidth(1), 80, Assets.TEX_PLATFORM_REPEAT_PAST, Assets.TEX_PLATFORM_LEFT_PAST, Assets.TEX_PLATFORM_RIGHT_PAST));
		
		entities = new ArrayList<Clock>();
		entities.add(new Clock(50, 140, 36, 42, Assets.TEX_CLOCK));
		entities.add(new Clock(100, 140, 36, 42, Assets.TEX_CLOCK));
		entities.add(new Clock(150, 140, 36, 42, Assets.TEX_CLOCK));
		entities.add(new Clock(250, 140, 36, 42, Assets.TEX_CLOCK));
	}
	
}
