package ru.loylabs.servicea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.loylabs.servicea.model.Request;

import java.util.UUID;

public interface RequestRepository extends JpaRepository<Request, UUID> {
}
