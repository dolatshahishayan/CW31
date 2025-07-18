package ir.maktabsharif.cw31.exception;

public class UserWithSameUsernameExistsException extends RuntimeException{
    public UserWithSameUsernameExistsException(){
        super("User With Same Username Exists");
    }
}
