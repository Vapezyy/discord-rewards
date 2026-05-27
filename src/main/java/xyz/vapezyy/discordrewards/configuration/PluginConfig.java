package xyz.vapezyy.discordrewards.configuration;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;

@Getter
public class PluginConfig extends OkaeriConfig {

    private String botToken = "TOKEN";

    private String invalidNameMessage = "Gracz nie jest online!";

}
