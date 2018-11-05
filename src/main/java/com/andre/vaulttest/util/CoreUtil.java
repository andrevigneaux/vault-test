package com.andre.vaulttest.util;

import org.springframework.http.ResponseEntity;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.function.Consumer;

public class CoreUtil {

    public static <T> void setIfNotNull(Consumer<T> setterMethod, T value) {
        if (value != null){
            setterMethod.accept(value);
        }
    }
}
