package napi.commands.bungee.nodes;

import napi.commands.ErrorMessages;
import napi.commands.exception.ArgumentParseException;
import napi.commands.node.CommandNode;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;
import java.util.stream.Collectors;

public class NodePlayer extends CommandNode {

    public NodePlayer(String key) {
        super(key);
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        return ProxyServer.getInstance().getPlayers().stream()
                .map(ProxiedPlayer::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args.next());

        if (player == null){
            throw new ArgumentParseException("Player not found")
                    .withMessage(ErrorMessages.PLAYER_NOT_FOUND);
        }

        return player;
    }
}
