package us.bulgatz_montgomery.evolution;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

public class Font {
	final static int FONT_SIZE = 8;
	
	public static void drawString(String str, int x, int y, double scale) {
		char[] arr = str.toCharArray();
		for(int i = 0; i < str.length(); i++) {
			drawGlyph(arr[i], (int) (x + i * FONT_SIZE * scale), y, scale);
		}
	}
	
	private static void drawGlyph(char c, int x, int y, double scale) {
		Assets.TEX_FONTSHEET.bind();
		double texStartX = (double) (c % 16) * FONT_SIZE / Assets.TEX_FONTSHEET.getWidth();
		double texWidth = (double) FONT_SIZE / Assets.TEX_FONTSHEET.getWidth();
		double texStartY = (double) (c / 16) * FONT_SIZE / Assets.TEX_FONTSHEET.getHeight();
		double texHeight = (double) FONT_SIZE / Assets.TEX_FONTSHEET.getWidth();
		glBegin(GL_QUADS);
			glTexCoord2d(texStartX, texStartY);
			glVertex2d(x, Display.getHeight() - y);
			glTexCoord2d(texStartX + texWidth, texStartY);
			glVertex2d(x + FONT_SIZE * scale, Display.getHeight() - y);
			glTexCoord2d(texStartX + texWidth, texStartY + texHeight);
			glVertex2d(x + FONT_SIZE * scale, Display.getHeight() - (y + FONT_SIZE * scale));
			glTexCoord2d(texStartX, texStartY + texHeight);
			glVertex2d(x, Display.getHeight() - (y + FONT_SIZE * scale));
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}
	
	public static int getHeight(double scale) {
		return (int) Math.ceil(FONT_SIZE * scale);
	}
	
	public static int getWidth(String str, double scale) {
		return (int) Math.ceil(FONT_SIZE * scale * str.length());
	}
}
