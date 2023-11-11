package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.ShoeSize;
import ru.bbnshp.entities.composite_key.ShoeSizeKey;

public interface ShoeSizeRepository extends JpaRepository<ShoeSize, ShoeSizeKey> {
}
