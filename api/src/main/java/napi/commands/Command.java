package napi.commands;

import napi.commands.exception.ArgumentParseException;
import napi.commands.exception.CommandException;
import napi.commands.node.CommandNode;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

import java.util.*;

public final class Command implements CommandCallable {

    private final String description;
    private final String permission;
    private final String[] help;

    private final CommandExecutor executor;
    private final CommandNode arguments;

    private Command(String description, String permission, String[] help, CommandExecutor executor, CommandNode arguments){
        this.description = description;
        this.permission = permission;
        this.help = help;
        this.arguments = arguments;

        if (executor == null){
            this.executor = new DefaultExecutor(this);
        } else {
            this.executor = executor;
        }
    }

    public CommandNode getArguments(){
        return arguments;
    }

    @Override
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.ofNullable(permission);
    }

    @Override
    public Optional<String[]> getHelp() {
        return Optional.ofNullable(help);
    }

    @Override
    public CommandExecutor getExecutor() {
        return executor;
    }

    @Override
    public String getUsage() {
        return this.arguments.getUsage();
    }

    public void checkPermission(CommandSender sender) throws CommandException{
        if (!hasPermission(sender)){
            throw new CommandException("Permission deny")
                    .withMessage(ErrorMessages.PERMISSION_DENY);
        }
    }

    public boolean hasPermission(CommandSender sender){
        if (permission == null) return true;
        return sender.hasPermission(permission);
    }

    @Override
    public void execute(CommandSender sender, CommandArguments args, CommandContext context) {
        try{
            checkPermission(sender);

            this.arguments.parse(sender, args, context);

            if(args.hasNext()) {
                throw new ArgumentParseException("Too many arguments")
                        .withMessage(ErrorMessages.ARGS_TOO_MANY);
            }

            executor.execute(sender, context);
        } catch (ArgumentParseException e) {
            if (!(executor instanceof DefaultExecutor)) {
                try {
                    executor.execute(sender, context);
                } catch (CommandException ex) {
                    sendErrorMessage(sender, ex);
                }
                return;
            }

            if (e.getLocalizedMessage() != null){
                sender.sendMessage(e.getLocalizedMessage());
            }

            if (e.isSendHelp()){
                getHelp().ifPresent(sender::sendMessage);
            }
        } catch (CommandException e) {
            sendErrorMessage(sender, e);
        }
    }

    private void sendErrorMessage(CommandSender sender, CommandException e) {
        if (e.getLocalizedMessage() != null){
            sender.sendMessage(e.getLocalizedMessage());
        }

        if (e.isSendHelp()){
            getHelp().ifPresent(sender::sendMessage);
        }
    }

    @Override
    public List<String> complete(CommandSender sender, CommandArguments args) {
        List<String> completed = this.arguments.getSuggestions(sender, args);
        return completed == null ? Collections.emptyList() : completed;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class DefaultExecutor implements CommandExecutor {

        private final CommandCallable command;

        public DefaultExecutor(CommandCallable command){
            this.command = command;
        }

        @Override
        public void execute(CommandSender sender, CommandContext args) {
            command.getHelp().ifPresent(sender::sendMessage);
        }

    }

    public static class Builder {

        private String description;
        private String permission;
        private String[] help;

        private CommandExecutor executor;

        private final Map<List<String>, Command> children;
        private final List<CommandNode> arguments;

        private Builder(){
            this.children = new LinkedHashMap<>();
            this.arguments = new ArrayList<>();
        }

        /**
         * Set command description
         * @param description Command description
         * @return Builder instance
         */
        public Builder description(String description){
            this.description = description;
            return this;
        }

        /**
         * Set command permission
         * @param permission Command permission
         * @return Builder instance
         */
        public Builder permission(String permission){
            this.permission = permission;
            return this;
        }

        /**
         * Set help message. This message will send if command used wrong
         * @param help Message lines
         * @return Builder instance
         */
        public Builder help(String... help){
            this.help = help;
            return this;
        }

        /**
         * Set help message. This message will send if command used wrong
         * @param help Message lines
         * @return Builder instance
         */
        public Builder help(Collection<String> help){
            return help(help.toArray(new String[0]));
        }

        /**
         * Set executor for this command
         * @param executor Command executor instance
         * @return Builder instance
         */
        public Builder executor(CommandExecutor executor){
            this.executor = executor;
            return this;
        }

        /**
         * Set arguments for this command
         * @param arguments Command nodes array. Each node is separate argument
         * @return Builder instance
         */
        public Builder args(CommandNode... arguments){
            Collections.addAll(this.arguments, arguments);
            return this;
        }

        /**
         * Set children command for this command with some aliases
         * @param child Command instance
         * @param aliases Command aliases
         * @return Builder instance
         */
        public Builder child(Command child, String... aliases){
            this.children.put(Arrays.asList(aliases), child);
            return this;
        }

        public Command build() {
            List<CommandNode> nodes = new ArrayList<>();

            if (!children.isEmpty()){
                ChildCommandExecutor childExecutor = new ChildCommandExecutor(executor);

                for (Map.Entry<List<String>, Command> entry : children.entrySet()){
                    childExecutor.register(entry.getValue(), entry.getKey());
                }

                executor(childExecutor);
                nodes.add(childExecutor);
            } else {
                nodes.addAll(arguments);
            }

            return new Command(description, permission, help, executor, Arguments.sequence(nodes));
        }
    }
}
