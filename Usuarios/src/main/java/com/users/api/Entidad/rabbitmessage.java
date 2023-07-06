package com.users.api.Entidad;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class rabbitmessage implements Serializable {
      
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("level")
    private String level;

    @JsonProperty("message")
    private String message;
}
