package us.bulgatz_montgomery.evolution;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import us.bulgatz_montgomery.evolution.level.Level;

public class Player {
	final int speed = 3;
	final int fallSpeed = 8;
	final int jumpSpeed = 20;
	static final long WALK_FRAME_DELAY = 500000000L; // 0.5s
	final float[][] frameColors = {{0.0f, 1.0f, 1.0f}, {1.0f, 1.0f, 0.0f}, {1.0f, 0.0f, 0.0f}};
	
	protected AABB aabb;
	protected Texture tex;
	protected Input input;
	protected Level level;
	protected int clocks;
	protected long frameChangeTime;
	protected int frame;
	
	int velX, velY;
	
	public Player(Level level) {
		aabb = new AABB(0, 200, 20, 20);
		input = new Input();
		this.level = level;
	}
	
	public void render() {
		int posX = Display.getWidth() / 2;
		int posY = (int) aabb.y;
		float[] colors = frameColors[frame];
		
		glBegin(GL_QUADS);
			glColor3f(colors[0], colors[1], colors[2]);
			
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
	
	public int getClocks() {
		return clocks;
	}
	
	private void walkAnimation() {
		if(!onGround())
			return;
		if(System.nanoTime() >= frameChangeTime + WALK_FRAME_DELAY) {
			frame = (frame == 0) ? 1 : 0;
			frameChangeTime = System.nanoTime();
		}
	}
	
	public boolean onGround() {
		AABB oneBelow = new AABB(aabb.x, aabb.y - 2, aabb.width, aabb.height);
		return level.collidesPlatform(oneBelow);
	}

	public void think() {
		input.poll();
		
		// Check for horizontal movement
		// Construct one here and reuse it instead of constructing one for each if-case
		AABB newloc = new AABB(aabb);
		if(input.keyDown(Keyboard.KEY_A)) {
			if(velX > -speed)
				velX--;
			walkAnimation();
			// Move 1 pixel at a time for precise collision
			for(int i = 0; i < Math.abs(velX); i++) {
				newloc.x = aabb.x - 1;
				if(!level.collidesPlatform(newloc))
					aabb.x -= 1;
				else
					velX = 0;
			}
		}
		else if(input.keyDown(Keyboard.KEY_D)) {
			if(velX < speed)
				velX++;
			walkAnimation();
			// Move 1 pixel at a time for precise collision
			for(int i = 0; i < Math.abs(velX); i++) {
				newloc.x = aabb.x + 1;
				if(!level.collidesPlatform(newloc))
					aabb.x += 1;
				else
					velX = 0;
			}
		}
		else {
			if(velX < 0) velX++;
			else if(velX > 0) velX--;
		}
		
		// Jump
		if(input.keyDown(Keyboard.KEY_W)) {
			if(onGround()) {
				velY = jumpSpeed;
			}
		}
		
		// Take care of gravity
		newloc = new AABB(aabb);
		if(velY > -fallSpeed)
			velY--;
		int fallDir;
		if(velY == 0) fallDir = 0;
		else fallDir = (velY / Math.abs(velY)); // +1=up, -1=down
		// Move 1 pixel at a time for precise collision
		for(int i = 0; i < Math.abs(velY); i++) {
			newloc.y = aabb.y + fallDir;
			if(!level.collidesPlatform(newloc))
				aabb.y += fallDir;
			else
				velY = 0;
		}
		if(aabb.y < 0) {
			aabb.y = 0;
			// DIE
		}
		
		// Switch to jump frame if in air
		if(!onGround()) {
			frame = 2;
		}
		else if(frame == 2) {
			frame = 0;
		}
		
		// Try to collect clocks
		if(level.collectClock(this)) {
			clocks++;
		}
	}

}
