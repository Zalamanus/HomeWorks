package com.javarush.test.level30.lesson15.big01.client;


import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by MVTitov on 30.08.2016.
 */
public class BotClient extends Client {
    public class BotSocketThread extends SocketThread {
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
        protected void processIncomingMessage(String message) {
            SimpleDateFormat dateFormat;
            ConsoleHelper.writeMessage(message);
            if (message == null || !message.contains(": ")) return;
            String[] userAndText = message.split(": ");
            switch (userAndText[1]) {
                case "дата":
                    dateFormat = new SimpleDateFormat("d.MM.YYYY");
                    break;
                case "день":
                    dateFormat = new SimpleDateFormat("d");
                    break;
                case "месяц":
                    dateFormat = new SimpleDateFormat("MMMM");
                    break;
                case "год":
                    dateFormat = new SimpleDateFormat("YYYY");
                    break;
                case "время":
                    dateFormat = new SimpleDateFormat("H:mm:ss");
                    break;
                case "час":
                    dateFormat = new SimpleDateFormat("H");
                    break;
                case "минуты":
                    dateFormat = new SimpleDateFormat("m");
                    break;
                case "секунды":
                    dateFormat = new SimpleDateFormat("s");
                    break;
                default: return;
            }
            sendTextMessage("Информация для "+userAndText[0]+": "+dateFormat.format(Calendar.getInstance().getTime()));
        }
    }
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }
    protected boolean shouldSentTextFromConsole() {
        return false;
    }
    protected String getUserName() {
        return "date_bot_"+(int) (Math.random()*100);
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
