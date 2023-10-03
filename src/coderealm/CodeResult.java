package coderealm;

public class CodeResult {
    
  private String Errors;
  private String Result;
  private String Stats;
  private String Files;

    public void setErrors(String Errors) {
        this.Errors = Errors;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public void setStats(String Stats) {
        this.Stats = Stats;
    }

    public void setFiles(String Files) {
        this.Files = Files;
    }
  
//    private String timeStamp;
//    private String status;
//    private String output;
//    private String error;
//    private String language;
//    private String info;
//
//    public String getTimeStamp() {
//        return timeStamp;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public String getOutput() {
//        return output;
//    }
//
//    public String getError() {
//        return error;
//    }
//
//    public String getLanguage() {
//        return language;
//    }
//
//    public String getInfo() {
//        return info;
//    }

    public String getErrors() {
        return Errors;
    }

    public String getResult() {
        return Result;
    }

    public String getStats() {
        return Stats;
    }

    public String getFiles() {
        return Files;
    }
    
}