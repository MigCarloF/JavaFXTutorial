package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Controller implements Initializable {

    //These items are for CheckBox example
    @FXML private Label pizzaOrderLabel;
    @FXML private CheckBox pepperoniCheckBox;
    @FXML private CheckBox pineappleCheckBox;
    @FXML private CheckBox baconCheckBox;

    //These items are for the ChoiceBox example
    @FXML private ChoiceBox choiceBox;
    @FXML private Label choiceBoxLabel;

    //These items are for the ComboBox example
    @FXML private ComboBox comboBox;
    @FXML private Label comboBoxLabel;

    //These items are for the radiobutton example
    @FXML private RadioButton rdbtnPhp;
    @FXML private RadioButton rdbtnJava;
    @FXML private RadioButton rdbtnCSharp;
    @FXML private RadioButton rdbtnCpp;
    @FXML private Label lblRadioButton;
    private ToggleGroup favLangToggleGroup;

    //These items are for ListView and TextArea example
    @FXML private ListView listView;
    @FXML private TextArea golfTextArea;


    /**
     * CheckBox Example
     */
    public void pizzaOrderButtonPushed() {
        String order = "Topings are: ";

        if (pineappleCheckBox.isSelected()) {
            order += "\nPineapple";
        }
        if (pepperoniCheckBox.isSelected()) {
            order += "\nPepperoni";
        }
        if (baconCheckBox.isSelected()) {
            order += "\nBacon";
        }
        this.pizzaOrderLabel.setText(order);
    }

    /**
     * Choicebox Example
     */
    public void choiceBoxButtonPressed() {
        choiceBoxLabel.setText("My favorite fruit is:\n" + choiceBox.getValue().toString());
    }

    /**
     * Update ComboBox example
     */
    public void comboBoxUpdated() {
        this.comboBoxLabel.setText("Courses selected:\n" + comboBox.getValue().toString());
    }

    /**
     * Update RadioButton example
     */
    public void radioButtonChanged() {
        String label = "Selected label is:\n";
        if (this.favLangToggleGroup.getSelectedToggle().equals(this.rdbtnCpp)) {
            label += "C++";
        }
        if (this.favLangToggleGroup.getSelectedToggle().equals(this.rdbtnCSharp)) {
            label += "C#";
        }
        if (this.favLangToggleGroup.getSelectedToggle().equals(this.rdbtnPhp)) {
            label += "PHP";
        }
        if (this.favLangToggleGroup.getSelectedToggle().equals(this.rdbtnJava)) {
            label += "Java";
        }
        lblRadioButton.setText(label);
    }

    /**
     * ListView example
     */
    public void listViewButtonPushed() {
        String textAreaString = "";

        ObservableList listofItems = listView.getSelectionModel().getSelectedItems();

        for (Object item: listofItems) {
            textAreaString += String.format("%s%n", (String) item);
        }

        this.golfTextArea.setText(textAreaString);
    }

    /**
     * Change scene button
     */
    public void changeSceneButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExampleOfTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * Initialize Function
     */
    @Override
    public void initialize (URL url, ResourceBundle rb) {
        pizzaOrderLabel.setText("");

        //Items for ChoiceBox
        choiceBoxLabel.setText("");
        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Bananas");
        choiceBox.getItems().add("Oranges");
        choiceBox.getItems().addAll("Pears", "Strawberries", "Avocado");
        choiceBox.setValue("Apples");

        //items for Combo Box
        comboBoxLabel.setText("");
        comboBox.getItems().add("COMP1030");
        comboBox.getItems().addAll("CMSC128", "CMSC130", "CMSC22", "CMSC178");

        //items for Radiobutton
        lblRadioButton.setText("");
        favLangToggleGroup = new ToggleGroup();
        this.rdbtnCpp.setToggleGroup(favLangToggleGroup);
        this.rdbtnCSharp.setToggleGroup(favLangToggleGroup);
        this.rdbtnJava.setToggleGroup(favLangToggleGroup);
        this.rdbtnPhp.setToggleGroup(favLangToggleGroup);

        //items for configuring the ListArea
        listView.getItems().addAll("Golf Balls", "Wedges", "Irons", "Driver", "Putter");
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE); //Allows to select multiple items in list view




    }
}
