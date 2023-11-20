package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.ShoeSize;

public interface ShoeSizeRepository extends JpaRepository<ShoeSize, Integer> {

}
