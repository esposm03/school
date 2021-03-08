import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        new CalcolatriceMedesima();
    }

    /**
     * Il cuore della calcolatrice, questa classe salva i numeri e le operazioni
     * inserite, ed effettua i calcoli nel metodo `execute`
     */
    private static class Stack {
        // In questo stack, gli elementi sono inseriti in alto; quindi, il primo elemento
        // inserito avrà indice 0, l'ultimo n
        private ArrayList<Object> stack = new ArrayList<Object>();

        public void push(int a) {
            stack.add(a);
        }

        public void push(Operazione a) {
            stack.add(a);
        }

        public void clear() {
            this.stack.clear();
        }

        public Object lastInserted() {
            if (stack.size() > 0) {
                return stack.get(stack.size() - 1);
            } else {
                return null;
            }
        }

        public Object pop() {
            if (stack.size() > 0) {
                return stack.remove(stack.size() - 1);
            } else {
                return null;
            }
        }

        public int execute() {
            int res = (int) stack.remove(0);

            while (stack.size() > 0) {
                Operazione op = (Operazione) stack.remove(0);
                int temp = (int) stack.remove(0);

                switch (op) {
                    case Somma:
                        res += temp;
                        break;
                    case Sottrazione:
                        res -= temp;
                        break;
                    case Moltiplicazione:
                        res *= temp;
                        break;
                    case Divisione:
                        res /= temp;
                        break;
                }
            }

            this.stack.clear();
            this.stack.add(res);
            return res;
        }
    }

    private enum Operazione {
        Somma,
        Sottrazione,
        Moltiplicazione,
        Divisione,
    }

    /**
     * Aggiorna il testo
     */
    public void updateText() {
        if (stack.lastInserted() instanceof Integer) {
            testo.setText(String.valueOf(stack.lastInserted()));
        } else {
            testo.setText("");
        }
    }

    public void actionPerformed(ActionEvent event) {
        String tasto = event.getActionCommand();

        // Se il tasto era uno numerico
        int valTasto = isIntero(tasto);
        if (valTasto >= 0 && valTasto <= 9) {

            // Se il  precedente era un numero intero, appendiamo
            // Se era un'operazione, pushiamo
            if (stack.lastInserted() instanceof Integer) {
                int last = (int) stack.pop();
                stack.push(Integer.parseInt("" + last + tasto));
            } else {
                stack.push(valTasto);
            }
            updateText();
        }

        // Se il tasto non era numerico
        switch (tasto) {
        case "+":
            stack.push(Operazione.Somma);
            updateText();
            break;
        case "-":
            stack.push(Operazione.Sottrazione);
            updateText();
            break;
        case "*":
            stack.push(Operazione.Moltiplicazione);
            updateText();
            break;
        case ":":
            stack.push(Operazione.Divisione);
            updateText();
            break;
        case "CE":
            stack.clear();
            updateText();
            break;
        case "=":
            stack.execute();
            updateText();
            break;
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

}