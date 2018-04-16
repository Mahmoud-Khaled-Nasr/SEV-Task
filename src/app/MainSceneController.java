package app;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainSceneController {
    @FXML private TableView resultsTable;
    @FXML private TableColumn timeCol, speedCol, accCol, currentCol, powerCol;
    @FXML private TextField fileNameTextField;
    @FXML private Label averageSpeed, averagePower;
    private List<Record> records;
    private static int CHECKING_TIME_INTERVALS = 3;

    public MainSceneController() {
        this.records = new LinkedList<>();
    }

    @FXML private void getFile (){
        if (readFileData(fileNameTextField.getText())){
            populateResultsTable();
            populateAverages();
            PauseTransition wait = new PauseTransition(Duration.seconds(CHECKING_TIME_INTERVALS));
            wait.setOnFinished((e) -> {
                readFileData(fileNameTextField.getText());
                populateResultsTable();
                populateAverages();
                wait.playFromStart();
            });
            wait.play();
        }
    }

    private void populateAverages() {
        double speedSum = 0, powerSum = 0;
        for (Record record : records){
            speedSum += Double.parseDouble(record.getSpeed());
            powerSum += Double.parseDouble(record.getPower());
        }
        averagePower.setText(String.valueOf(powerSum / records.size()));
        averageSpeed.setText(String.valueOf(speedSum / records.size()));
    }

    private void populateResultsTable(){
        ObservableList<Record> data = FXCollections.observableArrayList();
        data.addAll(records);

        timeCol.setCellValueFactory(
                new PropertyValueFactory<Record, String>("time")
        );
        speedCol.setCellValueFactory(
                new PropertyValueFactory<Record, String>("speed")
        );
        accCol.setCellValueFactory(
                new PropertyValueFactory<Record, String>("acc")
        );
        currentCol.setCellValueFactory(
                new PropertyValueFactory<Record, String>("current")
        );
        powerCol.setCellValueFactory(
                new PropertyValueFactory<Record, String>("power")
        );

        resultsTable.setItems(data);
    }

    private boolean readFileData (String fileName){
        Scanner scanner;
        try {
            scanner = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("file not");
            alert.setContentText("Please make sure that the file exists in the same directory as the JAR and that" +
                    "you entered the name of the file correctly ex. \"file name.txt\"");
            alert.show();
            return false;
        }
        records.clear();
        scanner.nextLine();
        while (scanner.hasNext()) {
            double time = scanner.nextDouble();
            double speed = scanner.nextDouble();
            double a = scanner.nextDouble();
            double current = scanner.nextDouble();
            double power = scanner.nextDouble();
            records.add(new Record(String.valueOf(time), String.valueOf(speed)
                    , String.valueOf(a), String.valueOf(current),String.valueOf(power)));
        }
        return true;
    }
}
