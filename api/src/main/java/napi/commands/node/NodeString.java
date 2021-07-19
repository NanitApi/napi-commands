package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;

public class NodeString extends CommandNode {

    public NodeString(String key) {
        super(key);
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        return args.next();
    }

}
