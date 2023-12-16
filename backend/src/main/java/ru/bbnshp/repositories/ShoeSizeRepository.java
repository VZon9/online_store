package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.ShoeSize;

import java.util.Optional;

public interface ShoeSizeRepository extends JpaRepository<ShoeSize, Integer> {
    Optional<ShoeSize> findByShoeIdAndSizeValue(Integer shoeId, Integer size);
}
