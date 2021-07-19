package napi.commands;

import napi.commands.parsed.CommandArguments;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

import java.util.List;
import java.util.Optional;

/**
 * Represents a callable command data
 */
public interface CommandCallable {

    /**
     * Get description of what this command does
     * @return Description of the command
     */
    Optional<String> getDescription();

    /**
     * Get the permission for this command (if specified)
     * @return Optional of the permission for this command
     */
    Optional<String> getPermission();

    /**
     * Get command help text
     * @return Command help text
     */
    Optional<String[]> getHelp();

    /**
     * Get executor for this command
     * @return Command executor
     */
    CommandExecutor getExecutor();

    /**
     * Get command usage
     * @return Command element usage
     */
    String getUsage();

    /**
     * Execute command with parsed arguments
     * @param sender A user who performed command
     * @param args Parsed arguments
     * @param context Command context (arguments)
     */
    void execute(CommandSender sender, CommandArguments args, CommandContext context);

    /**
     * Get available suggestions based on current cursor position.
     * If return empty list, the usage will be displayed instead.
     * @param sender Subject who performed command
     * @param args PArsed arguments
     * @return List of available suggestions. May be empty
     */
    List<String> complete(CommandSender sender, CommandArguments args);

}