package de.oszimt.fa45.motivoking.ui.fxml;

import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.ui.GraphicalUserInterface;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by RedCyberSamurai on 22.11.2016.
 */
public class App extends Application implements Initializable {

    private Stage mPrimaryStage;

    @FXML
    private TableView<Day> tvDates;
    @FXML
    private TableColumn<Day, String> column_date;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button buttonAddDay;

    @FXML private Label labelSelectedDate;
    @FXML private TableView<Activity> tvActivities;
    @FXML private TableColumn<Activity, String> tcActivity, tcStress, tcRelax;

    private ProgramLogic mProgramLogic;


    /**
     * App constructor, initializing tables
     */
    public App() {
    }


    /**
     * Main JavaFx hook
     *
     * @param tPrimaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage tPrimaryStage) throws Exception {
        System.out.println("App::start");
        mPrimaryStage = tPrimaryStage;

        initRootLayout();
    }


    /**
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mProgramLogic = GraphicalUserInterface.getProgramLogic();
        System.out.println("programLogic: " + this.mProgramLogic);

        initDays();
        tvDates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            labelSelectedDate.setText(newValue.getDate() == null ? "null" : newValue.getDate().toString());
//            tvActivities.setItems(newValue.get);
        });
    }


    /**
     *
     */
    private void initRootLayout() {
        System.out.println(getClass());
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("GraphicalUserInterface.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            mPrimaryStage.setScene(scene);
            mPrimaryStage.setTitle("MOTIVO.KING");
            mPrimaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    private void initDays() {
        ObservableList<Day> observableDays = FXCollections.observableArrayList(mProgramLogic.getDays());
        System.out.println(observableDays.toString());

        observableDays.add(new Day(new Date()));

        System.out.println(observableDays.toString());
        tvDates.setItems(observableDays);
        column_date.setCellValueFactory(param -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return new SimpleStringProperty(simpleDateFormat.format(param.getValue().getDate()));
        });
    }


    // ---------------------------------------------------------
    // NOTE: How about separate controllers??? (wall of text...)


    @FXML
    private void onAddDay(ActionEvent actionEvent) {
        if (datePicker.isDisabled()) {
            datePicker.setDisable(false);
            buttonAddDay.setText("Abbrechen");
            datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                buttonAddDay.setText(newValue == null ? "Abbrechen" : "Speichern");
            });
        } else {
            if (datePicker.getValue() != null) {
                this.mProgramLogic.createDay(datePicker.getValue().toString());
                datePicker.setValue(null);
                datePicker.setDisable(true);
            } else {
                datePicker.setDisable(true);
            }
            buttonAddDay.setText("Tag hinzufügen");
            initDays();
            Error.print();
        }
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
