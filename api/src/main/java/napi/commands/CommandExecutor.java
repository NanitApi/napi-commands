package napi.commands;

import napi.commands.exception.CommandException;
import napi.commands.parsed.CommandContext;
import napi.commands.parsed.CommandSender;

@FunctionalInterface
public interface CommandExecutor {

    /**
     * Execute command with given arguments (CommandContext) and command sender
     * @param sender Command sender implementation
     * @param args Parsed arguments
     * @throws CommandException if some wrong while executing
     */
    void execute(CommandSender sender, CommandContext args) throws CommandException;

}
