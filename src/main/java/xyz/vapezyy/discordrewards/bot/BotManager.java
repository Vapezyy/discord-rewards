package xyz.vapezyy.discordrewards.bot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import xyz.vapezyy.discordrewards.bot.listener.MessageReceivedListener;
import xyz.vapezyy.discordrewards.configuration.PluginConfig;

import java.util.EnumSet;

public class BotManager {

    private JDA jda;
    private final String token;

    public BotManager(String token) {
        this.token = token;
    }

    public void start(PluginConfig pluginConfig) {
        this.jda = JDABuilder.createLight(token, EnumSet.allOf(GatewayIntent.class))
                .addEventListeners(
                    new MessageReceivedListener(pluginConfig)
                )
                .build();
    }

}
