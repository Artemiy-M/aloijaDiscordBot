package commands.rollcube;

import java.util.concurrent.ThreadLocalRandom;

public class Cube {

    public int roll(int x) {
        if (x < 1) {
            x = 1;
        }
        return ThreadLocalRandom.current().nextInt(1, x + 1);
    }
}
