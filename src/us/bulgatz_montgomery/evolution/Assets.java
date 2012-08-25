package us.bulgatz_montgomery.evolution;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
			TEX_BG_PAST = new Texture(ImageIO.read(new File("background.jpg")));
			TEX_PLATFORM_REPEAT_PAST = new Texture(ImageIO.read(new File("platform-repeat.png")));
			TEX_PLATFORM_LEFT_PAST = new Texture(ImageIO.read(new File("left-platform-cap.png")));
			TEX_PLATFORM_RIGHT_PAST = new Texture(ImageIO.read(new File("right-platform-cap.png")));
			TEX_CLOCK = new Texture(ImageIO.read(new File("clock.png")));
			return true;
		}
		catch (IOException e) {
			System.err.println("Failed to load asset: ");
			e.printStackTrace();
			return false;
		}
	}
}
