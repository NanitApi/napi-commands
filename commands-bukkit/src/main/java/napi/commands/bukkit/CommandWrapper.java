package napi.commands.bukkit;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

import java.util.List;

public final class CommandWrapper extends BukkitCommand {

    private final BukkitCommandManager manager;

    public CommandWrapper(String name, String description, String usageMessage, List<String> aliases, BukkitCommandManager manager) {
        super(name, description, usageMessage, aliases);
        this.manager = manager;
    }

    @Override
    public boolean execute(CommandSender bsender, String label, String[] args) {
        String line = label + " " + String.join(" ", args);
        BukkitCommandSender sender = new BukkitCommandSender(manager, bsender);
        manager.process(sender, line);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender bsender, String alias, String[] args) throws IllegalArgumentException {
        String line = alias + " " + String.join(" ", args);
        BukkitCommandSender sender = new BukkitCommandSender(manager, bsender);
        return manager.complete(sender, line);
    }
}