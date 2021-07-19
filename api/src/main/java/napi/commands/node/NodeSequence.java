package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

import java.util.*;

public class NodeSequence extends CommandNode {

    private final List<CommandNode> nodes;

    public NodeSequence(List<CommandNode> nodes) {
        super(null);
        this.nodes = nodes;
    }

    @Override
    public String getUsage(){
        List<String> usage = new ArrayList<>();

        for (CommandNode node : nodes){
            usage.add(node.getUsage());
        }

        return String.join(" ", usage);
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        Set<String> completions = new HashSet<>();
        CommandContext context = new CommandContext();

        for (CommandNode node : this.nodes) {
            CommandArguments state = args.snapshot();

            try {
                node.parse(sender, args, context);

                if (state.equals(args.snapshot())) {
                    completions.addAll(node.getSuggestions(sender, args));
                    args.apply(state);
                } else if (args.hasNext()) {
                    completions.clear();
                } else {
                    args.apply(state);
                    completions.addAll(node.getSuggestions(sender, args));

                    if (!(node instanceof NodeOptional)) {
                        break;
                    }

                    args.apply(state);
                }
            } catch (ArgumentParseException e) {
                args.apply(state);
                completions.addAll(node.getSuggestions(sender, args));
                break;
            }
        }

        return new ArrayList<>(completions);
    }

    @Override
    public void parse(CommandSender sender, CommandArguments args, CommandContext context) throws ArgumentParseException {
        for (CommandNode node : nodes){
            node.parse(sender, args, context);
        }
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) {
        return null;
    }
}
