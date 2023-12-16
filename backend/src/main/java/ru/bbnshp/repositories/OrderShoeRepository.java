package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.OrderShoe;

public interface OrderShoeRepository extends JpaRepository<OrderShoe, Integer> {

}
