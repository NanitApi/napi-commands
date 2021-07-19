package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

import java.util.Collections;
import java.util.List;

public class NodePermission extends CommandNode {

    private final CommandNode node;
    private final String permission;
    private final boolean throwError;

    public NodePermission(CommandNode node, String permission, boolean throwError) {
        super(node.getKey());
        this.node = node;
        this.permission = permission;
        this.throwError = throwError;
    }

    @Override
    public String getUsage() {
        return node.getUsage();
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        List<String> suggestions = node.getSuggestions(sender, args);
        return sender.hasPermission(permission) ? suggestions : Collections.emptyList();
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        return node.parseValue(sender, args);
    }

    @Override
    public void parse(CommandSender sender, CommandArguments args, CommandContext context) throws ArgumentParseException {
        if (checkPermission(sender)){
            node.parse(sender, args, context);
        }
    }

    private boolean checkPermission(CommandSender sender) throws ArgumentParseException {
        boolean hasPermission = sender.hasPermission(this.permission);
        if (!hasPermission && this.throwError) {
            throw new ArgumentParseException("Argument permission deny")
                    .withMessage(ErrorMessages.PERMISSION_DENY_ARG);
        }
        return hasPermission;
    }
}
