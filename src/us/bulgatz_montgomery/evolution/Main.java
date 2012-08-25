package us.bulgatz_montgomery.evolution;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.level.Level;

public class Main {
	private static boolean running = true;
	private static Level level;
	private static Player player;

	public static void main(String[] args) {
		Screen.init(640, 480, "LD24");
		
		if(!Assets.init())
			return;
		level = new Level();
		player = new Player(level);
		
		int frames = 0;
		long lastTime = System.nanoTime();
		long delay = 1000000000L; // 1 second
		while(running) {
			frames++;
			tick();
			Display.sync(60);
			if(System.nanoTime() > lastTime + delay) {
				System.out.println("FPS: " + frames);
				lastTime = System.nanoTime();
				frames = 0;
			}
		}
	}
	
	public static void tick() {
		Display.update();
		player.think();
		glClear(GL_COLOR_BUFFER_BIT);
		render();
		
		if(Display.isCloseRequested()) {
			Display.destroy();
			running  = false;
		}
	}

	private static void render() {
		int startX = (int) (player.getAABB().x + player.getAABB().width / 2 - Display.getWidth() / 2);
		level.render(startX, 0);
		player.render();
		
		Assets.TEX_CLOCK.bind();
		glEnable(GL_BLEND);
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2d(10, Display.getHeight() - 10);
			glTexCoord2d(1, 0);
			glVertex2d(10 + Assets.TEX_CLOCK.getWidth(), Display.getHeight() - 10);
			glTexCoord2d(1, 1);
			glVertex2d(10 + Assets.TEX_CLOCK.getWidth(), Display.getHeight() - (10 + Assets.TEX_CLOCK.getHeight()));
			glTexCoord2d(0, 1);
			glVertex2d(10, Display.getHeight() - (10 + Assets.TEX_CLOCK.getHeight()));
		glEnd();
		glDisable(GL_BLEND);
		Font.drawString("" + player.getClocks(), 20 + Assets.TEX_CLOCK.getWidth(), 10 + Assets.TEX_CLOCK.getWidth() / 2 - 12, 3.0);
	}
}
