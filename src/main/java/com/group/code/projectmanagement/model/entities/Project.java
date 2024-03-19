package com.group.code.projectmanagement.model.entities;

import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projeto")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 200)
    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataPrevisaoFim;

    private LocalDate dataFim;

    @Size(min = 3, max = 5000)
    private String descricao;

    @Enumerated(value = EnumType.STRING)
    private ProjectStatusEnum status;

    private Double orcamento;

    @Size(min = 3, max = 45)
    private String risco;

    @OneToOne
    @JoinColumn(name = "idgerente")
    private Person gerente;

    @ManyToMany
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa"))
    private Set<Person> people;

}
