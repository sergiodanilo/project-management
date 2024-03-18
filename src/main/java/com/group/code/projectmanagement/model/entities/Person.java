package com.group.code.projectmanagement.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "datanascimento")
    private LocalDate dataNascimento;

    @Max(value = 14)
    private String cpf;

    private Boolean funcionario;

    private Boolean gerente;

    @ManyToMany(mappedBy = "people")
    private Set<Project> projects;

}
