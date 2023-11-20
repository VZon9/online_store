package ru.bbnshp.mapper;

import ru.bbnshp.dto.UserDto;
import ru.bbnshp.entities.Order;
import ru.bbnshp.entities.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toUserDto(User user){
        return new UserDto(user.getId(),
                            user.getLogin(),
                            user.getEmail(),
                            user.getOrderSet().stream().map(OrderMapper::toOrderDto).collect(Collectors.toSet()),
                            user.getBasketSet().stream().map(BasketMapper::toBasketDto).collect(Collectors.toSet()));
    }
}
