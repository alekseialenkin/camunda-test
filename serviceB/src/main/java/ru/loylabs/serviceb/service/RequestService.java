package ru.loylabs.serviceb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.loylabs.serviceb.model.Request;
import ru.loylabs.serviceb.repository.RequestRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    @Transactional
    public void save(Request request) {
        requestRepository.save(request);
    }

    public Request getByOrderId(UUID uuid) {
        return requestRepository.findById(uuid).orElse(null);
    }
}
