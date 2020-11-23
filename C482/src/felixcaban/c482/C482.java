package felixcaban.c482;

import felixcaban.data.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class C482 extends Application 
{
    
    Scene mainForm;  

    @Override
    public void start(Stage stage) throws Exception 
    {        
        stage.setTitle("C482 - Felix Caban");
        Parent root = FXMLLoader.load(getClass().getResource("/felixcaban/views/MainForm.fxml"));
        mainForm = new Scene(root);        
        stage.setScene(mainForm);
        stage.show();
    }
    
    public static void main(String[] args) 
    {     
        DataManager.LoadDataOnStart();
        launch(args);  
    }
    
    // TODO Implement JavaDocs for all files
    // TODO Implement min/max check
    
}
