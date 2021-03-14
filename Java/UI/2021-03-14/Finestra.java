import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Finestra extends JFrame {
	// Ignorare
	private static final long serialVersionUID = 4648172894076113183L;

	private JPanel[] panels = new JPanel[5];

	public static void main(String[] args) {
		new Finestra();
	}

	public Finestra() {
		super("Hello");
		panels[0] = new JPanel();
		panels[1] = new JPanel();
		panels[2] = new JPanel();
		panels[3] = new JPanel();
		panels[4] = new JPanel();

		panels[0].addMouseListener(new Listener(panels, 0));
		panels[1].addMouseListener(new Listener(panels, 1));
		panels[2].addMouseListener(new Listener(panels, 2));
		panels[3].addMouseListener(new Listener(panels, 3));
		panels[4].addMouseListener(new Listener(panels, 4));

		add(panels[0], "North");
		add(panels[1], "South");
		add(panels[2], "East");
		add(panels[3], "West");
		add(panels[4], "Center");

		for (int i = 0; i < panels.length; i++) {
			panels[i].add(new JLabel("a"));
			panels[i].add(new JLabel("b"));
		}

		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	class Listener implements MouseListener {
		JPanel[] pannelli;
		int questo;

		public Listener(JPanel[] pannelli, int questo) {
			this.pannelli = pannelli;
			this.questo = questo;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			for (int i = 0; i < panels.length; i++) {
				if (i == questo) {
					((JLabel) pannelli[i].getComponent(0)).setText("Clicccato");
					((JLabel) pannelli[i].getComponent(1)).setText("b");
				} else {
					((JLabel) pannelli[i].getComponent(0)).setText("a");
					((JLabel) pannelli[i].getComponent(1)).setText("Mollato");
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}
	}
}
