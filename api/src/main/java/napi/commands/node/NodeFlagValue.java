package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

public class NodeFlagValue extends CommandNode {

    private final CommandNode flagNode;
    private final String permission;
    private final boolean throwError;

    public NodeFlagValue(String key, CommandNode flagNode, String permission, boolean throwError) {
        super(key);
        this.flagNode = flagNode;
        this.permission = permission;
        this.throwError = throwError;
    }

    @Override
    public String getUsage() {
        return "[-" + getKey() + " " + flagNode.getUsage() + "]";
    }

    @Override
    public void parse(CommandSender sender, CommandArguments args, CommandContext context) throws ArgumentParseException {
        CommandArguments snapshot = args.snapshot();

        while (snapshot.hasNext()){
            String key = snapshot.next();

            if (key.startsWith("-" + getKey()) && checkPermission(sender)){
                Object value = flagNode.parseValue(sender, snapshot);
                context.addArgument(getKey(), value);
                args.remove(snapshot.getPointer());
                args.remove(snapshot.getPointer()-1);
                break;
            }
        }
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        return null;
    }

    private boolean checkPermission(CommandSender sender) throws ArgumentParseException {
        if (permission != null && !sender.hasPermission(permission)){
            if (throwError){
                throw new ArgumentParseException("Permission flag deny")
                        .withMessage(ErrorMessages.PERMISSION_DENY_ARG);
            } else {
                return false;
            }
        }

        return true;
    }
}
