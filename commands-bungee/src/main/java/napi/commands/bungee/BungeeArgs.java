package napi.commands.bungee;

import napi.commands.bungee.nodes.NodePlayer;
import napi.commands.bungee.nodes.NodeServer;
import napi.commands.node.CommandNode;

/**
 * Arguments factory for BungeeCord
 */
public final class BungeeArgs {

    private BungeeArgs(){}

    /**
     * Argument read player name and convert it to ProxiedPlayer object
     * Also suggest available player nicknames on tab complete
     * @param key Unique node key
     * @return Created command node
     */
    public static CommandNode player(String key){
        return new NodePlayer(key);
    }

    /**
     * Argument read server name and convert it to ServerInfo object
     * Also suggest available servers on tab complete
     * @param key Unique node key
     * @return Created command node
     */
    public static CommandNode server(String key){
        return new NodeServer(key);
    }

}
