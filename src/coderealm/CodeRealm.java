package coderealm;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class CodeRealm extends Application{

    public static void main(String[] args) throws IOException {
         EncryptionProgram ep = new EncryptionProgram();
        
        launch(args);
       
    }
   //
    double x,y =0;
    @Override   
    public void start(Stage primaryStage) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loginview.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        //String css=this.getClass().getResource("HomePageStyle.css").toExternalForm();
        //scene.getStylesheets().add(css);
        /* root.setOnMousePressed(event ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });
         root.setOnMouseDragged(event ->{
            primaryStage.setX( event.getSceneX() - x);
            primaryStage.setY(event.getSceneY()- y);
        });*/
         
         primaryStage.setScene(scene);
        
        primaryStage.setTitle("Home");
    
        primaryStage.show();
    }
  
}
