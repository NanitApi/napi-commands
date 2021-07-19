package napi.commands.exception;

public class ArgumentParseException extends Exception {

    private boolean sendHelp = true;
    private String message;

    public ArgumentParseException(String message) {
        super(message);
    }

    public ArgumentParseException(Throwable cause) {
        super(cause);
    }

    public ArgumentParseException(String message, boolean sendHelp) {
        super(message);
        this.sendHelp = sendHelp;
    }

    public ArgumentParseException(Throwable cause, boolean sendHelp) {
        super(cause);
        this.sendHelp = sendHelp;
    }

    public boolean isSendHelp() {
        return sendHelp;
    }

    public ArgumentParseException withMessage(String message){
        this.message = message;
        return this;
    }

    public String getLocalizedMessage(){
        return message;
    }

}
