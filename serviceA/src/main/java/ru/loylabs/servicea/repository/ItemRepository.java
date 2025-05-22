package ru.loylabs.servicea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.loylabs.servicea.model.Item;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {
}
