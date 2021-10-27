package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class NodeEnum extends CommandNode {

    private final Class enumType;
    private final List<String> values;

    public NodeEnum(String key, Class<?> enumType) {
        super(key);
        this.enumType = enumType;
        this.values = new ArrayList<>();

        for (Object en : enumType.getEnumConstants()){
            values.add(en.toString());
        }
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        return values;
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        try{
            return Enum.valueOf(enumType, args.next());
        } catch (IllegalArgumentException e){
            throw new ArgumentParseException("Invalid enum value")
                    .withMessage(sender.getManager().getMessages().getTypeErrEnum());
        }
    }

}
