import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

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
