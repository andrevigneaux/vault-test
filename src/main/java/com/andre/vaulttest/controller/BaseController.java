package com.andre.vaulttest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public abstract class BaseController {

    public ResponseEntity<Object> validateInsertAndRespond(HttpServletRequest req,
                                                                  Long id) {
        if (id == -1L) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Salary average constraint");
        }
        if (id != null) {
            try {
                URI createdURI = createURIForId(req.getRequestURI(), id.toString());
                return ResponseEntity.created(createdURI).build();
            } catch (URISyntaxException e) {
                return ResponseEntity.accepted().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    public URI createURIForId(String reqPath, String id) throws URISyntaxException {
        return new URI(reqPath + File.separator + id);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity handleConstraintViolation(NullPointerException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid Request Json");
    }

}
