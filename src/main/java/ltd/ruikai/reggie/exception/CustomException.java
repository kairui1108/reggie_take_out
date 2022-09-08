package ltd.ruikai.reggie.exception;

/**
 * 自定义业务异常
 * @ author  tanruikai
 * @ date  2022/9/4 22:11
 * @ version 1.0
 */
public class CustomException extends RuntimeException{

    public CustomException(String message){
        super(message);
    }

}
