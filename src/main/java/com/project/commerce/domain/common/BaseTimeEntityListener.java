package com.project.commerce.domain.common;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

public class BaseTimeEntityListener {

    @PrePersist
    public void prePersist(BaseTimeEntity entity) {
        Date now = new Date();
        entity.setCreatedAt(now);
        entity.setModifiedAt(now);

    }

    @PreUpdate
    public void postPersist(BaseTimeEntity entity) {
        entity.setModifiedAt(new Date());
    }
}
