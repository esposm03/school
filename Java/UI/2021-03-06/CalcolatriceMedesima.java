import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CalcolatriceMedesima /* extends JFrame implements ActionListener */ {
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
        Stack s = new CalcolatriceMedesima.Stack();
        s.push(10);
        s.push(Operazione.Somma);
        s.push(2);
        s.push(Operazione.Moltiplicazione);
        s.push(5);
        System.out.println("Risultato: " + s.execute());

        //new CalcolatriceMedesima();
    }

    private static class Stack {
        private ArrayList<Object> stack = new ArrayList<Object>();

        public void push(int a) {
            stack.add(a);
        }

        public void push(Operazione a) {
            stack.add(a);
        }

        public int execute() {
            int res = (int) stack.remove(0);

            for (int i = 0; i < (stack.size() - 1) / 2; i++) {
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
            return res;
        }
    }

    private enum Operazione {
        Somma, Sottrazione, Moltiplicazione, Divisione,
    }

    // /**
    //  * Aggiorna il testo
    //  */
    // public void update() {
    //     testo.setText(String.valueOf(stack.val1));
    // }

    // /**
    //  * Aggiorna il testo, da usare dopo un operazione
    //  */
    // public void updateZero() {
    //     testo.setText("");
    // }

    // public void actionPerformed(ActionEvent event) {
    //     String tasto = event.getActionCommand();

    //     // Se il tasto era uno numerico
    //     int valTasto = isIntero(tasto);
    //     if (valTasto >= 0 && valTasto <= 9) {
    //         testo.setText(testo.getText() + tasto);
    //         stack.push(Integer.parseInt(testo.getText()));
    //     }

    //     // Se il tasto non era numerico
    //     switch (tasto) {
    //     case "+":
    //         op = Operazione.Somma;
    //         updateZero();
    //         break;
    //     case "-":
    //         op = Operazione.Sottrazione;
    //         updateZero();
    //         break;
    //     case "*":
    //         op = Operazione.Moltiplicazione;
    //         updateZero();
    //         break;
    //     case ":":
    //         op = Operazione.Divisione;
    //         updateZero();
    //         break;
    //     case "CE":
    //         stack.push(0);
    //         stack.push(0);
    //         update();
    //         break;
    //     case "=":
    //         switch (op) {
    //         case Somma:
    //             stack.push(stack.getFirst() + stack.getLast());
    //             update();
    //             break;
    //         case Sottrazione:
    //             stack.push(stack.getFirst() - stack.getLast());
    //             update();
    //             break;
    //         case Moltiplicazione:
    //             stack.push(stack.getFirst() * stack.getLast());
    //             update();
    //             break;
    //         case Divisione:
    //             stack.push(stack.getFirst() / stack.getLast());
    //             update();
    //             break;
    //         }
    //     }

    // }

    // /**
    //  * Verifica se una stringa è un numero intero.
    //  * 
    //  * @return -1 se non lo è altrimenti il suo valore
    //  */
    // private int isIntero(String num) {
    //     try {
    //         int n = Integer.parseInt(num);
    //         return n;
    //     } catch (Exception e) {
    //         return -1;
    //     }
    // }

    /**
     * costruttore senza parametri crea una finestra per testare i componenti più
     * comuni
     */
    // public CalcolatriceMedesima() {
    //     setSize(400, 400);
    //     setTitle("Calcolatrice");
    //     setDefaultCloseOperation(EXIT_ON_CLOSE);

    //     testo = new JTextField("");
    //     testo.setHorizontalAlignment(JTextField.RIGHT);
    //     testo.setEditable(false);

    //     cifre = new JButton[10];
    //     for (int i = 0; i < 10; i++) {
    //         cifre[i] = new JButton("" + i);
    //         cifre[i].addActionListener(this);
    //     }

    //     piu = new JButton("+");
    //     meno = new JButton("-");
    //     per = new JButton("*");
    //     uguale = new JButton("=");
    //     ce = new JButton("CE");
    //     diviso = new JButton(":");

    //     piu.addActionListener(this);
    //     meno.addActionListener(this);
    //     per.addActionListener(this);
    //     diviso.addActionListener(this);
    //     uguale.addActionListener(this);
    //     ce.addActionListener(this);

    //     JPanel pannello2 = new JPanel();
    //     pannello2.setLayout(new GridLayout(4, 4));
    //     pannello2.add(cifre[1]);
    //     pannello2.add(cifre[2]);
    //     pannello2.add(cifre[3]);
    //     pannello2.add(ce);
    //     pannello2.add(cifre[4]);
    //     pannello2.add(cifre[5]);
    //     pannello2.add(cifre[6]);
    //     pannello2.add(piu);
    //     pannello2.add(cifre[7]);
    //     pannello2.add(cifre[8]);
    //     pannello2.add(cifre[9]);
    //     pannello2.add(meno);
    //     pannello2.add(per);
    //     pannello2.add(cifre[0]);
    //     pannello2.add(diviso);
    //     pannello2.add(uguale);

    //     add(testo, "North");

    //     add(pannello2, "Center");

    //     setVisible(true);
    // }

}