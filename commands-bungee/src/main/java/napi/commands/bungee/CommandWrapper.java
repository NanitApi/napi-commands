package napi.commands.bungee;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public final class CommandWrapper extends Command {

    private final BungeeCommandManager manager;

    public CommandWrapper(BungeeCommandManager manager, String name, String... aliases) {
        super(name, null, aliases);
        this.manager = manager;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        String line = getName() + " " + String.join(" ", args);
        BungeeCommandSender sender = new BungeeCommandSender(manager, commandSender);
        manager.process(sender, line);
    }

}
