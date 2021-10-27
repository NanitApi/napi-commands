package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;

public class NodeDouble extends CommandNode {

    public NodeDouble(String key) {
        super(key);
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        try {
            return Double.parseDouble(args.next());
        } catch (NumberFormatException e){
            throw new ArgumentParseException("Cannot parse double from string")
                    .withMessage(sender.getManager().getMessages().getTypeErrDouble());
        }
    }

}
