/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coderealm;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nadim
 */
public class RegisterViewController implements Initializable  {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone_no;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Button registerButton;
    
    Connection con;
   // EncryptionProgram ep =new EncryptionProgram();  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void register(ActionEvent event) {
        
        String fn = firstName.getText();
        String ln = lastName.getText();
        String un = username.getText();
        String psw = password.getText();
        String cpsw =confirmPassword.getText();
        String phn = phone_no.getText();
        int cs = 0;
        int cpp = 0;
        if(fn == "" ||ln == "" ||un == "" ||psw  == ""||cpsw  == ""||phn  == ""||!fn.matches(".*[a-zA-Z].*")||fn.matches(".*[0-9!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*")||!ln.matches(".*[a-zA-Z].*")
           ||ln.matches(".*[0-9!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*")||! phn.matches(".*[0-9].*")|| phn.matches(".*[a-zA-Z!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*")||phn.length()!= 11
           ||! psw.contentEquals(cpsw)||!psw.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*")||!psw.matches(".*[0-9].*")||!psw.matches(".*[a-z].*")||!psw.matches(".*[A-Z].*")|| psw.length() < 6){
            
            
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("input is invalid");
                alert.setHeaderText("Warning!!");
                
                if(fn == "" ||ln == "" ||un == "" ||psw  == ""||cpsw  == ""||phn  == "")
                 alert.setContentText("Atleast One of your input is empty !!");
                if(!fn.matches(".*[a-zA-Z].*")||fn.matches(".*[0-9!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*"))
                 alert.setContentText("fisrt name is in inavlid format !!");
                if(!ln.matches(".*[a-zA-Z].*")||ln.matches(".*[0-9!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*"))
                 alert.setContentText("last name is in inavlid format !!");
                if(! phn.matches(".*[0-9].*") || phn.length()!= 11|| phn.matches(".*[a-zA-Z!@#$%&*()_+=|<>?{}\\\\[\\\\]~-].*") )
                 alert.setContentText("phone number is in inavlid format !!");
                if(! psw.contentEquals(cpsw))
                 alert.setContentText("confirm password didn't match !!");
                if(!psw.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*")||!psw.matches(".*[0-9].*")||!psw.matches(".*[a-z].*")||!psw.matches(".*[A-Z].*"))
                 alert.setContentText(" password must contain atleast one capital ,letter one small later ,one unique symbol !!");
                if(psw.length() < 6)
                 alert.setContentText(" password length must be greater or equal then 6 !!");
                 alert.showAndWait();
                 
                
             }
        else{
            
            try {
                con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/coderealmdata", "coderealm", "1234");
            } catch (SQLException ex) {
                 System.out.println(ex);
            }
        
            try {
                 String sql = "INSERT INTO CODEREALMSs  (FIRSTNAME,LASTNAME,USERNAME,PASSWORD,PHONE_NO,CSCORE,CPLUSSCORE) VALUES (?, ?, ?, ?,?,?,?)";
         //con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/loginAccount", "dipto", "1234");
                 PreparedStatement ps = con.prepareStatement(sql);
                 ps.setString(1, fn); 
                 ps.setString(2, ln); 
                 ps.setString(3, un); 
                 ps.setString(4, psw); 
                 ps.setString(5, phn);
                 ps.setInt(6, cs);
                 ps.setInt(7, cpp);
                 
                
                 ps.executeUpdate();
            }catch(SQLException ex){
                 System.out.println(ex); 
            }
        
        
            try{
                
               FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            
               Parent root = loader.load();
               Scene scene = new Scene(root);
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.show();
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }

    @FXML
    private void showLoginStage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            
               Parent root = loader.load();
               Scene scene = new Scene(root);
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.show();
    }
    
}
