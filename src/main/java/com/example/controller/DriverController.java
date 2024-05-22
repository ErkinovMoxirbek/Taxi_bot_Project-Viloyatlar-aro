package com.example.controller;

import com.example.MyTelegramBot;
import com.example.enums.ProfileStep;
import com.example.repository.ProfileRepository;
import com.example.service.DriverService;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
            } else if (text.startsWith("✏️ Ismni o'zgartirish")) {
                driverService.editName(text,message);
            } else if (text.startsWith("✏\uFE0F Familiyani o'zgartirish")) {
                driverService.editSurname(text,message);
            }  else if (text.startsWith("✏️ Telefon raqamni o'zgartish")) {
                driverService.editPhone(text,message);
            } else if (text.startsWith("Haydovchidan ➡\uFE0F Yo'lovchiga o'tishlik")) {
                driverService.editRole(text,message);
            }else if (text.startsWith("➕ Avtomobil nomerini tahrirlash")) {
                driverService.editCarNum(text,message);
            } else if (text.startsWith("\uD83C\uDFE0 Bosh menyuga qaytish")) {
                driverService.exitMenu(message);
            } else if (text.startsWith("\uD83D\uDDC2 Ma'lumotlaringiz")) {
                driverService.infoDriver(message);
            } else if (text.startsWith("\uD83D\uDE95 Liniyaga kirish")) {
                driverService.enterLine(text ,message);
            } else if (text.startsWith("\uD83D\uDE95 Liniyadan chiqish")) {
                driverService.exitLine(message);
            } else if (text.startsWith("\uD83D\uDDC2 Liniya ma'lumotlari")) {
                driverService.lineInfo(message);
            } else if (profileRepository.findByUserId(message.getChatId()) != null){
                if ( profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_NAME)){
                    driverService.enterName(text,message);
                    driverService.enterSurname(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_SURNAME)) {
                    driverService.enterSurname(text,message);
                    driverService.enterPhone(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PHONE)) {
                    System.out.println(text);
                    if (text.startsWith("+998") && text.length() == 13 && (text.trim()).matches("^[0-9+]+$")){
                        driverService.enterPhone(text,message);
                        driverService.enterCarModel(text,message);
                    }else {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(message.getChatId());
                        sendMessage.setText("Kechirasiz, siz O'zbekiston aloqa operatoridan foydalanmayabsiz iltimos qayta urining!");
                        sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                        myTelegramBot.sendMsg(sendMessage);
                    }
                }else if ( profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_CAR_MODEL)){
                    driverService.enterCarModel(text,message);
                    driverService.enterCarNum(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_CAR_NUM)) {
                    driverService.enterCarNum(text,message);
                    driverService.menu(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_NAME)) {
                    driverService.editName(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_SURNAME)) {
                    driverService.editSurname(text,message);
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_PHONE)) {
                    if (text.startsWith("+998") && text.length() == 13 && (text.trim()).matches("^[0-9+]+$")) {
                        driverService.editPhone(text, message);
                    }else {
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(message.getChatId());
                        sendMessage.setText("Kechirasiz, siz O'zbekiston aloqa operatoridan foydalanmayabsiz iltimos qayta urining!");
                        sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                        myTelegramBot.sendMsg(sendMessage);
                    }
                } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_CAR_NUM)) {
                    driverService.editCarNum(text,message);
                }
            }
        }

    }
}
