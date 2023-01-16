package com.calculator.calculator;

import com.calculator.calculator.Controller.ModelController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URLDecoder;
import java.util.Objects;
import java.util.Optional;

public class CalcController {
    @FXML
    private ImageView btnClose, btnMinimize;
    private File history;
    @FXML
    private Pane titlePane;
    @FXML
    private Label lblResult, lblOut;
    private double x, y;
    private String tmp = "", buf = "";
    private final ModelController modelController;
    public CalcController() {
        modelController = new ModelController();
    }
    private int window = 1;

    public void setBuf(String buf) {
       this.buf = buf;
       lblOut.setText(buf + "=");
       window = 2;
    }

    public Label getLblOut() {return lblOut;}

    public void turnWindow(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    public void pusButton(Stage stage) {
        stage.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode().getName().equals("Enter")) {
                onClickedEquals();
            } else {
                switch (keyEvent.getText().trim()) {
                    case ("0") -> eventNumber("0");
                    case ("1") -> eventNumber("1");
                    case ("2") -> eventNumber("2");
                    case ("3") -> eventNumber("3");
                    case ("4") -> eventNumber("4");
                    case ("5") -> eventNumber("5");
                    case ("6") -> eventNumber("6");
                    case ("7") -> eventNumber("7");
                    case ("8") -> eventNumber("8");
                    case ("9") -> eventNumber("9");
                    case ("(") -> clickedEventToString("OpenBr");
                    case (")") -> clickedEventToString("CloseBr");
                    case (".") -> clickedEventToString("Dot");
                    case ("^") -> clickedEventToString("Pow");
                    case ("-") -> clickedEventToString("Minus");
                    case ("+") -> clickedEventToString("Plus");
                    case ("*") -> clickedEventToString("Mul");
                    case ("/") -> clickedEventToString("Div");
                    case ("%") -> clickedEventToString("Mod");
                    default -> {
                    }
                }
            }
        });
    }

    public void closeWindow(Stage stage) {
        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
    }

    public void minimizeWindow(Stage stage) {
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    private void setHistory() throws IOException {
        String testStr = new String(URLDecoder.decode(String.valueOf(getClass().getResource("calc-view.fxml")), "UTF-8").getBytes("UTF-8"));
        testStr = testStr.substring(testStr.indexOf("/"), testStr.indexOf("Calculator.jar!"));
        history = new File(testStr + "history.txt");
        if (!history.isFile()) history.createNewFile();
    }


    private void saveHistory(String functions) {
        try {
            setHistory();
            try(FileWriter writer = new FileWriter(history, true);
                BufferedWriter wr = new BufferedWriter(writer)) {
                wr.write("\n");
                wr.write(functions);
            } catch (Exception e) {
                lblOut.setText("Error in saveHistory");
            }
        } catch (IOException e) {
            lblOut.setText("Error in saveHistory");
        }
    }

    @FXML
    private void clearHistory()  {
        try {
            setHistory();
            try(FileWriter writer = new FileWriter(history, false);
                BufferedWriter wr = new BufferedWriter(writer)) {
                wr.write("0.0");
            } catch (Exception e) {
                lblOut.setText("Error in clearHistory(), file not found");
            }
        } catch (IOException e) {
            lblOut.setText("Error in clearHistory(), file not found");
        }
    }

    @FXML
    private void getHistory() {
        try {
            setHistory();
            try(FileReader reader = new FileReader(history);
                BufferedReader br = new BufferedReader(reader)) {
                String line;
                ObservableList<String> tableData = FXCollections.observableArrayList();
                TableView<String> tableView = new TableView<>(tableData);
                TableColumn<String, String> tc1 = new TableColumn<>("History");
                tc1.setCellValueFactory(param -> new SimpleStringProperty(param.getValue()));
                tableView.getColumns().add(tc1);
                tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                tableView.getSelectionModel().getSelectedCells().addListener(this::selectCells);

                while ((line = br.readLine()) != null) {
                    tableData.add(line);
                }

                Stage stage = new Stage();
                Scene scene = new Scene(tableView);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (Exception e) {
                lblOut.setText("Error in getHistory(), file not found");
            }
        } catch (IOException e) {
            lblOut.setText("Error in getHistory(), file not found");
        }
    }

    private void selectCells(ListChangeListener.Change<? extends TablePosition> c) {
        final String[] dataCell = new String[1];
        c.getList().forEach(tablePosition -> dataCell[0] = (String)tablePosition.getTableColumn().getCellData(tablePosition.getRow()));
        if (dataCell[0] != null) {
            tmp = dataCell[0];
            lblResult.setText(tmp);
        }
    }

    @FXML
    private void getInfo() {
        String info = "     \n" +
                "           Getting Started Guide\n" +
                "           This guide will give you informative instructions on how to use this calculator effectively.\n" +
                "       Chapter 1: Mouse Input\n" +
                "           Press the buttons to enter a mathematical formula or equation, just like on a pocket calculator.\n" +
                "       Chapter 2: Keyboard Input\n" +
                "           If you prefer keyboard input, math formulas can be entered directly in the input line.\n" +
                "       Chapter 3: Functions\n" +
                "           To evaluate a function as \"sine\" with an argument, enter the appropriate function name followed by the argument 90 in parentheses. Example: sin(90)\n" +
                "       Chapter 4: Mathematical formulas containing X\n" +
                "           If the formula contains an unknown component, the calculator will prompt you to select one of the 2 modes of operation.\n" +
                "           In the first mode, the user will be prompted to enter the value of the unknown, then the calculation will be made according to the given formula.\n" +
                "           In the second mode, a graph of the function is built, the user will be prompted to enter the initial and final coordinates on the X axis,\n" +
                "           as well as the step size by which the X coordinate increases. Default parameters: -10, 10, 0.1 \n";

        AnchorPane inf = new AnchorPane();
        Label lbl = new Label(info);
        lbl.setStyle("-fx-text-fill: white;");
        inf.getChildren().add(lbl);
        inf.setStyle("-fx-background-color: #222;");
        Stage stage = new Stage();
        Scene scene = new Scene(inf);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    void onClickedAC() {
        tmp = "";
        lblResult.setText("0.0");
        if (window == 2) {
            lblOut.setText(buf + "=");
        } else {
            lblOut.setText(tmp);
        }
    }


    @FXML
    void onClickedNumber(MouseEvent event) {
        eventNumber(((Pane) event.getSource()).getId().replace("btn", ""));
    }

    private void eventNumber(String num) {
        tmp = tmp + num;
        lblResult.setText(tmp);
    }


    @FXML
    void onClickedDel() {
        if (tmp.length() > 1) {
            if (tmp.substring(tmp.length() - 2).equals("ln")) {
                tmp = tmp.substring(0, tmp.length() - 2);
            } else if (tmp.length() >= 4 && (tmp.substring(tmp.length() - 4).equals("asin") ||
                    tmp.substring(tmp.length() - 4).equals("acos") ||
                    tmp.substring(tmp.length() - 4).equals("sqrt") ||
                    tmp.substring(tmp.length() - 4).equals("atan"))) {
                tmp = tmp.substring(0, tmp.length() - 4);
            } else if (tmp.length() >= 3 && (tmp.substring(tmp.length() - 3).equals("sin") ||
                       tmp.substring(tmp.length() - 3).equals("cos") ||
                       tmp.substring(tmp.length() - 3).equals("tan") ||
                       tmp.substring(tmp.length() - 3).equals("mod") ||
                       tmp.substring(tmp.length() - 3).equals("log"))) {
                tmp = tmp.substring(0, tmp.length() - 3);
            } else {
                tmp = tmp.substring(0, tmp.length() - 1);
            }
        } else if (tmp.length() == 1) {
            tmp = "";
        }

        if (tmp.length() > 0) {
            lblResult.setText(tmp);
        } else {
            lblResult.setText("0.0");
        }

    }

    private void callAlert() throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("SmartCalc v.3.0");
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(Objects.requireNonNull(this.getClass().getResource("icons8-calculator-64.png")).toString()));
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setHeaderText(null);
        dialog.setContentText("""
                Input line contain "x"\s
                If you want to:
                calculate result - press Calc\s
                build graph - press Graph""");
        ButtonType buttonTypeGraph = new ButtonType("Graph");
        ButtonType buttonTypeCalculate = new ButtonType("Calc");
        dialog.getDialogPane().getButtonTypes().setAll(buttonTypeGraph, buttonTypeCalculate);
        dialog.getDialogPane().getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("StyleDialog.css")).toExternalForm());
        dialog.getDialogPane().getStyleClass().add("StyleDialog");

        Optional<ButtonType> option = dialog.showAndWait();
        if (option.isPresent() && option.get() == buttonTypeGraph) {
            try {
                GraphScene graph = new GraphScene(tmp);
                graph.initGraph();
            } catch (Exception e) {
                lblOut.setText(e.getMessage());
            }

        } else if (option.isPresent() && option.get() == buttonTypeCalculate) {
            SecondScene second = new SecondScene(tmp);
            second.init();
        }
    }

    @FXML
    void onClickedEquals() {
        if (tmp.length() == 0) tmp = "0.0";
        if (window == 1 && tmp.contains("x")) {
            try {
                saveHistory(tmp);
            } catch (Exception ignored) {
            }
            try {
                callAlert();
            } catch (IOException e) {
                lblOut.setText("Unknown error");
            }
        } else if (window == 2) {
            tmp = buf.replaceAll("x", tmp);
            String result = modelController.calculation(tmp);
            result = buf + "=" + result;
            lblOut.setText(result);
            tmp = "";
        } else {
            try {
                saveHistory(tmp);
            } catch (Exception ignored) {
            }
            String result = modelController.calculation(tmp);
            lblOut.setText(result);
            tmp = "";
        }
    }

    private boolean dotCheck(String tmp) {
        if (tmp.length() > 0) {
            char[] buf = tmp.toCharArray();
            int countDot = 0;

            for (char c : buf) {
                if (c == '.') {
                    countDot = 1;
                } else if (!(c >= '0' && c <= '9')) {
                    countDot = 0;
                }
            }
            return countDot == 0 && (buf[buf.length - 1] >= '0' && buf[buf.length - 1] <= '9');

        } else {
            return false;
        }
    }

    private boolean checkMulDiv() {
        if (tmp.length() > 0) {
            return (tmp.charAt(tmp.length() - 1) >= '0' && tmp.charAt(tmp.length() - 1) <= '9') ||
                    tmp.charAt(tmp.length() - 1) == ')' || tmp.charAt(tmp.length() - 1) == 'x';
        } else {
            return false;
        }
    }

    private boolean checkFunctions() {
        if (tmp.length() == 0) return true;
        return tmp.charAt(tmp.length() - 1) == 'x' || tmp.charAt(tmp.length() - 1) == '+' ||
                    tmp.charAt(tmp.length() - 1) == '-' || tmp.charAt(tmp.length() - 1) == '*' ||
                    tmp.charAt(tmp.length() - 1) == '/' || tmp.charAt(tmp.length() - 1) == 'm' ||
                    tmp.charAt(tmp.length() - 1) == '^' || tmp.charAt(tmp.length() - 1) == '(';
    }

    private boolean checkBrackets(char bracket) {
        if (tmp.length() > 0) {
            char check = tmp.charAt(tmp.length() - 1);
            if (bracket == '(') {
                return (check < '0' || check > '9') && check != ')' && check != 'x';
            } else {
                return (check >= '0' && check <= '9') || (check == ')') || (check == 'x');
            }
        } else return bracket == '(';
    }

    private void clickedEventToString(String eventString) {
        switch (eventString) {
            case ("Dot") -> {if (dotCheck(tmp))tmp = tmp + ".";}
            case ("Plus") -> tmp = tmp + "+";
            case ("Minus") -> tmp = tmp + "-";
            case ("Mul") -> { if (checkMulDiv()) tmp = tmp + "*";}
            case ("Div") -> { if (checkMulDiv()) tmp = tmp + "/";}
            case ("Mod") -> { if (checkMulDiv()) tmp = tmp + "mod";}
            case ("OpenBr") -> { if (checkBrackets('(')) tmp = tmp + "(";}
            case ("CloseBr") -> { if (checkBrackets(')')) tmp = tmp + ")";}
            case ("Pow") -> { if (checkMulDiv()) tmp = tmp + "^";}
            case ("Sin") -> { if (checkFunctions()) tmp = tmp + "sin";}
            case ("Cos") -> { if (checkFunctions()) tmp = tmp + "cos";}
            case ("Tan") -> { if (checkFunctions()) tmp = tmp + "tan";}
            case ("Asn") -> { if (checkFunctions()) tmp = tmp + "asin";}
            case ("Acs") -> { if (checkFunctions()) tmp = tmp + "acos";}
            case ("Atn") -> { if (checkFunctions()) tmp = tmp + "atan";}
            case ("Ln") -> { if (checkFunctions()) tmp = tmp + "ln";}
            case ("Log") -> { if (checkFunctions()) tmp = tmp + "log";}
            case ("Sqrt") -> { if (checkFunctions()) tmp = tmp + "sqrt";}
            case ("Exp") -> {
                if (tmp.length() == 0) {
                    tmp = tmp + "2.7182818284590452";
                } else {
                    if (dotCheck(tmp + '2')) {
                        tmp = tmp + "2.7182818284590452";
                    }
                }
            }
            case ("Pi") -> {
                if (tmp.length() == 0) {
                    tmp = tmp + "2.7182818284590452";
                } else {
                    if (dotCheck(tmp + '3')) {
                        tmp = tmp + "3.1415926535897932";
                    }
                }
            }
            case ("X") -> tmp = tmp + "x";
            default -> {}
        }
        if (tmp.length() > 0) lblResult.setText(tmp);

    }

    @FXML
    void onClickedChar(MouseEvent event) {
        String buff = ((Pane) event.getSource()).getId().replace("btn", "");
        clickedEventToString(buff);
    }

}