package com.example.controller;

import com.example.MyTelegramBot;
import com.example.entity.AdminInfoEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileStep;
import com.example.info.InfoBot;
import com.example.repository.AdminInfoRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ProfileRepository;
import com.example.service.DriverService;
import com.example.service.PassengerService;
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
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AdminInfoController adminInfoController;
    @Autowired
    private AdminInfoRepository adminInfoRepository;
    @Autowired
    private PassengerService passengerService;

    public void handle(String text, Message message) {
        if (message.getChatId() < 0){
            passengerService.checkUser(message);
        }
        if (adminInfoRepository.findByInfoId(1) == null){
            AdminInfoEntity adminInfoEntity = new AdminInfoEntity();
            adminInfoRepository.save(adminInfoEntity);
        }
        if (text != null) {
            if (text.equals("/start") || text.equals("❌ Bekor qilish")) {
                helloWorld(text, message);
            } else if (text.equals("/admin") || profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.ADMIN)){
                adminInfoController.handle(message);
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
                String temp = message.getContact().getPhoneNumber();
                if (message.getContact().getPhoneNumber().startsWith("998")) {
                    temp = "+" + temp;
                }
                passengerController.handle(temp, message);
            }
        }


    }

    public void helloWorld(String text, Message message) {
        if (text.equals("/start") || text.equals("❌ Bekor qilish")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            if (profileRepository.findByUserId(message.getChatId()) == null){
                sendMessage.setText("Assalomu alaykum sizni ko'rib turganimizdan hursandmiz!");
            } else if (!profileRepository.findByUserId(message.getChatId()).getVisible()) {
                ProfileEntity profileEntity = profileRepository.findByUserId(message.getChatId());
                profileEntity.setStep(ProfileStep.DONE);
                myTelegramBot.updateProfileDB(profileEntity);
                message.setText("Yo'lovchi");
                handle("Yo'lovchi",message);
            } else if (profileRepository.findByUserId(message.getChatId()).getStatus().equals(ProfileStatus.NOACTIVE)) {
                ProfileEntity profileEntity = profileRepository.findByUserId(message.getChatId());
                profileEntity.setStep(ProfileStep.DONE);
                profileEntity.setRole(ProfileRole.PASSENGER);
                profileEntity.setStatus(ProfileStatus.ACTIVE);
                myTelegramBot.updateProfileDB(profileEntity);
            } else {
                sendMessage.setText("Bo'limlardan birini tanlang!");
            }
            sendMessage.enableHtml(true);
            ProfileEntity entity;
            if (orderRepository.findByProfileId(message.getChatId()) != null) {
                orderRepository.delete(orderRepository.findByProfileId(message.getChatId()));
            }
            if (profileRepository.findByUserId(message.getChatId()) == null) {
                entity = new ProfileEntity();
                entity.setUserId(message.getChatId());
                entity.setRole(ProfileRole.PASSENGER);
                entity.setStatus(ProfileStatus.NOACTIVE);
                entity.setStep(ProfileStep.DONE);
                entity.setLastMessageId(0);
                entity.setVisible(Boolean.FALSE);
                profileRepository.save(entity);
                message.setText("Yo'lovchi");
                myTelegramBot.sendMsg(sendMessage);
                handle("Yo'lovchi",message);
            }else {
                entity = profileRepository.findByUserId(message.getChatId());
            }
//            if (profileRepository.findByUserId(message.getChatId()).getStatus().equals(ProfileStatus.NOACTIVE)) {
//                entity.setRole(ProfileRole.DONE);
//                entity.setStep(ProfileStep.DONE);
//                entity.setLastMessageId(0);
//                myTelegramBot.updateProfileDB(entity);
//            }
             if (profileRepository.findByUserId(message.getChatId()).getStatus().equals(ProfileStatus.ACTIVE)) {
                 if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.ADMIN) && profileRepository.findByUserId(message.getChatId()).getStatus().equals(ProfileStatus.ACTIVE)) {
                     sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonAdminMenu());
                 }
                if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.DRIVER)) {
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver(entity.getVisible()));
                } else if (profileRepository.findByUserId(message.getChatId()).getRole().equals(ProfileRole.PASSENGER)) {
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
                }
                entity.setStep(ProfileStep.DONE);
                entity.setLastMessageId(0);
                myTelegramBot.updateProfileDB(entity);
                myTelegramBot.sendMsg(sendMessage);
            }

        }
    }
}
