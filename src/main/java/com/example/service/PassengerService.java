package com.example.service;

import com.example.MyTelegramBot;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileStep;
import com.example.repository.ProfileRepository;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class PassengerService {
    @Autowired
    private MyTelegramBot myTelegramBot;
    @Autowired
    private ProfileRepository profileRepository;
    public void helloDriver(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Assalomu alaykum siz tizimdan yo'lovchi sifatida ro'yhatdan o'tmoqchisiz!");
        myTelegramBot.sendMsg(sendMessage);
    }
    public void enterName(String text, Message message){
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Ismingizni kiriting.");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setRole(ProfileRole.PASSENGER);
            entity.setStep(ProfileStep.ENTER_NAME);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_NAME)) {
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setName(text);
            entity.setStep(ProfileStep.DONE);
            myTelegramBot.updateDB(entity);
        }

    }
    public void enterSurname(String text, Message message){
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Familiyangiz kiriting.");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setStep(ProfileStep.ENTER_SURNAME);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_SURNAME)) {
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setSurname(text);
            entity.setStep(ProfileStep.DONE);
            myTelegramBot.updateDB(entity);
        }
    }
    public void enterPhone(String text, Message message){
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Telefon raqamingizni (+998-XX-XXX-XX-XX) shu shaklda kiriting yoki kontaktni yuborish tugmasini bosing.");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.phoneKeyboard());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setStep(ProfileStep.ENTER_PHONE);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PHONE)) {
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setPhoneNumber(text);
            entity.setStep(ProfileStep.DONE);
            myTelegramBot.updateDB(entity);
        }
    }
    public void menu(String text, Message message){
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Siz ro'yhatdan o'tdingiz ma'lumotlarni sozlash sozlamalar bo'limida! \nBo'limlardan birini tanlang.");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setStatus(ProfileStatus.ACTIVE);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateDB(entity);
        }
    }
    public void settings(String text, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Sozlamalar bo'limi.\nBo'limlardan birini tanlang.");
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardPassanger());
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        entity.setLastMessageId(tempMessage.getMessageId());
        myTelegramBot.updateDB(entity);
    }
}
