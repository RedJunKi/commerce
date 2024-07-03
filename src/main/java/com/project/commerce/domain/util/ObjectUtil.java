package com.project.commerce.domain.util;

import java.util.NoSuchElementException;
import java.util.Optional;

public class ObjectUtil<T> {

    public static <T> T getObject(Optional<T> o) {
        return o.orElseThrow(() -> new NoSuchElementException("존재하지 않습니다."));
    }
}
