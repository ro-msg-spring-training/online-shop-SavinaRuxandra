package ro.msg.learning.shop.service.exception;

public class InvalidProductIdException extends RuntimeException {

    public InvalidProductIdException(String message) {
        super(message);
    }
}
