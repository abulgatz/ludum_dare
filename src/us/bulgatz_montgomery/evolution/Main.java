package us.bulgatz_montgomery.evolution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.level.Level;
import static org.lwjgl.opengl.GL11.*;

public class Main {
	private static boolean running = true;
	private static Texture testTex;
	private static Level level;
	private static Player player;

	public static void main(String[] args) {
		Screen.init(800, 600, "LD24");
		
		try {
			BufferedImage texImg = ImageIO.read(new File("test.png"));
			testTex = new Texture(texImg);
		}
		catch(IOException e) {
			System.err.println("Failed to load image: " + e.getMessage());
			return;
		}
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
	}
}
