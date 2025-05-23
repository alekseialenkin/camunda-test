package ru.loylabs.serviceb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.loylabs.serviceb.model.Request;

import java.util.UUID;

public interface RequestRepository extends JpaRepository<Request, UUID> {
}
