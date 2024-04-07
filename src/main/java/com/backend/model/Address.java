package com.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "[Address]")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    @Id
    private String id;

    @Column(name = "type_address")
    private String type_address;

    @Column(name = "sub_address")
    private String sub_address;

    @Column(name = "name_address")
    private String name_address;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_acc")
    private Account account;
}
