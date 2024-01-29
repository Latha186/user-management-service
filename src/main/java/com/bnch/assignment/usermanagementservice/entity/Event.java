package com.bnch.assignment.usermanagementservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;

    @Column(name = "date_created",nullable = false)
    @CreationTimestamp
    private Date dateCreated;

    @UpdateTimestamp
    @Column(name = "date_updated",nullable = false)
    private Date dateUpdated;

    @Column(nullable = false)
    private int created_by;
    @Column(nullable = false)
    private int updated_by;

}
