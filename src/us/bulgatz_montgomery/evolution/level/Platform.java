package us.bulgatz_montgomery.evolution.level;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.AABB;
import us.bulgatz_montgomery.evolution.Texture;

public class Platform {
	protected AABB aabb;
	protected Texture tex;
	
	public Platform(int x, int y, int width, int height, Texture tex) {
		this.aabb = new AABB(x, y, width, height);
		this.tex = tex;
	}
	
	public void render(int offX, int offY) {
		tex.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 1);
			glVertex2d(aabb.x - offX, aabb.y - offY);
			glTexCoord2d(1, 1);
			glVertex2d(aabb.x - offX + aabb.width, aabb.y - offY);
			glTexCoord2d(1, 0);
			glVertex2d(aabb.x - offX + aabb.width, aabb.y - offY + aabb.height);
			glTexCoord2d(0, 0);
			glVertex2d(aabb.x - offX, aabb.y - offY + aabb.height);
		glEnd();
		glDisable(GL_TEXTURE_2D);
	}
	
	public AABB getAABB() {
		return aabb;
	}

	public Texture getTex() {
		return tex;
	}

	public void setTex(Texture tex) {
		this.tex = tex;
	}
}
