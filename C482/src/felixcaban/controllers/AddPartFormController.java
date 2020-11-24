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
 * FXML Controller class for AddPartForm.fxml.
 * The controller for the UI to add a new {@link felixcaban.models.Part Part} 
 * to {@link felixcaban.models.Inventory Inventory}.
 *
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public class AddPartFormController implements Initializable 
{
    
    //================================================================================
    // UI Controls
    //================================================================================
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
    
    
    //================================================================================
    // Properties
    //================================================================================
    
    /**
     * 
     * List of validation errors from the form input.
     * 
     */
    private ObservableList<String> inputErrors = FXCollections.observableArrayList();
    
    
    //================================================================================
    // Initializer
    //================================================================================

    /**
     * 
     * Initializes the controller class. 
     * Sets the initial UI control properties.
     * 
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
    
    
    //================================================================================
    // Methods
    //================================================================================

    /**
     * 
     * Handles the radio button for {@link felixcaban.models.InHouse InHouse} parts being added to inventory. 
     * If selected, a {@link felixcaban.models.InHouse InHouse} part will be created.
     * 
     * @param event on action.
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
     * If selected, a {@link felixcaban.models.Outsourced Outsourced} part will be created.
     * 
     * @param event on action.
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
     * It then returns the user to the {@link felixcaban.controllers.MainFormController MainForm}.
     * 
     * @param event on action.
     * @throws IOException if the user can not be returned to the {@link felixcaban.controllers.MainFormController MainForm}.
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
     * Also perform validation on user input to ensure that all fields are complete and all data types match.
     * 
     * @param event on action.
     * @throws IOException if the user can not be returned to the {@link felixcaban.controllers.MainFormController MainForm}.
     * 
     */
    @FXML
    private void handleBtnSaveAction(ActionEvent event) throws IOException
    {
        
        inputErrors.clear(); 
        validateUserInput();        
        
        if (inputErrors.isEmpty())
        {
            int partId = DataManager.getNextPartId();
            
            if(rbInHouse.isSelected())
            {

                Inventory.addPart(new InHouse(
                        partId, 
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
                       partId, 
                       txtName.getText(), 
                       Double.parseDouble(txtPriceCost.getText()), 
                       Integer.parseInt(txtInv.getText()), 
                       Integer.parseInt(txtMin.getText()), 
                       Integer.parseInt(txtMax.getText()), 
                       txtAddModifyVariable.getText()
               ));   

            } 

            DataManager.incrementPartId();
            returnToMainForm(event); 
        }
        else
        {
            
            lstErrorList.setItems(inputErrors);              
            
        }
            
    }
    
    /**
     * 
     * Returns the user to the {@link felixcaban.controllers.MainFormController MainForm}. 
     * 
     * @param event on action.
     * @throws IOException thrown if the user can not be returned to the {@link felixcaban.controllers.MainFormController MainForm}.
     * 
     */
    private void returnToMainForm(ActionEvent event) throws IOException
    {
           
        Parent mainForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/MainForm.fxml"));
        Scene mainFormScene = new Scene(mainForm);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainFormScene);
        window.show();  
        
    }
    
    /**
     * 
     * Validates the UI text box input. 
     * If errors are found, they are added to the {@link #inputErrors inputError} list.
     * 
     */
    private void validateUserInput()
    {        
                
        int min = 0;
        int max = 0;
        int inv = 0;
                
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
        else
        {
            
            inv = Integer.parseInt(txtInv.getText());
            
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
        else
        {
            
            max = Integer.parseInt(txtMax.getText());
            
        }
        
        

        if(txtMin.getText() == null || txtMin.getText().isEmpty())
        {

            inputErrors.add("Min is a required field.");

        }
        else if(!DataManager.isInteger(txtMin.getText()))
        {

            inputErrors.add("Min must be an integer.");
            
        }
        else
        {
            
            min = Integer.parseInt(txtMin.getText());
            
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
        
        
        if (max < min)
        {
            
            inputErrors.add("Max can not be less than Min.");            
            
        }
        
        
        if (inv > max)
        {
            
            inputErrors.add("Inv can not be greater than Max.");            
            
        }
        
        
        if (inv < min)
        {
            
            inputErrors.add("Inv can not be less than Min.");            
            
        }
        
       
    }
    
}
