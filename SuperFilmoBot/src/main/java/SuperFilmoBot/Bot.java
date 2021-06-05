package SuperFilmoBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * todo Document type Bot
 */
public class Bot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "USER";
        //возвращаем юзера
    }

    @Override
    public String getBotToken() {
        return "YOUR_BOT_TOKEN";
        //Токен бота
    }

    @Override
    public void onUpdateReceived(Update update) {

    }
}
