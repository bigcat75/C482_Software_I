package felixcaban.controllers;

import felixcaban.data.DataManager;
import felixcaban.models.InHouse;
import felixcaban.models.Inventory;
import felixcaban.models.Outsourced;
import felixcaban.models.Part;
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
 * FXML Controller class for ModifyPartForm.fxml
 *
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public class ModifyPartFormController implements Initializable 
{
    
    @FXML
    private ToggleGroup PartType;
    @FXML
    private RadioButton rbInHouse;
    @FXML
    private RadioButton rbOutsourced;
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
    private Label lblAddModifyVariable;
    @FXML
    private TextField txtAddModifyVariable;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    
    Part currentPart = DataManager.getPartToModify();
    InHouse currentInHousePart = null;
    Outsourced currentOutsourcedPart = null;
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
        
        txtId.setDisable(true);
                
    
        if (currentPart instanceof InHouse)
       {
           
           currentInHousePart = (InHouse)currentPart;
           rbInHouse.setSelected(true);
           lblAddModifyVariable.setText("Machine ID");
           txtId.setText(String.valueOf(currentInHousePart.getId()));
           txtName.setText(currentInHousePart.getName());
           txtInv.setText(String.valueOf(currentInHousePart.getStock()));
           txtPriceCost.setText(String.valueOf(currentInHousePart.getPrice()));           
           txtMin.setText(String.valueOf(currentInHousePart.getMin()));   
           txtMax.setText(String.valueOf(currentInHousePart.getMax()));
           txtAddModifyVariable.setText(String.valueOf(currentInHousePart.getMachineId()));
           
       }
       else if (currentPart instanceof Outsourced)
       {
           
           currentOutsourcedPart = (Outsourced)currentPart;
           rbOutsourced.setSelected(true);
           lblAddModifyVariable.setText("Company Name");
           txtId.setText(String.valueOf(currentOutsourcedPart.getId()));
           txtName.setText(currentOutsourcedPart.getName());
           txtInv.setText(String.valueOf(currentOutsourcedPart.getStock()));
           txtPriceCost.setText(String.valueOf(currentOutsourcedPart.getPrice()));
           txtMin.setText(String.valueOf(currentOutsourcedPart.getMin()));
           txtMax.setText(String.valueOf(currentOutsourcedPart.getMax()));     
           txtAddModifyVariable.setText(String.valueOf(currentOutsourcedPart.getCompanyName()));
           
       }
        
    }    

     /**
     * 
     * Handles the radio button for {@link felixcaban.models.InHouse InHouse} parts being modified in inventory.
     * 
     * @param event the event to handle.
     * 
     */
    @FXML
    private void handleRbInHouseAction(ActionEvent event) 
    {
        
        lblAddModifyVariable.setText("Machine ID");
        txtId.setDisable(true);
        
    }

    /**
     * 
     * Handles the radio button for {@link felixcaban.models.Outsourced Outsourced} parts being modified in inventory.
     * 
     * @param event the event to handle.
     * 
     */
    @FXML
    private void handleRbOutsourcedAction(ActionEvent event) 
    {
        
        lblAddModifyVariable.setText("Company Name");
        txtId.setDisable(true);
        
    }
    
    /**
     * 
     * Handles the button to cancel modifying the {@link felixcaban.models.Part Part} in inventory.
     * 
     * @param event the event to handle.
     * 
     */
    @FXML
    private void handleBtnCancelAction(ActionEvent event) throws IOException
    {
        
        returnToMainForm(event);
        
    }

    /**
     * 
     * Handles the button to save the selected {@link felixcaban.models.Part Part} being modified in inventory.
     * 
     * @param event the event to handle.
     * 
     */
    @FXML
    private void handleBtnSaveAction(ActionEvent event)
    {
        
        Part tempPart;
        
        try
        {            

            if (rbInHouse.isSelected()) 
            {

                tempPart = new InHouse(
                        currentPart.getId(),
                        txtName.getText(),
                        Double.parseDouble(txtPriceCost.getText()),
                        Integer.parseInt(txtInv.getText()), 
                        Integer.parseInt(txtMin.getText()),  
                        Integer.parseInt(txtMax.getText()),
                        Integer.parseInt(txtAddModifyVariable.getText())
                );

                Inventory.updatePart(DataManager.getPartIndexToModify(), tempPart);
                returnToMainForm(event);

            }
            else if (rbOutsourced.isSelected()) 
            {

                tempPart = new Outsourced(
                        currentPart.getId(),
                        txtName.getText(),
                        Double.parseDouble(txtPriceCost.getText()),
                        Integer.parseInt(txtInv.getText()), 
                        Integer.parseInt(txtMin.getText()),  
                        Integer.parseInt(txtMax.getText()),                    
                        txtAddModifyVariable.getText()
                );

                Inventory.updatePart(DataManager.getPartIndexToModify(), tempPart);
                returnToMainForm(event);

            }
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