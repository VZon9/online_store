package ru.bbnshp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.Basket;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

    List<Basket> findByUserId(Integer userId);
}
