import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class GestoreScelta implements ActionListener {
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

    private Stack stack = new Stack();
    private Operazione op = Operazione.Somma;
    private JTextField testo;

    public GestoreScelta(JTextField testo) {
        this.testo = testo;
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
