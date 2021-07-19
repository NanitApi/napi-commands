package napi.commands.bukkit.nodes;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.node.CommandNode;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class NodeLocation extends CommandNode {

    public NodeLocation(String key) {
        super(key);
    }

    @Override
    public String getUsage() {
        return "<world> <x> <y> <z> <yaw> <pitch>";
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        Location location;

        try {
            World world = Bukkit.getWorld(args.next());

            if (world == null){
                throw new ArgumentParseException("Invalid world name")
                        .withMessage(ErrorMessages.TYPE_ERR_WORLD);
            }

            double x = Double.parseDouble(args.next());
            double y = Double.parseDouble(args.next());
            double z = Double.parseDouble(args.next());
            float yaw = Float.parseFloat(args.next());
            float pitch = Float.parseFloat(args.next());

            location = new Location(world, x, y, z, yaw, pitch);
        } catch (NumberFormatException e){
            throw new ArgumentParseException("Error while parse number")
                    .withMessage(ErrorMessages.TYPE_ERR_LOCATION);
        }

        return location;
    }
}
