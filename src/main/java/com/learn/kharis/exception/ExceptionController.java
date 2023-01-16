package com.learn.kharis.exception;

import com.learn.kharis.common.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            UnauthorizedException.class,
            AuthenticationException.class
    })
    public ApiResponse<String> unauthorizedAccess(Exception ex) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .msg("UNAUTHORIZED ACCESS")
                .data(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(
            ForbiddenException.class
    )
    public ApiResponse<String> forbiddenAccess(Exception ex) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.FORBIDDEN.value())
                .msg("FORBIDDEN ACCESS")
                .data(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class,
            DataIntegrityViolationException.class
    })
    public ApiResponse<String> badRequest(Exception ex) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .msg("BAD REQUEST")
                .data(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            NotFoundException.class,
            NoHandlerFoundException.class
    })
    public ApiResponse<String> recordNotFound(Exception ex) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.NOT_FOUND.value())
                .msg("NOT FOUND")
                .data(ex.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> internalServerError(Exception ex) {
        return ApiResponse.<String>builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .msg("INTERNAL SERVER ERROR")
                .data(ex.getMessage())
                .build();
    }
}
