package us.bulgatz_montgomery.evolution.level;

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
