package commands.rollcube;

public class RollCube {
    private final String START_MESSAGE = " rolled ";

    private String roll1dx(int x) {
        int result = new Cube().roll(x);
        return START_MESSAGE + result;
    }

    private String rollYdx(int y, int x) {
        if (y <= 1) {
            return roll1dx(x);
        }
        int[] rolls = getRolls(y, x);
        String reply = getRollsMessage(rolls);
        int summ = calcSumm(rolls, 0);

        return START_MESSAGE + reply + "=" + summ;
    }

    private String roll1dxPlusZ(int x, int z) {
        int roll = new Cube().roll(x);
        int result = roll + z;

        return START_MESSAGE + roll + "+" + z + "=" + result;
    }

    private String rollydxPlusZ(int y, int x, int z) {
        if (y <= 1) {
            return roll1dxPlusZ(x, z);
        }

        int[] rolls = getRolls(y, x);
        String reply = getRollsMessage(rolls);

        int summ = calcSumm(rolls, z);

        return START_MESSAGE + reply + "+" + z + "=" + summ;
    }

    public String roll(int y, int x, int z) {
        if (z == 0) {
            return rollYdx(y, x);
        } else {
            return rollydxPlusZ(y, x , z);
        }
    }

    private int calcSumm(int[] rolls, int z) {
        int summ = 0;
        for (int roll : rolls) {
            summ += roll;
        }

        return summ + z;
    }

    private int[] getRolls(int amount, int maxRoll) {
        int[] rolls = new int[amount];

        for (int i = 0; i < amount; i++) {
            rolls[i] = new Cube().roll(maxRoll);
        }

        return rolls;
    }

    private String getRollsMessage(int[] rolls) {
        StringBuilder reply = new StringBuilder("(");
        for (int i = 0; i < rolls.length; i++) {
            if (i == rolls.length-1) {
                reply.append(rolls[i]);
            } else {
                reply.append(rolls[i]).append("+");
            }
        }
        reply.append(")");

        return reply.toString();
    }
}
