/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Date;

/**
 *
 * @author hp
 */
@JsonPropertyOrder({ "timestamp", "level", "message" })
public class Rabbitmessage {
    private Date timestamp;
    private String level;
    private String message;

    public Rabbitmessage() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
