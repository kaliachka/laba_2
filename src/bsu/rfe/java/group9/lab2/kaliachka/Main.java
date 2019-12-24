package bsu.rfe.java.group9.lab2.kaliachka;

import java.awt.Color;
import java.awt.Toolkit;
import java.util.Locale;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    private Double sum = 0.0D;

    private Double calculate1(Double x, Double y, Double z) {
        return Math.pow(Math.cos(3.14*x*x*x)+Math.log(1 + y) * Math.log(1 + y), 0.25)*(Math.exp(z*z)+ Math.sqrt(1/x)+ Math.cos(Math.exp(y)));
    }

    private Double calculate2(Double x, Double y, Double z) {
        return (Math.pow(x, x))/(Math.sqrt(y*y*y + 1)+Math.log(z));
    }

    private void addRadioButton(String buttonName, int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener((ev) -> {
            this.formulaId = formulaId;
        });
        this.radioButtons.add(button);
        this.hboxFormulaType.add(button);
    }

    private Main() {
        super("Вычисление формулы");
        this.setSize(400, 320);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 400) / 2, (kit.getScreenSize().height - 320) / 2);
        this.hboxFormulaType.add(Box.createHorizontalGlue());
        this.addRadioButton("Формула 1", 1);
        this.addRadioButton("Формула 2", 2);
        this.radioButtons.setSelected(((AbstractButton)this.radioButtons.getElements().nextElement()).getModel(), true);
        this.hboxFormulaType.add(Box.createHorizontalGlue());
        // this.hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        JLabel labelForX = new JLabel("X:");
        this.textFieldX = new JTextField("0", 10);
        this.textFieldX.setMaximumSize(this.textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        this.textFieldY = new JTextField("0", 10);
        this.textFieldY.setMaximumSize(this.textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        this.textFieldZ = new JTextField("0", 10);
        this.textFieldZ.setMaximumSize(this.textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(50));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(this.textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
        JLabel labelForResult = new JLabel("Результат:");
        this.textFieldResult = new JTextField("0", 10);
        this.textFieldResult.setMaximumSize(this.textFieldResult.getPreferredSize());
        this.textFieldResult.setEnabled(false);
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(this.textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener((ev) -> {
            try {
                Double x = Double.parseDouble(this.textFieldX.getText());
                Double y = Double.parseDouble(this.textFieldY.getText());
                Double z = Double.parseDouble(this.textFieldZ.getText());
                Double result;
                if (this.formulaId == 1) {
                    result = this.calculate1(x, y, z);
                } else {
                    result = this.calculate2(x, y, z);
                }

                this.textFieldResult.setText(String.format(Locale.US, "%.2f", result));
            } catch (NumberFormatException var6) {
                JOptionPane.showMessageDialog(this, "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", 2);
            }

        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener((ev) -> {
            this.textFieldX.setText("0");
            this.textFieldY.setText("0");
            this.textFieldZ.setText("0");
            this.textFieldResult.setText("0");
        });
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener((ev) -> {
            this.sum = 0.0D;
            this.textFieldResult.setText("0");
        });
        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener((ev) -> {
            this.sum = this.sum + Double.parseDouble(this.textFieldResult.getText());
            this.textFieldResult.setText(String.format(Locale.US, "%.2f", this.sum));
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalStrut(10));
        hboxButtons.add(buttonMPlus);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(this.hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        this.getContentPane().add(contentBox, "Center");
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}

