package acntech.boundary;

import acntech.domain.Bar;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

@ControllerAdvice
public class ExceptionHandling {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Bar validationException(HttpServletRequest request, ValidationException exception) {
        return new Bar(exception.toString());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Bar methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        return new Bar(exception.toString());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseBody
    public Bar httpMediaTypeNotAcceptableException(HttpServletRequest request, HttpMediaTypeNotAcceptableException exception) {
        return new Bar(exception.toString());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Bar exception(HttpServletRequest request, Exception exception) {
        return new Bar(exception.toString());
    }
}
