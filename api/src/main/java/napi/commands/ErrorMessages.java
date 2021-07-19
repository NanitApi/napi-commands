package napi.commands;

public final class ErrorMessages {

    public static String PERMISSION_DENY;
    public static String PERMISSION_DENY_ARG;
    public static String ARGS_OUT_OF_BOUNDS;
    public static String ARGS_TOO_MANY;
    public static String TYPE_ERR_INT;
    public static String TYPE_ERR_DOUBLE;
    public static String TYPE_ERR_BOOL;
    public static String TYPE_ERR_ENUM;
    public static String TYPE_ERR_CHOICE;
    public static String TYPE_ERR_LOCATION;
    public static String TYPE_ERR_WORLD;
    public static String CHILD_NOT_FOUND;
    public static String PLAYER_NOT_FOUND;
    public static String SERVER_NOT_FOUND;

    private ErrorMessages() { }

    static {
        PERMISSION_DENY = "You don't have permission for this command";
        PERMISSION_DENY_ARG = "You don't have permission to use this argument";
        ARGS_OUT_OF_BOUNDS = "The required argument not exists";
        ARGS_TOO_MANY = "Too many arguments";
        TYPE_ERR_INT = "The argument must be integer";
        TYPE_ERR_DOUBLE = "The argument must be double";
        TYPE_ERR_BOOL = "The argument must be boolean";
        TYPE_ERR_ENUM = "Invalid enum value. Select one of the suggest values";
        TYPE_ERR_CHOICE = "Invalid value. Select one of the suggest values";
        TYPE_ERR_LOCATION = "Invalid location parameter. You need to use numbers";
        TYPE_ERR_WORLD = "World with this name not exists";
        CHILD_NOT_FOUND = "Child command with this name not found";
        PLAYER_NOT_FOUND = "Player with this name not found";
        SERVER_NOT_FOUND = "Server with this name not found";
    }

}
