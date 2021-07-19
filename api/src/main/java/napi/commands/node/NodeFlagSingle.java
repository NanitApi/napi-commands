package napi.commands.node;

import napi.commands.exception.ArgumentParseException;
import napi.commands.ErrorMessages;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

public class NodeFlagSingle extends CommandNode {

    private final String permission;
    private final boolean throwError;

    public NodeFlagSingle(String key, String permission, boolean throwError) {
        super(key);
        this.permission = permission;
        this.throwError = throwError;
    }

    @Override
    public String getUsage() {
        return "[-" + getKey() + "]";
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        return null;
    }

    @Override
    public void parse(CommandSender sender, CommandArguments args, CommandContext context) throws ArgumentParseException {
        CommandArguments snapshot = args.snapshot();

        while (snapshot.hasNext()){
            String arg = snapshot.next();

            if (arg.startsWith("-" + getKey()) && checkPermission(sender)){
                context.addArgument(arg.substring(1), true);
                args.remove(snapshot);
            }
        }

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
