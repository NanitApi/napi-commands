package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;

import java.util.Arrays;
import java.util.List;

public class NodeBool extends CommandNode {

    public NodeBool(String key) {
        super(key);
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        return Arrays.asList("true", "false");
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        String val = args.next();
        return val.equals("true") || val.equals("1") || val.equals("yes");
    }

}
