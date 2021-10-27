package napi.commands.bungee;

import napi.commands.Command;
import napi.commands.manager.AbstractCommandManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.command.ConsoleCommandSender;
import net.md_5.bungee.event.EventHandler;

import java.util.List;

public final class BungeeCommandManager extends AbstractCommandManager implements Listener {

    private final Plugin plugin;

    public BungeeCommandManager(Plugin plugin){
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }

    @Override
    public void register(Command command, String... aliases) {
        CommandWrapper wrapper = new CommandWrapper(this, aliases[0], aliases);
        ProxyServer.getInstance().getPluginManager().registerCommand(plugin, wrapper);
        addCommand(command, aliases);
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event){
        if (event.isCancelled()) return;

        String line = event.getCursor();

        if(line.charAt(0) == '/') line = line.substring(1);

        CommandSender bungeeSender;

        if (event.getSender() instanceof ProxiedPlayer){
            // Sender is a player
            bungeeSender = (ProxiedPlayer) event.getSender();
        } else {
            // Sender is a console
            bungeeSender = ConsoleCommandSender.getInstance();
        }

        BungeeCommandSender sender = new BungeeCommandSender(this, bungeeSender);
        List<String> suggestions = complete(sender, line);

        event.getSuggestions().addAll(suggestions);
    }
}
