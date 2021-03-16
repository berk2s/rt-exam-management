package com.rutinim.exam.management.web.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Malformed JSON request";
        return generateErrorTemplate(new ErrorTemplate( errorMessage, HttpStatus.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Could not resolve url";
        return generateErrorTemplate(new ErrorTemplate( errorMessage, HttpStatus.NOT_FOUND));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Error when writing JSON";
        return generateErrorTemplate(new ErrorTemplate( errorMessage, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Media type is not acceptable";
        return generateErrorTemplate(new ErrorTemplate(errorMessage, HttpStatus.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getContentType() + " media type is not supported";
        return generateErrorTemplate(new ErrorTemplate(errorMessage, HttpStatus.UNSUPPORTED_MEDIA_TYPE));
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getParameterName() + " parameter is missing";
        return generateErrorTemplate(new ErrorTemplate(errorMessage, HttpStatus.BAD_REQUEST));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = "Validation Error";
        return generateErrorTemplate(new ErrorTemplate(errorMessage, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = "Validation Error";
        return generateErrorTemplate(new ErrorTemplate(errorMessage, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        return generateErrorTemplate(new ErrorTemplate(ex, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<Object> handleNullPointerException(NullPointerException ex) {
        return generateErrorTemplate(new ErrorTemplate(ex, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(ExamNotFoundException.class)
    protected ResponseEntity<Object> handleStudentNotFoundException(ExamNotFoundException ex) {
        return generateErrorTemplate(new ErrorTemplate(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ExamTypeNotFoundException.class)
    protected ResponseEntity<Object> handleExamTypeNotFoundException(ExamTypeNotFoundException ex) {
        return generateErrorTemplate(new ErrorTemplate(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ExamFieldNotFoundException.class)
    protected ResponseEntity<Object> handleExamFieldNotFoundException(ExamFieldNotFoundException ex) {
        return generateErrorTemplate(new ErrorTemplate(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ExamPublisherNotFoundException.class)
    protected ResponseEntity<Object> handleExamPublisherNotFoundException(ExamPublisherNotFoundException ex) {
        return generateErrorTemplate(new ErrorTemplate(ex, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(PublisherSeriesNotFoundException.class)
    protected ResponseEntity<Object> handlePublisherSeriesNotFoundException(PublisherSeriesNotFoundException ex) {
        return generateErrorTemplate(new ErrorTemplate(ex, HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<Object> generateErrorTemplate(ErrorTemplate errorTemplate){
        return new ResponseEntity<>(errorTemplate, errorTemplate.getHttpStatus());
    }

}
