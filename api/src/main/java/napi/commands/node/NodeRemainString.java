package napi.commands.node;

import napi.commands.ErrorMessages;
import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class NodeRemainString extends CommandNode {

    public NodeRemainString(String key) {
        super(key);
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        if (!args.hasNext()){
            throw new ArgumentParseException("Argument not exists")
                    .withMessage(ErrorMessages.ARGS_OUT_OF_BOUNDS);
        }

        List<String> list = new ArrayList<>();

        while (args.hasNext()){
            list.add(args.next());
        }

        return String.join(" ", list);
    }

}
