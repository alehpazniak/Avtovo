package pl.aeh.shop.mapper.cartLine;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.aeh.shop.domain.cartLine.CartLine;
import pl.aeh.shop.dto.TotalCartSize;

@Mapper
public interface CartLineMapper {

  CartLineMapper INSTANCE = Mappers.getMapper(CartLineMapper.class);

  TotalCartSize mapTo(CartLine cartLine);

}
