package napi.commands.exception;

public class CommandException extends Exception {

    private String message;
    private boolean showHelp;

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, boolean showHelp) {
        super(message);
        this.showHelp = showHelp;
    }

    public CommandException withMessage(String message){
        this.message = message;
        return this;
    }

    public String getLocalizedMessage(){
        return message;
    }

    public boolean isSendHelp(){
        return showHelp;
    }
}
