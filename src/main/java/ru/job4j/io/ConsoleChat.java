package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleChat {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    private List<String> readChatBot() {
        List<String> listAnswer = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] res = line.split(" ");
                listAnswer.addAll(Arrays.asList(res));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listAnswer;
    }

    private void writeChat(List<String> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (String s : list) {
                bw.write(s + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        List<String> chatBot = readChatBot();
        List<String> chat = new ArrayList<>();
        String str;
        String change = CONTINUE;

        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in))) {
            str = bfr.readLine();
            while (!str.equals(OUT)) {
                if (str.equals(STOP) || str.equals(CONTINUE)) {
                    change = str;
                }
                if (change.equals(STOP)) {
                    chat.add(str);
                } else {
                    int index = (int) (Math.random() * chatBot.size());
                    chat.add(str);
                    chat.add(chatBot.get(index));
                    System.out.println(chatBot.get(index));
                }
                str = bfr.readLine();
            }
            chat.add(str);
            writeChat(chat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ConsoleChat cc = new ConsoleChat("result.txt", "even.txt");
            cc.run();
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }

    }
}
