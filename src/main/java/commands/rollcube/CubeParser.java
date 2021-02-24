package commands.rollcube;

public class CubeParser {
    int rollsAmount = 1;
    int maxRoll = 1;
    int z = 0;

    public CubeParser(String message) {
        parseMessage(message);
    }

    private void parseMessage(String message) {
        if (message.startsWith("/r ") && message.contains("d")) {
            String body = message.split(" ")[1];
            char[] msgSymbols = body.toCharArray();
            if (body.contains("+")) {
                int plusPos = findPlusPos(msgSymbols);
                rollsAmount = Integer.parseInt(String.valueOf(msgSymbols[0]));
                StringBuilder maxRoll = new StringBuilder();
                for (int i = 2; i < plusPos; i++) {
                    maxRoll.append(msgSymbols[i]);
                }
                this.maxRoll = Integer.parseInt(maxRoll.toString());
                z = Integer.parseInt(String.valueOf(msgSymbols[plusPos+1]));
            } else {
                rollsAmount = Integer.parseInt(String.valueOf(msgSymbols[0]));
                StringBuilder maxRoll = new StringBuilder();
                for (int i = 2; i < msgSymbols.length; i++) {
                    maxRoll.append(msgSymbols[i]);
                }
                this.maxRoll = Integer.parseInt(maxRoll.toString());
            }
        }
    }

    private int findPlusPos(char[] symbols) {
        for (int i = 0; i < symbols.length; i++) {
            if (String.valueOf(symbols[i]).equals("+")) {
                return i;
            }
        }
        return 0;
    }

    public String roll() {
        RollCube rollCube = new RollCube();
        return rollCube.roll(rollsAmount, maxRoll, z);
    }
}
