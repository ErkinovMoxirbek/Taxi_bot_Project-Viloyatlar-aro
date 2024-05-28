package com.example.service;

import com.example.MyTelegramBot;
import com.example.entity.OrderEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.ProfileStep;
import com.example.repository.OrderRepository;
import com.example.repository.ProfileRepository;
import com.example.util.InlineKeyBoardUtil;
import com.example.util.ReplyKeyboardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import javax.swing.*;

@Service
public class PassengerService {
    @Autowired
    private MyTelegramBot myTelegramBot;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private OrderRepository orderRepository;

    public void helloPassenger(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Siz aval tizimdan ro'yhatdan o'tishingiz kerak bu bir marta talab etiladi va sizga ko'p qulayliklar yaratishimizga biz uchun yordam!");
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
            entity.setRole(ProfileRole.PASSENGER);
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
            sendMessage.setText("Telefon raqamingizni (+998-XX-XXX-XX-XX) shu shaklda kiriting yoki kontaktni yuborish tugmasini bosing.\n\nEslatma: Albatta siz yuborayotgan telefon raqami ishlashligi lozim. Chunki haydovchi siz yuborayotgan raqam orqali siz bilan aloqa chiqadi! ");
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
            entity.setVisible(Boolean.TRUE);
            myTelegramBot.updateProfileDB(entity);
        }
    }

    public void menu(String text, Message message) {
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)) {
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
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardPassanger());
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
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.settingsKeyboardPassanger());
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
        if (orderRepository.findByProfileId(message.getChatId()) != null) {
            orderRepository.delete(orderRepository.findByProfileId(message.getChatId()));
        }
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
        sendMessage.setText("Ism: " + entity.getName() + ";\nFamiliya: " + entity.getSurname() + ";\nTelefon raqamingiz: " + entity.getPhoneNumber() + ";\nAgar ma'lumotlarni o'zgartimoqchi bo'lsangiz sozlamalar bo'limida buni bajara olasiz!");
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        entity.setLastMessageId(tempMessage.getMessageId());
        profileRepository.save(entity);
    }

    public void searchTaxi(Message message) {
        if (orderRepository.findByProfileId(message.getChatId()) != null) {
            orderRepository.delete(orderRepository.findByProfileId(message.getChatId()));
        }
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Qayerdan!");
        sendMessage.setReplyMarkup(InlineKeyBoardUtil.getListRegion());
        Message tempMessage = myTelegramBot.sendMsg(sendMessage);
        entity.setLastMessageId(tempMessage.getMessageId());
        profileRepository.save(entity);
    }

    public void enterFromRegion(String region, Message message) {
        ProfileEntity entity = profileRepository.findByUserId(message.getChatId());
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(message.getChatId());
        deleteMessage.setMessageId(message.getMessageId());
        myTelegramBot.deleteMsg(deleteMessage);
        OrderEntity entityOrder = new OrderEntity();
        entityOrder.setProfileId(message.getChatId());
        entityOrder.setFromWhereRegion(region);
        entityOrder.setFromWhereDistrict("");
        entityOrder.setToWhereDistrict("");
        entityOrder.setUserRole(ProfileRole.PASSENGER);
        orderRepository.save(entityOrder);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("Siz qaysi tuman yoki shahardan ketmoqchisiz tanlang!");
        if (region.startsWith("Farg'ona")) {
            sendMessage.setReplyMarkup(InlineKeyBoardUtil.getRegion1DistrictList());
        } else if (region.startsWith("Toshkent")) {
            sendMessage.setReplyMarkup(InlineKeyBoardUtil.getRegion2DistrictList());
        }
        myTelegramBot.sendMsg(sendMessage);
    }

    public void enterDistrict(String district, Message message) {
        OrderEntity entity = orderRepository.findByProfileId(message.getChatId());
        if (entity.getFromWhereDistrict().isBlank()) {
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(message.getChatId());
            deleteMessage.setMessageId(message.getMessageId());
            myTelegramBot.deleteMsg(deleteMessage);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            entity.setFromWhereDistrict(district);
            myTelegramBot.updateOrderDB(entity);
            sendMessage.setText("Qaysi tuman yoki shaharga borasiz!");
            if (entity.getFromWhereRegion().startsWith("Farg'ona")) {
                sendMessage.setReplyMarkup(InlineKeyBoardUtil.getRegion2DistrictList());
            } else if (entity.getFromWhereRegion().startsWith("Toshkent")) {
                sendMessage.setReplyMarkup(InlineKeyBoardUtil.getRegion1DistrictList());
            }
            myTelegramBot.sendMsg(sendMessage);
            myTelegramBot.updateOrderDB(entity);
        } else {
            DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(message.getChatId());
            deleteMessage.setMessageId(message.getMessageId());
            myTelegramBot.deleteMsg(deleteMessage);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            entity.setToWhereDistrict(district);
            myTelegramBot.updateOrderDB(entity);
            enterPeopleCount(message);
//            List<OrderEntity> list = orderRepository.findAllByOrderStatus(OrderStatus.ACTIVE);
//            if (list.size() > 0) {
//                for (OrderEntity order : list) {
//                    ProfileEntity profileEntity = profileRepository.findByUserId(order.getProfileId());
//                    if (order.getFromWhereDistrict().contains(entity.getFromWhereDistrict()) &&
//                            order.getToWhereDistrict().contains(entity.getToWhereDistrict())) {
//                        sendMessage.setText("Qayerdan: " + entity.getFromWhereDistrict() + ";\nQayerga: " + entity.getToWhereDistrict() + "; \n\uD83D\uDE97 Avtomobil: " + profileEntity.getCarModel() + "; \nAvtomobil raqami: " + profileEntity.getCarNum() + "; \n☎\uFE0F Telefon raqam: " + profileEntity.getPhoneNumber() + " " + profileEntity.getName() + "; \n\uD83D\uDCB8 Narx: " + entity.getPrice() + ";");
//                        myTelegramBot.sendMsg(sendMessage);
//                    }
//                    sendMessage.setText("Bosh menyu!");
//                    sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
//                    myTelegramBot.sendMsg(sendMessage);
//                }
//            }else {
//                sendMessage.setText("Bu yonalishda haydovchilar yo'q!");
//                sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
//                myTelegramBot.sendMsg(sendMessage);
//            }

        }
    }

    public void enterPeopleCount(Message message) {
        ProfileEntity profileEntity = profileRepository.findByUserId(message.getChatId());
        OrderEntity entity = orderRepository.findByProfileId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Nechta yo'lovchi bor yoki pochtangiz bormi?");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonPeopleCount());
            myTelegramBot.sendMsg(sendMessage);
            profileEntity.setStep(ProfileStep.ENTER_PEOPLE_COUNT);
            myTelegramBot.updateProfileDB(profileEntity);
            myTelegramBot.updateOrderDB(entity);
        }else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_PEOPLE_COUNT)){
            if (message.getText().equals("Pochta bor") || message.getText().equals("1 kishi") || message.getText().equals("2 kishi") || message.getText().equals("3 kishi")|| message.getText().equals("Bosh taxi kerak(Пустой)")) {
                entity.setPeopleCount(message.getText());
                profileEntity.setStep(ProfileStep.DONE);
                myTelegramBot.updateOrderDB(entity);
                myTelegramBot.updateProfileDB(profileEntity);
            }else {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setText("Sizga ko'rsatilayotgan tugmalardan foydalaning❗\uFE0F❗\uFE0F❗\uFE0F");
                sendMessage.setReplyMarkup(ReplyKeyboardUtil.buttonPeopleCount());
                myTelegramBot.sendMsg(sendMessage);
            }

        }
    }

    public void enterAdditionalInfo(Message message) {
        OrderEntity entity = orderRepository.findByProfileId(message.getChatId());
        ProfileEntity profileEntity = profileRepository.findByUserId(message.getChatId());
        if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Qo'shimcha ma'lumot kiriting! \n\nMasalan: \nSoat nechida ketish kerakligi, Qo'shimcha telefon raqam.\n\nShunga ohshash haydovchi uchun ma'lumot qoldiring bu sizga tez taksi topishingiz va manzilga sog'-salomat yetib olishingizga yordam beradi!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            myTelegramBot.sendMsg(sendMessage);
            profileEntity.setStep(ProfileStep.ENTER_ADDITIONAL_INFO);
            myTelegramBot.updateProfileDB(profileEntity);
            myTelegramBot.updateOrderDB(entity);
        }else if (profileRepository.findByUserId(message.getChatId()).getStep().equals(ProfileStep.ENTER_ADDITIONAL_INFO)){
            entity.setAdditionalInfo(message.getText());
            profileEntity.setStep(ProfileStep.DONE);
            myTelegramBot.updateOrderDB(entity);
            myTelegramBot.updateProfileDB(profileEntity);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Siz uchun taksi qidirilmoqda...\n\nSizga TAXI xizmati zarur bo'lsa quyidagi raqamga murojaat qiling.\n☎\uFE0F +998911444461");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
            myTelegramBot.sendMsg(sendMessage);
            SendMessage sendMessage2 = new SendMessage();
            String htmlText = "Qayerdan:<b> " + entity.getFromWhereDistrict() + "</b>;\n" +
                    "Qayerga:<b> " + entity.getToWhereDistrict() + "</b>;\n" +
                    "Telefon raqam: " + profileEntity.getPhoneNumber() + " " + profileEntity.getName() + ";\n" +
                    entity.getPeopleCount() + " ketishi kerak!;\n" +
                    "Qo'shimcha ma'lumot: " + entity.getAdditionalInfo() + ";";

            sendMessage2.setText(htmlText);
            sendMessage2.setParseMode("HTML");
            sendMessage2.setChatId("-1002086407002");
            if (orderRepository.findByProfileId(message.getChatId()) != null){
                orderRepository.delete(orderRepository.findByProfileId(message.getChatId()));
            }
            myTelegramBot.sendMsg(sendMessage2);
        }
    }

    public void enterOffer(Message message) {
        ProfileEntity profileEntity = profileRepository.findByUserId(message.getChatId());
        if (profileEntity.getStep().equals(ProfileStep.DONE)){
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Taklifingizni jo'nating!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.cancel());
            myTelegramBot.sendMsg(sendMessage);
            profileEntity.setStep(ProfileStep.ENTER_OFFEN);
            myTelegramBot.updateProfileDB(profileEntity);
        } else if (profileEntity.getStep().equals(ProfileStep.ENTER_OFFEN)) {
            ForwardMessage forwardMessage = new ForwardMessage();
            forwardMessage.setChatId("@TM6669008");
            forwardMessage.setFromChatId(message.getChatId());
            forwardMessage.setMessageId(message.getMessageId());
            myTelegramBot.sendMsg(forwardMessage);
            profileEntity.setStep(ProfileStep.DONE);
            myTelegramBot.updateProfileDB(profileEntity);
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId());
            sendMessage.setText("Taklifingizni qabul qilindi!");
            sendMessage.setReplyMarkup(ReplyKeyboardUtil.menuKeyboardPassenger());
            myTelegramBot.sendMsg(sendMessage);
        }

    }
}
