package napi.commands.bungee.nodes;

import napi.commands.exception.ArgumentParseException;
import napi.commands.node.CommandNode;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;

import java.util.ArrayList;
import java.util.List;

public class NodeServer extends CommandNode {

    public NodeServer(String key) {
        super(key);
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        return new ArrayList<>(ProxyServer.getInstance().getServers().keySet());
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        ServerInfo server = ProxyServer.getInstance().getServerInfo(args.next());

        if (server == null){
            throw new ArgumentParseException("Server not found")
                    .withMessage(sender.getManager().getMessages().getServerNotFound());
        }

        return server;
    }
}
