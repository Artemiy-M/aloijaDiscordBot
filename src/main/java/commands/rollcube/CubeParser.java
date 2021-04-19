package commands.rollcube;

public class CubeParser {
    private int rollsAmount = 1;
    private int maxRoll = 1;
    private int z = 0;
    private boolean doubleModificator = false;
    private final String prefix;
    private boolean parseError = false;

    public CubeParser(String message, String prefix) {
        this.prefix = prefix;
        if (message.contains("d") || message.contains("D")) {
            parseMessage(message);
        }
    }

    private void parseMessage(String message) {
        try {
            String messageBody = message.replace(prefix, "").trim();
            rollsAmount = Integer.parseInt(messageBody.substring(0, 1));
            if (messageBody.contains("+") || messageBody.contains("-")) {
                parseWithModificator(messageBody);
            } else {
                maxRoll = Integer.parseInt(messageBody.substring(2));
            }
        } catch (Exception e) {
            parseError = true;
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
        if (parseError) {
            return "Ошибка команды";
        }
        return new RollCube().roll(rollsAmount, maxRoll, z, doubleModificator);
    }
}
