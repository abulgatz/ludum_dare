package us.bulgatz_montgomery.evolution;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

public class Font {
	public void drawString(String str, int x, int y, int color) {
		
	}
	
	private static void drawGlyph(char c, int x, int y) {
		Assets.TEX_FONTSHEET.bind();
		double texStart = (double) c * 8 / Assets.TEX_FONTSHEET.getWidth();
		double texWidth = (double) 8 / Assets.TEX_FONTSHEET.getWidth();
		glBegin(GL_QUADS);
			glTexCoord2d(texStart, 0);
			glVertex2d(x, Display.getHeight() - y);
			glTexCoord2d(texStart + texWidth, 0);
	}
}
