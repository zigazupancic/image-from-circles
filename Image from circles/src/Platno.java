import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseMotionListener {

	protected Drevo drevo;
	/**
	 * Create the panel.
	 * @throws IOException 
	 */
	public Platno(String imeDatoteke) throws IOException {
		super();
		novaSlika(imeDatoteke);
	}
	
	public void novaSlika(String ime) throws IOException {
		File datoteka = new File(ime);
		BufferedImage slika = ImageIO.read(datoteka);
		drevo = new Drevo(slika, 0, 0, slika.getWidth(), slika.getHeight());
		drevo.razpadlo = true;
	}
	
	public void paintComponent(Graphics g) {
		narisiDrevo(g, drevo);
		
	}
	
	public void narisiDrevo(Graphics g, Drevo d) {
		if (d.razpadlo) {
			narisiDrevo(g, d.spodajDesno);
			narisiDrevo(g, d.spodajLevo);
			narisiDrevo(g, d.zgorajDesno);
			narisiDrevo(g, d.zgorajLevo);
		} else {
			g.setColor(d.barva);
			g.drawRect(d.ax, d.ay, d.bx - d.ax, d.by - d.ay);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Drevo d = Drevo.vsebujeTocko(drevo, e.getX(), e.getY());
		if (d.zgorajDesno != null) {
			d.razpadlo = true;
		}
		repaint();
	}

}