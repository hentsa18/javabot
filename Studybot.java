package com.Discord.Studybot;

import com.Discord.Studybot.commands.CommandManager;
import com.Discord.Studybot.listeners.EventListeners;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.util.function.IntFunction;

public class Studybot {

    private final Dotenv config;
    private final ShardManager shardManager;



    public Studybot() throws LoginException {
        config = Dotenv.configure().load();
        String token = config.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.listening("Lofi music and studying"));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGE_TYPING,GatewayIntent.GUILD_MEMBERS,GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_PRESENCES,GatewayIntent.GUILD_VOICE_STATES);
        shardManager = builder.build();

        shardManager.addEventListener(new EventListeners(), new CommandManager());

    }

    public Dotenv getConfig(){
        return config;
    }
    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            Studybot bot = new Studybot();
        } catch (LoginException e) {
            System.out.println("ERROR:provided bot token is invalided");
        }
    }
}






