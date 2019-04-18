package gromecode.lesson34.hw1.Exeptions;

public class InternalServerException extends Exception {
    public InternalServerException(String message) {
        super(message);
    }
}
