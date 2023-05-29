package com.lcw.electronic_store.exception;

import com.lcw.electronic_store.dtos.ApiResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandle {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandle.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage> resourceNotFoundExceptionHandle(ResourceNotFoundException exception){
        logger.info("Exception Handler invoked!!!!"
        );
        ApiResponseMessage responseMessage = ApiResponseMessage.builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .success(true)
                .build();
        return new ResponseEntity<>(responseMessage,HttpStatus.NOT_FOUND);
    }

    //method argument not valid exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException (MethodArgumentNotValidException exception){
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        Map<String ,Object> response = new HashMap<>();
        allErrors.stream().forEach(objectError -> {
            String message = objectError.getDefaultMessage();
            String field = ((FieldError) objectError).getField();
            response.put(field,message);
        });
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
