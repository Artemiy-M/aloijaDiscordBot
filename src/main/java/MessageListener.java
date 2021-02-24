import commands.rollcube.CubeParser;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        String author = event.getAuthor().getName();
        String messageText = event.getMessage().getContentRaw();

        if (messageText.startsWith("/r ") && messageText.contains("d")) {
            sendMessage(event, author + new CubeParser(messageText).roll());
        }
    }

    private void sendMessage(MessageReceivedEvent event, String message) {
        event.getChannel().sendMessage("```" + " " + message + " " + "```").queue();
    }
}
