package ru.loylabs.servicea.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.loylabs.servicea.dto.CreateItemRequest;
import ru.loylabs.servicea.model.Item;

import java.util.List;

@Mapper
public interface ItemMapper {
    @Mapping(target = "id", ignore = true)
    Item toEntity(CreateItemRequest request);

    List<Item> toEntityList(List<CreateItemRequest> requests);
}
