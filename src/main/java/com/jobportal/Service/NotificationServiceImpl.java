package com.jobportal.Service;

import com.jobportal.Dto.NotificationDto;
import com.jobportal.Dto.NotificationStatus;
import com.jobportal.Entity.Notification;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Repository.NotificationRepository;
import com.jobportal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationDto notificationDto) throws JobPortalException {
        notificationDto.setId(Utilities.getNextSequence("notification"));
        notificationDto.setStatus(NotificationStatus.UNREAD);
        notificationDto.setTimestamp(LocalDateTime.now());
        notificationRepository.save(notificationDto.toEntity());
    }

    @Override
    public List<Notification> getUnreadNotifications(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public void readNotifications(Long id) throws JobPortalException {
        Notification noti = notificationRepository.findById(id).orElseThrow(()-> new JobPortalException("No Notification Found"));
        noti.setStatus(NotificationStatus.READ);
        notificationRepository.save(noti);
    }
}
