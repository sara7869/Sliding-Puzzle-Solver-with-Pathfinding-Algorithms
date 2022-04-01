import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;

public class ArenaTest {

	public static final Arena TEST_ARENA = new Arena(5, 4, 9, 2, new int[][] {
			{ 2, 0, 0, 2, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 4, 0, 0, 0, 4, 0, 2, 0 },
			{ 0, 2, 2, 0, 4, 0, 6, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 4, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 2, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 4, 0, 0, 2, 2, 0 },
			{ 0, 0, 2, 0, 0, 0, 4, 0, 0, 4 },
			{ 4, 4, 4, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 0, 0, 0, 0, 0, 4, 0, 4 }
	});

	public static void main(String[] args) {
		int tileSize = 20;
		int imageWidth = 10 * tileSize;
		int imageHeight = 10 * tileSize;
		BufferedImage buffer = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();
		TEST_ARENA.paint(g, tileSize);
		try {
			ImageIO.write(buffer, "png", new File("./debug.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
