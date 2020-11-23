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
 * FXML Controller class
 *
 * @author felix.caban
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
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
            
    {
        
        lblAddModifyVariable.setText("Machine ID");
        txtId.setPromptText("Auto Gen-Disabled");
        txtId.setDisable(true);
        
    }    

    @FXML
    private void handleRbInHouseAction(ActionEvent event) 
    {
        
        lblAddModifyVariable.setText("Machine ID");

    }

    @FXML
    private void handleRbOutsourcedAction(ActionEvent event) 
    {
        
        lblAddModifyVariable.setText("Company Name");
        
    }
    
    @FXML
    private void handleBtnSaveAction(ActionEvent event) throws IOException
    {

        try
        {
            lstErrorList.setItems(validateUserInput());
            
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
            
        }
            
    }
    
    @FXML
    private void handleBtnCancelAction(ActionEvent event) throws IOException
    {
        
        returnToMainForm(event);
        
    }
    
    private void returnToMainForm(ActionEvent event) throws IOException
    {
        
        DataManager.incrementPartId();
        
        Parent mainForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/MainForm.fxml"));
        Scene mainFormScene = new Scene(mainForm);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainFormScene);
        window.show();  
        
    }
    
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

/*
if (DataManager.isInteger(txtMax.getText()) && DataManager.isInteger(txtMin.getText()))
        {
            if(!DataManager.minMaxTest(Integer.parseInt(txtMax.getText()), Integer.parseInt(txtMin.getText())))
            {
                inputErrors.add("Max must be greater than Min.");
                txtMax.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
                txtMin.setStyle("-fx-text-box-border: #B22222; -fx-focus-color: #B22222;");
            }
        }
*/