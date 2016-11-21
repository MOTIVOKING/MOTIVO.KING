package de.oszimt.fa45.motivoking.ui;

import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.model.Day;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by boerg on 13.10.2016.
 */
public class GraphicalUserInterface extends Application implements UserInterface, Initializable {
    private static ProgramLogic mProgramLogic;

    private Stage primaryStage;
    @FXML private TableView<Day> tv_dates;
    @FXML private TableColumn<Day, String> column_date;


    public GraphicalUserInterface() {
        System.out.println("GUI::construct");
    }


    public static void setProgramLogic(ProgramLogic programLogic) {
        mProgramLogic = programLogic;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("GUI::start");
        this.primaryStage = primaryStage;

        initRootLayout();
        initDays();
    }


    private void initDays() {
        ObservableList<Day> list = FXCollections.observableArrayList();

        list.addAll(mProgramLogic.getDays());
        tv_dates.setItems(list);
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        column_date.setCellValueFactory(
                cellData -> new SimpleStringProperty( cellData.getValue().getDate().toString() )
        );
    }
}
