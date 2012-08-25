package us.bulgatz_montgomery.evolution.level;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;

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
			if(plat.getX() + plat.getWidth() >= startX && plat.getX() < Display.getWidth() + startX) {
				renderPlatform(plat, startX, startY);
			}
		}
	}
	
	private void renderPlatform(Platform plat, int startX, int startY) {
		plat.getTex().bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2d(plat.getX() - startX, Display.getHeight() - (plat.getY() - startY));
			glTexCoord2d(1, 0);
			glVertex2d(plat.getX() - startX + plat.getWidth(), Display.getHeight() - (plat.getY() - startY));
			glTexCoord2d(1, 1);
			glVertex2d(plat.getX() - startX + plat.getWidth(), Display.getHeight() - (plat.getY() - startY + plat.getHeight()));
			glTexCoord2d(0, 1);
			glVertex2d(plat.getX() - startX, Display.getHeight() - (plat.getY() - startY + plat.getHeight()));
		glEnd();
	}
}
