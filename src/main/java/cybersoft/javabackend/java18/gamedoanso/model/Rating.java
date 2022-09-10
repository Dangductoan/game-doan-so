package cybersoft.javabackend.java18.gamedoanso.model;

import java.time.LocalDateTime;

public class Rating {
    private int amount ;
    private String session_id;
    private String time;
    private String username;

    public Rating(int amount, String session_id, String time, String username) {
        this.amount = amount;
        this.session_id = session_id;
        this.time = time;
        this.username = username;
    }

    public Rating() {

    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
