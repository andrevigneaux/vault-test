package com.andre.vaulttest.util;

import java.util.Date;
import java.util.function.Consumer;

public class CoreUtil {

    public static <T> void setIfNotNull(Consumer<T> setterMethod, T value) {
        if (value != null){
            setterMethod.accept(value);
        }
    }

}
