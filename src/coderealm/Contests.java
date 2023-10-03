package coderealm;

/*
name	Contest name.
url	Contest link.
start_time	Start time of the contest in UTC (Format: %Y-%m-%dT%H:%M:%S.%LZ) if exists, - otherwise.
end_time	End time of the contest in UTC (Format: %Y-%m-%dT%H:%M:%S.%LZ) if exists, - otherwise.
duration	Contest duration in seconds if exists, - otherwise.
in_24_hours	Yes if the contest starts within the next 24 hours, No otherwise.
status	CODING if the contest is running, BEFORE otherwise.
*/
public class Contests {
    private String name;
    private String url;
    private String start_time;
    private String end_time;
    private String duration;
    private String in_24_hours;
    private String status;

    public Contests() {
    }

    public Contests(String name, String url, String start_time, String end_time, String duration, String in_24_hours, String status) {
        this.name = name;
        this.url = url;
        this.start_time = start_time;
        this.end_time = end_time;
        this.duration = duration;
        this.in_24_hours = in_24_hours;
        this.status = status;
    }

    public Contests(String name, String start_time, String end_time,String duration ) {
        this.name = name;
        this.start_time = start_time;
        this.end_time = end_time;
        this.duration = duration;
        
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getDuration() {
        return duration;
    }

    public String getIn_24_hours() {
        return in_24_hours;
    }

    public String getStatus() {
        return status;
    }

}
