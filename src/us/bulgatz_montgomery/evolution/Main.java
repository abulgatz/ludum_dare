package us.bulgatz_montgomery.evolution;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.*;

public class Main {
	private static boolean running = true;
	private static Texture testTex;

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
		testTex.bind();
		int w = testTex.getWidth();
		int h = testTex.getHeight();
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2d(10, 10);
			glTexCoord2f(1, 0);
			glVertex2d(10+w, 10);
			glTexCoord2f(1, 1);
			glVertex2d(10+w, 10+h);
			glTexCoord2f(0, 1);
			glVertex2d(10, 10+h);
		glEnd();
		
	}
}
