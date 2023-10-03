/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coderealm;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author MoJoXoXo
 */
public class CompetitiveController implements Initializable {

    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private Label status;
    @FXML
    private ComboBox<String> problemComboBox;
    @FXML
    private Label submit;
    @FXML
    private ImageView exitIcon;
    @FXML
    private Label exit;

     public String lang;
    public String VersionIndex;
    public String plmno;
    public String inputs="";
    public String outputs="";
    @FXML
    private CodeArea code;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        languageComboBox.setItems(FXCollections.observableArrayList("java  JDK 1.8.0_66","java  JDK 17.0.1","c  GCC 5.3.0","C  GCC 11.1.0","cpp17  g++ 17 GCC 9.1.0","python3  3.9.9"));
        problemComboBox.setItems(FXCollections.observableArrayList("problem 1","problem 2"));
        
        exit.setOnMouseClicked(event ->{
                try { 
                    goBack(event);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
             });
    } 
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
               System.out.println(ex);
           }
     
     }
    @FXML
    void languageSelect(ActionEvent event){
       String[] la =  languageComboBox.getSelectionModel().getSelectedItem().toString().split("  ");
       
        lang = "";
        VersionIndex="";
     
        lang = la[0];
        if(la[1] .equals("JDK 1.8.0_66"))
            VersionIndex = "0";
        else if(la[1].equals("JDK 17.0.1") )
            VersionIndex = "4";
        else if(la[1].equals("GCC 11.1.0"))
            VersionIndex = "5";
        else if(la[1].equals( "GCC 5.3.0"))
            VersionIndex = "0";
        else if(la[1].equals( "g++ 17 GCC 9.1.0"))
            VersionIndex = "0";
        else if(la[1].equals("3.9.9") )
            VersionIndex = "4";
        System.out.println(lang +" "+  VersionIndex);
       
    }   

    @FXML
    private void problemSelect(ActionEvent event) {        
        String plmno =  problemComboBox.getSelectionModel().getSelectedItem();
        
        if(plmno.equals("problem 2")){
            inputs="23 45";
            outputs="68";
        }     
    }
    @FXML
    private void write(KeyEvent event) {
        code.setParagraphGraphicFactory(LineNumberFactory.get(code));
        code.setStyleSpans(0, computeHighlighting(code.getText()));
    }
     @FXML
    private void run(MouseEvent event) throws IOException, InterruptedException {
        
        String clientId = "b3ed2d67791159cd27575d603e467f74";
        String clientSecret = "7c1e14609fedb215f80386d976ed406508c9a4f7fe33841c5dc26c5178b6404b";
        String script = code.getText().toString();
        String language = lang;//"cpp17";
        String versionIndex = VersionIndex;//"1";
        String inputText = inputs;

        try {
            URL url = new URL("https://api.jdoodle.com/v1/execute");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            JSONObject jsonInput = new JSONObject();
            jsonInput.put("clientId", clientId);
            jsonInput.put("clientSecret", clientSecret);
            jsonInput.put("script", script);
            jsonInput.put("language", language);
            jsonInput.put("versionIndex", versionIndex);
            jsonInput.put("stdin", inputText);

            String input = jsonInput.toString();

            //System.out.println(input);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input.getBytes());
            outputStream.flush();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Please check your inputs : HTTP error code : "+ connection.getResponseCode());
            }BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new InputStreamReader(
            (connection.getInputStream())));

            String output;
            StringBuilder responseBuilder = new StringBuilder();
            while ((output = bufferedReader.readLine()) != null) {
                responseBuilder.append(output);
            }

            String jsonResponse = responseBuilder.toString();
            //System.out.println("Response JSON:\n" + jsonResponse);

            ObjectMapper objectMapper = new ObjectMapper();
            ResponseData responseData = objectMapper.readValue(jsonResponse, ResponseData.class);

//            System.out.println("Output: " + responseData.getOutput());
//            System.out.println("Status Code: " + responseData.getStatusCode());
//            System.out.println("Memory: " + responseData.getMemory());
//            System.out.println("CPU Time: " + responseData.getCpuTime());
//            System.out.println("Compilation Status: " + responseData.getCompilationStatus());
            
            
            //outputs.setText(responseData.getOutput());
            if(outputs.equals(responseData.getOutput())){
                status.setText("Accepted");
                
                String[] la =  languageComboBox.getSelectionModel().getSelectedItem().toString().split("  ");
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));

                    Parent root = loader.load();
                    HomeController hc = loader.getController();
                    if(la[1].equals( "g++ 17 GCC 9.1.0"))
                        hc.scorechange(2);
            }
            else
                status.setText("wrong");

            connection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

  /*        
        sample:
        
#include <iostream>
using namespace std;

int main() {
  int a,b;
  cin >> a>>b;

  cout << a+b;

  return 0;
}
      
        */     
        
        
//        HttpRequest request = HttpRequest.newBuilder()
//		.uri(URI.create("https://api.codex.jaagrav.in"))
//		.header("content-type", "application/x-www-form-urlencoded")
//		.method("POST", HttpRequest.BodyPublishers.ofString("input="+inputs.getText().toString()+"&code="+code.getText().toString()+"&language="+language.getText()))
//		.build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        


//        HttpRequest request = HttpRequest.newBuilder()
//		.uri(URI.create("https://code-compiler.p.rapidapi.com/v2"))
//		.header("content-type", "application/x-www-form-urlencoded")
//		.header("X-RapidAPI-Key", "58db07e382mshb0ba8bdce54360ap16822djsnd7382ff19b11")
//		.header("X-RapidAPI-Host", "code-compiler.p.rapidapi.com")
//		.method("POST", HttpRequest.BodyPublishers.ofString("LanguageChoice="+language.getText()+"&Program="+code.getText().toString()+"&Input="+inputs.getText().toString()))
//		.build();
//HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//System.out.println(response.body());
//
//    ObjectMapper mapper = new ObjectMapper();
//JsonNode root = mapper.readTree(response.body());
//
//CodeResult result = new CodeResult();
//
//if (root.has("Errors") && !root.get("Errors").isNull()) {
//    result.setErrors(root.get("Errors").asText());
//}
//
//if (root.has("Result")) {
//    result.setResult(root.get("Result").asText());
//}
//
//if (root.has("Stats")) {
//    result.setStats(root.get("Stats").asText());
//}
//
//if (root.has("Files")) {
//    result.setFiles(root.get("Files").asText());
//}
//        
//        if(result.getErrors()==null)
//            
//        else
//            outputs.setText(result.getErrors());
          
 

    }

     private static final String[] KEYWORDS = new String[] {
        "abstract", "and", "and_eq", "as", "assert", "async", "await", "auto", "bitand",
        "bitor", "bool", "break", "byte", "case", "catch", "char", "char16_t", "char32_t",
        "class", "compl", "concept", "const", "const_cast", "constexpr", "continue",
        "co_await", "co_return", "co_yield", "default", "decltype", "del", "do", "double",
        "dynamic_cast", "elif", "else", "enum", "except", "explicit", "export", "extern",
        "false", "final", "finally", "float", "for", "friend", "from", "global", "goto",
        "if", "import", "in", "inline", "instanceof", "int", "interface", "long", "mutable",
        "namespace", "native", "new", "noexcept", "nonlocal", "not", "not_eq", "nullptr",
        "operator", "or", "or_eq", "package", "pass", "private", "protected", "public",
        "reflexpr", "register", "reinterpret_cast", "requires", "return", "short", "signed",
        "sizeof", "static", "static_assert", "static_cast", "strictfp", "struct", "super",
        "switch", "synchronized", "template", "this", "thread_local", "throw", "throws",
        "transient", "true", "try", "typedef", "typeid", "typename", "union", "unsigned",
        "using", "virtual", "void", "volatile", "wchar_t", "while", "with", "xor", "xor_eq",
        "cin", "cout"

    };
    
    private static final String[] OPERTORS = new String[] {
        "=", "+", "-", "/", "%", "++", "--", "+=", "-=", "=", "/=", "%=", "==", "!=",
        ">", "<", ">=", "<=", "&&", "||", "&", "|", "^", "~", "<<", ">>"

    };
    
    
    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String PAREN_PATTERN = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String BRACKET_PATTERN = "\\[|\\]";
    private static final String OPERTOR_PATTERN = "\\b(" + String.join("|", OPERTORS).replaceAll("\\+", "\\\\+") + ")\\b";
    private static final String SEMICOLON_PATTERN = "\\;";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
    private static final String ASSIGNMENT_PATTERN = "\\s+\\w+?\\s+=" + "|" + "\\s+\\w+\\[.*\\]?\\s+=";

    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<OPERTOR>" + OPERTOR_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                    + "|(?<ASSIGNMENT>" + ASSIGNMENT_PATTERN + ")"
    );
    
    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        int lastKwEnd = 0;
        Matcher matcher = PATTERN.matcher(text);
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while(matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                            matcher.group("PAREN") != null ? "paren" :
                                    matcher.group("BRACE") != null ? "brace" :
                                            matcher.group("BRACKET") != null ? "bracket" :
                                                    matcher.group("SEMICOLON") != null ? "semicolon" :
                                                        matcher.group("OPERTOR") != null ? "operator" :
                                                            matcher.group("STRING") != null ? "string" :
                                                                    matcher.group("COMMENT") != null ? "comment" :
                                                                            matcher.group("ASSIGNMENT") != null ? "assignment" :
                                                                                    null; /* never happens */ assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}
