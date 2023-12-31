package com.atilaBiosystems.InventoryManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Request;
import com.atilaBiosystems.InventoryManagementSystem.Repository.RequestRepository;

@Service
public class RequestServiceImpl implements RequestService{

    private RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public Request findById(int requestId) {
        Request request = requestRepository.findById(requestId).orElse(null);
        return request;
    }

    @Override
    public Request createRequest(Request request) {
        return this.requestRepository.save(request);
    }

    @Override
    public boolean deleteRequestById(int requestId) {
        if (this.findById(requestId) != null) {
            this.requestRepository.deleteById(requestId);
            return true;
        }
        return false;
    }

    @Override
    public Request updateRequest(Request request) {
        this.requestRepository.save(request);
        return this.requestRepository.findById(request.getRequestId()).orElse(null);
    }
}
