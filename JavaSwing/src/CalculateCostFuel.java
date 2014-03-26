/**
 * Вариант 2. (М - Я). Данные представляют собой таблицу количеств потребляемого топлива для автомобилей разных марок,
 * например,
 * 95 9.5 Audi A6
 * ДТ 9.4 BMW 530D
 * 95 17.0 Chevrolet Tahoe 5.7 V8 4WD
 * …
 *
 * В данной таблице в первом столбце указан тип потребляемого топлива (92/95/98/ДТ),
 * во втором - среднее количество потребляемого топлива (в литрах на 100 км), далее - марка автомобиля.
 * Программа должна вводить эти данные (корректность заданного формата данных проверять не требуется) и позволять пользователю
 * на основании этих данных рассчитывать сумму (в рублях), затрачиваемую на топливо для этих автомобилей в год.
 * Пользователь вводит число километров, которые он намерен проезжать в год, выбирает марку автомобиля, вводит цену
 * на соответствующий вид топлива в рублях за 1 литр (программа подсказывает, какой вид топлива использует выбранный автомобиль),
 * после чего программа по нажатию кнопки рассчитывает годовой расход на топливо в рублях.
 *
 * Если данные введены неправильно (ошибка в формате числа, отрицательная цена или количество километров  и т.п.),
 * то пользователю сообщают об этом в отдельном информационном модальном окне.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main class calculate the amount spent on fuel in the year
 */
public class CalculateCostFuel extends JFrame {
    private boolean DEBUG = false;

    private ArrayList<Data> data;

    private JComboBox<String> selectList;
    private JLabel costLabel;

    private JTextField costField;
    private JTextField countMileageField;

    private JLabel answer;

    public CalculateCostFuel() throws FileNotFoundException {
        super("Расчёт стоимости топлива");
        setContentPane(createWidget());
    }

    private String genText(String str) {
        return "Цена на " + str + ":";
    }

    /**
     * created and showed GUI
     *
     * @return JComponent component for content pane
     * @throws FileNotFoundException
     */
    private JComponent createWidget() throws FileNotFoundException {
        Reader reader = new Reader("data.txt");
        data = reader.getData();
        String[] brands = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            brands[i] = data.get(i).getBrand();
        }
        if (DEBUG) {
            for (Data d: data) {
                System.out.println(d.getTypeFuel() + ", " + d.getCountFuel() + ", " + d.getBrand());
            }
        }

        /**
         * Create panels
         */
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel insidePanel = new JPanel(new GridLayout(4, 3, 5, 5));
        mainPanel.add(insidePanel);

        /**
         * Create default fields
         */
        JLabel brandLabel = new JLabel("Марка автомобиля:");
        brandLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        costLabel = new JLabel(genText(data.get(0).getTypeFuel()));
        costLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel yearMileageLabel = new JLabel("Годовой пробег:");
        yearMileageLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        selectList = new JComboBox<String>(brands);
        selectList.setPrototypeDisplayValue("XXXXXXXXXXXXX");
        selectList.addItemListener(new ItemChangeListener());

        costField = new JTextField("");

        countMileageField = new JTextField("");

        JButton calculate = new JButton("Стоимость");
        calculate.addActionListener(new CalculateActionListener());
        answer = new JLabel("");

        /**
         * Adding default fields in the inside panel
         */
        insidePanel.add(brandLabel);
        insidePanel.add(selectList);
        insidePanel.add(new JLabel(""));

        insidePanel.add(costLabel);
        insidePanel.add(costField);
        insidePanel.add(new JLabel("руб./л"));

        insidePanel.add(yearMileageLabel);
        insidePanel.add(countMileageField);
        insidePanel.add(new JLabel("км."));

        insidePanel.add(calculate);
        insidePanel.add(answer);

        return mainPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CalculateCostFuel calculateCostFuel = null;
                try {
                    calculateCostFuel = new CalculateCostFuel();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                assert calculateCostFuel != null;
                calculateCostFuel.setDefaultCloseOperation(EXIT_ON_CLOSE);
                calculateCostFuel.pack();
                calculateCostFuel.setLocationRelativeTo(null);
                calculateCostFuel.setResizable(false);
                calculateCostFuel.setVisible(true);
            }
        });
    }

    /**
     * Action listener for button calculate
     * Calculates the amount spent on fuel
     */
    private class CalculateActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            double costFuel = 0.0;
            double countMileage = 0.0;
            boolean error1 = false;
            boolean error2 = false;
            try {
                costFuel = Double.parseDouble(costField.getText());
            } catch (Exception ex) {
                error1 = true;
            }
            try {
                countMileage = Double.parseDouble(countMileageField.getText());
            } catch (Exception ex) {
                error2 = true;
            }
            error1 = error1 | (costFuel < 0);
            error2 = error2 | (countMileage < 0);
            if (error1 || error2) {
                StringBuilder msg = new StringBuilder();
                if (error1) {
                    if (costField.getText().length() == 0) {
                        msg.append("Цена на топливо не указана\n");
                    } else if (costFuel < 0) {
                        msg.append("Цена на топливо не может быть отрицательной\n");
                    } else {
                        msg.append("Цена на топливо указана неверно\n");
                    }
                }
                if (error2) {
                    if (countMileageField.getText().length() == 0) {
                        msg.append("Годовой пробег не указан\n");
                    } else if (countMileage < 0) {
                        msg.append("Годовой пробег не может быть отрицательным\n");
                    } else {
                        msg.append("Годовой пробег указан неверно\n");
                    }
                }
                JOptionPane.showMessageDialog(null, msg.toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                answer.setText("");
                return;
            }
            if (DEBUG) {
                System.out.println(costFuel + " " + countMileage);
            }
            String select = (String) selectList.getSelectedItem();
            double countFuel = 0.0;
            for (Data d: data) {
                if (d.getBrand().equals(select)) {
                    countFuel = d.getCountFuel();
                    break;
                }
            }
            answer.setText(String.format("%.2f руб.", countMileage / 100.0 * countFuel * costFuel));
        }
    }

    /**
     * Action listener for selected list
     * Changes costLabel with fuel for current brand auto
     */
    private class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                String select = event.getItem().toString();
                for (Data d: data) {
                    if (d.getBrand().equals(select)) {
                        costLabel.setText(genText(d.getTypeFuel()));
                        break;
                    }
                }
            }
        }
    }
}
