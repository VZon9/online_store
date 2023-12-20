package ru.bbnshp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.bbnshp.entities.Basket;
import ru.bbnshp.entities.Size;

import java.util.List;
import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

    List<Basket> findByUserId(Integer userId);

    Optional<Basket> findByUserIdAndShoeIdAndSizeValue(Integer userId, Integer shoeId, Integer size);
}
