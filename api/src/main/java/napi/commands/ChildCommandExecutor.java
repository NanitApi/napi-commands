package napi.commands;

import napi.commands.exception.ArgumentParseException;
import napi.commands.exception.CommandException;
import napi.commands.manager.AbstractCommandManager;
import napi.commands.manager.CommandManager;
import napi.commands.node.CommandNode;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public final class ChildCommandExecutor extends CommandNode implements CommandExecutor {

    private static final AtomicInteger COUNTER = new AtomicInteger();

    private final CommandManager commandManager;

    public ChildCommandExecutor() {
        super("child_" + COUNTER.getAndIncrement());
        this.commandManager = new ChildCommandManager();
    }

    public void register(Command command, List<String> aliases){
        commandManager.register(command, aliases);
    }

    @Override
    public List<String> getSuggestions(CommandSender sender, CommandArguments args) {
        String childName = args.nextIfPresent();

        if (childName == null){
            return Collections.emptyList();
        }

        Command child = commandManager.getCommand(childName);

        if (child == null) {
            return new ArrayList<>(commandManager.getCommandKeys(sender));
        }

        if (child.hasPermission(sender)){
            return child.complete(sender, args);
        }

        return Collections.emptyList();
    }

    private List<String> filterInput(Collection<String> col, String input){
        return col.stream().filter((elem)->elem.startsWith(input)).collect(Collectors.toList());
    }

    @Override
    public void parse(CommandSender sender, CommandArguments args, CommandContext context) throws ArgumentParseException {
        CommandArguments state = args.snapshot();
        String key = args.next();
        Command command = commandManager.getCommand(key);

        if (command != null) {
            try {
                command.getArguments().parse(sender, args, context);
                context.addArgument(getKey(), command);
            } catch (ArgumentParseException ex) {
                args.apply(state);
                args.next();
                throw ex;
            }
        }
    }

    @Override
    public void execute(CommandSender sender, CommandContext args) throws CommandException {
        Optional<Command> command = args.get(getKey());

        if (command.isPresent()){
            command.get().checkPermission(sender);
            command.get().getExecutor().execute(sender, args);
        } else {
            throw new CommandException("Child command not exists", true)
                    .withMessage(ErrorMessages.CHILD_NOT_FOUND);
        }
    }

    @Override
    public Object parseValue(CommandSender sender, CommandArguments args) throws ArgumentParseException {
        return null;
    }

    public static class ChildCommandManager extends AbstractCommandManager {

        @Override
        public void register(Command command, String... aliases) {
            addCommand(command, aliases);
        }

    }
}
