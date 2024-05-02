package com.example.controller;

import com.example.MyTelegramBot;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileStep;
import com.example.info.InfoBot;
import com.example.repository.ProfileRepository;
import com.example.service.DriverService;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
public class MainController {
    @Autowired
    private MyTelegramBot myTelegramBot;
    @Autowired
    private DriverController driverController;
    @Autowired
    private PassengerController passengerController;
    @Autowired
    private InfoBot infoBot;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private DriverService driverService;

    public void handle(String text, Message message) {
        if (text != null) {
            if (text.equals("/start")) {
                helloWorld(text, message);
            } else if (text.equals("Bot haqida")) {
                infoBot.handle(message);
            } else if (text.equals("Haydovchi") ||
                    profileRepository.findByUserId(message.getChatId()) != null &&
                            profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.DRIVER)) {
                driverController.handle(text, message);
            } else if (text.equals("Yo'lovchi") ||
                    profileRepository.findByUserId(message.getChatId()) != null &&
                            profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.PASSENGER)) {
                passengerController.handle(text, message);
            }
        } else if (message.hasContact()) {
            ForwardMessage forwardMessage = new ForwardMessage();
            forwardMessage.setChatId("@TM6669008");
            forwardMessage.setMessageId(message.getMessageId());
            forwardMessage.setFromChatId(message.getChatId());
            myTelegramBot.sendMsg(forwardMessage);
            message.setText(message.getContact().getPhoneNumber());
            if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.DRIVER)) {
                driverController.handle(message.getContact().getPhoneNumber(), message);
            } else if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.PASSENGER)) {
                passengerController.handle(message.getContact().getPhoneNumber(), message);
            }
        }


    }

    public void helloWorld(String text, Message message) {
        if (text.equals("/start")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Bo'limlardan birini tanlang!");
            sendMessage.enableHtml(true);
            ProfileEntity entity;
            if (profileRepository.findByUserId(message.getChatId()) == null) {
                entity = new ProfileEntity();
                entity.setUserId(message.getChatId());
                entity.setRole(ProfileRole.DONE);
                entity.setStatus(ProfileStatus.NOACTIVE);
                entity.setStep(ProfileStep.DONE);
                entity.setLastMessageId(0);
                entity.setVisible(Boolean.TRUE);
                profileRepository.save(entity);
            }else {
                entity = profileRepository.findByUserId(message.getChatId());
            }
            if (profileRepository.findByUserId(message.getChatId()).getStatus().equals(ProfileStatus.NOACTIVE)) {
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboard());
                entity.setRole(ProfileRole.DONE);
                entity.setStep(ProfileStep.DONE);
                entity.setLastMessageId(0);
                myTelegramBot.updateProfileDB(entity);
            } else if (profileRepository.findByUserId(message.getChatId()).getStatus().equals(ProfileStatus.ACTIVE)) {
                if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.DRIVER)) {
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver());
                } else if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.PASSENGER)) {
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
                }
                entity.setStep(ProfileStep.DONE);
                entity.setLastMessageId(0);
                myTelegramBot.updateProfileDB(entity);
            }
            myTelegramBot.sendMsg(sendMessage);

        }
    }
}
