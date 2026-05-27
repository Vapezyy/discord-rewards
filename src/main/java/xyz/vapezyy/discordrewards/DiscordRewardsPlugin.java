package xyz.vapezyy.discordrewards;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.vapezyy.discordrewards.bot.BotManager;
import xyz.vapezyy.discordrewards.configuration.PluginConfig;

import java.io.File;

public class DiscordRewardsPlugin extends JavaPlugin {

    private PluginConfig pluginConfig;
    private BotManager botManager;

    @Override
    public void onEnable() {
        this.pluginConfig = ConfigManager.create(PluginConfig.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
            it.withBindFile(new File(this.getDataFolder(), "config.yml"));
            it.saveDefaults();
            it.load(true);
        });

        this.botManager = new BotManager(this.pluginConfig.getBotToken());
        this.botManager.start(this.pluginConfig);
    }

    @Override
    public void onDisable() {
    }

}
