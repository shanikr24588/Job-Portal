package com.jobportal.Entity;


import com.jobportal.Dto.NotificationDto;
import com.jobportal.Dto.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notification")
public class Notification {
    private Long id;
    private Long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
    private LocalDateTime timestamp;

    public NotificationDto toDto(){
        return new NotificationDto(
                this.id,
                this.userId,
                this.message,
                this.action,
                this.route,
                this.status,
                this.timestamp
        );
    }
}
