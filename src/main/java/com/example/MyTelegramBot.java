package com.example;

import com.example.controller.CallBackController;
import com.example.controller.MainController;
import com.example.entity.OrderEntity;
import com.example.entity.ProfileEntity;
import com.example.repository.OrderRepository;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {
    @Autowired
    private MainController mainController;
    @Autowired
    private CallBackController callBackController;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public String getBotUsername() {
        return "@viloyat_aro_taxi_bot";
    }

    public MyTelegramBot(TelegramBotsApi telegramBotsApi) throws TelegramApiException {
        super("6708588106:AAE3PV-b3Wj1kCTgksQHjzyKcwB3eXJS-Lg");
        telegramBotsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {

                System.out.println(update);
                Message message = update.getMessage();
                mainController.handle(message.getText(), message);
            } else if (update.hasCallbackQuery()) {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                String data = callbackQuery.getData();
                callBackController.handle(data,callbackQuery.getMessage());
            }else {
                System.out.println("my telegram hatto");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
    public Message sendMsg(SendMessage method) {
        try {
            Message execute = execute(method);
            return execute;
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMsg(EditMessageText method) {
        try {
            execute(method);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMsg(ForwardMessage method) {
        try {
            execute(method);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMsg(SendPhoto method) {
        try {
            execute(method);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProfileDB(ProfileEntity entity){
        profileRepository.updateByUserId(entity.getName(),
                entity.getSurname(), entity.getRole(),
                entity.getStatus(), entity.getStep(),
                entity.getPhoneNumber(),entity.getCarNum(),
                entity.getLastMessageId(),entity.getVisible(),
                entity.getUserId());
    } public void updateOrderDB(OrderEntity entity){
        orderRepository.updateByProfileId(entity.getFromWhereRegion(),
                entity.getFromWhereDistrict(), entity.getToWhereRegion(),
                entity.getToWhereDistrict(), entity.getPrice(),
                entity.getHowManyPeople(),entity.getHowManyPeopleTaxi(),
                entity.getAdditionalInfo(),entity.getOrderStatus(),
                entity.getProfileId());
    }

    public Message sendMessage(String s, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(s);
        sendMessage.setChatId(chatId);
        return sendMsg(sendMessage);
    }

    public void deleteMsg(DeleteMessage deleteMessage) {
        try {
            execute(deleteMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
