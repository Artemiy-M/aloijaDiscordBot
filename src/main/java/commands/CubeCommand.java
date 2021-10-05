package commands;

import commands.rollcube.CubeParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CubeCommand implements Command {

    @CommandPrefix
    private final String rollPrefix = "/r";


    @Override
    public void proposeEvent(MessageReceivedEvent event) {
        if (getMessageText(event).startsWith(rollPrefix)) {
            String result = event.getAuthor().getName() + roll(event);
            sendStringMessage(event, result);
        }
    }

    private String roll(MessageReceivedEvent event) {
        CubeParser cubeParser = new CubeParser(getMessageText(event), rollPrefix);
        return cubeParser.roll();
    }
}
