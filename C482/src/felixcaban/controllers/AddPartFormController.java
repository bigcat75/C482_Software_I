package felixcaban.controllers;

import felixcaban.data.DataManager;
import felixcaban.models.InHouse;
import felixcaban.models.Inventory;
import felixcaban.models.Outsourced;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * 
 * FXML Controller class for AddPartForm.fxml
 *
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public class AddPartFormController implements Initializable 
{
    
    @FXML
    private RadioButton rbInHouse;
    @FXML
    private ToggleGroup PartType;
    @FXML
    private RadioButton rbOutsourced;
    @FXML
    private Label lblAddModifyVariable;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtInv;
    @FXML
    private TextField txtPriceCost;
    @FXML
    private TextField txtMax;
    @FXML
    private TextField txtMin;
    @FXML
    private TextField txtAddModifyVariable;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;
    @FXML
    private ListView<String> lstErrorList;
    

    /**
     * 
     * Initializes the controller class.
     * @param url an optional URL that can be passed.
     * @param rb an optional ResourceBundle that can be passed.
     * 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
            
    {
        
        lblAddModifyVariable.setText("Machine ID");
        txtId.setPromptText("Auto Gen-Disabled");
        txtId.setDisable(true);        
        
    }    

    /**
     * 
     * Handles the radio button for {@link felixcaban.models.InHouse InHouse} parts being added to inventory.
     * 
     * @param event the event to handle.
     * 
     */
    @FXML
    private void handleRbInHouseAction(ActionEvent event) 
    {
        
        lblAddModifyVariable.setText("Machine ID");

    }

    /**
     * 
     * Handles the radio button for {@link felixcaban.models.Outsourced Outsourced} parts being added to inventory.
     * 
     * @param event the event to handle.
     * 
     */
    @FXML
    private void handleRbOutsourcedAction(ActionEvent event) 
    {
        
        lblAddModifyVariable.setText("Company Name");
        
    }
    
    /**
     * 
     * Handles the button to cancel adding the {@link felixcaban.models.Part Part} to inventory.
     * 
     * @param event the event to handle.
     * @throws IOException 
     * 
     */
    @FXML
    private void handleBtnCancelAction(ActionEvent event) throws IOException
    {
        
        returnToMainForm(event);
        
    }
    
    /**
     * 
     * Handles the button to save the entered {@link felixcaban.models.Part Part} being added to inventory.
     * 
     * @param event the event to handle.
     * 
     */
    @FXML
    private void handleBtnSaveAction(ActionEvent event)
    {
        
        try
        {            
            
            if(rbInHouse.isSelected())
            {

                Inventory.addPart(new InHouse(
                        DataManager.getNextPartId(), 
                        txtName.getText(), 
                        Double.parseDouble(txtPriceCost.getText()), 
                        Integer.parseInt(txtInv.getText()), 
                        Integer.parseInt(txtMin.getText()), 
                        Integer.parseInt(txtMax.getText()), 
                        Integer.parseInt(txtAddModifyVariable.getText())
                ));

            }

            if(rbOutsourced.isSelected())
            {

               Inventory.addPart(new Outsourced(
                       DataManager.getNextPartId(), 
                       txtName.getText(), 
                       Double.parseDouble(txtPriceCost.getText()), 
                       Integer.parseInt(txtInv.getText()), 
                       Integer.parseInt(txtMin.getText()), 
                       Integer.parseInt(txtMax.getText()), 
                       txtAddModifyVariable.getText()
               ));   

            } 

            returnToMainForm(event); 
            
        }
        catch(Exception e)
        {                        
            lstErrorList.setItems(validateUserInput());    
        }
            
    }
    
    /**
     * 
     * Method to return to the {@link felixcaban.controllers.MainFormController MainForm}.
     * 
     * @param event the event to handle.
     * @throws IOException 
     * 
     */
    private void returnToMainForm(ActionEvent event) throws IOException
    {
        
        DataManager.incrementPartId();
        
        Parent mainForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/MainForm.fxml"));
        Scene mainFormScene = new Scene(mainForm);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainFormScene);
        window.show();  
        
    }
    
    /**
     * 
     * Returns a list of validation errors as a result of testing UI text box input.
     * 
     * @return a list of validation errors.
     * 
     */
    private ObservableList<String> validateUserInput()
    {        
        
        ObservableList<String> inputErrors = FXCollections.observableArrayList();
        
                
        if(txtName.getText() == null || txtName.getText().isEmpty())
        {

            inputErrors.add("Name is a required field.");

        } 
        
        
        
        if(txtInv.getText() == null || txtInv.getText().isEmpty())
        {

            inputErrors.add("Inv is a required field.");

        }
        else if(!DataManager.isInteger(txtInv.getText()))
        {

            inputErrors.add("Inv must be an integer.");

        }
                
        
        
        if(txtPriceCost.getText() == null || txtPriceCost.getText().isEmpty())
        {

            inputErrors.add("Price/Cost is a required field.");

        }
        else if(!DataManager.isDouble(txtPriceCost.getText()))
        {

            inputErrors.add("Price/Cost must be a decimal or integer.");

        }
        
        

        if(txtMax.getText() == null || txtMax.getText().isEmpty())
        {

            inputErrors.add("Max is a required field.");

        }
        else if(!DataManager.isInteger(txtMax.getText()))
        {

            inputErrors.add("Max must be an integer.");

        }
        
        

        if(txtMin.getText() == null || txtMin.getText().isEmpty())
        {

            inputErrors.add("Min is a required field.");

        }
        else if(!DataManager.isInteger(txtMin.getText()))
        {

            inputErrors.add("Min must be an integer.");
            
        }
        
        
        
        if (rbInHouse.isSelected())
        {

            if(txtAddModifyVariable.getText() == null || txtAddModifyVariable.getText().isEmpty())
            {

                inputErrors.add("Machine Id is a required field.");

            }
            else if(!DataManager.isInteger(txtAddModifyVariable.getText()))
            {

                inputErrors.add("Machine Id must be an integer.");

            }
            
        }
        
        

        if (rbOutsourced.isSelected())
        {

            if(txtAddModifyVariable.getText() == null || txtAddModifyVariable.getText().isEmpty())
            {

                inputErrors.add("Company Name is a required field.");

            }
        
        }  
        
        
        return inputErrors;
       
    }
    
}
