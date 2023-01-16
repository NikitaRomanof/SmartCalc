package com.calculator.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class CalcApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalcApplication.class.getResource("calc-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        scene.setFill(Color.TRANSPARENT);
        CalcController control = fxmlLoader.getController();
        try(InputStream iconStream = getClass().getResourceAsStream("icons8-calculator-64.png")) {
            Image image = new Image(Objects.requireNonNull(iconStream));
            stage.getIcons().add(image);
        } catch (Exception e) {
            control.getLblOut().setText("Error, image not found");
        }
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        pushButton(control, stage);
        stage.show();
    }

    private void pushButton(@NotNull CalcController control, Stage stage) {
        control.turnWindow(stage);
        control.closeWindow(stage);
        control.minimizeWindow(stage);
        control.pusButton(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}