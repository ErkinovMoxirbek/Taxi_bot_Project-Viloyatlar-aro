package com.example.util;

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

        KeyboardRow row = new KeyboardRow();
        row.add(button);

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(row);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }


    public static ReplyKeyboardMarkup menuKeyboardDriver() {
        KeyboardButton taskList = button("");
        KeyboardButton order = button("\uD83D\uDCE5 Buyurtma olish");
        KeyboardButton Suggestions = button("✅ Mening takliflarim");
        KeyboardButton driverInfo = button("\uD83D\uDDC2 Ma'lumotlaringiz");
        KeyboardButton driverClient = button("Haydovchidan ➡️ Yolovchiga o'tishlik");
        KeyboardButton settings = button("⚙️ Sozlamalar");
        KeyboardButton olinganOrderlar = button("\uD83D\uDCE4 Olingan buyurtmalar");


        KeyboardRow row1 = new KeyboardRow();
        row1.add(order);
        row1.add(olinganOrderlar);

        KeyboardRow row2 = new KeyboardRow();
        row2.add(driverInfo);
        row2.add(Suggestions);
        KeyboardRow row3 = new KeyboardRow();
        row3.add(settings);
        KeyboardRow row4 = new KeyboardRow();
        row4.add(driverClient);

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(row1);
        rowList.add(row2);
        rowList.add(row3);
        rowList.add(row4);

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
        KeyboardButton row5 = button(" \uD83C\uDFE0 Bosh menyuga ");


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
        KeyboardButton row5 = button("Haydovchidan ➡️ Yo'lovchiga o'tishlik");
        KeyboardButton row6 = button(" \uD83C\uDFE0 Bosh menyuga ");


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

        List<KeyboardRow> rowList = new LinkedList<>();
        rowList.add(rowList1);
        rowList.add(rowList2);
        rowList.add(rowList3);
        rowList.add(rowList4);
        rowList.add(rowList5);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(rowList);

        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        return replyKeyboardMarkup;
    }
    public static ReplyKeyboardMarkup menuKeyboardPassenger() {
        KeyboardButton order = button("\uD83D\uDCE5 Buyurtma berish");
        KeyboardButton orders = button("\uD83D\uDCE5 Buyurtmalaringiz");
        KeyboardButton Suggestions = button("✅ Mening takliflarim");
        KeyboardButton clientInfo = button("\uD83D\uDDC2 Ma'lumotlaringiz");
        KeyboardButton settings = button("⚙️ Sozlamalar");


        KeyboardRow row1 = new KeyboardRow();
        row1.add(order);
        row1.add(orders);

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



   /* public static ReplyKeyboardMarkup Location() {
        KeyboardButton button1 = KeyButtonUtil.keyButton("No");
        KeyboardButton button2 = KeyButtonUtil.keyButton("Yes");
        KeyboardButton button3 = KeyButtonUtil.keyButton("Back to location menu", ":arrow_left:");
        KeyboardRow keyboardRow1 = KeyButtonUtil.keyRow(button1, button2);
        KeyboardRow keyboardRow2 = KeyButtonUtil.keyRow(button3);
        List<KeyboardRow> keyboardRowList = KeyButtonUtil.keyboardRowList(keyboardRow1, keyboardRow2);
        return KeyButtonUtil.replyKeyboardMarkup(keyboardRowList);
    }*/

 /*   public static ReplyKeyboardMarkup replyKeyboardMarkup(List<KeyboardRow> keyboardRowList) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        replyKeyboardMarkup.setResizeKeyboard(true);//buttonni razmerini to'g'irlaydi
        replyKeyboardMarkup.setSelective(true);// bottinga strelka qoshadi;
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        return replyKeyboardMarkup;
    }*/

/*    public static KeyboardRow keyRow(KeyboardButton... keyboardButton) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.addAll(Arrays.asList(keyboardButton));
        return keyboardRow;

    }*/

}
