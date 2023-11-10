package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.Type;


public interface TypeRepository extends JpaRepository<Type, Integer> {

    Type findByName(String name);

    boolean existsByName(String name);
}
