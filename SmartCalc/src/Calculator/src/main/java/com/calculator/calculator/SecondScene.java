package com.calculator.calculator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class SecondScene {
    private final String inputString;

    public SecondScene (String inputString) {
        this.inputString = inputString;
    }

    public void init() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcApplication.class.getResource("second.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        scene.setFill(Color.TRANSPARENT);
        CalcController control2 = fxmlLoader.getController();
        Stage newWindow = new Stage();
        try(InputStream iconStream = getClass().getResourceAsStream("icons8-calculator-64.png")) {
            Image image = new Image(Objects.requireNonNull(iconStream));
            newWindow.getIcons().add(image);
        } catch (Exception e) {
            control2.getLblOut().setText("Error, image not found");
        }
        newWindow.setScene(scene);
        newWindow.initStyle(StageStyle.TRANSPARENT);
        newWindow.setResizable(false);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        control2.setBuf(inputString);
        pushButton(control2, newWindow);
        newWindow.show();
    }

    private void pushButton(@NotNull CalcController control, Stage stage) {
        control.turnWindow(stage);
        control.closeWindow(stage);
    }

}
