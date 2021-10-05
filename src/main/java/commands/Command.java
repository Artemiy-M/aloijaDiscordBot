package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface Command {

    void proposeEvent(MessageReceivedEvent event);

    default String getMessageText(MessageReceivedEvent event) {
        return event.getMessage().getContentRaw();
    }

    default void sendPlainMessage(MessageReceivedEvent event, String message) {
        event.getChannel().sendMessage(message).queue();
    }

    default void sendStringMessage(MessageReceivedEvent event, String message) {
        if (!message.contains("```")) {
            message = "```" + " " + message + " " + "```";
        }
        event.getChannel().sendMessage(message).queue();
    }

    default void sendEmbeddedMessage(MessageReceivedEvent event, EmbedBuilder embed) {
        event.getChannel().sendMessage(embed.build()).queue();
    }

    default boolean messageStartsWith(MessageReceivedEvent event, String prefix) {
        return getMessageText(event).startsWith(prefix);
    }

    default boolean messageEquals(MessageReceivedEvent event, String equalString) {
        return getMessageText(event).equals(equalString);
    }

    default Map<String, Class<? extends Command>> getPrefixes() {
        Class<? extends Command> clazz = this.getClass();
        Map<String, Class<? extends Command>> prefixesMap = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.getAnnotation(CommandPrefix.class) != null) {
                field.setAccessible(true);
                try {
                    String prefix = (String) field.get(this);
                    prefixesMap.put(prefix.trim(), clazz);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return prefixesMap;
    }
}
