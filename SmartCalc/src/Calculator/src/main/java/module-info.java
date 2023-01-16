module com.calculator.calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires javafx.graphics;


    opens com.calculator.calculator to javafx.fxml;
    exports com.calculator.calculator;
}