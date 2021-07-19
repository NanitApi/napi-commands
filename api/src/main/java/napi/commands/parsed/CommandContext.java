package napi.commands.parsed;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents parsed command arguments
 */
public final class CommandContext {

    private final Map<String, Object> arguments;

    public CommandContext(){
        this.arguments = new HashMap<>();
    }

    private CommandContext(Map<String, Object> arguments){
        this.arguments = arguments;
    }

    /**
     * Add argument to context
     * @param key Argument key
     * @param value Argument value
     */
    public void addArgument(String key, Object value){
        arguments.put(key.toLowerCase(), value);
    }

    /**
     * Check is context empty
     * @return true if context has no any argument
     */
    public boolean isEmpty(){
        return arguments.isEmpty();
    }

    /**
     * Get parsed argument from context
     * @param key Argument key
     * @param <T> Argument value
     * @return Argument in Optional wrapper or Optional.empty() if not exists
     */
    public <T> Optional<T> get(String key){
        return Optional.ofNullable(get(key, null));
    }

    /**
     * Get parsed argument from context or default value if argument not exists
     * @param key Argument key
     * @param def Default value
     * @param <T> type of the argument
     * @return Pared argument or specified default value
     */
    public <T> T get(String key, Object def){
        return (T) arguments.getOrDefault(key.toLowerCase(), def);
    }

    /**
     * Check is context has parsed argument. Also can be used for checking flags
     * @param key Argument key
     * @return true if argument exists or false otherwise
     */
    public boolean has(String key){
        return arguments.containsKey(key);
    }

    /**
     * Get argument as boolean value (if argument is really bool)
     * @param key Argument key
     * @return Boolean argument
     */
    public boolean getBool(String key){
        return getBool(key, false);
    }

    /**
     * Get argument as boolean value with default value (if argument is really bool)
     * @param key Argument key
     * @param def Default value
     * @return Boolean argument or default value if argument not exists
     */
    public boolean getBool(String key, boolean def){
        return get(key, def);
    }

    /**
     * Get argument as integer value (if argument is really bool)
     * @param key Argument key
     * @return Integer argument
     */
    public int getInt(String key){
        return getInt(key, 0);
    }

    /**
     * Get argument as integer value with default value (if argument is really bool)
     * @param key Argument key
     * @param def Default value
     * @return Integer argument or default value if argument not exists
     */
    public int getInt(String key, int def){
        return get(key, def);
    }

    /**
     * Get argument as double value (if argument is really bool)
     * @param key Argument key
     * @return Double argument
     */
    public double getDouble(String key){
        return getDouble(key, 0.0);
    }

    /**
     * Get argument as double value with default value (if argument is really bool)
     * @param key Argument key
     * @param def Default value
     * @return Double argument or default value if argument not exists
     */
    public double getDouble(String key, double def){
        return get(key, def);
    }

    /**
     * Get argument as string value (if argument is really bool)
     * @param key Argument key
     * @return String argument
     */
    public String getString(String key){
        return getString(key, null);
    }

    /**
     * Get argument as string value with default value (if argument is really bool)
     * @param key Argument key
     * @param def Default value
     * @return String argument or default value if argument not exists
     */
    public String getString(String key, String def){
        return get(key, def);
    }

    public CommandContext snapshot(){
        return new CommandContext(new HashMap<>(this.arguments));
    }

    public void apply(CommandContext context){
        this.arguments.putAll(context.arguments);
    }

    @Override
    public String toString() {
        return String.format("CommandContext{values=%s}", arguments.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandContext){
            return Objects.equals(this.arguments, ((CommandContext)obj).arguments);
        }
        return false;
    }
}
