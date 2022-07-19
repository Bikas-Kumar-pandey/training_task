package com.user.exception;//package com.list.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//@ControllerAdvice
//public class practiceException {
//
//    @ExceptionHandler
//    public ResponseEntity<String> exception() throws Exception{
//        return new ResponseEntity("No value present",HttpStatus.NOT_FOUND);
//    }
//
//    public ResponseEntity<Object> exceptions(Exception e)throws Exception{
//
//        ErrorResponse.builder().errorMessage(e.getMessage()).time(LocalDateTime.now()).build();
//
//        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
//    }
//}
//
//
