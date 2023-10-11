package sh.alex.rolldicetelegrambot.bot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@PropertySource("classpath:application-secret.properties")
public class RollDiceBot extends TelegramLongPollingBot {
    @Value("${telegram.bot.name}")
    private String botName;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String messageText = update.getMessage().getText();

            if (messageText.matches("\\d+d\\d+")) {
                String[] parts = messageText.split("d");
                int numDice = Integer.parseInt(parts[0]);
                int numFaces = Integer.parseInt(parts[1]);

                if (numDice > 0 && numFaces > 0) {
                    StringBuilder result = new StringBuilder();

                    for (int i = 0; i < numDice; i++) {
                        int roll = (int) (Math.random() * numFaces) + 1;
                        result.append(roll);
                        if (i < numDice - 1) {
                            result.append(", ");
                        }
                    }

                    sendResponse(message.getChatId(), result.toString());
                }
            }
        }
    }

    @PostConstruct
    private void init() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendResponse(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
