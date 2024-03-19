package com.group.code.projectmanagement.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 100)
    private String nome;

    @Column(name = "datanascimento")
    private LocalDate dataNascimento;

    @Size(min = 11, max = 100)
    private String cpf;

    private Boolean funcionario;

    private Boolean gerente;

    @ManyToMany(mappedBy = "membros")
    private Set<Project> projetos;

}
