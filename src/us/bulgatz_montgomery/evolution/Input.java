package us.bulgatz_montgomery.evolution;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class Input {
	boolean[] keys;
	
	public Input() {
		keys = new boolean[256];
	}
	
	public void poll() {
		
		if(!Display.isActive()) {
			for(int i = 0; i < keys.length; i++) {
				keys[i] = false;
			}
			return;
		}
		
		while(Keyboard.next()) {
			if(Keyboard.getEventKey() != -1) {
				keys[Keyboard.getEventKey()] = Keyboard.getEventKeyState();
			}
		}
	}
	
	public boolean keyDown(int key) {
		if(key >= 0 && key < keys.length) {
			return keys[key];
		}
		return false;
	}
}
