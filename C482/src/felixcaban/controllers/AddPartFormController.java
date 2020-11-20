/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package felixcaban.controllers;

import felixcaban.data.DataManager;
import felixcaban.models.InHouse;
import felixcaban.models.Inventory;
import felixcaban.models.Outsourced;
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
                
        if(rbInHouse.isSelected()){
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
        
         if(rbOutsourced.isSelected()){
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
    
    @FXML
    private void handleBtnCancelAction(ActionEvent event) throws IOException
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
