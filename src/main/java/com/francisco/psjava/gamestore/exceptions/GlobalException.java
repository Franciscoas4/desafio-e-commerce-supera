package com.francisco.psjava.gamestore.exceptions;

import com.francisco.psjava.gamestore.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDTO> notFound(NotFoundException e, HttpServletRequest req) {
        String error = "Item not found";
        HttpStatus stat = HttpStatus.NOT_FOUND;
        ErrorDTO err = new ErrorDTO(stat.value(), error, e.getMessage(), req.getRequestURI());
        return ResponseEntity.status(stat).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> methodArgumentInvalid(MethodArgumentNotValidException e, HttpServletRequest req) {
        String error = "Incomplete request";
        HttpStatus stat = HttpStatus.BAD_REQUEST;
        ErrorDTO err = new ErrorDTO(stat.value(), error, e.getAllErrors().toString(), req.getRequestURI());
        return ResponseEntity.status(stat).body(err);
    }
}
