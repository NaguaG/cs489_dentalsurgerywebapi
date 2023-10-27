package miu.edu.dentalsurgery.advice;

import miu.edu.dentalsurgery.exception.AddressNotFoundException;
import miu.edu.dentalsurgery.exception.PatientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DentalSurgeryWebApiExceptionHandler {
    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handlePatientNotFoundException(PatientNotFoundException patientNotFoundException){
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put("Error:", patientNotFoundException.getMessage());
        return errorMessageMap;

    }
    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handlePatientNotFoundException(AddressNotFoundException addressNotFoundException){
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put("Error:", addressNotFoundException.getMessage());
        return errorMessageMap;

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleDataValidationErrors(MethodArgumentNotValidException methodArgumentNotValidException) {
        var errorMap = new HashMap<String, String>();
        methodArgumentNotValidException.getBindingResult()
                .getFieldErrors()
                .forEach(
                        error -> errorMap.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );
        return errorMap;
    }

    // Handle BadCredentialsException
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BadCredentialsException.class)
    public Map<String, String> handleUserAuthBadCredentialException(BadCredentialsException bcEx) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", bcEx.getMessage());
        errorMap.put("errorDisplayText", "Invalid Username and/or Password!");
        return errorMap;
    }
}
