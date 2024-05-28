package com.example.service;

import com.example.MyTelegramBot;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileStep;
import com.example.repository.AdminInfoRepository;
import com.example.repository.ProfileRepository;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class AdminInfoService {
    @Autowired
    private MyTelegramBot myTelegramBot;
    @Autowired
    private AdminInfoRepository adminInfoRepository;
    @Autowired
    private ProfileRepository profileRepository;
    public void enterLogin(Message message){
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Login");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setStep(ProfileStep.ENTER_LOGIN);
            entity.setRole(ProfileRole.ADMIN);
            entity.setStatus(ProfileStatus.NOACTIVE);
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_LOGIN)) {
            if (message.getText().equals(adminInfoRepository.findByInfoId(1).getLogin())){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setText("Password");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                myTelegramBot.sendMsg(sendMessage);
                ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
                entity.setStep(ProfileStep.ENTER_PASSWORD);
                myTelegramBot.updateProfileDB(entity);
            } else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                sendMessage.setText("Invalid login, please try again");
                myTelegramBot.sendMsg(sendMessage);
            }
        }
    }
    public void enterPassword(Message message){
         if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PASSWORD)) {
            if (message.getText().equals(adminInfoRepository.findByInfoId(1).getPassword())){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonAdminMenu());
                sendMessage.setText("Admin menu");
                myTelegramBot.sendMsg(sendMessage);
                ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
                entity.setStep(ProfileStep.DONE);
                myTelegramBot.updateProfileDB(entity);

            }else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                sendMessage.setText("Invalid password, please try again");
                myTelegramBot.sendMsg(sendMessage);
            }
        }
    }

    public void exitAdmin(Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Bo'limlardan birini tanlang.");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
            myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setStep(ProfileStep.DONE);
            entity.setRole(ProfileRole.PASSENGER);
            entity.setStatus(ProfileStatus.ACTIVE);
            myTelegramBot.updateProfileDB(entity);

        }
    }
}
