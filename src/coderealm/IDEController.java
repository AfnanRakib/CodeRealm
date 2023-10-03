/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package coderealm;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author nadim
 */
public class IDEController implements Initializable {
    @FXML
    private ImageView exitIcon;
    @FXML
    private Label exit;

    @FXML
    private CodeArea code;
    @FXML
    private TextArea inputs;
    @FXML
    private TextArea outputs;
    @FXML
    private Button button;
    @FXML
    private ComboBox<String> languageComboBox;
    
    public String lang;
    public String VersionIndex;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        languageComboBox.setItems(FXCollections.observableArrayList("java  JDK 1.8.0_66","java  JDK 17.0.1","c  GCC 5.3.0","C  GCC 11.1.0","cpp17  g++ 17 GCC 9.1.0","python3  3.9.9"));
        exit.setOnMouseClicked(event ->{
                try { 
                    goBack(event);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
             });
    } 
    
    @FXML
    void languageSelect(ActionEvent event){
       String[] la =  languageComboBox.getSelectionModel().getSelectedItem().toString().split("  ");
      
        //System.out.println(la[0]);
       // System.out.println(la[1]);
        
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
    private void write(KeyEvent event) {
        code.setParagraphGraphicFactory(LineNumberFactory.get(code));
        code.setStyleSpans(0, computeHighlighting(code.getText()));
//        
//        LineNumberFactory l = null;
//        code.setParagraphGraphicFactory(l.get(code));
//        
//        Subscription cleanupWhenNoLongerNeedIt = (Subscription) code.multiPlainChanges()
//                .successionEnds(java.time.Duration.ofMillis(50))
//                .subscribe(ignore -> code.setStyleSpans(0, computeHighlighting(code.getText())));
//        final Pattern whiteSpace = Pattern.compile( "^\\s+" );
//        code.addEventHandler( KeyEvent.KEY_PRESSED, key -> {
//            if (key.getCode() == KeyCode.ENTER) {
//                int pos = code.getCaretPosition();
//                int par = code.getCurrentParagraph();
//                Matcher matcher = whiteSpace.matcher(code.getParagraph(par-1).getSegments().get(0));
//                if (matcher.find()) Platform.runLater(() -> code.insertText(pos, matcher.group()));
//            }
//        });
    }

   @FXML
    private void run(MouseEvent event) throws IOException, InterruptedException {
        
        String clientId = "b3ed2d67791159cd27575d603e467f74";
        String clientSecret = "7c1e14609fedb215f80386d976ed406508c9a4f7fe33841c5dc26c5178b6404b";
        String script = code.getText().toString();
        String language = lang;//"cpp17";
        String versionIndex = VersionIndex;//"1";
        String inputText = inputs.getText().toString();

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
            
            
            outputs.setText(responseData.getOutput());

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
  int n;

  cout << "Enter an integer: ";
  cin >> n;

  if ( n % 2 == 0)
    cout << n << " is even.";
  else
    cout << n << " is odd.";

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


    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, InterruptedException {
        
        
//        
//        HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("https://jokes-by-api-ninjas.p.rapidapi.com/v1/jokes"))
//            .header("X-RapidAPI-Key", "58db07e382mshb0ba8bdce54360ap16822djsnd7382ff19b11")
//            .header("X-RapidAPI-Host", "jokes-by-api-ninjas.p.rapidapi.com")
//            .method("GET", HttpRequest.BodyPublishers.noBody())
//            .build();
//        
//        
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        
//        ObjectMapper mapper = new ObjectMapper();
//        List<Joke> jokes = mapper.readValue(response.body(), new TypeReference<List<Joke>>() {});
//
//
//         jokes.forEach(joke -> {
//             outputs.setText(outputs.getText()+"\n"+joke.getJoke());
//         });


//    FileChooser fc=new FileChooser();
//    //fc.getExtensionFilters().addAll(new ExtensionFilter("TXT file","*txt"));
//    File file=fc.showOpenDialog(null);
//        System.out.println(file);
                        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://kontests.net/api/v1/codeforces"))
            .build();
        
        
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        
        ObjectMapper mapper = new ObjectMapper();
        List<Contests> contests = mapper.readValue(response.body(), new TypeReference<List<Contests>>() {});
        contests.forEach(contest -> {
             outputs.setText(outputs.getText()+"\n"+contest.getName());
         }); 
    }   
}
