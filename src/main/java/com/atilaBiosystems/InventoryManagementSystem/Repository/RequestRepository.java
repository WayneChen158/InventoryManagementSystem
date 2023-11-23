package com.atilaBiosystems.InventoryManagementSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Request;

public interface RequestRepository extends JpaRepository<Request, Integer>{
}