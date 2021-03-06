import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FinestraMouseMedesimo extends JFrame implements MouseListener {
    private static final long serialVersionUID = -5782704158752065024L;

    public static void main(String[] args) {
        new FinestraMouseMedesimo();
    }

    public FinestraMouseMedesimo() {
        super("Medesimo");

        JLabel a = new JLabel("Inizio");
        a.addMouseListener(this);
        add(a, "North");

        JLabel b = new JLabel("Inizio2");
        b.addMouseListener(this);
        add(b, "South");

        JLabel c = new JLabel("East");
        c.addMouseListener(this);
        add(c, "East");

        JLabel d = new JLabel("West");
        d.addMouseListener(this);
        add(d, "West");

        JLabel e = new JLabel("Center");
        e.addMouseListener(this);
        add(e, "Center");

        setSize(600, 400);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        ((JLabel) e.getComponent()).setText("Premuto");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ((JLabel) e.getComponent()).setText("Rilasciato");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        ((JLabel) e.getComponent()).setText("Mouse entrato");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        ((JLabel) e.getComponent()).setText("Mouse uscito");
    }

}
