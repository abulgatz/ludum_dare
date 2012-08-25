package us.bulgatz_montgomery.evolution;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.level.Level;

public class Player {
	final int speed = 3;
	
	protected AABB aabb;
	protected Texture tex;
	protected Input input;
	protected Level level;
	
	public Player(Level level) {
		aabb = new AABB(0, 200, 20, 20);
		input = new Input();
		this.level = level;
	}
	
	public void render() {
		int posX = Display.getWidth() / 2;
		int posY = (int) aabb.y;
		
		glBegin(GL_QUADS);
			glColor3f(0.0f, 1.0f, 1.0f);
			
			glVertex2d(posX - aabb.width / 2, posY);
			glVertex2d(posX + aabb.width / 2, posY);
			glVertex2d(posX + aabb.width / 2, posY + aabb.height);
			glVertex2d(posX - aabb.width / 2, posY + aabb.height);
			
			glColor3f(1.0f, 1.0f, 1.0f);
		glEnd();

	}
	
	public AABB getAABB() {
		return aabb;
	}

	public void think() {
		input.poll();
		// Move 1 pixel at a time for precise collision
		for(int i = 0; i < speed; i++) {
			// Construct one here and reuse it instead of constructing one for each if-case
			AABB newloc = new AABB(aabb);
			if(input.keyDown(Keyboard.KEY_A)) {
				newloc.x = aabb.x - 1;
				if(!level.collidesPlatform(newloc))
					aabb.x -= 1;
			}
			if(input.keyDown(Keyboard.KEY_D)) {
				newloc.x = aabb.x + 1;
				if(!level.collidesPlatform(newloc))
					aabb.x += 1;
			}
			newloc.x = aabb.x; // Just in case horizontal mvmt failed
			if(input.keyDown(Keyboard.KEY_W)) {
				newloc.y = aabb.y + 1;
				if(!level.collidesPlatform(newloc))
					aabb.y += 1;
			}
			if(input.keyDown(Keyboard.KEY_S)) {
				newloc.y = aabb.y - 1;
				if(!level.collidesPlatform(newloc))
					aabb.y -= 1;
			}
		}
	}

}
