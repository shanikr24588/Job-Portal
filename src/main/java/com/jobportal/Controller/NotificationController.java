package com.jobportal.Controller;

import com.jobportal.Dto.ResponseDto;
import com.jobportal.Entity.Notification;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/notification")
@Validated
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<Notification>> getNotification(@PathVariable Long userId) throws JobPortalException{
        return new ResponseEntity<>(notificationService.getUnreadNotifications(userId), HttpStatus.OK);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<ResponseDto> readNotification(@PathVariable Long id) throws JobPortalException {
        notificationService.readNotifications(id);
        return new ResponseEntity<>(new ResponseDto("Success"), HttpStatus.OK);
    }
}
