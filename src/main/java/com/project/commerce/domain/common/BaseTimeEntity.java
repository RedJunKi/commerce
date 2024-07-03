package com.project.commerce.domain.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(BaseTimeEntityListener.class)
public class BaseTimeEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(name = "CREATED_AT")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @JoinColumn(name = "MODIFIED_AT")
    private Date modifiedAt;
}
