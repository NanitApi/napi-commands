package napi.commands.bukkit.nodes;

import napi.commands.exception.ArgumentParseException;
import napi.commands.node.CommandNode;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;
import java.util.stream.Collectors;

public class NodeWorld extends CommandNode {

    public NodeWorld(String key) {
        super(key);
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        return Bukkit.getWorlds().stream()
                .map(World::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        World world = Bukkit.getWorld(args.next());

        if (world == null){
            throw new ArgumentParseException("Invalid world name")
                    .withMessage(sender.getManager().getMessages().getTypeErrWorld());
        }

        return world;
    }
}
