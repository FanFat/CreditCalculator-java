package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;


public class Controller {
    @FXML
    private TextField sum;
    @FXML
    private TextField time;
    @FXML
    private TextField percent;

    static double CK = 0;
    static double KP = 0;
    static double PC = 0;
    static LocalDate start;


    public void showDialog(ActionEvent actionEvent) {
        if (sum.getText().isEmpty() || time.getText().isEmpty() || percent.getText().isEmpty()) {
            isWarningWindow(actionEvent, "Вы ввели не правильные данные");
        }
        else if (Double.parseDouble(percent.getText()) > 60 || Double.parseDouble(percent.getText()) <= 0) {
            isWarningWindow(actionEvent, "Не верно введен процент");
        }
        else {
            CK = Double.parseDouble(sum.getText());
            KP = Double.parseDouble(time.getText());
            PC = Double.parseDouble(percent.getText());

            calculateWindow("Аннуитетный", "fxml/annuity.fxml");
        }
    }

    public void showDialogDif(ActionEvent actionEvent) {
        if (sum.getText().isEmpty() || time.getText().isEmpty() || percent.getText().isEmpty()) {
            isWarningWindow(actionEvent, "Вы ввели не правильные данные");
        }
        else if (Double.parseDouble(percent.getText()) > 60 || Double.parseDouble(percent.getText()) <= 0) {
            isWarningWindow(actionEvent, "Не верно введен процент");
        }
        else {
            CK = Double.parseDouble(sum.getText());
            KP = Double.parseDouble(time.getText());
            PC = Double.parseDouble(percent.getText());
            calculateWindow("Дифференцированный", "fxml/differentiated.fxml");
        }
    }


    private void calculateWindow(String title, String resource) {
        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            stage.setTitle(title);
            Scene scene = new Scene(root);
            scene.getStylesheets().add(0, "sample/style/style.css");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void isWarningWindow(ActionEvent actionEvent, String s) {
        Label secondLabel = new Label(s);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, 230, 100);
        Stage newWindow = new Stage();
        newWindow.setTitle("Ошибка");
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
        newWindow.show();
    }

    @FXML
    public void initialize() {
    }
}
