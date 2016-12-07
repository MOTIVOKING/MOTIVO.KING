package de.oszimt.fa45.motivoking.ui.fxml;

import de.oszimt.fa45.motivoking.Error;
import de.oszimt.fa45.motivoking.functionality.ProgramLogic;
import de.oszimt.fa45.motivoking.model.Activity;
import de.oszimt.fa45.motivoking.model.Day;
import de.oszimt.fa45.motivoking.ui.GraphicalUserInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by RedCyberSamurai on 22.11.2016.
 *
 * Main Application-Class that will launch the Graphical User Interface
 */
public class App extends Application implements Initializable {

    @FXML
    private TextArea textAreaRelax, textAreaStress, textAreaDescription;
    @FXML
    private ComboBox<Activity> comboBoxAllActivities;
    @FXML
    private Label txtStresslevel, txtRelaxlevel;
    private Stage mPrimaryStage;

    @FXML
    private TableView<Day> tvDates;
    @FXML
    private TableColumn<Day, String> column_date;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button buttonAddDay, buttonCreateActivity, buttonAddActivityToDay;

    @FXML
    private Label labelSelectedDate;
    @FXML
    private TableView<Activity> tvActivities;
    @FXML
    private TableColumn<Activity, String> tcActivity, tcStress, tcRelax;

    private ProgramLogic mProgramLogic;


    /**
     * App constructor
     */
    public App() {
    }


    /**
     * Main JavaFx hook
     * loads the fxml
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
     * hook after fxml is loaded. this initializes the tables in the ui,
     * loads the days from the program-logic and all available activities
     *
     * the selection-change-listener for the days-tableview is set here,
     * also this initializes listeners for the textareas that will only allow correct
     * content when creating new activities and enable or disable the
     * parts of the ui that is neede / not needed
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mProgramLogic = GraphicalUserInterface.getProgramLogic();
        System.out.println("programLogic: " + this.mProgramLogic);

        initDays();
        initActivityTable();
        initActivities();
        tvDates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> labelSelectedDate.setText(newValue.getDate() == null ? "null" : new SimpleDateFormat("dd.MM.yyyy").format(newValue.getDate())));
            refreshDayDetails();
            buttonCreateActivity.setDisable(false);
        });
        textAreaDescription.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\n"))
                textAreaDescription.setText(newValue.replace("\n", ""));
        });
        textAreaRelax.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textAreaRelax.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        textAreaStress.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textAreaStress.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        InvalidationListener listener = observableValue -> {
            buttonCreateActivity.setText(textAreaDescription.getText().isEmpty() || textAreaStress.getText().isEmpty() || textAreaRelax.getText().isEmpty() ? "Abbrechen" : "Speichern");
        };

        textAreaDescription.textProperty().addListener(listener);
        textAreaRelax.textProperty().addListener(listener);
        textAreaStress.textProperty().addListener(listener);
    }

    /**
     * fills the combobox with all known activities
     */
    private void initActivities() {
        ObservableList<Activity> a = FXCollections.observableArrayList(mProgramLogic.getAllActivities());
        comboBoxAllActivities.setItems(a);
        comboBoxAllActivities.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            buttonAddActivityToDay.setDisable(newValue == null);
        });
    }

    /**
     * sets the CellValueFactories for the activity table.
     * this is neede to only set all items to the tableview and it fills itself
     */
    private void initActivityTable() {
        tcActivity.setCellValueFactory(item -> new SimpleStringProperty(item.getValue().getName()));
        tcRelax.setCellValueFactory(item -> new SimpleStringProperty(Integer.toString(item.getValue().getRelaxLevel())));
        tcStress.setCellValueFactory(item -> new SimpleStringProperty(Integer.toString(item.getValue().getStressLevel())));
    }


    /**
     * load the  fxml
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
     * loads all days and shows them inside the tableview
     */
    private void initDays() {
        ObservableList<Day> observableDays = FXCollections.observableArrayList(mProgramLogic.getDays());
        System.out.println(observableDays.toString());
        tvDates.setItems(observableDays);
        column_date.setCellValueFactory(param -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            return new SimpleStringProperty(simpleDateFormat.format(param.getValue().getDate()));
        });
    }


    /**
     * FXML-Eventhandler that fires when the user clicks the button to add a day.
     * the controller will enable the the datepicker if it is not still enabled
     * disable it if it is enabled but no date is selected or create the new day
     * if any day is chosen in the Datepicker.
     *
     * @throws ParseException
     */
    @FXML
    private void onAddDay() throws ParseException {
        if (datePicker.isDisabled()) {
            datePicker.setDisable(false);
            buttonAddDay.setText("Abbrechen");
            datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                buttonAddDay.setText(newValue == null ? "Abbrechen" : "Speichern");
            });
        } else {
            if (datePicker.getValue() != null) {
                if (!dayExisting(new SimpleDateFormat("yyyy-MM-dd").parse(datePicker.getValue().toString())))
                    this.mProgramLogic.createDay(datePicker.getValue().toString());
                else {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Tag vorhaden");
                    error.setHeaderText("Tag bereits vorhanden");
                    error.setContentText("Ein Eintrag für diesen Tag existiert bereits und kann nicht doppelt " +
                            "angelegt werden");
                    error.showAndWait();
                }
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

    /**
     * this method checks if a selected date already exists in the lsit of days to avoid
     * double entries.
     *
     * @param date - the selected date that should be checked
     * @return true if any entry for the same day is existig or false if not
     */
    private boolean dayExisting(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Day day : mProgramLogic.getDays()) {
            System.out.println(simpleDateFormat.format(date) + " - " + simpleDateFormat.format(day.getDate()));
            if (simpleDateFormat.format(date).equals(simpleDateFormat.format(day.getDate())))
                return true;
        }
        return false;
    }


    /**
     * FXML Eventhandler that fires if the user selected a activity and clicked the button
     * to add it to any day. it is not neccessary to check if any activity is selected
     * because the button will only be enabled if any is selected.
     */
    @FXML
    private void onAddActivity() {
        mProgramLogic.addActivity(tvDates.getSelectionModel().getSelectedItem().getId(), comboBoxAllActivities.getSelectionModel().getSelectedItem().getId());
        refreshDayDetails();
    }

    /**
     * FXML Eventhandler that fires if the user clicks to create a new activity
     * if the input fiels are disabled this will enable them
     * if not all fiels are filled it will cancel creating the new activity
     * and if all input fields are filled it will give the data to the programlogic to
     * finish creating the activity
     */
    @FXML
    private void onCreateActivity() {
        if (textAreaDescription.isDisabled()) {
            textAreaDescription.setDisable(false);
            textAreaStress.setDisable(false);
            textAreaRelax.setDisable(false);
            buttonCreateActivity.setText("Abbrechen");
//            datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
//                buttonAddDay.setText(newValue == null ? "Abbrechen" : "Speichern");
//            });
        } else {
            if (buttonCreateActivity.getText().equals("Abbrechen")) {
            } else {
                mProgramLogic.createActivity(tvDates.getSelectionModel().getSelectedItem().getId(), new Activity(textAreaDescription.getText(), Integer.parseInt(textAreaStress.getText()), Integer.parseInt(textAreaRelax.getText())));
            }
            textAreaDescription.setText("");
            textAreaStress.setText("");
            textAreaRelax.setText("");
            textAreaDescription.setDisable(true);
            textAreaStress.setDisable(true);
            textAreaRelax.setDisable(true);
            buttonCreateActivity.setText("Aktivität erstellen");
            refreshDayDetails();
        }
    }

    /**
     * if an entry for a day is changed or the user selected another day
     * this method will refresh all details that are shown for the selected day
     */
    private void refreshDayDetails() {
        Day day = mProgramLogic.getDay(tvDates.getSelectionModel().getSelectedItem().getId());
        ObservableList<Activity> a = FXCollections.observableArrayList(mProgramLogic.getActivities(day.getId()));
        tvActivities.setItems(a);
        int stress = 0;
        int relax = 0;
        for (Activity activity : a) {
            stress = stress + activity.getStressLevel();
            relax = relax + activity.getRelaxLevel();
        }
        txtRelaxlevel.setText(Integer.toString(relax));
        txtStresslevel.setText(Integer.toString(stress));
    }
}