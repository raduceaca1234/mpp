package converter;

import dto.BaseDto;
import movieRental.core.model.Entity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends Entity<Long>, Dto extends BaseDto>
        implements Converter<Model, Dto> {
    public Set<Long> convertModelsToIDs(Set<Model> models){
        return models.stream()
                .map(Entity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Long> convertDTOsToIDs(Set<Dto> dtos){
        return dtos.stream()
                .map(BaseDto::getId)
                .collect(Collectors.toSet());
    }

    public Set<Dto> convertModelsToDtos(List<Model> models){
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toSet());
    }
}
