package napi.commands.parsed;

import napi.commands.manager.CommandManager;

import java.util.Optional;
import java.util.UUID;

/**
 * Represent user who performs command.
 */
public interface CommandSender {

    /**
     * Get command manager created this sender
     * @return Command manager instance
     */
    CommandManager getManager();

    /**
     * Get native sender object
     * @param <T> Sender type
     * @return Native sender object
     */
    <T> T getSender();

    /**
     * Get name of the sender
     * @return Name of the sender
     */
    String getName();

    /**
     * Get UUID (if exists) of the sender
     * @return UUID optional of the sender
     */
    Optional<UUID> getUuid();

    /**
     * Send message to the command sender
     * @param message Message lines
     */
    void sendMessage(String... message);

    /**
     * Check is sender has required permission
     * @param permission Required permission string
     * @return true if has permission or false otherwise
     */
    boolean hasPermission(String permission);

}
