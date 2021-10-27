package napi.commands;

public final class ErrorMessages {

    private String permissionDeny;
    private String permissionDenyArg;
    private String argsOutOfBounds;
    private String argsTooMany;
    private String typeErrInt;
    private String typeErrDouble;
    private String typeErrBool;
    private String typeErrEnum;
    private String typeErrChoice;
    private String typeErrLocation;
    private String typeErrWorld;
    private String childNotFound;
    private String playerNotFound;
    private String serverNotFound;

    public ErrorMessages() {
        permissionDeny = "You don't have permission for this command";
        permissionDenyArg = "You don't have permission to use this argument";
        argsOutOfBounds = "The required argument not exists";
        argsTooMany = "Too many arguments";
        typeErrInt = "The argument must be integer";
        typeErrDouble = "The argument must be double";
        typeErrBool = "The argument must be boolean";
        typeErrEnum = "Invalid enum value. Select one of the suggest values";
        typeErrChoice = "Invalid value. Select one of the suggest values";
        typeErrLocation = "Invalid location parameter. You need to use numbers";
        typeErrWorld = "World with this name not exists";
        childNotFound = "Child command with this name not found";
        playerNotFound = "Player with this name not found";
        serverNotFound = "Server with this name not found";
    }

    public String getPermissionDeny() {
        return permissionDeny;
    }

    public void setPermissionDeny(String permissionDeny) {
        this.permissionDeny = permissionDeny;
    }

    public String getPermissionDenyArg() {
        return permissionDenyArg;
    }

    public void setPermissionDenyArg(String permissionDenyArg) {
        this.permissionDenyArg = permissionDenyArg;
    }

    public String getArgsOutOfBounds() {
        return argsOutOfBounds;
    }

    public void setArgsOutOfBounds(String argsOutOfBounds) {
        this.argsOutOfBounds = argsOutOfBounds;
    }

    public String getArgsTooMany() {
        return argsTooMany;
    }

    public void setArgsTooMany(String argsTooMany) {
        this.argsTooMany = argsTooMany;
    }

    public String getTypeErrInt() {
        return typeErrInt;
    }

    public void setTypeErrInt(String typeErrInt) {
        this.typeErrInt = typeErrInt;
    }

    public String getTypeErrDouble() {
        return typeErrDouble;
    }

    public void setTypeErrDouble(String typeErrDouble) {
        this.typeErrDouble = typeErrDouble;
    }

    public String getTypeErrBool() {
        return typeErrBool;
    }

    public void setTypeErrBool(String typeErrBool) {
        this.typeErrBool = typeErrBool;
    }

    public String getTypeErrEnum() {
        return typeErrEnum;
    }

    public void setTypeErrEnum(String typeErrEnum) {
        this.typeErrEnum = typeErrEnum;
    }

    public String getTypeErrChoice() {
        return typeErrChoice;
    }

    public void setTypeErrChoice(String typeErrChoice) {
        this.typeErrChoice = typeErrChoice;
    }

    public String getTypeErrLocation() {
        return typeErrLocation;
    }

    public void setTypeErrLocation(String typeErrLocation) {
        this.typeErrLocation = typeErrLocation;
    }

    public String getTypeErrWorld() {
        return typeErrWorld;
    }

    public void setTypeErrWorld(String typeErrWorld) {
        this.typeErrWorld = typeErrWorld;
    }

    public String getChildNotFound() {
        return childNotFound;
    }

    public void setChildNotFound(String childNotFound) {
        this.childNotFound = childNotFound;
    }

    public String getPlayerNotFound() {
        return playerNotFound;
    }

    public void setPlayerNotFound(String playerNotFound) {
        this.playerNotFound = playerNotFound;
    }

    public String getServerNotFound() {
        return serverNotFound;
    }

    public void setServerNotFound(String serverNotFound) {
        this.serverNotFound = serverNotFound;
    }
}
