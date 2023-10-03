/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coderealm;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;







import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**

/**
 * FXML Controller class
 *
 * @author User
 */
public class UserAccountController implements Initializable {

    @FXML
    private ImageView exitIcon;
    @FXML
    private Label exit;
    @FXML
    private TextField userAccName;
    @FXML
    private TextField userAccFName;
    @FXML
    private TextField userAccLName;
    @FXML
    private TextField userAccPhnNo;
    Connection con;
    
    public static String Un;
    public static String pn;
    public static String UID;
    
    public String firstName;
    public String lastName;
    public String user;
    public String phone;
    public String pass;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        exit.setOnMouseClicked(event->{
            try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
              Parent root = loader.load();
              Scene scene = new Scene(root);
              HomeController sc = loader.getController();
              Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              
           } catch (IOException ex) {
              
               System.out.println(ex);
               
           }  
           });
        
    }    
    public void setData(String Un,String pn,String UID){
        this.Un=Un;
        this.pn=pn;
        this.UID=UID;
    }
    
    public void show() throws SQLException{
        try {
                con = (java.sql.Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/coderealmdata", "coderealm", "1234");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            String sql = "SELECT *FROM CODEREALMSs  WHERE USERNAME  = ?AND PASSWORD = ?AND UID = ? ";
            PreparedStatement px = con.prepareStatement(sql);
            px.setString(1, Un);
            px.setString(2, pn);
            px.setString(3, UID);
            
            ResultSet rx =px.executeQuery();
            while(rx.next())
            {
                 firstName = rx.getString("FIRSTNAME");
                 lastName = rx.getString("LASTNAME");
                 user = rx.getString("USERNAME");
                 phone = rx.getString("PHONE_NO");
                
                userAccFName.setText(firstName);
                userAccLName.setText(lastName);
                userAccName.setText(user);
                userAccPhnNo.setText(phone);
                
            }
            System.out.println(firstName+""+pass);
            System.out.println(Un+pn+UID);
    }
    
}



