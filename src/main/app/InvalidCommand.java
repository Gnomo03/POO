package app;

public class InvalidCommand extends Exception {
    
    public InvalidCommand(String command, int lineNumber) {
        super("Error executing " + command + " in line " + lineNumber);
    }
    
}
