package pl.aeh.shop.mapper.cart;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.aeh.shop.domain.Cart;

@Mapper
public interface CartMapper {

  CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

  CartResponse mapToCartResponse(Cart cart);


}
