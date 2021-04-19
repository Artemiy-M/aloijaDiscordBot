package commands.rollcube;

public class CubeParser {
    int rollsAmount = 1;
    int maxRoll = 1;
    int z = 0;
    boolean doubleModificator = false;

    public CubeParser(String message) {
        parseMessage(message);
    }

    private void parseMessage(String message) {
        if (message.startsWith("/r ") && message.contains("d")) {
            String messageBody = message.split(" ")[1];
            rollsAmount = Integer.parseInt(messageBody.substring(0, 1));

            if (messageBody.contains("+") || messageBody.contains("-")) {
                parseWithModificator(messageBody);
            } else {
                maxRoll = Integer.parseInt(messageBody.substring(2));
            }

        }
    }

    private void parseWithModificator(String messageBody) {
        String modificator = "-";
        if (messageBody.contains("+")) {
            modificator = "+";
        }

        int modificatorPosition = messageBody.indexOf(modificator);

        maxRoll = Integer.parseInt(messageBody.substring(2, modificatorPosition));

        if (messageBody.contains(modificator + modificator)) {
            doubleModificator = true;
            modificatorPosition++;
        }

        z = Integer.parseInt(messageBody.substring(modificatorPosition+1));

        if (modificator.equals("-")) {
            z = -z;
        }
    }

    public String roll() {
        return new RollCube().roll(rollsAmount, maxRoll, z, doubleModificator);
    }
}
