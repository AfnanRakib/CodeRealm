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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LoginViewController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    public String Un;
    public String pn;
    public String UID;
    UserData obj;
    Connection con;

    // EncryptionProgram ep = new EncryptionProgram();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void login(ActionEvent event) throws IOException {

        try {
            
            try {
                con = (java.sql.Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/coderealmdata", "coderealm", "1234");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            String s1 = username.getText();
            Un = username.getText();

            String s2 = password.getText();
            pn = password.getText();

            String sql = "SELECT *FROM CODEREALMSs  WHERE USERNAME  = ? AND PASSWORD = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, s1);
            ps.setString(2, s2);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                PreparedStatement pt = con.prepareStatement(sql);
                pt.setString(1, s1);
                pt.setString(2, s2);
                ResultSet rt = pt.executeQuery();
                while (rt.next()) {
                    UID = rt.getString("UID");
                    //System.out.println(UID);
                }
                UserData userData = new UserData();
               
                obj = new UserData( Un,  pn,  UID);
                
                //System.out.println(Un+" "+ pn+" "+ UID);
                
                
                try {
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));

                    Parent root = loader.load();
                    HomeController hc = loader.getController();
                    
                    hc.setData(Un,pn,UID);
                    hc.setScoreDataLogin();

                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("input is invalid");
                alert.setHeaderText("Warning!!");
                alert.setContentText("user account not found !!");
                alert.showAndWait();

                System.out.println(s1);
                System.out.println(s2);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void showRegisterStage(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterView.fxml"));

        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
