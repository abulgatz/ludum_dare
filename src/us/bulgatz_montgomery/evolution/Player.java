package us.bulgatz_montgomery.evolution;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Player {
	final double speed = 2.0;
	
	protected double x, y;
	protected double width, height;
	protected Texture tex;
	protected Input input;
	
	public Player() {
		width = 20;
		height = 20;
		y = 200.0;
		input = new Input();
	}
	
	public void render() {
		int posX = Display.getWidth() / 2;
		int posY = (int) y;
		
		glBegin(GL_QUADS);
			glColor3f(0.0f, 1.0f, 1.0f);
			
			glVertex2d(posX - width / 2, posY + height / 2);
			glVertex2d(posX + width / 2, posY + height / 2);
			glVertex2d(posX + width / 2, posY - height / 2);
			glVertex2d(posX - width / 2, posY - height / 2);
			
			glColor3f(1.0f, 1.0f, 1.0f);
		glEnd();

	}
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void think() {
		input.poll();
		if(input.keyDown(Keyboard.KEY_A)) {
			x -= speed;
		}
		if(input.keyDown(Keyboard.KEY_D)) {
			x += speed;
		}
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
