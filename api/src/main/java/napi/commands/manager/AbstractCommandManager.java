package napi.commands.manager;

import napi.commands.exception.ArgumentParseException;
import napi.commands.parsed.CommandContext;
import napi.commands.Command;
import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandSender;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractCommandManager implements CommandManager {

    private final Map<String, Command> commands = new HashMap<>();

    @Override
    public Command getCommand(String alias) {
        return alias == null ? null : commands.get(alias.toLowerCase());
    }

    @Override
    public Command getCommand(String alias, CommandSender sender) {
        Command command = getCommand(alias);
        return command != null && command.hasPermission(sender) ? command : null;
    }

    @Override
    public List<Command> getCommands(CommandSender sender) {
        return commands.values().stream()
                .filter(command -> command.hasPermission(sender))
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getCommandKeys(CommandSender sender) {
        Set<String> keys = new HashSet<>();

        for (Map.Entry<String, Command> entry : commands.entrySet()){
            if (entry.getValue().hasPermission(sender)){
                keys.add(entry.getKey());
            }
        }

        return keys;
    }

    @Override
    public Collection<Command> getRegisteredCommands() {
        return commands.values();
    }

    @Override
    public void process(CommandSender sender, String line) {
        String[] parts = line.split(" ");

        if (parts.length > 0){
            String cmdName = parts[0];
            Command command = getCommand(cmdName);

            if (command != null){
                String[] args = Arrays.copyOfRange(parts, 1, parts.length);
                CommandArguments arguments = new CommandArguments(args);
                CommandContext context = new CommandContext();

                command.execute(sender, arguments, context);
            } else {
                System.out.println("Command not found");
            }
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String line) {
        List<String> parts = parseArgs(line);

        if (parts.size() > 0){
            String cmdName = parts.get(0);
            Command command = getCommand(cmdName);

            if (command != null){
                parts.remove(0);
                CommandArguments arguments = new CommandArguments(parts);
                List<String> suggestion = command.complete(sender, arguments);

                try{
                    String last = arguments.last();

                    suggestion = suggestion.stream()
                            .filter(s->s.startsWith(last))
                            .collect(Collectors.toList());
                } catch (ArgumentParseException e){
                    // Ignore
                }

                return suggestion;
            }
        }

        return Collections.emptyList();
    }

    private List<String> parseArgs(String line) {
        List<String> ret = new ArrayList<>();
        int spaceIndex;

        while ((spaceIndex = line.indexOf(" ")) != -1) {
            if (spaceIndex != 0) {
                ret.add(line.substring(0, spaceIndex));
                line = line.substring(spaceIndex);
            } else {
                line = line.substring(1);
            }
        }

        ret.add(line);

        return ret;
    }

    protected void addCommand(Command command, String... aliases){
        for (String alias : aliases){
            this.commands.put(alias.toLowerCase(), command);
        }
    }

}
