package org.fullstack4.springboot.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public abstract class BaseEntity {
    @CreatedDate
    @Column(name = "reg_date" , updatable = false)
    private LocalDateTime reg_date;
    @LastModifiedDate
    @Column(name = "modify_date" , nullable = true , updatable = true)
    private LocalDateTime modify_date;

    public void setModify_date(LocalDateTime modify_date){
        this.modify_date = LocalDateTime.now();
    }

}
