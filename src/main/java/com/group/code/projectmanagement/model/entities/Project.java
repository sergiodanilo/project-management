package com.group.code.projectmanagement.model.entities;

import com.group.code.projectmanagement.model.enums.ProjectStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "projeto")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Max(value = 200)
    private String nome;

    private LocalDate dataInicio;

    private LocalDate dataPrevisaoFim;

    private LocalDate dataFim;

    @Max(value = 5000)
    private String descricao;

    @Enumerated(value = EnumType.STRING)
    private ProjectStatusEnum status;

    private Double orcamento;

    @Max(value = 45)
    private String risco;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idgerente")
    private Person gerente;

    @ManyToMany
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "idprojeto"),
            inverseJoinColumns = @JoinColumn(name = "idpessoa"))
    private Set<Person> people;

}
