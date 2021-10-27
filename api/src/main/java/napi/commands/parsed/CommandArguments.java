package napi.commands.parsed;

import napi.commands.exception.ArgumentParseException;
import napi.commands.manager.CommandManager;

import java.util.*;

public final class CommandArguments {

    private final CommandManager manager;
    private List<String> arguments;
    private int pointer = -1;

    public CommandArguments(CommandManager manager, Collection<String> arguments){
        this.manager = manager;
        this.arguments = new ArrayList<>(arguments);
    }

    public CommandArguments(CommandManager manager, String[] arguments){
        this(manager, Arrays.asList(arguments));
    }

    public CommandManager getManager() {
        return manager;
    }

    public int getPointer(){
        return pointer;
    }

    public int getSize(){
        return arguments.size();
    }

    public List<String> getArguments(){
        return new ArrayList<>(arguments);
    }

    public boolean hasNext(){
        return pointer < arguments.size() - 1;
    }

    public boolean hasPrev(){
        return pointer > 0;
    }

    /**
     * Get first argument
     * @return First argument value
     * @throws ArgumentParseException If arguments has no elements
     */
    public String first() throws ArgumentParseException {
        if (arguments.size() > 0){
            return arguments.iterator().next();
        } else {
            throw outOfBounds();
        }
    }

    /**
     * Get last argument
     * @return Last argument value
     * @throws ArgumentParseException If arguments has no elements
     */
    public String last() throws ArgumentParseException {
        if (arguments.size() > 0){
            return arguments.get(arguments.size()-1);
        } else {
            throw outOfBounds();
        }
    }

    /**
     * Get next argument
     * @return Next argument
     * @throws ArgumentParseException if has no next argument
     */
    public String next() throws ArgumentParseException {
        if (!hasNext()){
            throw outOfBounds();
        }
        return arguments.get(++pointer);
    }

    /**
     * Get next element without throwing error if it not exists
     * @return Next argument or null if not exists
     */
    public String nextIfPresent(){
        return hasNext() ? arguments.get(++pointer) : null;
    }

    /**
     * Just get next argument and not increment pointer
     * @return Next argument or null if not exists
     */
    public String peek(){
        try{
            return hasNext() ? arguments.get(pointer + 1) : null;
        } catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * Get previous argument
     * @return Previous argument
     * @throws ArgumentParseException if has no previous argument
     */
    public String prev() throws ArgumentParseException {
        if (!hasPrev()){
            throw outOfBounds();
        }
        return arguments.get(--pointer);
    }

    public void add(String arg){
        this.arguments.add(arg);
    }

    /**
     * Remove some argument by index
     * @param index Index of the argument
     */
    public void remove(int index){
        arguments.remove(index);
        if (pointer >= index) pointer--;
    }

    /**
     * Remove some several arguments by start and end indexes
     * @param start Start index (inclusive)
     * @param end End index (inclusive)
     */
    public void remove(int start, int end){
        for (int i = start; i <= end; i++){
            remove(i);
        }
    }

    public void remove(CommandArguments snapshot){
        remove(snapshot.getPointer());
    }

    /**
     * Set values of another arguments instance to this instance
     * @param args CommandArguments instance
     */
    public void apply(CommandArguments args){
        this.arguments = args.arguments;
        this.pointer = args.pointer;
    }

    /**
     * Create copy of the current instance
     * @return Copy of the arguments
     */
    public CommandArguments snapshot(){
        CommandArguments snapshot = new CommandArguments(manager, this.getArguments());
        snapshot.pointer = this.pointer;
        return snapshot;
    }

    private ArgumentParseException outOfBounds() {
        return new ArgumentParseException("Arguments out of bound")
                .withMessage(manager.getMessages().getArgsOutOfBounds());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof CommandArguments)) return false;
        CommandArguments args = (CommandArguments) obj;
        return this.pointer == args.pointer && Objects.equals(this.arguments, args.arguments);
    }

    @Override
    public String toString() {
        return String.format("CommandArguments{pointer:%s, args:%s}", pointer, arguments.toString());
    }
}
