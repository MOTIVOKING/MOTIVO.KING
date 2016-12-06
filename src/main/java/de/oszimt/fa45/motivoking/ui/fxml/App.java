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
 */
public class App extends Application implements Initializable {

    @FXML
    private TextArea textAreaRelax, textAreaStress, textAreaDescription;
    @FXML
    private ComboBox<String> comboBoxAllActivities;
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
        initActivityTable();
        initActivities();
        tvDates.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> labelSelectedDate.setText(newValue.getDate() == null ? "null" : new SimpleDateFormat("dd.MM.yyyy").format(newValue.getDate())));
            ObservableList<Activity> activities = FXCollections.observableArrayList(mProgramLogic.getActivities(newValue.getId()));
            tvActivities.setItems(activities);
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

    private void initActivities() {
        ObservableList<String> a = FXCollections.observableArrayList();
        mProgramLogic.getAllActivities().forEach(activity -> a.add(activity.getName()));
        comboBoxAllActivities.setItems(a);
        comboBoxAllActivities.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            buttonAddActivityToDay.setDisable(newValue == null);
        });
    }

    private void initActivityTable() {
        tcActivity.setCellValueFactory(item -> new SimpleStringProperty(item.getValue().getName()));
        tcRelax.setCellValueFactory(item -> new SimpleStringProperty(Integer.toString(item.getValue().getRelaxLevel())));
        tcActivity.setCellValueFactory(item -> new SimpleStringProperty(Integer.toString(item.getValue().getRelaxLevel())));
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
    private void onAddDay(ActionEvent actionEvent) throws ParseException {
        if (datePicker.isDisabled()) {
            datePicker.setDisable(false);
            buttonAddDay.setText("Abbrechen");
            datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
                buttonAddDay.setText(newValue == null ? "Abbrechen" : "Speichern");
            });
        } else {
            if (datePicker.getValue() != null) {
                if (!dayExisting(new SimpleDateFormat("yyyy-mm-dd").parse(datePicker.getValue().toString())))
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

    private boolean dayExisting(Date date) {
        Calendar cDateCalendar = Calendar.getInstance();
        cDateCalendar.setTime(date);
        for (Day day : mProgramLogic.getDays()) {
            Calendar temp = Calendar.getInstance();
            temp.setTime(day.getDate());
            System.out.println("NEW: " + date.toString() + " OLD: " + day.getDate().toString());
            if (cDateCalendar.get(Calendar.YEAR) == temp.get(Calendar.YEAR) &&
                    cDateCalendar.get(Calendar.DAY_OF_YEAR) == temp.get(Calendar.DAY_OF_YEAR)) {
                System.out.println("EXISTING");
                return true;
            }
        }
        return false;
    }


    @FXML
    private void onAddActivity() {
    }

    @FXML
    private void onCreateActivity(ActionEvent actionEvent) {
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
            buttonCreateActivity.setText("Aktivität erstellen");
            initActivities();
        }
    }

    @FXML
    private void selectedActivityChanged(ActionEvent actionEvent) {
    }
}
