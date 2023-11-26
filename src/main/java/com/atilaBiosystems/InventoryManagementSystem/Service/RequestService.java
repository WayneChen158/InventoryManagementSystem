package com.atilaBiosystems.InventoryManagementSystem.Service;

import java.util.List;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Request;

public interface RequestService { 
    List<Request> findAll();

    Request findById(int requestId);

    Request createRequest(Request request);
}
