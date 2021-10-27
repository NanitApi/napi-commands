package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;

import java.util.List;

public class NodeChoice extends CommandNode {

    private final List<String> values;

    public NodeChoice(String key, List<String> values) {
        super(key);
        this.values = values;
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        return values;
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        String value = args.next();

        if (!values.contains(value)){
            throw new ArgumentParseException("Invalid choice value")
                    .withMessage(sender.getManager().getMessages().getTypeErrChoice());
        }

        return value;
    }

}
