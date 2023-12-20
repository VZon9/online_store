package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.Size;

public interface SizeRepository extends JpaRepository<Size, Integer> {
}
