package com.jobportal.Service;

import com.jobportal.Dto.LoginDto;
import com.jobportal.Dto.NotificationDto;
import com.jobportal.Dto.ResponseDto;
import com.jobportal.Dto.UserDto;
import com.jobportal.Entity.OTP;
import com.jobportal.Entity.User;
import com.jobportal.Exception.JobPortalException;
import com.jobportal.Repository.NotificationRepository;
import com.jobportal.Repository.OTPRepository;
import com.jobportal.Repository.UserRepository;

import com.jobportal.Utility.Data;
import com.jobportal.Utility.Utilities;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private NotificationService notificationService;

    @Override
    public UserDto registerUser(UserDto userDto) throws JobPortalException {

        Optional<User> optional = userRepository.findByEmail(userDto.getEmail());
        if (optional.isPresent()) throw new JobPortalException("USER_FOUND");
        userDto.setProfileId(profileService.createProfile(userDto.getEmail()));
        userDto.setId(Utilities.getNextSequence("users"));
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDto.toEntity();
        user = userRepository.save(user);
        return user.toDto();

    }

    @Override
    public UserDto getUserByEmail(String email) throws JobPortalException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        return user.toDto();
    }

    @Override
    public UserDto loginUser(LoginDto loginDto) throws JobPortalException {
        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(() -> new JobPortalException("USER_NOT_FOUND"));
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) throw new JobPortalException("INVaLID_CREDENTIALS");
        return user.toDto();
    }

    @Override
    public Boolean sendOtp(String email) throws Exception{
        userRepository.findByEmail(email).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));

        MimeMessage mm = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mm, true);
        message.setTo(email);
        message.setSubject("Your OTP Code");
        String genOtp= Utilities.generateOtp();
        OTP otp = new OTP(email, genOtp, LocalDateTime.now());
        otpRepository.save(otp);
        message.setText(Data.getMessageBody(genOtp),true);
        javaMailSender.send(mm);
        return true;
    }

    @Override
    public Boolean verifyOtp(String email, String otp) throws JobPortalException {
        OTP otpEntity=  otpRepository.findById(email).orElseThrow(()->new JobPortalException("OTP_NOT_FOUND"));
        if(!otpEntity.getOtpCode().equals(otp)) throw new JobPortalException("OTP_INCORRECT");
        return true;

    }

    @Override
    public ResponseDto changePassword(LoginDto loginDto) throws JobPortalException {

        User user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow(()-> new JobPortalException("USER_NOT_FOUND"));
        user.setPassword(passwordEncoder.encode(loginDto.getPassword()));
        userRepository.save(user);
        NotificationDto noti= new NotificationDto();
        noti.setUserId(user.getId());
        noti.setMessage("Password Reset Successfull");
        noti.setAction("Password Reset");
        notificationService.sendNotification(noti);
        return new ResponseDto("Password changed successfully");
    }

    @Scheduled(fixedRate = 3000)
    public void removeExpiredOTPs(){
        LocalDateTime expiry= LocalDateTime.now().minusMinutes(5);
        List<OTP>expiredOTPs = otpRepository.findByCreationTimeBefore(expiry);
        if (!expiredOTPs.isEmpty()){
            otpRepository.deleteAll(expiredOTPs);
        }
    }
}
