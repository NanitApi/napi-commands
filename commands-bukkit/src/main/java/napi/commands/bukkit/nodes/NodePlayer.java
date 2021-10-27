package napi.commands.bukkit.nodes;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.node.CommandNode;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class NodePlayer extends CommandNode {

    public NodePlayer(String key) {
        super(key);
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        Player player = Bukkit.getPlayer(args.next());

        if (player == null){
            throw new ArgumentParseException("Player not found")
                    .withMessage(sender.getManager().getMessages().getPlayerNotFound());
        }

        return player;
    }
}
