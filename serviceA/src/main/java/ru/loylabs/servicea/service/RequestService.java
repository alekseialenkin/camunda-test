package ru.loylabs.servicea.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.loylabs.servicea.exceptions.RequestNotFoundException;
import ru.loylabs.servicea.mapper.RequestMapper;
import ru.loylabs.servicea.model.Order;
import ru.loylabs.servicea.model.Request;
import ru.loylabs.servicea.model.Status;
import ru.loylabs.servicea.repository.RequestRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Transactional
    public Request create(Order order) {
        return requestRepository.save(requestMapper.toEntity(order));
    }

    public Request findByRequestId(UUID requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new RequestNotFoundException("Request with id " + requestId + " not found"));
    }

    @Transactional
    public Request createWithException(String message) {
        return requestRepository.save(new Request(Status.ERROR, message, null));
    }

    @Transactional
    public void changeStatus(UUID id, Status status) {
        Request request = findByRequestId(id);
        request.setStatus(status);
        requestRepository.save(request);
    }
}
