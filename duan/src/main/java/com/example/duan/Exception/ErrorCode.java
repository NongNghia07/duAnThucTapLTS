package com.example.duan.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
@Getter
public enum ErrorCode {
    USER_EXISTED(1001,"User existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003,"User not existed",HttpStatus.NOT_FOUND),
    UNCATEGORIZED_EXEPTION(9999,"Uncategorized error",HttpStatus.INTERNAL_SERVER_ERROR),
    PASSWORD_INVALID(1002,"Password must be {min} characters",HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1004,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1005,"You have no permission",HttpStatus.FORBIDDEN),
    INVALID_DOB(1006,"You must at least {min} years old",HttpStatus.BAD_REQUEST)

    ;


    ErrorCode(int code, String message,HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

}

