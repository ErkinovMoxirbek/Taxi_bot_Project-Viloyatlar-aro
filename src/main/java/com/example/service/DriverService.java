package com.example.service;

import com.example.MyTelegramBot;
import com.example.entity.OrderEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.OrderStatus;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileStep;
import com.example.repository.OrderRepository;
import com.example.repository.ProfileRepository;
import com.example.util.InlineKeyBoardUtil;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class DriverService {
    @Autowired
    private MyTelegramBot myTelegramBot;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private OrderRepository orderRepostory;

    public void helloDriver(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Assalomu alaykum siz tizimdan haydovchi sifatida ro'yhatdan o'tmoqchisiz!");
        myTelegramBot.sendMsg(sendMessage);
    }

    public void enterName(String text, Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Ismingizni kiriting.");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setRole(ProfileRole.DRIVER);
            entity.setStep(ProfileStep.ENTER_NAME);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_NAME)) {
            if (text != null && text.length() > 2 && (text.trim()).matches("^[A-Za-z`']+$")) {
                ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
                entity.setName(text);
                entity.setStep(ProfileStep.DONE);
                myTelegramBot.updateProfileDB(entity);
            } else {
                myTelegramBot.sendMessage("Ism tog'ri shaklda kiritilmadi qayta urining!", message.getChatId());
            }
        }

    }

    public void enterSurname(String text, Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
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
            } else {
                myTelegramBot.sendMessage("Familiya tog'ri shaklda kiritilmadi qayta urining!", message.getChatId());
            }

        }
    }

    public void enterPhone(String text, Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
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

    public void menu(String text, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Siz ro'yhatdan o'tdingiz ma'lumotlarni sozlash sozlamalar bo'limida! \nBo'limlardan birini tanlang.");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver(entity.getVisible()));
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity = profileRepository.findByUserId(message.getChatId());
            entity.setStatus(ProfileStatus.ACTIVE);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }


    public void settings(String text, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Sozlamalar bo'limi.\nBo'limlardan birini tanlang.");
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardDriver());
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        entity.setLastMessageId(tempMessage.getMessageId());
        myTelegramBot.updateProfileDB(entity);
    }

    public void editName(String text, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
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
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardDriver());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void editSurname(String text, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
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
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardDriver());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void editPhone(String text, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
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
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardDriver());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void editRole(String text, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Siz endi yo'lovchisiz, endi siz taxi xizmatidan foydalangan holda sayohat amalga oshira olasiz!");
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        entity.setLastMessageId(tempMessage.getMessageId());
        entity.setRole(ProfileRole.PASSENGER);
        entity.setStep(ProfileStep.DONE);
        myTelegramBot.updateProfileDB(entity);
        if (orderRepostory.findByProfileId(message.getChatId())!= null){
            orderRepostory.delete(orderRepostory.findByProfileId(message.getChatId()));
        }
    }

    public void exitMenu(Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Bo'limlardan birini tanlang.");
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver(entity.getVisible()));
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        entity = profileRepository.findByUserId(message.getChatId());
        entity.setLastMessageId(tempMessage.getMessageId());
        entity.setStep(ProfileStep.DONE);
        myTelegramBot.updateProfileDB(entity);
    }

    public void editCarNum(String text, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Avtomobil raqamingizni kiriting!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.phoneKeyboard());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            entity.setStep(ProfileStep.EDIT_CAR_NUM);
            myTelegramBot.updateProfileDB(entity);
        } else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.EDIT_CAR_NUM)) {
            entity.setCarNum(text);
            entity.setStep(ProfileStep.DONE);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Avtomobil raqam muvaffaqiyatli qo'shildi!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardDriver());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void infoDriver(Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        String temp = "";
        if (entity.getCarNum() != null) {
            temp = entity.getCarNum();
        } else {
            temp = "Avtomobil raqam hali kirilmagan!";
        }
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Ism: " + entity.getName() + ";\nFamiliya: " + entity.getSurname() + ";\nTelefon nomeringiz: " + entity.getPhoneNumber() + ";\nAvtomobil raqamingiz: " + temp + ";\nSiz tizimda haydovchisiz!");
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        entity.setLastMessageId(tempMessage.getMessageId());
        profileRepository.save(entity);
    }

    public void enterLine(String text, Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Siz liniyaga qo'shilmoqchisiz!\nQayerdan odam olmoqchisiz?");
            sendMessage.setReplyMarkup(InlineKeyBoardUtil.getListRegion());
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
            entity.setStep(ProfileStep.ENTER_REGION);
            entity.setLastMessageId(tempMessage.getMessageId());
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void enterFromRegion(String regionId, String regionName, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        if (entity.getStep().equals(ProfileStep.ENTER_REGION)) {
            OrderEntity orderEntity;
            if (orderRepostory.findByProfileId(entity.getUserId()) == null){
                orderEntity = new OrderEntity();
            }else{
                orderEntity = orderRepostory.findByProfileId(entity.getUserId());
            }
            orderEntity.setProfileId(message.getChatId());
            orderEntity.setFromWhereRegion(regionName);
            orderEntity.setFromWhereDistrict("");
            orderEntity.setToWhereDistrict("");
            orderEntity.setUserRole(ProfileRole.DRIVER);
            orderEntity.setOrderStatus(OrderStatus.NOACTIVE);
            orderRepostory.save(orderEntity);
            entity.setStep(ProfileStep.DONE);
            myTelegramBot.updateProfileDB(entity);
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(message.getChatId());
            deleteMessage.setMessageId(message.getMessageId());
            myTelegramBot.deleteMsg(deleteMessage);
        }
    }

    public void enterFromDistrict( Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        OrderEntity orderEntity = orderRepostory.findByProfileId(message.getChatId());
        if (entity.getStep().equals(ProfileStep.DONE)) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Eslatma: Siz bir necha tuman yoki shahar tanlay olasiz!\nTumanlar yoki shaharlar tanlang!");
            if (orderEntity.getFromWhereRegion().contains("Farg'ona")) {//regionid = 1
                sendMessage.setReplyMarkup(InlineKeyBoardUtil.getRegion1DistrictList());
            } else if (orderEntity.getFromWhereRegion().contains("Toshkent")) {//regionId = 2
                sendMessage.setReplyMarkup(InlineKeyBoardUtil.getRegion2DistrictList());
            }
//             else if (orderEntity.getFromWhereRegion().contains("Farg'ona")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Jizzax")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Navoiy")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Namangan")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Qashqadaryo")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Qoraqalpog'iston")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Samarqand")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Surxandaryo")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Toshkent")) {
//
//            }else if (orderEntity.getFromWhereRegion().contains("Xorazm")) {
//
//            }
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            entity.setStep(ProfileStep.ENTER_FROM_DISTRICT);
            myTelegramBot.updateProfileDB(entity);
            SendMessage sendMessage2 = new SendMessage();
            sendMessage2.setChatId(message.getChatId());
            sendMessage2.setText("Tumanlarni tanlab [Davom etish] tugmasini bosing \uD83D\uDC47");
            sendMessage2.setReplyMarkup(InlineKeyBoardUtil.continueNext());
            myTelegramBot.sendMsg(sendMessage2);
        }


    }
    public void enterDistrict(String districtId, String districtName, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        OrderEntity orderEntity = orderRepostory.findByProfileId(message.getChatId());
        if (entity.getStep().equals(ProfileStep.ENTER_FROM_DISTRICT)) {
            System.out.println(districtName);
            if (orderEntity.getFromWhereDistrict() != null && orderEntity.getFromWhereDistrict().contains(districtName)){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setText("Siz buni tanlagansiz!");
                myTelegramBot.sendMsg(sendMessage);
            }else {
                orderEntity.setFromWhereDistrict(orderEntity.getFromWhereDistrict()+ "  " + districtName);
                myTelegramBot.updateOrderDB(orderEntity);
                myTelegramBot.updateProfileDB(entity);
            }
        }else if (entity.getStep().equals(ProfileStep.ENTER_TO_DISTRICT)) {
            System.out.println(districtName);
            if (orderEntity.getToWhereDistrict() != null && orderEntity.getToWhereDistrict().contains(districtName)){
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setText("Siz buni tanlagansiz!");
                myTelegramBot.sendMsg(sendMessage);
            }else {
                orderEntity.setToWhereDistrict(orderEntity.getToWhereDistrict()+ "  " + districtName);
                myTelegramBot.updateOrderDB(orderEntity);
                myTelegramBot.updateProfileDB(entity);
            }
        }
    }

    public void continueStep(Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        OrderEntity orderEntity = orderRepostory.findByProfileId(message.getChatId());
        if (entity.getStep().equals(ProfileStep.ENTER_FROM_DISTRICT)) {
            enterToDistrict(message);
        }else if (entity.getStep().equals(ProfileStep.ENTER_TO_DISTRICT)) {
            entity.setStep(ProfileStep.DONE);
            entity.setVisible(Boolean.TRUE);
            orderEntity.setOrderStatus(OrderStatus.ACTIVE);
            myTelegramBot.updateOrderDB(orderEntity);
            myTelegramBot.updateProfileDB(entity);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Siz liniyadasiz yo'lovchilar siz kiritgan ma'lumot bo'yicha aloqa chiqishadi!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver(entity.getVisible()));
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(message.getChatId());
            deleteMessage.setMessageId(message.getMessageId());
            myTelegramBot.deleteMsg(deleteMessage);
            deleteMessage.setMessageId(entity.getLastMessageId());
            myTelegramBot.deleteMsg(deleteMessage);
            myTelegramBot.sendMsg(sendMessage);
            System.out.println(orderRepostory.findByProfileId(message.getChatId()));

        }
    }

    public void enterToDistrict(Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        OrderEntity orderEntity = orderRepostory.findByProfileId(message.getChatId());
        if (entity.getStep().equals(ProfileStep.ENTER_FROM_DISTRICT)) {
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(message.getChatId());
            deleteMessage.setMessageId(message.getMessageId());
            myTelegramBot.deleteMsg(deleteMessage);
            deleteMessage.setMessageId(entity.getLastMessageId());
            myTelegramBot.deleteMsg(deleteMessage);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Eslatma: Siz bir necha tuman yoki shahar tanlay olasiz! \nTumanlar yoki shaharlar tanlang!");
            if (orderEntity.getFromWhereRegion().contains("Farg'ona")) {//regionid = 1
                sendMessage.setReplyMarkup(InlineKeyBoardUtil.getRegion2DistrictList());
            } else if (orderEntity.getFromWhereRegion().contains("Toshkent")) {//regionId = 2
                sendMessage.setReplyMarkup(InlineKeyBoardUtil.getRegion1DistrictList());
            }
            Message tempMessage = myTelegramBot.sendMsg(sendMessage);
            entity.setLastMessageId(tempMessage.getMessageId());
            entity.setStep(ProfileStep.ENTER_TO_DISTRICT);
            myTelegramBot.updateProfileDB(entity);
            SendMessage sendMessage2 = new SendMessage();
            sendMessage2.setChatId(message.getChatId());
            sendMessage2.setText("Tumanlarni tanlab [Davom etish] tugmasini bosing \uD83D\uDC47");
            sendMessage2.setReplyMarkup(InlineKeyBoardUtil.continueNext());
            myTelegramBot.sendMsg(sendMessage2);
        }
    }

    public void exitLine(Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        OrderEntity orderEntity = orderRepostory.findByProfileId(message.getChatId());
        entity.setVisible(Boolean.FALSE);
        entity.setStep(ProfileStep.DONE);
        myTelegramBot.updateProfileDB(entity);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Siz liniyadan chiqdingiz!");
        sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver(entity.getVisible()));
        myTelegramBot.sendMsg(sendMessage);
        orderRepostory.delete(orderEntity);
    }

    public void lineInfo(Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        OrderEntity orderEntity = orderRepostory.findByProfileId(message.getChatId());
        if (entity.getVisible()){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Ketish: ``" + orderEntity.getFromWhereDistrict().trim().replace("  " , ", ") + "``dan\n\nBorish: ``" + orderEntity.getToWhereDistrict().trim().replace("  " , ", ") + "``ga\n\nAvtomobil: " + entity.getCarModel() + ";\nAvtomobil raqam: " + entity.getCarNum() + ";\n" + orderEntity.getAdditionalInfo());
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver(entity.getVisible()));
            myTelegramBot.sendMsg(sendMessage);
        }else{
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Siz hali liniya qo'shilmagansiz!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardDriver(entity.getVisible()));
            myTelegramBot.sendMsg(sendMessage);
        }
    }
}
