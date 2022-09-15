package com.ciandt.summit.bootcamp2022.domain.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String STATUS = "status";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException exception) {
        //Get all errors
        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(STATUS, HttpStatus.BAD_REQUEST.value());
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArtistOrMusicNotFoundException.class)
    public ResponseEntity<Object> handleException(ArtistOrMusicNotFoundException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put(STATUS, HttpStatus.NO_CONTENT.value());
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<Object> getObjectResponseEntityBadRequest(String message) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put(STATUS, HttpStatus.BAD_REQUEST.value());
        body.put("message", message);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlaylistNotFoundException.class)
    public ResponseEntity<Object> handleException(PlaylistNotFoundException exception) {
        return getObjectResponseEntityBadRequest(exception.getMessage());
    }

    @ExceptionHandler(LengthValidationException.class)
    public ResponseEntity<Object> handleException(LengthValidationException exception) {
        return getObjectResponseEntityBadRequest(exception.getMessage());
    }

    @ExceptionHandler(MusicNotFoundException.class)
    public ResponseEntity<Object> handleException(MusicNotFoundException exception) {
        return getObjectResponseEntityBadRequest(exception.getMessage());
    }

    @ExceptionHandler(MusicNotInPlaylistException.class)
    public ResponseEntity<Object> handleException(MusicNotInPlaylistException exception) {
        return getObjectResponseEntityBadRequest(exception.getMessage());
    }

    @ExceptionHandler(MusicLimitException.class)
    public ResponseEntity<Object> handleException(MusicLimitException exception) {
        return getObjectResponseEntityBadRequest(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleException(UserNotFoundException exception){
        return getObjectResponseEntityBadRequest((exception.getMessage()));
    }
}
