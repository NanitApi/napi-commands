package napi.commands.manager;

import napi.commands.Command;
import napi.commands.ErrorMessages;
import napi.commands.parsed.CommandSender;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface CommandManager {

    /**
     * Get command by one of the registered aliases
     * @param alias Command alias
     * @return Command object if command found or null otherwise
     */
    Command getCommand(String alias);

    /**
     * Get command if available for specified player
     * @param alias Command alias
     * @param sender Command sender
     * @return Command object if command found or null otherwise
     */
    Command getCommand(String alias, CommandSender sender);

    /**
     * Get commands available for specified user
     * @param sender Command sender
     * @return List of available commands
     */
    List<Command> getCommands(CommandSender sender);

    /**
     * Get all registered aliases
     * @param sender Command sender
     * @return Registered commands aliases
     */
    Set<String> getCommandKeys(CommandSender sender);

    /**
     * Get all registered commands
     * @return Collection of registered commands
     */
    Collection<Command> getRegisteredCommands();

    /**
     * Process command by input line
     * @param sender Who entered command
     * @param line Command line without slash (/), like "kick Notch"
     */
    void process(CommandSender sender, String line);

    /**
     * Get available tab complete content (list of suggestions)
     * @param sender Who entered command
     * @param line Command line before cursor and without slash (/)
     * @return Tab complete result
     */
    List<String> complete(CommandSender sender, String line);

    /**
     * Register command with specified aliases and base name
     * @param command Command instance
     * @param aliases Command aliases
     */
    void register(Command command, String... aliases);

    /**
     * Register command with specified aliases and base name
     * @param command Command instance
     * @param aliases Command aliases
     */
    default void register(Command command, List<String> aliases){
        register(command, aliases.toArray(new String[0]));
    }

    /**
     * Get error messages container
     * @return Error messages
     */
    ErrorMessages getMessages();
}
