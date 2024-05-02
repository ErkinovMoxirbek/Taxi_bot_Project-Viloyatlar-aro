package com.example.controller;

import com.example.MyTelegramBot;
import com.example.enums.ProfileStep;
import com.example.repository.ProfileRepository;
import com.example.service.PassengerService;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Controller
public class PassengerController {
    @Autowired
    private PassengerService passengerService;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MyTelegramBot myTelegramBot;
    public void handle(String text, Message message){
        if (text != null){
            if (text.equals("Yo'lovchi")){
                passengerService.helloPassenger(message);
                passengerService.enterName(text,message);
            }else if (text.startsWith("⚙\uFE0F Sozlamalar")){
                passengerService.settings(text,message);
            }else if (text.startsWith("⚙️ Sozlamalar")) {
                passengerService.settings(text,message);
            } else if (text.startsWith("\uD83D\uDDC2 Ma'lumotlaringiz")) {
                passengerService.infoPassenger(message);
            } else if (text.startsWith("✏\uFE0F Ismni o'zgartirish")) {
                passengerService.editName(text,message);
            } else if (text.startsWith("✏\uFE0F Familiyani o'zgartirish")) {
                passengerService.editSurname(text,message);
            }  else if (text.startsWith("✏️ Telefon raqamni o'zgartish")) {
                passengerService.editPhone(text,message);
            } else if (text.startsWith("Yolovchidan ➡️ Haydovchiga o'tishlik")) {
                passengerService.editRole(text,message);
            } else if (text.startsWith("\uD83C\uDFE0 Bosh menyuga qaytish")) {
                passengerService.exitMenu(message);
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
                    if (text.startsWith("+998") && text.length() == 13 && (text.trim()).matches("^[0-9+]+$")){
                        passengerService.enterPhone(text,message);
                        passengerService.menu(text,message);
                    }else {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(message.getChatId());
                        sendMessage.setText("Kechirasiz, siz O'zbekiston aloqa operatoridan foydalanmayabsiz iltimos qayta urining!");
                        sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                        myTelegramBot.sendMsg(sendMessage);
                    }
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_NAME)) {
                    passengerService.editName(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_SURNAME)) {
                    passengerService.editSurname(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_PHONE)) {
                    if (text.startsWith("+998") && text.length() == 13) {
                        passengerService.editPhone(text, message);
                    }else {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(message.getChatId());
                        sendMessage.setText("Kechirasiz, siz O'zbekiston aloqa operatoridan foydalanmayabsiz iltimos qayta urining!");
                        sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                        myTelegramBot.sendMsg(sendMessage);
                    }
                }
            }
        }
    }
}
