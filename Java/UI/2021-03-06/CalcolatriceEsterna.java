import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class CalcolatriceEsterna extends JFrame {
    private static final long serialVersionUID = 3735498265493338619L;

    private JTextField testo;
    private JButton[] cifre;
    private JButton piu;
    private JButton meno;
    private JButton uguale;
    private JButton per;
    private JButton diviso;
    private JButton ce;

    public static void main(String[] args) {
        new CalcolatriceEsterna();
    }

    /**
     * Costruttore senza parametri, che crea una finestra
     * per testare i componenti più comuni
     */
    public CalcolatriceEsterna() {
        setSize(400, 400);
        setTitle("Calcolatrice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        testo = new JTextField("");
        testo.setHorizontalAlignment(JTextField.RIGHT);
        testo.setEditable(false);

        GestoreScelta gestore = new GestoreScelta(testo);

        cifre = new JButton[10];
        for (int i = 0; i < 10; i++) {
            cifre[i] = new JButton("" + i);
            cifre[i].addActionListener(gestore);
        }

        piu = new JButton("+");
        meno = new JButton("-");
        per = new JButton("*");
        uguale = new JButton("=");
        ce = new JButton("CE");
        diviso = new JButton(":");

        piu.addActionListener(gestore);
        meno.addActionListener(gestore);
        per.addActionListener(gestore);
        diviso.addActionListener(gestore);
        uguale.addActionListener(gestore);
        ce.addActionListener(gestore);

        JPanel pannello2 = new JPanel();
        pannello2.setLayout(new GridLayout(4, 4));
        pannello2.add(cifre[1]);
        pannello2.add(cifre[2]);
        pannello2.add(cifre[3]);
        pannello2.add(ce);
        pannello2.add(cifre[4]);
        pannello2.add(cifre[5]);
        pannello2.add(cifre[6]);
        pannello2.add(piu);
        pannello2.add(cifre[7]);
        pannello2.add(cifre[8]);
        pannello2.add(cifre[9]);
        pannello2.add(meno);
        pannello2.add(per);
        pannello2.add(cifre[0]);
        pannello2.add(diviso);
        pannello2.add(uguale);

        add(testo, "North");

        add(pannello2, "Center");

        setVisible(true);
    }

}