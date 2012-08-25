package us.bulgatz_montgomery.evolution;

import static org.lwjgl.opengl.GL11.*;

public class Clock {
	protected AABB aabb;
	protected Texture tex;
	
	public Clock(int x, int y, int width, int height, Texture tex) {
		aabb = new AABB(x, y, width, height);
		this.tex = tex;
	}
	
	public void render(int offX, int offY) {
		tex.bind();
		glEnable(GL_BLEND);
		glBegin(GL_QUADS);
			glTexCoord2d(0, 1);
			glVertex2d(aabb.x - offX, aabb.y - offY);
			glTexCoord2d(1, 1);
			glVertex2d(aabb.x + aabb.width - offX, aabb.y - offY);
			glTexCoord2d(1, 0);
			glVertex2d(aabb.x + aabb.width - offX, aabb.y + aabb.height - offY);
			glTexCoord2d(0, 0);
			glVertex2d(aabb.x - offX, aabb.y + aabb.height - offY);
		glEnd();
		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}
	
	public AABB getAABB() {
		return aabb;
	}
}
