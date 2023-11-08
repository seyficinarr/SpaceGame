import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JFrame;



public class OyunEkraný extends JFrame {
	
	

	public OyunEkraný(String title) throws HeadlessException {
		super(title);
	}

	public static void main(String[] args) {

		OyunEkraný ekran = new OyunEkraný("Uzay Oyunu");

		ekran.setResizable(false);
		ekran.setFocusable(false);

		ekran.setSize(800, 600);

		ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Oyun oyun = new Oyun();

		oyun.requestFocus();

		oyun.addKeyListener(oyun);

		oyun.setFocusable(true);
		oyun.setFocusTraversalKeysEnabled(false);

		ekran.add(oyun);

		ekran.setVisible(true);
	}

}
