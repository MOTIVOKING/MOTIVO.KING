package de.oszimt.fa45.motivoking.ui;

import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by boerg on 13.10.2016.
 */
public class GraphicalUserInterface extends Application implements UserInterface {
    private Stage primaryStage;
    private ProgramLogic programLogic;

    public GraphicalUserInterface(ProgramLogic programLogic) {
        this.programLogic = programLogic;

        this.launch("");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Graphical User Interface started");
        this.primaryStage = primaryStage;
        initRootLayout();
    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GraphicalUserInterface.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("MOTIVO.KING");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAddDay(ActionEvent actionEvent) {
        Alert dateInput = new Alert(Alert.AlertType.NONE);
        DialogPane dialogPane = new DialogPane();
        DatePicker datePicker = new DatePicker();
        dialogPane.getChildren().add(datePicker);
        dateInput.setDialogPane(dialogPane);
        dateInput.showAndWait();
    }

    @FXML
    private void onAddAction(ActionEvent actionEvent) {
    }

    @FXML
    private void onRemoveAction(ActionEvent actionEvent) {
    }

    @FXML
    private void onChangeAction(ActionEvent actionEvent) {
    }
}
