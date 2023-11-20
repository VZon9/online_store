package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.Shoe;

import java.util.List;

public interface ShoeRepository extends JpaRepository<Shoe, Integer> {

    List<Shoe> findByColorIn(Iterable<String> colorList);

}
