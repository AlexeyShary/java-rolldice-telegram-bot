package sh.alex.rolldicetelegrambot.bot;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
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
import sh.alex.rolldicetelegrambot.stats.logic.StatsService;

@Component
@PropertySource("classpath:application-secret.properties")
@RequiredArgsConstructor
public class RollDiceBot extends TelegramLongPollingBot {
    private final StatsService statsService;

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

            if (update.getMessage().getChat().isUserChat()) {
                handlePersonalChatMessage(message, messageText);
            } else {
                handleGroupChatMessage(message, messageText);
            }
        }
    }

    private void handlePersonalChatMessage(Message message, String messageText) {
        if (messageText.equals("/start")) {
            sendResponse(message.getChatId(), "Hello! I'm RollDiceBot. Write a message in the format [x]d[y], " +
                    "where x is the number of rolls, and y is the number of sides on the die, and I will roll them " +
                    "for you. For example, 2d6 is a roll of two six-sided dice.");
        }

        if (messageText.matches("\\d+d\\d+")) {
            rollDice(message, messageText);
        }
    }

    private void handleGroupChatMessage(Message message, String messageText) {
        if (messageText.startsWith("/roll")) {
            String rollRequest = messageText.substring("/roll".length()).trim();
            rollDice(message, rollRequest);
        }
    }

    private void rollDice(Message message, String rollRequest) {
        String[] parts = rollRequest.split("d");

        int numDice;
        int numFaces;

        try {
            numDice = Integer.parseInt(parts[0]);
            numFaces = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            sendResponse(message.getChatId(), "Sorry, I can't understand the numbers.");
            return;
        }

        if (numDice <= 0 || numFaces <= 0) {
            sendResponse(message.getChatId(), "Sorry, the numbers must be > 0.");
            return;
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < numDice; i++) {
            int roll = (int) (Math.random() * numFaces) + 1;
            result.append(roll);
            if (i < numDice - 1) {
                result.append(", ");
            }
        }

        sendResponse(message.getChatId(), result.toString());
        statsService.storeDiceRoll(rollRequest, result.toString());
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
