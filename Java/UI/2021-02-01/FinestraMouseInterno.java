import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FinestraMouseInterno extends JFrame {
    private static final long serialVersionUID = -5782704158752065024L;

    public static void main(String[] args) {
        new FinestraMouseInterno();
    }

    public FinestraMouseInterno() {
        super("Interno");

        JLabel a = new JLabel("North");
        a.addMouseListener(new FinestraMouseInterno.AscoltatoreMouse(a));
        add(a, "North");

        JLabel b = new JLabel("South");
        b.addMouseListener(new FinestraMouseInterno.AscoltatoreMouse(b));
        add(b, "South");

        JLabel c = new JLabel("East");
        c.addMouseListener(new FinestraMouseInterno.AscoltatoreMouse(c));
        add(c, "East");

        JLabel d = new JLabel("West");
        d.addMouseListener(new FinestraMouseInterno.AscoltatoreMouse(d));
        add(d, "West");

        JLabel e = new JLabel("Center");
        e.addMouseListener(new FinestraMouseInterno.AscoltatoreMouse(e));
        add(e, "Center");

        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public class AscoltatoreMouse implements MouseListener {
        private JLabel mouseLabel;

        public AscoltatoreMouse(JLabel m) {
            this.mouseLabel = m;
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
            mouseLabel.setText("Premuto");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            mouseLabel.setText("Rilasciato");
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            mouseLabel.setText("Mouse entrato");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            mouseLabel.setText("Mouse uscito");
        }
    }
}
