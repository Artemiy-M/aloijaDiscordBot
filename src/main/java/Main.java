import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class Main {

    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createDefault("NTcwMTg3NDc2MzUwNTMzNjMz.XL7idw.ph9ZN01JHtxS_oyC3ylKOD1bKJc")
                .build();
        jda.addEventListener(new MessageListener());
    }
}