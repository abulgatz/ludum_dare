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

	public static void main(String[] args) {
		Screen.init(400, 400, "LD24");
		
		try {
			BufferedImage texImg = ImageIO.read(new File("test.png"));
			testTex = new Texture(texImg);
		}
		catch(IOException e) {
			System.err.println("Failed to load image: " + e.getMessage());
			return;
		}
		level = new Level();
		
		while(running) {
			tick();
			Display.sync(60);
		}
	}
	
	public static void tick() {
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT);
		render();
		
		if(Display.isCloseRequested()) {
			Display.destroy();
			running  = false;
		}
	}

	private static void render() {
		level.render(0, 0);
	}
}
