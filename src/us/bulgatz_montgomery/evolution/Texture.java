package us.bulgatz_montgomery.evolution;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Texture {
	public final int texID;
	public final BufferedImage sourceImage;
	
	public Texture(BufferedImage img) {
		BufferedImage tmp = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
		tmp.getGraphics().drawImage(img, 0, 0, null);
		img = tmp;
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		sourceImage = img;
		ByteBuffer buffer = convertToBuffer(img);
		texID = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D,
							0,
							GL11.GL_RGBA8,
							img.getWidth(),
							img.getHeight(),
							0,
							GL11.GL_RGBA,
							GL11.GL_UNSIGNED_BYTE,
							buffer);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
	}
	
	public void bind() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
	}
	
	public int getWidth() {
		return sourceImage.getWidth();
	}
	
	public int getHeight() {
		return sourceImage.getHeight();
	}

	private ByteBuffer convertToBuffer(BufferedImage img) {
		ByteBuffer buffer;
		int[] pixels = new int[img.getWidth() * img.getHeight() * 4];
		img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
		buffer = BufferUtils.createByteBuffer(img.getWidth() * img.getHeight() * 4);
		
		for(int y = 0; y < img.getHeight(); y++) {
			for(int x = 0; x < img.getWidth(); x++) {
				int p = pixels[y * img.getWidth() + x];
				buffer.put((byte) ((p >> 16) & 0xFF));
				buffer.put((byte) ((p >> 8) & 0xFF));
				buffer.put((byte) (p & 0xFF));
				buffer.put((byte) ((p >> 24) & 0xFF));
			}
		}
		buffer.flip();
		return buffer;
	}
}
