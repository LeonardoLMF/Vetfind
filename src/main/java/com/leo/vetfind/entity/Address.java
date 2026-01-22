package com.leo.vetfind.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Column(name = "logradouro")
    private String street;

    @Column(name = "numero", length = 10)
    private String number;

    @Column(name = "complemento")
    private String complement;

    @Column(name = "bairro")
    private String neighborhood;

    @Column(name = "cidade")
    private String city;

    @Column(name = "estado", length = 2)
    private String state;  // SP RJ etc...

    @Column(name = "cep", length = 8)
    private String zipCode;  // 12000100
}
