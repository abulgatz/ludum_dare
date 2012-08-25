package us.bulgatz_montgomery.evolution;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.GL11;

public class Assets {
	public static Texture TEX_PLAYER;
	public static Texture TEX_BG_PAST;
	public static Texture TEX_PLATFORM_LEFT_PAST;
	public static Texture TEX_PLATFORM_RIGHT_PAST;
	public static Texture TEX_PLATFORM_REPEAT_PAST;
	public static Texture TEX_CLOCK;
	public static Texture TEX_FONTSHEET;
	
	public static boolean init() {
		TEX_PLAYER = null;
		try {
			TEX_BG_PAST = new Texture(ImageIO.read(new File("res/past/background.jpg")));
			TEX_PLATFORM_REPEAT_PAST = new Texture(ImageIO.read(new File("res/past/platform-repeat.png")));
			TEX_PLATFORM_LEFT_PAST = new Texture(ImageIO.read(new File("res/past/left-platform-cap.png")));
			TEX_PLATFORM_RIGHT_PAST = new Texture(ImageIO.read(new File("res/past/right-platform-cap.png")));
			TEX_CLOCK = new Texture(ImageIO.read(new File("res/clock.png")));
			TEX_FONTSHEET = new Texture(ImageIO.read(new File("res/font.png")));
			
			
			TEX_FONTSHEET.bind();
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			return true;
		}
		catch (IOException e) {
			System.err.println("Failed to load asset: ");
			e.printStackTrace();
			return false;
		}
	}
}
