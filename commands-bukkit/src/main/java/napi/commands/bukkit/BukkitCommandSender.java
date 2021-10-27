package napi.commands.bukkit;

import napi.commands.manager.CommandManager;
import napi.commands.parsed.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public final class BukkitCommandSender implements CommandSender {

    private final CommandManager manager;
    private final org.bukkit.command.CommandSender sender;

    public BukkitCommandSender(CommandManager manager, org.bukkit.command.CommandSender sender){
        this.manager = manager;
        this.sender = sender;
    }

    @Override
    public CommandManager getManager() {
        return manager;
    }

    @Override
    public <T> T getSender() {
        return (T) sender;
    }

    @Override
    public String getName() {
        return sender.getName();
    }

    @Override
    public Optional<UUID> getUuid() {
        return sender instanceof Player ? Optional.of(((Player)sender).getUniqueId()) : Optional.empty();
    }

    @Override
    public void sendMessage(String... message) {
        sender.sendMessage(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return sender.hasPermission(permission);
    }
}
