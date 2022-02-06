package sample;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.*;
import sample.Moon;

public class FindMoonPhase extends Application {

    //Text Fields
    private TextField yearAmountTF = new TextField();
    private TextField monthAmountTF = new TextField();
    private TextField dayAmountTF = new TextField();
    private TextField phaseTF = new TextField();

    //Buttons
    private Button calculateButton = new Button("Calculate");
    private Button clearButton = new Button("Clear");

    //Variable for Image
    public int imageChoice = 0;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        yearAmountTF.setEditable(true);
        monthAmountTF.setEditable(true);
        dayAmountTF.setEditable(true);
        yearAmountTF.setFocusTraversable(false);
        monthAmountTF.setFocusTraversable(false);
        dayAmountTF.setFocusTraversable(false);

        //Panes + image display
        File newImage = new File("C:moonPhases.png");
        Image moonImage = new Image(newImage.toURI().toString());
        //Image moonImage = new Image(new File("file").toURI().toString());

        ImageView moonPic = new ImageView();
        moonPic.setFitWidth(400);
        moonPic.setFitHeight(300);
        moonPic.setImage(moonImage);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        gridPane.add(moonPic, 1,0);

        gridPane.add(new Label("Year:"), 0, 2);
        gridPane.add(yearAmountTF, 1, 2);

        gridPane.add(new Label("Month:"), 0, 4);
        gridPane.add(monthAmountTF, 1, 4);

        gridPane.add(new Label("Day:"), 0, 6);
        gridPane.add(dayAmountTF, 1, 6);

        gridPane.add(new Label("Moon Phase:"), 0, 8);
        gridPane.add(phaseTF, 1, 8);

        HBox hBox = new HBox();
        hBox.setSpacing(10);

        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().add(clearButton);
        hBox.getChildren().add(calculateButton);

        gridPane.add(hBox, 1, 7);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(calculateButton, HPos.RIGHT);
        GridPane.setHalignment(clearButton, HPos.RIGHT);

        yearAmountTF.setAlignment(Pos.BOTTOM_RIGHT);
        monthAmountTF.setAlignment(Pos.BOTTOM_RIGHT);
        dayAmountTF.setAlignment(Pos.BOTTOM_RIGHT);
        phaseTF.setAlignment(Pos.BOTTOM_RIGHT);
        clearButton.setOnAction(event -> clear());

        //try catch if value entered was not a number
        try {
            calculateButton.setOnAction(event -> calculate());
        } catch (NumberFormatException nfe) {
            clear();
            primaryStage.setTitle("Invalid input! Please enter number value!");
        }

        if (imageChoice == 0) {
            newImage = new File("C:moonPhases.png");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else if (imageChoice == 1) {
            newImage = new File("C:NewMoon.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else if (imageChoice == 2) {
            newImage = new File("C:WaningCrescent.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else if (imageChoice == 3) {
            newImage = new File("C:ThirdQuarter.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else if (imageChoice == 4) {
            newImage = new File("C:WaningGibbous.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else if (imageChoice == 5) {
            newImage = new File("C:FullMoon.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else if (imageChoice == 6) {
            newImage = new File("C:WaxingGibbous.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else if (imageChoice == 7) {
            newImage = new File("C:FirstQuarter.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else if (imageChoice == 8) {
            newImage = new File("C:WaxingCrescent.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        } else {
            newImage = new File("C:FullMoon.jpg");
            moonImage = new Image(newImage.toURI().toString());
            moonPic.setImage(moonImage);
        }


        //-------------------Set Scene--------------------
        Scene mainScene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Moon Phase Calculator");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    //--------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------

    public void calculate() {
        calculateFunctions();
    }

    //Accesses the Moon class and creates a new moon
    public void calculateFunctions() {
        int year = Integer.parseInt(yearAmountTF.getText());
        int month = Integer.parseInt(monthAmountTF.getText());
        int day = Integer.parseInt(dayAmountTF.getText());

        Moon newMoon = new Moon(year, month, day);

        String phase = newMoon.allFunctions(year, month, day);

        System.out.println(phase);
        phaseTF.setText(phase);

        //Checks image choice
        checkImageChoice(phase);

        System.out.println(imageChoice);
    }

    public void checkImageChoice(String phase) {
        if(phase == "New Moon") {
            imageChoice = 1;
        } else if(phase == "Waning Crescent") {
            imageChoice = 2;
        } else if(phase == "Third Quarter") {
            imageChoice = 3;
        } else if(phase == "Waning Gibbous") {
            imageChoice = 4;
        } else if(phase == "Full Moon / Gibbous") {
            imageChoice = 5;
        } else if(phase == "Waxing Gibbous") {
            imageChoice = 6;
        } else if(phase == "First Quarter") {
            imageChoice = 7;
        }  else if(phase == "Waxing Crescent") {
            imageChoice = 8;
        } else {
            imageChoice = 5;
        }
    }

    //--------------------------------------------------------------------------------------------------------------
    //--------------------------------------------------------------------------------------------------------------

    public void clear() {
        yearAmountTF.clear();
        monthAmountTF.clear();
        dayAmountTF.clear();
        phaseTF.clear();
    }

    //Inner Class
    class clearHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent arg0) {
            clear();
        }

    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }

}