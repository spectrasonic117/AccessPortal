package com.spectrasonic.accessportal;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.CommandCompletion;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

@CommandAlias("portalpass")
public class PortalCommand extends BaseCommand {

    private final Main plugin;

    public PortalCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Subcommand("reload")
    @Default
    @CommandCompletion("reload")
    public void onReload(CommandSender sender) {
        plugin.loadConfig();
        String reloadMessage = plugin.getConfig().getString("messages.reload-message", "<green>Configuration reloaded successfully!");
        sender.sendMessage(MiniMessage.miniMessage().deserialize(reloadMessage));
    }

    @Subcommand("version")
    @CommandCompletion("version")
    public void onVersion(CommandSender sender) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize("<green>Access Portal v1.0.0"));
    }
}