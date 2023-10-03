/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coderealm;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData {
    @JsonProperty("output")
    private String output;

    @JsonProperty("statusCode")
    private int statusCode;

    @JsonProperty("memory")
    private String memory;

    @JsonProperty("cpuTime")
    private String cpuTime;

    @JsonProperty("compilationStatus")
    private String compilationStatus;

//    public void setAll(String output, int statusCode, String memory, String cpuTime, String compilationStatus) {
//        this.output = output;
//        this.statusCode = statusCode;
//        this.memory = memory;
//        this.cpuTime = cpuTime;
//        this.compilationStatus = compilationStatus;
//    }

    
    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(String cpuTime) {
        this.cpuTime = cpuTime;
    }

    public String getCompilationStatus() {
        return compilationStatus;
    }

    public void setCompilationStatus(String compilationStatus) {
        this.compilationStatus = compilationStatus;
    }
    
    public ResponseData compile(String script,String inputText,String language,String versionIndex){
        
        ResponseData responseData= new ResponseData();
        
        String clientId = "b3ed2d67791159cd27575d603e467f74";
        String clientSecret = "7c1e14609fedb215f80386d976ed406508c9a4f7fe33841c5dc26c5178b6404b";
//        String script = code.getText().toString();
//        String language = "cpp17";
//        String versionIndex = "1";
//        String inputText = inputs.getText().toString();

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
            responseData = objectMapper.readValue(jsonResponse, ResponseData.class);

            connection.disconnect();
            

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

}

