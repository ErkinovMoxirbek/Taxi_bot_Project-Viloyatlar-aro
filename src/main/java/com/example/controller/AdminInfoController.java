package com.example.controller;

import com.example.MyTelegramBot;
import com.example.enums.ProfileStep;
import com.example.repository.AdminInfoRepository;
import com.example.repository.ProfileRepository;
import com.example.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
public class AdminInfoController {
    @Autowired
    private MyTelegramBot myTelegramBot;
    @Autowired
    private AdminInfoRepository adminInfoRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AdminInfoService adminInfoService;

    public void handle(Message message){
        if (message.getText().equals("/admin")){
            adminInfoService.enterLogin(message);
        } else if (message.getText().equals("Orqaga")) {
            adminInfoService.exitAdmin(message);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_LOGIN)) {
            adminInfoService.enterLogin(message);

        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PASSWORD)) {
            adminInfoService.enterPassword(message);
        }
    }
}
