package com.example.service;

import com.example.MyTelegramBot;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileStep;
import com.example.repository.AdminInfoRepository;
import com.example.repository.ProfileRepository;
import com.example.util.InlineKeyBoardUtil;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Service
public class AdminInfoService {
    @Autowired
    private MyTelegramBot myTelegramBot;
    @Autowired
    private AdminInfoRepository adminInfoRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public void enterLogin(Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
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
            if (message.getText().equals(adminInfoRepository.findByInfoId(1).getLogin())) {
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

    public void enterPassword(Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PASSWORD)) {
            if (message.getText().equals(adminInfoRepository.findByInfoId(1).getPassword())) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonAdminMenu());
                sendMessage.setText("Admin menu");
                myTelegramBot.sendMsg(sendMessage);
                ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
                entity.setStep(ProfileStep.DONE);
                entity.setStatus(ProfileStatus.ACTIVE);
                myTelegramBot.updateProfileDB(entity);

            } else {
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

    public void addDriver(Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (entity.getStep().equals(ProfileStep.DONE)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            sendMessage.setText("Qo'shmoqchi bo'lgan haydovchini nomerini kiriting!\nNamuna: +9989177777777");
            myTelegramBot.sendMsg(sendMessage);
            entity.setStep(ProfileStep.ENTER_DRIVER_PHONE);
            myTelegramBot.updateProfileDB(entity);
        } else if (entity.getStep().equals(ProfileStep.ENTER_DRIVER_PHONE)) {
            if (message.getText().startsWith("+998") && message.getText().length() == 13) {
                List<ProfileEntity> list = profileRepository.findAllByPhoneNumber(message.getText());
                if (list.size() == 1) {
                    ProfileEntity entity1 = list.get(0);
                    entity1.setSendMessageGroup(Boolean.TRUE);
                    myTelegramBot.updateProfileDB(entity1);
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonAdminMenu());
                    sendMessage.setText("Qo'shildi!\n\nAdmin menu");
                    myTelegramBot.sendMsg(sendMessage);
                    entity.setStep(ProfileStep.DONE);
                    myTelegramBot.updateProfileDB(entity);
                    sendMessage.setChatId(entity1.getUserId());
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
                    sendMessage.setText("Sizni admin haydovchilik qo'shishdi!");
                    myTelegramBot.sendMsg(sendMessage);
                } else if (list.size() > 1) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonAdminMenu());
                    sendMessage.setText("Bu birdan oshiq akkauntlar raqami, Siz agar haydovchini qo'shmoqchi bo'lsangiz haydovchi raqamini boshqa akkauntlaridan chiqarib yuborishini yoki yangi raqamga o'zgartirib siz qayta murojat qilishini so'rang!\nBu tizim barqaror ishlashi uchun zarur!\nRaqamni o'zgartirish: Sozlamalar>Telefon raqamni o'zgartirish buni haydovchi istagidagi inson telegram bot orqali amalga oshira oladi!\n\nAdmin menu");
                    myTelegramBot.sendMsg(sendMessage);
                    entity.setStep(ProfileStep.DONE);
                    myTelegramBot.updateProfileDB(entity);
                } else if (list.size() == 0) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonAdminMenu());
                    sendMessage.setText("Bunday raqamdan hali hech kim ro'yhatdan o'tmagan!\n\nAdmin menu");
                    myTelegramBot.sendMsg(sendMessage);
                    entity.setStep(ProfileStep.DONE);
                    myTelegramBot.updateProfileDB(entity);
                }
            } else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                sendMessage.setText("O'zbekiston aloqa operatorlaridan foydalanmadi yoki raqam tog'ri kiritilmadi, Qayta urining!");
                myTelegramBot.sendMsg(sendMessage);
            }
        }
    }

    public void searchDriver(Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            sendMessage.setText("Telefon nomerni kiriting!");
            myTelegramBot.sendMsg(sendMessage);
            ProfileEntity profile = profileRepository.findByUserId(message.getChatId());
            profile.setStep(ProfileStep.ENTER_SEARCH_DRIVER_PHONE);
            profileRepository.save(profile);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_SEARCH_DRIVER_PHONE) ) {
            if (message.getText().startsWith("+998") && message.getText().length() == 13) {
                List<ProfileEntity> list = profileRepository.findAllBySendMessageGroupAndStatusAndPhoneNumber(Boolean.TRUE, ProfileStatus.ACTIVE, message.getText());
                if (list.size() == 1) {
                    ProfileEntity profile = list.get(0);
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setText("Ism: " + profile.getName() + ";\nTelefon raqam: " + profile.getPhoneNumber());
                    sendMessage.setReplyMarkup(InlineKeyBoardUtil.deleteDriver(profile.getUserId()));
                    profile.setLastMessageId(myTelegramBot.sendMsg(sendMessage).getMessageId());
                    myTelegramBot.updateProfileDB(profile);
                } else if (list.size() == 0) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setText("Bu telefon raqamda haydovchi yo'q qayta urinib ko'ring!");
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                    myTelegramBot.sendMsg(sendMessage);
                } else {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(message.getChatId());
                    sendMessage.setText("Xatolik!");
                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonAdminMenu());
                    myTelegramBot.sendMsg(sendMessage);
                }
            }else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
                sendMessage.setText("O'zbekiston aloqa operatorlaridan foydalanmadi yoki raqam tog'ri kiritilmadi, Qayta urining!");
                myTelegramBot.sendMsg(sendMessage);
            }
        }
    }

    public void deleteDriver(String driverId,Message message) {
        ProfileEntity entity = profileRepository.findByUserId(Long.valueOf(driverId));
        entity.setStep(ProfileStep.DONE);
        entity.setSendMessageGroup(Boolean.FALSE);
        profileRepository.save(entity);
        ProfileEntity profile = profileRepository.findByUserId(message.getChatId());
        profile.setStep(ProfileStep.DONE);
        profileRepository.save(profile);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Haydovchilikdan chiqarildi!\n\nAdmin menu!");
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonAdminMenu());
        myTelegramBot.sendMsg(sendMessage);
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(message.getChatId());
        deleteMessage.setMessageId(message.getMessageId());
        myTelegramBot.deleteMsg(deleteMessage);
        sendMessage.setText("Sizni admin haydovchilikdan chiqardi!");
        sendMessage.setChatId(driverId);
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
        myTelegramBot.sendMsg(sendMessage);
    }

//    public void listDriver(Message message) {
//        List<ProfileEntity> list = profileRepository.findAllBySendMessageGroupAndStatus(Boolean.TRUE,ProfileStatus.ACTIVE);
//        if (list.size() > 0) {
//            SendMessage sendMessage = new SendMessage();
//            sendMessage.setChatId(message.getChatId());
//            for (ProfileEntity entity : list) {
//                sendMessage.setText("Ism: " + entity.getName() + ";\nTel. raqam: " + entity.getPhoneNumber());
//                sendMessage.setReplyMarkup(InlineKeyBoardUtil.);
//            }
//        }
//    }
}
