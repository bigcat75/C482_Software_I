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
 * FXML Controller class for AddProductForm.fxml. 
 * The controller for the UI to add a new {@link felixcaban.models.Product Product} 
 * to {@link felixcaban.models.Inventory Inventory}.
 *
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public class AddProductFormController implements Initializable 
{
    
    //================================================================================
    // UI Controls
    //================================================================================
    
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
    @FXML
    private ListView<String> lstErrorList;
    
    
    //================================================================================
    // Properties
    //================================================================================
    
    /**
     * 
     * List of {@link felixcaban.models.Part Parts} that the new {@link felixcaban.models.Product Product}
     * will be composed of.
     * 
     */
    private ObservableList<Part> includedParts = FXCollections.observableArrayList();
    
    /**
     * 
     * List of validation errors from the form input.
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
        
        txtId.setPromptText("Auto Gen-Disabled");
        txtId.setDisable(true);
        
        colPartIdA.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartNameA.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartInventoryLevelA.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartCostA.setCellValueFactory(new PropertyValueFactory<>("price"));
        allPartsTable.setItems(Inventory.getAllParts());   
        
        colPartIdB.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPartNameB.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPartInventoryLevelB.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPartCostB.setCellValueFactory(new PropertyValueFactory<>("price"));
        includedPartsTable.setItems(includedParts); 
        
    }    
    
    
    //================================================================================
    // Methods
    //================================================================================

    /**
     * 
     * Handles the button to cancel adding the {@link felixcaban.models.Product Product} to inventory. 
     * Returns the user to the {@link felixcaban.controllers.MainFormController MainForm}.
     * 
     * @param event on action.
     * @throws IOException thrown if the user can not be returned to the {@link felixcaban.controllers.MainFormController MainForm}.
     * 
     */
    @FXML
    private void handleBtnCancelAction(ActionEvent event) throws IOException 
    {
        
        returnToMainForm(event);  
        
    }

    /**
     * 
     * Handles the button to add the selected {@link felixcaban.models.Part Part} to the product. 
     * If no {@link felixcaban.models.Part Part} is selected, a message dialog will prompt the user
     * to select a part.
     * 
     * @param event on action.
     * 
     */
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

     /**
     * 
     * Handles the button to remove the selected {@link felixcaban.models.Part Part} from the product. 
     * If no {@link felixcaban.models.Part Part} is selected, a message dialog will prompt the user
     * to select a part.
     * 
     * @param event on action.
     * 
     */
    @FXML
    private void handleBtnRemovePartAction(ActionEvent event) 
    {
               
        if(includedPartsTable.getSelectionModel().getSelectedItem() == null)
        {

            JOptionPane.showMessageDialog(null, "Please select a Part to remove.", "Selection Error", 0);

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

    /**
     * 
     * Handles the button to save the entered {@link felixcaban.models.Product Product} being added to inventory.  
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
            
            int productId = DataManager.getNextProductId();
            
            var tempProduct = new Product(
                        productId, 
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

            Inventory.addProduct(tempProduct);
            DataManager.incrementProductId();            
            returnToMainForm(event); 
            
        }
        else
        {
            
            lstErrorList.setItems(inputErrors);              
            
        }
        
    }

    /**
     * 
     * Handles the entered text in the txtPartSearch text box. 
     * Initiates a search on existing {@link felixcaban.models.Part Parts} in inventory. If
     * the {@link felixcaban.models.Part Part} exists, it is selected.
     * 
     * @param event on key press.
     * 
     */
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
                
                if (!DataManager.partExists(partId))
                {
                    
                    allPartsTable.setItems(Inventory.getAllParts());
                    allPartsTable.getSelectionModel().clearSelection();
                    allPartsTable.scrollTo(0);
                    JOptionPane.showMessageDialog(null, "The Part your entered does not exist.", "Search Error", 0);
                    
                }
                else
                {
                    
                    allPartsTable.scrollTo(Inventory.lookupPart(partId));
                    allPartsTable.getSelectionModel().select(Inventory.lookupPart(partId));
                    
                }

            }
            else
            {      
                
                String partName = txtPartSearch.getText().toLowerCase();
                allPartsTable.setItems(Inventory.lookupPart(partName));

            }            
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