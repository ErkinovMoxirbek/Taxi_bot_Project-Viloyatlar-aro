package com.example.controller;

import com.example.MyTelegramBot;
import com.example.enums.ProfileStep;
import com.example.repository.ProfileRepository;
import com.example.service.DriverService;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
public class DriverController {
    @Autowired
    private MyTelegramBot myTelegramBot;
    @Autowired
    private DriverService driverService;
    @Autowired
    private ProfileRepository profileRepository;

    public void handle(String text, Message message){
        System.out.println(text);
        if (text != null){
            if (text.equals("Haydovchi")){
                driverService.helloDriver(message);
                driverService.enterName(text,message);
            } else if (text.startsWith("⚙️ Sozlamalar")) {
                driverService.settings(text,message);
            } else if (profileRepository.findByUserId(message.getChatId()) != null){
                if ( profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_NAME)){
                    driverService.enterName(text,message);
                    driverService.enterSurname(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_SURNAME)) {
                    driverService.enterSurname(text,message);
                    driverService.enterPhone(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PHONE)) {
                    System.out.println(text);
                    if (text.startsWith("+998")){
                        driverService.enterPhone(text,message);
                        driverService.menu(text,message);
                    }
                }
            }
        }

    }
}
