package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.Brand;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    Brand findByName(String name);

    boolean existsByName(String name);
}
