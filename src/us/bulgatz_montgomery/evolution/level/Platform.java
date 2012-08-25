package us.bulgatz_montgomery.evolution.level;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.AABB;
import us.bulgatz_montgomery.evolution.Texture;

public class Platform {
	final int CAP_BB_WIDTH = 40;
	
	protected AABB aabb;
	protected Texture center;
	protected Texture leftCap;
	protected Texture rightCap;
	
	public Platform(int x, int y, int width, int height, Texture center, Texture leftCap, Texture rightCap) {
		this.aabb = new AABB(x - CAP_BB_WIDTH, y, width + 2 * CAP_BB_WIDTH, height);
		this.center = center;
		this.leftCap = leftCap;
		this.rightCap = rightCap;
	}
	
	public void render(int offX, int offY) {
		double centerWidth = aabb.width - 2 * CAP_BB_WIDTH;
		int x = aabb.x + CAP_BB_WIDTH - leftCap.getWidth() - offX;
		
		// Left cap
		glEnable(GL_BLEND);
		leftCap.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 1);
			glVertex2d(x, aabb.y - offY);
			glTexCoord2d(1, 1);
			glVertex2d(x + leftCap.getWidth(), aabb.y - offY);
			glTexCoord2d(1, 0);
			glVertex2d(x + leftCap.getWidth(), aabb.y - offY + leftCap.getHeight());
			glTexCoord2d(0, 0);
			glVertex2d(x, aabb.y - offY + leftCap.getHeight());
		glEnd();
		
		x += leftCap.getWidth();
		center.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 1);
			glVertex2d(x, aabb.y - offY);
			glTexCoord2d(centerWidth / center.getWidth(), 1);
			glVertex2d(x + centerWidth, aabb.y - offY);
			glTexCoord2d(centerWidth / center.getWidth(), 0);
			glVertex2d(x + centerWidth, aabb.y - offY + center.getHeight());
			glTexCoord2d(0, 0);
			glVertex2d(x, aabb.y - offY + center.getHeight());
		glEnd();
		
		x += centerWidth;
		rightCap.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 1);
			glVertex2d(x, aabb.y - offY);
			glTexCoord2d(1, 1);
			glVertex2d(x + rightCap.getWidth(), aabb.y - offY);
			glTexCoord2d(1, 0);
			glVertex2d(x + rightCap.getWidth(), aabb.y - offY + rightCap.getHeight());
			glTexCoord2d(0, 0);
			glVertex2d(x, aabb.y - offY + rightCap.getHeight());
		glEnd();
		glDisable(GL_BLEND);
		glDisable(GL_TEXTURE_2D);
	}
	
	public AABB getAABB() {
		return aabb;
	}

	public Texture getTex() {
		return center;
	}

	public void setTex(Texture tex) {
		this.center = tex;
	}
}
