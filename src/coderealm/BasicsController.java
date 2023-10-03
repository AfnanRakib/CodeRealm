/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coderealm;

import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author User
 */
public class BasicsController implements Initializable {

    @FXML
    private ImageView exitIcon;
    @FXML
    private Label exit;
    @FXML
    private Label next;
    @FXML
    private Label prev;
    @FXML
    private Label tryIDE;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    } 
    @FXML
    void goBack(MouseEvent event) throws IOException {
      try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
              Parent root = loader.load();
              Scene scene = new Scene(root);
              HomeController sc = loader.getController();
              Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              
           } catch (IOException ex) {
               //Logger.getLogger(ToDoListWindowController.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println(ex);
           }
     
     }

    @FXML
    private void goNext(MouseEvent event) {
        try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Conditional Instructions.fxml"));
            
               Parent root = loader.load();
               Scene scene = new Scene(root);
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.show();
            }catch(IOException ex){
                System.out.println(ex);
            }
    }

   
    

    @FXML
    private void goToIDE(MouseEvent event) {
        try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("IDE.fxml"));
            
               Parent root = loader.load();
               Scene scene = new Scene(root);
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.show();
            }catch(IOException ex){
                System.out.println(ex);
            }
    }
}
