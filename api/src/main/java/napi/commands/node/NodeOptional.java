package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

import java.util.List;

public class NodeOptional extends CommandNode {

    private final CommandNode node;

    public NodeOptional(CommandNode node) {
        super(node.getKey());
        this.node = node;
        this.node.setUsage(getUsage());
    }

    @Override
    public String getUsage() {
        return String.format("[%s]", node.getUsage());
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        return node.getSuggestions(sender, args);
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        return args.hasNext() ? null : node.parseValue(sender, args);
    }

    @Override
    public void parse(CommandSender sender, CommandArguments args, CommandContext context) throws ArgumentParseException {
        if (!args.hasNext()){
            return;
        }

        CommandArguments snapshot = args.snapshot();

        try{
            node.parse(sender, args, context);
        } catch (ArgumentParseException e){
            if(args.hasNext()){
                args.apply(snapshot);
            } else {
                throw e;
            }
        }
    }
}
