package napi.commands.bukkit;

import napi.commands.Command;
import napi.commands.manager.AbstractCommandManager;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.Arrays;

public final class BukkitCommandManager extends AbstractCommandManager implements Listener {

    private final Plugin plugin;
    private SimpleCommandMap commandMap;

    public BukkitCommandManager(Plugin plugin){
        this.plugin = plugin;

        try{
            String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            Class<?> craftServerClass = Class.forName(String.format("org.bukkit.craftbukkit.%s.CraftServer", version));
            Method getCommandMap = craftServerClass.getDeclaredMethod("getCommandMap");
            commandMap = (SimpleCommandMap) getCommandMap.invoke(Bukkit.getServer());
        } catch (ReflectiveOperationException e){
            e.printStackTrace();
        }
    }

    @Override
    public void register(Command command, String... aliases) {
        String label = aliases[0];
        String usage = String.join("\n", command.getHelp().orElse(new String[0]));

        CommandWrapper wrapper = new CommandWrapper(label,
                command.getDescription().orElse(plugin.getDescription().getName() + " command"),
                usage,
                Arrays.asList(aliases),
                this);

        commandMap.register(label, plugin.getDescription().getName(), wrapper);
        addCommand(command, aliases);
    }


}
