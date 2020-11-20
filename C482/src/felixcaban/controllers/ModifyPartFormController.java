package felixcaban.controllers;

import felixcaban.data.DataManager;
import felixcaban.models.InHouse;
import felixcaban.models.Inventory;
import felixcaban.models.Outsourced;
import felixcaban.models.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

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
    
    InHouse currentInHousePart = null;
    Outsourced currentOutsourcedPart = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        txtId.setDisable(true);
                
        for (Part p : Inventory.getAllParts())
        {
            if(p.getId() == DataManager.getPartId())
            {
                 if (p instanceof InHouse)
                {
                    currentInHousePart = (InHouse)p;
                    rbInHouse.setSelected(true);
                    lblAddModifyVariable.setText("Machine ID");
                    txtId.setText(String.valueOf(currentInHousePart.getId()));
                    txtName.setText(currentInHousePart.getName());
                    txtInv.setText(String.valueOf(currentInHousePart.getStock()));
                    txtPriceCost.setText(String.valueOf(currentInHousePart.getPrice()));
                    txtMax.setText(String.valueOf(currentInHousePart.getMax()));
                    txtMin.setText(String.valueOf(currentInHousePart.getMin()));     
                    txtAddModifyVariable.setText(String.valueOf(currentInHousePart.getMachineId()));
                }
                else if (p instanceof Outsourced)
                {
                    currentOutsourcedPart = (Outsourced)p;
                    rbOutsourced.setSelected(true);
                    lblAddModifyVariable.setText("Company Name");
                    txtId.setText(String.valueOf(currentOutsourcedPart.getId()));
                    txtName.setText(currentOutsourcedPart.getName());
                    txtInv.setText(String.valueOf(currentOutsourcedPart.getStock()));
                    txtPriceCost.setText(String.valueOf(currentOutsourcedPart.getPrice()));
                    txtMax.setText(String.valueOf(currentOutsourcedPart.getMax()));
                    txtMin.setText(String.valueOf(currentOutsourcedPart.getMin()));     
                    txtAddModifyVariable.setText(String.valueOf(currentOutsourcedPart.getCompanyName()));
                }
            }
        }
        
    }    

    @FXML
    private void handleRbInHouseAction(ActionEvent event) 
    {
        
        lblAddModifyVariable.setText("Machine ID");
        txtId.setDisable(true);
        
    }

    @FXML
    private void handleRbOutsourcedAction(ActionEvent event) 
    {
        
        lblAddModifyVariable.setText("Company Name");
        txtId.setDisable(true);
        
    }
    
    @FXML
    private void handleBtnCancelAction(ActionEvent event) throws IOException
    {
        
        returnToMainForm(event);
        
    }

    @FXML
    private void handleBtnSaveAction(ActionEvent event) throws IOException 
    {
        
        returnToMainForm(event);
        
    }
    
    @FXML
    private void returnToMainForm(ActionEvent event) throws IOException
    {
        Parent mainForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/MainForm.fxml"));
        Scene mainFormScene = new Scene(mainForm);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainFormScene);
        window.show();  
    }

}