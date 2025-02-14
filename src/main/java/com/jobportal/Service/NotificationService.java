package com.jobportal.Service;

import com.jobportal.Dto.NotificationDto;
import com.jobportal.Entity.Notification;
import com.jobportal.Exception.JobPortalException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface NotificationService {
    public void sendNotification(NotificationDto notificationDto) throws JobPortalException;
    public List<Notification> getUnreadNotifications(Long userId);

    public void readNotifications(Long id) throws JobPortalException;

}
