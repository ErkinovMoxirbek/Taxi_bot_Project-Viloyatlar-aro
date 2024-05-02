package com.example.util;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;

public class InlineKeyBoardUtil {
    public static InlineKeyboardButton button(String text, String callBack) {
        InlineKeyboardButton button = new InlineKeyboardButton(text);
        button.setCallbackData(callBack);
        return button;
    }


    public static InlineKeyboardMarkup getMenuKeyboard() {
        List<InlineKeyboardButton> row = new LinkedList<>();
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("Haydovchi", "driver");
        InlineKeyboardButton button2 = InlineKeyBoardUtil.button("Yo`lovchi",  "Passenger");

        row.add(button1);
        row.add(button2);


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        rowList.add(row);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static  InlineKeyboardMarkup getListRegion(){
        List<InlineKeyboardButton> row1 = new LinkedList<>();
        List<InlineKeyboardButton> row2 = new LinkedList<>();
        List<InlineKeyboardButton> row3 = new LinkedList<>();
        List<InlineKeyboardButton> row4 = new LinkedList<>();
        List<InlineKeyboardButton> row5 = new LinkedList<>();
        List<InlineKeyboardButton> row6 = new LinkedList<>();
        List<InlineKeyboardButton> row7 = new LinkedList<>();
        List<InlineKeyboardButton> row8 = new LinkedList<>();
        List<InlineKeyboardButton> row9 = new LinkedList<>();
        List<InlineKeyboardButton> row10 = new LinkedList<>();
        List<InlineKeyboardButton> row11 = new LinkedList<>();
        List<InlineKeyboardButton> row12 = new LinkedList<>();
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("Andijon vil.", "region1/Andijon vil.");
        InlineKeyboardButton button2 = InlineKeyBoardUtil.button("Buxoro vil.", "region2/Buxoro vil.");
        InlineKeyboardButton button3 = InlineKeyBoardUtil.button("Farg'ona vil.", "region3/Farg'ona vil.");
        InlineKeyboardButton button4 = InlineKeyBoardUtil.button("Jizzax vil.", "region4/Jizzax vil.");
        InlineKeyboardButton button5 = InlineKeyBoardUtil.button("Navoiy vil.", "region5/Navoiy vil.");
        InlineKeyboardButton button6 = InlineKeyBoardUtil.button("Namangan vil.", "region6/Namangan vil.");
        InlineKeyboardButton button7 = InlineKeyBoardUtil.button("Qashqadaryo vil.", "region7/Qashqadaryo vil.");
        InlineKeyboardButton button8 = InlineKeyBoardUtil.button("Qoraqalpog'iston Res.", "region8/Qoraqalpog'iston Res.");
        InlineKeyboardButton button9 = InlineKeyBoardUtil.button("Samarqand vil.", "region9/Samarqand vil.");
        InlineKeyboardButton button10 = InlineKeyBoardUtil.button("Surxandaryo vil.", "region10/Surxandaryo vil.");
        InlineKeyboardButton button11 = InlineKeyBoardUtil.button("Toshkent vil.", "region11/Toshkent vil.");
        InlineKeyboardButton button12 = InlineKeyBoardUtil.button("Xorazm vil.", "region12/Xorazm vil.");
        row1.add(button1);
        row2.add(button2);
        row3.add(button3);
        row4.add(button4);
        row5.add(button5);
        row6.add(button6);
        row7.add(button7);
        row8.add(button8);
        row9.add(button9);
        row10.add(button10);
        row11.add(button11);
        row12.add(button12);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
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
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getRegion1DistrictList(){
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("Andijon shahri", "district1/Andijon shahri");
        InlineKeyboardButton button2 = InlineKeyBoardUtil.button("Andijon tumani", "district2/Andijon tumani");
        InlineKeyboardButton button3 = InlineKeyBoardUtil.button("Asaka tumani", "district3/Asaka tumani");
        InlineKeyboardButton button4 = InlineKeyBoardUtil.button("Baliqchi tumani", "district4/Baliqchi tumani");
        InlineKeyboardButton button5 = InlineKeyBoardUtil.button("Bo'z tumani", "district5/Bo'z tumani");
        InlineKeyboardButton button6 = InlineKeyBoardUtil.button("Buloqboshi tumani", "district6/Buloqboshi tumani");
        InlineKeyboardButton button7 = InlineKeyBoardUtil.button("Izboskan tumani", "district7/Izboskan tumani");
        InlineKeyboardButton button8 = InlineKeyBoardUtil.button("Jalolquduq tumani", "district8/Jalolquduq tumani");
        InlineKeyboardButton button9 = InlineKeyBoardUtil.button("Marhamat tumani", "district9/Marhamat tumani");
        InlineKeyboardButton button10 = InlineKeyBoardUtil.button("Oltinko'l tumani", "district10/Oltinko'l tumani");
        InlineKeyboardButton button11 = InlineKeyBoardUtil.button("Paxtaobod tumani", "district11/Paxtaobod tumani");
        InlineKeyboardButton button12 = InlineKeyBoardUtil.button("Qo'rg'ontepa tumani", "district12/Qo'rg'ontepa tumani");
        InlineKeyboardButton button13 = InlineKeyBoardUtil.button("Shahrixon tumani", "district13/Shahrixon tumani");
        InlineKeyboardButton button14 = InlineKeyBoardUtil.button("Ulug'nor tumani", "district14/Ulug'nor tumani");
        InlineKeyboardButton button15 = InlineKeyBoardUtil.button("Xo'jaobod tumani", "district15/Xo'jaobod tumani");
        InlineKeyboardButton button16 = InlineKeyBoardUtil.button("Xonobod shahri", "district16/Xonobod shahri");
        InlineKeyboardButton buttonNext = InlineKeyBoardUtil.button("Kiyingi qadamga o'tish ➡\uFE0F","next/Next");
        List<InlineKeyboardButton> row1 = new LinkedList<>();
        List<InlineKeyboardButton> row2 = new LinkedList<>();
        List<InlineKeyboardButton> row3 = new LinkedList<>();
        List<InlineKeyboardButton> row4 = new LinkedList<>();
        List<InlineKeyboardButton> row5 = new LinkedList<>();
        List<InlineKeyboardButton> row6 = new LinkedList<>();
        List<InlineKeyboardButton> row7 = new LinkedList<>();
        List<InlineKeyboardButton> row8 = new LinkedList<>();
        List<InlineKeyboardButton> row9 = new LinkedList<>();
        List<InlineKeyboardButton> row10 = new LinkedList<>();
        List<InlineKeyboardButton> row11 = new LinkedList<>();
        List<InlineKeyboardButton> row12 = new LinkedList<>();
        List<InlineKeyboardButton> row13 = new LinkedList<>();
        List<InlineKeyboardButton> row14 = new LinkedList<>();
        List<InlineKeyboardButton> row15 = new LinkedList<>();
        List<InlineKeyboardButton> row16 = new LinkedList<>();
        List<InlineKeyboardButton> row17 = new LinkedList<>();
        row1.add(button1);
        row2.add(button2);
        row3.add(button3);
        row4.add(button4);
        row5.add(button5);
        row6.add(button6);
        row7.add(button7);
        row8.add(button8);
        row9.add(button9);
        row10.add(button10);
        row11.add(button11);
        row12.add(button12);
        row13.add(button13);
        row14.add(button14);
        row15.add(button15);
        row16.add(button16);
        row17.add(buttonNext);
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
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
        rowList.add(row13);
        rowList.add(row14);
        rowList.add(row15);
        rowList.add(row16);
        rowList.add(row17);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }public static InlineKeyboardMarkup getRegion2DistrictList(){
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("Buxoro shahri", "district1/Buxoro shahri");
        InlineKeyboardButton button2 = InlineKeyBoardUtil.button("Buxoro tumani", "district2/Buxoro tumani");
        InlineKeyboardButton button3 = InlineKeyBoardUtil.button("G'ijduvon tumani", "district3/G'ijduvon tumani");
        InlineKeyboardButton button4 = InlineKeyBoardUtil.button("Jondor tumani", "district4/Jondor tumani");
        InlineKeyboardButton button5 = InlineKeyBoardUtil.button("Kogon shahri", "district5/Kogon shahri");
        InlineKeyboardButton button6 = InlineKeyBoardUtil.button("Kogon tumani", "district6/Kogon tumani");
        InlineKeyboardButton button7 = InlineKeyBoardUtil.button("Olot tumani", "district7/Olot tumani");
        InlineKeyboardButton button8 = InlineKeyBoardUtil.button("Peshku tumani", "district8/Peshku tumani");
        InlineKeyboardButton button9 = InlineKeyBoardUtil.button("Qorako'l tumani", "district9/Qorako'l tumani");
        InlineKeyboardButton button10 = InlineKeyBoardUtil.button("Qorovulbozor tumani", "district10/Qorovulbozor tumani");
        InlineKeyboardButton button11 = InlineKeyBoardUtil.button("Romitan tumani", "district11/Romitan tumani");
        InlineKeyboardButton button12 = InlineKeyBoardUtil.button("Shofirkon tumani", "district12/Shifirkon tumani");
        InlineKeyboardButton button13 = InlineKeyBoardUtil.button("Vobkent tumani", "district13/Vobkent tumani");
        InlineKeyboardButton buttonNext = InlineKeyBoardUtil.button("Kiyingi qadamga o'tish ➡\uFE0F","next/Next");
        List<InlineKeyboardButton> row1 = new LinkedList<>();
        List<InlineKeyboardButton> row2 = new LinkedList<>();
        List<InlineKeyboardButton> row3 = new LinkedList<>();
        List<InlineKeyboardButton> row4 = new LinkedList<>();
        List<InlineKeyboardButton> row5 = new LinkedList<>();
        List<InlineKeyboardButton> row6 = new LinkedList<>();
        List<InlineKeyboardButton> row7 = new LinkedList<>();
        List<InlineKeyboardButton> row8 = new LinkedList<>();
        List<InlineKeyboardButton> row9 = new LinkedList<>();
        List<InlineKeyboardButton> row10 = new LinkedList<>();
        List<InlineKeyboardButton> row11 = new LinkedList<>();
        List<InlineKeyboardButton> row12 = new LinkedList<>();
        List<InlineKeyboardButton> row13 = new LinkedList<>();
        List<InlineKeyboardButton> row17 = new LinkedList<>();
        row1.add(button1);
        row2.add(button2);
        row3.add(button3);
        row4.add(button4);
        row5.add(button5);
        row6.add(button6);
        row7.add(button7);
        row8.add(button8);
        row9.add(button9);
        row10.add(button10);
        row11.add(button11);
        row12.add(button12);
        row13.add(button13);
        row17.add(buttonNext);
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
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
        rowList.add(row13);
        rowList.add(row17);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getRegion3DistrictList(){
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("Beshariq tumani", "district1/Beshariq tumani");
        InlineKeyboardButton button2 = InlineKeyBoardUtil.button("Bog'dod tumani", "district2/Bog'dod tumani");
        InlineKeyboardButton button3 = InlineKeyBoardUtil.button("Buvayda tumani", "district3/Buvayda tumani");
        InlineKeyboardButton button4 = InlineKeyBoardUtil.button("Dang'ara tumani", "district4/Dang'ara tumani");
        InlineKeyboardButton button5 = InlineKeyBoardUtil.button("Farg'ona shahri", "district5/Farg'ona shahri");
        InlineKeyboardButton button6 = InlineKeyBoardUtil.button("Farg'ona tumani", "district6/Buloqboshi tumani");
        InlineKeyboardButton button7 = InlineKeyBoardUtil.button("Furqat tumani", "district7/Izboskan tumani");
        InlineKeyboardButton button8 = InlineKeyBoardUtil.button("Marg'ilon shahri", "district8/Jalolquduq tumani");
        InlineKeyboardButton button9 = InlineKeyBoardUtil.button("O'zbekiston tumani", "district9/O'zbekiston tumani");
        InlineKeyboardButton button10 = InlineKeyBoardUtil.button("Oltiariq tumani", "district10/Oltiariq tumani");
        InlineKeyboardButton button11 = InlineKeyBoardUtil.button("Qo'qon shahri", "district11/Qo'qon shahri");
        InlineKeyboardButton button12 = InlineKeyBoardUtil.button("Qo'shtepa tumani", "district12/Qo'shtepa tumani");
        InlineKeyboardButton button13 = InlineKeyBoardUtil.button("Quva tumani", "district13/Quva tumani");
        InlineKeyboardButton button14 = InlineKeyBoardUtil.button("Quvasoy shahri", "district14/Quvasoy shahri");
        InlineKeyboardButton button15 = InlineKeyBoardUtil.button("Rishton tumani", "district15/Rishton tumani");
        InlineKeyboardButton button16 = InlineKeyBoardUtil.button("So'x tumani", "district16/So'x tumani");
        InlineKeyboardButton button17 = InlineKeyBoardUtil.button("Toshloq tumani", "district16/Toshloq tumani");
        InlineKeyboardButton button18 = InlineKeyBoardUtil.button("Uchko'prik tumani", "district16/Uchko'prik tumani");
        InlineKeyboardButton button19 = InlineKeyBoardUtil.button("Yozyovon tumani", "district16/Yozyovon tumani");
        InlineKeyboardButton buttonNext = InlineKeyBoardUtil.button("Kiyingi qadamga o'tish ➡\uFE0F","next/Next");
        List<InlineKeyboardButton> row1 = new LinkedList<>();
        List<InlineKeyboardButton> row2 = new LinkedList<>();
        List<InlineKeyboardButton> row3 = new LinkedList<>();
        List<InlineKeyboardButton> row4 = new LinkedList<>();
        List<InlineKeyboardButton> row5 = new LinkedList<>();
        List<InlineKeyboardButton> row6 = new LinkedList<>();
        List<InlineKeyboardButton> row7 = new LinkedList<>();
        List<InlineKeyboardButton> row8 = new LinkedList<>();
        List<InlineKeyboardButton> row9 = new LinkedList<>();
        List<InlineKeyboardButton> row10 = new LinkedList<>();
        List<InlineKeyboardButton> row11 = new LinkedList<>();
        List<InlineKeyboardButton> row12 = new LinkedList<>();
        List<InlineKeyboardButton> row13 = new LinkedList<>();
        List<InlineKeyboardButton> row14 = new LinkedList<>();
        List<InlineKeyboardButton> row15 = new LinkedList<>();
        List<InlineKeyboardButton> row16 = new LinkedList<>();
        List<InlineKeyboardButton> row17 = new LinkedList<>();
        List<InlineKeyboardButton> row18 = new LinkedList<>();
        List<InlineKeyboardButton> row19 = new LinkedList<>();
        row1.add(button1);
        row2.add(button2);
        row3.add(button3);
        row4.add(button4);
        row5.add(button5);
        row6.add(button6);
        row7.add(button7);
        row8.add(button8);
        row9.add(button9);
        row10.add(button10);
        row11.add(button11);
        row12.add(button12);
        row13.add(button13);
        row14.add(button14);
        row15.add(button15);
        row16.add(button16);
        row17.add(button17);
        row18.add(button18);
        row19.add(button19);
        row17.add(buttonNext);
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
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
        rowList.add(row13);
        rowList.add(row14);
        rowList.add(row15);
        rowList.add(row16);
        rowList.add(row17);
        rowList.add(row18);
        rowList.add(row19);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getPhoneOrderKeyboard(String id) {

        List<InlineKeyboardButton> row = new LinkedList<>();
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("☎️ Telefon raqamini olish", "phoneOrder/" + id );



        row.add(button1);




        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        rowList.add(row);


        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getOrderCheckDriverKeyboard(String id) {

        List<InlineKeyboardButton> row2 = new LinkedList<>();
        InlineKeyboardButton button2 = InlineKeyBoardUtil.button("Buyurtmani qabul qilish", "CheckOrder/" + id);


        row2.add(button2);



        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();

        rowList.add(row2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getCheckOrder(String OrderId,String messageId) {

        List<InlineKeyboardButton> row = new LinkedList<>();
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("✅ Ha, oldi", "clientCheck/" + OrderId + "/" + messageId );
        List<InlineKeyboardButton> row2 = new LinkedList<>();
        InlineKeyboardButton button2 = InlineKeyBoardUtil.button("❌ Yo'q, olmadi", "clientNotChek/" + OrderId + "/" + messageId);

        row.add(button1);
        row2.add(button2);



        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        rowList.add(row);
        rowList.add(row2);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getOrderRemoveKeyboard(String id, String messageId) {

        List<InlineKeyboardButton> row = new LinkedList<>();
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("Buyurtmani ochirish \uD83D\uDDD1", "deliteDriverOrder/" + id  );

        row.add(button1);


        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        rowList.add(row);


        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
    public static InlineKeyboardMarkup getDriverOrderRemoveKeyboard(String id) {

        List<InlineKeyboardButton> row = new LinkedList<>();
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("Buyurtmani ochirish \uD83D\uDDD1", "deliteOrder/" + id  );

        row.add(button1);

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        rowList.add(row);


        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getOrderCancellationKeyboard(String id) {

        List<InlineKeyboardButton> row = new LinkedList<>();
        InlineKeyboardButton button1 = InlineKeyBoardUtil.button("Safarni bekor qilish \uD83D\uDDD1", "cancellation/"+id);

        row.add(button1);



        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
        rowList.add(row);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }


}
