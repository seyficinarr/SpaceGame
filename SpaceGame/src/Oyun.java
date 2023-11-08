import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Oyun extends JPanel implements KeyListener, ActionListener {

	Timer timer = new Timer(5, this);
	
	private int harcanan_ates = 0;
	
	private int harcananSüre = 0;
	private int gecenSüre = 0;

	private BufferedImage image;
	private BufferedImage image1;

	private ArrayList<Ates> atesler = new ArrayList<Ates>();

	private int atesdirY = 3;

	private int topX = 0;

	private int topdirX = 3;

	private int uzayGemisiX = 0;

	private int dirUzayX = 20;
	
	public boolean kontrolEt() {
		
		for(Ates ates : atesler) {
			
			if(new Rectangle(ates.getX(), ates.getY(), 10, 20)
					.intersects(new Rectangle(topX, 0, 20, 20))){
				
				return true;
			}
			
			
		}
		
		return false;
	}

	public Oyun() {
		try {
			image = ImageIO.read(new FileImageInputStream(new File("eren.jpg")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setBackground(Color.BLACK);

		timer.start();

	}

	class Ates {
		private int x;
		private int y;

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

		public Ates(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (Ates ates : atesler) {
			
			ates.setY(ates.getY() - atesdirY);
		}

		topX += topdirX;

		if (topX >= 750 || topX <= 0)
			topdirX = -topdirX;

		repaint();

	}

	@Override
	public void paint(Graphics g) {
		
		gecenSüre += 5;
		super.paint(g);
		g.setColor(Color.BLUE);

		g.fillOval(topX, 0, 20, 20);

		g.drawImage(image, uzayGemisiX, 490, image.getWidth() / 2, image.getHeight() / 2, this);
		
		for (Ates ates : atesler) {
			if(ates.getY() < 0) {
				atesler.remove(ates);
			}
		}
		
		g.setColor(Color.RED);
		
		for(Ates ates : atesler) {
			
			g.fillRect(ates.getX(), ates.getY(), 10, 20);
		}
		
		if(kontrolEt()) {
			
			timer.stop();
			
			String message = "Oyun bitti \n" +
			                 "Harcanan Ateþ : " + harcanan_ates +
			                 "\nGeçen Süre :" + gecenSüre / 1000.0 + "saniye";
			
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
			
			
					
		
					
			
		}
		
		
	}

	@Override
	public void repaint() {
		super.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();

		if (c == KeyEvent.VK_LEFT) {
			if (uzayGemisiX <= 0)
				uzayGemisiX = 0;

			else
				uzayGemisiX -= dirUzayX;

		}

		else if (c == KeyEvent.VK_RIGHT) {
			if ((uzayGemisiX >= 720))
				uzayGemisiX = 720;
			else
				uzayGemisiX += dirUzayX;

		}
		
		else if(c == KeyEvent.VK_CONTROL) {
			atesler.add(new Ates(uzayGemisiX + 15, 480));
			
			harcanan_ates++;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
