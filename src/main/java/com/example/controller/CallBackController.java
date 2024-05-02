package com.example.controller;

import com.example.enums.ProfileRole;
import com.example.repository.ProfileRepository;
import com.example.service.DriverService;
import com.example.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
public class CallBackController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private ProfileRepository profileRepository;
    public void handle(String data, Message message) {
        if (data != null){
            if (data.startsWith("region")){
                String [] arr = data.split("/");
                if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.DRIVER)){
                    driverService.enterFromRegion(arr[0],arr[1],message);
                    driverService.enterFromDistrict(null,null,message);
                }
            } else if (data.startsWith("district") || data.startsWith("next")) {
                String [] arr = data.split("/");
                if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.DRIVER)){
                    driverService.enterFromDistrict(arr[0],arr[1],message);
                   //todo
                }
            }
        }
    }
}
