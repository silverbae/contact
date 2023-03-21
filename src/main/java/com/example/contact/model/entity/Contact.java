package com.example.contact.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    private String username;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    public Contact() {

    }
}

