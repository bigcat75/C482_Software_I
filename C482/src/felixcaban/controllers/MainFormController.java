package felixcaban.controllers;

import felixcaban.data.DataManager;
import felixcaban.models.Inventory;
import felixcaban.models.Part;
import felixcaban.models.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class MainFormController implements Initializable 
{    
    Part selectedPart;
    Product selectedProduct;

    @FXML
    private Pane partPane;
    @FXML
    private TextField txtPartSearch;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn <Part, Integer> colPartId;
    @FXML
    private TableColumn<Part, String> colPartName;
    @FXML
    private TableColumn<Part, Integer> colPartInventoryLevel;
    @FXML
    private TableColumn<Part, Double> colPartCost;
    @FXML
    private Button btnPartAdd;
    @FXML
    private Button btnPartModify;
    @FXML
    private Button btnPartDelete;
    
    
    @FXML
    private Pane productPane;
    @FXML
    private TextField txtProductSearch;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> colProductId;
    @FXML
    private TableColumn<Product, String> colProductName;
    @FXML
    private TableColumn<Product, Integer> colProductInventoryLevel;
    @FXML
    private TableColumn<Product, Double> colProductCost;
    @FXML
    private Button btnProductAdd;
    @FXML
    private Button btnProductModify;
    @FXML
    private Button btnProductDelete;
    
    
    @FXML
    private Button btnExit;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    { 
        
        colPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTable.setItems(Inventory.getAllParts());        
      
        colProductId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colProductCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        productTable.setItems(Inventory.getAllProducts()); 
        
        
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
        sortedParts.comparatorProperty().bind(partTable.comparatorProperty());
        partTable.setItems(sortedParts);
        
        
        FilteredList<Product> filteredProducts = new FilteredList<>(Inventory.getAllProducts(), b -> true);
        
        txtProductSearch.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            filteredProducts.setPredicate(p -> 
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
        
        SortedList<Product> sortedProducts = new SortedList<> (filteredProducts);
        sortedProducts.comparatorProperty().bind(productTable.comparatorProperty());
        productTable.setItems(sortedProducts);
        
    }    

    @FXML
    private void handleBtnExitAction(ActionEvent event) 
    {
        
        Platform.exit();
        
    }

    @FXML
    private void handleBtnPartAddAction(ActionEvent event) throws IOException 
    {
        
        Parent addPartForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/AddPartForm.fxml"));
        Scene addPartScene = new Scene(addPartForm);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addPartScene);
        window.show();  
        
    }

    @FXML
    private void handleBtnPartModifyAction(ActionEvent event) throws IOException 
    {
        
        try
        {        
            DataManager.selectedPartId(partTable.getSelectionModel().getSelectedItem().getId());

            Parent modPartForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/ModifyPartForm.fxml"));
            Scene modPartScene = new Scene(modPartForm);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();   

            window.setScene(modPartScene);
            window.show();  
        } 
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please select an item to modify", "Selection Error", 0);
        };
        
    }

    @FXML
    private void handleBtnPartDeleteAction(ActionEvent event)throws IOException 
    {
        
        int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete this record?", "Confim Delete", JOptionPane.YES_NO_OPTION);
        
        if(result ==0)
        {        
            var selectedPart = partTable.getSelectionModel().getSelectedItem();
            Inventory.deletePart(selectedPart);
        }
        
    }
    
    @FXML
    private void handleBtnProductAddAction(ActionEvent event) throws IOException 
    {
        
        Parent addProductForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/AddProductForm.fxml"));
        Scene addProductScene = new Scene(addProductForm);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addProductScene);
        window.show(); 
        
    }

    @FXML
    private void handleBtnProductModifyAction(ActionEvent event)throws IOException 
    {
    
        try
        { 
            DataManager.selectedProductId(productTable.getSelectionModel().getSelectedItem().getId());

            Parent modProductForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/ModifyProductForm.fxml"));
            Scene modProductScene = new Scene(modProductForm);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(modProductScene);
            window.show(); 
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Please select an item to modify", "Selection Error", 0);
        };
                
    }
    
    @FXML
    private void handleBtnProductDeleteAction(ActionEvent event)throws IOException 
    {
        int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete this record?", "Confim Delete", JOptionPane.YES_NO_OPTION);
        
        if(result ==0)
        {
            var selectedProduct = productTable.getSelectionModel().getSelectedItem();
            Inventory.deleteProduct(selectedProduct);
        }  
        
    }

    @FXML
    private void handlePartSearchAction(KeyEvent event) 
    {
        
    }

    @FXML
    private void handleProductSearchAction(KeyEvent event) 
    {
        
    }
      
}
