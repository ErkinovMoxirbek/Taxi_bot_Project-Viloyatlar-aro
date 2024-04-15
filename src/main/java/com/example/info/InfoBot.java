package com.example.info;


import com.example.MyTelegramBot;
import com.example.util.InlineKeyBoardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
@Component
public class InfoBot {
    @Autowired
    private MyTelegramBot myTelegramBot;

    public InfoBot(MyTelegramBot myTelegramBot) {
        this.myTelegramBot = myTelegramBot;

    }
    public void handle(Message message){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Bu yerda bot haqida boladi.");
        sendMessage.setChatId(message.getChatId());
        sendMessage.setReplyMarkup(InlineKeyBoardUtil.getMenuKeyboard());
        myTelegramBot.sendMsg(sendMessage);

    }
}
