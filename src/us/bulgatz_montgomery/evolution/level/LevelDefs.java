package us.bulgatz_montgomery.evolution.level;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import us.bulgatz_montgomery.evolution.Texture;
import us.bulgatz_montgomery.evolution.level.Level.TimePeriod;

public class LevelDefs {
	public static List<Platform> getPlatforms(int period) {
		List<Platform> list = new ArrayList<Platform>();
		switch(period) {
			case TimePeriod.TEST_PERIOD:
				Texture tex;
				try {
					tex = new Texture(ImageIO.read(new File("test.png")));
				}
				catch (IOException e) {
					System.err.println("Texture load failed!" + e.getMessage());
					return list;
				}
				list.add(new Platform(20, 20, 200, 30, tex));
				list.add(new Platform(50, 5, 300, 10, tex));
				list.add(new Platform(600, 50, 500, 50, tex));
				break;
			default:
				break;
		}
		return list;
	}
}
