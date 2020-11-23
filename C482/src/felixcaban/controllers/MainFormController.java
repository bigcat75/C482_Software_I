package felixcaban.controllers;

import felixcaban.data.DataManager;
import felixcaban.models.Inventory;
import felixcaban.models.Part;
import felixcaban.models.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
        addPartScene.getStylesheets().add("felixcaban/css/Listview.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addPartScene);
        window.show();  
        
    }

    @FXML
    private void handleBtnPartModifyAction(ActionEvent event) throws IOException 
    {
        
        if(partTable.getSelectionModel().getSelectedItem() == null)
        {
            
            JOptionPane.showMessageDialog(null, "Please select a Part to modify.", "Selection Error", 0);
            
        }        
        else
        {        
            
            DataManager.setPartToModify(partTable.getSelectionModel().getSelectedIndex(), partTable.getSelectionModel().getSelectedItem());

            Parent modPartForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/ModifyPartForm.fxml"));
            Scene modPartScene = new Scene(modPartForm);
            modPartScene.getStylesheets().add("felixcaban/css/Listview.css");
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();   

            window.setScene(modPartScene);
            window.show();  
            
        } 
        
    }

    @FXML
    private void handleBtnPartDeleteAction(ActionEvent event)
    {
        if(partTable.getSelectionModel().getSelectedItem() == null)
        {
            
            JOptionPane.showMessageDialog(null, "Please select a Part to delete.", "Selection Error", 0);
            
        }        
        else
        {
            
            int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete this Part?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if(result ==0)
            {        
                
                var selectedPart = partTable.getSelectionModel().getSelectedItem();
                Inventory.deletePart(selectedPart);
                
            }
            
        }
        
    }
    
    @FXML
    private void handleBtnProductAddAction(ActionEvent event) throws IOException
    {
        
        Parent addProductForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/AddProductForm.fxml"));
        Scene addProductScene = new Scene(addProductForm);
        addProductScene.getStylesheets().add("felixcaban/css/Listview.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(addProductScene);
        window.show(); 
        
    }

    @FXML
    private void handleBtnProductModifyAction(ActionEvent event) throws IOException
    {
        
        if(productTable.getSelectionModel().getSelectedItem() == null)
        {
            
            JOptionPane.showMessageDialog(null, "Please select a Product to modify", "Selection Error", 0);
            
        }    
        else
        { 
            
            DataManager.setProductToModify(productTable.getSelectionModel().getSelectedIndex(), productTable.getSelectionModel().getSelectedItem());

            Parent modProductForm = FXMLLoader.load(getClass().getResource("/felixcaban/views/ModifyProductForm.fxml"));
            Scene modProductScene = new Scene(modProductForm);
            modProductScene.getStylesheets().add("felixcaban/css/Listview.css");
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

            window.setScene(modProductScene);
            window.show(); 
            
        }
                
    }
    
    @FXML
    private void handleBtnProductDeleteAction(ActionEvent event)
    {
        
        if(productTable.getSelectionModel().getSelectedItem() == null)
        {
            
            JOptionPane.showMessageDialog(null, "Please select a Product to delete.", "Selection Error", 0);
            
        }
        else if (productTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty())
        {
            int result = JOptionPane.showConfirmDialog(null, "Do you really want to delete this Product?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            
            if(result ==0)
            {

                var selectedProduct = productTable.getSelectionModel().getSelectedItem();
                Inventory.deleteProduct(selectedProduct);

            }  
        }
        else        
        {
            JOptionPane.showMessageDialog(null, "You can not delete a Product that contains Parts.", "Selection Error", 0);
        }
        
    }

    @FXML
    private void handlePartSearchAction(KeyEvent event) 
    {
             
        if(event.getCode() == KeyCode.ENTER) 
        {
            
            if (txtPartSearch.getText() == null || txtPartSearch.getText().isEmpty())
            {

                partTable.setItems(Inventory.getAllParts());
                partTable.getSelectionModel().clearSelection();
                partTable.scrollTo(0);

            }
            else if (DataManager.isInteger(txtPartSearch.getText()))
            {
                
                int partId = Integer.parseInt(txtPartSearch.getText());
                Inventory.lookupPart(partId);
                partTable.scrollTo(Inventory.lookupPart(partId));
                partTable.getSelectionModel().select(Inventory.lookupPart(partId));

            }
            else
            {      
                
                String partName = txtPartSearch.getText();
                partTable.setItems(Inventory.lookupPart(partName));

            }            
        }        
        
    }

    @FXML
    private void handleProductSearchAction(KeyEvent event) 
    {
                
        if(event.getCode() == KeyCode.ENTER) 
        {
            
            if (txtProductSearch.getText() == null || txtProductSearch.getText().isEmpty())
            {

                productTable.setItems(Inventory.getAllProducts());
                productTable.getSelectionModel().clearSelection();
                productTable.scrollTo(0);

            }
            else if (DataManager.isInteger(txtProductSearch.getText()))
            {
                
                int partId = Integer.parseInt(txtProductSearch.getText());
                Inventory.lookupPart(partId);
                productTable.scrollTo(Inventory.lookupProduct(partId));
                productTable.getSelectionModel().select(Inventory.lookupProduct(partId));

            }
            else
            {   
                
                String productName = txtProductSearch.getText();
                productTable.setItems(Inventory.lookupProduct(productName));

            }            
        }        
        
    }
      
}
