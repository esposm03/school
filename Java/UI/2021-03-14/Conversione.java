import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Conversione extends JFrame {
    private static final long serialVersionUID = -7052340380152234204L;

    public static void main(String[] args) {
        new Conversione();
    }

    public Conversione() {
        super("Conversione");

        ButtonGroup bGroup = new ButtonGroup();
        JRadioButton kelvin = new JRadioButton("Kelvin");
        JRadioButton celsius = new JRadioButton("Celsius");
        bGroup.add(kelvin);
        bGroup.add(celsius);

        JPanel pan = new JPanel();
        pan.add(kelvin);
        pan.add(celsius);
        add(pan, "North");

        JTextField textInput = new JTextField();
        textInput.setColumns(20);
        add(textInput, "Center");

        JPanel panSud = new JPanel();
        JLabel output = new JLabel("Kelvin: ");
        JButton invia = new JButton("Calcola");
        invia.addActionListener(new Listener(kelvin, celsius, textInput, output));
        panSud.add(invia);
        panSud.add(output);
        add(panSud, "South");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
    }

    class Listener implements ActionListener {
        JRadioButton kelvinButton, celsiusButton;
        JTextField input;
        JLabel output;

        public Listener(JRadioButton kelvinButton, JRadioButton celsiusButton, JTextField input, JLabel output) {
            this.kelvinButton = kelvinButton;
            this.celsiusButton = celsiusButton;
            this.input = input;
            this.output = output;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            float input = Float.parseFloat(this.input.getText());
            if (!kelvinButton.isSelected()) {
                input -= 273.15;
            }
            output.setText("Kelvin: " + input);
        }

    }
}
