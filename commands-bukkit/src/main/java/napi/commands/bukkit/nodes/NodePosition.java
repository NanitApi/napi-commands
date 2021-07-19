package napi.commands.bukkit.nodes;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.node.CommandNode;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NodePosition extends CommandNode {

    public NodePosition(String key) {
        super(key);
    }

    @Override
    public String getUsage() {
        return "<x> <y> <z>";
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        Location location;
        World world;

        if (sender.getSender() instanceof Player){
            world = ((Player)sender.getSender()).getWorld();
        } else {
            world = Bukkit.getWorlds().iterator().next();
        }

        try {
            double x = Double.parseDouble(args.next());
            double y = Double.parseDouble(args.next());
            double z = Double.parseDouble(args.next());

            location = new Location(world, x, y, z, 0.0F, 0.0F);
        } catch (NumberFormatException e){
            throw new ArgumentParseException("Error while parse number")
                    .withMessage(ErrorMessages.TYPE_ERR_LOCATION);
        }

        return location;
    }
}
