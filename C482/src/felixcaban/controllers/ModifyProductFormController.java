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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author felix.caban
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
    
    ObservableList<Part> includedParts = FXCollections.observableArrayList();
    
    Product currentProduct = null;
    

    /**
     * Initializes the controller class.
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
        
        FilteredList<Part> filteredParts = new FilteredList<>(Inventory.getAllParts(), b -> true);
        
        txtPartSearch.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filteredParts.setPredicate(p -> 
            {
                if(newValue == null || newValue.isEmpty())
                {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (p.getName().toLowerCase().indexOf(lowerCaseFilter) != -1)
                {
                    return true;
                }
                else if (String.valueOf(p.getId()).indexOf(newValue) != -1)
                {
                    return true;
                }
                else return false;
            });
            
        });
        
        SortedList<Part> sortedParts = new SortedList<> (filteredParts);
        sortedParts.comparatorProperty().bind(allPartsTable.comparatorProperty());
        allPartsTable.setItems(sortedParts);
        
        for (Product p : Inventory.getAllProducts())
        {
            if(p.getId() == DataManager.getProductId())
            {
                currentProduct = p;
            }
        }
              
        txtId.setText(String.valueOf(currentProduct.getId()));
        txtName.setText(currentProduct.getName());
        txtInv.setText(String.valueOf(currentProduct.getStock()));
        txtPriceCost.setText(String.valueOf(currentProduct.getPrice()));
        txtMax.setText(String.valueOf(currentProduct.getMax()));
        txtMin.setText(String.valueOf(currentProduct.getMin()));   
        includedParts = currentProduct.getAllAssociatedParts();
        
        txtId.setDisable(true);
        
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
        
        var selectedPart = allPartsTable.getSelectionModel().getSelectedItem();
        includedParts.add(selectedPart);
        
    }

    @FXML
    private void handleBtnRemovePartAction(ActionEvent event) 
    {
        
        try
            { 
            int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete this record?", "Confim Delete", JOptionPane.YES_NO_OPTION);

            if(result ==0)
            {  
                var selectedProduct = includedPartsTable.getSelectionModel().getSelectedItem();
                includedParts.remove(selectedProduct);
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please select an item to modify", "Selection Error", 0);
        };
        
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