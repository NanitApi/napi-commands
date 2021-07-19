package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

import java.util.Collections;
import java.util.List;

public abstract class CommandNode {

    private final String key;
    private String usage;

    public CommandNode(String key){
        this.key = key;
    }

    /**
     * Get node key. Might be null
     * @return Node unique key
     */
    public String getKey(){
        return this.key;
    }

    /**
     * Get node usage based on the sender and parsed arguments
     * @return Node usage
     */
    public String getUsage() {
        return usage == null ? String.format("<%s>", getKey()) : usage;
    }

    public void setUsage(String usage){
        this.usage = usage;
    }

    /**
     * Parse some object from arguments
     * @param sender Command sender
     * @param args Parsed arguments
     * @return Parsed object
     * @throws ArgumentParseException if something wrong while parsing
     */
    public abstract Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException;

    /**
     * Get suggestions (tab complete) for this node
     * @param sender Command sender
     * @param args Parsed arguments
     * @return List of suggestions
     */
    public List<String> getSuggestions(CommandSender sender, CommandArguments args){
        return Collections.singletonList(getUsage());
    }

    public void parse(CommandSender sender, CommandArguments args, CommandContext context) throws ArgumentParseException {
        Object value = parseValue(sender, args);

        if (value != null){
            if (value instanceof Iterable<?>){
                Iterable<?> iterable = (Iterable<?>) value;

                for (Object val : iterable){
                    context.addArgument(getKey(), val);
                }
            } else {
                context.addArgument(getKey(), value);
            }
        }
    }
}
