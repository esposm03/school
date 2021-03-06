import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalcolatriceMedesima extends JFrame implements ActionListener {
    private static final long serialVersionUID = 3735498265493338619L;

    private JTextField testo;
    private JButton[] cifre;
    private JButton piu;
    private JButton meno;
    private JButton uguale;
    private JButton per;
    private JButton diviso;
    private JButton ce;

    private Stack stack = new Stack();
    private Operazione op = Operazione.Somma;

    public static void main(String[] args) {
        new CalcolatriceMedesima();
    }

    /**
     * costruttore senza parametri crea una finestra per testare i componenti più
     * comuni
     */
    public CalcolatriceMedesima() {
        setSize(400, 400);
        setTitle("Calcolatrice");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        testo = new JTextField("");
        testo.setHorizontalAlignment(JTextField.RIGHT);
        testo.setEditable(false);

        cifre = new JButton[10];
        for (int i = 0; i < 10; i++) {
            cifre[i] = new JButton("" + i);
            cifre[i].addActionListener(this);
        }

        piu = new JButton("+");
        meno = new JButton("-");
        per = new JButton("*");
        uguale = new JButton("=");
        ce = new JButton("CE");
        diviso = new JButton(":");

        piu.addActionListener(this);
        meno.addActionListener(this);
        per.addActionListener(this);
        diviso.addActionListener(this);
        uguale.addActionListener(this);
        ce.addActionListener(this);

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

    private class Stack {
        public int val1, val2;

        public void push(int a) {
            val2 = val1;
            val1 = a;
        }

        public int getFirst() {
            return val2;
        }

        public int getLast() {
            return val1;
        }
    }

    private enum Operazione {
        Somma, Sottrazione, Moltiplicazione, Divisione,
    }

    /**
     * Aggiorna il testo
     */
    public void update() {
        testo.setText(String.valueOf(stack.val1));
    }

    /**
     * Aggiorna il testo, da usare dopo un operazione
     */
    public void updateZero() {
        testo.setText("");
    }

    public void actionPerformed(ActionEvent event) {
        String tasto = event.getActionCommand();

        // Se il tasto era uno numerico
        int valTasto = isIntero(tasto);
        if (valTasto >= 0 && valTasto <= 9) {
            testo.setText(testo.getText() + tasto);
            stack.push(Integer.parseInt(testo.getText()));
        }

        // Se il tasto non era numerico
        switch (tasto) {
        case "+":
            op = Operazione.Somma;
            updateZero();
            break;
        case "-":
            op = Operazione.Sottrazione;
            updateZero();
            break;
        case "*":
            op = Operazione.Moltiplicazione;
            updateZero();
            break;
        case ":":
            op = Operazione.Divisione;
            updateZero();
            break;
        case "CE":
            stack.push(0);
            stack.push(0);
            update();
            break;
        case "=":
            switch (op) {
            case Somma:
                stack.push(stack.getFirst() + stack.getLast());
                update();
                break;
            case Sottrazione:
                stack.push(stack.getFirst() - stack.getLast());
                update();
                break;
            case Moltiplicazione:
                stack.push(stack.getFirst() * stack.getLast());
                update();
                break;
            case Divisione:
                stack.push(stack.getFirst() / stack.getLast());
                update();
                break;
            }
        }

    }

    /**
     * Verifica se una stringa è un numero intero.
     * 
     * @return -1 se non lo è altrimenti il suo valore
     */
    private int isIntero(String num) {
        try {
            int n = Integer.parseInt(num);
            return n;
        } catch (Exception e) {
            return -1;
        }
    }

}