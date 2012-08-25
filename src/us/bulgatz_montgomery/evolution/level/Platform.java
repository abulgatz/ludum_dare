package us.bulgatz_montgomery.evolution.level;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.Texture;

public class Platform {
	protected int x, y;
	protected int width, height;
	protected Texture tex;
	
	public Platform(int x, int y, int width, int height, Texture tex) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.tex = tex;
	}
	
	public void render(int offX, int offY) {
		tex.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 1);
			glVertex2d(x - offX, y - offY);
			glTexCoord2d(1, 1);
			glVertex2d(x - offX + width, y - offY);
			glTexCoord2d(1, 0);
			glVertex2d(x - offX + width, y - offY + height);
			glTexCoord2d(0, 0);
			glVertex2d(x - offX, y - offY + height);
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}
}
