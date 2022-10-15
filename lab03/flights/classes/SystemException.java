package classes;

public class SystemException extends Exception {
    private static final String DEFAULT_ERROR = "Something went wrong with the system!";
    private static final String UNKNOWN_COMMAND = "That command does not exist!";
    private static final String ARGUMENT_ERROR = "Wrong arguments were passed!";
    private static final String UNKNOWN_FLIGHT = "That flight does not exist!";
    private static final String UNKNOWN_RESERVATION = "That reservation does not exist!";
    private static final String FILE_NOT_FOUND = "That file does not exist!";

    public enum Type {
        UnknownCommand, ArgumentError, UnknownFlight, UnknownReservation, FileNotFound;
    }

    private static String getMessage(Type type) {
        String message = DEFAULT_ERROR;
        switch (type) {
            case UnknownCommand: 
                message = UNKNOWN_COMMAND;
                break;
            case ArgumentError:
                message = ARGUMENT_ERROR;
                break;
            case UnknownFlight:
                message = UNKNOWN_FLIGHT;
                break;
            case UnknownReservation:
                message = UNKNOWN_RESERVATION;
                break;
            case FileNotFound:
                message = FILE_NOT_FOUND;
                break;
            default:
                message = DEFAULT_ERROR;
        };

        return message;
    }

    public SystemException(Type type) {
        super(getMessage(type));
    }

    public SystemException() {
        super(DEFAULT_ERROR);
    }
}
