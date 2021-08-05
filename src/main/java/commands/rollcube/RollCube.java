package commands.rollcube;

public class RollCube {

    private final String START_MESSAGE = " rolled ";
    private String plusMessage = "+";

    public String roll(int y, int x, int z, boolean plusPlus) {
        if (plusPlus && z != 0) {
            if (z < 0) {
                plusMessage = "";
            }
            return rollPlusPlus(y, x, z);
        }

        if (z == 0) {
            return rollYdx(y, x);
        } else {
            if (z < 0) {
                plusMessage = "";
            }
            return rollydxPlusZ(y, x, z);
        }
    }

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

        return START_MESSAGE + roll + plusMessage + z + "=" + result;
    }

    private String rollydxPlusZ(int y, int x, int z) {
        if (y <= 1) {
            return roll1dxPlusZ(x, z);
        }

        int[] rolls = getRolls(y, x);
        String reply = getRollsMessage(rolls);

        int summ = calcSumm(rolls, z);

        return START_MESSAGE + reply + plusMessage + z + "=" + summ;
    }

    private String rollPlusPlus(int y, int x, int z) {
        if (y <= 1) {
            return roll1dxPlusZ(x, z);
        }

        int[] rolls = getRolls(y, x);
        int[] finalRolls = addPlusToRolls(rolls, z);
        String reply =getPlusRollsMessage(finalRolls, z);

        int summ = calcSumm(rolls, 0);

        return START_MESSAGE + reply + "=" + summ;
    }

    private int[] addPlusToRolls(int[] rolls, int z) {
        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = Math.max(rolls[i] + z, 1);
        }
        return rolls;
    }

    private String getPlusRollsMessage(int[] rolls, int z) {
        StringBuilder reply = new StringBuilder("(");
        for (int i = 0; i < rolls.length; i++) {
            if (i == rolls.length-1) {
                reply.append(rolls[i]).append("(").append(rolls[i]-z).append(plusMessage).append(z).append(")");
            } else {
                reply.append(rolls[i]).append("(").append(rolls[i]-z).append(plusMessage).append(z).append(")").append("+");
            }
        }
        reply.append(")");

        return reply.toString();
    }

    private int calcSumm(int[] rolls, int z) {
        int summ = 0;
        for (int roll : rolls) {
            summ += roll;
        }
        return Math.max(summ + z, 1);
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
