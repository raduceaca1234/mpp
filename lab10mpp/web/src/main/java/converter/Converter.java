package converter;

import dto.BaseDto;

import movieRental.core.model.Entity;

public interface Converter<Model extends Entity<Long>, Dto extends BaseDto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
