package com.openbanking.platform.model;

import com.openbanking.platform.storage.Status;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "AUTH")
@Data
@Setter
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String videoURL;

    private Status status = Status.UNVERIFIED;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updateDate;


}