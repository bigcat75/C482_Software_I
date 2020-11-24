package felixcaban.c482;

import felixcaban.data.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * Contains the main method for the C482 application that manages inventory.      
 * This class creates the stage and sets the first scene that will be visible to the end 
 * user. It also calls the {@link felixcaban.data.DataManager#LoadDataOnStart() 
 * LoadDataOnStart()} method to load the initial data for the application.
 * 
 * @author felix.caban
 * @version 1.0
 * @since 1.0
 * 
 */
public class C482 extends Application 
{
    
    Scene mainForm;  

    /**
     * 
     * Declares the Stage and specifies the scene to show upon program load. 
     * Upon execution, the user will be taken to the {@link felixcaban.controllers.MainFormController MainForm}.
     * 
     * @param stage the stage set upon program load.
     * @throws java.lang.Exception thrown if the stage is not set correctly and the 
     * application can not start.
     * 
     */
    @Override
    public void start(Stage stage) throws Exception
    {        
        stage.setTitle("C482 - Felix Caban");
        Parent root = FXMLLoader.load(getClass().getResource("/felixcaban/views/MainForm.fxml"));
        mainForm = new Scene(root);        
        stage.setScene(mainForm);
        stage.show();
    }
    
    /**
     * 
     * Main method and entry point into the application. 
     * It also calls the {@link felixcaban.data.DataManager#LoadDataOnStart() 
     * LoadDataOnStart()} method to load the initial data for the application.
     *
     * @param args arguments that may get passed when the application loads.
     * 
     */
    public static void main(String[] args) 
    {     
        DataManager.LoadDataOnStart();
        launch(args);  
    }

}
