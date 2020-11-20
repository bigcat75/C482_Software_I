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
        DataManager.LoadData();
        launch(args);  
    }
    
    // TODO Fix issue with Product associated parts being saved without hitting save
    // TODO Finish feature to save modified Parts and Products
    // TODO Fix the search feature so that it executes on "Enter" and not use a listener
    // TODO Implement JavaDocs for all files
    
    
    
}
