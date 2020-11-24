package felixcaban.controllers;

import felixcaban.data.DataManager;
import felixcaban.models.Inventory;
import felixcaban.models.Part;
import felixcaban.models.Product;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * 
 * FXML Controller class for ModifyProductForm.fxml
 *
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public class ModifyProductFormController implements Initializable 
{
    
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
    private Pane partPane;
    @FXML
    private TextField txtPartSearch;
    @FXML
    private Button btnPartAdd;
    @FXML
    private Pane partPane1;
    
    @FXML
    private TableView<Part> allPartsTable;  
    @FXML
    private TableColumn<Part, Integer> colPartIdA;
    @FXML
    private TableColumn<Part, String> colPartNameA;
    @FXML
    private TableColumn<Part, Integer> colPartInventoryLevelA;
    @FXML
    private TableColumn<Part, Double> colPartCostA;
    
    @FXML
    private Button btnPartRemove;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;  
    
    @FXML
    private TableView<Part> includedPartsTable;
    @FXML
    private TableColumn<Part, Integer> colPartIdB;
    @FXML
    private TableColumn<Part, String> colPartNameB;
    @FXML
    private TableColumn<Part, Integer> colPartInventoryLevelB;
    @FXML
    private TableColumn<Part, Double> colPartCostB;
    
    Product currentProduct = DataManager.getProductToModify();
    ObservableList<Part> includedParts = FXCollections.observableArrayList();
    @FXML
    private ListView<String> lstErrorList;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        txtId.setPromptText("Auto Gen-Disabled");
        txtId.setDisable(true);
        
        colPartIdA.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartNameA.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartInventoryLevelA.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartCostA.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartsTable.setItems(Inventory.getAllParts());            
              
        for(Part pa : currentProduct.getAllAssociatedParts())
        {
            
            includedParts.add(pa);
            
        }
                   
        txtId.setDisable(true);        
        txtId.setText(String.valueOf(currentProduct.getId()));
        txtName.setText(currentProduct.getName());
        txtInv.setText(String.valueOf(currentProduct.getStock()));
        txtPriceCost.setText(String.valueOf(currentProduct.getPrice()));
        txtMin.setText(String.valueOf(currentProduct.getMin()));  
        txtMax.setText(String.valueOf(currentProduct.getMax()));
         
        
        colPartIdB.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartNameB.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartInventoryLevelB.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartCostB.setCellValueFactory(new PropertyValueFactory<>("price"));
        includedPartsTable.setItems(includedParts);   
        
    }   

    @FXML
    private void handleBtnCancelAction(ActionEvent event) throws IOException 
    {
        
        returnToMainForm(event);  
        
    }

    @FXML
    private void handleBtnAddPartAction(ActionEvent event) 
    {
        
        if(allPartsTable.getSelectionModel().getSelectedItem() == null)
        {
            
            JOptionPane.showMessageDialog(null, "Please select a Part to add.", "Selection Error", 0);
            
        }        
        else
        {    
        
            var selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
            includedParts.add(selectedPart);

            allPartsTable.setItems(Inventory.getAllParts());
            allPartsTable.getSelectionModel().clearSelection();
            allPartsTable.scrollTo(0);
            txtPartSearch.setText("");
            
        }
        
    }

    @FXML
    private void handleBtnRemovePartAction(ActionEvent event) 
    {
               
        if(includedPartsTable.getSelectionModel().getSelectedItem() == null)
        {

            JOptionPane.showMessageDialog(null, "Please select an Part to remove.", "Selection Error", 0);

        }   
        else
        {
            int result = JOptionPane.showConfirmDialog(null, "Do you really want to remove this part?", "Confim Removal", JOptionPane.YES_NO_OPTION);

            if(result ==0)
            {  

                var selectedPart = includedPartsTable.getSelectionModel().getSelectedItem();
                includedParts.remove(selectedPart);
                includedPartsTable.getSelectionModel().clearSelection();
                includedPartsTable.scrollTo(0);

            }
        }            
        
    }

    @FXML
    private void handleBtnSaveAction(ActionEvent event) throws IOException 
    {       
        
        try
        {
            
            var tempProduct = new Product(
                    currentProduct.getId(),
                    txtName.getText(),
                    Double.parseDouble(txtPriceCost.getText()),
                    Integer.parseInt(txtInv.getText()),
                    Integer.parseInt(txtMin.getText()),
                    Integer.parseInt(txtMax.getText())                
            );        

            for (Part pa : includedParts)
            {
                tempProduct.addAssociatedPart(pa);
            }

            Inventory.updateProduct(DataManager.getProductIndexToModify(), tempProduct);

            returnToMainForm(event);
        }
        catch(Exception e)
        {
            lstErrorList.setItems(validateUserInput());
        }
        
    }
    
    private void returnToMainForm(ActionEvent event) throws IOException
    {
        
        Parent mainForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/MainForm.fxml"));
        Scene mainFormScene = new Scene(mainForm);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(mainFormScene);
        window.show();  
        
    }

    @FXML
    private void handlePartSearchAction(KeyEvent event) 
    {
        
        if(event.getCode() == KeyCode.ENTER) 
        {
            
            if (txtPartSearch.getText() == null || txtPartSearch.getText().isEmpty())
            {

                allPartsTable.setItems(Inventory.getAllParts());
                allPartsTable.getSelectionModel().clearSelection();
                allPartsTable.scrollTo(0);

            }
            else if (DataManager.isInteger(txtPartSearch.getText()))
            {
                
                int partId = Integer.parseInt(txtPartSearch.getText());
                Inventory.lookupPart(partId);
                allPartsTable.scrollTo(Inventory.lookupPart(partId));
                allPartsTable.getSelectionModel().select(Inventory.lookupPart(partId));

            }
            else
            {      
                
                String partName = txtPartSearch.getText();
                allPartsTable.setItems(Inventory.lookupPart(partName));

            }            
        }  
        
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
        
        return inputErrors;
        
    }    
    
}