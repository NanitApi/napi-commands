package napi.commands;

import napi.commands.node.*;

import java.util.Arrays;
import java.util.List;

/**
 * A command arguments factory
 * Use it for briefly code
 */
public final class Arguments {

    private Arguments(){}

    /**
     * Single string argument
     * @param key Unique name of the argument
     * @return Created command node
     */
    public static CommandNode string(String key){
        return new NodeString(key);
    }

    /**
     * Collect all remain arguments in one string
     * @param key Unique name of the argument
     * @return Created command node
     */
    public static CommandNode remainString(String key){
        return new NodeRemainString(key);
    }

    /**
     * Single int argument
     * @param key Unique name of the argument
     * @return Created command node
     */
    public static CommandNode integer(String key){
        return new NodeInteger(key);
    }

    /**
     * Single double argument
     * @param key Unique name of the argument
     * @return Created command node
     */
    public static CommandNode doubleVal(String key){
        return new NodeDouble(key);
    }

    /**
     * Simple boolean argument (accept values: `true/false`, `yes/no`, `1/0`)
     * @param key Unique name of the argument
     * @return Created command node
     */
    public static CommandNode bool(String key){
        return new NodeBool(key);
    }

    /**
     * Optional argument. Its a wrapper for another argument.
     * If player miss this argument or type it wrong, error will not throw
     * @param node Command node instance
     * @return Created optional command node
     */
    public static CommandNode optional(CommandNode node){
        return new NodeOptional(node);
    }

    /**
     * Optional argument. Its a wrapper for another argument.
     * If player miss this argument or type it wrong, error will not throw
     * @param key Argument name
     * @param enumClass Class of the required enum
     * @return Created optional command node
     */
    public static CommandNode enumVal(String key, Class<?> enumClass){
        return new NodeEnum(key, enumClass);
    }

    /**
     * THis node provide list of string values to choice.
     * If player type some invalid value this will throw error
     * @param key Name of the argument
     * @param values List of the allowed values
     * @return Created optional command node
     */
    public static CommandNode choice(String key, List<String> values){
        return new NodeChoice(key, values);
    }

    /**
     * THis node provide list of string values to choice.
     * If player type some invalid value this will throw error
     * @param key Name of the argument
     * @param values Array of the allowed values
     * @return Created optional command node
     */
    public static CommandNode choice(String key, String... values){
        return choice(key, Arrays.asList(values));
    }

    /**
     * Create a sequence of some nodes
     * @param nodes Command nodes list
     * @return Created sequence of command nodes
     */
    public static CommandNode sequence(List<CommandNode> nodes){
        return new NodeSequence(nodes);
    }

    /**
     * Wrap node in permission node.
     * If player not have permission node just ignore it but not add argument in context
     * @param node Command node
     * @param permission Required permission
     * @return Created permissioned command node
     */
    public static CommandNode permission(CommandNode node, String permission){
        return new NodePermission(node, permission, false);
    }

    /**
     * Wrap node in permission node.
     * If player not have permission the error throws
     * @param node Command node
     * @param permission Required permission
     * @return Created permissioned command node
     */
    public static CommandNode permissionStrong(CommandNode node, String permission){
        return new NodePermission(node, permission, true);
    }

    /**
     * Simple boolean flag without permission
     * @param key Flag key
     * @return Created flag node
     */
    public static CommandNode flag(String key){
        return new NodeFlagSingle(key, null, false);
    }

    /**
     * Simple boolean flag with permission
     * Throws an error if player type this flag but don't have permission for this
     * @param key Flag key
     * @param permission Permission to use this flag
     * @return Created flag node
     */
    public static CommandNode flag(String key, String permission){
        return new NodeFlagSingle(key, permission, true);
    }

    /**
     * Simple boolean flag with permission
     * @param key Flag key
     * @param permission Permission to use this flag
     * @param throwError if false, node not throw error if player don't have permission
     * @return Created flag node
     */
    public static CommandNode flag(String key, String permission, boolean throwError){
        return new NodeFlagSingle(key, permission, throwError);
    }

    /**
     * Create value flag node
     * @param key Key of flag
     * @param value Value node of flag
     * @return Created flag node
     */
    public static CommandNode flagValue(String key, CommandNode value){
        return new NodeFlagValue(key, value, null, false);
    }

    /**
     * Create value flag node with specific permission
     * Throws an error if player don't have permission and use this flag
     * @param key Key of flag
     * @param permission Required flag permission
     * @param value Value node of flag
     * @return Created flag node
     */
    public static CommandNode flagValue(String key, CommandNode value, String permission){
        return new NodeFlagValue(key, value, permission, true);
    }

    /**
     * Create value flag node with specific permission
     * @param key Key of flag
     * @param value Value node of flag
     * @param permission Required permission
     * @param throwError if false, node not throw error if player don't have permission
     * @return Created flag node
     */
    public static CommandNode flagValue(String key, CommandNode value, String permission, boolean throwError){
        return new NodeFlagValue(key, value, permission, throwError);
    }

}
