package mx.edu.ito.estres.infrastructure.exception;

import mx.edu.ito.estres.domain.exception.InvalidCredentialsException;
import mx.edu.ito.estres.domain.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            InvalidCredentialsException.class
    )
    public ResponseEntity<ErrorResponse>
    handleInvalidCredentials(
            InvalidCredentialsException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                401
                        )
                );
    }

    @ExceptionHandler(
            StudentNotFoundException.class
    )
    public ResponseEntity<ErrorResponse>
    handleStudentNotFound(
            StudentNotFoundException ex
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                404
                        )
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse>
    handleGeneric(
            Exception ex
    ) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        new ErrorResponse(
                                ex.getMessage(),
                                500
                        )
                );
    }
}
