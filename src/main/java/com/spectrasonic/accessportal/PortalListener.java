package com.spectrasonic.accessportal;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PortalListener implements Listener {

    private final Main plugin;

    public PortalListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        event.getTo();
        if (event.getTo().getWorld() == null) return;

        String worldName = event.getTo().getWorld().getName();
        String permission = switch (worldName) {
            case "world_nether" -> "portal.pass.nether";
            case "world_the_end" -> "portal.pass.end";
            default -> null;
        };

        if (permission == null || event.getPlayer().hasPermission(permission)) return;

        event.setCancelled(true);
        String title = plugin.getConfig().getString("messages.denied-title", "<red>Access Denied");
        String subtitle = plugin.getConfig().getString("messages.denied-subtitle", "You need permission to pass");

        event.getPlayer().showTitle(net.kyori.adventure.title.Title.title(
                MiniMessage.miniMessage().deserialize(title),
                MiniMessage.miniMessage().deserialize(subtitle)
        ));
    }
}