package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean a = true;
        char b = 'm';
        byte c = 1;
        short d = 2;
        int e = 3;
        long f = 4L;
        float g = 5.1f;
        double h = 5.2d;
        LOG.debug("Text message {}, and {}, and {}, and {}, and {}, and {}, and {}, and {}",
                a, b, c, d, e, f, g, h);
    }
}
