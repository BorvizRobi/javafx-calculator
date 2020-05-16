package calculator;

import calculator.model.Calculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Locale;

public class CalculatorController {

    @FXML
    private TextField display;

    private Calculator calculator;
    private boolean startNumber = true;
    private double number1;
    private String operator = "";

    @FXML
    private void initialize() {
        calculator = new Calculator();
    }

    @FXML
    public void processDigit(ActionEvent event) {
        String digitPressed = ((Button) event.getSource()).getText();
        System.out.println(digitPressed);
        if (startNumber || display.getText().equals("0")) {
            display.setText(digitPressed);
        } else {
            display.setText(display.getText() + digitPressed);
        }
        startNumber = false;
    }

    @FXML
    public void processOperator(ActionEvent event) {
        String operatorPressed = ((Button) event.getSource()).getText();
        System.out.println(operatorPressed);
        if (operatorPressed.equals("=")) {
           if (operator.isEmpty()) {
               return;
           }
           double number2 = Double.parseDouble(display.getText());
           double result = calculator.calculate(number1, number2, operator);
           System.out.println(result);
           if(result == (long)result)
               display.setText(String.format(Locale.ROOT,"%.0f", result));
           else
           display.setText(String.format(Locale.ROOT,"%.1f", result));

           operator = "";
        } else {
            if (! operator.isEmpty()) {
                return;
            }
            number1 = Double.parseDouble(display.getText());
            operator = operatorPressed;
            startNumber = true;
        }
    }

    @FXML
    public void processAllClear(ActionEvent event) {

        String ACPressed = ((Button) event.getSource()).getText();
        System.out.println(ACPressed);

        display.setText("0");

    }
    @FXML
    public void processPlusMinus(ActionEvent event) {

        String PlusMinus = ((Button) event.getSource()).getText();
        System.out.println(PlusMinus);

        number1 = Double.parseDouble(display.getText());
        number1 = -number1;
        if(number1 == (long)number1)
            display.setText(String.format(Locale.ROOT,"%.0f", number1));
        else
            display.setText(String.format(Locale.ROOT,"%.1f", number1));
    }

    @FXML
    public void processDecimalPoint(ActionEvent event) {

        String DecimalPoint = ((Button) event.getSource()).getText();
        System.out.println(DecimalPoint);

        display.setText(display.getText() + DecimalPoint);


    }

}
