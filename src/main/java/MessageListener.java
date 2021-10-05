import commands.Command;
import commands.rollcube.CubeParser;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;
import services.AiTextGenYandex;

import javax.annotation.Nonnull;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MessageListener extends ListenerAdapter {

    private final List<Command> commands = getCommands();
    private final Map<String, Class<? extends Command>> commandPrefixes = getCommandPrefixes();

    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.isFromType(ChannelType.PRIVATE)) {
            findCommand(event);
        }

        for (Member member : event.getMessage().getMentionedMembers()) {
            if (member.getEffectiveName().equals("Разикаль")) {
                String message = event.getMessage().getContentDisplay().replace("@Разикаль ", "").trim();
                if (!message.equals("")) {
                    try {
                        System.out.println("getting answer");
                        String answer = AiTextGenYandex.getResponse(message);
                        event.getMessage().reply(answer).queue();
                    } catch (Exception e) {
                        event.getMessage().reply("<:okayeg:681523147606065221>").queue();
                    }
                }
            }
        }
    }

    private void findCommand(@NotNull MessageReceivedEvent event) {
        String prefix = event.getMessage().getContentRaw().split(" ")[0];
        Class<? extends Command> clazz = commandPrefixes.get(prefix);
        if (clazz != null) {
            try {
                clazz.getConstructor().newInstance().proposeEvent(event);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    private Map<String, Class<? extends Command>> getCommandPrefixes() {
        Map<String, Class<? extends Command>> prefixes = new HashMap<>();
        for (Command command : commands) {
            prefixes.putAll(command.getPrefixes());
        }
        System.out.println(prefixes.keySet());
        return prefixes;
    }

    private List<Command> getCommands() {
        Reflections reflections = new Reflections("commands");
        Set<Class<? extends Command>> commandClasses = reflections.getSubTypesOf(Command.class);
        List<Command> commands = new ArrayList<>();
        for (Class<? extends Command> clazz : commandClasses) {
            Command command;
            try {
                command = clazz.getConstructor().newInstance();
                commands.add(command);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return commands;
    }
}
