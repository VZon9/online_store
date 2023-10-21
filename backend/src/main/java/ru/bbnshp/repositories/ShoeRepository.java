package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.Shoe;

public interface ShoeRepository extends JpaRepository<Shoe, Integer> {
}
