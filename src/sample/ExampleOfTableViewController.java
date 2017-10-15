package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class ExampleOfTableViewController implements Initializable{

    //configure the table
    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, LocalDate> birthdayColumn;

    //Add person example
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private DatePicker birthdayDatePicker;
    /**
     * This method will allow the user to double click on a cell
     * and update the first name of the person
     */
    public void changeFirstNameEvent(TableColumn.CellEditEvent editedCell) {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setFirstName(editedCell.getNewValue().toString());

    }
    public void changeLastNameEvent(TableColumn.CellEditEvent editedCell) {
        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setLastName(editedCell.getNewValue().toString());

    }

    /**
     * Change scene button
     */
    public void changeSceneButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set up the columns in the table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("birthday"));

        //Loads Dummy data
        tableView.setItems(getPeople());

        //Update the table to allow editing of columns
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }


    /**
     * Method will create a new person and add to table
     */
    public void newPersonButtonPushed() {
        Person newPerson = new Person(firstNameTextField.getText(), lastNameTextField.getText(), birthdayDatePicker.getValue());

        //Get all items from table as a list, then add person to the list
        tableView.getItems().add(newPerson);
    }
    /**
     * Method will return an ObservableList of People objects
     */

    public ObservableList<Person> getPeople() {

        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("Frank","Sinatra", LocalDate.of(1815, Month.DECEMBER, 12)));
        people.add(new Person("Rebecca","Ferguason", LocalDate.of(1986, Month.JULY, 21)));
        people.add(new Person("MR.","T", LocalDate.of(1952, Month.MAY, 21)));

        return people;
    }
}
