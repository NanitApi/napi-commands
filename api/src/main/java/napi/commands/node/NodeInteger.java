package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;

public class NodeInteger extends CommandNode {

    public NodeInteger(String key) {
        super(key);
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        try {
            return Integer.parseInt(args.next());
        } catch (NumberFormatException e){
            throw new ArgumentParseException("Cannot parse double from string")
                    .withMessage(ErrorMessages.TYPE_ERR_INT);
        }
    }

}
