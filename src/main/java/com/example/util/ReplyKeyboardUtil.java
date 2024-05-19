package com.example.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.LinkedList;
import java.util.List;

public class ReplyKeyboardUtil {

    public static KeyboardButton button(String text) {
        KeyboardButton button = new KeyboardButton();
        button.setText(text);
        return button;
    }

    public static ReplyKeyboardMarkup phoneKeyboard() {
        KeyboardButton button = new KeyboardButton();
        button.setText(" ☎️ Kontaktni yuborish");
        button.setRequestContact(true);
        KeyboardButton button2 = button("❌ Bekor qilish");

        KeyboardRow row = new KeyboardRow();
        row.add(button);
        row.add(button2);

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(row);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }


    public static ReplyKeyboardMarkup menuKeyboardDriver(Boolean active) {
        String butn1Text ;
        if (active) {
            butn1Text = "\uD83D\uDE95 Liniyadan chiqish";
        }else {
            butn1Text = "\uD83D\uDE95 Liniyaga kirish";
        }
        KeyboardButton taskList = button("");
        KeyboardButton butn1 = button(butn1Text);
        KeyboardButton butn2 = button("✅ Mening takliflarim");
        KeyboardButton butn3 = button("\uD83D\uDDC2 Ma'lumotlaringiz");
        KeyboardButton butn4 = button("⚙️ Sozlamalar");
        KeyboardButton butn5 = button("\uD83D\uDDC2 Liniya ma'lumotlari");


        KeyboardRow row1 = new KeyboardRow();
        row1.add(butn1);
        row1.add(butn2);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(butn3);
        row2.add(butn4);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(butn5);

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    } public static ReplyKeyboardMarkup cancel() {
        KeyboardButton button = button("❌ Bekor qilish");


        KeyboardRow row1 = new KeyboardRow();
        row1.add(button);

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(row1);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
    public static ReplyKeyboardMarkup menuKeyboard() {
        KeyboardButton button1 = button("Haydovchi");
        KeyboardButton button2 = button("Yo'lovchi");
        KeyboardButton button3 = button("Bot haqida");



        KeyboardRow row1 = new KeyboardRow();
        row1.add(button1);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(button2);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(button3);

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }


    public static ReplyKeyboardMarkup settingsKeyboardPassanger() {
        KeyboardButton row1 = button(" ✏️ Ismni o'zgartirish");
        KeyboardButton row2 = button(" ✏️ Familiyani o'zgartirish");
        KeyboardButton row3 = button(" ✏️ Telefon raqamni o'zgartish");
        KeyboardButton row4 = button("Yolovchidan ➡️ Haydovchiga o'tishlik");
        KeyboardButton row5 = button(" \uD83C\uDFE0 Bosh menyuga qaytish");


        KeyboardRow rowList1 = new KeyboardRow();
        rowList1.add(row1);
        rowList1.add(row2);

        KeyboardRow rowList2 = new KeyboardRow();
        rowList2.add(row3);
        KeyboardRow rowList3 = new KeyboardRow();
        rowList3.add(row4);
        KeyboardRow rowList4 = new KeyboardRow();
        rowList4.add(row5);

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(rowList1);
        rowList.add(rowList2);
        rowList.add(rowList3);
        rowList.add(rowList4);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
    public static ReplyKeyboardMarkup settingsKeyboardDriver() {
        KeyboardButton taskList = button("");
        KeyboardButton row1 = button(" ✏️ Ismni o'zgartirish");
        KeyboardButton row2 = button(" ✏️ Familiyani o'zgartirish");
        KeyboardButton row3 = button(" ✏️ Telefon raqamni o'zgartish");
        KeyboardButton row4 = button(" ➕ Avtomobil nomerini tahrirlash");
        KeyboardButton row5 = button(" ➕ Avtomobil modelini tahrirlash");
        KeyboardButton row6 = button("Haydovchidan ➡️ Yo'lovchiga o'tishlik");
        KeyboardButton row7 = button(" \uD83C\uDFE0 Bosh menyuga qaytish");


        KeyboardRow rowList1 = new KeyboardRow();
        rowList1.add(row1);
        rowList1.add(row2);

        KeyboardRow rowList2 = new KeyboardRow();
        rowList2.add(row3);
        KeyboardRow rowList3 = new KeyboardRow();
        rowList3.add(row4);
        KeyboardRow rowList4 = new KeyboardRow();
        rowList4.add(row5);
        KeyboardRow rowList5 = new KeyboardRow();
        rowList5.add(row6);
        KeyboardRow rowList6 = new KeyboardRow();
        rowList6.add(row7);

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(rowList1);
        rowList.add(rowList2);
        rowList.add(rowList3);
        rowList.add(rowList4);
        rowList.add(rowList5);
        rowList.add(rowList6);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
    public static ReplyKeyboardMarkup menuKeyboardPassenger() {
        KeyboardButton order = button("\uD83D\uDD0D Taxi qidirish");;
        KeyboardButton Suggestions = button("✅ Mening takliflarim");
        KeyboardButton clientInfo = button("\uD83D\uDDC2 Ma'lumotlaringiz");
        KeyboardButton settings = button("⚙️ Sozlamalar");


        KeyboardRow row1 = new KeyboardRow();
        row1.add(order);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(Suggestions);
        row2.add(clientInfo);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(settings);
        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }

    public static ReplyKeyboard listRegion() {
        KeyboardButton region1 = button("Andijon vil.");
        KeyboardButton region2 = button("Buxoro vil.");
        KeyboardButton region3 = button("Farg'ona vil.");
        KeyboardButton region4 = button("Jizzax vil.");
        KeyboardButton region5 = button("Navoiy vil.");
        KeyboardButton region6 = button("Namangan vil.");
        KeyboardButton region7 = button("Qashqadaryo vil.");
        KeyboardButton region8 = button("Qoraqalpog'iston Res.");
        KeyboardButton region9 = button("Samarqand vil.");
        KeyboardButton region10 = button("Surxandaryo vil.");
        KeyboardButton region11 = button("Toshkent vil.");
        KeyboardButton region12 = button("Xorazm vil.");
        KeyboardRow row1 = new KeyboardRow();
        row1.add(region1);
        KeyboardRow row2 = new KeyboardRow();
        row2.add(region2);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(region3);
        KeyboardRow row4 = new KeyboardRow();
        row4.add(region4);
        KeyboardRow row5 = new KeyboardRow();
        row5.add(region5);
        KeyboardRow row6 = new KeyboardRow();
        row6.add(region6);
        KeyboardRow row7 = new KeyboardRow();
        row7.add(region7);
        KeyboardRow row8 = new KeyboardRow();
        row8.add(region8);
        KeyboardRow row9 = new KeyboardRow();
        row9.add(region9);
        KeyboardRow row10 = new KeyboardRow();
        row10.add(region10);
        KeyboardRow row11 = new KeyboardRow();
        row11.add(region11);
        KeyboardRow row12 = new KeyboardRow();
        row12.add(region12);
        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        rowList.add(row4);
        rowList.add(row5);
        rowList.add(row6);
        rowList.add(row7);
        rowList.add(row8);
        rowList.add(row9);
        rowList.add(row10);
        rowList.add(row11);
        rowList.add(row12);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
}
