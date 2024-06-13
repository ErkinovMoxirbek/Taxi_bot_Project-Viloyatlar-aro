package com.example.controller;

import com.example.enums.ProfileRole;
import com.example.repository.ProfileRepository;
import com.example.service.AdminInfoService;
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
    @Autowired
    private AdminInfoService adminInfoService;
    public void handle(String data, Message message) {
        if (data != null){
            if (data.startsWith("region")){
                String [] arr = data.split("/");
                if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.DRIVER)){
                    driverService.enterFromRegion(arr[0],arr[1],message);
                    driverService.enterFromDistrict(message);
                } else if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.PASSENGER)) {
                    passengerService.enterFromRegion(arr[1], message);
                }
            } else if (data.startsWith("district") ) {
                String [] arr = data.split("/");
                if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.DRIVER)){
                    driverService.enterDistrict(arr[0],arr[1],message);
                }else if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.PASSENGER)) {
                    passengerService.enterDistrict(arr[1],message);
                }
            } else if (data.startsWith("deleteDriver")) {
                String [] arr = data.split("/");
                if (arr.length == 2) {
                    adminInfoService.deleteDriver(arr[1],message);
                }
            } else if (data.startsWith("continue")) {
                driverService.continueStep(message);
            }
        }
    }
}
