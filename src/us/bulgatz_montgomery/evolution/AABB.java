package us.bulgatz_montgomery.evolution;

public class AABB {
	public int x, y, width, height;
	
	public AABB(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public AABB(AABB copy) {
		x = copy.x;
		y = copy.y;
		width = copy.width;
		height = copy.height;
	}
	
	public boolean collides(AABB other) {		
		boolean right = (x + width) >= other.x && (x + width) <= (other.x + other.width);
		boolean left = x >= other.x && x <= (other.x + other.width);
		boolean top = (y + height) >= other.y && (y + height) <= (other.y + other.height);
		boolean bottom = y >= other.y && y <= (other.y + other.height);
		
		return (right && top) || (right && bottom) || (left && top) || (left && bottom);
	}
}
