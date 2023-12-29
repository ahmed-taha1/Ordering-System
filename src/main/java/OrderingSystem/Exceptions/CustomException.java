package OrderingSystem.Exceptions;

public class CustomException extends Exception{
    private final int statusCode;
    public CustomException(int statusCode,String message){
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode() {
        return statusCode;
    }
}
