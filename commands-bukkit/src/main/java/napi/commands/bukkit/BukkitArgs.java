package napi.commands.bukkit;

import napi.commands.bukkit.nodes.NodeLocation;
import napi.commands.bukkit.nodes.NodePlayer;
import napi.commands.bukkit.nodes.NodePosition;
import napi.commands.bukkit.nodes.NodeWorld;
import napi.commands.node.CommandNode;

/**
 * Bukkit arguments factory
 */
public final class BukkitArgs {

    private BukkitArgs(){}

    /**
     * This node provide player selector.
     * On tab complete it will display all player names
     * @param key Unique argument key
     * @return Created command node
     */
    public static CommandNode player(String key){
        return new NodePlayer(key);
    }

    /**
     * Location argument
     * Accepts full location data: world, x, y, z, yaw, pitch
     * Adds in context native location object
     * @param key Unique argument key
     * @return Created command node
     */
    public static CommandNode location(String key){
        return new NodeLocation(key);
    }

    /**
     * Position argument
     * Accepts only position data: x, y, z
     * Adds in context native location object
     * @param key Unique argument key
     * @return Created command node
     */
    public static CommandNode position(String key){
        return new NodePosition(key);
    }

    /**
     * World argument
     * Provide available server's worlds list on complete
     * Adds in context native world object
     * @param key Unique argument key
     * @return Created command node
     */
    public static CommandNode world(String key){
        return new NodeWorld(key);
    }
}
