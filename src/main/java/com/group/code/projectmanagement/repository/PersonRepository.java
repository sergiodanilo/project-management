package com.group.code.projectmanagement.repository;

import com.group.code.projectmanagement.model.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByFuncionarioTrueAndGerenteFalse();
    List<Person> findAllByGerenteTrue();

}
