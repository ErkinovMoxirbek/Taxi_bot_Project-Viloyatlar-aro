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
    public void helloPassenger(Message message){
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
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_NAME)) {
            if (text != null && text.length() > 2 && (text.trim()).matches("^[A-Za-z`']+$")){
                ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
                entity.setName(text);
                entity.setStep(ProfileStep.DONE);
                myTelegramBot.updateProfileDB(entity);
            }else {
                myTelegramBot.sendMessage("Ism tog'ri shaklda kiritilmadi qayta urining!",message.getChatId());
            }
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
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_SURNAME)) {
            if (text != null && text.length() > 4 && (text.trim()).matches("^[A-Za-z`']+$")) {
                ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
                entity.setSurname(text);
                entity.setStep(ProfileStep.DONE);
                myTelegramBot.updateProfileDB(entity);
            }else {
                myTelegramBot.sendMessage("Familiya tog'ri shaklda kiritilmadi qayta urining!",message.getChatId());
            }

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
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PHONE)) {
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setPhoneNumber(text);
            entity.setStep(ProfileStep.DONE);
            myTelegramBot.updateProfileDB(entity);
        }
    }
    public void menu(String text, Message message){
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Siz ro'yhatdan o'tdingiz ma'lumotlarni sozlash sozlamalar bo'limida! \nBo'limlardan birini tanlang.");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setStatus(ProfileStatus.ACTIVE);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
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
        myTelegramBot.updateProfileDB(entity);
    }
    public void editName(String text, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("O'zgartirmoqchi bo'lgan ismingizni kiriting!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            entity.setStep(ProfileStep.EDIT_NAME);
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_NAME)) {
            entity.setName(text);
            entity.setStep(ProfileStep.DONE);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Ismingiz o'zgartirildi!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardPassanger());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void editSurname(String text, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("O'zgartirmoqchi bo'lgan familiyangizni kiriting!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            entity.setStep(ProfileStep.EDIT_SURNAME);
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_SURNAME)) {
            entity.setSurname(text);
            entity.setStep(ProfileStep.DONE);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Familiyangiz o'zgartirildi!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardPassanger());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void editPhone(String text, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("O'zgartirmoqchi bo'lgan telefon raqamingizni yuborish tugmasi bilan yuboring yoki (+998-XX-XXX-XX-XX) shu shaklda raqamingizni kiriting!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.phoneKeyboard());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            entity.setStep(ProfileStep.EDIT_PHONE);
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_NAME)) {
            entity.setName(text);
            entity.setStep(ProfileStep.DONE);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Telefon raqamingiz o'zgartirildi!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardPassanger());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void editRole(String text, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Siz endi haydovchisiz, endi insonlarga taxi xizmatini ko'rsatishingizga biz yordamlashamiz!");
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        entity.setLastMessageId(tempMessage.getMessageId());
        entity.setRole(ProfileRole.DRIVER);
        entity.setStep(ProfileStep.DONE);
        myTelegramBot.updateProfileDB(entity);
    }

    public void exitMenu(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Bo'limlardan birini tanlang.");
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        entity.setLastMessageId(tempMessage.getMessageId());
        entity.setStep(ProfileStep.DONE);
        myTelegramBot.updateProfileDB(entity);
    }

    public void infoPassenger(Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Ism: " + entity.getName() + ";\nFamiliya: " + entity.getSurname() + ";\nTelefon nomeringiz: " + entity.getPhoneNumber() + ";\nSiz tizimda yo'lovchisiz!");
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        entity.setLastMessageId(tempMessage.getMessageId());
        profileRepository.save(entity);
    }
}
