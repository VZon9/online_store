package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
}
