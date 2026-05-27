package xyz.vapezyy.discordrewards.bot.listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.vapezyy.discordrewards.configuration.PluginConfig;

public class MessageReceivedListener extends ListenerAdapter {

    private final PluginConfig pluginConfig;

    public MessageReceivedListener(PluginConfig pluginConfig) {
        this.pluginConfig = pluginConfig;
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.isWebhookMessage() || event.getAuthor().isBot()) return;

        final TextChannel textChannel = event.getChannel().asTextChannel();
        final Member member = event.getMember();
        final Message message = event.getMessage();
        final String rawMessage = message.getContentRaw();

        final Player player = Bukkit.getPlayer(rawMessage);
        if (player == null) {
            message.reply(this.pluginConfig.getInvalidNameMessage()).queue();
            return;
        }

        player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
    }

}
