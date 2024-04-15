package com.example.controller;

import com.example.enums.ProfileStep;
import com.example.repository.ProfileRepository;
import com.example.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private ProfileRepository profileRepository;
    public void handle(String text, Message message){
        if (text != null){
            if (text.equals("Yo'lovchi")){
                passengerService.helloDriver(message);
                passengerService.enterName(text,message);
            }else if (text.startsWith("⚙\uFE0F Sozlamalar")){
                passengerService.settings(text,message);
            }
            else if (profileRepository.findByUserId(message.getChatId()) != null){
                if ( profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_NAME)){
                    passengerService.enterName(text,message);
                    passengerService.enterSurname(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_SURNAME)) {
                    passengerService.enterSurname(text,message);
                    passengerService.enterPhone(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PHONE)) {
                    System.out.println(text);
                    if (text.startsWith("+998")){
                        passengerService.enterPhone(text,message);
                        passengerService.menu(text,message);
                    }
                }
            }
        }
    }
}
