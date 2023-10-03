package coderealm;

import static coderealm.UserAccountController.UID;
import static coderealm.UserAccountController.Un;
import static coderealm.UserAccountController.pn;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.lang.model.SourceVersion;

public class HomeController implements Initializable {


    @FXML
    private ImageView IdeIcon;


    @FXML
    private AnchorPane blackPane;



    @FXML
    private TreeView<String> codeTreeView;

    @FXML
    private ImageView codeTuIcon;

    @FXML
    private Label codeTutorialLabel;

    @FXML
    private Label exit;

    @FXML
    private ImageView exitIcon;


    @FXML
    private AnchorPane homeSlidePane;

    @FXML
    private ImageView infoIcon;


    @FXML
    private Label menu;

    @FXML
    private ImageView menuIcon;


    @FXML
    private ImageView settingsIcon;


    @FXML
    private AnchorPane treeSlidePane;

    
    private AnchorPane barPane;
    Contests contest;
    boolean s = false;
    boolean radio = false;
    @FXML
    private Label idelabel;
    @FXML
    private Label cplabel;
    @FXML
    private Label aboutuslabel;
    @FXML
    private TableView<Contests> ContestTV;
    @FXML
    private TableColumn<Contests, String> contestNameTV;
    @FXML
    private TableColumn<Contests,String > ContestStartTimeTV;
    @FXML
    private TableColumn<Contests, String> ContestEndTimeTV;
    @FXML
    private TableColumn<Contests, String> ContestDurationTV;
    @FXML
    private BarChart<String,Integer> barView;
    @FXML
    private ImageView infoIcon1;
    @FXML
    private Label UserInfo;
    
    public static String Un;
    public static String pn;
    public static String UID;
    
    Connection con;
    
    static int cSolved;
    static int cPSolved;
   
    //ArrayList<String> rootItem = new ArrayList(2);
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            //        setScoreData( cSolved, cPSolved);
            setScoreDataLogin();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        barShow();
        
        
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://kontests.net/api/v1/codeforces"))
                        .build();
                
                
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println(response.body());
                
                ObjectMapper mapper = new ObjectMapper();
                List<Contests> contests = mapper.readValue(response.body(), new TypeReference<List<Contests>>() {});
                ObservableList<Contests> clist = FXCollections.observableArrayList();
                contests.forEach(contest -> {
                    
                      clist.add(new Contests(contest.getName(),contest.getStart_time(),contest.getEnd_time(),contest.getDuration()));
                      contestNameTV.setCellValueFactory(new PropertyValueFactory<Contests,String>("name"/*contest.getName()*/));
                      ContestStartTimeTV.setCellValueFactory(new PropertyValueFactory<Contests,String>("Start_time"/*contest.getStart_time()*/));
                      ContestEndTimeTV.setCellValueFactory(new PropertyValueFactory<Contests,String>("End_time"/*contest.getEnd_time()*/));
                      ContestDurationTV.setCellValueFactory(new PropertyValueFactory<Contests,String>("duration"/*contest.getDuration()*/));
                      ContestTV.setItems(clist);
                    
                  
              });} catch (Exception ex) {
               System.out.println(ex);
                }
        
        TreeItem<String> rootItem1 = new TreeItem<>("Languages");
         TreeItem<String> branchItem1 = new TreeItem<>(" C ");
         TreeItem<String> branchItem2 = new TreeItem<>(" C++ ");
            TreeItem<String> leafItem1 = new TreeItem<>("Basics");
            TreeItem<String> leafItem2 = new TreeItem<>("Conditional Instructions");
            TreeItem<String> leafItem3 = new TreeItem<>("Iterative Statements");
            TreeItem<String> leafItem4 = new TreeItem<>("Functions and Recursion");
            TreeItem<String> leafItem5 = new TreeItem<>("Structures");
            TreeItem<String> leafItem6 = new TreeItem<>("File Handling");
            TreeItem<String> leafItem7 = new TreeItem<>("Dynamic Memory Allocation");
            
            TreeItem<String> leafcItem1 = new TreeItem<>("Introduction");
            TreeItem<String> leafcItem2 = new TreeItem<>("Functions");
            TreeItem<String> leafcItem3 = new TreeItem<>("Decision-Making Statements");
            TreeItem<String> leafcItem4 = new TreeItem<>("Loops");
            TreeItem<String> leafcItem5 = new TreeItem<>("Storage Classes");
            TreeItem<String> leafcItem6 = new TreeItem<>("Structure");
            TreeItem<String> leafcItem7 = new TreeItem<>("File Handling");
            TreeItem<String> leafcItem8 = new TreeItem<>("Dynamic Memory Management");
            TreeItem<String> leafcItem9 = new TreeItem<>("Array");
         
         
        branchItem1.getChildren().addAll(leafItem1,leafItem2,leafItem3,leafItem4,leafItem5,leafItem6,leafItem7);
        branchItem2.getChildren().addAll(leafcItem1,leafcItem2,leafcItem3,leafcItem4,leafcItem5,leafcItem6,leafcItem7,leafcItem8,leafcItem9);
        rootItem1.getChildren().addAll(branchItem1,branchItem2);
       
        codeTreeView.setRoot(rootItem1);
     
        
     blackPane.setVisible(false);
       FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5),blackPane);
       fadeTransition.setFromValue(1);
       fadeTransition.setToValue(0);
       fadeTransition.play();
       
       TranslateTransition translateTransition = new TranslateTransition (Duration.seconds(0.5),homeSlidePane);
       translateTransition.setByX(-600);
       translateTransition.play();
       
       TranslateTransition treeSlidePaneTransition = new TranslateTransition (Duration.seconds(0.5),treeSlidePane);
       treeSlidePaneTransition.setByX(-800);
       treeSlidePaneTransition.play();
     
     menu.setOnMouseClicked(event ->{
          System.out.println(Un+pn+UID);
       blackPane.setVisible(true);
       FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(.5),blackPane);
       fadeTransition1.setFromValue(0);
       fadeTransition1.setToValue(0.15);
       fadeTransition1.play();
       
       FadeTransition menuTransition1 = new FadeTransition(Duration.seconds(0.5),menuIcon);
       FadeTransition menuLTransition1 = new FadeTransition(Duration.seconds(0.5),menu);
       menuTransition1.setFromValue(0);
       menuLTransition1.setFromValue(0);
       menuTransition1.setToValue(0.36);
       menuLTransition1.setToValue(0.73);
       menuTransition1.play();
       menuLTransition1.play();
       
       TranslateTransition treeSlidePaneTransition1 = new TranslateTransition (Duration.seconds(0.5),treeSlidePane);
       
       
       TranslateTransition translateTransition1 = new TranslateTransition (Duration.seconds(0.5),homeSlidePane);
       if(s == false){
            translateTransition1.setFromX(-600);
            translateTransition1.setToX(0);
            translateTransition1.play();
            s = true;
            
          
       }
       else if(s == true){
            translateTransition1.setFromX(0);
            translateTransition1.setToX(-600);
            translateTransition1.play();
            s =false;
            
            FadeTransition blackPaneTransition1 = new FadeTransition(Duration.seconds(0.5), blackPane);
            blackPaneTransition1.setFromValue(0.02);
            blackPaneTransition1.setToValue(0);
            blackPaneTransition1.play();
            
            if(radio == true){
            treeSlidePaneTransition1.setFromX(0);
             treeSlidePaneTransition1.setToX(-600);
            treeSlidePaneTransition1.play();
            radio =false;
            
           
            }
      }
  });
    blackPane.setOnMouseMoved(event ->{
            
       FadeTransition fadeTransition1 = new FadeTransition(Duration.seconds(0.5), blackPane);
       fadeTransition1.setFromValue(0.02);
       fadeTransition1.setToValue(0);
       fadeTransition1.play();
       
       TranslateTransition translateTransition1 = new TranslateTransition (Duration.seconds(0.5),homeSlidePane);
       TranslateTransition RadioTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),treeSlidePane);
      if(s == true){
       translateTransition1.setFromX(0);
       translateTransition1.setToX(-600);
       translateTransition1.play();
       s =false;
       if(radio == true){
            RadioTaskpaneTransition1.setFromX(0);
            RadioTaskpaneTransition1.setToX(-800);
            RadioTaskpaneTransition1.play();
            radio =false;
       }
       
      }
     
       });

    codeTutorialLabel.setOnMouseClicked(event ->{
            TranslateTransition RadioTaskpaneTransition1 = new TranslateTransition (Duration.seconds(0.5),treeSlidePane);
            if(radio == false){
                RadioTaskpaneTransition1.setFromX(-800);
                RadioTaskpaneTransition1.setToX(0);
                RadioTaskpaneTransition1.play();
                radio = true;
            }
            else if(radio == true){
               RadioTaskpaneTransition1.setFromX(0);
                RadioTaskpaneTransition1.setToX(-800);
                RadioTaskpaneTransition1.play();
                radio =false;
            }
       });
    
       exit.setOnMouseClicked(event ->{
           FadeTransition exitTransition1 = new FadeTransition(Duration.seconds(0.5),exitIcon);
           exitTransition1.setFromValue(0);
           exitTransition1.setToValue(0.6);
           exitTransition1.play();
           
            FadeTransition exitLTransition1 = new FadeTransition(Duration.seconds(0.5),exit);
           exitLTransition1.setFromValue(0);
           exitLTransition1.setToValue(0.73);
           exitLTransition1.play();
           
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Exit!");
           alert.setHeaderText("You're about to log out");
           alert.setContentText("Do you want to log out? ");
           if(alert.showAndWait().get() == ButtonType.OK){
               try {
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
                   
                   Parent root = loader.load();
                   Scene scene = new Scene(root);
                   Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   
                   stage.setScene(scene);
                   stage.setTitle("User Login");
                   //stage.getIcons().add(new Image("/asset/icon.png"));
                   stage.show();
               } catch (IOException ex) {
                   Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           
       }

    
);
}
    public void setData(String Un,String pn,String UID){
        this.Un=Un;
        this.pn=pn;
        this.UID=UID;
        
        setScoreData( cSolved, cPSolved);
        barShow();
    }
    
    @FXML
    void selectCodeTuItem(MouseEvent event) throws IOException {
        TreeItem<String> item = codeTreeView.getSelectionModel().getSelectedItem();
        if(item != null){
            try{
              if(item.isLeaf()){
               FXMLLoader loader = new FXMLLoader(getClass().getResource(item.getValue()+".fxml"));            
               Parent root = loader.load();
               Scene scene = new Scene(root);
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.show();
                }
            }catch(Exception ex){
                
            }
        }
    }

    @FXML
    private void openIDE(MouseEvent event) {
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

    @FXML
    private void openCP(MouseEvent event) {
        try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("competitive.fxml"));
            
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
    private void openAbUS(MouseEvent event) {
        try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("aboutUs.fxml"));
            
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
    private void openAcc(MouseEvent event) throws SQLException {
        try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserAccount.fxml"));
            
               Parent root = loader.load();
               
               UserAccountController ac = loader.getController();
                    
               ac.setData(Un,pn,UID);
               Scene scene = new Scene(root);
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(scene);
               stage.show();
               ac.show();
            }catch(IOException ex){
                System.out.println(ex);
            }
    }
    public void setScoreDataLogin() throws SQLException{
            con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/coderealmdata", "coderealm", "1234");
            
            String sql = "SELECT *FROM CODEREALMSs  WHERE USERNAME  = ?AND PASSWORD = ?AND UID = ? ";
            PreparedStatement px = con.prepareStatement(sql);
            px.setString(1, Un);
            px.setString(2, pn);
            px.setString(3, UID);
            
            ResultSet rx =px.executeQuery();
            while(rx.next())
            {
                 cSolved = rx.getInt("CSCORE");
                 cPSolved = rx.getInt("CPLUSSCORE");
                
            }
        
    }
    public void setScoreData(int cSolved,int cPSolved) {
         try {
            System.out.println( UID +" "+cSolved+" "+cPSolved);
            
            try {
                con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/coderealmdata", "coderealm", "1234");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
            
            try {
                String sql = "UPDATE CODEREALMSs SET CSCORE =?, CPLUSSCORE =?  WHERE UID = ?";
                //con = (Connection) DriverManager.getConnection("jdbc:derby://localhost:1527/loginAccount", "dipto", "1234");
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, cSolved);
                ps.setInt(2, cPSolved);
                ps.setString(3, UID);
                
                

                ps.executeUpdate();
            }catch(SQLException ex){
                System.out.println(ex); 
            }
        } catch (Exception ex) {
                 System.out.println(ex);
            }
            
    }
    
    public void scorechange(int f){
        if (f==1)
            cSolved++;
        else if(f==2)
            cPSolved++;
        
        setScoreData( cSolved, cPSolved);
        //barShow();
    }
    public void barShow() {
         
       
        XYChart.Series<String,Integer> series1 = new  XYChart.Series();
        series1.setName("Score");
        series1.getData().add(new  XYChart.Data("C",cSolved));
        series1.getData().add(new  XYChart.Data("C++",cPSolved));
        
        barView.getData().addAll( series1);
    } 

}