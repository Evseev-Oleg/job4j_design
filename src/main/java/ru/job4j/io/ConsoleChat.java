package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> listAnswer = new ArrayList<>();
        String str = null;
        String change = CONTINUE;
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
        BufferedReader br = new BufferedReader(new FileReader(botAnswers));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String[] res = line.split(" ");
            listAnswer.addAll(Arrays.asList(res));
        }
        try {
            str = bfr.readLine();
            while (!str.equals(OUT)) {
                if (str.equals(STOP) || str.equals(CONTINUE)) {
                    change = str;
                }
                if (change.equals(STOP)) {
                    bw.write(str + " ");
                } else {
                    int index = (int) (Math.random() * listAnswer.size());
                    bw.write(str + " ");
                    bw.write(listAnswer.get(index) + " ");
                    System.out.println(listAnswer.get(index));
                }
                str = bfr.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bw.write(str + " ");
            bfr.close();
            bw.close();
            br.close();
        }

    }

    public static void main(String[] args) throws IOException {
        ConsoleChat cc = new ConsoleChat("result.txt", "even.txt");
        cc.run();
    }
}
